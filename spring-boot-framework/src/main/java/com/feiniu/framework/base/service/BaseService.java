package com.feiniu.framework.base.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.feiniu.framework.base.mapper.BaseMapper;
import com.feiniu.framework.paginter.domain.Order;
import com.feiniu.framework.paginter.domain.PageBounds;
import com.feiniu.framework.paginter.domain.PageList;

/**
 * Service基础类
 * 
 * @author chensheng
 *
 * @param <T>
 *            Model对象
 * @param <M>
 *            Mapper对象
 */
@SuppressWarnings("rawtypes")
public class BaseService<T, M extends BaseMapper<T>> {

	protected M mapper;

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 *            数据ID值
	 * 
	 * @return 影响行数
	 */
	public int deleteById(Long id) {
		return mapper.deleteById(id);
	}

	/**
	 * 根据ID查询对象
	 * 
	 * @param id
	 *            数据ID值
	 * 
	 */
	public T selectById(Long id) {
		return (T) mapper.selectById(id);
	}

	/**
	 * 查询一条记录
	 */
	public T selectOne(Map<String, ?> queryMap) {
		T entity = null;
		List<T> list = mapper.select(queryMap);
		if (!list.isEmpty()) {
			entity = list.get(0);
		}
		return entity;
	}

	/**
	 * 新增所有列表
	 * 
	 * @param record
	 *            Model对象
	 * 
	 * @return 影响行数
	 */
	public int insertAllField(T record) {
		return mapper.insert(record);
	}

	/**
	 * 新增 (如果为空,插入默认值)
	 * 
	 * @param record
	 *            Model对象
	 * 
	 * @return 影响行数
	 */
	public int insert(T record) {
		return mapper.insert(record);
	}

	/**
	 * 根据主键，更新所有数据
	 * 
	 * @param record
	 *            Model对象(主键不能为空)
	 * 
	 * @return 影响行数
	 */
	public int updateAllField(T record) {
		return mapper.updateAllField(record);
	}

	/**
	 * 根据主键，更新数据(如果对象属性为空,不更新)
	 * 
	 * @param record
	 *            Model对象(主键不能为空)
	 * 
	 * @return 影响行数
	 */
	public int update(T record) {
		return mapper.update(record);
	}

	/**
	 * 查询记录数
	 * 
	 * @param queryMap
	 *            查询条件
	 */
	public int count(Map<String, ?> queryMap) {
		return mapper.count(queryMap);
	}

	/**
	 * 根据条件分页查询列表及分页信息
	 * 
	 * @param queryMap
	 *            查询条件
	 * 
	 * @param PageBounds
	 *            分页参数
	 */
	public PageList<T> queryForPage(Map<String, ?> queryMap, PageBounds page) {
		page.setContainsTotalCount(true);
		PageList<T> list = (PageList<T>) mapper.select(queryMap, page);
		return list;
	}

	/**
	 * 根据条件分页查询列表及分页信息
	 * 
	 * @param object
	 *            查询对象
	 * 
	 * @param PageBounds
	 *            分页参数
	 */
	public PageList<T> queryForPage(T object, PageBounds page) {
		page.setContainsTotalCount(true);
		PageList<T> list = (PageList<T>) mapper.select(object, page);
		return list;
	}

	/**
	 * 根据条件分页查询列表
	 * 
	 * @param queryMap
	 *            查询条件
	 * 
	 * @param PageBounds
	 *            分页参数
	 */
	public List<T> queryList(Map<String, ?> queryMap, PageBounds page) {
		page.setContainsTotalCount(false);
		List<T> list = mapper.select(queryMap, page);
		return list;
	}

	/**
	 * 根据条件分页查询列表
	 * 
	 * @param offset
	 *            从多少条开始
	 * @param limit
	 *            查询多少条数据
	 */
	public List<T> queryList(Map<String, ?> queryMap, int offset, int limit) {
		List<T> list = mapper.select(queryMap, new RowBounds(offset, limit));
		return list;
	}

