package com.hw.hwsafe.sms.czd.exception;

/**
 * 项目名称：hw-sms
 * 类名称：SMSException
 * 类描述：自定义异常，返回异常信息
 * 创建人：陈浙东
 * 创建时间：2013-4-09
 *
 */
public class SMSException extends RuntimeException {
	
	private static final long serialVersionUID = 4221497856278864687L;

	public SMSException(){
		super();
	}
	
	public SMSException(String msg){
		super(msg);
	}
}
