package com.feiniu.quartz.core.base.provider;

import java.sql.Connection;
import java.sql.SQLException;

import org.quartz.utils.ConnectionProvider;
import org.springframework.stereotype.Component;

import com.feiniu.quartz.core.base.load.spring.ServerEnv;

/**
 * Quartz数据库连接
 * 
 * @author chensheng
 *
 */
@Component
public class WebConnectionProvider implements ConnectionProvider {

	public WebConnectionProvider() {
	}

	@Override
	public Connection getConnection() throws SQLException {
		return ServerEnv.getInstance().getDataSource().getConnection();
	}

	@Override
	public void shutdown() throws SQLException {
	}

	@Override
	public void initialize() throws SQLException {

	}
}
