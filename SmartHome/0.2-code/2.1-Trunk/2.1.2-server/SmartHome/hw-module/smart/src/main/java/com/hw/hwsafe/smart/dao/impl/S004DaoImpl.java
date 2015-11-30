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
import com.hw.hwsafe.smart.dao.IS004Dao;
import com.hw.hwsafe.smart.dao.IS006Dao;
import com.hw.hwsafe.smart.pojo.S004PO;
import com.hw.hwsafe.smart.pojo.S006PO;

/**
 * S004Dao层实现类
 *
 */
public class S004DaoImpl extends BaseDaoImpl implements IS004Dao{
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IS004Dao.class).retrieveByPage(map);
	}
	@Override
	public List<S004PO> getListTitle() throws Exception {
		return getSqlSession().getMapper(IS004Dao.class).getListTitle();
	}
	@Override
	public void insertInstance(S004PO s004po) throws Exception {
		getSqlSession().getMapper(IS004Dao.class).insertInstance(s004po);
	}
	@Override
	public Integer delBatchS004(Map<String, Object> map) throws Exception {
		return getSqlSession().getMapper(IS004Dao.class).delBatchS004(map);
	}
	@Override
	public S004PO retrieveInstanceById(String ma001) throws Exception {
		return getSqlSession().getMapper(IS004Dao.class).retrieveInstanceById(ma001);
	}
	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		getSqlSession().getMapper(IS004Dao.class).deleteInstanceById(ma001);
	}
}