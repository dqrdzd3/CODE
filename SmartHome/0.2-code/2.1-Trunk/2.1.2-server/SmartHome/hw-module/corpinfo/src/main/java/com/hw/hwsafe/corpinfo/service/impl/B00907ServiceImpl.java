package com.hw.hwsafe.corpinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.action.B00907Action;
import com.hw.hwsafe.corpinfo.dao.IB00907Dao;
import com.hw.hwsafe.corpinfo.pojo.B00907PO;
import com.hw.hwsafe.corpinfo.service.IB00907Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.utils.UUIDGenerater;

public class B00907ServiceImpl extends BaseServiceImpl implements IB00907Service{
	
	@Autowired	
	private IB00907Dao b00907Dao;
	
	/**
	 * 	
	 * 函 数 名：	retrieveAllB00907()
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
	public List<B00907PO> retrieveAllB00907() throws Exception {
		return b00907Dao.retrieveAllB00907();
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB00907ByID
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
	public B00907PO retrieveB00907ByID(String b00907ID)
			throws Exception {
		return b00907Dao.retrieveB00907ByID(b00907ID);
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB00907ByPO
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
	public List<B00907PO> retrieveB00907ByPO(B00907PO b00907PO) throws Exception {
		return b00907Dao.retrieveB00907ByPO(b00907PO);
	}

	/**
	 * 	
	 * 函 数 名：	insertB00907
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
	public void insertB00907(B00907PO b00907PO,B00907Action b00907Action)
			throws Exception {
		b00907PO.setMA001(UUIDGenerater.getUUID());// 主键
		b00907Dao.insertB00907(b00907PO);
		b00907Action.getMessage().set(1, "已经成功添加！");
	}

	/**
	 * 	
	 * 函 数 名：	updateB00907
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
	public void updateB00907(B00907PO b00907PO,B00907Action b00907Action)
			throws Exception {
		B00907PO retrieveb00907PO = b00907Dao.retrieveB00907ByID(b00907PO.getMA001());
		if (retrieveb00907PO == null)
			b00907Action.getMessage().set(5, "该条记录不存在", "错误");
		b00907Dao.updateB00907(b00907PO);
		b00907Action.getMessage().setContent("修改成功");
	}

	public void deleteB00907ByID(String userID,B00907Action b00907Action) throws Exception {
		B00907PO retrieveb00907PO=b00907Dao.retrieveB00907ByID(userID);
		if(retrieveb00907PO==null)b00907Action.getMessage().setContent("该记录不存在");
			b00907Dao.deleteB00907ByID(userID);
	}
	
	public List<B00907PO> retrieveB00907ByParentID(String id) throws Exception{
		return b00907Dao.retrieveB00907ByParentID(id);
	}
	
}
