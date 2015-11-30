package com.hw.hwsafe.sms.czd.outer;

import java.util.List;

import com.hw.hwsafe.sms.czd.po.MessagePO;

/**
 * 项目名称：hw-sms
 * 类名称：ISendMessage
 * 类描述：短信发送对外接口
 * 创建人：陈浙东
 * 创建时间：2013-4-09
 *
 */
public interface ISendMessage {
	/**
	 * 发送外部短信，成功返回0     不成功返回1
	 * @param message
	 * @return int
	 * @author 陈浙东
	 */
	public int sendOuterMessage(MessagePO message);
	
	/**
	 * 发送内部短信，成功返回0     不成功返回1
	 * @param message
	 * @return int
	 * @author 陈浙东
	 */
	public int sendInnerMessage(MessagePO message);
	
	/**
	 * 按条件查询数据(外部短信)，分页获取，条件封装在message对象中
	 * @param message
	 * @return List<Message>
	 */
	public List<MessagePO> getOuterMessage(MessagePO message);
	
	
	/**
	 * 按条件查询数据(内部短信    发件箱)，分页获取，条件封装在message对象中
	 * @param message
	 * @return List<Message>
	 */
	public List<MessagePO> getInnerMessageSend(MessagePO message);
	
	/**
	 * 按条件查询数据(内部短信    收件箱)，分页获取，条件封装在message对象中
	 * @param message
	 * @return List<Message>
	 */
	public List<MessagePO> getInnerMessageReceive(MessagePO message);
	
	/**
	 * 发件箱删除
	 * @param id
	 * @return 0成功 1失败
	 */
	public int deleteInnerMessageSend(String id);
	
	/**
	 * 收件箱删除
	 * @param id
	 * @return 0成功 1失败
	 */
	public int deleteInnerMessageReceive(String id);
	
	/**
	 * 更改阅读标志
	 * @param id
	 * @return 0成功 1失败
	 */
	public int updateReadTag(String id);
	
	/**
	 * 插入外部短信发送记录
	 * @param message
	 */
	public void insertOuterMessageRecord(MessagePO message);
	
	/**
	 * 插入高队列的数据
	 * @return int 0表示成功 1表示失败
	 */
	public int insertHighQueue();
	
	/**
	 * 插入低队列的数据
	 * @return int 0表示成功 1表示失败
	 */
	public int insertLowQueue();
	
	/**
	 * 获取高队列的数据
	 * @return int 0表示成功 1表示失败
	 */
	public int getHighQueue();
	
	/**
	 * 获取低队列的数据
	 * @return int 0表示成功 1表示失败
	 */
	public int getLowQueue() ;
	
	/**
	 * 
	 * 函 数 名：getMessageInfoReceive
	 * 功能描述：获取短信息
	 * 输入参数：
	 * @param id
	 * 返 回 值： 
	 * @return MessagePO
	 * 创建人：陈浙东
	 * 创建时间：2013-4-12 上午11:16:34
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public MessagePO getMessageInfoReceive(String id);
	
	/**
	 * 
	 * 按发信箱获取短消息信息
	 * @param id
	 * @return          
	 * @author 陈浙东
	 * @create_time 2013-5-15 下午4:14:41
	 */
	public MessagePO getMessageInfoSend(String id);
}
