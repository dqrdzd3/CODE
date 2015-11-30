package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IDeviceDao;
import com.hw.hwsafe.smart.pojo.DevicePO;
import com.hw.hwsafe.smart.pojo.DeviceSharePO;
import com.hw.hwsafe.smart.service.IDeviceService;

public class DeviceServiceImpl extends BaseServiceImpl implements IDeviceService {

	@Autowired
	private IDeviceDao deviceDao;

	@Override
	public List<DeviceSharePO> getAllDeviceShare(Map<String, String> map)
			throws Exception {
		// TODO Auto-generated method stub
		return deviceDao.getAllDeviceShare(map);
	}

	@Override
	public DeviceSharePO getDeviceShare(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceShare(map);
	}

	@Override
	public void insertDeviceShare(DeviceSharePO po) throws Exception {
		// TODO Auto-generated method stub
		deviceDao.insertDeviceShare(po);
	}

	@Override
	public void deleteShareDeviceById(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		deviceDao.deleteShareDeviceById(map);
	}

	@Override
	public List<Map<String, String>> getDeviceLinkage(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceLinkage(map);
	}

	@Override
	public void insertDeviceLinkage(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		deviceDao.insertDeviceLinkage(map);
	}

	@Override
	public void deleteDeviceLinkageById(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		deviceDao.deleteDeviceLinkageById(map);
	}

	@Override
	public void updateDeviceLinkage(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		deviceDao.updateDeviceLinkage(map);
	}

	@Override
	public List<Map<String, String>> getDeviceAir(Map<String, String> map)
			throws Exception {
		// TODO Auto-generated method stub
		return deviceDao.getDeviceAir(map);
	}

	@Override
	public void insertDeviceAir(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		deviceDao.insertDeviceAir(map);
	}

	@Override
	public void deleteDeviceAirById(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		deviceDao.deleteDeviceAirById(map);
	}

	@Override
	public void updateDeviceAir(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		deviceDao.updateDeviceAir(map);
	}

	@Override
	public List<Map<String, String>> OnlyDeviceShareInvalid(
			Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return deviceDao.OnlyDeviceShareInvalid(map);
	}

	@Override
	public List<Map<String, String>> OnlyDeviceLinkageInvalid(
			Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return deviceDao.OnlyDeviceLinkageInvalid(map);
	}

	@Override
	public List<Map<String, String>> OnlyDeviceAirInvalid(
			Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return deviceDao.OnlyDeviceAirInvalid(map);
	}
	
}
