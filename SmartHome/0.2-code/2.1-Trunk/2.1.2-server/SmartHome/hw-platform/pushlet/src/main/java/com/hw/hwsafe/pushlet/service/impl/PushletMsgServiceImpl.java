package com.hw.hwsafe.pushlet.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.Session;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.pushlet.dao.IPushletMsgDao;
import com.hw.hwsafe.pushlet.po.PushletMsgPO;
import com.hw.hwsafe.pushlet.service.IPushletMsgService;
import com.hw.hwsafe.pushlet.utils.EventManagerHw;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * pushletMsg的service层实现类
 * 
 * @author 马宁
 * @创建时间 2013-09-04
 */
public class PushletMsgServiceImpl implements IPushletMsgService{

	@Autowired
	private IPushletMsgDao pushletMsgDao;
	
	@Override
	public void insertMsg(PushletMsgPO pushletMsgPO) throws Exception {
		pushletMsgDao.insertInstance(pushletMsgPO);
	}

	@Override
	public void insertMsg(String userId, String msg) throws Exception {
		insertMsg(userId, msg, null);
	}
	
	@Override
	public void insertMsg(String userId, String msg, String url)
			throws Exception {
		PushletMsgPO pushletMsgPO = new PushletMsgPO(UUIDGenerater.getUUID(),
				userId, msg, url);
		insertMsg(pushletMsgPO);
	}
	
	@Override
	public void deleteMsg(String id) throws Exception {
		pushletMsgDao.deleteInstanceById(id);
	}

	@Override
	public void deleteMsgs(List<String> ids) throws Exception {
		for(String id : ids){
			deleteMsg(id);
		}
	}

	@Override
	public List<PushletMsgPO> retrievePushletMsgPosByUserId(String userId)
			throws Exception {
		return pushletMsgDao.retrieveInstancesByUserId(userId);
	}

	@Override
	public Map<String, List<PushletMsgPO>> retrievePushletMsgPosByUserIds(
			List<String> userIds) throws Exception {
		
		Map<String, List<PushletMsgPO>> result = new HashMap<String, List<PushletMsgPO>>();
		
		for(String userId : userIds){
			List<PushletMsgPO> pushletMsgPOs = retrievePushletMsgPosByUserId(userId);
			result.put(userId, pushletMsgPOs);
		}
		
		return result;
	}

	@Override
	public void unicastCurrentUserMsg() throws Exception {
		String userId = SessionUtil.getUserId();
		List<PushletMsgPO> pos = retrievePushletMsgPosByUserId(userId);
		
		for(PushletMsgPO po : pos){
			Event event = EventManagerHw.createHwUnicastEvent(po);
			
			Dispatcher dispatcher = Dispatcher.getInstance();
			if(dispatcher != null){
				dispatcher.unicast(event, userId);
			}
		}
	}

	@Override
	public void unicastUserMsg(String userId) throws Exception {
		List<PushletMsgPO> pos = retrievePushletMsgPosByUserId(userId);
		
		for (PushletMsgPO po : pos) {
			Event event = EventManagerHw.createHwUnicastEvent(po);
			Dispatcher.getInstance().unicast(event, userId);
		}
	}

	@Override
	public void handleUnicast(Session[] sessions) throws Exception {
		List<String> userIds = getUserIds(sessions);
		
		for(String userId : userIds){
			unicastUserMsg(userId);
		}
		
	}
	
	// ------------------- private methods -------------------
	
	/*
	 * 通过session数组获取userId集合
	 */
	private List<String> getUserIds(Session[] sessions){
		List<String> result = new ArrayList<String>();
		for(Session session : sessions){
			result.add(session.getId());
		}
		return result;
	}

}
