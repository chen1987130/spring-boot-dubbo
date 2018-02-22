package com.feiniu.quartz.core.base;

/**
 * 任务终端异常
 * 
 * @author chensheng
 *
 */
public class TaskInterruptException extends Exception {

	private static final long serialVersionUID = -5294726014896831439L;

	public TaskInterruptException() {
	}

	public TaskInterruptException(String message) {
		super(message);
	}

	public TaskInterruptException(Throwable cause) {
		super(cause);
	}

	public TaskInterruptException(String message, Throwable cause) {
		super(message, cause);
	}

}
