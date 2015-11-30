/**
 * 文件名：ISysRecordDao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.dao;
import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.platform.pojo.SysRecordPO;

/**
 * SYS_RECORDDao层接口
 *
 */
public interface ISysRecordDao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	SysRecordPO retrieveInstanceById(String ma001) throws Exception;

	/**
	 * 添加实例
	 */
	Integer insertInstance(SysRecordPO sysRecordPO) throws Exception;

	/**
	 * 修改实例
	 */
	Integer updateInstance(SysRecordPO sysRecordPO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	Integer deleteInstanceById(String ma001) throws Exception;

}