/**
 * 文件名：UserLoginException.java
 *
 * 版本信息：1.0
 * 日期：2012-5-25
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.exception;

/**
 * 
 * 项目名称：framework
 * 类名称：UserLoginException
 * 类描述：用户登录异常
 * 创建人：盛家龙
 * 创建时间：2012-5-25 下午1:54:34
 * 修改人：马宁
 * 修改时间：2012-12-26 上午09:22:56
 * 修改备注：
 * @version 
 * 
 */
public class UserLoginException extends RuntimeException {

	private static final long serialVersionUID = 5615864432519600869L;

	public UserLoginException() {
		
	}

	public UserLoginException(String msg) {
		super(msg);
	}

	public UserLoginException(Throwable e) {
		super(e);
	}

	public UserLoginException(String msg, Throwable e) {
		super(msg, e);
	}

}
