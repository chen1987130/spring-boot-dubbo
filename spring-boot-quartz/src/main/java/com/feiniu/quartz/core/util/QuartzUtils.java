package com.feiniu.quartz.core.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.core.env.Environment;

import com.feiniu.quartz.core.base.plugin.JobHistoryPlugin;
import com.feiniu.quartz.core.base.provider.WebConnectionProvider;
import com.feiniu.quartz.core.model.AdvPlanDefinition;
import com.feiniu.quartz.core.model.ParameterDefinition;
import com.feiniu.quartz.core.model.PlanDefinition;
import com.feiniu.quartz.core.model.SimplePlanDefinition;
import com.feiniu.quartz.core.model.TaskDefinition;

/**
 * 定时器帮助类
 * 
 * @author chensheng
 *
 */
public class QuartzUtils {

	private QuartzUtils() {
	}

	private static Scheduler scheduler = null;

	public static Scheduler getScheduler() {
		return QuartzUtils.scheduler;
	}

	/**
	 * 初始化调度器
	 */
	public static Scheduler initScheduler(Environment env) throws SchedulerException {
		StdSchedulerFactory factory = new StdSchedulerFactory();
		Properties props = new Properties();
		props.put("org.quartz.scheduler.instanceName", "Beta");
		props.put("org.quartz.scheduler.instanceId", "AUTO");
		props.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
		props.put("org.quartz.threadPool.threadCount", env.getProperty("cron.threadCount", "10"));
		props.put("org.quartz.threadPool.threadPriority", "5");
		props.put("org.quartz.jobStore.misfireThreshold", String.valueOf(Integer.parseInt(env.getProperty("cron.misfireThreshold", "60")) * 1000));
		props.put("org.quartz.jobStore.class", org.quartz.impl.jdbcjobstore.JobStoreTX.class.getName());
		props.put("org.quartz.jobStore.driverDelegateClass", getDelegateByCode(env.getProperty("cron.driver", "stdJDBC")));
		props.put("org.quartz.jobStore.dataSource", "myDS");
		props.put("org.quartz.dataSource.myDS.connectionProvider.class", WebConnectionProvider.class.getName());
		props.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
		props.put("org.quartz.jobStore.isClustered", env.getProperty("cron.isClustered", "false"));
		props.put("org.quartz.jobStore.clusterCheckinInterval", "20000");
		factory.initialize(props);

		Scheduler scheduler = factory.getScheduler();
		JobHistoryPlugin logPlugin = new JobHistoryPlugin();
		scheduler.getListenerManager().addJobListener(logPlugin);

		QuartzUtils.scheduler = scheduler;

		return scheduler;
	}

	/**
	 * 根据任务编码，获取任务代理
	 */
	public static String getDelegateByCode(String code) {
		String delegate = (String) delegateMap.get(code);
		if (delegate == null)
			delegate = (String) delegateMap.get("stdJDBC");
		return delegate;
	}

	/**
	 * 获取正在执行的任务
	 */
	public static List<TaskDefinition> getExecutingTasks(Scheduler scheduler) {
		return convertJobsToTasks(getExecutingJobs(scheduler));
	}

	/**
	 * 获取正在执行的任务
	 */
	public static List<JobDetail> getExecutingJobs(Scheduler scheduler) {
		List<JobDetail> jobList = new ArrayList<JobDetail>();
		try {
			List<JobExecutionContext> jobDetails = scheduler.getCurrentlyExecutingJobs();
			JobDetail jobDetail;
			for (Iterator<JobExecutionContext> itr = jobDetails.iterator(); itr.hasNext(); jobList.add(jobDetail)) {
				JobExecutionContext job = itr.next();
				jobDetail = job.getJobDetail();
			}

		} catch (SchedulerException e) {
			log.error("error in getExecutingJobs", e);
		}
		return jobList;
	}

