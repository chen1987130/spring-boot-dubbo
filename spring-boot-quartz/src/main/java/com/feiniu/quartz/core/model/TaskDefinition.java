package com.feiniu.quartz.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务
 * 
 * @author chensheng
 *
 */
public class TaskDefinition implements Serializable {

	private static final long serialVersionUID = 292919924562032112L;

	/**
	 * 任务编码
	 */
	protected String code;
	/**
	 * 任务名称
	 */
	protected String taskName;
	/**
	 * 执行类
	 */
	protected String clazz;
	/**
	 * 备注
	 */
	protected String remark;
	/**
	 * 异常恢复
	 */
	protected String recovery;
	/**
	 * 前置任务
	 */
	protected String prevTask;

	/**
	 * 参数
	 */
	@SuppressWarnings("rawtypes")
	protected List parameters;

	@SuppressWarnings("rawtypes")
	public TaskDefinition() {
		code = null;
		taskName = null;
		clazz = null;
		remark = null;
		recovery = null;
		prevTask = null;
		parameters = new ArrayList();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRecovery() {
		return recovery;
	}

	public void setRecovery(String recovery) {
		this.recovery = recovery;
	}

	public String getPrevTask() {
		return prevTask;
	}

	public void setPrevTask(String prevTask) {
		this.prevTask = prevTask;
	}

	@SuppressWarnings("rawtypes")
	public List getParameters() {
		return parameters;
	}

	@SuppressWarnings("rawtypes")
	public void setParameters(List parameters) {
		this.parameters = parameters;
	}

}
