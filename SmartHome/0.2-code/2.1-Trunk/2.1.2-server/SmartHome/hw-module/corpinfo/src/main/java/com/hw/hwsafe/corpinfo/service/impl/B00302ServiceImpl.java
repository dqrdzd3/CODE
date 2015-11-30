/**
 * 文件名：B003ServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.dao.IB00302Dao;
import com.hw.hwsafe.corpinfo.pojo.B00302PO;
import com.hw.hwsafe.corpinfo.service.IB00302Service;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe 类名称：B003ServiceImpl 类描述： 创建人：刁海港 创建时间：2012-6-11 下午6:08:30 修改人：
 * 修改时间：2012-6-11 下午6:08:30 修改备注：
 * 
 * @version
 * 
 */
public class B00302ServiceImpl extends BaseServiceImpl implements
		IB00302Service {

	@Autowired
	private IB00302Dao b00302Dao;

	/**
	 * 添加人员证书信息
	 */
	public void insertB00302(B00302PO b00302PO)
			throws Exception {
		b00302Dao.insertB00302(b00302PO);
	}

	public void updateB00302(B00302PO b00302PO)
			throws Exception {
		b00302Dao.updateB00302(b00302PO);
	}

	public B00302PO retrieveB00302ByID(String b00302id) throws Exception {
		return b00302Dao.retrieveB00302ByID(b00302id);
	}

	/**
	 * 删除人员证书信息
	 */
	public UserMessageData delb00302(String id)throws Exception{
		UserMessageData message= new UserMessageData();
		String[] idarr=id.split(",");
		HashMap<String, String[]> map=new HashMap<String, String[]>();
		map.put("ids", idarr);
		b00302Dao.delb00302(map);
		message.set(Constants.MSG_OK, Constants.DEL_SUCCESS_MSG);
		return message;
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id)throws Exception{
		return b00302Dao.retrieveB00302ByID(id) == null ? false : true;
	}
}
