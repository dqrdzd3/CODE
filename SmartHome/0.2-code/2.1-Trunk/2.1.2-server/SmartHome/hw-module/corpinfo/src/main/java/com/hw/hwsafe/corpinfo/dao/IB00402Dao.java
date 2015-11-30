/**
 * 文件名：IB00402Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.dao;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B00402PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * B00402Dao层接口
 *
 */
public interface IB00402Dao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	B00402PO retrieveInstanceById(String ma001) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(B00402PO b00402PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(B00402PO b00402PO) throws Exception;
	/**
	 * 通过条件查询记录
	 * @param b00402PO
	 * @return
	 * @throws Exception
	 */
	List<B00402PO> retrieveInstanceByPO(B00402PO b00402PO)throws Exception;

}