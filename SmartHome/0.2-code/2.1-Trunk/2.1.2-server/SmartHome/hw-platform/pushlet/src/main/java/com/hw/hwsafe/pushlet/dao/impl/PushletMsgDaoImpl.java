/**
 * 文件名：PushletMsgDaoImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.pushlet.dao.impl;

import java.util.List;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.pushlet.dao.IPushletMsgDao;
import com.hw.hwsafe.pushlet.po.PushletMsgPO;

/**
 * pushletMsg的dao层实现类
 * 
 * @author 马宁
 * @创建时间 2013-09-04 08:52
 */
public class PushletMsgDaoImpl extends BaseDaoImpl implements IPushletMsgDao{

	@Override
	public PushletMsgPO retrieveInstanceById(String id) throws Exception{
		return getSqlSession().getMapper(IPushletMsgDao.class).retrieveInstanceById(id);
	}

	@Override
	public void insertInstance(PushletMsgPO pushletMsgPO) throws Exception{
		getSqlSession().getMapper(IPushletMsgDao.class).insertInstance(pushletMsgPO);
	}

	@Override
	public void updateInstance(PushletMsgPO pushletMsgPO) throws Exception{
		getSqlSession().getMapper(IPushletMsgDao.class).updateInstance(pushletMsgPO);
	}

	@Override
	public void deleteInstanceById(String id) throws Exception{
		getSqlSession().getMapper(IPushletMsgDao.class).deleteInstanceById(id);
	}

	@Override
	public List<PushletMsgPO> retrieveInstancesByUserId(String userId)
			throws Exception {
		return getSqlSession().getMapper(IPushletMsgDao.class).retrieveInstancesByUserId(userId);
	}

}