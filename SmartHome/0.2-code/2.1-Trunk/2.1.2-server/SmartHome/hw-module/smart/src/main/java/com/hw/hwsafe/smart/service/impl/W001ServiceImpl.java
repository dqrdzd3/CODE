package com.hw.hwsafe.smart.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IW001Dao;
import com.hw.hwsafe.smart.pojo.W001PO;
import com.hw.hwsafe.smart.service.IW001Service;

public class W001ServiceImpl extends BaseServiceImpl implements IW001Service {
	@Autowired
	private IW001Dao w001Dao;
	@Override
	public void insertInstance(W001PO w001po) throws Exception {
		// TODO Auto-generated method stub
		w001Dao.insertInstance(w001po);
	}

}
