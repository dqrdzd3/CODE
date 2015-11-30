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

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.dao.IB003Dao;
import com.hw.hwsafe.corpinfo.pojo.B003PO;
import com.hw.hwsafe.corpinfo.service.IB003Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.platform.userinfo.SessionUtil;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B003ServiceImpl
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:08:30
 * 修改人：
 * 修改时间：2012-6-11 下午6:08:30
 * 修改备注：
 * @version 
 * 
 */
public class B003ServiceImpl extends BaseServiceImpl implements IB003Service {

	@Autowired	
    private IB003Dao b003Dao;
	private boolean flag=false;

	public B003PO retrieveB003ByID(String b003ID) throws Exception {
		return b003Dao.retrieveB003ByID(b003ID);
	}

	public List<B003PO> retrieveB003ByPO(B003PO b003PO) throws Exception {
		return b003Dao.retrieveB003ByPO(b003PO);
	}

	/**
	 * 添加人员信息
	 */
	public void insertB003(B003PO b003PO) throws Exception {

		b003Dao.insertB003(b003PO);

	}

	/**
	 * 修改人员信息
	 */
	public void updateB003(B003PO b003PO) throws Exception {
		b003Dao.updateB003(b003PO);
	}

	/**
	 * 检查人员编号是否重复
	 */
	public boolean checkCorpNum(B003PO b003PO) throws Exception {
		B003PO b003po = new B003PO();
		b003po.setMA002(b003PO.getMA002());
		b003po.setMA003(SessionUtil.getOrgId());
		if (b003PO.getMA001() != null) {
			b003po.setMA001(b003PO.getMA001());
		}
		int i = b003Dao.retrieveB001ByName(b003po);
		if (i > 0)
			flag = true;
		else
			flag = false;
		return flag;
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id) throws Exception {
		return b003Dao.retrieveB003ByID(id) == null ? false : true;
	}
	
	@Override
	public List<Map<String, Object>> retrieveByPage1(Map map) {
		try {
			return b003Dao.retrieveByPage1(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List getInfoByname(B003PO b003PO)throws Exception{
		
		return b003Dao.getInfoByname(b003PO);
	}
	
}
