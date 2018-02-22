package com.feiniu.quartz.core.vo;

import com.feiniu.quartz.core.constant.Recovery;
import com.feiniu.quartz.core.model.TaskDefinition;

/**
 * 定时任务
 * 
 * @author chensheng
 *
 */
public class TaskDefinitionVo extends TaskDefinition {

	private static final long serialVersionUID = 1L;

	private String prevTaskView;

	public String getRecoveryView() {
		return Recovery.getView(getRecovery());
	}

	public String getPrevTaskView() {
		return prevTaskView;
	}

	public void setPrevTaskView(String prevTaskView) {
		this.prevTaskView = prevTaskView;
	}

}
