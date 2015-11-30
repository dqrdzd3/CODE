/**
 * 文件名：PushletMsgPO.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.pushlet.po;

import java.io.Serializable;

/**
 * pushletMsg的po类
 * 
 * @author 马宁
 * @创建时间 2013-09-04 08:40
 */
public class PushletMsgPO implements Serializable {

	private static final long serialVersionUID = -3507544630260502219L;

	// --------------- constructor ------------------
	public PushletMsgPO() {
		super();
	}
	
	public PushletMsgPO(String id, String userId, String msg, String url) {
		super();
		this.id = id;
		this.userId = userId;
		this.msg = msg;
		this.url = url;
	}

	// -------------- fields --------------

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 消息
	 */
	private String msg;

	/**
	 * 访问url
	 */
	private String url;
	
	// -------------- getter and setter --------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}