package com.feiniu.quartz.core.imp;

import java.util.Date;

import org.quartz.JobExecutionContext;

import com.feiniu.quartz.core.base.Task;
import com.feiniu.quartz.core.base.TaskContext;
import com.feiniu.quartz.core.base.TaskDataMap;

/**
 * 任务上下文
 * 
 * @author chensheng
 *
 */
public class TaskContextImpl implements TaskContext {

	private JobExecutionContext jobContext;
	private TaskDataMap dataMap;

	public TaskContextImpl(JobExecutionContext jobContext) {
		this.jobContext = jobContext;
		dataMap = new TaskDataMapImpl(jobContext.getMergedJobDataMap());
	}

	public Task getTask() {
		return (Task) jobContext.getJobInstance();
	}

	public Date getExecTime() {
		return jobContext.getFireTime();
	}

	public TaskDataMap getTaskDataMap() {
		return dataMap;
	}

}
