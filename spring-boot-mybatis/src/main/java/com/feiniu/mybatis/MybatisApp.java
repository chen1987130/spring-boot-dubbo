package com.feiniu.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@ImportResource({ "classpath:spring/spring-transaction.xml" })
public class MybatisApp {

	public static void main(String[] args) {
		SpringApplication.run(MybatisApp.class, args);
	}
}
