/**
 * 文件名：D002DaoImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.smart.dao.impl;
import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.ID002Dao;
import com.hw.hwsafe.smart.pojo.D002PO;

/**
 * D002Dao层实现类
 *
 */
public class D002DaoImpl extends BaseDaoImpl implements ID002Dao{

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(ID002Dao.class).retrieveByPage(map);
	}

	@Override
	public D002PO retrieveInstanceById(String ma001) throws Exception{
		return getSqlSession().getMapper(ID002Dao.class).retrieveInstanceById(ma001);
	}
	
	@Override
	public List<D002PO> retrieveInstanceByPO(D002PO d002po) throws Exception {
		return getSqlSession().getMapper(ID002Dao.class).retrieveInstanceByPO(d002po);
	}

	@Override
	public void insertInstance(D002PO d002PO) throws Exception{
		getSqlSession().getMapper(ID002Dao.class).insertInstance(d002PO);
	}

	@Override
	public void updateInstance(D002PO d002PO) throws Exception{
		getSqlSession().getMapper(ID002Dao.class).updateInstance(d002PO);
	}

	@Override
	public void deleteInstanceById(D002PO d002po) throws Exception {
		getSqlSession().getMapper(ID002Dao.class).deleteInstanceById(d002po);
	}

	@Override
	public List<D002PO> retrieveDuplicateInstanceByPO(D002PO d002po)
			throws Exception {
		return getSqlSession().getMapper(ID002Dao.class).retrieveDuplicateInstanceByPO(d002po);
	}

	@Override
	public Integer delBatchD002(Map<String, Object> map) throws Exception {
		return getSqlSession().getMapper(ID002Dao.class).delBatchD002(map);
	}

	@Override
	public List<D002PO> retrieveInstanceByDevId(String ma004) throws Exception {
		return getSqlSession().getMapper(ID002Dao.class).retrieveInstanceByDevId(ma004);
	}

	@Override
	public Integer retrieveDeviceFromP001(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ID002Dao.class).retrieveDeviceFromP001(map);
	}

	@Override
	public void insertInstanceP(Map<String, Object> map) throws Exception {
		getSqlSession().getMapper(ID002Dao.class).insertInstanceP(map);
		
	}
}