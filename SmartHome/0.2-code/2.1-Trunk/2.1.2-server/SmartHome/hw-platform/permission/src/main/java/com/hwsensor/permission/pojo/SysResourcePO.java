package com.hwsensor.permission.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * 
 * 项目名称：framework
 * 类名称：SysResourcePO
 * 类描述：系统业资源PO
 * 创建人：杜群星
 * 创建时间：2012-10-9 下午12:06:41
 * 修改人：Administrator
 * 修改时间：2012-10-9 下午12:06:41
 * 修改备注：
 * @version 
 *
 */
public class SysResourcePO implements Serializable{

	private static final long serialVersionUID = 5691722395044255423L;

	/**
	 * 业务资源主键
	 */
	private String UUID;
	
	/**
	 * 业务资源名称
	 */
	private String RESOURCE_NAME;
	
	/**
	 * 业务资源类型
	 */
	private String RESOURCE_TYPE;
	
	/**
	 * 业务菜单/业务操作主键
	 */
	private String MODULE_OPERA_UUID;
	
	/**
	 * 资源
	 */
	private String URL;
	
	/**
	 * 绑定函数
	 */
	private String BIND_FUNC;
	
	/**
	 * 是否默认资源0：默认，1：自定义
	 */
	private String IS_DEFAULT;
	
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

	public String getRESOURCE_NAME() {
		return RESOURCE_NAME;
	}

	public void setRESOURCE_NAME(String rESOURCE_NAME) {
		RESOURCE_NAME = rESOURCE_NAME;
	}

	public String getRESOURCE_TYPE() {
		return RESOURCE_TYPE;
	}

	public void setRESOURCE_TYPE(String rESOURCE_TYPE) {
		RESOURCE_TYPE = rESOURCE_TYPE;
	}

	public String getMODULE_OPERA_UUID() {
		return MODULE_OPERA_UUID;
	}

	public void setMODULE_OPERA_UUID(String mODULE_OPERA_UUID) {
		MODULE_OPERA_UUID = mODULE_OPERA_UUID;
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

	public String getIS_DEFAULT() {
		return IS_DEFAULT;
	}

	public void setIS_DEFAULT(String iS_DEFAULT) {
		IS_DEFAULT = iS_DEFAULT;
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
