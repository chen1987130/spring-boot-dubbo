package com.feiniu.dubbo.service.dubbo;

import org.springframework.stereotype.Component;

import com.feiniu.api.HelloApi;

@Component("helloApi")
public class HelloApiImpl implements HelloApi {

	@Override
	public Integer add(Integer num1, Integer num2) {
		return num1 + num2;
	}

}
