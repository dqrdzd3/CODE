package com.hw.hwsafe.smart.dao.impl;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.IBigScreenDao;
import com.hw.hwsafe.smart.dao.IDeviceDao;
import com.hw.hwsafe.smart.pojo.DevicePO;
import com.hw.hwsafe.smart.pojo.DeviceSharePO;

public class DeviceDaoImpl extends BaseDaoImpl implements IDeviceDao {

	@Override
	public List<DeviceSharePO> getAllDeviceShare(Map<String, String> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IDeviceDao.class).getAllDeviceShare(map);
	}

	@Override
	public DeviceSharePO getDeviceShare(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IDeviceDao.class).getDeviceShare(map);
	}

	@Override
	public void insertDeviceShare(DeviceSharePO po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IDeviceDao.class).insertDeviceShare(po);
	}

	@Override
	public void deleteShareDeviceById(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IDeviceDao.class).deleteShareDeviceById(map);
	}

	@Override
	public List<Map<String, String>> getDeviceLinkage(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IDeviceDao.class).getDeviceLinkage(map);
	}

	@Override
	public void insertDeviceLinkage(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IDeviceDao.class).insertDeviceLinkage(map);
	}

	@Override
	public void deleteDeviceLinkageById(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IDeviceDao.class).deleteDeviceLinkageById(map);
	}

	@Override
	public void updateDeviceLinkage(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IDeviceDao.class).updateDeviceLinkage(map);
	}

	@Override
	public List<Map<String, String>> getDeviceAir(Map<String, String> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IDeviceDao.class).getDeviceAir(map);
	}

	@Override
	public void insertDeviceAir(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IDeviceDao.class).insertDeviceAir(map);
	}

	@Override
	public void deleteDeviceAirById(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IDeviceDao.class).deleteDeviceAirById(map);
	}

	@Override
	public void updateDeviceAir(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IDeviceDao.class).updateDeviceAir(map);
	}

	@Override
	public List<Map<String, String>> OnlyDeviceShareInvalid(
			Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IDeviceDao.class).OnlyDeviceShareInvalid(map);
	}

	@Override
	public List<Map<String, String>> OnlyDeviceLinkageInvalid(
			Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IDeviceDao.class).OnlyDeviceLinkageInvalid(map);
	}

	@Override
	public List<Map<String, String>> OnlyDeviceAirInvalid(
			Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IDeviceDao.class).OnlyDeviceAirInvalid(map);
	}

}
