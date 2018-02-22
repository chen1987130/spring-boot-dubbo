package com.feiniu.session.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 通过redis同步session
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {

}
