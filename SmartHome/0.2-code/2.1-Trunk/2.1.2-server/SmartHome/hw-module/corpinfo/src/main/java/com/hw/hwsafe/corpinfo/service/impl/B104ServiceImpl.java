package com.hw.hwsafe.corpinfo.service.impl;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.corpinfo.dao.IB104Dao;
import com.hw.hwsafe.corpinfo.pojo.B104PO;
import com.hw.hwsafe.corpinfo.service.IB104Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

public class B104ServiceImpl extends BaseServiceImpl implements IB104Service {
	
	@Autowired
	private IB104Dao b104Dao;


	@Override
	public B104PO retrieveInstanceById(String ma001) throws Exception {
		return b104Dao.retrieveInstanceById(ma001);
	}

	@Override
	public void insertInstance(B104PO b104po) throws Exception {
		b104Dao.insertInstance(b104po);
	}

	@Override
	public void updateInstance(B104PO b104po) throws Exception {
		b104Dao.updateInstance(b104po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		b104Dao.deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchB104(Map<String, Object> map) throws Exception {
		return b104Dao.delBatchB104(map);
	}

	@Override
	public void updateRefuseStatus(B104PO b104po) throws Exception {
		b104Dao.updateRefuseStatus(b104po);
	}

}
