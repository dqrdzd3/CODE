/**
 * 文件名：B00402DaoImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.dao.impl;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.hw.hwsafe.corpinfo.dao.IB00402Dao;
import com.hw.hwsafe.corpinfo.pojo.B00402PO;
import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;

/**
 * B00402Dao层实现类
 *
 */
public class B00402DaoImpl extends BaseDaoImpl implements IB00402Dao{

	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IB00402Dao.class).retrieveByPage(map);
	}

	@Override
	public B00402PO retrieveInstanceById(String ma001) throws Exception{
		return getSqlSession().getMapper(IB00402Dao.class).retrieveInstanceById(ma001);
	}

	@Override
	public void insertInstance(B00402PO b00402PO) throws Exception{
		getSqlSession().getMapper(IB00402Dao.class).insertInstance(b00402PO);
	}

	@Override
	public void updateInstance(B00402PO b00402PO) throws Exception{
		getSqlSession().getMapper(IB00402Dao.class).updateInstance(b00402PO);
	}
	@Override
	public List<B00402PO> retrieveInstanceByPO(B00402PO b00402PO) throws Exception{
		return getSqlSession().getMapper(IB00402Dao.class).retrieveInstanceByPO(b00402PO);
	}

}