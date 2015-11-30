package com.hw.hwsafe.smart.dao;

import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.DevicePO;
import com.hw.hwsafe.smart.pojo.DeviceSharePO;

public interface IDataDao extends IBaseDao {

	/**
	 * 通过deviceid 获得该设备信息
	 */
	DevicePO getCurDevice(String deviceId) throws Exception;
	
	/**
	 * 通过deviceid 设置该设备信息
	 */
    Boolean setCurDevice(String devicePO) throws Exception;
    
    /**
	 * 通过deviceid 获得该局域网配置设备信息
	 */
	DevicePO getCurDeviceConfig(String deviceId) throws Exception;
	
	/**
	 * 通过deviceid 设置该设备局域网信息信息
	 */
    Boolean setCurDeviceConfig(String devicePO) throws Exception;
}
