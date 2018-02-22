package com.feiniu.quartz.page.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.feiniu.quartz.core.model.TaskDefinition;
import com.feiniu.quartz.core.util.QuartzUtils;
import com.feiniu.quartz.core.vo.CrnServerVo;
import com.feiniu.quartz.page.model.ApiResult;
import com.feiniu.quartz.page.util.JsonUtil;

/**
 * 定时任务服务器管理类
 * 
 * @author chensheng
 */
@Controller
@RequestMapping(value = "/cronServer")
public class CronServerController extends CronController {

	private static final Logger logger = LoggerFactory.getLogger(CronServerController.class);

	@Autowired
	private Environment env;

	/**
	 * 跳转定时任务服务器状态列表
	 */
	@RequestMapping(value = "/toList")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response) {
		return list(request, response);
	}

	/**
	 * 定时任务服务器状态列表
	 */
	private ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("quartz/serverView");
		Scheduler scheduler = getScheduler();
		try {
			CrnServerVo vo = new CrnServerVo();
			SchedulerMetaData metaData = scheduler.getMetaData();
			vo.setNumTasksExecuted(String.valueOf(metaData.getNumberOfJobsExecuted()));

			if (metaData.getRunningSince() == null) {
				vo.setRunSince("");
			} else {
				vo.setRunSince(DateFormatUtils.format(metaData.getRunningSince(), "yyyy-MM-dd HH:mm:ss"));
			}

			String status = null;
			if (scheduler.isShutdown() || !scheduler.isStarted())
				status = "stoped";
			else if (scheduler.isInStandbyMode())
				status = "paused";
			else
				status = "started";
			vo.setStatus(status);
			vo.setThreadPoolSize(String.valueOf(metaData.getThreadPoolSize()));

			mv.addObject("vo", vo);
		} catch (SchedulerException e) {
			logger.error("can't get CronServer information.", e);
		}
		return mv;
	}

	/**
	 * 获取服务器状态
	 */
	@ResponseBody
	@RequestMapping(value = "/getServerStatus")
	public ApiResult getServerStatus(HttpServletRequest request, HttpServletResponse response) {
		ApiResult api = new ApiResult();
		try {
			CrnServerVo vo = new CrnServerVo();
			Scheduler scheduler = getScheduler();
			SchedulerMetaData metaData = scheduler.getMetaData();
			vo.setNumTasksExecuted(String.valueOf(metaData.getNumberOfJobsExecuted()));

			if (metaData.getRunningSince() == null) {
				vo.setRunSince("");
			} else {
				vo.setRunSince(DateFormatUtils.format(metaData.getRunningSince(), "yyyy-MM-dd HH:mm:ss"));
			}

			String status = null;
			if (scheduler.isShutdown() || !scheduler.isStarted())
				status = "stoped";
			else if (scheduler.isInStandbyMode())
				status = "paused";
			else
				status = "started";
			vo.setStatus(status);
			vo.setThreadPoolSize(String.valueOf(metaData.getThreadPoolSize()));
			api.setData(vo);
		} catch (SchedulerException e) {
			api.setFlag(ApiResult.FAIL);
			logger.error("can't get CronServer information.", e);
		}
		return api;
	}

	/**
	 * 获取任务执行情况
	 */
	@RequestMapping(value = "/getExecutingTaskList")
	public void getExecutingTaskList(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<TaskDefinition> modelList = new ArrayList<TaskDefinition>();
			int limit = Integer.parseInt(request.getParameter("limit"));
			int page = Integer.parseInt(request.getParameter("page"));
			if (page < 1) {
				page = 1;
			}

			List<TaskDefinition> list = QuartzUtils.getExecutingTasks(getScheduler());

			for (int idx = (page - 1) * limit; idx < page * limit && idx < list.size(); idx++) {
				modelList.add(list.get(idx));
			}

			JsonUtil.sendJson(response, modelList, list.size());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 暂停服务
	 */
	@RequestMapping(value = "/pause")
	public ModelAndView pause(HttpServletRequest request, HttpServletResponse response) {
		try {
			getScheduler().standby();
		} catch (SchedulerException e) {
			logger.error("error in pause cronServer", e);
		}
		return list(request, response);
	}

	/**
	 * 停止服务
	 */
	@RequestMapping(value = "/stop")
	public ModelAndView stop(HttpServletRequest request, HttpServletResponse response) {
		try {
			getScheduler().shutdown(true);
		} catch (SchedulerException e) {
			logger.error("error in pause cronServer", e);
		}
		return list(request, response);
	}

	/**
	 * 启动服务
	 */
	@RequestMapping(value = "/start")
	public ModelAndView start(HttpServletRequest request, HttpServletResponse response) {
		try {
			Scheduler scheduler = getScheduler();
			if (scheduler.isShutdown())
				scheduler = QuartzUtils.initScheduler(env);
			scheduler.start();
		} catch (Exception ex) {
			logger.error("error in start server.", ex);
		}
		return list(request, response);
	}

	/**
	 * 暂停计划
	 */
	@RequestMapping(value = "/pausePlan")
	public void pausePlan(HttpServletRequest request, HttpServletResponse response) {
		try {
			String code = request.getParameter("code");
			JobKey jobkey = new JobKey(code, "DEFAULT");
			getScheduler().pauseJob(jobkey);
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 恢复计划
	 */
	@RequestMapping(value = "/resumePlan")
	public void resumePlan(HttpServletRequest request, HttpServletResponse response) {
		try {
			String code = request.getParameter("code");
			JobKey jobkey = new JobKey(code, "DEFAULT");
			getScheduler().resumeJob(jobkey);
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
