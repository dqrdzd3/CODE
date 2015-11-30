package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IS001Dao;
import com.hw.hwsafe.smart.pojo.S001PO;
import com.hw.hwsafe.smart.service.IS001Service;

public class S001ServiceImpl extends BaseServiceImpl implements IS001Service {
	
	@Autowired
	private IS001Dao s001Dao;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return s001Dao.retrieveByPage(map);
	}

	@Override
	public S001PO retrieveInstanceById(String ma001) throws Exception {
		return s001Dao.retrieveInstanceById(ma001);
	}

	@Override
	public void insertInstance(S001PO k001po) throws Exception {
		s001Dao.insertInstance(k001po);
	}

	@Override
	public void updateInstance(S001PO k001po) throws Exception {
		s001Dao.updateInstance(k001po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		s001Dao.deleteInstanceById(ma001);
	}
	
	@Override
	public Integer delBatchS001(Map<String, Object> map) throws Exception{
		return s001Dao.delBatchS001(map);
	}

	@Override
	public List<Map<String, Object>> getQAList() throws Exception {
		return s001Dao.getQAList();
	}

}
