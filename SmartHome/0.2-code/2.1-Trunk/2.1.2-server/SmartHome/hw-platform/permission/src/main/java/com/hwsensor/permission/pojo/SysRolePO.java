package com.hwsensor.permission.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 项目名称：framework
 * 类名称：SysRolePO
 * 类描述：系统角色
 * 创建人：杜群星
 * 创建时间：2012-10-11 下午9:43:59
 */

public class SysRolePO implements Serializable{

	private static final long serialVersionUID = 3444759841254800682L;

	/**
	 * 用户类型 常量定义   政府
	 */
	public static String USER_TYPE_GOV = "GOV";
	
	/**
	 * 用户类型 常量定义   企业
	 */
	public static String USER_TYPE_ENT = "ENT";
	
	/**
	 * 用户类型 常量定义   系统
	 */
	public static String USER_TYPE_SYS = "SYS";	
	
	/**
	 * 主键
	 */
	private String UUID;
	
	/**
	 * 角色名称
	 */
	private String ROLE_NAME;
	
	/**
	 * 角色代码
	 */
	private String ROLE_CODE;
	
	/**
	 * 用户类型 常量定义暂定：企业：ENT，政府:GOV，系统:SYS
	 */
	private String USER_TYPE;
	
	/**
	 * 企业或政府机构编码
	 */
	private String ORGAN_UUID;
	
	/**
	 * 备注
	 */
	private String REMARK;
	
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

	public String getROLE_NAME() {
		return ROLE_NAME;
	}

	public void setROLE_NAME(String rOLE_NAME) {
		ROLE_NAME = rOLE_NAME;
	}

	public String getROLE_CODE() {
		return ROLE_CODE;
	}

	public void setROLE_CODE(String rOLE_CODE) {
		ROLE_CODE = rOLE_CODE;
	}

	public String getUSER_TYPE() {
		return USER_TYPE;
	}

	public void setUSER_TYPE(String uSER_TYPE) {
		USER_TYPE = uSER_TYPE;
	}

	public String getORGAN_UUID() {
		return ORGAN_UUID;
	}

	public void setORGAN_UUID(String oRGAN_UUID) {
		ORGAN_UUID = oRGAN_UUID;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
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
