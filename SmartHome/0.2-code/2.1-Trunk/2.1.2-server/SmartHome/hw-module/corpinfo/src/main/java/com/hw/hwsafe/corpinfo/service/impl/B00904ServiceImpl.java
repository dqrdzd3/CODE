package com.hw.hwsafe.corpinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.action.B00904Action;
import com.hw.hwsafe.corpinfo.dao.IB00904Dao;
import com.hw.hwsafe.corpinfo.pojo.B00904PO;
import com.hw.hwsafe.corpinfo.service.IB00904Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.utils.UUIDGenerater;

public class B00904ServiceImpl extends BaseServiceImpl implements IB00904Service{
	
	@Autowired	
	private IB00904Dao b00904Dao;
	
	/**
	 * 	
	 * 函 数 名：	retrieveAllB00904()
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
	public List<B00904PO> retrieveAllB00904() throws Exception {
		return b00904Dao.retrieveAllB00904();
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB00904ByID
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
	public B00904PO retrieveB00904ByID(String b00904ID)
			throws Exception {
		return b00904Dao.retrieveB00904ByID(b00904ID);
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB00904ByPO
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
	public List<B00904PO> retrieveB00904ByPO(B00904PO b00904PO) throws Exception {
		return b00904Dao.retrieveB00904ByPO(b00904PO);
	}


	/**
	 * 	
	 * 函 数 名：	insertB00904
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
	public void insertB00904(B00904PO b00904PO,B00904Action b00904Action)
			throws Exception {
		b00904PO.setMA001(UUIDGenerater.getUUID());// 主键
		b00904Dao.insertB00904(b00904PO);
		b00904Action.getMessage().set(1, "已经成功添加！");
	}

	/**
	 * 	
	 * 函 数 名：	updateB00904
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
	public void updateB00904(B00904PO b00904PO,B00904Action b00904Action)
			throws Exception {
		B00904PO retrieveb00904PO = b00904Dao.retrieveB00904ByID(b00904PO.getMA001());
		if (retrieveb00904PO == null)
			b00904Action.getMessage().set(5, "该条记录不存在", "错误");
		b00904Dao.updateB00904(b00904PO);
		b00904Action.getMessage().setContent("修改成功");
	}

	public void deleteB00904ByID(String userID,B00904Action b00904Action) throws Exception {
		B00904PO retrieveb00904PO=b00904Dao.retrieveB00904ByID(userID);
		if(retrieveb00904PO==null)b00904Action.getMessage().setContent("该记录不存在");
			b00904Dao.deleteB00904ByID(userID);
	}
	
	public List<B00904PO> retrieveB00904ByParentID(String id) throws Exception{
		return b00904Dao.retrieveB00904ByParentID(id);
	}
}
