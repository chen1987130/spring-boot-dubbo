package com.feiniu.quartz.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间转化类
 * 
 * @author chensheng
 */
public class DateUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATETIME_PATTERN_DASH = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy/MM/dd HH:mm:ss
	 */
	public static final String DATETIME_PATTERN_SLASH = "yyyy/MM/dd HH:mm:ss";

	/**
	 * MM月dd日 HH时mm分
	 */
	public static final String DATETIME_PATTERN_CHINA = "MM月dd日 HH时mm分";

	/**
	 * yyyyMMddHHmmss
	 */
	public static final String DATETIME_PATTERN_UNSIGN = "yyyyMMddHHmmss";
	/**
	 * yyyyMMdd
	 */
	public static final String DATE_PATTERN_UNSIGN = "yyyyMMdd";
	/**
	 * yyyyMMdd
	 */
	public static final String DATE_PATTERN_DASH = "yyyy-MM-dd";

	/**
	 * 字符串转日期(yyyyMMddHHmmss)
	 */
	public static Date stringToDate14(String str) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat("");
		Date date = null;
		try {
			if (StringUtils.isNotBlank(str)) {
				date = sdf.parse(str);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return date;
	}

	/**
	 * 字符串转日期(yyyyMMdd)
	 */
	public static Date stringToDate8(String str) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			if (StringUtils.isNotBlank(str)) {
				date = sdf.parse(str);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return date;
	}

	/**
	 * 字符串转日期
	 */
	public static Date stringToDateByPattern(String str, String pattern) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			if (StringUtils.isNotBlank(str)) {
				date = sdf.parse(str);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return date;
	}

	/**
	 * 日期转字符串(yyyyMMddHHmmss)
	 */
	public static String dateToString14(Date date) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = "";
		if (date != null) {
			dateStr = sdf.format(date);
		}
		return dateStr;
	}

	/**
	 * 日期转字符串(yyyyMMdd)
	 */
	public static String dateToString8(Date date) throws RuntimeException {
		return getStringFromDate(date, "yyyyMMdd");
	}

	/**
	 * 日期转字符串
	 */
	public static String dateToStringPattern(Date date, String pattern) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dateStr = "";
		if (date != null) {
			dateStr = sdf.format(date);
		}
		return dateStr;
	}

	/**
	 * 格式化时间(yyyy-MM-dd HH:mm)
	 */
	public static String getTime(Date date) throws RuntimeException {
		return getStringFromDate(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 格式化时间(MM/dd HH:mm)
	 */
	public static String getTime2(Date date) throws RuntimeException {
		return getStringFromDate(date, "MM/dd HH:mm");
	}

	/**
	 * 格式化时间(yyyyMMddHHmm)
	 */
	public static String getTime3(Date date) throws RuntimeException {
		return getStringFromDate(date, "yyyyMMddHHmm");
	}

	/**
	 * 格式化日期(yyyy-MM-dd)
	 */
	public static String getDate(Date date) throws RuntimeException {
		return getStringFromDate(date, "yyyy-MM-dd");
	}

	/**
	 * 日期转字符串
	 */
	public static String getStringFromDate(Date date, String pattern) throws RuntimeException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String result = "";
		if (date != null) {
			result = dateFormat.format(date);
		}
		return result;
	}

}
