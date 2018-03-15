package com.feiniu.mybatis.util.collection;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListUtil {
	public static void main(String[] args) throws UnsupportedEncodingException {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");

		List<String> list2 = new LinkedList<String>();
		list2.add("1");
		list2.add("2");
		list2.add("3");
	}
}
