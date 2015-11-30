/**
 * 文件名：IK008Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.dao;
import java.util.Map;

import com.hw.hwsafe.knowledge.pojo.K008PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * K008Dao层接口
 *
 */
public interface IK008Dao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	K008PO retrieveInstanceById(String ma001) throws Exception;

	/**
	 * 添加实例
	 */
	Integer insertInstance(K008PO k008PO) throws Exception;

	/**
	 * 修改实例
	 */
	Integer updateInstance(K008PO k008PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	Integer deleteInstanceById(Map<String,Object> map) throws Exception;

}