package com.feiniu.mybatis.model.response;

public class ApiResult {

	public static final String SUCCESS = "0";
	public static final String FAIL = "1";

	public ApiResult() {

	}

	public ApiResult(String flag, String msg) {
		super();
		this.code = flag;
		this.msg = msg;
	}

	private String code = SUCCESS;

	private String msg = "";

	private Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
