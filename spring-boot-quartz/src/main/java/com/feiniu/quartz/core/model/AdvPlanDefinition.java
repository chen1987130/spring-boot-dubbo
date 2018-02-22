package com.feiniu.quartz.core.model;

/**
 * 高级计划
 * 
 * @author chensheng
 *
 */
public class AdvPlanDefinition extends PlanDefinition {
	private static final long serialVersionUID = -5483453339959646632L;
	/**
	 * cron表达式
	 */
	private String cronExpr;

	public AdvPlanDefinition() {
		cronExpr = null;
		type = "cron";
	}

	public String getCronExpr() {
		return cronExpr;
	}

	public void setCronExpr(String cronExpr) {
		this.cronExpr = cronExpr;
	}

	public String getDesc() {
		return cronExpr;
	}

}
