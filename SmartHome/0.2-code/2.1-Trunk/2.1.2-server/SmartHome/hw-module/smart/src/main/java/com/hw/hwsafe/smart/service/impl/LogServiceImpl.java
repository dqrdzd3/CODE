package com.hw.hwsafe.smart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.ILogDao;
import com.hw.hwsafe.smart.pojo.LogCrashPO;
import com.hw.hwsafe.smart.pojo.LogOpPO;
import com.hw.hwsafe.smart.service.ILogService;

public class LogServiceImpl extends BaseServiceImpl implements ILogService {

	@Autowired
	private ILogDao logDao;
	@Override
	public void insertOpLog(LogOpPO po) throws Exception {
		// TODO Auto-generated method stub
		logDao.insertOpLog(po);
	}

	@Override
	public void insertCrashLog(LogCrashPO po) throws Exception {
		// TODO Auto-generated method stub
		logDao.insertCrashLog(po);
	}

}
