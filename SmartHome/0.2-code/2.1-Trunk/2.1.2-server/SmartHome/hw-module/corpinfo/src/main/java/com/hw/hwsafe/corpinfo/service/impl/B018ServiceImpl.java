package com.hw.hwsafe.corpinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.dao.IB018Dao;
import com.hw.hwsafe.corpinfo.pojo.B018PO;
import com.hw.hwsafe.corpinfo.service.IB018Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

public class B018ServiceImpl extends BaseServiceImpl implements IB018Service{
	
	@Autowired	
	private IB018Dao b018Dao;
	
	/**
	 * 	
	 * 函 数 名：	retrieveAllB018()
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
	public List<B018PO> retrieveAllB018() throws Exception {
		return b018Dao.retrieveAllB018();
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB018ByID
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
	public B018PO retrieveB018ByID(String b018ID)
			throws Exception {
		return b018Dao.retrieveB018ByID(b018ID);
	}

	/**
	 * 	
	 * 函 数 名：	retrieveB018ByPO
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
	public List<B018PO> retrieveB018ByPO(B018PO b018PO) throws Exception {
		return b018Dao.retrieveB018ByPO(b018PO);
	}

	/**
	 * 添加记录
	 */
	public void insertB018(B018PO b018PO)
			throws Exception {
		b018Dao.insertB018(b018PO);
	}

	/**
	 * 修改记录
	 */
	public void updateB018(B018PO b018PO)throws Exception 
	   {
		b018Dao.updateB018(b018PO);
   	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id) throws Exception {
		return b018Dao.retrieveB018ByID(id) == null ? false : true;
	}
}
