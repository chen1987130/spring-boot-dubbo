package com.feiniu.quartz.page.vo;

import com.feiniu.quartz.core.util.DateUtil;
import com.feiniu.quartz.page.model.QrtzLog;

/**
 * 定时任务日志
 * 
 * @author chensheng
 *
 */
public class QrtzLogVo extends QrtzLog {

	private static final long serialVersionUID = 1L;

	public String getLogTimeView() {
		return DateUtil.getTime(getLogTime());
	}

}
