/**
 * 文件名：B012ServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-7-2
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.corpinfo.dao.IB012Dao;
import com.hw.hwsafe.corpinfo.pojo.B012PO;
import com.hw.hwsafe.corpinfo.service.IB012Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B012ServiceImpl
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-7-2 上午11:04:15
 * 修改人：
 * 修改时间：2012-7-2 上午11:04:15
 * 修改备注：
 * @version 
 * 
 */
public class B012ServiceImpl extends BaseServiceImpl implements IB012Service{
	
	@Autowired	
	private IB012Dao b012Dao;
	/**
	 * 根据主键查询记录
	 */
	public B012PO retrieveB012ByID(String b012ID)
			throws Exception {
		return b012Dao.retrieveB012ByID(b012ID);
	}
	/**
	 * 根据条件查询记录
	 */
	public List<B012PO> retrieveB012ByPO(B012PO b012PO) throws Exception {
		return b012Dao.retrieveB012ByPO(b012PO);
	}
	/**
	 * 添加记录
	 */
	public void insertB012(B012PO b012PO)
			throws Exception {
		b012Dao.insertB012(b012PO);
	}
	/**
	 * 修改记录
	 */
	public void updateB012(B012PO b012PO)
			throws Exception {
			b012Dao.updateB012(b012PO);
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id) throws Exception {
		return b012Dao.retrieveB012ByID(id) == null ? false : true;
	}
}

