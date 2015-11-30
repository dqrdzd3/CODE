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
import com.hw.hwsafe.smart.dao.IS005Dao;
import com.hw.hwsafe.smart.pojo.S005PO;

/**
 * S005Dao层实现类
 *
 */
public class S005DaoImpl extends BaseDaoImpl implements IS005Dao{
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IS005Dao.class).retrieveByPage(map);
	}

	@Override
	public void addMessage(S005PO s005po) throws Exception {
		getSqlSession().getMapper(IS005Dao.class).addMessage(s005po);;
	}

	@Override
	public List<Map<String, Object>> getReplyList(String titleId) throws Exception{
		return getSqlSession().getMapper(IS005Dao.class).getReplyList(titleId);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		getSqlSession().getMapper(IS005Dao.class).deleteInstanceById(ma001);
	}

	@Override
	public int getCounts(String ma001) throws Exception {
		return getSqlSession().getMapper(IS005Dao.class).getCounts(ma001);
	}
}