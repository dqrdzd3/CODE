package com.hw.hwsafe.smart.dao;

import java.util.List;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.UpdatePO;

public interface IUpdateDao extends IBaseDao {
	/**
	 * 通过userid获取实例
	 */
	List<UpdatePO> retrieveUpdateByUserId(String userid) throws Exception;
	
	/**
	 * 添加更新记录
	 */
	void insertUpdateInfo(UpdatePO  po) throws Exception;
}
