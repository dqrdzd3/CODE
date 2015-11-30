package com.hw.hwsafe.smart.dao.impl;

import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.IP2BDao;


public class P2BDaoImpl extends BaseDaoImpl implements IP2BDao {

	@Override
	public String authByIdAndKey(Map<String, String> parm) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IP2BDao.class).authByIdAndKey(parm);
	}

}
