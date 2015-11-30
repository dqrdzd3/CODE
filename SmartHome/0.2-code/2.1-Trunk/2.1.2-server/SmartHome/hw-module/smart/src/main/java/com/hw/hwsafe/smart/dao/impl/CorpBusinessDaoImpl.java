package com.hw.hwsafe.smart.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.ICorpBusinessDao;
import com.hw.hwsafe.smart.pojo.BusinessPO;

public class CorpBusinessDaoImpl extends BaseDaoImpl implements
		ICorpBusinessDao {

	@Override
	public BusinessPO doCorpLogin(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpBusinessDao.class).doCorpLogin(map);
	}

	@Override
	public void doManageAcount(BusinessPO po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ICorpBusinessDao.class).doManageAcount(po);
	}

	@Override
	public BusinessPO resetPWD(BusinessPO po) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpBusinessDao.class).resetPWD(po);
	}

	@Override
	public void updateYZM(String ma001) throws Exception {
		getSqlSession().getMapper(ICorpBusinessDao.class).updateYZM(ma001);
		
	}

	@Override
	public void sendYZM(HashMap<String, String> map) throws Exception {
		getSqlSession().getMapper(ICorpBusinessDao.class).sendYZM(map);
		
	}

}
