package com.hw.hwsafe.smart.dao.impl;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.IU001Dao;
import com.hw.hwsafe.smart.dao.IW001Dao;
import com.hw.hwsafe.smart.pojo.W001PO;

public class W001DaoImpl extends BaseDaoImpl implements IW001Dao {

	@Override
	public void insertInstance(W001PO w001PO) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IW001Dao.class).insertInstance(w001PO);
	}

}
