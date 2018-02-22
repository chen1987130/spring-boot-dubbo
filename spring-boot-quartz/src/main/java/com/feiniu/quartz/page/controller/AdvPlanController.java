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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.feiniu.quartz.core.model.AdvPlanDefinition;
import com.feiniu.quartz.core.model.PlanDefinition;
import com.feiniu.quartz.page.model.ApiResult;

/**
 * 高级定时任务计划
 * 
 * @author chensheng
 */
@Controller
@RequestMapping(value = "/advPlan")
public class AdvPlanController extends CronController {

	private static final Logger logger = LoggerFactory.getLogger(AdvPlanController.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	/**
	 * 跳转高级计划编辑界面
	 */
	@RequestMapping(value = "/toAddOrUpdate")
	public ModelAndView toAddOrUpdate(HttpServletRequest request, HttpServletResponse response, AdvPlanDefinition entity) {
		ModelAndView mv = new ModelAndView("quartz/advPlanEdit");
		entity.setCronExpr("0 0 22 ? * *");
		mv.addObject("entity", entity);
		return mv;
	}

	/**
	 * 添加高级计划
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdate")
	public ApiResult addOrUpdate(HttpServletRequest request, HttpServletResponse response, AdvPlanDefinition entity) {
		ApiResult api = new ApiResult();
		try {
			PlanDefinition oldPlan = cronService.findPlanByName(entity.getPlanName());
			if (oldPlan != null) {
				api.setMsg("计划名称已经存在!");
				return new ApiResult(ApiResult.FAIL, "计划名称已经存在!");
			} else {
				cronService.addPlan(entity);
				return new ApiResult(ApiResult.SUCCESS, "计划保存成功!");
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return new ApiResult(ApiResult.FAIL, "计划保存失败!");
		}

	}
}
