package com.feiniu.framework.paginter.config;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.feiniu.framework.paginter.PageInterceptor;
import com.feiniu.framework.paginter.dialect.MySQLDialect;

/**
 * mybatis start 拦截器配置
 * 
 * @author chensheng
 */
@Configuration
public class InterceptorConfig {

	private final String DIALECT_CLASS = MySQLDialect.class.getName();

	@Bean
	@ConditionalOnMissingBean(Interceptor.class)
	public Interceptor getInterceptor() {
		PageInterceptor interceptor = new PageInterceptor();
		interceptor.setDialectClass(DIALECT_CLASS);
		return interceptor;
	}

}
