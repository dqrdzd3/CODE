package com.hw.hwsafe.corpinfo.dao.impl;

import com.hw.hwsafe.corpinfo.dao.IB00103Dao;
import com.hw.hwsafe.corpinfo.pojo.B00103PO;
import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;

public class B00103DaoImpl extends BaseDaoImpl implements IB00103Dao {

	@Override
	public B00103PO retrieveInstanceById(String ma001) throws Exception {
		return getSqlSession().getMapper(IB00103Dao.class).retrieveInstanceById(ma001);
	}

	@Override
	public void insertInstance(B00103PO b00103po) throws Exception {
		getSqlSession().getMapper(IB00103Dao.class).insertInstance(b00103po);
	}

	@Override
	public void updateInstance(B00103PO b00103po) throws Exception {
		getSqlSession().getMapper(IB00103Dao.class).updateInstance(b00103po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		getSqlSession().getMapper(IB00103Dao.class).deleteInstanceById(ma001);
	}

}
