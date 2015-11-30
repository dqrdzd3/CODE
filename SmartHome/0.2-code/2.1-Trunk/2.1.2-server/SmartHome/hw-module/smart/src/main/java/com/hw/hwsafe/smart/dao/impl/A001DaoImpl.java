/**
 * 文件名：A001DaoImpl.java
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
import com.hw.hwsafe.smart.dao.IA001Dao;
import com.hw.hwsafe.smart.pojo.A001PO;

/**
 * A001Dao层实现类
 *
 */
public class A001DaoImpl extends BaseDaoImpl implements IA001Dao{

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IA001Dao.class).retrieveByPage(map);
	}

	@Override
	public A001PO retrieveInstanceById(String ma001) throws Exception{
		return getSqlSession().getMapper(IA001Dao.class).retrieveInstanceById(ma001);
	}
	
	
	@Override
	public List<A001PO> retrieveInstanceByPO(A001PO a001po) throws Exception {
		return getSqlSession().getMapper(IA001Dao.class).retrieveInstanceByPO(a001po);
	}

	@Override
	public void insertInstance(A001PO a001PO) throws Exception{
		getSqlSession().getMapper(IA001Dao.class).insertInstance(a001PO);
	}

	@Override
	public void updateInstance(A001PO a001PO) throws Exception{
		getSqlSession().getMapper(IA001Dao.class).updateInstance(a001PO);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception{
		getSqlSession().getMapper(IA001Dao.class).deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchInstance(Map<String, Object> map) throws Exception {
		return getSqlSession().getMapper(IA001Dao.class).delBatchInstance(map);
	}

	@Override
	public List<HashMap<String, Object>> getHisAlarmList(Map<String, Object> map) throws Exception {
		return getSqlSession().getMapper(IA001Dao.class).getHisAlarmList(map);
	}

	@Override
	public List<Map<String, Object>> getNoAlarmList(Map<String, Object> map) throws Exception {
		return getSqlSession().getMapper(IA001Dao.class).getNoAlarmList(map);
	}

	@Override
	public void updatePushState(Map<String, Object> map) throws Exception {
		getSqlSession().getMapper(IA001Dao.class).updatePushState(map);;
	}



}