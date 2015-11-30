package com.hw.hwsafe.smart.dao.impl;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.IBusinessDao;

import com.hw.hwsafe.smart.pojo.BusinessPO;

public class BusinessDaoImpl extends BaseDaoImpl implements IBusinessDao {

	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IBusinessDao.class).retrieveByPage(map);
	}
	@Override
	public BusinessPO retrieveInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IBusinessDao.class).retrieveInstanceById(ma001);
	}

	@Override
	public List<BusinessPO> retrieveInstanceByPO(BusinessPO a001PO)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IBusinessDao.class).retrieveInstanceByPO(a001PO);
	}

	@Override
	public void insertInstance(BusinessPO a001PO) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IBusinessDao.class).insertInstance(a001PO);
	}

	@Override
	public void updateInstance(BusinessPO a001PO) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IBusinessDao.class).updateInstance(a001PO);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IBusinessDao.class).deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchInstance(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IBusinessDao.class).delBatchInstance(map);
	}

}
