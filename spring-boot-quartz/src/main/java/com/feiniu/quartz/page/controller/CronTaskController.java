package com.feiniu.quartz.page.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.feiniu.quartz.core.base.Task;
import com.feiniu.quartz.core.constant.Recovery;
import com.feiniu.quartz.core.model.PlanDefinition;
import com.feiniu.quartz.core.model.TaskDefinition;
import com.feiniu.quartz.core.util.ClassUtils;
import com.feiniu.quartz.core.vo.PlanDefinitionVo;
import com.feiniu.quartz.core.vo.TaskDefinitionVo;
import com.feiniu.quartz.page.util.BeanUtil;
import com.feiniu.quartz.page.util.JsonUtil;

/**
 * 定时任务管理类
 * 
 * @author chensheng
 */
@Controller
@RequestMapping(value = "/cronTask")
public class CronTaskController extends CronController {

	private static final Logger logger = LoggerFactory.getLogger(CronTaskController.class);

	/**
	 * 跳转任务列表
	 */
	@RequestMapping(value = "/toList")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("quartz/taskList");
		return mv;
	}

	/**
	 * 跳转任务编辑
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/toAddOrUpdate")
	public ModelAndView toAddOrUpdate(HttpServletRequest request, HttpServletResponse response, TaskDefinition task) {

		ModelAndView mv = new ModelAndView("quartz/taskEdit");
		String type = request.getParameter("type");

		// 前置任务列表
		List<TaskDefinition> list = getAllTasks();

		List<Map> taskList = new ArrayList<Map>();
		String id = request.getParameter("ID");
		if (StringUtils.isNotBlank(id)) {
			task = cronService.findTaskByCode(id);
			for (TaskDefinition model : list) {
				if (!model.getCode().equals(id)) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("code", model.getCode());
					map.put("name", model.getTaskName());
					taskList.add(map);
				}
			}
		} else {
			for (TaskDefinition model : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", model.getCode());
				map.put("name", model.getTaskName());
				taskList.add(map);
			}
		}

		mv.addObject("recoveryList", Recovery.toList());
		mv.addObject("taskList", taskList);
		mv.addObject("task", task);
		mv.addObject("type", type);
		return mv;
	}

	/**
	 * 跳转任务查看设置
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/toView")
	public ModelAndView toView(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, TaskDefinition task) {
		ModelAndView mv = new ModelAndView("quartz/taskView");
		List<TaskDefinition> list = getAllTasks();
		List<PlanDefinition> planList = new ArrayList<PlanDefinition>();
		List<PlanDefinitionVo> planVoList = new ArrayList<PlanDefinitionVo>();

		TaskDefinitionVo taskVo = new TaskDefinitionVo();

		String id = request.getParameter("ID");
		if (StringUtils.isNotBlank(id)) {
			task = cronService.findTaskByCode(id);
			if (task != null) {

				BeanUtils.copyProperties(task, taskVo);

				taskVo.setPrevTaskView("无");
				for (TaskDefinition model : list) {
					if (StringUtils.equals(taskVo.getPrevTask(), model.getCode())) {
						taskVo.setPrevTaskView(model.getTaskName());
						break;
					}
				}

				planList = cronService.getPlansByTaskCode(id);

				planVoList = BeanUtil.convertModelToVo(planList, PlanDefinitionVo.class);
			}

		}

		mv.addObject("task", taskVo);
		mv.addObject("planList", planVoList);
		return mv;
	}

	/**
	 * 新增一个任务
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, TaskDefinition task) {
		StringBuffer sb = new StringBuffer();
		Boolean flag = true;
		try {
			String type = request.getParameter("type");
			if (type.equals("Add")) {
				TaskDefinition oldTask = cronService.findTaskByCode(task.getCode());
				if (oldTask != null) {
					flag = false;
					sb.append("{\"flag\":\"false\",\"msg\":\"编码已存在!\"}");
				}
			}
			try {
				Class clazz = ClassUtils.classForName(task.getClazz());
				Object object = ClassUtils.newInstance(clazz);
				if (!(object instanceof Task)) {
					flag = false;
					sb.append("{\"flag\":\"false\",\"msg\":\"").append(task.getClazz()).append("不是合法的任务对象!\"}");
				} else {
					cronService.addTask(task);
				}
			} catch (ClassNotFoundException e) {
				flag = false;
				sb.append("{\"flag\":\"false\",\"msg\":\"").append("无法加载类").append(task.getClazz()).append("!\"}");
			} catch (Throwable t) {
				t.printStackTrace();
				flag = false;
				sb.append("{\"flag\":\"false\",\"msg\":\"").append(task.getClazz()).append("无法进行实例化!\"}");
			}
			if (flag) {
				sb.append("{\"flag\":\"true\"}");
			}
		} catch (Exception e) {
			sb.delete(0, sb.length());
			sb.append("{\"flag\":\"false\",\"msg\":\"操作失败!\"}");
			logger.error(e.getMessage(), e);
		} finally {
			JsonUtil.sendJson(response, sb);
		}
	}

	/**
	 * 删除任务
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		StringBuffer sb = new StringBuffer();
		try {
			String id = request.getParameter("ID");
			cronService.deleteTask(id);
			sb.append("{\"flag\":\"true\"}");
		} catch (Exception e) {
			sb.delete(0, sb.length());
			sb.append("{\"flag\":\"false\",\"msg\":\"删除失败!\"}");
			logger.error(e.getMessage(), e);
		} finally {
			JsonUtil.sendJson(response, sb);
		}
	}

	/**
	 * 获取所有任务列表
	 * 
	 */
	@RequestMapping(value = "/getTableList")
	public void getTableList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		try {
			List<TaskDefinition> modelList = new ArrayList<TaskDefinition>();
			int limit = Integer.parseInt(request.getParameter("limit"));
			int page = Integer.parseInt(request.getParameter("page"));
			if (page < 1) {
				page = 1;
			}
			// 所有任务
			List<TaskDefinition> list = getAllTasks();

			Collections.sort(list, new Comparator<TaskDefinition>() {
				@Override
				public int compare(TaskDefinition o1, TaskDefinition o2) {
					Long code1 = Long.parseLong(o1.getCode());
					Long code2 = Long.parseLong(o2.getCode());
					if (code1 < code2) {
						return 1;
					} else if (code1 > code2) {
						return -1;
					}
					return 0;
				}
			});

			// 分页
			for (int idx = (page - 1) * limit; idx < page * limit && idx < list.size(); idx++) {
				modelList.add(list.get(idx));
			}
			JsonUtil.sendJson(response, BeanUtil.convertModelToVo(modelList, TaskDefinitionVo.class), list.size());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 立即执行任务
	 */
	@RequestMapping(value = "/execNow")
	public void execNow(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		StringBuffer sb = new StringBuffer();
		try {
			String id = request.getParameter("ID");
			cronService.execTask(id);
			sb.append("{\"flag\":\"true\"}");
		} catch (Exception e) {
			sb.delete(0, sb.length());
			sb.append("{\"flag\":\"false\",\"msg\":\"执行失败!\"}");
			logger.error(e.getMessage(), e);
		} finally {
			JsonUtil.sendJson(response, sb);
		}
	}

	/**
	 * 删除计划
	 */
	@RequestMapping(value = "/cancelPlan")
	public void cancelPlan(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		StringBuffer sb = new StringBuffer();
		try {
			String name = request.getParameter("NAME");
			name = URLDecoder.decode(name, "utf-8");
			cronService.deletePlan(name);
			sb.append("{\"flag\":\"true\"}");
		} catch (Exception e) {
			sb.delete(0, sb.length());
			sb.append("{\"flag\":\"false\",\"msg\":\"删除失败!\"}");
			logger.error(e.getMessage(), e);
		} finally {
			JsonUtil.sendJson(response, sb);
		}

	}

}
