package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IMessageDao;
import com.hw.hwsafe.smart.pojo.MessagePO;
import com.hw.hwsafe.smart.service.IMessageService;

public class MessageServiceImpl extends BaseServiceImpl implements
		IMessageService {

	@Autowired
	private IMessageDao messageDao;
	@Override
	public List<Map<String, Object>> retrieveMessageByUserid(String userid)
			throws Exception {
		
		return messageDao.retrieveMessageByUserid(userid);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> retrieveMessageDetail(String id) throws Exception {
		// TODO Auto-generated method stub
		return messageDao.retrieveMessageDetail(id);
	}

	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		// TODO Auto-generated method stub
		return messageDao.retrieveByPage(map);
	}

}
