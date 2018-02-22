package com.feiniu.quartz.page.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.feiniu.framework.base.mapper.BaseMapper;
import com.feiniu.quartz.page.model.QrtzLog;

/**
 * 定时任务日志Mapper对象
 **/
@Mapper
public interface QrtzLogMapper extends BaseMapper<QrtzLog> {

	int deleteAll();
}