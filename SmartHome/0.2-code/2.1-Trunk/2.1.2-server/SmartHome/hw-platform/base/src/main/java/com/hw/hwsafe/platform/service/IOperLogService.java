/**
 * 文件名：IOperLogService.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.service;

import com.hw.hwsafe.platform.pojo.OperLogPO;

/**
 * 操作日志service接口
 * 
 * @author 马宁
 * @创建时间 2013-03-20
 */
public interface IOperLogService extends IBaseService {

	/**
	 * 记录日志
	 * 
	 * @param className
	 *            - 类名
	 * @param methodName
	 *            - 方法名
	 * @param content
	 *            - 内容
	 */
	void log(String className, String methodName, String content);

	/**
	 * 通过条件查询实例
	 * 
	 * @param id
	 *            - 操作日志的id
	 */
	OperLogPO retrieveInstanceById(String id);

}
