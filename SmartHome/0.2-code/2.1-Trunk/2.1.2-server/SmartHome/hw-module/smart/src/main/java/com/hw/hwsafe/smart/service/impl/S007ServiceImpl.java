package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IS007Dao;
import com.hw.hwsafe.smart.pojo.S007PO;
import com.hw.hwsafe.smart.pojo.SolutionPO;
import com.hw.hwsafe.smart.service.IS007Service;

public class S007ServiceImpl extends BaseServiceImpl implements IS007Service {

	@Autowired
	private IS007Dao s007Dao;
	@Override
	public S007PO retrieveInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return s007Dao.retrieveInstanceById(ma001);
	}

	@Override
	public void insertInstance(S007PO po) throws Exception {
		// TODO Auto-generated method stub
		s007Dao.insertInstance(po);
	}

	@Override
	public void updateInstance(S007PO po) throws Exception {
		// TODO Auto-generated method stub
		s007Dao.updateInstance(po);
	}

	@Override
	public S007PO retrieveInstanceByCondition(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return s007Dao.retrieveInstanceByCondition(map);
	}

	@Override
	public Integer delBatchS007(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return s007Dao.delBatchS007(map);
	}

	@Override
	public List<S007PO> retrieveAllInstance() throws Exception {
		// TODO Auto-generated method stub
		return s007Dao.retrieveAllInstance();
	}

	@Override
	public List<SolutionPO> retrieveAllSolution(SolutionPO po) throws Exception {
		// TODO Auto-generated method stub
		return s007Dao.retrieveAllSolution(po);
	}

}
