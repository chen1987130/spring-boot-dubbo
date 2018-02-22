package com.feiniu.quartz.core.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 异常恢复
 */
public class Recovery {

	public static List<Map<String, Object>> toList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("code", "Y");
		map1.put("name", "是");
		list.add(map1);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("code", "N");
		map2.put("name", "否");
		list.add(map2);

		return list;
	}

	public static String getView(String code) {
		String result = "";
		if (StringUtils.isBlank(code)) {
			return "";
		}
		if (StringUtils.equals(code, "Y")) {
			result = "是";
		} else if (StringUtils.equals(code, "N")) {
			result = "否";
		}

		return result;
	}
}
