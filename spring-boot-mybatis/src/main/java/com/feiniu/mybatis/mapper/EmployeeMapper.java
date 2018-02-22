package com.feiniu.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import com.feiniu.mybatis.mapper.base.MultiMapper;
import com.feiniu.mybatis.model.Employee;
import com.feiniu.mybatis.provider.EmployeeProvider;

@Mapper
public interface EmployeeMapper extends MultiMapper<Employee> {

	@InsertProvider(method = "insertSql", type = EmployeeProvider.class)
	int insert(Employee entity);

	@SelectProvider(method = "queryListOrderByAgeSql", type = EmployeeProvider.class)
	List<Employee> queryListOrderByAge(int offset, int pageSize, Integer index);

	@SelectProvider(method = "queryListBetweenAgeSql", type = EmployeeProvider.class)
	List<Employee> queryListBetweenAge(int minAge, int maxAge, Integer index);

	@SelectProvider(method = "countByAge", type = EmployeeProvider.class)
	Integer countByAge(int age, int id, Integer index);

	@SelectProvider(method = "countSql", type = EmployeeProvider.class)
	Integer count(Integer index);

}
