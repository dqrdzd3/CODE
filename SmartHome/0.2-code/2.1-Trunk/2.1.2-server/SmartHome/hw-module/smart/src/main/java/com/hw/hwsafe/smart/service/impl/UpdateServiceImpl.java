package com.hw.hwsafe.smart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IUpdateDao;
import com.hw.hwsafe.smart.pojo.UpdatePO;
import com.hw.hwsafe.smart.service.IUpdateService;

public class UpdateServiceImpl extends BaseServiceImpl implements
		IUpdateService {

	@Autowired
	private IUpdateDao updateDao;
	@Override
	public List<UpdatePO> retrieveUpdateByUserId(String userid)
			throws Exception {
		// TODO Auto-generated method stub
		return updateDao.retrieveUpdateByUserId(userid);
	}

	@Override
	public void insertUpdateInfo(UpdatePO po) throws Exception {
		// TODO Auto-generated method stub
		updateDao.insertUpdateInfo(po);
	}

}
