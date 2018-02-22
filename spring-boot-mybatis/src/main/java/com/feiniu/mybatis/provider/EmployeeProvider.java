package com.feiniu.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

import com.feiniu.mybatis.Constant;
import com.feiniu.mybatis.model.Employee;

public class EmployeeProvider {

	public String insertSql(Employee entity) {
		int index = entity.getId() % Constant.EMPLOYEE_TABLES.length;
		String table = Constant.EMPLOYEE_TABLES[index];
		return new SQL() {
			{
				INSERT_INTO(table);
				VALUES("id", "#{id}");
				VALUES("name", "#{name}");
				VALUES("age", "#{age}");
			}
		}.toString();
	}

	public String queryListOrderByAgeSql(int offset, int pageSize, Integer index) throws IllegalAccessException {
		String table = Constant.EMPLOYEE_TABLES[index];
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ").append(table).append(" order by age,id asc limit ").append(offset).append(",").append(pageSize);
		return sb.toString();
	}

	public String queryListBetweenAgeSql(int minAge, int maxAge, Integer index) {
		String table = Constant.EMPLOYEE_TABLES[index];
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ").append(table).append(" where ").append(" age >= ").append(minAge).append(" and ").append(" age <= ").append(maxAge)
				.append(" order by age,id asc");
		return sb.toString();
	}

	public String countByAge(int age, int id, Integer index) {
		String table = Constant.EMPLOYEE_TABLES[index];
		StringBuffer sb = new StringBuffer();
		sb.append("select count(0) from ").append(table).append(" where age < ").append(age).append(" or ( age = ").append(age).append(" and id < ").append(id)
				.append(" )");
		return sb.toString();
	}

	public String countSql(Integer index) {
		String table = Constant.EMPLOYEE_TABLES[index];
		StringBuffer sb = new StringBuffer();
		sb.append("select count(0) from ").append(table);
		return sb.toString();
	}

}
