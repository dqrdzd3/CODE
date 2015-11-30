/**
 * 文件名：K006ServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-6-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.knowledge.dao.IK006Dao;
import com.hw.hwsafe.knowledge.pojo.K006PO;
import com.hw.hwsafe.knowledge.service.IK006Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：K006ServiceImpl
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-8 下午3:15:40
 * 修改人：
 * 修改时间：2012-6-8 下午3:15:40
 * 修改备注：
 * @version 
 * 
 */
public class K006ServiceImpl extends BaseServiceImpl implements IK006Service{
	
	@Autowired	
	private IK006Dao iK006Dao;
	
	public List<K006PO> retrieveAllK006() throws Exception {
		return iK006Dao.retrieveAllK006();
	}

	public K006PO retrieveK006ByID(String k006ID) throws Exception {
		return iK006Dao.retrieveK006ByID(k006ID);
	}

	public List<K006PO> retrieveK006ByPO(K006PO k006PO) throws Exception {
		return iK006Dao.retrieveK006ByPO(k006PO);
	}
	/**
	 * 添加记录
	 */
	public void insertK006(K006PO k006PO) throws Exception {
		iK006Dao.insertK006(k006PO);
	}

	public void updateK006(K006PO k006PO) throws Exception {
		iK006Dao.updateK006(k006PO);
	}


	public boolean isMA002UniqueWhenAdd(String ma002) throws Exception {
		return iK006Dao.countByMA002(ma002) == 0;
	}

	public boolean isMA002UniqueWhenUpdate(K006PO po) throws Exception{
		return iK006Dao.countByMA001AndMA002(po) == 0;
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id) throws Exception {
		return iK006Dao.retrieveK006ByID(id) == null ? false : true;
	}
}
