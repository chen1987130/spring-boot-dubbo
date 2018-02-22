package com.feiniu.framework.paginter.dialect;

import java.util.List;

import org.apache.ibatis.mapping.MappedStatement;

import com.feiniu.framework.paginter.domain.Hepler;
import com.feiniu.framework.paginter.domain.Order;
import com.feiniu.framework.paginter.domain.PageBounds;

/**
 * MySql分页方言(InterceptorConfig中配置)
 */
public class MySQLDialect extends Dialect {

	public MySQLDialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
		super(mappedStatement, parameterObject, pageBounds);
	}

	protected String getLimitString(String sql, String offsetName, int offset, String limitName, int limit) {
		StringBuffer buffer = new StringBuffer(sql.length() + 20).append(Hepler.compress(sql));
		if (offset > 0) {
			buffer.append(" limit ?, ?");
			setPageParameter(offsetName, offset, Integer.class);
			setPageParameter(limitName, limit, Integer.class);
		} else {
			buffer.append(" limit ?");
			setPageParameter(limitName, limit, Integer.class);
		}
		return buffer.toString();
	}

	protected String getSortString(String sql, List<Order> orders) {
		if (orders == null || orders.isEmpty()) {
			return sql;
		}

		StringBuffer buffer = new StringBuffer(sql).append(" order by ");
		for (Order order : orders) {
			if (order != null) {
				buffer.append(order.toString()).append(", ");
			}
		}
		buffer.delete(buffer.length() - 2, buffer.length());
		return buffer.toString();
	}
}
