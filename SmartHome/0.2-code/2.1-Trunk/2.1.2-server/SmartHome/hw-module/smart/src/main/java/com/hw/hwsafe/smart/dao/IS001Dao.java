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
import com.hw.hwsafe.smart.pojo.S001PO;

/**
 * K001Dao层接口
 *
 */
public interface IS001Dao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	S001PO retrieveInstanceById(String ma001) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(S001PO s001PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(S001PO s001PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
	/**
	 * 通过id批量删除实例
	 */
	Integer delBatchS001(Map<String, Object> map) throws Exception;
	/**
	 * 
	 * 获取问答列表
	 * @return
	 * @throws Exception          
	 * @author liyuhao
	 * @create_time 2014年7月23日下午7:10:34
	 */
    List<Map<String, Object>> getQAList() throws Exception;
}