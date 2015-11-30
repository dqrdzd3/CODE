package com.hw.hwsafe.smart.service;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.smart.pojo.W001PO;

public interface IW001Service extends IBaseService {

	/**
	 * 添加实例
	 */
	void insertInstance(W001PO w001PO) throws Exception;
}
