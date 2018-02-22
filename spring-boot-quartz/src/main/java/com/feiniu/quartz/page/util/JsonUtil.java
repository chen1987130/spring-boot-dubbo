package com.feiniu.quartz.page.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.feiniu.framework.paginter.domain.PageList;

/**
 * Json 帮助类
 * 
 * @author chensheng
 *
 */
public class JsonUtil {

	/**
	 * fastjson输出
	 * 
	 * @说明:由于GJson转换通过属性非get方法，JSON转化改为fastjson
	 */
	public static String toJson(Object obj) {
		return JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * PageList 转 JsonTable
	 */
	@SuppressWarnings("rawtypes")
	public static JsonTable listToJson(PageList list) {
		JsonTable json = new JsonTable();
		if (list != null) {
			// 总记录数
			json.setData(list);
			// 数据
			json.setTotal(list.getPaginator().getTotalCount());
		}
		return json;
	}

	/**
	 * 发送json格式数据
	 */
	public static void sendJson(HttpServletResponse response, StringBuffer str) {
		PrintWriter out = null;
		try {
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			out = response.getWriter();
			out.print(str);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * 发送json格式数据
	 */
	public static void sendJson(HttpServletResponse response, String str) {
		PrintWriter out = null;
		try {
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			out = response.getWriter();
			out.print(str);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * 发送json格式数据
	 */
	@SuppressWarnings("rawtypes")
	public static void sendJson(HttpServletResponse response, PageList jsonList) {
		PrintWriter out = null;
		try {
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");

			out = response.getWriter();
			if (jsonList != null) {
				String json = toJson(jsonList);
				out.print(json);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * 发送json格式数据 - 分页信息
	 */
	@SuppressWarnings("rawtypes")
	public static void sendJson(HttpServletResponse response, List jsonList, int total) {
		PrintWriter out = null;
		try {
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");

			out = response.getWriter();
			if (jsonList != null) {
				JsonTable jsonTable = new JsonTable(jsonList, total);
				String json = toJson(jsonTable);
				out.print(json);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
