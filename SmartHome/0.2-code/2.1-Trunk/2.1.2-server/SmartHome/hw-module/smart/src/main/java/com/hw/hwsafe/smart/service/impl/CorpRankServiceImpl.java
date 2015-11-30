package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.ICorpRankDao;
import com.hw.hwsafe.smart.pojo.CorpRankPO;
import com.hw.hwsafe.smart.service.ICorpRankService;

public class CorpRankServiceImpl extends BaseServiceImpl implements
		ICorpRankService {

	@Autowired
	private ICorpRankDao corpRankDao;
	
	
	@Override
	public List<CorpRankPO> retrieveRankByMonth(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return corpRankDao.retrieveRankByMonth(map);
	}

	@Override
	public List<CorpRankPO> retrieveRankByWeek(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return corpRankDao.retrieveRankByWeek(map);
	}

	@Override
	public List<Map<String, Object>> rankOnCondition(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return corpRankDao.rankOnCondition(map);
	}

	@Override
	public List<Map<String, Object>> rankByUser(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return corpRankDao.rankByUser(map);
	}

	@Override
	public List<Map<String, Object>> retrieveCityInRank(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return corpRankDao.retrieveCityInRank(map);
	}

}
