/**
 * 文件名：U001DaoImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.smart.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.IU001Dao;
import com.hw.hwsafe.smart.pojo.U001PO;

/**
 * U001Dao层实现类
 *
 */
public class U001DaoImpl extends BaseDaoImpl implements IU001Dao{

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IU001Dao.class).retrieveByPage(map);
	}

	@Override
	public U001PO retrieveInstanceById(String ma001) throws Exception{
		return getSqlSession().getMapper(IU001Dao.class).retrieveInstanceById(ma001);
	}
	
	@Override
	public U001PO retrieveInstanceByToken(String ma010) throws Exception {
		return getSqlSession().getMapper(IU001Dao.class).retrieveInstanceByToken(ma010);
	}
	
	@Override
	public List<U001PO> retrieveInstanceByPO(U001PO u001po) throws Exception {
		return getSqlSession().getMapper(IU001Dao.class).retrieveInstanceByPO(u001po);
	}

	@Override
	public void insertInstance(U001PO u001PO) throws Exception{
		getSqlSession().getMapper(IU001Dao.class).insertInstance(u001PO);
	}

	@Override
	public void updateInstance(U001PO u001PO) throws Exception{
		getSqlSession().getMapper(IU001Dao.class).updateInstance(u001PO);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception{
		getSqlSession().getMapper(IU001Dao.class).deleteInstanceById(ma001);
	}

	@Override
	public void updateYZM(String ma001) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IU001Dao.class).updateYZM(ma001);
	}

	@Override
	public void sendYZM(HashMap<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IU001Dao.class).sendYZM(map);
	}

	@Override
	public Integer getUserCount() throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IU001Dao.class).getUserCount();
	}

}