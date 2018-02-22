package com.feiniu.mybatis.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.feiniu.mybatis.Constant;
import com.feiniu.mybatis.mapper.EmployeeMapper;
import com.feiniu.mybatis.model.Employee;
import com.feiniu.mybatis.model.PageTable;
import com.feiniu.mybatis.mongo.listener.SaveEventListener;

@Component
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private SaveEventListener saveEventListener;

	private static final int TABLE_SIZE = Constant.EMPLOYEE_TABLES.length;

	private static ExecutorService Pool = Executors.newFixedThreadPool(TABLE_SIZE);

	public void insert(Employee employee) {
		if (employee != null) {
			saveEventListener.onBeforeConvert(employee);
			employeeMapper.insert(employee);
		}
	}

	public PageTable queryListOrderByAge(int page, int pageSize) throws Exception {
		List<Employee> dataList = new ArrayList<>();
		PageTable pageTable = new PageTable();

		int total = 0;
		int[] counts = new int[TABLE_SIZE];

		// 异步计算count
		List<Future<Integer>> cfList = new ArrayList<>();
		for (int i = 0; i < TABLE_SIZE; i++) {
			final int index = i;
			Future<Integer> countFuture = call(new Callable<Integer>() {
				public Integer call() throws Exception {
					return employeeMapper.count(index);
				}
			}, true);
			cfList.add(countFuture);
		}

		for (int i = 0; i < TABLE_SIZE; i++) {
			Future<Integer> countFuture = cfList.get(i);
			Integer count = (Integer) countFuture.get();
			counts[i] = count;
			total = total + count;
		}

		if ((page * pageSize) <= total) {
			int offset = (page - 1) * pageSize / TABLE_SIZE;
			int limit = ((page * pageSize % TABLE_SIZE == 0) ? (page * pageSize / TABLE_SIZE) : (page * pageSize / TABLE_SIZE + 1)) - offset;

			List<Employee> totalList = new ArrayList<Employee>();
			List<Employee> beginList = new ArrayList<Employee>();
			int minAge = Integer.MAX_VALUE;
			int maxAge = Integer.MIN_VALUE;
			int minAgeID = 0;

			List<Future<List<Employee>>> qfList = new ArrayList<>();
			for (int i = 0; i < TABLE_SIZE; i++) {
				final int index = i;

				if (counts[i] >= offset) {
					Future<List<Employee>> entityfuture = call(new Callable<List<Employee>>() {
						public List<Employee> call() throws Exception {
							return employeeMapper.queryListOrderByAge(offset, limit, index);
						}
					}, true);
					qfList.add(entityfuture);
				} else {
					qfList.add(null);
				}
			}

			for (int i = 0; i < TABLE_SIZE; i++) {
				List<Employee> tempList = null;

				Future<List<Employee>> entityfuture = qfList.get(i);
				if (entityfuture != null) {
					tempList = entityfuture.get();
				}

				if (tempList != null && tempList.size() > 0) {
					int tempMinAge = tempList.get(0).getAge();
					int tempMaxAge = tempList.get(tempList.size() - 1).getAge();

					int id = tempList.get(0).getId();
					if (tempMinAge < minAge) {
						minAge = tempMinAge;
						minAgeID = id;
					} else if (tempMinAge == minAge && id < minAgeID) {
						minAgeID = id;
					}

					if (tempMaxAge > maxAge) {
						maxAge = tempMaxAge;
					}
					beginList.add(tempList.get(0));
				} else {
					beginList.add(null);
				}
			}

			// minAgeID的初始游标(假设第一次查询与第二次查询开始记录相同，0开始)
			int cursor = offset * TABLE_SIZE;
			// 数组起始位移
			int totalIndex = 0;

			cfList.clear();

			for (int i = 0; i < TABLE_SIZE; i++) {
				if (beginList.get(i) == null) {
					final int index = i;
					final int ageProp = minAge;
					final int idProp = minAgeID;

					Future<Integer> countfuture = call(new Callable<Integer>() {
						public Integer call() throws Exception {
							return employeeMapper.countByAge(ageProp, idProp, index);
						}
					}, true);

					cfList.add(countfuture);
				}
			}

			for (Future<Integer> future : cfList) {
				cursor = cursor - offset + future.get();
			}

			for (int i = 0; i < TABLE_SIZE; i++) {
				List<Employee> tempList = employeeMapper.queryListBetweenAge(minAge, maxAge, i);
				Employee beignEmployee = beginList.get(i);

				int index = 0;

				for (Employee employee : tempList) {

					// 如果新数据在minAgeID之前,数组起始位移+1
					if (employee.getAge() == minAge && employee.getId() < minAgeID) {
						totalIndex++;
						continue;
					}

					// 如果新数据在上次查询的最小值之前,minAgeID的序列-1
					if (beignEmployee != null) {
						if (employee.getId() == beignEmployee.getId()) {
							break;
						}
						index++;
					}
				}

				totalList.addAll(tempList);
				cursor = cursor - index;
			}

			Collections.sort(totalList, new Comparator<Employee>() {
				@Override
				public int compare(Employee o1, Employee o2) {
					if (o1.getAge() != o2.getAge()) {
						return o1.getAge() - o2.getAge();
					} else {
						if (o1.getId() > o2.getId()) {
							return 1;
						} else if (o1.getId() < o2.getId()) {
							return -1;
						}
						return 0;
					}
				}
			});

			// 数组位移 = 起始位移 + 最小值偏移量 - 最小值游标
			totalIndex = totalIndex + (page - 1) * pageSize - cursor;
			dataList = totalList.subList(totalIndex, Math.min(totalIndex + pageSize, totalList.size()));
		}

		pageTable.setTotal(total);
		pageTable.setData(dataList);
		return pageTable;
	}

	private <T> Future<T> call(Callable<T> callable, boolean async) {
		if (async) {
			return Pool.submit(callable);
		} else {
			FutureTask<T> future = new FutureTask<T>(callable);
			future.run();
			return future;
		}
	}

}
