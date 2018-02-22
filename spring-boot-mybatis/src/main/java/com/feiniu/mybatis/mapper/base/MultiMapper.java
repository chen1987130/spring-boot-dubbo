package com.feiniu.mybatis.mapper.base;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.feiniu.mybatis.provider.base.MultiProvider;

public interface MultiMapper<T> {

	@SelectProvider(method = "queryListOrderByPropSql", type = MultiProvider.class)
	List<T> queryListOrderByProp(Class<?> entity, String[] tables, Integer index, int offset, int pageSize, String prop);

}
