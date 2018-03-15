package com.feiniu.mybatis.util.concurrent;

import java.util.concurrent.ConcurrentHashMap;

public class Concurrent {

	public static void main(String[] args) {
		ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<>();
		Boolean t = map.putIfAbsent("1", true);
		System.out.println(t);
		t = map.putIfAbsent("1", false);
		System.out.println(t);
		t = map.putIfAbsent("1", false);
		System.out.println(t);
	}

}
