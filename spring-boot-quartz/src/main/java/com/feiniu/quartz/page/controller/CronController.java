package com.feiniu.quartz.page.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;

import com.feiniu.quartz.core.base.service.CronService;
import com.feiniu.quartz.core.model.TaskDefinition;
import com.feiniu.quartz.core.util.QuartzUtils;

/**
 * 定时任务基类
 * 
 * @author chensheng
 * 
 */
public abstract class CronController {

	@Autowired
	protected CronService cronService;

	/**
	 * 获取定时任务管理器
	 */
	public Scheduler getScheduler() {
		return QuartzUtils.getScheduler();
	}

	/**
	 * 获取所有任务
	 */
	public List<TaskDefinition> getAllTasks() {
		List<TaskDefinition> allTaskList = cronService.getAllTasks();
		return allTaskList;
	}

	/**
	 * 格式化日期
	 */
	public Date getDateFromString(String str) {
		try {
			DateFormat dateFormat;
			if (StringUtils.isBlank(str))
				return null;
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return dateFormat.parse(str);
		} catch (Exception e) {
			return null;
		}

	}
}
