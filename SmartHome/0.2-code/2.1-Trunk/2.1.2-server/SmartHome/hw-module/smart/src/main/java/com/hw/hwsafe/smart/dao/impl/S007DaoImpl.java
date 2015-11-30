package com.hw.hwsafe.smart.dao.impl;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.IS006Dao;
import com.hw.hwsafe.smart.dao.IS007Dao;
import com.hw.hwsafe.smart.pojo.S007PO;
import com.hw.hwsafe.smart.pojo.SolutionPO;

public class S007DaoImpl extends BaseDaoImpl implements IS007Dao{
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IS007Dao.class).retrieveByPage(map);
	}
	@Override
	public S007PO retrieveInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IS007Dao.class).retrieveInstanceById(ma001);
	}

	@Override
	public void insertInstance(S007PO po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IS007Dao.class).insertInstance(po);
	}

	@Override
	public void updateInstance(S007PO po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IS007Dao.class).updateInstance(po);
	}

	@Override
	public S007PO retrieveInstanceByCondition(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IS007Dao.class).retrieveInstanceByCondition(map);
	}

	@Override
	public Integer delBatchS007(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IS007Dao.class).delBatchS007(map);
	}
	@Override
	public List<S007PO> retrieveAllInstance() throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IS007Dao.class).retrieveAllInstance();
	}
	@Override
	public List<SolutionPO> retrieveAllSolution(SolutionPO po) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IS007Dao.class).retrieveAllSolution(po);
	}

}
