package com.feiniu.quartz.page.model;

public class ApiResult {

	public static final String SUCCESS = "0";
	public static final String FAIL = "1";

	public ApiResult() {

	}

	public ApiResult(String flag, String msg) {
		super();
		this.flag = flag;
		this.msg = msg;
	}

	private String flag = SUCCESS;

	private String msg = "";

	private Object data;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
