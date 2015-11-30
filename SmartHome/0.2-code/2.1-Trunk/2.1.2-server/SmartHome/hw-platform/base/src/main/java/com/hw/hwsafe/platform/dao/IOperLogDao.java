/**
 * 文件名：IOperLogDao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.dao;

import com.hw.hwsafe.platform.pojo.OperLogPO;

/**
 * 操作日志DAO接口
 * 
 * @author 马宁
 * @创建时间 2013-03-20
 */
public interface IOperLogDao extends IBaseDao {

	/**
	 * 添加操作日志
	 * 
	 * @param logPO
	 *            - 日志实体
	 */
	void insertOperLog(OperLogPO logPO);

	/**
	 * 通过条件查询实例
	 * 
	 * @param id
	 *            - 操作日志的id
	 */
	OperLogPO retrieveInstanceById(String id);
	
	/**
	 * 获取最大的日志编号
	 */
	String retrieveMaxCode();
}
