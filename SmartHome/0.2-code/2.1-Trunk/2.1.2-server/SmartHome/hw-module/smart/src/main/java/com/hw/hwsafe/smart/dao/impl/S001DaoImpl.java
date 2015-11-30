package com.hw.hwsafe.smart.dao.impl;
/**
 * 文件名：K001DaoImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.IS001Dao;
import com.hw.hwsafe.smart.pojo.S001PO;

/**
 * K001Dao层实现类
 *
 */
public class S001DaoImpl extends BaseDaoImpl implements IS001Dao{

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IS001Dao.class).retrieveByPage(map);
	}

	@Override
	public S001PO retrieveInstanceById(String ma001) throws Exception{
		return getSqlSession().getMapper(IS001Dao.class).retrieveInstanceById(ma001);
	}

	@Override
	public void insertInstance(S001PO s001PO) throws Exception{
		getSqlSession().getMapper(IS001Dao.class).insertInstance(s001PO);
	}

	@Override
	public void updateInstance(S001PO s001PO) throws Exception{
		getSqlSession().getMapper(IS001Dao.class).updateInstance(s001PO);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception{
		getSqlSession().getMapper(IS001Dao.class).deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchS001(Map<String, Object> map) throws Exception {
		return getSqlSession().getMapper(IS001Dao.class).delBatchS001(map);
	}

	@Override
	public List<Map<String, Object>> getQAList() throws Exception {
		return getSqlSession().getMapper(IS001Dao.class).getQAList();
	}


}