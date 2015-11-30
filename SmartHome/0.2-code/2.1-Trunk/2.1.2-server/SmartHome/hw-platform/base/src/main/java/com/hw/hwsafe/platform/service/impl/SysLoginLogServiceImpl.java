/**
 * 文件名：SysLoginLogServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.platform.dao.ISysLoginLogDao;
import com.hw.hwsafe.platform.pojo.SysLoginLogPO;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.service.ISysLoginLogService;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 登录日志service实现类
 * 
 * @author 马宁
 * @创建时间　2013-06-06
 */
public class SysLoginLogServiceImpl extends BaseServiceImpl implements ISysLoginLogService{

	@Autowired
	private ISysLoginLogDao sysLoginLogDao;

	/*
	 * (non-Javadoc)
	 * @see com.hw.hwsafe.platform.service.ISysLoginLogService#log(java.lang.Integer, java.lang.String, com.hw.hwsafe.platform.pojo.UserPO)
	 */
	@Override
	public void log(Integer type, String ip, UserPO userPo) throws Exception{
		SysLoginLogPO sysLoginLogPO = new SysLoginLogPO(
				UUIDGenerater.getUUID(), type, DateTimeUtils.getCurrentDate(), 
				userPo.getUUID(), userPo.getLOGIN_NAME(), ip, 
				getGovId(userPo), getCorpId(userPo));
		sysLoginLogDao.insertInstance(sysLoginLogPO);
	}

	
	// ------------------- private methods ---------------------
	
	/**
	 * 获取政府标识
	 * 
	 * @author 马宁
	 * @create_time 2013-6-6 下午4:20:10
	 */
	private String getGovId(UserPO userPo) {
		String result = null;
		if (userPo.isGovType()) {
			result = userPo.getORGAN_UUID();
		} else if (userPo.isCorpType()) {
			result = sysLoginLogDao.retrieveGovIdByCorpId(userPo.getORGAN_UUID());
		}
		return result;
	}
	
	/**
	 * 获取企业标识
	 * 
	 * @author 马宁
	 * @create_time 2013-6-6 下午4:20:23
	 */
	private String getCorpId(UserPO userPo){
		String result = null;
		if(userPo.isCorpType()){
			result = userPo.getORGAN_UUID();
		}
		return result;
	}
}
