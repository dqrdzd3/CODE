package com.hw.hwsafe.smart.service;

import java.util.List;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.smart.pojo.UpdatePO;

public interface IUpdateService extends IBaseService {
	/**
	 * 通过appbaseid获取实例
	 */
	List<UpdatePO> retrieveUpdateByUserId(String userid) throws Exception;
	
	/**
	 * 添加更新记录
	 */
	void insertUpdateInfo(UpdatePO  po) throws Exception;
}
