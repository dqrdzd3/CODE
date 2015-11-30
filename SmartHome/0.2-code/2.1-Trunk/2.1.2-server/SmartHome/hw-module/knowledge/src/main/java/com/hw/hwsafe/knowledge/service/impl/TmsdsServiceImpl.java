package com.hw.hwsafe.knowledge.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.knowledge.dao.ITmsdsDao;
import com.hw.hwsafe.knowledge.pojo.TmsdsPO;
import com.hw.hwsafe.knowledge.service.ITmsdsService;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：TmsdsServiceImpl
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-6 上午9:09:04
 * 修改人：李玉梅
 * 修改时间：2012-6-6 上午9:09:04
 * 修改备注：
 * @version 
 *
 */

public class TmsdsServiceImpl extends BaseServiceImpl implements ITmsdsService{
	
	@Autowired	
	private ITmsdsDao iTmsdsDao;
	/**
	 * 通过主键查询记录
	 */
	public TmsdsPO retrieveTmsdsByID(String tmsdsID) throws Exception {
		return iTmsdsDao.retrieveTmsdsByID(tmsdsID);
	}
	/**
	 * 添加记录
	 */
	public void insertTmsds(TmsdsPO tmsdsPO) throws Exception {
		iTmsdsDao.insertTmsds(tmsdsPO);
	}
	/**
	 * 修改记录
	 */
	public void updateTmsds(TmsdsPO tmsdsPO) throws Exception {
		iTmsdsDao.updateTmsds(tmsdsPO);
	}

	/**
	 * 添加时查看名称是否已存在
	 */
	public boolean isNameUniqueWhenAdd(String name) throws Exception {
		return iTmsdsDao.countByName(name) == 0;
	}
	/**
	 * 修改时查看名称是否已存在
	 */
	public boolean isNameUniqueWhenUpdate(TmsdsPO po) throws Exception {
		return iTmsdsDao.countByIdAndName(po) == 0;
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id) throws Exception {
		return iTmsdsDao.retrieveTmsdsByID(id) == null ? false : true;
	}
	/**
	 * 根据危化品名称进行查询危化品记录
	 */
	@Override
	public TmsdsPO retrieveTmsdsByChname(String chName) throws Exception {
		// TODO Auto-generated method stub
		return iTmsdsDao.retrieveTmsdsByChname(chName);
	}
}
