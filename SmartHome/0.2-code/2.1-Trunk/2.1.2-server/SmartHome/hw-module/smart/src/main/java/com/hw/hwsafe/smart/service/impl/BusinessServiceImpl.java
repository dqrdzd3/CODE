package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IBusinessDao;
import com.hw.hwsafe.smart.pojo.BusinessPO;
import com.hw.hwsafe.smart.service.IBusinessService;

public class BusinessServiceImpl extends BaseServiceImpl implements
		IBusinessService {

	@Autowired
	private IBusinessDao businessDao;
	@Override
	public BusinessPO retrieveInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return businessDao.retrieveInstanceById(ma001);
	}

	@Override
	public List<BusinessPO> retrieveInstanceByPO(BusinessPO a001PO)
			throws Exception {
		// TODO Auto-generated method stub
		return businessDao.retrieveInstanceByPO(a001PO);
	}

	@Override
	public void insertInstance(BusinessPO a001PO) throws Exception {
		// TODO Auto-generated method stub
		businessDao.insertInstance(a001PO);
	}

	@Override
	public void updateInstance(BusinessPO a001PO) throws Exception {
		// TODO Auto-generated method stub
		businessDao.updateInstance(a001PO);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		businessDao.deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchInstance(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return businessDao.delBatchInstance(map);
	}

	

}
