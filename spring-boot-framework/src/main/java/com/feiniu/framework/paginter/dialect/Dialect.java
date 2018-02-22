package com.feiniu.framework.paginter.dialect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.BeanWrapper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.feiniu.framework.paginter.domain.Hepler;
import com.feiniu.framework.paginter.domain.Order;
import com.feiniu.framework.paginter.domain.PageBounds;

/**
 * 类似HIBERNATE的Dialect,但只精简出分页部分
 */
public class Dialect {
	protected TypeHandlerRegistry typeHandlerRegistry;
	protected MappedStatement mappedStatement;
	protected PageBounds pageBounds;
	protected Object parameterObject;
	protected BoundSql boundSql;
	protected List<ParameterMapping> parameterMappings;
	protected Map<String, Object> pageParameters = new HashMap<String, Object>();

	private String pageSQL;
	private String countSQL;

	public Dialect(MappedStatement mappedStatement, Object parameterObject, PageBounds pageBounds) {
		this.mappedStatement = mappedStatement;
		this.parameterObject = parameterObject != null ? parameterObject : new HashMap<String, Object>();
		this.pageBounds = pageBounds;
		this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();

		init();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void init() {
		boundSql = mappedStatement.getBoundSql(parameterObject);
		parameterMappings = new ArrayList(boundSql.getParameterMappings());
		if (parameterObject instanceof Map) {
			pageParameters.putAll((Map) parameterObject);
		} else {
			MetaObject metaObject = mappedStatement.getConfiguration().newMetaObject(parameterObject);
			BeanWrapper wrapper = new BeanWrapper(metaObject, parameterObject);
			for (ParameterMapping parameterMapping : parameterMappings) {
				PropertyTokenizer prop = new PropertyTokenizer(parameterMapping.getProperty());
				pageParameters.put(parameterMapping.getProperty(), wrapper.get(prop));
			}
		}

		StringBuffer bufferSql = new StringBuffer(boundSql.getSql().trim());
		if (bufferSql.lastIndexOf(";") == bufferSql.length() - 1) {
			bufferSql.deleteCharAt(bufferSql.length() - 1);
		}
		String sql = bufferSql.toString();
		pageSQL = sql;
		if (pageBounds.getOrders() != null && !pageBounds.getOrders().isEmpty()) {
			pageSQL = getSortString(sql, pageBounds.getOrders());
		}
		if (pageBounds.getOffset() != RowBounds.NO_ROW_OFFSET || pageBounds.getLimit() != RowBounds.NO_ROW_LIMIT) {
			pageSQL = getLimitString(pageSQL, "__offset", pageBounds.getOffset(), "__limit", pageBounds.getLimit());
		}

		countSQL = getCountString(sql);
	}

	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public Object getParameterObject() {
		return pageParameters;
	}

	public String getPageSQL() {
		return pageSQL;
	}

	@SuppressWarnings("rawtypes")
	protected void setPageParameter(String name, Object value, Class type) {
		ParameterMapping parameterMapping = new ParameterMapping.Builder(mappedStatement.getConfiguration(), name, type).build();
		parameterMappings.add(parameterMapping);
		pageParameters.put(name, value);
	}

	public String getCountSQL() {
		return countSQL;
	}

	/**
	 * 将sql变成分页sql语句
	 */
	protected String getLimitString(String sql, String offsetName, int offset, String limitName, int limit) {
		throw new UnsupportedOperationException("paged queries not supported");
	}

	/**
	 * 将sql转换为总记录数SQL
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 总记录数的sql
	 */
	protected String getCountString(String sql) {
		sql = Hepler.compress(sql);
		int orderIndex = Hepler.getLastOrderInsertPoint(sql);
		int formIndex = Hepler.getAfterFormInsertPoint(sql);
		String select = sql.substring(0, formIndex);

		// 如果SELECT 中包含 DISTINCT 只能在外层包含COUNT
		if (select.toLowerCase().indexOf("select distinct") != -1 || sql.toLowerCase().indexOf("group by") != -1) {
			return new StringBuffer(sql.length()).append("select count(1) from (").append(sql.substring(0, orderIndex)).append(" ) t").toString();
		} else {
			return new StringBuffer(sql.length()).append("select count(1)").append(sql.substring(formIndex, orderIndex)).toString();
		}
	}

	/**
	 * 将sql转换为带排序的SQL
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 总记录数的sql
	 */
	protected String getSortString(String sql, List<Order> orders) {
		throw new UnsupportedOperationException("paged queries not supported");
	}

}
