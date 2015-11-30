package com.hw.hwsafe.sms.czd.dao;

import java.util.List;

import com.hw.hwsafe.sms.czd.po.MessagePO;
import com.hw.hwsafe.sms.czd.po.QueuePO;
/**
 * 项目名称：hw-sms
 * 类名称：ISMSDao
 * 类描述：短信发送Dao接口
 * 创建人：陈浙东
 * 创建时间：2013-4-09
 *
 */
public interface ISMSDao {
	/**
	 * 插入内部短信发送人的记录
	 * @param message
	 */
	public void insertInnerSendMessage(MessagePO message) throws Exception;
	
	/**
	 * 插入内部短信收信人的记录
	 * @param message
	 */
	public void insertInnerRecieveMessage(MessagePO message) throws Exception ;
	
	/**
	 * 插入外部短信发送人的记录
	 * @param message
	 */
	public void insertOuterSendMessage(MessagePO message) throws Exception;
	
	/**
	 * 插入外部短信收信人的记录
	 * @param message
	 */
	public void insertOuterReceiveMessage(MessagePO message) throws Exception;
	
	/**
	 * 插入高队列的数据
	 * @param message
	 */
	public void insertHighQueue(QueuePO queue) throws Exception;
	
	/**
	 * 插入低队列的数据
	 * @param message
	 */
	public void insertLowQueue(QueuePO queue) throws Exception;
	
	/**
	 * 获取高队列的数据
	 * @param message
	 */
	public List<QueuePO> getHighQueue() throws Exception;
	
	/**
	 * 获取低队列的数据
	 * @param message
	 */
	public List<QueuePO> getLowQueue() throws Exception;
	
	/**
	 * 按查询条件分页获取内部短信的发送记录
	 * @param message
	 * @return
	 */
	public List<MessagePO> getInnerMessageByPageSend(MessagePO message) throws Exception;
	
	
	/**
	 * 按查询条件分页获取内部短信的接收记录
	 * @param message
	 * @return
	 */
	public List<MessagePO> getInnerMessageByPageReceive(MessagePO message) throws Exception;
	
	/**
	 * 查询消息信息（发送方）
	 * @param id
	 * @return
	 */
	public MessagePO getMessageInfoSend(String id) throws Exception;
	
	/**
	 * 查询消息信息（接收方）
	 * @param id
	 * @return
	 */
	public MessagePO getMessageInfoReceive(String id) throws Exception;
	
	/**
	 * 发件箱删除
	 * @param id
	 */
	public void deleteInnerMessageSend(String id) throws Exception;
	
	/**
	 * 收件箱删除
	 * @param id
	 */
	public void deleteInnerMessageReceive(String id) throws Exception; 
	
	/**
	 * 更改已读标志
	 * @param id
	 */
	public void updateReadTag(String id) throws Exception;
	
	/**
	 * 插入外部短信发送记录  发送
	 * @param message
	 * @throws Exception
	 */
	public void insertOuterMessageSend(MessagePO message) throws Exception;
	
	/**
	 * 插入外部短信发送记录  接收
	 * @param message
	 * @throws Exception
	 */
	public void insertOuterMessageReceive(MessagePO message) throws Exception;
	
	/**
	 * 分页获取外部短信发送记录
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public List<MessagePO> getOuterMessageByPage(MessagePO message) throws Exception;
}

