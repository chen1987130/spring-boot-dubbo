package com.feiniu.quartz.page.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

/**
 * 对象帮助类
 * 
 * @author chensheng
 *
 */
public class BeanUtil {

	/**
	 * model转vo
	 * 
	 * @param modelList
	 * @param clazz
	 *            VO对象Class类型
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List convertModelToVo(List modelList, Class clazz) {
		List voList = new ArrayList();
		try {
			for (Object model : modelList) {
				Object vo = clazz.newInstance();
				BeanUtils.copyProperties(model, vo);
				voList.add(vo);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return voList;
	}

	/**
	 * 格式化查看字段
	 */
	public static String formatView(String object) {
		if (StringUtils.isNotBlank(object)) {
			return object;
		} else {
			return "-";
		}
	}

	/**
	 * 格式化查看字段
	 */
	public static String formatView(Long object) {
		if (object != null && object != 0L) {
			return object.toString();
		} else {
			return "-";
		}
	}

	/**
	 * 格式化查看字段
	 */
	public static String formatView(Integer object) {
		if (object != null && object != 0) {
			return object.toString();
		} else {
			return "-";
		}
	}

}
