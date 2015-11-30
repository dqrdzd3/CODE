package com.hw.hwsafe.knowledge.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.knowledge.dao.IK001Dao;
import com.hw.hwsafe.knowledge.pojo.K001PO;
import com.hw.hwsafe.knowledge.service.IK001Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

public class K001ServiceImpl extends BaseServiceImpl implements IK001Service {
	
	@Autowired
	private IK001Dao k001Dao;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return k001Dao.retrieveByPage(map);
	}

	@Override
	public K001PO retrieveInstanceById(String ma001) throws Exception {
		return k001Dao.retrieveInstanceById(ma001);
	}

	@Override
	public void insertInstance(K001PO k001po) throws Exception {
		k001Dao.insertInstance(k001po);
	}

	@Override
	public void updateInstance(K001PO k001po) throws Exception {
		k001Dao.updateInstance(k001po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		k001Dao.deleteInstanceById(ma001);
	}
	
	@Override
	public Integer delBatchK001(Map<String, Object> map) throws Exception{
		return k001Dao.delBatchK001(map);
	}

}
