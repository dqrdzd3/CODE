package com.hwsensor.permission.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * 项目名称：framework
 * 类名称：SysOperationPO
 * 类描述：系统业务操作PO
 * 创建人：杜群星
 * 创建时间：2012-10-9 下午12:06:27
 * 修改人：Administrator
 * 修改时间：2012-10-9 下午12:06:27
 * 修改备注：
 * @version 
 *
 */
public class SysOperationPO implements Serializable{
	
	private static final long serialVersionUID = 6847158702498887627L;

	/**
	 * 业务操作主键
	 */
	private String UUID;
	
	/**
	 * 业务模块主键
	 */
	private String MENU_UUID;
	
	/**
	 * 业务操作名称
	 */
	private String ACTION_NAME;
	
	/**
	 * 操作编码
	 */
	private String ACTION_CODE;
	
	/**
	 * 样式名称
	 */
	private String STYLE_NAME;
	
	/**
	 * 业务操作请求
	 */
	private String URL;
	
	/**
	 * 绑定函数
	 */
	private String BIND_FUNC;
	
	/**
	 * 是否有效 字典定义暂定：无效：00，有效：10
	 */
	private String IS_VALID;
	
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
	
	/**
	 * 排序号
	 */
	private Integer ORDERNUM;
	
	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getMENU_UUID() {
		return MENU_UUID;
	}

	public void setMENU_UUID(String mENU_UUID) {
		MENU_UUID = mENU_UUID;
	}

	public String getACTION_NAME() {
		return ACTION_NAME;
	}

	public void setACTION_NAME(String aCTION_NAME) {
		ACTION_NAME = aCTION_NAME;
	}

	public String getACTION_CODE() {
		return ACTION_CODE;
	}

	public void setACTION_CODE(String aCTION_CODE) {
		ACTION_CODE = aCTION_CODE;
	}

	public String getSTYLE_NAME() {
		return STYLE_NAME;
	}

	public void setSTYLE_NAME(String sTYLE_NAME) {
		STYLE_NAME = sTYLE_NAME;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getBIND_FUNC() {
		return BIND_FUNC;
	}

	public void setBIND_FUNC(String bIND_FUNC) {
		BIND_FUNC = bIND_FUNC;
	}

	public String getIS_VALID() {
		return IS_VALID;
	}

	public void setIS_VALID(String iS_VALID) {
		IS_VALID = iS_VALID;
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

	public void setORDERNUM(Integer oRDERNUM) {
		ORDERNUM = oRDERNUM;
	}

	public Integer getORDERNUM() {
		return ORDERNUM;
	}
	
}
