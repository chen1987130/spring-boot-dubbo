package com.feiniu.quartz.core.vo;

import java.text.SimpleDateFormat;

import com.feiniu.quartz.core.model.PlanDefinition;

/**
 * 定时任务计划
 * 
 * @author chensheng
 *
 */
public class PlanDefinitionVo extends PlanDefinition {

	private static final long serialVersionUID = 1L;
	private String desc;

	private String type;

	public String getStartTimeView() {
		String view = "";
		if (getStartTime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			view = sdf.format(getStartTime());
		}
		return view;
	}

	public String getStopTimeView() {
		String view = "";
		if (getStopTime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			view = sdf.format(getStopTime());
		}
		return view;
	}

	public String getNextFireTimeView() {
		String view = "";
		if (getNextFireTime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			view = sdf.format(getNextFireTime());
		}
		return view;
	}

	public String getPrevFireTimeView() {
		String view = "";
		if (getPrevFireTime() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			view = sdf.format(getPrevFireTime());
		}
		return view;
	}

	public String getTypeView() {
		String typeView = "";
		if ("simple".equals(type)) {
			typeView = "简单计划";
		} else if ("cron".equals(type)) {
			typeView = "高级计划";
		}
		return typeView;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
