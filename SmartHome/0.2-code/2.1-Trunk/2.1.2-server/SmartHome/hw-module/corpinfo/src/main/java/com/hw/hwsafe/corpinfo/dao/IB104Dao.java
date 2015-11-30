/**
 * 文件名：IB104Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.dao;

import java.util.Map;

import com.hw.hwsafe.corpinfo.pojo.B104PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * B104Dao层接口
 *
 */
public interface IB104Dao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	B104PO retrieveInstanceById(String ma001) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(B104PO b104PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(B104PO b104PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
	
	/**
	 * 审核通过更改状态
	 */
	void updateStatus(B104PO b104PO) throws Exception;
	/**
	 * 审核不通过更改状态
	 * 注：应与前一个方法进行合并
	 */
	void updateRefuseStatus(B104PO b104PO) throws Exception;
	
	/**
	 * 批量删除实例
	 */
	Integer delBatchB104(Map<String, Object> map) throws Exception;

}