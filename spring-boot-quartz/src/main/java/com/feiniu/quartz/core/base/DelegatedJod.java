package com.feiniu.quartz.core.base;

import java.util.Iterator;
import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.UnableToInterruptJobException;

import com.feiniu.quartz.core.imp.TaskContextImpl;
import com.feiniu.quartz.core.model.TaskDefinition;
import com.feiniu.quartz.core.util.ClassUtils;
import com.feiniu.quartz.core.util.QuartzUtils;

/**
 * 任务代理,所有任务都需要通过代理执行
 * 
 * @author chensheng
 *
 */
@DisallowConcurrentExecution
public class DelegatedJod implements InterruptableJob {

	public static final String PROP_CODE = "cron.code";
	public static final String PROP_NAME = "cron.name";
	public static final String PROP_CLASS = "cron.class";
	public static final String PROP_PREV_TASK = "cron.prevTask";
	private boolean finished;
	private boolean started;
	private Task task;

	public DelegatedJod() {
		finished = false;
		started = false;
		task = null;
	}

	public void interrupt() throws UnableToInterruptJobException {
		if (!started || finished)
			return;
		else
			return;
	}

	/**
	 * 执行任务
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		started = true;
		TaskContext context = new TaskContextImpl(ctx);
		TaskDataMap data = context.getTaskDataMap();
		String taskCode = data.getString("cron.code");
		String className = data.getString("cron.class");
		try {
			Class clazz = ClassUtils.classForName(className);
			Object object = ClassUtils.newInstance(clazz);
			task = (Task) object;
			task.execute(context);
		} catch (ClassNotFoundException e) {
			throw new JobExecutionException((new StringBuilder("无法加载类[")).append(className).append("]").toString(), e);
		} catch (TaskException e) {
			throw new JobExecutionException(e.getMessage());
		}

		List taskList = QuartzUtils.getAllTasks(ctx.getScheduler());
		List nextTaskList = QuartzUtils.findNextTasks(taskList, taskCode);
		for (Iterator itr = nextTaskList.iterator(); itr.hasNext();) {
			TaskDefinition taskVO = (TaskDefinition) itr.next();
			try {
				JobKey jobKey = new JobKey(taskVO.getCode(), "DEFAULT");
				ctx.getScheduler().triggerJob(jobKey);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
		finished = true;
	}

}
