/**
 * 文件名：IK001Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.smart.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.S006PO;

/**
 * S006Dao层接口
 * 
 */
public interface IS006Dao extends IBaseDao {
	// 添加留言
	public void addMessage(S006PO s006po) throws Exception;

	// 查询该用户的所有反馈信息
	List<S006PO> getListMessage(String userId) throws Exception;
	/**
	 * 通过id批量删除推送记录
	 */
	Integer delBatchS006(Map<String, Object> map) throws Exception;
	/**
	 * 通过id获取实例
	 */
	S006PO retrieveInstanceById(String ma001) throws Exception;
	/**
	 * 修改实例
	 */
	void updateInstance(S006PO s006PO) throws Exception;
}