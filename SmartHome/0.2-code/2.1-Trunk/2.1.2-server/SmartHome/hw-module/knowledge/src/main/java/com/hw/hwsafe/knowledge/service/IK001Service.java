package com.hw.hwsafe.knowledge.service;

import java.util.Map;

import com.hw.hwsafe.knowledge.pojo.K001PO;

public interface IK001Service {
	
	/**
	 * 通过id获取实例
	 */
	K001PO retrieveInstanceById(String ma001) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(K001PO k001PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(K001PO k001PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
	
	/**
	 * 通过id批量删除实例
	 */
	Integer delBatchK001(Map<String, Object> map) throws Exception;

}
