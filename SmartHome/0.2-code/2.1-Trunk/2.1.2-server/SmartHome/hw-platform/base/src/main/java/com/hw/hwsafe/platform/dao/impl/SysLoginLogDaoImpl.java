/**
 * 文件名：SysLoginLogDaoImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.dao.impl;

import java.util.List;
import java.util.Map;
import com.hw.hwsafe.platform.dao.ISysLoginLogDao;
import com.hw.hwsafe.platform.pojo.SysLoginLogPO;

/**
 * SYS_LOGIN_LOGDao层实现类
 *
 */
public class SysLoginLogDaoImpl extends BaseDaoImpl implements ISysLoginLogDao{

	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(ISysLoginLogDao.class).retrieveByPage(map);
	}

	@Override
	public SysLoginLogPO retrieveInstanceById(String id) throws Exception{
		return getSqlSession().getMapper(ISysLoginLogDao.class).retrieveInstanceById(id);
	}

	@Override
	public void insertInstance(SysLoginLogPO sysLoginLogPO) throws Exception{
		getSqlSession().getMapper(ISysLoginLogDao.class).insertInstance(sysLoginLogPO);
	}

	@Override
	public void updateInstance(SysLoginLogPO sysLoginLogPO) throws Exception{
		getSqlSession().getMapper(ISysLoginLogDao.class).updateInstance(sysLoginLogPO);
	}

	@Override
	public void deleteInstanceById(String id) throws Exception{
		getSqlSession().getMapper(ISysLoginLogDao.class).deleteInstanceById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.hw.hwsafe.platform.dao.ISysLoginLogDao#retrieveGovIdByCorpId(java.lang.String)
	 */
	@Override
	public String retrieveGovIdByCorpId(String corpId) {
		return getSqlSession().getMapper(ISysLoginLogDao.class).retrieveGovIdByCorpId(corpId);
	}

}