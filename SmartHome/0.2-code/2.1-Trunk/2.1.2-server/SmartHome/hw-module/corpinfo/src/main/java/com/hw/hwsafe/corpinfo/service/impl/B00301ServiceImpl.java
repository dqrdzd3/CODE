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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.dao.IB00301Dao;
import com.hw.hwsafe.corpinfo.pojo.B00301PO;
import com.hw.hwsafe.corpinfo.service.IB00301Service;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe 
 * 类名称：B00301ServiceImpl 
 * 类描述： 创建人：wangyong  
 * 创建时间：2012-11-27 9:08:30 
 * 修改人：wangyong
 * 修改时间：2012-11-27 
 * 修改备注：
 * 
 * @version
 * 
 */
public class B00301ServiceImpl extends BaseServiceImpl implements
		IB00301Service {

	@Autowired
	private IB00301Dao b00301Dao;

	public List<B00301PO> retrieveAllB00301() throws Exception {
		return b00301Dao.retrieveAllB00301();
	}

	public void insertB00301(B00301PO b00301PO)
			throws Exception {
		b00301Dao.insertB00301(b00301PO);
	}

	public void updateB00301(B00301PO b00301PO)
			throws Exception {
		b00301Dao.updateB00301(b00301PO);
	}

	public B00301PO retrieveB00301ByID(String b00301ID) throws Exception {
		return b00301Dao.retrieveB00301ByID(b00301ID);
	}
	
	/**
	 * 删除人员培训信息
	 */
	public UserMessageData delb00301(String id)throws Exception{
		UserMessageData message= new UserMessageData();
		String[] idarr=id.split(",");
		HashMap<String, String[]> map=new HashMap<String, String[]>();
		map.put("ids", idarr);
		b00301Dao.delb00301(map);
		message.set(Constants.MSG_OK, Constants.DEL_SUCCESS_MSG);
		return message;
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id)throws Exception{
		return b00301Dao.retrieveB00301ByID(id) == null ? false : true;
	}
}
