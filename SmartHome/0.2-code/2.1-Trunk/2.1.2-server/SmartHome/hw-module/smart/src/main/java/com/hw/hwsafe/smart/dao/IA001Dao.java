/**
 * 文件名：IA001Dao.java
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
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.A001PO;

/**
 * A001Dao层接口
 *
 */
public interface IA001Dao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	A001PO retrieveInstanceById(String ma001) throws Exception;
	
	/**
	 * 通过PO获取实例
	 */
	List<A001PO> retrieveInstanceByPO(A001PO a001PO) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(A001PO a001PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(A001PO a001PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
	
	
	/**
	 * 批量删除实例
	 */
	Integer delBatchInstance(Map<String, Object> map) throws Exception;
	/**
	 * 发送历史报警信息
	 */
	List<HashMap<String, Object>> getHisAlarmList(Map<String, Object> map) throws Exception;
	/**
	 * 
	 *发布报警信息
	 * @param ma006 设备id
	 * @return
	 * @throws Exception          
	 * @author liyuhao
	 * @create_time 2014年7月24日下午2:35:19
	 */
	List<Map<String, Object>> getNoAlarmList(Map<String, Object> map) throws Exception;
	 /**
     * 
     * 发布报警信息后修改，修改推送状态
     * @param map          
     * @author liyuhao
     * @create_time 2014年7月24日下午6:26:44
     */
	void updatePushState(Map<String, Object> map) throws Exception;

}