package com.feiniu.quartz.core.vo;

/**
 * 定时任务服务器
 * 
 * @author chensheng
 *
 */
public class CrnServerVo {

	private String numTasksExecuted;
	private String runSince;
	private String status;
	private String statusText;
	private String threadPoolSize;

	public String getNumTasksExecuted() {
		return numTasksExecuted;
	}

	public void setNumTasksExecuted(String numTasksExecuted) {
		this.numTasksExecuted = numTasksExecuted;
	}

	public String getRunSince() {
		return runSince;
	}

	public void setRunSince(String runSince) {
		this.runSince = runSince;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusText() {
		statusText = "";
		if (status.equals("started")) {
			statusText = "开始";
		} else if (status.equals("stoped")) {
			statusText = "停止";
		} else if (status.equals("paused")) {
			statusText = "暂停";
		}
		return statusText;
	}

	public String getThreadPoolSize() {
		return threadPoolSize;
	}

	public void setThreadPoolSize(String threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}

}
