package com.feiniu.quartz.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 计划基础类
 * 
 * @author chensheng
 *
 */
public abstract class PlanDefinition implements Serializable {
	private static final long serialVersionUID = 1673948435017982446L;
	public static final String SIMPLE_TYPE = "simple";
	public static final String ADV_TYPE = "cron";

	/**
	 * 计划开始时间
	 */
	private Date startTime;

	/**
	 * 计划截至时间
	 */
	private Date stopTime;
	/**
	 * 错过次数
	 */
	private int misFire;
	/**
	 * 计划名称
	 */
	private String planName;
	/**
	 * 任务编码
	 */
	private String jobCode;
	/**
	 * 下次触发时间
	 */
	private Date nextFireTime;
	/**
	 * 上次触发时间
	 */
	private Date prevFireTime;
	/**
	 * 计划类型
	 */
	protected String type;
	/**
	 * 备注
	 */
	private String remark;

	public PlanDefinition() {
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public int getMisFire() {
		return misFire;
	}

	public void setMisFire(int misFire) {
		this.misFire = misFire;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public Date getPrevFireTime() {
		return prevFireTime;
	}

	public void setPrevFireTime(Date prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

	public String getType() {
		return type;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public abstract String getDesc();

}
