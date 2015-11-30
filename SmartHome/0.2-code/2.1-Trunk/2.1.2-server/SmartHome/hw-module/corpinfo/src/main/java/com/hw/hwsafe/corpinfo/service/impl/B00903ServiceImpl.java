package com.hw.hwsafe.corpinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.action.B00903Action;
import com.hw.hwsafe.corpinfo.dao.IB00903Dao;
import com.hw.hwsafe.corpinfo.pojo.B00903PO;
import com.hw.hwsafe.corpinfo.service.IB00903Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.utils.UUIDGenerater;

public class B00903ServiceImpl extends BaseServiceImpl implements IB00903Service{
	
	@Autowired	
	private IB00903Dao b00903Dao;
	
	/**
	 * 	
	 * 函 数 名：	retrieveAllB00903()
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
	public List<B00903PO> retrieveAllB00903() throws Exception {
		return b00903Dao.retrieveAllB00903();
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB00903ByID
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
	public B00903PO retrieveB00903ByID(String b00903ID)
			throws Exception {
		return b00903Dao.retrieveB00903ByID(b00903ID);
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB00903ByPO
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
	public List<B00903PO> retrieveB00903ByPO(B00903PO b00903PO) throws Exception {
		return b00903Dao.retrieveB00903ByPO(b00903PO);
	}


	/**
	 * 	
	 * 函 数 名：	insertB00903
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
	public void insertB00903(B00903PO b00903PO,B00903Action b00903Action)
			throws Exception {
		b00903PO.setMA001(UUIDGenerater.getUUID());// 主键
		b00903Dao.insertB00903(b00903PO);
		b00903Action.getMessage().set(1, "已经成功添加！");
	}

	/**
	 * 	
	 * 函 数 名：	updateB00903
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
	public void updateB00903(B00903PO b00903PO,B00903Action b00903Action)
			throws Exception {
		B00903PO retrieveb00903PO = b00903Dao.retrieveB00903ByID(b00903PO.getMA001());
		if (retrieveb00903PO == null)
			b00903Action.getMessage().set(5, "该条记录不存在", "错误");
		b00903Dao.updateB00903(b00903PO);
		b00903Action.getMessage().setContent("修改成功");
	}

	public void deleteB00903ByID(String userID,B00903Action b00903Action) throws Exception {
		B00903PO retrieveb00903PO=b00903Dao.retrieveB00903ByID(userID);
		if(retrieveb00903PO==null)b00903Action.getMessage().setContent("该记录不存在");
			b00903Dao.deleteB00903ByID(userID);
	}
	
	public List<B00903PO> retrieveB00903ByParentID(String id) throws Exception{
		return b00903Dao.retrieveB00903ByParentID(id);
	}
	
}
