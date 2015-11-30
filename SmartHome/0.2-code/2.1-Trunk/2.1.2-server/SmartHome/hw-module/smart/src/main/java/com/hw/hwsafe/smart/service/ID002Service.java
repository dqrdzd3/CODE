
/**
 * 文件名：ID002Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.smart.service;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.smart.pojo.D002PO;
import com.hw.hwsafe.smart.pojo.SensorDetail;

/**
 * D002Dao层接口
 *
 */
public interface ID002Service extends IBaseService {

	/**
	 * 通过id获取实例
	 */
	D002PO retrieveInstanceById(String ma001) throws Exception;
	
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
	void insertD002(D002PO d002PO) throws Exception;
	/**
	 * 添加实例p001
	 */
	void insertInstanceP(Map<String, Object> map) throws Exception;

	/**
	 * 修改实例
	 */
	void updateD002(D002PO d002PO) throws Exception;

	/**
	 * 添加实例(接口使用）
	 */
	List<SensorDetail> insertInstance(D002PO d002PO) throws Exception;

	/**
	 * 修改实例（接口使用）
	 */
	List<SensorDetail> updateInstance(D002PO d002PO) throws Exception;

	/**
	 * 删除实例
	 */
	List<SensorDetail> deleteInstanceById(D002PO d002PO,String userId) throws Exception;
	/**
	 * 转换设备信息以方便返回
	 */
	List<SensorDetail> covertionDriver(String userId)throws Exception;
	
	/**
	 * 批量删除实例
	 */
	Integer delBatchD002(Map<String, Object> map) throws Exception;
	/**
	 * 通过设备id获取实例
	 */
	List<D002PO> retrieveInstanceByDevId(String ma004) throws Exception;
	/**
	 * 查找数据库出库设备是否有此设备
	 */
	Integer retrieveDeviceFromP001(Map<String, Object> map) throws Exception;
	


}