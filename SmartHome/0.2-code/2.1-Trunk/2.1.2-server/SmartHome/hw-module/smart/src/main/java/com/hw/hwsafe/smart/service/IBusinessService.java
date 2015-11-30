package com.hw.hwsafe.smart.service;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.smart.pojo.BusinessPO;

public interface IBusinessService extends IBaseService {
	/**
	 * 通过id获取实例
	 */
	BusinessPO retrieveInstanceById(String ma001) throws Exception;
	
	/**
	 * 通过PO获取实例
	 */
	List<BusinessPO> retrieveInstanceByPO(BusinessPO a001PO) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(BusinessPO a001PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(BusinessPO a001PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
	
	
	/**
	 * 批量删除实例
	 */
	Integer delBatchInstance(Map<String, Object> map) throws Exception;
}
