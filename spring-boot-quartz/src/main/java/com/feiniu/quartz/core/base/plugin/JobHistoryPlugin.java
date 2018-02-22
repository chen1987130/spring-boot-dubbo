package com.feiniu.quartz.core.base.plugin;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

import com.feiniu.quartz.core.base.load.spring.ServerEnv;
import com.feiniu.quartz.page.service.QrtzLogService;

/**
 * Quartz日志插件
 * 
 * @author chensheng
 */
public class JobHistoryPlugin implements JobListener {

	private String jobToBeFiredMessage;
	private String jobSuccessMessage;
	private String jobFailedMessage;
	private QrtzLogService logMgr;

	public JobHistoryPlugin() {
		jobToBeFiredMessage = "任务[{0}]开始执行";
		jobSuccessMessage = "任务[{0}]成功执行完成,耗时{9}秒";
		jobFailedMessage = "任务[{0}]执行失败，失败原因：{8}";
		logMgr = (QrtzLogService) ServerEnv.getInstance().getBean(QrtzLogService.class);
	}

	public String getName() {
		return "JobHistoryPlugin";
	}

	public void jobExecutionVetoed(JobExecutionContext jobexecutioncontext) {
	}

	/**
	 * 准备执行
	 */
	public void jobToBeExecuted(JobExecutionContext context) {
		try {
			Trigger trigger = context.getTrigger();
			TriggerKey triggerKey = trigger.getKey();
			JobDetail jobDetail = context.getJobDetail();
			JobKey jobKey = jobDetail.getKey();

			String taskName = getTaskName(context);

			Object args[] = { taskName, jobKey.getGroup(), new Date(), triggerKey.getName(), triggerKey.getGroup(), trigger.getPreviousFireTime(),
					trigger.getNextFireTime(), new Integer(context.getRefireCount()) };
			String msg = MessageFormat.format(jobToBeFiredMessage, args);
			log(taskName, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getTaskName(JobExecutionContext context) {
		JobDataMap map = context.getMergedJobDataMap();
		return (String) map.get("cron.name");
	}

	/**
	 * 完成
	 */
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		try {
			Trigger trigger = context.getTrigger();
			TriggerKey triggerKey = trigger.getKey();
			JobDetail jobDetail = context.getJobDetail();
			JobKey jobKey = jobDetail.getKey();

			String msg = null;
			Object args[] = (Object[]) null;
			float second = (float) context.getJobRunTime() / 1000F;
			BigDecimal runTime = (new BigDecimal(second)).setScale(2, 4);
			String taskName = getTaskName(context);

			if (jobException != null) {
				String errMsg = jobException.getMessage();
				args = (new Object[] { taskName, jobKey.getGroup(), new Date(), triggerKey.getName(), triggerKey.getGroup(), trigger.getPreviousFireTime(),
						trigger.getNextFireTime(), new Integer(context.getRefireCount()), errMsg, runTime });
				msg = MessageFormat.format(jobFailedMessage, args);
			} else {
				String result = String.valueOf(context.getResult());
				args = (new Object[] { taskName, jobKey.getGroup(), new Date(), triggerKey.getName(), triggerKey.getGroup(), trigger.getPreviousFireTime(),
						trigger.getNextFireTime(), new Integer(context.getRefireCount()), result, runTime });
				msg = MessageFormat.format(jobSuccessMessage, args);
			}
			log(taskName, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void log(String taskName, String msg) throws Exception {
		logMgr.log(taskName, msg);
	}

}
