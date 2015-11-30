/**
 * 文件名：B008ServiceImpl.java
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
import com.hw.hwsafe.corpinfo.dao.IB008Dao;
import com.hw.hwsafe.corpinfo.pojo.B008PO;
import com.hw.hwsafe.corpinfo.service.IB008Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B008ServiceImpl
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-11 下午6:08:46
 * 修改人：
 * 修改时间：2012-6-11 下午6:08:46
 * 修改备注：
 * @version 
 * 
 */
public class B008ServiceImpl extends BaseServiceImpl implements IB008Service{

	@Autowired	
    private IB008Dao b008Dao;
	/**
	 * 通过主键查询记录
	 */
	public B008PO retrieveB008ByID(String b008ID)
			throws Exception {
		return b008Dao.retrieveB008ByID(b008ID);
	}
    /**
     * 根据条件查询记录
     */
	public List<B008PO> retrieveB008ByPO(B008PO b008PO) throws Exception {
		return b008Dao.retrieveB008ByPO(b008PO);
	}
    /**
     * 添加记录
     */
	public void insertB008(B008PO b008PO)
			throws Exception {
		
		b008Dao.insertB008(b008PO);
		
	}
	/**
	 * 修改记录
	 */
	public void updateB008(B008PO b008PO)
			throws Exception {
			b008Dao.updateB008(b008PO);
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id) throws Exception {
		return b008Dao.retrieveB008ByID(id) == null ? false : true;
	}
}
