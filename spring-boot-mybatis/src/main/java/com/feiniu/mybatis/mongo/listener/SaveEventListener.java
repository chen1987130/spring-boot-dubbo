package com.feiniu.mybatis.mongo.listener;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.feiniu.mybatis.mongo.annotation.AutoIncrease;
import com.feiniu.mybatis.mongo.entity.Sequence;

@Component
public class SaveEventListener {

	@Autowired
	private MongoTemplate mongo;

	public void onBeforeConvert(final Object source) {
		if (source != null) {
			ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {
				public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
					ReflectionUtils.makeAccessible(field);
					// 如果字段添加了我们自定义的AutoIncKey注解
					if (field.isAnnotationPresent(AutoIncrease.class)) {

						// 设置自增ID
						AutoIncrease autoIncrease = field.getAnnotation(AutoIncrease.class);
						String collName = autoIncrease.collName();
						field.set(source, getNextId(collName));
					}
				}
			});
		}
	}

	/**
	 * 获取下一个自增ID
	 * 
	 * @param collName
	 *            集合（这里用类名，就唯一性来说最好还是存放长类名）名称
	 * @return 序列值
	 */
	private Long getNextId(String collName) {
		Query query = new Query(Criteria.where("collName").is(collName));
		Update update = new Update();
		update.inc("seqId", 1);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.upsert(true);
		options.returnNew(true);
		Sequence seq = mongo.findAndModify(query, update, options, Sequence.class);
		return seq.getSeqId();
	}
}
