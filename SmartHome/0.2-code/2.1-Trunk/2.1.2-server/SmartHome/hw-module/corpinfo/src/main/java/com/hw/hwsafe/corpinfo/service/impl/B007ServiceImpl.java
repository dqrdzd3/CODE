/**
 * 文件名：B007ServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.corpinfo.dao.IB007Dao;
import com.hw.hwsafe.corpinfo.pojo.B007PO;
import com.hw.hwsafe.corpinfo.service.IB007Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B007ServiceImpl
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-11 下午6:08:46
 * 修改人：
 * 修改时间：2012-6-11 下午6:08:46
 * 修改备注：
 * @version 
 * 
 */
public class B007ServiceImpl extends BaseServiceImpl implements IB007Service{

	@Autowired	
    private IB007Dao b007Dao;
   /**
    * 通过主键查询记录
    */
	public B007PO retrieveB007ByID(String b007ID)
			throws Exception {
		return b007Dao.retrieveB007ByID(b007ID);
	}
	/**
	 * 通过条件查询记录
	 */
	public List<B007PO> retrieveB007ByPO(B007PO b007PO) throws Exception {
		return b007Dao.retrieveB007ByPO(b007PO);
	}
	  /**
	   * 插入记录
	   */
	public void insertB007(B007PO b007PO)
			throws Exception {
		b007Dao.insertB007(b007PO);
	}
	/**
	 * 修改操作
	 */
	public void updateB007(B007PO b007PO) throws Exception {
		b007Dao.updateB007(b007PO);
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id) throws Exception {
		return b007Dao.retrieveB007ByID(id) == null ? false : true;
	}
}
