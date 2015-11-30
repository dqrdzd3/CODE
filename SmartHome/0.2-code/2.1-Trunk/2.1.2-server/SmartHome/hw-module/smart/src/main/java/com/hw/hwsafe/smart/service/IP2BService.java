package com.hw.hwsafe.smart.service;

import java.util.Map;

import com.hw.hwsafe.platform.service.IBaseService;


public interface IP2BService extends IBaseService {
	/**
	 * 通过sensorid 和 key 获得合法性
	 */
	String authByIdAndKey(Map<String, String> parma) throws Exception;
}
