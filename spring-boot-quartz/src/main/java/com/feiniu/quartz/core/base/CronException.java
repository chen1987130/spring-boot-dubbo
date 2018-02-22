package com.feiniu.quartz.core.base;

/**
 * 定时器错误
 * 
 * @author chensheng
 */
public class CronException extends Exception {

	private static final long serialVersionUID = 7516392734465619878L;

	public CronException() {
	}

	public CronException(String message) {
		super(message);
	}

	public CronException(Throwable cause) {
		super(cause);
	}

	public CronException(String message, Throwable cause) {
		super(message, cause);
	}

}
