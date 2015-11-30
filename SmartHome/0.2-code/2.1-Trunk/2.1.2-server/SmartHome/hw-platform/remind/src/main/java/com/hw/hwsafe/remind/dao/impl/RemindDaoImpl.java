/**
 * 文件名：RemindDaoImpl.java
 *
 * 版本信息：
 * 日期：2013-4-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2013 
 * 版权所有
 *
 */
package com.hw.hwsafe.remind.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.hw.hwsafe.remind.dao.IRemindDao;
import com.hw.hwsafe.remind.po.RemindPO;


/**
 * 
 * 项目名称：hw-remind
 * 类名称：RemindDaoImpl
 * 类描述：
 * 创建人：陈浙东
 * 创建时间：2013-4-11 下午3:43:38
 * 修改人：陈浙东
 * 修改时间：2013-4-11 下午3:43:38
 * 修改备注：
 * @version 
 * 
 */
public class RemindDaoImpl extends SqlSessionDaoSupport implements IRemindDao {

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.remind.dao.IRemindDao#insertRemind(com.hw.hwsafe.remind.po.RemindPO)
	 */
	@Override
	public void insertRemind(RemindPO remindPO) throws Exception {
		getSqlSession().getMapper(IRemindDao.class).insertRemind(remindPO);

	}

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.remind.dao.IRemindDao#getRemindTotal(java.lang.String)
	 */
	@Override
	public int getRemindTotal(String receive) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IRemindDao.class).getRemindTotal(receive);
	}

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.remind.dao.IRemindDao#deleteRemind(java.lang.String)
	 */
	@Override
	public void deleteRemind(String remindId) throws Exception {
		getSqlSession().getMapper(IRemindDao.class).deleteRemind(remindId);

	}

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.remind.dao.IRemindDao#getRemindList(java.lang.String)
	 */
	@Override
	public List<RemindPO> getRemindList(String receive) throws Exception {
		return getSqlSession().getMapper(IRemindDao.class).getRemindList(receive);
	}

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.remind.dao.IRemindDao#getRemindInfo(java.lang.String)
	 */
	@Override
	public RemindPO getRemindInfo(String remindId) throws Exception {
		return getSqlSession().getMapper(IRemindDao.class).getRemindInfo(remindId);
	}

}
