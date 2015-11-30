package com.hw.hwsafe.smart.dao;

import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;


public interface IP2BDao extends IBaseDao {

	
	/**
	 * 通过sensorid 和 key 获得合法性
	 */
	String authByIdAndKey(Map<String, String> parm) throws Exception;
}
