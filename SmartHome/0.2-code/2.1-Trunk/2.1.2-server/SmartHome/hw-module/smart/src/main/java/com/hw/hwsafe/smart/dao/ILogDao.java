package com.hw.hwsafe.smart.dao;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.LogCrashPO;
import com.hw.hwsafe.smart.pojo.LogOpPO;

public interface ILogDao extends IBaseDao {

	/**
	 * 添加操作日志
	 */
	void insertOpLog(LogOpPO  po) throws Exception;
	/**
	 * 添加异常日志
	 */
	void insertCrashLog(LogCrashPO  po) throws Exception;
}
