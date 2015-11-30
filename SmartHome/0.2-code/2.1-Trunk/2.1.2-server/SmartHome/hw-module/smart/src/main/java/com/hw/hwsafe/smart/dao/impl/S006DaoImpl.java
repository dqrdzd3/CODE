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
import com.hw.hwsafe.smart.dao.IS006Dao;
import com.hw.hwsafe.smart.pojo.S006PO;

/**
 * S006Dao层实现类
 *
 */
public class S006DaoImpl extends BaseDaoImpl implements IS006Dao{
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IS006Dao.class).retrieveByPage(map);
	}
	//添加留言
	@Override
	public void addMessage(S006PO s006po) throws Exception{
		getSqlSession().getMapper(IS006Dao.class).addMessage(s006po);
	}
	// 查询该用户的所有留言信息
	@Override
	public List<S006PO> getListMessage(String userId) throws Exception{
		return getSqlSession().getMapper(IS006Dao.class).getListMessage(userId);
	}
	@Override
	public Integer delBatchS006(Map<String, Object> map) throws Exception {
		return getSqlSession().getMapper(IS006Dao.class).delBatchS006(map);
	}
	@Override
	public S006PO retrieveInstanceById(String ma001) throws Exception {
		return getSqlSession().getMapper(IS006Dao.class).retrieveInstanceById(ma001);
	}
	@Override
	public void updateInstance(S006PO s006po) throws Exception {
		getSqlSession().getMapper(IS006Dao.class).updateInstance(s006po);
	}
}