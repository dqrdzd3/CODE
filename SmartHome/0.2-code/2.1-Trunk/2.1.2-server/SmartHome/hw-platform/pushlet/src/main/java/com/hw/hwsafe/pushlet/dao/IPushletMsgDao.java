/**
 * 文件名：IPushletMsgDao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.pushlet.dao;

import java.util.List;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.pushlet.po.PushletMsgPO;

/**
 * pushletMsg的dao层接口
 * 
 * @author 马宁
 * @创建时间 2013-09-04 08:50
 */
public interface IPushletMsgDao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	PushletMsgPO retrieveInstanceById(String id) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(PushletMsgPO pushletMsgPO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(PushletMsgPO pushletMsgPO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String id) throws Exception;
	
	/**
	 * 通过用户id获取实例集合
	 */
	List<PushletMsgPO> retrieveInstancesByUserId(String userId) throws Exception;

}