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

import com.hw.hwsafe.gov.pojo.C002PO;
import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.smart.dao.IS001Dao;
import com.hw.hwsafe.smart.dao.IS002Dao;
import com.hw.hwsafe.smart.pojo.S002PO;
import com.hw.hwsafe.smart.pojo.U001PO;

/**
 * S002Dao层实现类
 *
 */
public class S002DaoImpl extends BaseDaoImpl implements IS002Dao{

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IS002Dao.class).retrieveByPage(map);
	}
	@Override
	public List<U001PO> retrieveInstance() throws Exception {
		return getSqlSession().getMapper(IS002Dao.class).retrieveInstance();
	}
	@Override
	public List<U001PO> retrieveInstanceByName(String name) throws Exception {
		return getSqlSession().getMapper(IS002Dao.class).retrieveInstanceByName(name);
	}
	@Override
	public void insertInstance(S002PO s002po) throws Exception {
		getSqlSession().getMapper(IS002Dao.class).insertInstance(s002po);	
	}
	@Override
	public Integer delBatchS002(Map<String, Object> map) throws Exception {
		return getSqlSession().getMapper(IS002Dao.class).delBatchS002(map);
	}
	@Override
	public S002PO retrieveInstanceById(String ma001) throws Exception {
		return getSqlSession().getMapper(IS002Dao.class).retrieveInstanceById(ma001);
	}
	@Override
	public List<U001PO> retrieveProvinceList() throws Exception {
		return getSqlSession().getMapper(IS002Dao.class).retrieveProvinceList();
	}
	@Override
	public List<U001PO> retrieveCityList() throws Exception {
		return getSqlSession().getMapper(IS002Dao.class).retrieveCityList();
	}
	@Override
	public List<U001PO> retrieveAreaList() throws Exception {
		return getSqlSession().getMapper(IS002Dao.class).retrieveAreaList();
	}
}