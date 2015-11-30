package com.hw.hwsafe.platform.service;

import com.hw.hwsafe.platform.pojo.SysRecordPO;

public interface ISysRecordService {
	
	/**
	 * 通过id获取实例
	 */
	SysRecordPO retrieveInstanceById(String ma001) throws Exception;

	/**
	 * 添加实例
	 */
	Integer insertInstance(SysRecordPO sysRecordPO) throws Exception;

	/**
	 * 修改实例
	 */
	Integer updateInstance(SysRecordPO sysRecordPO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	Integer deleteInstanceById(String ma001) throws Exception;

}
