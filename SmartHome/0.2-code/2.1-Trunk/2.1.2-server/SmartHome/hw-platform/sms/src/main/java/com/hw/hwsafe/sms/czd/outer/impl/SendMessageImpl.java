package com.hw.hwsafe.sms.czd.outer.impl;
/**
 * 项目名称：hw-sms
 * 类名称：ISMSCat
 * 类描述：发送短信实现类
 * 创建人：陈浙东
 * 创建时间：2013-4-09
 *
 */
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.sms.czd.dao.ISMSDao;
import com.hw.hwsafe.sms.czd.outer.ISendMessage;
import com.hw.hwsafe.sms.czd.po.MessagePO;
import com.hw.hwsafe.sms.czd.po.QueuePO;
import com.hw.hwsafe.sms.czd.po.SMSCatPO;
import com.hw.hwsafe.sms.czd.util.SMSCatUtil;

public class SendMessageImpl implements ISendMessage {
	@Autowired
	private ISMSDao smsDao;
	@Override
	public int sendOuterMessage(MessagePO message) {
		int result = 0;
		List<String> peopleList = message.getRecievePeopleList();
		String mode = message.getMode();
		if("high".equals(mode)){
			//循环放队列里
			for(String telphone : peopleList){
				MessagePO po = new MessagePO();
				po.setContent(message.getContent());
				po.setTelphone(telphone);
				SMSCatUtil.highQueue.add(po);
			}
		}
		
		if("low".equals(mode)){
			for(String telphone : peopleList){
				MessagePO po = new MessagePO();
				po.setContent(message.getContent());
				po.setTelphone(telphone);
				SMSCatUtil.lowQueue.add(po);
			}
		}
		return result;
	}

	@Override
	public int sendInnerMessage(MessagePO message) {
		int result = 1;
		try{
			message.setSendTime(new Date());
			message.setSendDeleteFlag("0");
			message.setReceiveReadFlag("0");
			message.setReceiveDeleteFlag("0");
			String uuid = UUID.randomUUID().toString();
			message.setId(uuid);
			smsDao.insertInnerSendMessage(message);
			if(message.getRecievePeopleList() != null){
				for(String recievePeople : message.getRecievePeopleList() ){
					message.setSendPeople(uuid);
					message.setId(UUID.randomUUID().toString());
					message.setRecievePeople(recievePeople);
					smsDao.insertInnerRecieveMessage(message);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<MessagePO> getOuterMessage(MessagePO message) {
		List<MessagePO> list = null;
		try {
			list = smsDao.getOuterMessageByPage(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MessagePO> getInnerMessageSend(MessagePO message) {
		int curPage = message.getCurPage();
		int num = message.getNum();
		int beginNum = (curPage - 1) * num + 1;
		int endNum = curPage * num;
		
		message.setBeginNum(beginNum);
		message.setEndNum(endNum);
		List<MessagePO> list = null;
		try {
			list =  smsDao.getInnerMessageByPageSend(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MessagePO> getInnerMessageReceive(MessagePO message) {
		int curPage = message.getCurPage();
		int num = message.getNum();
		int beginNum = (curPage - 1) * num + 1;
		int endNum = curPage * num;
		
		message.setBeginNum(beginNum);
		message.setEndNum(endNum);
		List<MessagePO> list = null;
		try {
			list = smsDao.getInnerMessageByPageReceive(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteInnerMessageSend(String id) {
		int result = 1;
		try{
			smsDao.deleteInnerMessageSend(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteInnerMessageReceive(String id) {
		int result = 1;
		try{
			smsDao.deleteInnerMessageReceive(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateReadTag(String id) {
		int result = 1;
		try{
			smsDao.updateReadTag(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void insertOuterMessageRecord(MessagePO message) {
		try{
			
			message.setSendTime(new Date());
			String uuid = UUID.randomUUID().toString();
			message.setId(uuid);
			SMSCatPO catPO = SMSCatUtil.getSmsCatInfo();
			message.setIp("localhost");
			message.setPort(catPO.getPort());
			message.setPin(catPO.getPin());
			message.setGateway(catPO.getGateway());
			message.setManufacturer(catPO.getManufacturer());
			
			smsDao.insertOuterMessageSend(message);
			
//			if(message.getRecievePeopleList() != null){
//				for(String telphone : message.getRecievePeopleList() ){
//					message.setSendPeople(uuid);
//					message.setId(UUID.randomUUID().toString());
//					message.setTelphone(telphone);
//					message.setIp("0");
//					smsDao.insertOuterMessageReceive(message);
//				}
//			}
			
			message.setSendPeople(uuid);
			message.setId(UUID.randomUUID().toString());
			message.setIp("0");
			smsDao.insertOuterMessageReceive(message);
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public int insertHighQueue(){
		int result = 1;
		int count = 1;
		while(true){
			MessagePO po = SMSCatUtil.highQueue.poll();
			if(po == null){
				break;
			}
			
			QueuePO queue = new QueuePO();
			queue.setContent(po.getContent());
			queue.setId(UUID.randomUUID().toString());
			queue.setTelphone(po.getTelphone());
			queue.setSort(count);
			count++;
			try {
				smsDao.insertHighQueue(queue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		result = 0;
		return result;
	}

	@Override
	public int insertLowQueue() {
		int result = 1;
		int count = 1;
		while(true){
			MessagePO po = SMSCatUtil.lowQueue.poll();
			if(po == null){
				break;
			}
			
			QueuePO queue = new QueuePO();
			queue.setContent(po.getContent());
			queue.setId(UUID.randomUUID().toString());
			queue.setTelphone(po.getTelphone());
			queue.setSort(count);
			count++;
			try {
				smsDao.insertLowQueue(queue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		result = 0;
		return result;
		
	}

	@Override
	public int getHighQueue() {
		int result = 1;
		try {
			List<QueuePO> list = smsDao.getHighQueue();
			for(QueuePO po : list){
				MessagePO mpo = new MessagePO();
				mpo.setContent(po.getContent());
				mpo.setTelphone(po.getTelphone());
				SMSCatUtil.highQueue.add(mpo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;	
	}

	@Override
	public int getLowQueue() {
		int result = 1;
		try {
			List<QueuePO> list = smsDao.getHighQueue();
			for(QueuePO po : list){
				MessagePO mpo = new MessagePO();
				mpo.setContent(po.getContent());
				mpo.setTelphone(po.getTelphone());
				SMSCatUtil.lowQueue.add(mpo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;	
		
	}

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.sms.czd.outer.ISendMessage#getMessageInfoReceive(java.lang.String)
	 */
	@Override
	public MessagePO getMessageInfoReceive(String id) {
		MessagePO po = null;
		try {
			po = smsDao.getMessageInfoReceive(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return po;
	}

	public ISMSDao getSmsDao() {
		return smsDao;
	}

	public void setSmsDao(ISMSDao smsDao) {
		this.smsDao = smsDao;
	}

	/*
	   (non-Javadoc)
	 * @see com.hw.hwsafe.sms.czd.outer.ISendMessage#getMessageInfoSend(java.lang.String)
	 */
		
	@Override
	public MessagePO getMessageInfoSend(String id) {
		MessagePO po = null;
		try {
			po = smsDao.getMessageInfoSend(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return po;
	}


}
