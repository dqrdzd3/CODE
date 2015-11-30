package com.hw.hwsafe.smart.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.ICorpBusinessDao;
import com.hw.hwsafe.smart.pojo.BusinessPO;
import com.hw.hwsafe.smart.service.IBusinessService;
import com.hw.hwsafe.smart.service.ICorpBusinessService;

public class CorpBusinessServiceImpl extends BaseServiceImpl implements
		ICorpBusinessService {

	@Autowired
	private ICorpBusinessDao corpBusinessDao;

	@Override
	public BusinessPO doCorpLogin(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return corpBusinessDao.doCorpLogin(map);
	}

	@Override
	public void doManageAcount(BusinessPO po) throws Exception {
		// TODO Auto-generated method stub
		 corpBusinessDao.doManageAcount(po);
	}

	@Override
	public BusinessPO resetPWD(BusinessPO po) throws Exception {
		// TODO Auto-generated method stub
		return corpBusinessDao.resetPWD(po);
	}

	@Override
	public void updateYZM(String ma001) throws Exception {
		 corpBusinessDao.updateYZM(ma001);
		
	}

	@Override
	public void sendYZM(HashMap<String, String> map) throws Exception {
		corpBusinessDao.sendYZM(map);
		
	}
	
	

}
