package com.feiniu.quartz.core.model;

import java.io.Serializable;

/**
 * 任务参数
 * 
 * @author chensheng
 *
 */
public class ParameterDefinition implements Serializable {

	private static final long serialVersionUID = -2954720795374335088L;
	private String param_name;
	private String param_value;
	private boolean required;

	public ParameterDefinition() {
		param_name = null;
		param_value = null;
		required = false;
	}

	public ParameterDefinition(String param_name, String param_value) {
		required = false;
		this.param_name = param_name;
		this.param_value = param_value;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getParam_name() {
		return param_name;
	}

	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}

	public String getParam_value() {
		return param_value;
	}

	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}

}
