/**
 * 文件名：IB00103Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.dao;
import com.hw.hwsafe.corpinfo.pojo.B00103PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * B00103Dao层接口
 *
 */
public interface IB00103Dao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	B00103PO retrieveInstanceById(String ma001) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(B00103PO b00103PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(B00103PO b00103PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;

}