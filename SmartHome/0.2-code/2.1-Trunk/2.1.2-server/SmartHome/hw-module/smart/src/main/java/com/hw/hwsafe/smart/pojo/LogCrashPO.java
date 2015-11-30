package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;

public class LogCrashPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String errorInfo;    //错误信息
	private String createTime;
	private UserBasePO baseInfo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public UserBasePO getBaseInfo() {
		return baseInfo;
	}
	public void setBaseInfo(UserBasePO baseInfo) {
		this.baseInfo = baseInfo;
	}
	
}
