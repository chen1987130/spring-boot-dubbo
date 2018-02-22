package com.feiniu.quartz.page.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.feiniu.framework.paginter.domain.Order;
import com.feiniu.framework.paginter.domain.PageList;
import com.feiniu.quartz.core.page.QrtzLogPage;
import com.feiniu.quartz.page.model.QrtzLog;
import com.feiniu.quartz.page.service.QrtzLogService;
import com.feiniu.quartz.page.util.BeanUtil;
import com.feiniu.quartz.page.util.JsonUtil;
import com.feiniu.quartz.page.vo.QrtzLogVo;

/**
 * 定时任务日志
 * 
 * @author chensheng
 */
@Controller
@RequestMapping(value = "/cronLog")
public class CronLogController {
	private static final Logger logger = LoggerFactory.getLogger(CronLogController.class);

	@Autowired
	protected QrtzLogService logService;

	/**
	 * 获取任务执行日志
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getTableList")
	public void getTableList(HttpServletRequest request, HttpServletResponse response, QrtzLogPage page) {
		try {

			Map queryMap = new HashMap();
			if (StringUtils.isNotBlank(page.getLogTask())) {
				queryMap.put("logTask", page.getLogTask());
			}
			page.setOrders(Order.formString("id.desc"));

			PageList<QrtzLog> list = logService.queryForPage(queryMap, page);

			JsonUtil.sendJson(response, BeanUtil.convertModelToVo(list, QrtzLogVo.class), list.getPaginator().getTotalCount());
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}

	}

	/**
	 * 清空日志
	 */
	@RequestMapping(value = "/clearAll")
	public void clearAll(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		StringBuffer sb = new StringBuffer();
		try {
			logService.clearAll();
			sb.append("{\"flag\":\"true\"}");
		} catch (Exception e) {
			sb.delete(0, sb.length());
			sb.append("{\"flag\":\"false\",\"msg\":\"删除失败!\"}");
			logger.debug(e.getMessage(), e);
		} finally {
			JsonUtil.sendJson(response, sb);
		}
	}

	/**
	 * 删除日志
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		StringBuffer sb = new StringBuffer();
		try {
			String ID = request.getParameter("ID");
			logService.deleteById(Long.parseLong(ID));
			sb.append("{\"flag\":\"true\"}");
		} catch (Exception e) {
			sb.delete(0, sb.length());
			sb.append("{\"flag\":\"false\",\"msg\":\"删除失败!\"}");
			logger.debug(e.getMessage(), e);
		} finally {
			JsonUtil.sendJson(response, sb);
		}
	}

	/**
	 * 跳转日志列表
	 */
	@RequestMapping(value = "/toList")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("quartz/logList");
		return mv;
	}
}
