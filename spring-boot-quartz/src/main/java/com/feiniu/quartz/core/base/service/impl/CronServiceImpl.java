package com.feiniu.quartz.core.base.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Service;

import com.feiniu.quartz.core.base.CronException;
import com.feiniu.quartz.core.base.DelegatedJod;
import com.feiniu.quartz.core.base.service.CronService;
import com.feiniu.quartz.core.model.AdvPlanDefinition;
import com.feiniu.quartz.core.model.PlanDefinition;
import com.feiniu.quartz.core.model.SimplePlanDefinition;
import com.feiniu.quartz.core.model.TaskDefinition;
import com.feiniu.quartz.core.util.QuartzUtils;

/**
 * 定时器服务
 * 
 * @author chensheng
 */
@Service
public class CronServiceImpl implements CronService {

	private static Log log = LogFactory.getLog(CronServiceImpl.class);

	public CronServiceImpl() {
	}

	/**
	 * 获取调度器
	 */
	protected Scheduler getScheduler() {
		return QuartzUtils.getScheduler();
	}

	/**
	 * 获取所有任务
	 */
	public List<TaskDefinition> getAllTasks() {
		return QuartzUtils.getAllTasks(getScheduler());
	}

	/**
	 * 根据编码获取任务
	 */
	public TaskDefinition findTaskByCode(String code) {
		return QuartzUtils.findTaskByCode(getAllTasks(), code);
	}

	/**
	 * 新增任务
	 */
	public void addTask(TaskDefinition task) throws CronException {
		JobDetail job = JobBuilder.newJob(DelegatedJod.class).withIdentity(task.getCode(), "DEFAULT").withDescription(task.getRemark())
				.requestRecovery("Y".equals(task.getRecovery())).storeDurably(true).build();

		JobDataMap dataMap = job.getJobDataMap();
		dataMap.put("cron.code", task.getCode());
		dataMap.put("cron.name", task.getTaskName());
		dataMap.put("cron.class", task.getClazz());
		if (StringUtils.isNotBlank(task.getPrevTask()) && !StringUtils.equals(task.getCode(), task.getPrevTask())) {
			dataMap.put("cron.prevTask", task.getPrevTask());
		}
		try {
			getScheduler().addJob(job, true);
		} catch (SchedulerException e) {
			log.error((new StringBuilder("can't add [")).append(task.getCode()).append("] to scheduler.").toString(), e);
			throw new CronException(e);
		}
	}

	/**
	 * 删除任务
	 */
	public void deleteTask(String taskCode) throws CronException {
		try {
			JobKey jobKey = new JobKey(taskCode, "DEFAULT");
			getScheduler().deleteJob(jobKey);
		} catch (SchedulerException e) {
			log.error((new StringBuilder("can't remove [")).append(taskCode).append("] from scheduler.").toString(), e);
			throw new CronException(e);
		}
	}

	/**
	 * 执行任务
	 */
	public void execTask(String taskCode) throws CronException {
		try {
			JobKey jobKey = new JobKey(taskCode, "DEFAULT");
			getScheduler().triggerJob(jobKey);
		} catch (SchedulerException e) {
			log.error((new StringBuilder("can't execute [")).append(taskCode).append("]").toString(), e);
			throw new CronException(e);
		}
	}

	/**
	 * 获取所有计划
	 */
	public List<PlanDefinition> getAllPlans() {
		return QuartzUtils.getAllPlans(getScheduler());
	}

	/**
	 * 根据计划名称获取单个计划
	 */
	public PlanDefinition findPlanByName(String name) {
		return QuartzUtils.findPlanByName(getScheduler(), name);
	}

	/**
	 * 根据任务编码获取计划
	 */
	public List<PlanDefinition> getPlansByTaskCode(String code) {
		return QuartzUtils.convertTriggersToPlans(QuartzUtils.getTriggersFromJob(getScheduler(), code));
	}

	/**
	 * 新增计划
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addPlan(PlanDefinition planDefinition) throws CronException {
		if (planDefinition == null)
			return;
		TriggerBuilder trigger = TriggerBuilder.newTrigger();

		JobKey jobKey = new JobKey(planDefinition.getJobCode(), "DEFAULT");

		if (planDefinition instanceof SimplePlanDefinition) {
			SimplePlanDefinition plan = (SimplePlanDefinition) planDefinition;

			trigger.withIdentity(plan.getPlanName(), "DEFAULT").withSchedule(
					SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(plan.getInterval()).withRepeatCount(plan.getRepeatCount()));

		} else {
			AdvPlanDefinition plan = (AdvPlanDefinition) planDefinition;

			trigger.withIdentity(plan.getPlanName(), "DEFAULT").withSchedule(CronScheduleBuilder.cronSchedule(plan.getCronExpr()));
		}
		if (planDefinition.getStartTime() != null)
			trigger.startAt(planDefinition.getStartTime());
		if (planDefinition.getStopTime() != null)
			trigger.endAt(planDefinition.getStopTime());
		// trigger.setVolatility(false);
		trigger.withDescription(planDefinition.getRemark());
		trigger.forJob(jobKey);

		try {
			getScheduler().scheduleJob(trigger.build());
		} catch (SchedulerException e) {
			log.error("error in scheduleJob", e);
			throw new CronException(e);
		}
	}

	/**
	 * 删除计划
	 */
	public void deletePlan(String planName) throws CronException {
		Scheduler scheduler = getScheduler();
		try {
			TriggerKey trigger = new TriggerKey(planName, "DEFAULT");
			scheduler.unscheduleJob(trigger);
		} catch (SchedulerException e) {
			log.error((new StringBuilder("can't cancel [")).append(planName).append("]").toString(), e);
			throw new CronException(e);
		}
	}

}
