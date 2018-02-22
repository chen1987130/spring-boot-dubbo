package com.feiniu.quartz.core.base;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 任务参数
 * 
 * @author chensheng
 *
 */
public interface TaskDataMap {
	public void clear();

	public boolean containsKey(Object obj);

	public boolean containsValue(Object obj);

	@SuppressWarnings("rawtypes")
	public Set entrySet();

	public Object get(Object obj);

	public boolean getBooleanValue(String s);

	public char getChar(String s);

	public double getDoubleValue(String s);

	public float getFloatValue(String s);

	public int getIntValue(String s);

	public String[] getKeys();

	public long getLongValue(String s);

	public String getString(String s);

	public boolean isEmpty();

	@SuppressWarnings("rawtypes")
	public Set keySet();

	public void put(String s, boolean flag);

	public void put(String s, char c);

	public void put(String s, double d);

	public void put(String s, float f);

	public void put(String s, int i);

	public void put(String s, long l);

	public void put(String s, String s1);

	@SuppressWarnings("rawtypes")
	public void putAll(Map map);

	public void putAsString(String s, boolean flag);

	public void putAsString(String s, Boolean boolean1);

	public void putAsString(String s, char c);

	public void putAsString(String s, Character character);

	public void putAsString(String s, double d);

	public void putAsString(String s, Double double1);

	public void putAsString(String s, float f);

	public void putAsString(String s, Float float1);

	public void putAsString(String s, int i);

	public void putAsString(String s, Integer integer);

	public void putAsString(String s, long l);

	public void putAsString(String s, Long long1);

	public Object remove(Object obj);

	public int size();

	@SuppressWarnings("rawtypes")
	public Collection values();
}
