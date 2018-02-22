package com.feiniu.data.rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/template")
public class TemplateController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/findAll")
	public List<Map<String, Object>> findAll() {
		return jdbcTemplate.queryForList("select * from Country");
	}

}
