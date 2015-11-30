/**
 * 文件名：RemindService.java
 *
 * 版本信息：
 * 日期：2013-4-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2013 
 * 版权所有
 *
 */
package com.hw.hwsafe.remind.outer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.remind.dao.IRemindDao;
import com.hw.hwsafe.remind.outer.IRemindService;
import com.hw.hwsafe.remind.po.RemindPO;

/**
 * 
 * 项目名称：hw-remind
 * 类名称：RemindService
 * 类描述：
 * 创建人：陈浙东
 * 创建时间：2013-4-11 下午3:36:45
 * 修改人：陈浙东
 * 修改时间：2013-4-11 下午3:36:45
 * 修改备注：
 * @version 
 * 
 */
public class RemindServiceImpl implements IRemindService {
	@Autowired
	private IRemindDao remindDao;
	
	
	/* (non-Javadoc)
	 * @see com.hw.hwsafe.remind.outer.IRemindService#insertRemind(com.hw.hwsafe.remind.po.RemindPO)
	 */
	@Override
	public int insertRemind(RemindPO remindPO) {
		int result = 1;
		try{
			remindDao.insertRemind(remindPO);
			result = 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.remind.outer.IRemindService#getRemindTotal(java.lang.String)
	 */
	@Override
	public int getRemindTotal(String receive) {
		int result = 0;
		try{
			result = remindDao.getRemindTotal(receive);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.remind.outer.IRemindService#deleteRemind(java.lang.String)
	 */
	@Override
	public int deleteRemind(String remindId) {
		int result = 1;
		try{
			remindDao.deleteRemind(remindId);
			result = 0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.remind.outer.IRemindService#getRemindList(java.lang.String)
	 */
	@Override
	public List<RemindPO> getRemindList(String receive) {
		List<RemindPO>  result = null;
		try{
			result = remindDao.getRemindList(receive);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.remind.outer.IRemindService#getRemindInfo(java.lang.String)
	 */
	@Override
	public RemindPO getRemindInfo(String remindId) {
		RemindPO result = null;
		try{
			result = remindDao.getRemindInfo(remindId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
