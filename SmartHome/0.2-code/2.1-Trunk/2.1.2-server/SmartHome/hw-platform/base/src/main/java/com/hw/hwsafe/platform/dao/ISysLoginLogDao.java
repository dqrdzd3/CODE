/**
 * 文件名：ISysLoginLogDao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.dao;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.platform.pojo.SysLoginLogPO;

/**
 * SYS_LOGIN_LOGDao层接口
 * 
 */
public interface ISysLoginLogDao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	SysLoginLogPO retrieveInstanceById(String id) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(SysLoginLogPO sysLoginLogPO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(SysLoginLogPO sysLoginLogPO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String id) throws Exception;

	/**
	 * 
	 * 通过企业标识获取所属政府标识
	 * 
	 * @param corpId
	 *            - 企业标识
	 * @return
	 * @author 马宁
	 * @create_time 2013-6-6 下午2:16:42
	 */
	String retrieveGovIdByCorpId(String corpId);

}