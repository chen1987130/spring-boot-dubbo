package com.feiniu.session.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

	@GetMapping(value = "/set")
	public Map<String, Object> firstResp(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		String uuid = UUID.randomUUID().toString();
		request.getSession().setAttribute("uuid", uuid);
		map.put("uuid", uuid);
		return map;
	}

	@GetMapping(value = "/get")
	public Object sessions(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", request.getSession().getId());
		map.put("uuid", request.getSession().getAttribute("uuid"));
		return map;
	}
}
