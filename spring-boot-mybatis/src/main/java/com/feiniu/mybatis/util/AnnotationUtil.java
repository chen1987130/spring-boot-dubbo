package com.feiniu.mybatis.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.feiniu.mybatis.model.annotation.Id;

public class AnnotationUtil {

	public static Annotation getAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
		if (clazz.isAnnotationPresent(annotation)) {
			Annotation a = clazz.getAnnotation(annotation);
			return a;
		}
		return null;
	}

	public static String getId(Class<?> clazz) throws IllegalAccessException {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Id.class)) {
				return field.getName();
			}
		}
		throw new IllegalAccessException("实体为设置ID注解");
	}
}
