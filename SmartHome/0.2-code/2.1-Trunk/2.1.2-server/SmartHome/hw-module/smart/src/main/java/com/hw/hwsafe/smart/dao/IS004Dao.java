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
import com.hw.hwsafe.smart.pojo.S004PO;

/**
 * S004Dao层接口
 * 
 */
public interface IS004Dao extends IBaseDao {
	/**
	 * 主题列表
	 */
	List<S004PO> getListTitle() throws Exception;
	/**
	 * 添加实例
	 */
	void insertInstance(S004PO s004PO) throws Exception;
	/**
	 * 通过id批量删除实例
	 */
	Integer delBatchS004(Map<String, Object> map) throws Exception;
	/**
	 * 通过id获取实例
	 */
	S004PO retrieveInstanceById(String ma001) throws Exception;
	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
}