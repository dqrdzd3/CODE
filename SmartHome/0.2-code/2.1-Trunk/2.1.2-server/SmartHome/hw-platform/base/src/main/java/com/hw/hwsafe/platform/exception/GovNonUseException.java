/**
 * 文件名：GovNonUseException.java
 * 版本信息：
 * 日期：2012-12-17
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 */
package com.hw.hwsafe.platform.exception;


/**
 * 
 * 项目名称：framework
 * 类名称：GovNonUseException
 * 类描述：政府已停止使用异常
 * 创建人：杜群星
 * 创建时间：2012-12-17 下午1:49:26
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 
 * 
 */
public class GovNonUseException extends UserLoginException {

	private static final long serialVersionUID = -9118670969927672836L;

	public GovNonUseException() {
		super();
	}

	public GovNonUseException(String message) {
		super(message);
	}

	public GovNonUseException(Throwable e) {
		super(e);
	}

	public GovNonUseException(String message, Throwable e) {
		super(message, e);
	}

}
