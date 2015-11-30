/**
 * 文件名：IU001Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.smart.dao;

import java.util.HashMap;
import java.util.List;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.U001PO;

/**
 * U001Dao层接口
 */
public interface IU001Dao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	U001PO retrieveInstanceById(String ma001) throws Exception;
	
	/**
	 * 通过令牌获取实例
	 */
	U001PO retrieveInstanceByToken(String ma010) throws Exception;
	
	/**
	 * 通过PO获取实例
	 */
	List<U001PO> retrieveInstanceByPO(U001PO u001PO) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(U001PO u001PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(U001PO u001PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
	/**
	 * 寻找密码成功后清除验证码
	 */
	void updateYZM(String ma001) throws Exception;
	/**
	 * 发送验证码
	 */
	void sendYZM(HashMap<String, String> map) throws Exception;
	/**
	 * 获得用户总数
	 */
	Integer getUserCount() throws Exception;
}