/**
 * 文件名：IAccidentCasesServiceImpl.java
 *
 * 版本信息：
 * 日期：2012-5-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.knowledge.dao.ITaccidentcaseDao;
import com.hw.hwsafe.knowledge.pojo.TaccidentcasePO;
import com.hw.hwsafe.knowledge.service.ITaccidentcaseService;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IAccidentCasesServiceImpl
 * 类描述：
 * 创建人：张成
 * 创建时间：2012-5-29 上午10:08:04
 * 修改人：张成
 * 修改时间：2012-5-29 上午10:08:04
 * 修改备注：
 * @version 
 * 
 */
public class TaccidentcaseServiceImpl extends BaseServiceImpl implements ITaccidentcaseService {
	@Autowired	
	private ITaccidentcaseDao iTaccidentcaseDao;
	/**
	 * 通过主键查询记录
	 */
	public TaccidentcasePO retrieveTaccidentcaseByID(String taccidentcaseID)
			throws Exception {
		return iTaccidentcaseDao.retrieveTaccidentcaseByID(taccidentcaseID);
	}
	/**
	 * 通过条件查询记录
	 */
	public List<TaccidentcasePO> retrieveTaccidentcaseByPO(
			TaccidentcasePO taccidentcasePO) throws Exception {
		return iTaccidentcaseDao.retrieveTaccidentcaseByPO(taccidentcasePO);
	}
	/**
	 * 添加记录
	 */
	public void insertTaccidentcase(TaccidentcasePO taccidentcasePO)
			throws Exception {
		iTaccidentcaseDao.insertTaccidentcase(taccidentcasePO);
	}
	/**
	 * 修改记录
	 */
	public void updateTaccidentcase(TaccidentcasePO taccidentcasePO)
			throws Exception {
		iTaccidentcaseDao.updateTaccidentcase(taccidentcasePO);
	}

	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id) throws Exception {
		return iTaccidentcaseDao.retrieveTaccidentcaseByID(id) == null ? false : true;
	}
}
