package com.feiniu.quartz.core.imp;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.quartz.JobDataMap;

import com.feiniu.quartz.core.base.TaskDataMap;

/**
 * 任务属性
 * 
 * @author chensheng
 *
 */
public class TaskDataMapImpl implements TaskDataMap {

	private JobDataMap jobDataMap;

	public TaskDataMapImpl(JobDataMap jobDataMap) {
		this.jobDataMap = jobDataMap;
	}

	public void clear() {
		jobDataMap.clear();
	}

	public boolean containsKey(Object key) {
		return jobDataMap.containsKey(key);
	}

	public boolean containsValue(Object val) {
		return jobDataMap.containsValue(val);
	}

	@SuppressWarnings("rawtypes")
	public Set entrySet() {
		return jobDataMap.entrySet();
	}

	public Object get(Object key) {
		return jobDataMap.get(key);
	}

	public boolean getBoolean(String arg0) {
		return jobDataMap.getBoolean(arg0);
	}

	public Boolean getBooleanFromString(String key) {
		return jobDataMap.getBooleanFromString(key);
	}

	public boolean getBooleanValue(String key) {
		return jobDataMap.getBooleanValue(key);
	}

	public boolean getBooleanValueFromString(String key) {
		return jobDataMap.getBooleanValueFromString(key);
	}

	public char getChar(String arg0) {
		return jobDataMap.getChar(arg0);
	}

	public Character getCharacterFromString(String key) {
		return jobDataMap.getCharacterFromString(key);
	}

	public char getCharFromString(String key) {
		return jobDataMap.getCharFromString(key);
	}

	public double getDouble(String arg0) {
		return jobDataMap.getDouble(arg0);
	}

	public Double getDoubleFromString(String key) {
		return jobDataMap.getDoubleFromString(key);
	}

	public double getDoubleValue(String key) {
		return jobDataMap.getDoubleValue(key);
	}

	public double getDoubleValueFromString(String key) {
		return jobDataMap.getDoubleValueFromString(key);
	}

	public float getFloat(String arg0) {
		return jobDataMap.getFloat(arg0);
	}

	public Float getFloatFromString(String key) {
		return jobDataMap.getFloatFromString(key);
	}

	public float getFloatValue(String key) {
		return jobDataMap.getFloatValue(key);
	}

	public float getFloatValueFromString(String key) {
		return jobDataMap.getFloatValueFromString(key);
	}

	public int getInt(String arg0) {
		return jobDataMap.getInt(arg0);
	}

	public Integer getIntegerFromString(String key) {
		return jobDataMap.getIntegerFromString(key);
	}

	public int getIntFromString(String key) {
		return jobDataMap.getIntFromString(key);
	}

	public int getIntValue(String key) {
		return jobDataMap.getIntValue(key);
	}

	public String[] getKeys() {
		return jobDataMap.getKeys();
	}

	public long getLong(String arg0) {
		return jobDataMap.getLong(arg0);
	}

	public Long getLongFromString(String key) {
		return jobDataMap.getLongFromString(key);
	}

	public long getLongValue(String key) {
		return jobDataMap.getLongValue(key);
	}

	public long getLongValueFromString(String key) {
		return jobDataMap.getLongValueFromString(key);
	}

	public String getString(String arg0) {
		return jobDataMap.getString(arg0);
	}

	public boolean isDirty() {
		return jobDataMap.isDirty();
	}

	public boolean isEmpty() {
		return jobDataMap.isEmpty();
	}

	@SuppressWarnings("rawtypes")
	public Set keySet() {
		return jobDataMap.keySet();
	}

	public void put(String key, boolean value) {
		jobDataMap.put(key, value);
	}

	public void put(String key, char value) {
		jobDataMap.put(key, value);
	}

	public void put(String key, double value) {
		jobDataMap.put(key, value);
	}

	public void put(String key, float value) {
		jobDataMap.put(key, value);
	}

	public void put(String key, int value) {
		jobDataMap.put(key, value);
	}

	public void put(String key, long value) {
		jobDataMap.put(key, value);
	}

	public void put(String key, String value) {
		jobDataMap.put(key, value);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void putAll(Map arg0) {
		jobDataMap.putAll(arg0);
	}

	public void putAsString(String key, boolean value) {
		jobDataMap.putAsString(key, value);
	}

	public void putAsString(String key, Boolean value) {
		jobDataMap.putAsString(key, value);
	}

	public void putAsString(String key, char value) {
		jobDataMap.putAsString(key, value);
	}

	public void putAsString(String key, Character value) {
		jobDataMap.putAsString(key, value);
	}

	public void putAsString(String key, double value) {
		jobDataMap.putAsString(key, value);
	}

	public void putAsString(String key, Double value) {
		jobDataMap.putAsString(key, value);
	}

	public void putAsString(String key, float value) {
		jobDataMap.putAsString(key, value);
	}

	public void putAsString(String key, Float value) {
		jobDataMap.putAsString(key, value);
	}

	public void putAsString(String key, int value) {
		jobDataMap.putAsString(key, value);
	}

	public void putAsString(String key, Integer value) {
		jobDataMap.putAsString(key, value);
	}

	public void putAsString(String key, long value) {
		jobDataMap.putAsString(key, value);
	}

	public void putAsString(String key, Long value) {
		jobDataMap.putAsString(key, value);
	}

	public Object remove(Object key) {
		return jobDataMap.remove(key);
	}

	public int size() {
		return jobDataMap.size();
	}

	@SuppressWarnings("rawtypes")
	public Collection values() {
		return jobDataMap.values();
	}

}
