package com.hw.hwsafe.sms.czd.dao.impl;
/**
 * 项目名称：hw-sms
 * 类名称：SMSDaoImpl
 * 类描述：短信发送Dao接口实现类
 * 创建人：陈浙东
 * 创建时间：2013-4-09
 *
 */
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.hw.hwsafe.sms.czd.dao.ISMSDao;
import com.hw.hwsafe.sms.czd.po.MessagePO;
import com.hw.hwsafe.sms.czd.po.QueuePO;

public class SMSDaoImpl extends SqlSessionDaoSupport implements ISMSDao {

	@Override
	public void insertInnerSendMessage(MessagePO message) throws Exception {
		getSqlSession().getMapper(ISMSDao.class).insertInnerSendMessage(message);

	}

	@Override
	public void insertInnerRecieveMessage(MessagePO message) throws Exception {
		getSqlSession().getMapper(ISMSDao.class).insertInnerRecieveMessage(message);

	}

	@Override
	public void insertOuterSendMessage(MessagePO message) throws Exception {
		getSqlSession().getMapper(ISMSDao.class).insertOuterSendMessage(message);

	}

	@Override
	public void insertOuterReceiveMessage(MessagePO message) throws Exception {
		getSqlSession().getMapper(ISMSDao.class).insertOuterReceiveMessage(message);

	}

	@Override
	public void insertHighQueue(QueuePO queue) throws Exception {
		getSqlSession().getMapper(ISMSDao.class).insertHighQueue(queue);

	}

	@Override
	public void insertLowQueue(QueuePO queue) throws Exception {
		getSqlSession().getMapper(ISMSDao.class).insertLowQueue(queue);

	}

	@Override
	public List<QueuePO> getHighQueue() throws Exception {
		 return getSqlSession().getMapper(ISMSDao.class).getHighQueue();
	}

	@Override
	public List<QueuePO> getLowQueue() throws Exception {
		return getSqlSession().getMapper(ISMSDao.class).getLowQueue();
	}	

	@Override
	public MessagePO getMessageInfoSend(String id) throws Exception {
		return getSqlSession().getMapper(ISMSDao.class).getMessageInfoSend(id);
	}

	@Override
	public MessagePO getMessageInfoReceive(String id) throws Exception {
		return getSqlSession().getMapper(ISMSDao.class).getMessageInfoReceive(id);
	}

	@Override
	public List<MessagePO> getInnerMessageByPageSend(MessagePO message) throws Exception {
		return getSqlSession().getMapper(ISMSDao.class).getInnerMessageByPageSend(message);
	}

	@Override
	public List<MessagePO> getInnerMessageByPageReceive(MessagePO message) throws Exception {
		return getSqlSession().getMapper(ISMSDao.class).getInnerMessageByPageReceive(message);
	}

	@Override
	public void deleteInnerMessageSend(String id) throws Exception {
		getSqlSession().getMapper(ISMSDao.class).deleteInnerMessageSend(id);
		
	}

	@Override
	public void deleteInnerMessageReceive(String id) throws Exception {
		getSqlSession().getMapper(ISMSDao.class).deleteInnerMessageReceive(id);
	}

	@Override
	public void updateReadTag(String id) throws Exception {
		getSqlSession().getMapper(ISMSDao.class).updateReadTag(id);
		
	}

	@Override
	public void insertOuterMessageSend(MessagePO message) throws Exception {
		getSqlSession().getMapper(ISMSDao.class).insertOuterMessageSend(message);
		
	}

	@Override
	public void insertOuterMessageReceive(MessagePO message) throws Exception {
		getSqlSession().getMapper(ISMSDao.class).insertOuterMessageReceive(message);		
	}

	@Override
	public List<MessagePO> getOuterMessageByPage(MessagePO message)
			throws Exception {
		return getSqlSession().getMapper(ISMSDao.class).getOuterMessageByPage(message);
	}

	
	
}
