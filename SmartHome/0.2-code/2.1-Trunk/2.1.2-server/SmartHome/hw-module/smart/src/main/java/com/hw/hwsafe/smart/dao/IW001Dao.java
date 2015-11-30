package com.hw.hwsafe.smart.dao;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.W001PO;


public interface IW001Dao extends IBaseDao {

	/**
	 * 添加实例
	 */
	void insertInstance(W001PO w001PO) throws Exception;
}
