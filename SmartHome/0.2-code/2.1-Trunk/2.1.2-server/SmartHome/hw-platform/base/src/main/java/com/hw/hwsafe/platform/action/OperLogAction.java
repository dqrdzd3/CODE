/**
 * 文件名：OperLogAction.java
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
import com.hw.hwsafe.platform.pojo.OperLogPO;
import com.hw.hwsafe.platform.service.IOperLogService;

/**
 * 操作日志Action类
 * 
 * @author 马宁
 * @创建时间 2013-03-28 15:21
 */
public class OperLogAction extends BaseAction {

	/**
	 * service
	 */
	@Autowired
	private IOperLogService operLogService;

	/**
	 * po
	 */
	private OperLogPO operLogPO;

	/**
	 * 日志id 由页面传入
	 */
	private String logId;

	// ------------- methods ----------------

	/**
	 * 查看操作
	 */
	public String doView() {
		operLogPO = operLogService.retrieveInstanceById(logId);

		if (operLogPO == null) {
			message.set(Constants.MSG_ERROR, "信息已不存在，请刷新后重试!",
					Constants.SERVER_MSG);
			throw new SystemException("信息已不存在，请刷新后重试!");
		}

		return "view";
	}

	// -------------- setter and getter ------------------

	public void setOperLogService(IOperLogService operLogService) {
		this.operLogService = operLogService;
	}

	public OperLogPO getOperLogPO() {
		return operLogPO;
	}

	public void setOperLogPO(OperLogPO operLogPO) {
		this.operLogPO = operLogPO;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

}
