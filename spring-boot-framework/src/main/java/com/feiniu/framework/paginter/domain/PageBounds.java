package com.feiniu.framework.paginter.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

/**
 * 分页查询对象
 */
public class PageBounds extends RowBounds implements Serializable {
	private static final long serialVersionUID = -6414350656252331011L;
	public final static int NO_PAGE = 1;

	/**
	 * 页号 (从1开始)
	 */
	protected int page = NO_PAGE;

	/**
	 * 每页显示个数
	 */
	protected int limit = NO_ROW_LIMIT;

	/**
	 * 开始游标
	 */
	protected int offset = NO_ROW_OFFSET;

	/**
	 * 排序
	 */
	protected List<Order> orders = new ArrayList<Order>();

	/**
	 * 是否执行Count查询
	 */
	protected boolean containsTotalCount;

	protected Boolean asyncTotalCount;

	public PageBounds() {
		containsTotalCount = false;
	}

	public PageBounds(RowBounds rowBounds) {
		if (rowBounds instanceof PageBounds) {
			PageBounds pageBounds = (PageBounds) rowBounds;
			this.page = pageBounds.page;
			this.limit = pageBounds.limit;
			this.orders = pageBounds.orders;
			this.containsTotalCount = pageBounds.containsTotalCount;
			this.asyncTotalCount = pageBounds.asyncTotalCount;

			if (pageBounds.offset != NO_ROW_OFFSET) {
				this.offset = pageBounds.offset;
			} else if (this.page != NO_ROW_LIMIT && this.limit != NO_ROW_OFFSET) {
				this.offset = (page - 1) * limit;
			}
		} else {
			this.offset = rowBounds.getOffset();
			this.limit = rowBounds.getLimit();
			this.page = (rowBounds.getOffset() / rowBounds.getLimit()) + 1;
		}

	}

	/**
	 * Query TOP N, default containsTotalCount = false
	 * 
	 * @param limit
	 */
	public PageBounds(int limit) {
		this.limit = limit;
		this.containsTotalCount = false;
	}

	public PageBounds(int page, int limit) {
		this(page, limit, new ArrayList<Order>(), true);
	}

	public PageBounds(int page, int limit, boolean containsTotalCount) {
		this(page, limit, new ArrayList<Order>(), containsTotalCount);
	}

	public PageBounds(List<Order> orders) {
		this(NO_PAGE, NO_ROW_LIMIT, orders, false);
	}

	/**
	 * Just sorting, default containsTotalCount = false
	 * 
	 * @param order
	 */
	public PageBounds(Order... order) {
		this(NO_PAGE, NO_ROW_LIMIT, order);
		this.containsTotalCount = false;
	}

	public PageBounds(int page, int limit, Order... order) {
		this(page, limit, Arrays.asList(order), true);
	}

	public PageBounds(int page, int limit, List<Order> orders) {
		this(page, limit, orders, true);
	}

	public PageBounds(int page, int limit, List<Order> orders, boolean containsTotalCount) {
		this.page = page;
		this.limit = limit;
		this.orders = orders;
		this.containsTotalCount = containsTotalCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isContainsTotalCount() {
		return containsTotalCount;
	}

	public void setContainsTotalCount(boolean containsTotalCount) {
		this.containsTotalCount = containsTotalCount;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Boolean getAsyncTotalCount() {
		return asyncTotalCount;
	}

	public void setAsyncTotalCount(Boolean asyncTotalCount) {
		this.asyncTotalCount = asyncTotalCount;
	}

	public void setSort(String sort) {
		this.orders = Order.formString(sort);
	}

	@Override
	public int getOffset() {
		if (offset > 0) {
			return offset;
		}
		return 0;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PageBounds{");
		sb.append("page=").append(page);
		sb.append(", limit=").append(limit);
		sb.append(", orders=").append(orders);
		sb.append(", containsTotalCount=").append(containsTotalCount);
		sb.append(", asyncTotalCount=").append(asyncTotalCount);
		sb.append('}');
		return sb.toString();
	}
}