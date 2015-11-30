package com.hw.hwsafe.smart.dao.impl;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.ICorpRankDao;
import com.hw.hwsafe.smart.dao.IUserBaseDao;
import com.hw.hwsafe.smart.pojo.UserBasePO;

public class UserBaseDaoImpl extends BaseDaoImpl implements IUserBaseDao {

	@Override
	public void updateAppBase(UserBasePO po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IUserBaseDao.class).updateAppBase(po);
	}

	@Override
	public void insertAppBase(UserBasePO po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IUserBaseDao.class).insertAppBase(po);
	}

	@Override
	public UserBasePO getBaseByUserId(String userid) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IUserBaseDao.class).getBaseByUserId(userid);
	}

}