	/**
	 * 获取所有任务
	 */
	public static List<JobDetail> getAllJobs(Scheduler scheduler) {
		List<JobDetail> jobList = new ArrayList<JobDetail>();
		try {
			Set<JobKey> jobs = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(Scheduler.DEFAULT_GROUP));
			for (JobKey jobkey : jobs) {
				JobDetail jobDetail = scheduler.getJobDetail(jobkey);
				jobList.add(jobDetail);
			}

		} catch (SchedulerException e) {
			System.out.println("Problem get all jobs, schedule may be paused or stopped");
		}
		return jobList;
	}

	/**
	 * 获取所有任务
	 */
	public static List<TaskDefinition> getAllTasks(Scheduler scheduler) {
		return convertJobsToTasks(getAllJobs(scheduler));
	}

	/**
	 * JobDetail 转 TaskDefinition
	 */
	public static List<TaskDefinition> convertJobsToTasks(List<JobDetail> jobList) {
		List<TaskDefinition> taskList = new ArrayList<TaskDefinition>(jobList.size());
		for (JobDetail jobDetail : jobList) {
			TaskDefinition taskVO = convertJobToTask(jobDetail);
			if (StringUtils.isNotBlank(taskVO.getCode()))
				taskList.add(taskVO);
		}
		return taskList;
	}

	/**
	 * JobDetail 转 TaskDefinition
	 */
	public static TaskDefinition convertJobToTask(JobDetail job) {
		TaskDefinition task = new TaskDefinition();
		JobDataMap dataMap = job.getJobDataMap();
		task.setCode(dataMap.getString("cron.code"));
		task.setTaskName(dataMap.getString("cron.name"));
		task.setClazz(dataMap.getString("cron.class"));
		task.setPrevTask(dataMap.getString("cron.prevTask"));
		task.setRemark(job.getDescription());
		task.setRecovery(job.requestsRecovery() ? "Y" : "N");
		List<ParameterDefinition> parameters = new ArrayList<ParameterDefinition>();
		for (Iterator<String> itr = dataMap.keySet().iterator(); itr.hasNext();) {
			String key = itr.next();
			if (!"cron.code".equals(key) && !"cron.name".equals(key) && !"cron.class".equals(key) && !"cron.prevTask".equals(key)) {
				ParameterDefinition vo = new ParameterDefinition();
				vo.setParam_name(key);
				vo.setParam_value(dataMap.getString(key));
				parameters.add(vo);
			}
		}

		task.setParameters(parameters);
		return task;
	}

	/**
	 * 获取依赖该任务的前置任务列表
	 */
	public static List<TaskDefinition> findNextTasks(List<TaskDefinition> taskList, String currTaskCode) {
		List<TaskDefinition> nextTaskList = new ArrayList<TaskDefinition>();
		for (TaskDefinition taskVO : taskList) {
			if (StringUtils.equals(currTaskCode, taskVO.getPrevTask()))
				nextTaskList.add(taskVO);
		}

		return nextTaskList;
	}

	/**
	 * 根据编码获取任务
	 */
	public static TaskDefinition findTaskByCode(List<TaskDefinition> taskList, String taskCode) {
		for (TaskDefinition taskVO : taskList) {
			if (StringUtils.equals(taskCode, taskVO.getCode()))
				return taskVO;
		}

		return null;
	}

	/**
	 * 获取所有计划
	 */
	public static List<PlanDefinition> getAllPlans(Scheduler scheduler) {
		return convertTriggersToPlans(getAllTriggers(scheduler));
	}

	/**
	 * 根据计划名称获取计划
	 */
	public static PlanDefinition findPlanByName(Scheduler scheduler, String name) {
		List<PlanDefinition> plans = getAllPlans(scheduler);
		for (PlanDefinition plan : plans) {
			if (StringUtils.equals(plan.getPlanName(), name))
				return plan;
		}

		return null;
	}

	/**
	 * 获取所有计划触发器
	 */
	public static List<Trigger> getAllTriggers(Scheduler scheduler) {
		List<Trigger> triggerList = new ArrayList<Trigger>();
		try {
			List<String> groups = scheduler.getTriggerGroupNames();
			for (String group : groups) {
				Set<TriggerKey> keys = scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(group));
				for (TriggerKey key : keys) {
					Trigger trigger = scheduler.getTrigger(key);
					if (trigger != null)
						triggerList.add(trigger);
				}

			}

		} catch (SchedulerException e) {
			log.error("Error in getAllTriggers", e);
		}
		return triggerList;
	}

	/**
	 * 根据任务编码获取计划触发器
	 */
	public static List<Trigger> getTriggersFromJob(Scheduler scheduler, String jobCode) {
		List<Trigger> triggerList = new ArrayList<Trigger>();
		List<Trigger> allTriggerList = getAllTriggers(scheduler);
		for (Trigger trigger : allTriggerList) {
			JobKey jobKey = trigger.getJobKey();
			if (jobKey.getName().equals(jobCode) && jobKey.getGroup().equals("DEFAULT"))
				triggerList.add(trigger);
		}

		return triggerList;
	}

	/**
	 * 触发器转计划
	 */
	public static List<PlanDefinition> convertTriggersToPlans(List<Trigger> triggerList) {
		List<PlanDefinition> planList = new ArrayList<PlanDefinition>(triggerList.size());
		PlanDefinition plan;
		for (Iterator<Trigger> itr = triggerList.iterator(); itr.hasNext(); planList.add(plan)) {
			Trigger trigger = (Trigger) itr.next();
			String type = getTriggerType(trigger);
			plan = null;
			if ("simple".equals(type)) {
				SimplePlanDefinition simplePlan = new SimplePlanDefinition();
				SimpleTrigger st = (SimpleTrigger) trigger;
				simplePlan.setRepeatCount(st.getRepeatCount());
				simplePlan.setInterval((int) (st.getRepeatInterval() / 1000L));
				plan = simplePlan;
			} else {
				AdvPlanDefinition advPlan = new AdvPlanDefinition();
				advPlan.setCronExpr(((CronTrigger) trigger).getCronExpression());
				plan = advPlan;
			}

			TriggerKey triggerKey = trigger.getKey();
			JobKey jobKey = trigger.getJobKey();

			plan.setJobCode(jobKey.getName());
			plan.setMisFire(trigger.getMisfireInstruction());
			plan.setStartTime(trigger.getStartTime());
			plan.setStopTime(trigger.getEndTime());
			plan.setPrevFireTime(trigger.getPreviousFireTime());
			plan.setNextFireTime(trigger.getNextFireTime());
			plan.setPlanName(triggerKey.getName());
			plan.setRemark(trigger.getDescription());
		}

		return planList;
	}

	/**
	 * 获取计划触发器类型
	 */
	public static String getTriggerType(Trigger trigger) {
		String type = null;
		if (trigger == null)
			return null;
		if (trigger instanceof SimpleTrigger)
			type = "simple";
		else if (trigger instanceof CronTrigger)
			type = "cron";
		else
			type = trigger.getClass().getName();
		return type;
	}

	public static String formatDate(Date date) {
		if (date == null)
			return null;
		else
			return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static final String QUARTZ_SCHEDULER_INSNTANCE = "quartz.scheduler";
	public static final Log log = LogFactory.getLog(QuartzUtils.class);
	public static final String DEFAULT_DELEGATE = "stdJDBC";
	public static final Map<String, String> delegateMap;

	static {
		delegateMap = new HashMap<String, String>();
		delegateMap.put("db2v6", "org.quartz.impl.jdbcjobstore.DB2v6Delegate");
		delegateMap.put("db2v7", "org.quartz.impl.jdbcjobstore.DB2v7Delegate");
		delegateMap.put("db2v8", "org.quartz.impl.jdbcjobstore.DB2v8Delegate");
		delegateMap.put("hsqldb", "org.quartz.impl.jdbcjobstore.HSQLDBDelegate");
		delegateMap.put("mysql", "org.quartz.impl.jdbcjobstore.MSSQLDelegate");
		delegateMap.put("pointbase", "org.quartz.impl.jdbcjobstore.PointbaseDelegate");
		delegateMap.put("postgreSQL", "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate");
		delegateMap.put("oracle", "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate ");
		delegateMap.put("stdJDBC", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
	}
}
