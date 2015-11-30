package com.hw.hwsafe.corpinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.action.B00905Action;
import com.hw.hwsafe.corpinfo.dao.IB00905Dao;
import com.hw.hwsafe.corpinfo.pojo.B00905PO;
import com.hw.hwsafe.corpinfo.service.IB00905Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.utils.UUIDGenerater;

public class B00905ServiceImpl extends BaseServiceImpl implements IB00905Service{
	
	@Autowired	
	private IB00905Dao b00905Dao;
	
	/**
	 * 	
	 * 函 数 名：	retrieveAllB00905()
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
	public List<B00905PO> retrieveAllB00905() throws Exception {
		return b00905Dao.retrieveAllB00905();
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB00905ByID
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
	public B00905PO retrieveB00905ByID(String b00905ID)
			throws Exception {
		return b00905Dao.retrieveB00905ByID(b00905ID);
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB00905ByPO
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
	public List<B00905PO> retrieveB00905ByPO(B00905PO b00905PO) throws Exception {
		return b00905Dao.retrieveB00905ByPO(b00905PO);
	}


	/**
	 * 	
	 * 函 数 名：	insertB00905
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
	public void insertB00905(B00905PO b00905PO,B00905Action b00905Action)
			throws Exception {
		b00905PO.setMA001(UUIDGenerater.getUUID());// 主键
		b00905Dao.insertB00905(b00905PO);
		b00905Action.getMessage().set(1, "已经成功添加！");
	}

	/**
	 * 	
	 * 函 数 名：	updateB00905
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
	public void updateB00905(B00905PO b00905PO,B00905Action b00905Action)
			throws Exception {
		B00905PO retrieveb00905PO = b00905Dao.retrieveB00905ByID(b00905PO.getMA001());
		if (retrieveb00905PO == null)
			b00905Action.getMessage().set(5, "该条记录不存在", "错误");
		b00905Dao.updateB00905(b00905PO);
		b00905Action.getMessage().setContent("修改成功");
	}

	public void deleteB00905ByID(String userID,B00905Action b00905Action) throws Exception {
		B00905PO retrieveb00905PO=b00905Dao.retrieveB00905ByID(userID);
		if(retrieveb00905PO==null)b00905Action.getMessage().setContent("该记录不存在");
			b00905Dao.deleteB00905ByID(userID);
	}
	
	public List<B00905PO> retrieveB00905ByParentID(String id) throws Exception{
		return b00905Dao.retrieveB00905ByParentID(id);
	}
}
