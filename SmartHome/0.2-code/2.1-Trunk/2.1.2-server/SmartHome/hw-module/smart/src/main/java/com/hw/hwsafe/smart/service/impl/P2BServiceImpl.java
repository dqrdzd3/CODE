package com.hw.hwsafe.smart.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.ID002Dao;
import com.hw.hwsafe.smart.dao.IP2BDao;
import com.hw.hwsafe.smart.service.IP2BService;


public class P2BServiceImpl extends BaseServiceImpl implements IP2BService {
	@Autowired
	private IP2BDao p2BDao;
	@Override
	public String authByIdAndKey(Map<String, String> parm) throws Exception {
		// TODO Auto-generated method stub
		return p2BDao.authByIdAndKey(parm);
	}

}
