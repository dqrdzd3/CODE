package com.hw.hwsafe.corpinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.action.B00901Action;
import com.hw.hwsafe.corpinfo.dao.IB00901Dao;
import com.hw.hwsafe.corpinfo.pojo.B00901PO;
import com.hw.hwsafe.corpinfo.service.IB00901Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.utils.UUIDGenerater;

public class B00901ServiceImpl extends BaseServiceImpl implements IB00901Service{
	
	@Autowired	
	private IB00901Dao b00901Dao;
	
	/**
	 * 	
	 * 函 数 名：	retrieveAllB00901()
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
	public List<B00901PO> retrieveAllB00901() throws Exception {
		return b00901Dao.retrieveAllB00901();
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB00901ByID
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
	public B00901PO retrieveB00901ByID(String b00901ID)
			throws Exception {
		return b00901Dao.retrieveB00901ByID(b00901ID);
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB00901ByPO
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
	public List<B00901PO> retrieveB00901ByPO(B00901PO b00901PO) throws Exception {
		return b00901Dao.retrieveB00901ByPO(b00901PO);
	}


	/**
	 * 	
	 * 函 数 名：	insertB00901
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
	public void insertB00901(B00901PO b00901PO,B00901Action b00901Action)
			throws Exception {
		b00901PO.setMA001(UUIDGenerater.getUUID());// 主键
		b00901Dao.insertB00901(b00901PO);
		b00901Action.getMessage().set(1, "已经成功添加！");
	}

	/**
	 * 	
	 * 函 数 名：	updateB00901
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
	public void updateB00901(B00901PO b00901PO,B00901Action b00901Action)
			throws Exception {
		B00901PO retrieveb00901PO = b00901Dao.retrieveB00901ByID(b00901PO.getMA001());
		if (retrieveb00901PO == null)
			b00901Action.getMessage().set(5, "该条记录不存在", "错误");
		b00901Dao.updateB00901(b00901PO);
		b00901Action.getMessage().setContent("修改成功");
	}

	public void deleteB00901ByID(String userID,B00901Action b00901Action) throws Exception {
		B00901PO retrieveb00901PO=b00901Dao.retrieveB00901ByID(userID);
		if(retrieveb00901PO==null)b00901Action.getMessage().setContent("该记录不存在");
			b00901Dao.deleteB00901ByID(userID);
	}
	
}
