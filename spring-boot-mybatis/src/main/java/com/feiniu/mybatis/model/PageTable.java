package com.feiniu.mybatis.model;

import java.util.List;

public class PageTable {

	private List<?> data;
	private int total;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
