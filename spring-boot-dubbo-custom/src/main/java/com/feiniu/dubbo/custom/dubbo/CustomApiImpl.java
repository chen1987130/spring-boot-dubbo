package com.feiniu.dubbo.custom.dubbo;

import org.springframework.stereotype.Component;

import com.feiniu.api.CustomApi;

@Component("customApi")
public class CustomApiImpl implements CustomApi {

	@Override
	public Integer del(Integer num1, Integer num2) {
		return num1 - num2;
	}

}
