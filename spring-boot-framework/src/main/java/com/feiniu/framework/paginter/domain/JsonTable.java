package com.feiniu.framework.paginter.domain;

import java.util.List;

/**
 * dataTable列表json
 */
public class JsonTable<T> {

	public JsonTable() {
		super();
	}

	public JsonTable(List<T> data, int total) {
		super();
		this.total = total;
		this.data = data;
	}

	private int total = 0;

	/**
	 * /** 数据
	 */
	private List<T> data;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
