package com.feiniu.quartz.core.base.service;

import java.util.List;

import com.feiniu.quartz.core.base.CronException;
import com.feiniu.quartz.core.model.PlanDefinition;
import com.feiniu.quartz.core.model.TaskDefinition;

/**
 * 定时器服务接口
 * 
 * @author chensheng
 *
 */
public interface CronService {

	/**
	 * 获取所有任务
	 */
	public List<TaskDefinition> getAllTasks();

	/**
	 * 根据编码获取任务
	 */
	public TaskDefinition findTaskByCode(String s);

	/**
	 * 新增任务
	 */
	public void addTask(TaskDefinition taskdefinition) throws CronException;

	/**
	 * 删除任务
	 */
	public void deleteTask(String s) throws CronException;

	/**
	 * 执行任务
	 */
	public void execTask(String s) throws CronException;

	/**
	 * 获取所有计划
	 */
	public List<PlanDefinition> getAllPlans();

	/**
	 * 根据任务编码获取计划
	 */
	public List<PlanDefinition> getPlansByTaskCode(String s);

	/**
	 * 根据计划名称获取单个计划
	 */
	public PlanDefinition findPlanByName(String s);

	/**
	 * 新增计划
	 */
	public void addPlan(PlanDefinition plandefinition) throws CronException;

	/**
	 * 删除计划
	 */
	public void deletePlan(String s) throws CronException;
}
