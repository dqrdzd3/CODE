/**
 * 文件名：IExceptionLogService.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.service;

import java.io.Serializable;

import com.hw.hwsafe.platform.pojo.ExceptionLogPO;

/**
 * 异常日志service接口
 * 
 * @author 马宁
 * @创建时间 2013-03-20
 */
public interface IExceptionLogService extends IBaseService, Serializable {

	/**
	 * 记录日志
	 * 
	 * @param msg
	 *            - 错误信息
	 * @param content
	 *            - 日志内容
	 * @return 日志编号
	 */
	String log(String msg, String content);

	/**
	 * 通过条件查询实例
	 * 
	 * @param id
	 *            - 异常日志的id
	 */
	ExceptionLogPO retrieveInstanceById(String id);
}
