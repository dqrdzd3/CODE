package com.hw.hwsafe.smart.service;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.smart.dao.ICorpMaterialDao;
import com.hw.hwsafe.smart.pojo.CorpMaterialDetailPO;
import com.hw.hwsafe.smart.pojo.CorpMaterialPO;

public interface ICorpMaterialService extends IBaseService {
	/**
	 * 通过id获取实例
	 */
	CorpMaterialPO retrieveInstanceById(String ma001) throws Exception;
	CorpMaterialDetailPO retrieveDetailById(String ma001) throws Exception;
	/**
	 * 通过PO获取实例
	 */
	List<CorpMaterialPO> retrieveInstanceByPO(CorpMaterialPO a001PO) throws Exception;
	List<CorpMaterialDetailPO> retrieveDetailByPO(CorpMaterialDetailPO a001PO) throws Exception;
	List<CorpMaterialDetailPO> retrieveDetails(CorpMaterialDetailPO a001PO) throws Exception;
	/**
	 * 添加实例
	 */
	void insertInstance(CorpMaterialPO a001PO) throws Exception;
	void insertDetail(CorpMaterialDetailPO a001PO) throws Exception;
	/**
	 * 修改实例
	 */
	void updateInstance(CorpMaterialPO a001PO) throws Exception;
	void updateDetail(CorpMaterialDetailPO a001PO) throws Exception;
	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
	
	
	/**
	 * 批量删除实例
	 */
	Integer delBatchInstance(Map<String, Object> map) throws Exception;
	/**
	 * 计算要更换的
	 */
	Integer countAll(Map<String, Object> map) throws Exception;
}
