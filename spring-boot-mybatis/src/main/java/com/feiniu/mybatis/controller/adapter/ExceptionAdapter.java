package com.feiniu.mybatis.controller.adapter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feiniu.mybatis.model.response.ApiResult;

@ControllerAdvice
public class ExceptionAdapter {

	@ResponseBody
	@ExceptionHandler(value = RuntimeException.class)
	public ApiResult handle(RuntimeException e) {
		return new ApiResult(ApiResult.FAIL, e.getMessage());
	}
}
