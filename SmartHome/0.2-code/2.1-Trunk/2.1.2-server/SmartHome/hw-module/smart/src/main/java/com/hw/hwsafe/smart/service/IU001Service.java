package com.hw.hwsafe.smart.service;

import java.util.HashMap;
import java.util.List;

import com.hw.hwsafe.smart.pojo.U001PO;


public interface IU001Service {
	
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
	 * 检验用户名是否重复
	 */
	boolean checkAccount(U001PO u001PO) throws Exception;
	/**
	 * Md5(Md5(用户名)+Pw)
	 */
	String GetMD5Code(String str1,String str2) throws Exception;
	/**
	 * 校验用户的合法性
	 */
	U001PO checkUserBySessionId(String sessionId) throws Exception;
	
	/**
	 * 寻找密码成功后清除验证码
	 */
	void updateYZM(String ma001) throws Exception;
	/**
	 * 发送验证码
	 */
	void sendYZM(HashMap<String, String> map) throws Exception;
	/**
	 * 用户总人数
	 */
	Integer getUserCount() throws Exception;
}
