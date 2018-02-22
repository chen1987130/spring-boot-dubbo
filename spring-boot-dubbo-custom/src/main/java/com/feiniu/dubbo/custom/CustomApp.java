package com.feiniu.dubbo.custom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableDiscoveryClient
@ImportResource({ "classpath:spring/spring-dubbo.xml" })
public class CustomApp {
	public static void main(String[] args) {
		SpringApplication.run(CustomApp.class, args);
	}
}