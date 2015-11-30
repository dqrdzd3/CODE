package com.hw.hwsafe.smart.dao;

import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.UserBasePO;

public interface IUserBaseDao extends IBaseDao {

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
