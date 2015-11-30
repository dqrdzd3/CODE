/**
 * 文件名：ID002Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.smart.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.D002PO;

/**
 * D002Dao层接口
 *
 */
public interface ID002Dao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	D002PO retrieveInstanceById(String ma001) throws Exception;
	/**
	 * 通过设备id获取实例
	 */
	List<D002PO> retrieveInstanceByDevId(String ma004) throws Exception;
	
	/**
	 * 通过PO获取实例
	 */
	List<D002PO> retrieveInstanceByPO(D002PO d002PO) throws Exception;
	
	/**
	 * 通过PO来判断该设备是否已经注册过
	 */
	List<D002PO> retrieveDuplicateInstanceByPO(D002PO d002PO) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(D002PO d002PO) throws Exception;
	/**
	 * 添加实例p001
	 */
	void insertInstanceP(Map<String, Object> map) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(D002PO d002PO) throws Exception;

	/**
	 * 通过PO删除实例
	 */
	void deleteInstanceById(D002PO d002PO) throws Exception;
	
	/**
	 * 批量删除实例
	 */
	Integer delBatchD002(Map<String, Object> map) throws Exception;
	/**
	 * 查找数据库出库设备是否有此设备
	 */
	Integer retrieveDeviceFromP001(Map<String, Object> map) throws Exception;


}