package com.feiniu.quartz.page.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.feiniu.quartz.core.model.PlanDefinition;
import com.feiniu.quartz.core.model.SimplePlanDefinition;
import com.feiniu.quartz.page.util.JsonUtil;

/**
 * 简单定时任务计划
 * 
 * @author chensheng
 */
@Controller
@RequestMapping(value = "/simplePlan")
public class SimplePlanController extends CronController {

	private static final Logger logger = LoggerFactory.getLogger(SimplePlanController.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	/**
	 * 跳转计划新增页面
	 */
	@RequestMapping(value = "/toAddOrUpdate")
	public ModelAndView toAddOrUpdate(HttpServletRequest request, HttpServletResponse response, SimplePlanDefinition entity) {
		ModelAndView mv = new ModelAndView("quartz/simplePlanEdit");
		mv.addObject("entity", entity);
		return mv;
	}

	/**
	 * 新增计划
	 */
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(HttpServletRequest request, HttpServletResponse response, SimplePlanDefinition entity) {
		StringBuffer sb = new StringBuffer();
		try {
			PlanDefinition oldPlan = cronService.findPlanByName(entity.getPlanName());
			if (oldPlan != null) {
				sb.append("{\"flag\":\"false\",\"msg\":\"计划名称已经存在!\"}");
			} else {
				cronService.addPlan(entity);
				sb.append("{\"flag\":\"true\",\"msg\":\"计划保存成功!\"}");
			}
		} catch (Exception e) {
			sb.delete(0, sb.length());
			sb.append("{\"flag\":\"false\",\"msg\":\"计划保存失败!\"}");
			logger.error(e.getMessage(), e);
		} finally {
			JsonUtil.sendJson(response, sb);
		}

	}

}
