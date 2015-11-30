/**
 * 文件名：CustomJsonMsgException.java
 *
 * 版本信息：1.0
 * 日期：2012-5-22
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.exception.jsonmsg;

import com.hw.hwsafe.platform.constants.Constants;

/**
 * JSON信息展示异常类
 * 
 * @author 马宁
 * @创建时间: 2013-04-01 10:30
 */
public class JsonMsgException extends Exception {

	private static final long serialVersionUID = -2501616276729189100L;

	private Integer msgType;
	
	private String msgTitle;
	
	// -------------- 构造函数 ---------------
	
	public JsonMsgException(int msgType, String msg) {
		super(msg);
		this.msgType = msgType;
	}
	
	public JsonMsgException(int msgType, String msg, Throwable cause) {
		super(msg, cause);
		this.msgType = msgType;
	}
	
	public JsonMsgException(int msgType, String msg, String msgTitle){
		this(msgType, msg);
		this.msgTitle = msgTitle;
	}
	
	public JsonMsgException(int msgType, String msg, String msgTitle, Throwable cause){
		this(msgType, msg, cause);
		this.msgTitle = msgTitle;
	}

	// ---------- methods -----------
	
	/**
	 * 是否有msgTitle
	 */
	public boolean hasMsgTitle(){
		return msgTitle != null;
	}
	
	/**
	 * 是否错误msg
	 */
	public boolean isErrorMsg(){
		return msgType == Constants.MSG_ERROR;
	}

	// ----------- getter ------------
	
	public Integer getMsgType() {
		return msgType;
	}

	public String getMsgTitle() {
		return msgTitle;
	}
}
