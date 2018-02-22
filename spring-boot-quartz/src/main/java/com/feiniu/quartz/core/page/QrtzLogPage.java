package com.feiniu.quartz.core.page;

import com.feiniu.framework.paginter.domain.PageBounds;


/**
 * 任务日志查询对象
 * 
 * @author chensheng
 *
 */
public class QrtzLogPage extends PageBounds {

	private static final long serialVersionUID = 8166592826567484706L;
	private String logTask;

	public String getLogTask() {
		return logTask;
	}

	public void setLogTask(String logTask) {
		this.logTask = logTask;
	}

}
