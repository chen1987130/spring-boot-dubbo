package com.feiniu.oauth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Endpoints {

	Logger log = LoggerFactory.getLogger(Endpoints.class);

	@GetMapping("/product/{id}")
	public String getProduct(@PathVariable String id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("---------------product---------------");
		log.info("authentication:" + authentication.getAuthorities());
		log.info("principal:" + authentication.getPrincipal());
		log.info("credentials:" + authentication.getCredentials());
		log.info("details:" + authentication.getDetails());
		return "product id : " + id;
	}

	@GetMapping("/order/{id}")
	public String getOrder(@PathVariable String id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("---------------order---------------");
		log.info("authentication:" + authentication.getAuthorities());
		log.info("principal:" + authentication.getPrincipal());
		log.info("credentials:" + authentication.getCredentials());
		log.info("details:" + authentication.getDetails());
		return "order id : " + id;
	}
}
