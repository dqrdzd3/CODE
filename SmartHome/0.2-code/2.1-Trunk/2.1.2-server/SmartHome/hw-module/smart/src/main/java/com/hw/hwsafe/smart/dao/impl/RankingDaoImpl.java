package com.hw.hwsafe.smart.dao.impl;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.IRankingDao;
import com.hw.hwsafe.smart.pojo.RankingPO;

public class RankingDaoImpl extends BaseDaoImpl implements IRankingDao{

	@Override
	public RankingPO retrieveInstanceByUserid(RankingPO po) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IRankingDao.class).retrieveInstanceByUserid(po);
	}

	@Override
	public void insertInstance(RankingPO po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IRankingDao.class).insertInstance(po);
	}

	@Override
	public void updateInstance(RankingPO po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IRankingDao.class).updateInstance(po);
	}

	@Override
	public Integer getRankByScore(RankingPO po) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IRankingDao.class).getRankByScore(po);
	}

}
