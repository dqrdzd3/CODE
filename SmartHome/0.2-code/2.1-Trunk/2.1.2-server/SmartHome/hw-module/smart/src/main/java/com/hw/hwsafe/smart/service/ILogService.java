package com.hw.hwsafe.smart.service;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.smart.pojo.LogCrashPO;
import com.hw.hwsafe.smart.pojo.LogOpPO;

public interface ILogService extends IBaseService{
	/**
	 * 添加操作日志
	 */
	void insertOpLog(LogOpPO  po) throws Exception;
	/**
	 * 添加异常日志
	 */
	void insertCrashLog(LogCrashPO  po) throws Exception;
}
