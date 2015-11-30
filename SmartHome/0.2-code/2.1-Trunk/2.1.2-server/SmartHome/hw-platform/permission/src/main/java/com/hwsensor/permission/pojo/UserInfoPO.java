/**
 * 文件名：UserPO.java
 *
 * 版本信息：1.0
 * 日期：2012-5-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 项目名称：framework
 * 类名称：UserPO
 * 类描述：用户表
 * 创建人：盛家龙
 * 创建时间：2012-5-8 下午7:57:37
 * 修改人：盛家龙
 * 修改时间：2012-5-8 下午7:57:37
 * 修改备注：
 * @version 
 * 
 */
public class UserInfoPO implements Serializable {

	private static final long serialVersionUID = -4424085611177707312L;

	/**
	 * 主键
	 */
	private String UUID;
	
	/**
	 * 账户名称
	 */
	private String LOGIN_NAME;
	
	/**
	 * 密码
	 */
	private String PASSWORD;
	
	/**
	 * 真实姓名
	 */
	private String REAL_NAME;
	
	/**
	 * 年龄
	 */
	private Integer AGE;
	
	/**
	 * 性别 字典表定义男：M，女：F
	 */
	private String SEX;
	
	/**
	 * 手机
	 */
	private String MOBILE_NUMBER;
	
	/**
	 * 邮箱
	 */
	private String EMAIL;
	
	/**
	 * 身份证号码
	 */
	private String ID_CARD;
	
	/**
	 * 用户类型 常量定义企业：ENT，政府:GOV，系统:SYSַ
	 */
	private String USER_TYPE;
	
	/**
	 * 是否管理员
	 */
	private String IS_ADMIN;

	/**
	 * 企业编号
	 */
	private String COMPANY_UUID;

	/**
	 * 部门编号
	 */
	private String DEPART_UUID;

	/**
	 * 部门代码
	 */
	private String DEPART_CODE;

	/**
	 * 政府单位编号
	 */
	private String GOV_UUID;

	/**
	 * 状态 字典表定义 00:未审核，10：禁用，20：开通
	 */
	private String USER_STATUS;

	/**
	 * 员工编号
	 */
	private String EMPLOYEE_NUM;

	/**
	 * 最后一次访问系统IP
	 */
	private String LAST_ACCESS_IP;

	/**
	 * 最后一次访问系统时间
	 */
	private Date LAST_ACCESS_DATETIME;
	
	/**
	 * 创建人
	 */
	private String CREATOR;

	/**
	 * 创建日期
	 */
	private Date CREATE_DATE;

	/**
	 * 修改人
	 */
	private String EDITOR;

	/**
	 * 修改日期
	 */
	private Date MODIFIED_DATE;
	
	private String NEWPASSWORD;

	public String getNEWPASSWORD() {
		return NEWPASSWORD;
	}

	public void setNEWPASSWORD(String nEWPASSWORD) {
		NEWPASSWORD = nEWPASSWORD;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getLOGIN_NAME() {
		return LOGIN_NAME;
	}

	public void setLOGIN_NAME(String lOGIN_NAME) {
		LOGIN_NAME = lOGIN_NAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public String getREAL_NAME() {
		return REAL_NAME;
	}

	public void setREAL_NAME(String rEAL_NAME) {
		REAL_NAME = rEAL_NAME;
	}

	public Integer getAGE() {
		return AGE;
	}

	public void setAGE(Integer aGE) {
		AGE = aGE;
	}

	public String getSEX() {
		return SEX;
	}

	public void setSEX(String sEX) {
		SEX = sEX;
	}

	public String getMOBILE_NUMBER() {
		return MOBILE_NUMBER;
	}

	public void setMOBILE_NUMBER(String mOBILE_NUMBER) {
		MOBILE_NUMBER = mOBILE_NUMBER;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getID_CARD() {
		return ID_CARD;
	}

	public void setID_CARD(String iD_CARD) {
		ID_CARD = iD_CARD;
	}

	public String getUSER_TYPE() {
		return USER_TYPE;
	}

	public void setUSER_TYPE(String uSER_TYPE) {
		USER_TYPE = uSER_TYPE;
	}

	public String getIS_ADMIN() {
		return IS_ADMIN;
	}

	public void setIS_ADMIN(String iS_ADMIN) {
		IS_ADMIN = iS_ADMIN;
	}

	public String getCOMPANY_UUID() {
		return COMPANY_UUID;
	}

	public void setCOMPANY_UUID(String cOMPANY_UUID) {
		COMPANY_UUID = cOMPANY_UUID;
	}

	public String getDEPART_UUID() {
		return DEPART_UUID;
	}

	public void setDEPART_UUID(String dEPART_UUID) {
		DEPART_UUID = dEPART_UUID;
	}

	public String getDEPART_CODE() {
		return DEPART_CODE;
	}

	public void setDEPART_CODE(String dEPART_CODE) {
		DEPART_CODE = dEPART_CODE;
	}

	public String getGOV_UUID() {
		return GOV_UUID;
	}

	public void setGOV_UUID(String gOV_UUID) {
		GOV_UUID = gOV_UUID;
	}

	public String getUSER_STATUS() {
		return USER_STATUS;
	}

	public void setUSER_STATUS(String uSER_STATUS) {
		USER_STATUS = uSER_STATUS;
	}

	public String getEMPLOYEE_NUM() {
		return EMPLOYEE_NUM;
	}

	public void setEMPLOYEE_NUM(String eMPLOYEE_NUM) {
		EMPLOYEE_NUM = eMPLOYEE_NUM;
	}

	public String getLAST_ACCESS_IP() {
		return LAST_ACCESS_IP;
	}

	public void setLAST_ACCESS_IP(String lAST_ACCESS_IP) {
		LAST_ACCESS_IP = lAST_ACCESS_IP;
	}

	public Date getLAST_ACCESS_DATETIME() {
		return LAST_ACCESS_DATETIME;
	}

	public void setLAST_ACCESS_DATETIME(Date lAST_ACCESS_DATETIME) {
		LAST_ACCESS_DATETIME = lAST_ACCESS_DATETIME;
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

	public String getEDITOR() {
		return EDITOR;
	}

	public void setEDITOR(String eDITOR) {
		EDITOR = eDITOR;
	}

	public Date getMODIFIED_DATE() {
		return MODIFIED_DATE;
	}

	public void setMODIFIED_DATE(Date mODIFIED_DATE) {
		MODIFIED_DATE = mODIFIED_DATE;
	}

}