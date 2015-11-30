package com.hw.hwsafe.smart.dao.impl;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.IBigScreenDao;
import com.hw.hwsafe.smart.dao.ILogDao;
import com.hw.hwsafe.smart.pojo.LogCrashPO;
import com.hw.hwsafe.smart.pojo.LogOpPO;
import com.hw.hwsafe.smart.pojo.UpdatePO;

public class LogDaoImpl extends BaseDaoImpl implements ILogDao {

	@Override
	public void insertOpLog(LogOpPO po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ILogDao.class).insertOpLog(po);
	}

	@Override
	public void insertCrashLog(LogCrashPO po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ILogDao.class).insertCrashLog(po);
	}


}
