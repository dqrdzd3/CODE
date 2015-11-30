package com.hw.hwsafe.smart.dao.impl;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.ICorpCrmDao;
import com.hw.hwsafe.smart.dao.ICorpRankDao;
import com.hw.hwsafe.smart.pojo.CorpCrmPO;
import com.hw.hwsafe.smart.pojo.CorpRankPO;

public class CorpRankDaoImpl extends BaseDaoImpl implements ICorpRankDao {
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(ICorpRankDao.class).retrieveByPage(map);
	}
	@Override
	public List<CorpRankPO> retrieveRankByMonth(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpRankDao.class).retrieveRankByMonth(map);
	}

	@Override
	public List<CorpRankPO> retrieveRankByWeek(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpRankDao.class).retrieveRankByWeek(map);
	}

	@Override
	public List<Map<String, Object>> rankOnCondition(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpRankDao.class).rankOnCondition(map);
	}

	@Override
	public List<Map<String, Object>> rankByUser(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpRankDao.class).rankByUser(map);
	}

	@Override
	public List<Map<String, Object>> retrieveCityInRank(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpRankDao.class).retrieveCityInRank(map);
	}

}
