package com.hw.hwsafe.pushlet.service;

import java.util.List;
import java.util.Map;

import nl.justobjects.pushlet.core.Session;

import com.hw.hwsafe.pushlet.po.PushletMsgPO;

/**
 * pushletMsg的service层接口
 * 
 * @author 马宁
 * @创建时间 2013-09-04 08:54
 *
 */
public interface IPushletMsgService {

	/**
	 * 添加消息
	 * @param pushletMsgPO - po
	 */
	void insertMsg(PushletMsgPO pushletMsgPO) throws Exception;
	
	/**
	 * 添加消息
	 * @param userId - 用户id
	 * @param msg - 消息内容
	 * @throws Exception
	 */
	void insertMsg(String userId, String msg) throws Exception;
	
	/**
	 * 添加消息
	 * @param userId - 用户id
	 * @param msg - 消息内容
	 * @param url - 访问url
	 * @throws Exception
	 */
	void insertMsg(String userId, String msg, String url) throws Exception;
	
	/**
	 * 删除消息
	 * @param id - 消息id
	 */
	void deleteMsg(String id) throws Exception;
	
	/**
	 * 删除消息
	 * @param ids - 消息id集合
	 */
	void deleteMsgs(List<String> ids) throws Exception;
	
	/**
	 * 通过用户id获取消息po集合
	 * @param userId - 用户id
	 */
	List<PushletMsgPO> retrievePushletMsgPosByUserId(String userId) throws Exception;
	
	/**
	 * 通过用户id集合获取po集合,并封装为map
	 * @param userIds - 用户id集合
	 * @return Map<String, List<PushletMsgPO>> - key:用户id; value:用户对应的po集合
	 */
	Map<String, List<PushletMsgPO>> retrievePushletMsgPosByUserIds(List<String> userIds) throws Exception;

	/**
	 * 单播推送当前用户的消息
	 */
	void unicastCurrentUserMsg() throws Exception;
	
	/**
	 * 单播推送指定用户的消息
	 * @param userId - 用户id
	 */
	void unicastUserMsg(String userId) throws Exception;
	
	/**
	 * 处理单播推送
	 * @param sessions - session数组
	 */
	void handleUnicast(Session[] sessions) throws Exception;
}
