package com.hw.hwsafe.smart.dao;

import java.util.List;
import java.util.Map;



import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.CorpAttachmentPO;


public interface ICorpAttachmentDao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	CorpAttachmentPO retrieveInstanceById(String ma001) throws Exception;
	
	/**
	 * 通过PO获取实例
	 */
	List<CorpAttachmentPO> retrieveInstanceByPO(CorpAttachmentPO a001PO) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(CorpAttachmentPO a001PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(CorpAttachmentPO a001PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
	
	
	/**
	 * 批量删除实例
	 */
	Integer delBatchInstance(Map<String, Object> map) throws Exception;
}
