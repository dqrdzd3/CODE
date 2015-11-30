/**
 * 文件名：ExceptionLogServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.dao.IExceptionLogDao;
import com.hw.hwsafe.platform.pojo.ExceptionLogPO;
import com.hw.hwsafe.platform.service.IExceptionLogService;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.HttpUtil;
import com.hw.hwsafe.utils.StringUtil;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 异常日志service实现类
 * 
 * @author 马宁
 * @创建时间 2013-03-20
 */
public class ExceptionLogServiceImpl extends BaseServiceImpl implements
		IExceptionLogService {

	private static final long serialVersionUID = 3830876474341381350L;
	
	@Autowired
	private IExceptionLogDao exceptionDao;

	@Override
	public String log(String msg, String content) {
		String maxCode = exceptionDao.retrieveMaxCode();
		ExceptionLogPO logPO = new ExceptionLogPO(UUIDGenerater.getUUID(),
				getNextCode(maxCode), getOperatorId(), getOperatorName(),
				DateTimeUtils.getCurrentDate(), msg, content, HttpUtil.getRemoteAddr());
		exceptionDao.insertExceptionLog(logPO);
		return logPO.getCode();
	}

	@Override
	public ExceptionLogPO retrieveInstanceById(String id) {
		return exceptionDao.retrieveInstanceById(id);
	}

	// ---------- private methods -------------

	/*
	 * 获取操作人ID
	 */
	private String getOperatorId() {
		try {
			return SessionUtil.getUserId();
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * 获取操作人姓名
	 */
	private String getOperatorName() {
		try {
			return SessionUtil.getUserName();
		} catch (Exception e) {
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
		return StringUtil.fillPrefix(result, ExceptionLogPO.CODE_FILL_PREFIX, ExceptionLogPO.CODE_LENGTH);
	}
	
}
