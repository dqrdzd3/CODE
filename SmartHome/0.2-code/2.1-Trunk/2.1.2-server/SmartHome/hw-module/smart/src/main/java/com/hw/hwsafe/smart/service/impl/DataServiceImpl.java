package com.hw.hwsafe.smart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IDataDao;
import com.hw.hwsafe.smart.pojo.DevicePO;
import com.hw.hwsafe.smart.service.IDataService;

public class DataServiceImpl extends BaseServiceImpl implements IDataService {

	@Autowired
	private IDataDao dataDao;
	
	
	@Override
	public DevicePO getCurDevice(String deviceId) throws Exception {
		// TODO Auto-generated method stub
		return dataDao.getCurDevice(deviceId);
	}

	@Override
	public Boolean setCurDevice(String devicePO) throws Exception {
		// TODO Auto-generated method stub
		return dataDao.setCurDevice(devicePO);
	}

	@Override
	public DevicePO getCurDeviceConfig(String deviceId) throws Exception {
		// TODO Auto-generated method stub
		return dataDao.getCurDeviceConfig(deviceId);
	}

	@Override
	public Boolean setCurDeviceConfig(String devicePO) throws Exception {
		// TODO Auto-generated method stub
		return dataDao.setCurDeviceConfig(devicePO);
	}

}
