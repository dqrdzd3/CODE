package com.hw.hwsafe.smart.service;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.smart.pojo.DevicePO;

public interface IDataService extends IBaseService {
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
