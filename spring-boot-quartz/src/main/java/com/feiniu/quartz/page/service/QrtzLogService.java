package com.feiniu.quartz.page.service;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feiniu.framework.base.service.BaseService;
import com.feiniu.quartz.page.mapper.QrtzLogMapper;
import com.feiniu.quartz.page.model.QrtzLog;

/**
 * 定时任务日志Service对象
 **/
@Service
public class QrtzLogService extends BaseService<QrtzLog, QrtzLogMapper> {

	@Autowired
	public void setMapper(QrtzLogMapper mapper) {
		this.mapper = mapper;
	}

	public void log(String taskName, String msg) throws Exception {
		QrtzLog log = new QrtzLog();
		log.setLogTime(new Date());
		log.setLogTask(taskName);
		if (StringUtils.isNotBlank(msg) && msg.length() > 1500) {
			msg = msg.substring(0, 1500);
		}
		log.setLogContent(msg);
		insert(log);
	}

	public void clearAll() throws Exception {
		mapper.deleteAll();
	}
}
