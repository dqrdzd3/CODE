package com.hw.hwsafe.smart.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.ID005Dao;
import com.hw.hwsafe.smart.pojo.D005PO;

public class D005DaoImpl extends BaseDaoImpl implements ID005Dao {
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(ID005Dao.class).retrieveByPage(map);
	}

	@Override
	public D005PO retrieveInstanceById(String ma001) throws Exception {
		return getSqlSession().getMapper(ID005Dao.class).retrieveInstanceById(ma001);
	}

	@Override
	public void insertInstance(D005PO d005po) throws Exception {
		getSqlSession().getMapper(ID005Dao.class).insertInstance(d005po);
	}

	@Override
	public void updateInstance(D005PO d005po) throws Exception {
		getSqlSession().getMapper(ID005Dao.class).updateInstance(d005po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		getSqlSession().getMapper(ID005Dao.class).deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchD005(Map<String, Object> map) throws Exception {
		return getSqlSession().getMapper(ID005Dao.class).delBatchD005(map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage1(Map map) throws Exception {
		return getSqlSession().getMapper(ID005Dao.class).retrieveByPage1(map);
	}

	@Override
	public List<HashMap<String, Object>> groupByMA002(
			HashMap<String, Object> map) throws Exception {
		return getSqlSession().getMapper(ID005Dao.class).groupByMA002(map);
	}

	@Override
	public List<HashMap<String, Object>> groupByMA003(
			HashMap<String, Object> map) throws Exception {
		return getSqlSession().getMapper(ID005Dao.class).groupByMA003(map);
	}

	@Override
	public List<HashMap<String, Object>> groupByMA007(
			HashMap<String, Object> map) throws Exception {
		return getSqlSession().getMapper(ID005Dao.class).groupByMA007(map);
	}

}
