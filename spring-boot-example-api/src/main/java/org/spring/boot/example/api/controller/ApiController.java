package org.spring.boot.example.api.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

	@RequestMapping("/b2b")
	public String b2b(@RequestParam String key) {
		String result = "";

		try {
			File file = ResourceUtils.getFile("classpath:b2b/" + key);
			result = readFile(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	private String readFile(File file) {
		StringBuilder result = new StringBuilder();
		try {
			if (file.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String s = null;
				while ((s = br.readLine()) != null) {
					result.append(System.lineSeparator() + s);
				}
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();

	}

}
