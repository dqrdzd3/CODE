/**
 * 文件名：OperLogServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.platform.dao.IOperLogDao;
import com.hw.hwsafe.platform.pojo.OperLogPO;
import com.hw.hwsafe.platform.service.IOperLogService;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.HttpUtil;
import com.hw.hwsafe.utils.StringUtil;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 操作日志service实现类
 * 
 * @author 马宁
 * @创建时间　2013-03-20
 */
public class OperLogServiceImpl extends BaseServiceImpl implements IOperLogService{

	@Autowired
	private IOperLogDao operLogDao;
	
	@Override
	public void log(String className, String methodName, String content) {
		String maxCode = operLogDao.retrieveMaxCode();
		OperLogPO logPO = new OperLogPO(UUIDGenerater.getUUID(),
				getNextCode(maxCode), getOperatorId(), getOperatorName(),
				DateTimeUtils.getCurrentDate(), className, methodName, content, HttpUtil.getRemoteAddr());
		operLogDao.insertOperLog(logPO);
	}

	@Override
	public OperLogPO retrieveInstanceById(String id) {
		return operLogDao.retrieveInstanceById(id);
	}

	// ---------- private methods -------------
	
	/*
	 * 获取操作人ID
	 */
	private String getOperatorId(){
		try{
			return SessionUtil.getUserId();
		}catch (Exception e){
			return null;
		}
	}
	
	/*
	 * 获取操作人姓名
	 */
	private String getOperatorName(){
		try{
			return SessionUtil.getUserName();
		} catch (Exception e){
			return null;
		}
	}
	
	/*
	 * 获取下一个编号
	 */
	private String getNextCode(String code){
		String result = code == null
				? "1"
				: String.valueOf((Long.parseLong(code)+1));
		return StringUtil.fillPrefix(result, OperLogPO.CODE_FILL_PREFIX, OperLogPO.CODE_LENGTH);
	}
	
}
