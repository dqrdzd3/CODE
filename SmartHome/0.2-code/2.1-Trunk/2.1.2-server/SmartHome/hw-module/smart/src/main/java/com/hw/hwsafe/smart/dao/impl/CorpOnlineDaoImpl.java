package com.hw.hwsafe.smart.dao.impl;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.ICorpCrmDao;
import com.hw.hwsafe.smart.dao.ICorpOnlineDao;
import com.hw.hwsafe.smart.pojo.CorpCrmPO;
import com.hw.hwsafe.smart.pojo.U001PO;

public class CorpOnlineDaoImpl extends BaseDaoImpl implements ICorpOnlineDao {
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(ICorpOnlineDao.class).retrieveByPage(map);
	}
	@Override
	public List<U001PO> doQuery(Map<String,Object> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpOnlineDao.class).doQuery(map);
	}

	@Override
	public List<CorpCrmPO> rankWithConditionByWeek(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpOnlineDao.class).rankWithConditionByWeek(map);
	}

	@Override
	public List<CorpCrmPO> rankWithConditionByMonth(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpOnlineDao.class).rankWithConditionByMonth(map);
	}

	@Override
	public Integer countAll(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpOnlineDao.class).countAll(map);
	}

}
