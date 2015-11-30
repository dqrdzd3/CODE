/**
 * 文件名：BaseDaoImpl.java
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
import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * baseDao实现类
 * 
 * @author 马宁
 * @创建时间 2013-05-21
 */
public class BaseDaoImpl extends SqlSessionDaoSupport implements
		IBaseDao {

	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IBaseDao.class).retrieveByPage(map);
	}
		
	@Override
	public List listByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IBaseDao.class).listByPage(map);
	}

	@Override
	public String getTotal(Map map) throws Exception {
		return getSqlSession().getMapper(IBaseDao.class).getTotal(map);
	}

}
