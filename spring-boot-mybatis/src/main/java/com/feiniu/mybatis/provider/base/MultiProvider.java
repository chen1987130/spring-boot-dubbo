package com.feiniu.mybatis.provider.base;

import com.alibaba.druid.util.StringUtils;
import com.feiniu.mybatis.Constant;
import com.feiniu.mybatis.util.AnnotationUtil;

public class MultiProvider {

	public String queryListOrderByPropSql(Class<?> entity, String[] tables, Integer index, int offset, int pageSize, String prop) throws IllegalAccessException {

		String idProp = AnnotationUtil.getId(entity);
		StringBuffer order = new StringBuffer();

		if (StringUtils.equals(idProp, prop)) {
			order.append(" order by ").append(prop);
		} else {
			order.append(" order by ").append(prop).append(",").append(idProp);
		}

		String table = Constant.EMPLOYEE_TABLES[index];
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ").append(table).append(order.toString()).append(" asc limit ").append(offset).append(",").append(pageSize);
		return sb.toString();
	}

}
