/**
 * 文件名：BadUserNameOrPassWordException.java
 * 版本信息：
 * 日期：2012-12-17
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 */
package com.hw.hwsafe.platform.exception;


/**
 * 
 * 项目名称：framework
 * 类名称：BadUserNameOrPassWordException
 * 类描述：用户名或密码错误异常
 * 创建人：杜群星
 * 创建时间：2012-12-17 下午1:49:26
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 
 * 
 */
public class BadUserNameOrPassWordException extends UserLoginException {

	private static final long serialVersionUID = -5596985649588903580L;

	public BadUserNameOrPassWordException() {
		super();
	}

	public BadUserNameOrPassWordException(String message) {
		super(message);
	}

	public BadUserNameOrPassWordException(Throwable e) {
		super(e);
	}

	public BadUserNameOrPassWordException(String message, Throwable e) {
		super(message, e);
	}

}
