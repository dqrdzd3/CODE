/**
 * 文件名：B00401ServiceImpl.java
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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.dao.IB00401Dao;
import com.hw.hwsafe.corpinfo.pojo.B00401PO;
import com.hw.hwsafe.corpinfo.service.IB00401Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B004ServiceImpl
 * 类描述：
 * 创建人：wy
 * 创建时间：2012-6-11 下午6:08:46
 * 修改人：
 * 修改时间：2012-6-11 下午6:08:46
 * 修改备注：
 * @version 
 * 
 */
public class B00401ServiceImpl extends BaseServiceImpl implements IB00401Service{

	@Autowired	
    private IB00401Dao b00401Dao;

	@Override
	public List<B00401PO> retrieveAllB00401() throws Exception {
		return b00401Dao.retrieveAllB00401();
	}

	@Override
	public B00401PO retrieveB00401ByID(String b00401ID) throws Exception {
		return b00401Dao.retrieveB00401ByID(b00401ID);
	}

	@Override
	public List<B00401PO> retrieveB00401ByPO(B00401PO b00401PO)
			throws Exception {
		return b00401Dao.retrieveB00401ByPO(b00401PO);
	}

	@Override
	public void insertB00401(B00401PO b00401PO)
			throws Exception {
		b00401Dao.insertB00401(b00401PO);
	}

	@Override
	public void updateB00401(B00401PO b00401PO)
			throws Exception {
		b00401Dao.updateB00401(b00401PO);
	}

	@Override
	public List retrieveB00401ByPinyin(Map map) throws Exception {
		return b00401Dao.retrieveB00401ByPinyin(map);
	}

	@Override
	public List statDevide(String id) throws Exception {
		return b00401Dao.statDevide(id);
	}

	@Override
	public List<HashMap<String, Object>> getGroupDangerCategory(
			HashMap<String, Object> map) {
		try {
			return b00401Dao.getGroupDangerCategory(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<HashMap<String, Object>> getGroupDangerLevel(
			HashMap<String, Object> map) {
		try {
			return b00401Dao.getGroupDangerLevel(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Map<String, Object>> retrieveByPage1(Map map) {
		try {
			return b00401Dao.retrieveByPage1(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> retrieveByPage2(Map map) {
		try {
			return b00401Dao.retrieveByPage2(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Map<String, Object>> retrieveByPage3(Map map) {
		try {
			return b00401Dao.retrieveByPage3(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id)throws Exception{
		return b00401Dao.retrieveB00401ByID(id) == null ? false : true;
	}
}
