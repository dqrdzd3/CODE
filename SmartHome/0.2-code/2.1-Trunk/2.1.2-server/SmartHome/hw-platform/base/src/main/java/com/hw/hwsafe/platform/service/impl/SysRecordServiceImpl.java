package com.hw.hwsafe.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.dao.ISysRecordDao;
import com.hw.hwsafe.platform.pojo.SysRecordPO;
import com.hw.hwsafe.platform.service.ISysRecordService;

public class SysRecordServiceImpl extends BaseServiceImpl implements
		ISysRecordService {
	
	@Autowired
	private ISysRecordDao sysRecordDao;

	@Override
	public SysRecordPO retrieveInstanceById(String ma001) throws Exception {
		return sysRecordDao.retrieveInstanceById(ma001);
	}

	@Override
	public Integer insertInstance(SysRecordPO sysRecordPO) throws Exception {
		return sysRecordDao.insertInstance(sysRecordPO);
	}

	@Override
	public Integer updateInstance(SysRecordPO sysRecordPO) throws Exception {
		return sysRecordDao.updateInstance(sysRecordPO);
	}

	@Override
	public Integer deleteInstanceById(String ma001) throws Exception {
		return sysRecordDao.deleteInstanceById(ma001);
	}

}
