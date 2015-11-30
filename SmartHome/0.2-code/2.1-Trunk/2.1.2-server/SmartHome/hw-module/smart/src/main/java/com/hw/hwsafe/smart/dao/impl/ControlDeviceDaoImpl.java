package com.hw.hwsafe.smart.dao.impl;

import java.util.List;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.IA001Dao;
import com.hw.hwsafe.smart.dao.IControlDeviceDao;
import com.hw.hwsafe.smart.pojo.ControlDevicePO;

public class ControlDeviceDaoImpl extends BaseDaoImpl implements
		IControlDeviceDao {

	@Override
	public ControlDevicePO retrieveInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IControlDeviceDao.class).retrieveInstanceById(ma001);
	}

	@Override
	public List<ControlDevicePO> retrieveInstanceByPO(
			ControlDevicePO controlDevicePO) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IControlDeviceDao.class).retrieveInstanceByPO(controlDevicePO);
	}

	@Override
	public void insertInstance(ControlDevicePO controlDevicePO)
			throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IControlDeviceDao.class).insertInstance(controlDevicePO);
	}

	@Override
	public void updateInstance(ControlDevicePO controlDevicePO)
			throws Exception {
		getSqlSession().getMapper(IControlDeviceDao.class).updateInstance(controlDevicePO);

	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		getSqlSession().getMapper(IControlDeviceDao.class).deleteInstanceById(ma001);

	}

	@Override
	public void updateInstanceName(ControlDevicePO controlDevicePO)
			throws Exception {
		getSqlSession().getMapper(IControlDeviceDao.class).updateInstanceName(controlDevicePO);
		
	}

}
