package com.hw.hwsafe.platform.dao.impl;

import com.hw.hwsafe.platform.dao.ISysRecordDao;
import com.hw.hwsafe.platform.pojo.SysRecordPO;

public class SysRecordDaoImpl extends BaseDaoImpl implements ISysRecordDao {

	@Override
	public SysRecordPO retrieveInstanceById(String ma001) throws Exception {
		return getSqlSession().getMapper(ISysRecordDao.class).retrieveInstanceById(ma001);
	}

	@Override
	public Integer insertInstance(SysRecordPO sysRecordPO) throws Exception {
		return getSqlSession().getMapper(ISysRecordDao.class).insertInstance(sysRecordPO);
	}

	@Override
	public Integer updateInstance(SysRecordPO sysRecordPO) throws Exception {
		return getSqlSession().getMapper(ISysRecordDao.class).updateInstance(sysRecordPO);
	}

	@Override
	public Integer deleteInstanceById(String ma001) throws Exception {
		return getSqlSession().getMapper(ISysRecordDao.class).deleteInstanceById(ma001);
	}

}
