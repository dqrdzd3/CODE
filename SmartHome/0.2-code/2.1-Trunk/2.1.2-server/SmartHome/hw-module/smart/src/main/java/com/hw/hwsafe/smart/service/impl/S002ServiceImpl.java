package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IS002Dao;
import com.hw.hwsafe.smart.pojo.S002PO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.IS002Service;

public class S002ServiceImpl extends BaseServiceImpl implements IS002Service {
	
	@Autowired
	private IS002Dao s002Dao;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return s002Dao.retrieveByPage(map);
	}

	@Override
	public List<U001PO> retrieveInstance() throws Exception {
		return s002Dao.retrieveInstance();
	}

	@Override
	public List<U001PO> retrieveInstanceByName(String name) throws Exception {
		return s002Dao.retrieveInstanceByName(name);
	}

	@Override
	public void insertInstance(S002PO s002po) throws Exception {
	    s002Dao.insertInstance(s002po);
	}

	@Override
	public Integer delBatchS002(Map<String, Object> map) throws Exception {
		return s002Dao.delBatchS002(map);
	}

	@Override
	public S002PO retrieveInstanceById(String ma001) throws Exception {
		return s002Dao.retrieveInstanceById(ma001);
	}

	@Override
	public List<U001PO> retrieveProvinceList() throws Exception {
		return s002Dao.retrieveProvinceList();
	}

	@Override
	public List<U001PO> retrieveCityList() throws Exception {
		return s002Dao.retrieveCityList();
	}

	@Override
	public List<U001PO> retrieveAreaList() throws Exception {
		return s002Dao.retrieveAreaList();
	}

}
