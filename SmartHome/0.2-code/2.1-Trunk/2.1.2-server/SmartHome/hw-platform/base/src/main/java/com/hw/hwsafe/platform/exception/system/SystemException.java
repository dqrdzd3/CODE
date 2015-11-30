/**
 * 文件名：SystemException.java
 *
 * 版本信息：1.0
 * 日期：2012-5-25
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.exception.system;

/**
 * 
 * 项目名称：framework
 * 类名称：SystemException
 * 类描述：系统异常
 * 创建人：盛家龙
 * 创建时间：2012-5-25 下午1:54:34
 * 修改人：盛家龙
 * 修改时间：2012-5-25 下午1:54:34
 * 修改备注：
 * @version 
 * 
 */
public class SystemException extends RuntimeException {

	private static final long serialVersionUID = -6406396060407643262L;

	public SystemException() {

	}

	public SystemException(String msg) {
		super(msg);
	}

	public SystemException(Throwable e) {
		super(e);
	}

	public SystemException(String msg, Throwable e) {
		super(msg, e);
	}

}
