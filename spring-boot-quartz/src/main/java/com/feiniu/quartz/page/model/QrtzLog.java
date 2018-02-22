package com.feiniu.quartz.page.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务日志实体对象
 **/
public class QrtzLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 任务名称
     **/
    private String logTask;

    /**
     * 任务名称
     **/
    private Date logTime;

    /**
     * 执行情况
     **/
    private String logContent;

    
    public QrtzLog() {
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setLogTask(String logTask) {
    	this.logTask = logTask == null ? null : logTask.trim();
    }

    public String getLogTask() {
        return this.logTask;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Date getLogTime() {
        return this.logTime;
    }

    public void setLogContent(String logContent) {
    	this.logContent = logContent == null ? null : logContent.trim();
    }

    public String getLogContent() {
        return this.logContent;
    }

}