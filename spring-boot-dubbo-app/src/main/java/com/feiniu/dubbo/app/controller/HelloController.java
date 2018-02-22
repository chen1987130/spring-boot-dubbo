package com.feiniu.dubbo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feiniu.api.CustomApi;
import com.feiniu.api.HelloApi;

@RestController
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	public CustomApi customApi;

	@Autowired
	public HelloApi helloApi;

	@Autowired
	private Environment env;

	@RequestMapping("/")
	public String home() {
		return "Hello APP!";
	}

	@RequestMapping("/del")
	public Integer del(@RequestParam int num1, @RequestParam int num2) {
		return customApi.del(num1, num2);
	}

	@RequestMapping("/add")
	public Integer add(@RequestParam int num1, @RequestParam int num2) {
		return helloApi.add(num1, num2);
	}

	/**
	 * 读取配置服务器
	 */
	@RequestMapping("/properties")
	public String properties() {
		return env.getProperty("app.level");
	}

}
