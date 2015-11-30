/**
 * 文件名：SysUserRolePO.java
 *
 * 版本信息：
 * 日期：2012-10-22
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.pojo;

import java.io.Serializable;
import java.util.Date;


/**
 * 项目名称：framework
 * 类名称：SysUserRolePO
 * 类描述：
 * 创建人：杜群星
 * 创建时间：2012-10-22 下午3:28:31
 */
public class SysUserRolePO implements Serializable {

	private static final long serialVersionUID = -5573481545095319964L;

	/**
	 * 主键
	 */
	private String UUID;
	
	/**
	 * 用户ID
	 */
	private String USER_UUID;
	
	/**
	 * 角色ID
	 */
	private String ROLE_UUID;
	
	/**
	 * 创建人
	 */
	private String CREATOR;
	
	/**
	 * 创建日期
	 */
	private Date CREATE_DATE;

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getUSER_UUID() {
		return USER_UUID;
	}

	public void setUSER_UUID(String uSER_UUID) {
		USER_UUID = uSER_UUID;
	}

	public String getROLE_UUID() {
		return ROLE_UUID;
	}

	public void setROLE_UUID(String rOLE_UUID) {
		ROLE_UUID = rOLE_UUID;
	}

	public String getCREATOR() {
		return CREATOR;
	}

	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}

	public Date getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(Date cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}

}
