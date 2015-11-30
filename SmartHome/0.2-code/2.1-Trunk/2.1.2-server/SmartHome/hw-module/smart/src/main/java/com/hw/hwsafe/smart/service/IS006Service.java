package com.hw.hwsafe.smart.service;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.smart.pojo.S001PO;
import com.hw.hwsafe.smart.pojo.S006PO;

/**
 * 
 * @author 李羽皓
 *
 */

public interface IS006Service {
	//添加留言
	public void addMessage(S006PO s006po) throws Exception;
	//查询该用户的所有反馈信息
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
