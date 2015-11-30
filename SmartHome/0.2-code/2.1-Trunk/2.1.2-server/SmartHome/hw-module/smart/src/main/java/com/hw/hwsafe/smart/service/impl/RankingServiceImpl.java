package com.hw.hwsafe.smart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IRankingDao;
import com.hw.hwsafe.smart.pojo.RankingPO;
import com.hw.hwsafe.smart.service.IRankingService;

public class RankingServiceImpl extends BaseServiceImpl implements
		IRankingService {

	@Autowired
	private IRankingDao rankingDao;
	@Override
	public RankingPO retrieveInstanceByUserid(RankingPO po) throws Exception {
		// TODO Auto-generated method stub
		return rankingDao.retrieveInstanceByUserid(po);
	}

	@Override
	public void insertInstance(RankingPO po) throws Exception {
		// TODO Auto-generated method stub
		rankingDao.insertInstance(po);
	}

	@Override
	public void updateInstance(RankingPO po) throws Exception {
		// TODO Auto-generated method stub
		rankingDao.updateInstance(po);
	}

	@Override
	public Integer getRankByScore(RankingPO po) throws Exception {
		// TODO Auto-generated method stub
		return rankingDao.getRankByScore(po);
	}

}
