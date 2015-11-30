package com.hw.hwsafe.corpinfo.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.action.B00902Action;
import com.hw.hwsafe.corpinfo.dao.IB00902Dao;
import com.hw.hwsafe.corpinfo.pojo.B00902PO;
import com.hw.hwsafe.corpinfo.service.IB00902Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.utils.UUIDGenerater;

public class B00902ServiceImpl extends BaseServiceImpl implements IB00902Service{
	
	@Autowired	
	private IB00902Dao b00902Dao;
	
	/**
	 * 	
	 * 函 数 名：	retrieveAllB00902()
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
	public List<B00902PO> retrieveAllB00902() throws Exception {
		return b00902Dao.retrieveAllB00902();
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB00902ByID
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
	public B00902PO retrieveB00902ByID(String b00902ID)
			throws Exception {
		return b00902Dao.retrieveB00902ByID(b00902ID);
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB00902ByPO
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
	public List<B00902PO> retrieveB00902ByPO(B00902PO b00902PO) throws Exception {
		return b00902Dao.retrieveB00902ByPO(b00902PO);
	}


	/**
	 * 	
	 * 函 数 名：	insertB00902
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
	public void insertB00902(B00902PO b00902PO,B00902Action b00902Action)
			throws Exception {
		b00902PO.setMA001(UUIDGenerater.getUUID());// 主键
		
		b00902Dao.insertB00902(b00902PO);
		b00902Action.getMessage().set(1, "已经成功添加！");
	}

	/**
	 * 	
	 * 函 数 名：	updateB00902
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
	public void updateB00902(B00902PO b00902PO,B00902Action b00902Action)
			throws Exception {
		B00902PO retrieveb00902PO = b00902Dao.retrieveB00902ByID(b00902PO.getMA001());
		if (retrieveb00902PO == null)
			b00902Action.getMessage().set(5, "该条记录不存在", "错误");
		b00902Dao.updateB00902(b00902PO);
		b00902Action.getMessage().setContent("修改成功");
	}

	public void deleteB00902ByID(String userID,B00902Action b00902Action) throws Exception {
		B00902PO retrieveb00902PO=b00902Dao.retrieveB00902ByID(userID);
		if(retrieveb00902PO==null)b00902Action.getMessage().setContent("该记录不存在");
			b00902Dao.deleteB00902ByID(userID);
	}
	
	public List<B00902PO> retrieveB00902ByParentID(String id) throws Exception{
		return b00902Dao.retrieveB00902ByParentID(id);
	}
	
}
