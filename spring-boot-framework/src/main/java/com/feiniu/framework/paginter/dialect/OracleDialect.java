package com.feiniu.framework.paginter.dialect;

import java.util.List;

import org.apache.ibatis.mapping.MappedStatement;

import com.feiniu.framework.paginter.domain.Order;
import com.feiniu.framework.paginter.domain.PageBounds;

/**
 * Oracle分页方言(InterceptorConfig中配置)
 */
public class OracleDialect extends Dialect {

	public OracleDialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
		super(mappedStatement, parameterObject, pageBounds);
	}

	protected String getLimitString(String sql, String offsetName, int offset, String limitName, int limit) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (offset > 0) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		} else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (offset > 0) {
			pagingSelect.append(" ) row_ ) where rownum_ <= ? and rownum_ > ?");
			setPageParameter("__offsetEnd", offset + limit, Integer.class);
			setPageParameter(offsetName, offset, Integer.class);
		} else {
			pagingSelect.append(" ) where rownum <= ?");
			setPageParameter(limitName, limit, Integer.class);
		}

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}

		return pagingSelect.toString();
	}

	/**
	 * 将sql转换为带排序的SQL
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 总记录数的sql
	 */
	protected String getSortString(String sql, List<Order> orders) {
		if (orders == null || orders.isEmpty()) {
			return sql;
		}

		StringBuffer buffer = new StringBuffer("select * from (").append(sql).append(") temp_order order by ");
		for (Order order : orders) {
			if (order != null) {
				buffer.append(order.toString()).append(", ");
			}
		}
		buffer.delete(buffer.length() - 2, buffer.length());
		return buffer.toString();
	}

}
