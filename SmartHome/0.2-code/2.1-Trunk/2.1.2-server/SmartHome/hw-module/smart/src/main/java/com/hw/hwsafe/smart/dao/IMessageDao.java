package com.hw.hwsafe.smart.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.MessagePO;

public interface IMessageDao extends IBaseDao {
	/**
	 * 通过userid获取实例
	 */
	List<Map<String, Object>> retrieveMessageByUserid(String  userid) throws Exception;
	/**
	 * 通过id 获取实例
	 */
	List<Map<String, Object>> retrieveMessageDetail(String id) throws Exception;
}
