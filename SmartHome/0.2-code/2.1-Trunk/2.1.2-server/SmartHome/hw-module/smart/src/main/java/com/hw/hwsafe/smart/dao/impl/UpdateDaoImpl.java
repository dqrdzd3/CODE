package com.hw.hwsafe.smart.dao.impl;

import java.util.List;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.IUpdateDao;
import com.hw.hwsafe.smart.pojo.UpdatePO;

public class UpdateDaoImpl extends BaseDaoImpl implements IUpdateDao{

	@Override
	public List<UpdatePO> retrieveUpdateByUserId(String userid)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IUpdateDao.class).retrieveUpdateByUserId(userid);
	}

	@Override
	public void insertUpdateInfo(UpdatePO po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IUpdateDao.class).insertUpdateInfo(po);
	}

	
}
