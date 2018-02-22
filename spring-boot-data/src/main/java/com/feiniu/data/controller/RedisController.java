package com.feiniu.data.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@RequestMapping("/putRedisValue")
	public String putRedisValue(@RequestParam String key, @RequestParam String data) {
		redisTemplate.opsForValue().set(key, data);
		return "success";
	}

	@RequestMapping("/getRedisValue")
	public String getRedisValue(@RequestParam String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@RequestMapping("/putRedisExpire")
	public String putRedisValue(@RequestParam String key, @RequestParam Integer expire) {
		redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		return "success";
	}

	@RequestMapping("/getRedisExpire")
	public String putRedisValue(@RequestParam String key) {
		// 获取有效期
		long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
		return String.valueOf(expire);
	}
}
