package com.hw.hwsafe.smart.dao.impl;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.IBusinessDao;
import com.hw.hwsafe.smart.dao.IMessageDao;
import com.hw.hwsafe.smart.pojo.MessagePO;

public class MessageDaoImpl extends BaseDaoImpl implements IMessageDao {

	@Override
	public List<Map<String, Object>> retrieveMessageByUserid(String userid)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IMessageDao.class).retrieveMessageByUserid(userid);
	}

	@Override
	public List<Map<String, Object>> retrieveMessageDetail(String id) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IMessageDao.class).retrieveMessageDetail(id);
	}

}
