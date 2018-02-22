package com.feiniu.quartz.core.base;

public interface Task {

	/**
	 * 执行任务
	 */
	public void execute(TaskContext taskcontext) throws TaskException;

	/**
	 * 是否支持中断
	 */
	public boolean isSupportInterrupted();

	public void interrupt() throws TaskInterruptException;
}
