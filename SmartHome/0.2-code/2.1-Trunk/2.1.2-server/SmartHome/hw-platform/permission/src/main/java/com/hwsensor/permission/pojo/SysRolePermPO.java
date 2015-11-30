package com.hwsensor.permission.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * 项目名称：framework
 * 类名称：SysRolePermPO
 * 类描述：系统业务模块操作关联
 * 创建人：杜群星
 * 创建时间：2012-10-9 下午1:28:37
 * 修改人：Administrator
 * 修改时间：2012-10-9 下午1:28:37
 * 修改备注：
 * @version 
 *
 */
public class SysRolePermPO implements Serializable{
	
	private static final long serialVersionUID = -2123695979199523395L;
	
	/**
	 * 主键
	 */
	private String UUID;
	
	/**
	 * 角色ID
	 */
	private String ROLE_UUID;
	
	/**
	 * 业务模块ID
	 */
	private String MODULE_UUID;
	
	/**
	 * 业务模块操作ID
	 */
	private String OPERA_UUID;
	
	/**
	 * 企业或政府单位编码
	 */
	private String ORGAN_UUID;
	
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

	public String getROLE_UUID() {
		return ROLE_UUID;
	}

	public void setROLE_UUID(String rOLE_UUID) {
		ROLE_UUID = rOLE_UUID;
	}

	public String getMODULE_UUID() {
		return MODULE_UUID;
	}

	public void setMODULE_UUID(String mODULE_UUID) {
		MODULE_UUID = mODULE_UUID;
	}

	public String getOPERA_UUID() {
		return OPERA_UUID;
	}

	public void setOPERA_UUID(String oPERA_UUID) {
		OPERA_UUID = oPERA_UUID;
	}

	public String getORGAN_UUID() {
		return ORGAN_UUID;
	}

	public void setORGAN_UUID(String oRGAN_UUID) {
		ORGAN_UUID = oRGAN_UUID;
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
