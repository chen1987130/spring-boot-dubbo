package com.feiniu.framework.paginter.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 包含“分页”信息的List
 */
public class PageOffsetList<E> implements Serializable {
	private static final long serialVersionUID = 1412759446332294208L;

	private List<E> datas;

	private int limit;

	private int offset;

	private Object param;

	public PageOffsetList() {
		datas = new ArrayList<E>();
	}

	public PageOffsetList(int limit, int offset) {
		super();
		datas = new ArrayList<E>();
		this.limit = limit;
		this.offset = offset;
	}

	@SuppressWarnings("unchecked")
	public PageOffsetList(Collection<? extends E> c) {
		if (c != null) {
			datas = (List<E>) c;
		} else {
			datas = new ArrayList<E>();
		}
	}

	@SuppressWarnings("unchecked")
	public PageOffsetList(Collection<? extends E> c, int limit, int offset) {
		if (c != null) {
			datas = (List<E>) c;
		} else {
			datas = new ArrayList<E>();
		}
		this.offset = offset;
		this.limit = limit;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	public void addAll(Collection<? extends E> c) {
		datas.addAll(c);
	}

	public boolean add(E c) {
		return datas.add(c);
	}

	public int size() {
		return datas.size();
	}

	public List<E> getDatas() {
		return datas;
	}

	public void setDatas(List<E> datas) {
		this.datas = datas;
	}

}
