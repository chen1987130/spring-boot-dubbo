package com.feiniu.data.rest.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cookie")
public class CookieController {

	@RequestMapping("/test")
	public void cookie(@RequestParam("browser") String browser, HttpServletRequest request, HttpSession session) {
		// 取出session中的browser
		Object sessionBrowser = session.getAttribute("browser");
		if (sessionBrowser == null) {
			System.out.println("不存在session，设置browser=" + browser);
			session.setAttribute("browser", browser);
		} else {
			System.out.println("存在session，browser=" + sessionBrowser.toString());
		}
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				System.out.println(cookie.getName() + " : " + cookie.getValue());
			}
		}
	}
}
