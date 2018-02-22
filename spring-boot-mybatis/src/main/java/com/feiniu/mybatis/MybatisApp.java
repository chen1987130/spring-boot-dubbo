package com.feiniu.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class MybatisApp {

	public static void main(String[] args) {
		SpringApplication.run(MybatisApp.class, args);
	}
}
