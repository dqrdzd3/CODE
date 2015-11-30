/**
 * 文件名：NumberLimitException.java
 * 版本信息：
 * 日期：2012-12-17
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 */
package com.hw.hwsafe.platform.exception;


/**
 * 
 * 项目名称：framework
 * 类名称：NumberLimitException
 * 类描述：登录人数超过限制异常
 * 创建人：杜群星
 * 创建时间：2012-12-17 下午1:49:26
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 
 * 
 */
public class NumberLimitException extends UserLoginException {

	private static final long serialVersionUID = 1627649866114288614L;

	public NumberLimitException() {
		super();
	}

	public NumberLimitException(String message) {
		super(message);
	}

	public NumberLimitException(Throwable e) {
		super(e);
	}

	public NumberLimitException(String message, Throwable e) {
		super(message, e);
	}

}
