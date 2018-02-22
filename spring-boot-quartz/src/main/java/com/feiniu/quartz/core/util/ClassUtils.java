package com.feiniu.quartz.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class帮助类
 * 
 * @author chensheng
 */
public class ClassUtils {

	@SuppressWarnings("rawtypes")
	public static Class classForName(String type) throws ClassNotFoundException {
		if (type == null)
			throw new NullPointerException("type");
		try {
			return Class.forName(type, false, Thread.currentThread().getContextClassLoader());
		} catch (Exception e) {
			return Class.forName(type, false, ClassUtils.class.getClassLoader());
		}
	}

	@SuppressWarnings("rawtypes")
	public static Class simpleClassForName(String type) {
		try {
			return classForName(type);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	public static InputStream getResourceAsStream(String resource) {
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
		if (stream == null)
			stream = ClassUtils.class.getClassLoader().getResourceAsStream(resource);
		if (stream == null)
			stream = ClassUtils.class.getResourceAsStream(resource);
		return stream;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Iterator getResources(String resource, Object defaultObject) {
		try {
			List lst;
			Enumeration resources = getCurrentLoader(defaultObject).getResources(resource);
			lst = new ArrayList();
			for (; resources.hasMoreElements(); lst.add(resources.nextElement()))
				;
			return lst.iterator();
		} catch (IOException e) {
			return null;
		}

	}

	public static Object newInstance(String type) {
		if (type == null)
			return null;
		else
			return newInstance(simpleClassForName(type));
	}

	@SuppressWarnings("rawtypes")
	public static Object newInstance(Class clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}

	}

	protected static ClassLoader getCurrentLoader(Object defaultObject) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null)
			loader = defaultObject.getClass().getClassLoader();
		return loader;
	}

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(ClassUtils.class);
}
