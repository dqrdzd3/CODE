package com.hw.hwsafe.smart.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.DevicePO;
import com.hw.hwsafe.smart.pojo.DeviceSharePO;
import com.hw.hwsafe.smart.pojo.MessagePO;

public interface IDeviceDao extends IBaseDao {

	   //   共享 设备 
	/**
	 * 通过userid获取实例
	 */
	List<DeviceSharePO> getAllDeviceShare(Map<String, String> map) throws Exception;
	/**
	 * 通过id 获取实例
	 */
	DeviceSharePO getDeviceShare(Map<String, String> map) throws Exception;
	
	/**
	 * 添加
	 */
	void insertDeviceShare(DeviceSharePO po) throws Exception;
	/**
	 * 通过id删除实例
	 */
	void deleteShareDeviceById(Map<String, String> map) throws Exception;
	
	/**
	 * 通过toPhone 和  DEVICEID 检查是否唯一
	 */
	List<Map<String, String>> OnlyDeviceShareInvalid(Map<String, String> map) throws Exception;
	
	
	  //   联动设备  
	/**
	 * 通过传参获取实例
	 */
	List<Map<String, String>> getDeviceLinkage(Map<String, String> map) throws Exception;
	/**
	 * 添加
	 */
	void insertDeviceLinkage(Map<String, String> map) throws Exception;
	/**
	 * 通过id删除实例
	 */
	void deleteDeviceLinkageById(Map<String, String> map) throws Exception;
	
	/**
	 * 通过id删除实例
	 */
	void updateDeviceLinkage(Map<String, String> map) throws Exception;
	
	/**
	 * 通过userid 和  linkage_id 检查是否唯一
	 */
	List<Map<String, String>> OnlyDeviceLinkageInvalid(Map<String, String> map) throws Exception;

	//  空气电台设备
	/**
	 * 通过传参获取实例
	 */
	List<Map<String, String>> getDeviceAir(Map<String, String> map) throws Exception;
	/**
	 * 添加
	 */
	void insertDeviceAir(Map<String, String> map) throws Exception;
	/**
	 * 通过id删除实例
	 */
	void deleteDeviceAirById(Map<String, String> map) throws Exception;
	
	/**
	 * 通过id删除实例
	 */
	void updateDeviceAir(Map<String, String> map) throws Exception;
	/**
	 * 通过userid 和  deviceid 检查是否唯一
	 */
	List<Map<String, String>> OnlyDeviceAirInvalid(Map<String, String> map) throws Exception;
}
