/**
 * 文件名：SysUserPO.java
 *
 * 版本信息：
 * 日期：2012-10-15
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名称：framework 类名称：SysUserPO 类描述： 创建人：杜群星 创建时间：2012-10-15 下午1:25:36
 */
public class UserPO implements Serializable {

	private static final long serialVersionUID = -561145578552365809L;
	
	/**
	 * 用户类型 常量定义 企业：ENT，政府:GOV，系统:SYS
	 */
	public static final String USER_TYPE_GOV = "GOV";
	public static final String USER_TYPE_ENT = "ENT";
	public static final String USER_TYPE_SYS = "SYS";
	/**
	 * 状态 字典表定义 00:未审核，10：禁用，20：开通
	 */
	public static final String USER_STATUS_WSH = "00";
	public static final String USER_STATUS_JY = "10";
	public static final String USER_STATUS_KT = "20";
	/**
	 * 是否管理员
	 */
	public static final String IS_ADMIN_Y = "1";
	public static final String IS_ADMIN_N = "0";
	
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
	 * 用户类型 常量定义企业：ENT，政府:GOV，系统:SYS
	 */
	private String USER_TYPE;
	/**
	 * 是否管理员
	 */
	private String IS_ADMIN;
	/**
	 * 部门编号
	 */
	private String DEPART_UUID;
	/**
	 * 部门代码
	 */
	private String DEPART_CODE;
	/**
	 * 保留字段
	 */
	private String GOV_UUID;
	/**
	 * 状态 字典表定义00:未审核，10：禁用，20：开通
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
	/**
	 * 企业/政府机构UUID
	 */
	private String ORGAN_UUID;

	/**
	 * 移动设备访问 1：允许；0：禁止
	 */
	private String MOBILE_DEVICE_ACCESS;
	/**
	 * 外网访问 1：允许；0：禁止
	 */
	private String OUTER_NET_ACCESS;
	/**
	 * 扩展字段
	 */
	private String EXT1;
	/**
	 * 扩展字段
	 */
	private String EXT2;
	/**
	 * 扩展字段
	 */
	private String EXT3;

	/**
	 * 邮箱密码
	 */
	private String mailPassword;

	/**
	 * 职位
	 */
	private String positions;
	/**
	 * 所属企业/政府名称
	 */
	private String orgName;

	/**
	 * 用户类型名称
	 */
	private String userTypeName;
	/**
	 * 用户新密码
	 */
	private String newpassword;

	//------------- getter and setter --------------------
	
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

	public String getORGAN_UUID() {

		return ORGAN_UUID;
	}

	public void setORGAN_UUID(String oRGAN_UUID) {

		ORGAN_UUID = oRGAN_UUID;
	}

	public String getId() {

		return UUID;
	}

	public void setId(String id) {

		this.UUID = id;
	}

	public String getLoginName() {

		return LOGIN_NAME;
	}

	public void setLoginName(String loginName) {

		this.LOGIN_NAME = loginName;
	}

	public String getPassword() {

		return PASSWORD;
	}

	public void setPassword(String password) {

		this.PASSWORD = password;
	}

	public String getCorpid() {

		return ORGAN_UUID;
	}

	public void setCorpid(String corpid) {

		this.ORGAN_UUID = corpid;
	}

	public String getMOBILE_DEVICE_ACCESS() {

		return MOBILE_DEVICE_ACCESS;
	}

	public void setMOBILE_DEVICE_ACCESS(String mOBILE_DEVICE_ACCESS) {

		MOBILE_DEVICE_ACCESS = mOBILE_DEVICE_ACCESS;
	}

	public String getOUTER_NET_ACCESS() {

		return OUTER_NET_ACCESS;
	}

	public void setOUTER_NET_ACCESS(String oUTER_NET_ACCESS) {

		OUTER_NET_ACCESS = oUTER_NET_ACCESS;
	}

	public String getEXT1() {

		return EXT1;
	}

	public void setEXT1(String eXT1) {

		EXT1 = eXT1;
	}

	public String getEXT2() {

		return EXT2;
	}

	public void setEXT2(String eXT2) {

		EXT2 = eXT2;
	}

	public String getEXT3() {

		return EXT3;
	}

	public void setEXT3(String eXT3) {

		EXT3 = eXT3;
	}

	public void setMailPassword(String mailPassword) {

		this.mailPassword = mailPassword;
	}

	public String getMailPassword() {

		return mailPassword;
	}

	public String getOrgId() {

		return ORGAN_UUID;
	}

	public void setOrgName(String orgName) {

		this.orgName = orgName;
	}

	public String getOrgName() {

		return orgName;
	}

	public void setPositions(String positions) {

		this.positions = positions;
	}

	public String getPositions() {

		return positions;
	}

	public void setUserName(String userName) {

		this.REAL_NAME = userName;
	}

	public String getUserName() {

		return REAL_NAME;
	}

	public void setUserTypeName(String userTypeName) {

		this.userTypeName = userTypeName;
	}

	public String getUserTypeName() {

		return userTypeName;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public boolean isGovType(){
		return UserPO.USER_TYPE_GOV.equals(USER_TYPE);
	}
	
	public boolean isCorpType(){
		return UserPO.USER_TYPE_ENT.equals(USER_TYPE);
	}
	
	public boolean isSysType(){
		return UserPO.USER_TYPE_SYS.equals(USER_TYPE);
	}
	
	/**
	 * 
	 * 判断是否系统管理员
	 * @return boolean
	 * @author 马宁
	 * @create_time 2013-7-26 上午10:26:00
	 */
	public boolean isSysAdmin(){
		return "SYS".equals(getUSER_TYPE())
				&& "1".equals(getIS_ADMIN())
				&& "admin".equals(getLOGIN_NAME());
	}
}
