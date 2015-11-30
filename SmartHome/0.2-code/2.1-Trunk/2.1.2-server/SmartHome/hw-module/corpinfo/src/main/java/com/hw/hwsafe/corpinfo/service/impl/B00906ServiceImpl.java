package com.hw.hwsafe.corpinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.action.B00906Action;
import com.hw.hwsafe.corpinfo.dao.IB00906Dao;
import com.hw.hwsafe.corpinfo.pojo.B00906PO;
import com.hw.hwsafe.corpinfo.service.IB00906Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.utils.UUIDGenerater;

public class B00906ServiceImpl extends BaseServiceImpl implements IB00906Service{
	
	@Autowired	
	private IB00906Dao b00906Dao;
	
	/**
	 * 	
	 * 函 数 名：	retrieveAllB00906()
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
	public List<B00906PO> retrieveAllB00906() throws Exception {
		return b00906Dao.retrieveAllB00906();
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB00906ByID
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
	public B00906PO retrieveB00906ByID(String b00906ID)
			throws Exception {
		return b00906Dao.retrieveB00906ByID(b00906ID);
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB00906ByPO
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
	public List<B00906PO> retrieveB00906ByPO(B00906PO b00906PO) throws Exception {
		return b00906Dao.retrieveB00906ByPO(b00906PO);
	}


	/**
	 * 	
	 * 函 数 名：	insertB00906
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
	public void insertB00906(B00906PO b00906PO,B00906Action b00906Action)
			throws Exception {
		b00906PO.setMA001(UUIDGenerater.getUUID());// 主键
		b00906Dao.insertB00906(b00906PO);
		b00906Action.getMessage().set(1, "已经成功添加！");
	}

	/**
	 * 	
	 * 函 数 名：	updateB00906
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
	public void updateB00906(B00906PO b00906PO,B00906Action b00906Action)
			throws Exception {
		B00906PO retrieveb00906PO = b00906Dao.retrieveB00906ByID(b00906PO.getMA001());
		if (retrieveb00906PO == null)
			b00906Action.getMessage().set(5, "该条记录不存在", "错误");
		b00906Dao.updateB00906(b00906PO);
		b00906Action.getMessage().setContent("修改成功");
	}
	
	public void deleteB00906ByID(String userID,B00906Action b00906Action) throws Exception {
		B00906PO retrieveb00906PO=b00906Dao.retrieveB00906ByID(userID);
		if(retrieveb00906PO==null)b00906Action.getMessage().setContent("该记录不存在");
			b00906Dao.deleteB00906ByID(userID);
	}
	
	public List<B00906PO> retrieveB00906ByParentID(String id) throws Exception{
		return b00906Dao.retrieveB00906ByParentID(id);
	}
}