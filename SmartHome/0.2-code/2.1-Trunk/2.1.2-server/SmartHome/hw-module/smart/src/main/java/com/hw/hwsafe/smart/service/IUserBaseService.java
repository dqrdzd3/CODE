package com.hw.hwsafe.smart.service;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.smart.pojo.UserBasePO;

public interface IUserBaseService extends IBaseService {
	/**
	 * 更新
	 */
	void updateAppBase(UserBasePO po) throws Exception;
	/**
	 * 添加
	 */
	void insertAppBase(UserBasePO po) throws Exception;
	
	/**
	 * 根据userid查询po
	 */
	UserBasePO getBaseByUserId(String userid) throws Exception;
}
