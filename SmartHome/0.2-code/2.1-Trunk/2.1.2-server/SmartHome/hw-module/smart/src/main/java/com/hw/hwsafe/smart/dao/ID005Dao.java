/**
 * 文件名：ID005Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.smart.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.D005PO;

/**
 * D005Dao层接口
 *
 */
public interface ID005Dao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	D005PO retrieveInstanceById(String ma001) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(D005PO d005PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(D005PO d005PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
	
	/**
	 * 批量删除实例
	 */
	Integer delBatchD005(Map<String, Object> map) throws Exception;
	
	/**
	 * 获取登录日志的分页信息
	 */
	@SuppressWarnings("rawtypes")
	List<Map<String, Object>> retrieveByPage1(Map map) throws Exception;
	/**
	 * 按手机厂商分布进行统计
	 */
	List<HashMap<String,Object>> groupByMA002(HashMap<String,Object> map) throws Exception;
	/**
	 * 按操作系统分布进行统计
	 */
	List<HashMap<String,Object>> groupByMA003(HashMap<String,Object> map) throws Exception;
	/**
	 * 按手机型号分布进行统计
	 */
	List<HashMap<String,Object>> groupByMA007(HashMap<String,Object> map) throws Exception;

}