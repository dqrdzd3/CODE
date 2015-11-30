package com.hw.hwsafe.smart.dao;

import java.util.List;

import com.hw.hwsafe.platform.dao.IBaseDao;

import com.hw.hwsafe.smart.pojo.ControlDevicePO;

public interface IControlDeviceDao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	ControlDevicePO retrieveInstanceById(String ma001) throws Exception;
	
	/**
	 * 通过PO获取实例
	 */
	List<ControlDevicePO> retrieveInstanceByPO(ControlDevicePO controlDevicePO) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(ControlDevicePO controlDevicePO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(ControlDevicePO controlDevicePO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
	/**
	 * 修改实例
	 */
	void updateInstanceName(ControlDevicePO controlDevicePO) throws Exception;
}