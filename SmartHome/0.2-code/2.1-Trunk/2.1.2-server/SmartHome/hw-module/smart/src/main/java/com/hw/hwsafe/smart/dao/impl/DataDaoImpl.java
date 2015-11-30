package com.hw.hwsafe.smart.dao.impl;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.IDataDao;
import com.hw.hwsafe.smart.dao.IDeviceDao;
import com.hw.hwsafe.smart.pojo.DevicePO;

public class DataDaoImpl extends BaseDaoImpl implements IDataDao {

	@Override
	public DevicePO getCurDevice(String deviceId) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IDataDao.class).getCurDevice(deviceId);
	}

	@Override
	public Boolean setCurDevice(String devicePO) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IDataDao.class).setCurDevice(devicePO);
	}

	@Override
	public DevicePO getCurDeviceConfig(String deviceId) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IDataDao.class).getCurDeviceConfig(deviceId);
	}

	@Override
	public Boolean setCurDeviceConfig(String devicePO) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IDataDao.class).setCurDeviceConfig(devicePO);
	}

}
