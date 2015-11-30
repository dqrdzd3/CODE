/**
 * 文件名：ExceptionLogAction.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hw.hwsafe.platform.pojo.ExceptionLogPO;
import com.hw.hwsafe.platform.service.IExceptionLogService;

/**
 * 异常日志Action类
 * 
 * @author 马宁
 * @创建时间 2013-03-28 15:20
 */
public class ExceptionLogAction extends BaseAction {

	/**
	 * service
	 */
	@Autowired
	private IExceptionLogService exceptionLogService;

	/**
	 * po
	 */
	private ExceptionLogPO exceptionLogPO;

	/**
	 * 日志id 由页面传入
	 */
	private String logId;

	// ----------------- methods ------------------

	/**
	 * 查看操作
	 */
	public String doView() {
		exceptionLogPO = exceptionLogService.retrieveInstanceById(logId);

		if (exceptionLogPO == null) {
			message.set(Constants.MSG_ERROR, "信息已不存在，请刷新后重试!",
					Constants.SERVER_MSG);
			throw new SystemException("信息已不存在，请刷新后重试!");
		}

		return "view";
	}

	// ------------- getter and setter -----------------

	public ExceptionLogPO getExceptionLogPO() {
		return exceptionLogPO;
	}

	public void setExceptionLogPO(ExceptionLogPO exceptionLogPO) {
		this.exceptionLogPO = exceptionLogPO;
	}

	public void setExceptionLogService(IExceptionLogService exceptionLogService) {
		this.exceptionLogService = exceptionLogService;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

}
