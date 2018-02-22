package com.feiniu.quartz.core.base.load.spring;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;

/**
 * 系统环境变量<br/>
 * 
 * @author chensheng
 * 
 */

public class ServerEnv {

	private ApplicationContext context;

	private ServerEnv() {
	}

	public static ServerEnv getInstance() {
		return instance;
	}

	public void init(ApplicationContext context) {
		this.context = context;
	}

	/**
	 * 获取数据库连接池
	 */
	public DataSource getDataSource() {
		return (DataSource) context.getBean(DataSource.class);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getBean(Class clazz) {
		return context.getBean(clazz);
	}

	private static final ServerEnv instance = new ServerEnv();
}
