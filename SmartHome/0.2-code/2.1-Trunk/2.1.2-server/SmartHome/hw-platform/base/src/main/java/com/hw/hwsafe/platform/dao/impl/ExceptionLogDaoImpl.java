/**
 * 文件名：ExceptionLogDaoImpl.java
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

import com.hw.hwsafe.platform.dao.IExceptionLogDao;
import com.hw.hwsafe.platform.pojo.ExceptionLogPO;

/**
 * 异常日志DAO实现类
 * 
 * @author 马宁
 * @创建时间 2013-03-20
 */
public class ExceptionLogDaoImpl extends BaseDaoImpl implements
		IExceptionLogDao {

	private static final long serialVersionUID = 6140857559118416791L;

	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IExceptionLogDao.class)
				.retrieveByPage(map);
	}

	@Override
	public ExceptionLogPO retrieveInstanceById(String id) {
		return getSqlSession().getMapper(IExceptionLogDao.class)
				.retrieveInstanceById(id);
	}

	@Override
	public String retrieveMaxCode() {
		return getSqlSession().getMapper(IExceptionLogDao.class)
				.retrieveMaxCode();
	}

	@Override
	public void insertExceptionLog(ExceptionLogPO logPO) {
		getSqlSession().getMapper(IExceptionLogDao.class).insertExceptionLog(
				logPO);
	}

}
