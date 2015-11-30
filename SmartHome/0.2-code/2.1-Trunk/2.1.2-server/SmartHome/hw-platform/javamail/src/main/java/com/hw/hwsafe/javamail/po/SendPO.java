package com.hw.hwsafe.javamail.po;

import java.util.List;

public class SendPO {
	/**
	 * 邮件ID
	 */
	private String mailId;
	
	/**
	 * 发送人
	 */
	private String userName;
	
	/**
	 * 发送人密码
	 */
	private String password;
	
	/**
	 * 接收人集合
	 */
	private List<String> reList;
	
	/**
	 * 邮件服务器
	 */
	private String host;

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getReList() {
		return reList;
	}

	public void setReList(List<String> reList) {
		this.reList = reList;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
}
