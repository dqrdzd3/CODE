package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.ICorpOnlineDao;
import com.hw.hwsafe.smart.pojo.CorpCrmPO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.ICorpOnlineService;

public class CorpOnlineServiceImpl extends BaseServiceImpl implements
		ICorpOnlineService {

	@Autowired
	private ICorpOnlineDao corpOnlineDao;
	
	@Override
	public List<U001PO> doQuery(Map<String,Object> map) throws Exception {
		// TODO Auto-generated method stub
		return corpOnlineDao.doQuery(map);
	}

	@Override
	public List<CorpCrmPO> rankWithCondition(Map<String, Object> map)
			throws Exception {
		if (map.get("rankBy").equals("week")) {
			return corpOnlineDao.rankWithConditionByWeek(map);
		}
		if (map.get("rankBy").equals("month")) {
			return corpOnlineDao.rankWithConditionByMonth(map);
		}
		return null;
	}

	@Override
	public Integer countAll(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return corpOnlineDao.countAll(map);
	}

}
