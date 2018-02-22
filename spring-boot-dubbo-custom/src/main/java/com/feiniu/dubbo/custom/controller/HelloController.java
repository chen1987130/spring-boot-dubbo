package com.feiniu.dubbo.custom.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@RefreshScope
public class HelloController {

	@RequestMapping("/")
	public String home() {
		return "Hello Custom!";
	}

	@Value("${spring.frequency}")
	private String frequency;

	@GetMapping("/properties")
	public String properties() {
		return frequency;
	}
}
