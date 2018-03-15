package com.feiniu.mybatis.util.collection;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapUtil {

	public static void main(String[] args) {
		Map<String, String> m = new TreeMap<String, String>();
		m.put("1", "e");
		m.put("2", "f");
		m.put("3", "g");
		m.put("6", "a");
		m.put("4", "b");
		m.put("5", "c");
		for (Map.Entry<String, String> s : m.entrySet()) {
			System.out.println(s.getKey());
		}

		System.out.println("-----------------");
		Map<String, String> m2 = new LinkedHashMap<String, String>();
		m2.put("1", "a");
		m2.put("2", "b");
		m2.put("3", "c");
		m2.put("6", "f");
		m2.put("4", "d");
		m2.put("5", "e");

		for (Map.Entry<String, String> s : m2.entrySet()) {
			System.out.println(s.getKey());
		}

		Set<String> s = new HashSet<String>();
		s.add("1");
		s.add("2");
		s.add("3");
	}
}
