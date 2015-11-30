/**
 * 文件名：OperLogDaoImpl.java
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
import com.hw.hwsafe.platform.dao.IOperLogDao;
import com.hw.hwsafe.platform.pojo.OperLogPO;

/**
 * 操作日志DAO实现类
 * 
 * @author 马宁
 * @创建时间 2013-03-20
 */
public class OperLogDaoImpl extends BaseDaoImpl implements IOperLogDao {

	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IOperLogDao.class).retrieveByPage(map);
	}

	@Override
	public void insertOperLog(OperLogPO logPO) {
		getSqlSession().getMapper(IOperLogDao.class).insertOperLog(logPO);
	}

	@Override
	public String retrieveMaxCode() {
		return getSqlSession().getMapper(IOperLogDao.class).retrieveMaxCode();
	}

	@Override
	public OperLogPO retrieveInstanceById(String id) {
		return getSqlSession().getMapper(IOperLogDao.class)
				.retrieveInstanceById(id);
	}

}
