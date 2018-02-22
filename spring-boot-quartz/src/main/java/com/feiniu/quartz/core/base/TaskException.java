package com.feiniu.quartz.core.base;

/**
 * 任务异常
 * 
 * @author chensheng
 *
 */
public class TaskException extends Exception {

	private static final long serialVersionUID = -8061110327362310397L;

	public TaskException() {
	}

	public TaskException(String message) {
		super(message);
	}

	public TaskException(Throwable cause) {
		super(cause);
	}

	public TaskException(String message, Throwable cause) {
		super(message, cause);
	}

}
