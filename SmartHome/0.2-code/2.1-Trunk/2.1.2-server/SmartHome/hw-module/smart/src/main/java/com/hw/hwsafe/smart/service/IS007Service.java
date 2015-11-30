package com.hw.hwsafe.smart.service;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.smart.pojo.S007PO;
import com.hw.hwsafe.smart.pojo.SolutionPO;

public interface IS007Service extends IBaseService {
	/**
	 * 通过id获取实例
	 */
	S007PO retrieveInstanceById(String ma001) throws Exception;
	/**
	 * 获得全部
	 */
	List<S007PO> retrieveAllInstance() throws Exception;
	/**
	 * 通过条件获取
	 */
	S007PO retrieveInstanceByCondition(Map<String, Object> map) throws Exception;
	/**
	 * 通过id批量删除推送记录
	 */
	Integer delBatchS007(Map<String, Object> map) throws Exception;
	/**
	 * 插入
	 */

	void insertInstance(S007PO po) throws Exception;
	/**
	 * 修改实例
	 */
	void updateInstance(S007PO po) throws Exception;
	
	/**
	 * 获得全部
	 */
	List<SolutionPO> retrieveAllSolution(SolutionPO po) throws Exception;
}
