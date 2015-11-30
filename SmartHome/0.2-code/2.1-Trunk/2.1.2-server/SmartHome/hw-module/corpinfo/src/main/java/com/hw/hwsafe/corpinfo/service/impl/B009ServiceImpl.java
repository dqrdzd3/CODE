package com.hw.hwsafe.corpinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.dao.IB009Dao;
import com.hw.hwsafe.corpinfo.pojo.B009PO;
import com.hw.hwsafe.corpinfo.service.IB009Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

public class B009ServiceImpl extends BaseServiceImpl implements IB009Service{
	
	@Autowired	
	private IB009Dao b009Dao;

	/**
	 * 	
	 * 函 数 名：	retrieveB009ByID
	 * 功能描述：查询	
	 * 适用条件：	
	 * 执行流程：	
	 * 使用方法：	
	 * 注意事项	
	 * 输入参数：
	 * 创建人：刁海港	
	 * 创建时间：2012-7-23 下午4:04:14	
	 * 修改人：	
	 * 修改时间：
	 * 修改原因描述：
	 */
	public B009PO retrieveB009ByID(String b009ID)
			throws Exception {
		return b009Dao.retrieveB009ByID(b009ID);
	}

	/**
	 * 查询危险源的周边环境
	 */
	public B009PO retrieveB009BywxyID(String wxyID)
			throws Exception {
		return b009Dao.retrieveB009BywxyID(wxyID);
	}
	/**
	 * 	
	 * 函 数 名：	retrieveB009ByPO
	 * 功能描述：条件查询	 建议查询条件拼接 
	 * 适用条件：	
	 * 执行流程：	
	 * 使用方法：	
	 * 注意事项	
	 * 输入参数：
	 * 创建人：刁海港	
	 * 创建时间：2012-7-23 下午4:04:14	
	 * 修改人：	
	 * 修改时间：
	 * 修改原因描述：
	 */
	public List<B009PO> retrieveB009ByPO(B009PO b009PO) throws Exception {
		return b009Dao.retrieveB009ByPO(b009PO);
	}


	/**
	 * 	
	 * 函 数 名：	insertB009
	 * 功能描述：新增企业
	 * 适用条件：新增企业	
	 * 执行流程：1.判断企业名称是否重复 ，是；返回；否，新增；
	 * 使用方法：	
	 * 注意事项：企业名称不允许重复；
	 * 输入参数：
	 * 创建人：刁海港	
	 * 创建时间：2012-7-20 下午1:10:14	
	 * 修改人：	lm
	 * 修改时间：2012-7-23 下午4:04:14	
	 * 修改原因描述：新增需验证企业名称是否重复。
	 */
	public void insertB009(B009PO b009PO) throws Exception {
		b009Dao.insertB009(b009PO);
	}
	/**
	 * 	
	 * 函 数 名：	updateB009
	 * 功能描述：新增企业
	 * 适用条件：新增企业	
	 * 执行流程：1.判断企业名称是否重复 ，是；返回；否，新增；
	 * 使用方法：	
	 * 注意事项：企业名称不允许重复；
	 * 输入参数：
	 * 创建人：刁海港	
	 * 创建时间：2012-7-20 下午1:10:14	
	 * 修改人：	lm
	 * 修改时间：2012-7-23 下午4:04:14	
	 * 修改原因描述：新增需验证企业名称是否重复。
	 */
	public void updateB009(B009PO b009PO)
			throws Exception {
		b009Dao.updateB009(b009PO);
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id) throws Exception {
		return b009Dao.retrieveB009ByID(id) == null ? false : true;
	}
}