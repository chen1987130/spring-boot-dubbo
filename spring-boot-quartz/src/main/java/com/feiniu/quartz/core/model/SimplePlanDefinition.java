package com.feiniu.quartz.core.model;

/**
 * 简单计划
 * 
 * @author chensheng
 *
 */
public class SimplePlanDefinition extends PlanDefinition {

	private static final long serialVersionUID = 6054151025022010859L;

	/**
	 * 重复次数
	 */
	private int repeatCount;
	/**
	 * 间隔
	 */
	private int interval;

	public SimplePlanDefinition() {
		repeatCount = -1;
		interval = 0;
		type = "simple";
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getDesc() {
		StringBuilder builder = new StringBuilder();
		builder.append("重复次数:").append(getRepeatCount());
		builder.append(" 间隔时间:").append(getInterval()).append("秒");
		return builder.toString();
	}

}
