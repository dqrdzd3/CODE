/**
 * 文件名：K001DaoImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.dao.impl;
import java.util.List;
import java.util.Map;

import com.hw.hwsafe.knowledge.dao.IK001Dao;
import com.hw.hwsafe.knowledge.pojo.K001PO;
import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;

/**
 * K001Dao层实现类
 *
 */
public class K001DaoImpl extends BaseDaoImpl implements IK001Dao{

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IK001Dao.class).retrieveByPage(map);
	}

	@Override
	public K001PO retrieveInstanceById(String ma001) throws Exception{
		return getSqlSession().getMapper(IK001Dao.class).retrieveInstanceById(ma001);
	}

	@Override
	public void insertInstance(K001PO k001PO) throws Exception{
		getSqlSession().getMapper(IK001Dao.class).insertInstance(k001PO);
	}

	@Override
	public void updateInstance(K001PO k001PO) throws Exception{
		getSqlSession().getMapper(IK001Dao.class).updateInstance(k001PO);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception{
		getSqlSession().getMapper(IK001Dao.class).deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchK001(Map<String, Object> map) throws Exception {
		return getSqlSession().getMapper(IK001Dao.class).delBatchK001(map);
	}


}