package com.hw.hwsafe.corpinfo.service;

import java.util.Map;

import com.hw.hwsafe.corpinfo.pojo.B104PO;

public interface IB104Service {
	

	/**
	 * 通过id获取实例
	 */
	B104PO retrieveInstanceById(String ma001) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(B104PO b104PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(B104PO b104PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;

	/**
	 * 批量删除实例
	 */
	Integer delBatchB104(Map<String, Object> map) throws Exception;
	/**
	 * 审核不通过更改状态
	 */
	void updateRefuseStatus(B104PO b104PO) throws Exception;

}
