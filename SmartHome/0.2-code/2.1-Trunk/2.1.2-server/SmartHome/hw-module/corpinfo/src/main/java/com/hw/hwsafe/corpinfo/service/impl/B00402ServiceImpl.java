/**
 * 文件名：B00402ServiceImpl.java
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

import com.hw.hwsafe.corpinfo.dao.IB00402Dao;
import com.hw.hwsafe.corpinfo.pojo.B00402PO;
import com.hw.hwsafe.corpinfo.service.IB00402Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B004ServiceImpl
 * 类描述：设备设施从表信息
 * 创建人：dhg
 */
public class B00402ServiceImpl extends BaseServiceImpl implements IB00402Service{

	@Autowired	
    private IB00402Dao b00402Dao;
	
	public B00402PO retrieveB00402ByID(String b00402ID) throws Exception {
		return b00402Dao.retrieveInstanceById(b00402ID);
	}
	
	public List<B00402PO> retrieveB00402ByPO(B00402PO b00402PO)
			throws Exception {
		return b00402Dao.retrieveInstanceByPO(b00402PO);
	}

	public void insertB00402(B00402PO b00402PO)
			throws Exception {
		b00402Dao.insertInstance(b00402PO);
	}

	@Override
	public void updateB00402(B00402PO b00402PO)
			throws Exception {
		b00402Dao.updateInstance(b00402PO);
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id)throws Exception{
		return b00402Dao.retrieveInstanceById(id) == null ? false : true;
	}
}
