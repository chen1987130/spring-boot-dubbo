package com.feiniu.quartz;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.feiniu.quartz.core.base.load.spring.AppInit;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(App.class).listeners(new AppInit()).run(args);
	}
}
