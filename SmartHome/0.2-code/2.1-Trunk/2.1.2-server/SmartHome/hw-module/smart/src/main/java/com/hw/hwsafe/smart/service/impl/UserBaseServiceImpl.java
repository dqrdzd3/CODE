package com.hw.hwsafe.smart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IUserBaseDao;
import com.hw.hwsafe.smart.pojo.UserBasePO;
import com.hw.hwsafe.smart.service.IUserBaseService;

public class UserBaseServiceImpl extends BaseServiceImpl implements IUserBaseService {

	@Autowired
	private IUserBaseDao userBaseDao;
	@Override
	public void updateAppBase(UserBasePO po) throws Exception {
		// TODO Auto-generated method stub
		userBaseDao.updateAppBase(po);
	}

	@Override
	public void insertAppBase(UserBasePO po) throws Exception {
		// TODO Auto-generated method stub
		userBaseDao.insertAppBase(po);
	}

	@Override
	public UserBasePO getBaseByUserId(String userid) throws Exception {
		// TODO Auto-generated method stub
		return userBaseDao.getBaseByUserId(userid);
	}

}
