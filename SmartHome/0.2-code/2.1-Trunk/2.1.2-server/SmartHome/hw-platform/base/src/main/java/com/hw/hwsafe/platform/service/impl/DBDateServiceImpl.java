package com.hw.hwsafe.platform.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.dao.IDBDateDao;
import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hw.hwsafe.platform.service.IDBDateService;

public class DBDateServiceImpl implements IDBDateService {

	@Autowired
	private IDBDateDao idbDateDao;

	public Date getDBDate() throws SystemException {
		return idbDateDao.getDBDate();
	}

}
