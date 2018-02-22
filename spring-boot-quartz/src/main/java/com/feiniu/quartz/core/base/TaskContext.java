package com.feiniu.quartz.core.base;

import java.util.Date;

public interface TaskContext {
	public abstract Task getTask();

	public abstract Date getExecTime();

	public abstract TaskDataMap getTaskDataMap();
}
