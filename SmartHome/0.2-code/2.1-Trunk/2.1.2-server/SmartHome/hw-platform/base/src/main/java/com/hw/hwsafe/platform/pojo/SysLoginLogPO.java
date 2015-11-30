/**
 * 文件名：SysLoginLogPO.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * SYS_LOGIN_LOGPO类
 * 
 */
public class SysLoginLogPO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 正常登录类型
	 */
	public static final Integer NORMAL_LOGIN_TYPE = 1;

	/**
	 * 正常登出类型
	 */
	public static final Integer NORMAL_LOGOUT_TYPE = 2;

	// -------------- fields --------------

	/**
	 * 日志标识
	 */
	private String id;

	/**
	 * 日志类型(1:正常登录; 2:正常登出)
	 */
	private Integer type;

	/**
	 * 记录时间
	 */
	private Date recordingTime;

	/**
	 * 用户标识
	 */
	private String userId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * IP
	 */
	private String ip;

	/**
	 * 政府标识
	 */
	private String govId;

	/**
	 * 企业标识
	 */
	private String corpId;

	/**
	 * 备注
	 */
	private String remarks;

	// -------------- Constructor -----------------

	public SysLoginLogPO(String id, Integer type, Date recordingTime,
			String userId, String userName, String ip, String govId,
			String corpId) {
		this.id = id;
		this.type = type;
		this.recordingTime = recordingTime;
		this.userId = userId;
		this.userName = userName;
		this.ip = ip;
		this.govId = govId;
		this.corpId = corpId;
	}

	// -------------- getter and setter --------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getRecordingTime() {
		return recordingTime;
	}

	public void setRecordingTime(Date recordingTime) {
		this.recordingTime = recordingTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getGovId() {
		return govId;
	}

	public void setGovId(String govId) {
		this.govId = govId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}