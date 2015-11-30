/**
 * 文件名：OnlineNumPO.java
 *
 * 版本信息：
 * 日期：2012-8-1
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.online;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：OnlineNumPO
 * 类描述：在线人数PO
 * 创建人：马宁
 * 创建时间：2013-02-27 9:07
 */
public class OnlineNumPO {

	/**
	 * 主体编码
	 */
	private String principalCode;

	/**
	 * 在线人数
	 */
	private Integer onlineNum;

	/**
	 * 最大在线人数
	 */
	private Integer maxOnlineNum;

	public String getPrincipalCode() {
		return principalCode;
	}

	public void setPrincipalCode(String principalCode) {
		this.principalCode = principalCode;
	}

	public Integer getOnlineNum() {
		return onlineNum;
	}

	public void setOnlineNum(Integer onlineNum) {
		this.onlineNum = onlineNum;
	}

	public Integer getMaxOnlineNum() {
		return maxOnlineNum;
	}

	public void setMaxOnlineNum(Integer maxOnlineNum) {
		this.maxOnlineNum = maxOnlineNum;
	}

}
