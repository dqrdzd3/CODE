package com.hw.hwsafe.smart.service;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.smart.pojo.MessagePO;

public interface IMessageService extends IBaseService {

	/**
	 * 通过userid获取实例
	 */
	List<Map<String, Object>> retrieveMessageByUserid(String  userid) throws Exception;
	/**
	 * 通过id 获取实例
	 */
	List<Map<String, Object>> retrieveMessageDetail(String id) throws Exception;
}