	/**
	 * 根据条件分页查询列表
	 * 
	 * @param offset
	 *            从多少条开始
	 * @param limit
	 *            查询多少条数据
	 * @param Order
	 *            排序字符串 如：id.asc 或者id.asc,name.desc
	 */
	public List<T> queryList(Map<String, ?> queryMap, int offset, int limit, String order) {
		PageBounds pageBound = new PageBounds(new RowBounds(offset, limit));
		pageBound.setOrders(Order.formString(order));
		List<T> list = mapper.select(queryMap, pageBound);
		return list;
	}

	/**
	 * 查询前N条数据
	 * 
	 * @param queryMap
	 *            查询条件
	 * @param limit
	 *            查询记录数
	 */
	public List<T> queryTop(Map<String, ?> queryMap, int limit) {
		List<T> list = mapper.select(queryMap, new PageBounds(limit));
		return list;
	}

	/**
	 * 查询前N条数据
	 * 
	 * @param queryMap
	 *            查询条件
	 * @param limit
	 *            查询记录数
	 * @param Order
	 *            排序字符串 如：id.asc 或者id.asc,name.desc
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryTop(Map queryMap, int limit, String order) {
		PageBounds pageBounds = new PageBounds(limit);
		pageBounds.setOrders(Order.formString(order));
		List<T> list = mapper.select(queryMap, pageBounds);
		return list;
	}

	/**
	 * 根据条件查询所有数据
	 * 
	 * @param queryMap
	 *            查询条件
	 */
	public List<T> queryList(Map<String, ?> queryMap) {
		List<T> list = mapper.select(queryMap);
		return list;
	}

	/**
	 * 根据实体对象查询所有数据
	 * 
	 * @param obj
	 *            Model对象
	 */
	public List<T> queryList(T obj) {
		List<T> list = mapper.select(obj);
		return list;
	}

	/**
	 * 根据实体对象查询所有数据
	 */
	public List<T> queryAll() {
		List<T> list = mapper.select(new HashMap<String, String>());
		return list;
	}

	/**
	 * 批量插入
	 */
	public int batchInsert(List<T> list) {
		if (list != null && list.size() > 0) {
			return mapper.batchInsert(list);
		} else {
			return 0;
		}
	}

	/**
	 * 批量插入或更新
	 */
	public int batchInsertOrUpdate(List<T> list) {
		int size = 1000;
		if (list != null && list.size() > 0) {
			if (list.size() <= size) {
				return mapper.batchInsertOrUpdate(list);
			} else {
				for (int i = 0; i < list.size(); i = i + size) {
					List<T> tempList = new ArrayList<T>();
					for (int j = 0; (i + j) < list.size() && j < 1000; j++) {
						T module = list.get(i + j);
						tempList.add(module);
					}
					mapper.batchInsertOrUpdate(tempList);
				}
			}
			return list.size();
		} else {
			return 0;
		}
	}

	/**
	 * 批量删除
	 * 
	 * 数据类型一定要与主键类型一致
	 */
	public int batchDelete(List<Object> ids) {
		int size = 1000;
		if (ids != null && ids.size() > 0) {
			if (ids.size() <= size) {
				return mapper.batchDelete(ids);
			} else {
				for (int i = 0; i < ids.size(); i = i + size) {
					List<Object> tempIds = new ArrayList<Object>();
					for (int j = 0; (i + j) < ids.size() && j < 1000; j++) {
						Object id = ids.get(i + j);
						tempIds.add(id);
					}
					mapper.batchDelete(tempIds);
				}
			}
			return ids.size();
		} else {
			return 0;
		}
	}

	/**
	 * 批量删除
	 */
	public int batchDelete(String[] ids, Class clazz) {
		List<Object> list = new ArrayList<Object>();
		if (clazz == String.class) {
			list = Arrays.asList(ids);
		} else if (clazz == Long.class) {
			for (String id : ids) {
				list.add(Long.parseLong(id));
			}
		} else if (clazz == Integer.class) {
			for (String id : ids) {
				list.add(Integer.parseInt(id));
			}
		} else {
			throw new RuntimeException("不支持该数据类型");
		}

		if (list != null && list.size() > 0) {
			return mapper.batchDelete(list);
		} else {
			return 0;
		}

	}
}
