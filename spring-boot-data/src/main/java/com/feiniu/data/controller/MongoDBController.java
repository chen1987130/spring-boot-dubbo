package com.feiniu.data.controller;

import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feiniu.data.mongo.model.City;
import com.feiniu.data.mongo.model.Department;
import com.feiniu.data.mongo.model.User;
import com.feiniu.data.mongo.repository.CityRepository;
import com.feiniu.data.mongo.repository.DepartmentRepository;
import com.feiniu.data.mongo.repository.UserRepository;

@RestController
@RequestMapping("/mongodb")
public class MongoDBController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private CityRepository cityRepository;

	@RequestMapping("/save")
	public String save(@RequestParam Long id, @RequestParam String username, @RequestParam Integer age) {
		User user = new User(id, username, age);
		userRepository.save(user);
		return "success";
	}

	@RequestMapping("/find")
	public User save(@RequestParam Long id) throws ScriptException {
		return userRepository.findOne(id);
	}

	@RequestMapping("/findByUsername")
	public User save(@RequestParam String username) {
		return userRepository.findByUsername(username);
	}

	@RequestMapping("/saveDepartment")
	public String saveDepartment(@RequestParam String name) {
		Department department = new Department(name);
		departmentRepository.save(department);
		return "success";
	}

	@RequestMapping("/saveCity")
	public String saveCity(@RequestParam String name) {
		City city = new City(name);
		cityRepository.save(city);
		return "success";
	}
}
