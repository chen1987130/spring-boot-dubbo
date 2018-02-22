package com.feiniu.oauth.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private InMemoryUserDetailsManager userDetailsManager;

	@PostMapping("/add")
	public User getProduct(@RequestParam String username, @RequestParam String password, @RequestParam String authorities) {
		User user = new User(username, password, Arrays.asList(new SimpleGrantedAuthority(authorities)));
		userDetailsManager.createUser(user);
		return user;
	}
}
