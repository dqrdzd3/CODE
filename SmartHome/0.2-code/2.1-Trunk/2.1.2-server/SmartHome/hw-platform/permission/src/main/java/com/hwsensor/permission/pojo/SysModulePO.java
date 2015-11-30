package com.hwsensor.permission.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 项目名称：framework
 * 类名称：SysModulePO
 * 类描述：系统业务模块PO
 * 创建人：杜群星
 * 创建时间：2012-10-9 下午12:05:57
 * 修改人：Administrator
 * 修改时间：2012-10-9 下午12:05:57
 * 修改备注：
 * @version 
 *
 */
public class SysModulePO implements Serializable{

	private static final long serialVersionUID = 8469688318920324454L;
	
	/**
	 * 主键
	 */
	private String UUID;
	
	/**
	 * 菜单名称
	 */
	private String MENU_NAME;
	
	/**
	 * 模块编码
	 */
	private String MENU_CODE;
	
	/**
	 * 上级模块主键
	 */
	private String PARENT_UUID;
	
	/**
	 * URL链接
	 */
	private String URL;
	
	/**
	 * 用户类型 常量定义暂定：企业：ENT，政府:GOV，系统:SYS
	 */
	private String USER_TYPE;
	
	/**
	 * 是否有效 字典定义暂定：无效：00，有效：10
	 */
	private String IS_VALID;
	
	/**
	 * 权限类别_数据字典
	 */
	private String MENU_TYPE;
	
	/**
	 * 备注
	 */
	private String REMARK;
	
	/**
	 * 顺序号
	 */
	private Integer ORDERNUM;
	
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
	 * 是否显示
	 */
	private String IS_SHOW;
	
	/**
	 * 模块原始编码，不加父模块编码
	 */
	private String subCode;
	
	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getMENU_NAME() {
		return MENU_NAME;
	}

	public void setMENU_NAME(String mENU_NAME) {
		MENU_NAME = mENU_NAME;
	}

	public String getMENU_CODE() {
		return MENU_CODE;
	}

	public void setMENU_CODE(String mENU_CODE) {
		MENU_CODE = mENU_CODE;
	}

	public String getPARENT_UUID() {
		return PARENT_UUID;
	}

	public void setPARENT_UUID(String pARENT_UUID) {
		PARENT_UUID = pARENT_UUID;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getUSER_TYPE() {
		return USER_TYPE;
	}

	public void setUSER_TYPE(String uSER_TYPE) {
		USER_TYPE = uSER_TYPE;
	}

	public String getIS_VALID() {
		return IS_VALID;
	}

	public void setIS_VALID(String iS_VALID) {
		IS_VALID = iS_VALID;
	}

	public String getMENU_TYPE() {
		return MENU_TYPE;
	}

	public void setMENU_TYPE(String mENU_TYPE) {
		MENU_TYPE = mENU_TYPE;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	public Integer getORDERNUM() {
		return ORDERNUM;
	}

	public void setORDERNUM(Integer oRDERNUM) {
		ORDERNUM = oRDERNUM;
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

	public void setIS_SHOW(String iS_SHOW) {
		IS_SHOW = iS_SHOW;
	}

	public String getIS_SHOW() {
		return IS_SHOW;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getSubCode() {
		return subCode;
	}
}
