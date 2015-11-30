package com.hw.hwsafe.corpinfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.dao.IB00103Dao;
import com.hw.hwsafe.corpinfo.pojo.B00103PO;
import com.hw.hwsafe.corpinfo.service.IB00103Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

public class B00103ServiceImpl extends BaseServiceImpl implements IB00103Service {
	
	@Autowired
	private IB00103Dao b01003Dao;

	@Override
	public B00103PO retrieveInstanceById(String ma001) throws Exception {
		return b01003Dao.retrieveInstanceById(ma001);
	}

	@Override
	public void insertInstance(B00103PO b00103po) throws Exception {
		b01003Dao.insertInstance(b00103po);
	}

	@Override
	public void updateInstance(B00103PO b00103po) throws Exception {
		b01003Dao.updateInstance(b00103po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		b01003Dao.deleteInstanceById(ma001);
	}

	public IB00103Dao getB01003Dao() {
		return b01003Dao;
	}

	public void setB01003Dao(IB00103Dao b01003Dao) {
		this.b01003Dao = b01003Dao;
	}
	
	

}
