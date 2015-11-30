/**
 * 文件名：DepartmentPO.java
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
 * 类名称：DepartmentPO
 * 类描述：部门表
 * 创建人：盛家龙
 * 创建时间：2012-5-8 下午7:57:37
 * 修改人：盛家龙
 * 修改时间：2012-5-8 下午7:57:37
 * 修改备注：
 * @version 
 * 
 */
public class DepartmentPO implements Serializable{

	private static final long serialVersionUID = -940823680879389144L;
	
	/**
	 * 部门主键
	 */
	private String uuid;
	
	/**
	 * 部门名称
	 */
	private String depart_name;
	
	/**
	 * 部门编码
	 */
	private String depart_code;
	
	/**
	 * 上级部门主键(父节点)
	 */
	private String parent_uuid;
	
	/**
	 * 企业/政府机构主键
	 */
	private String organ_uuid;
	
	/**
	 * 备注 	
	 */
	private String remark;
	
	/**
	 * 创建人
	 */
	private String creator;
	
	/**
	 * 创建日期
	 */
	private Date create_date;
	
	/**
	 * 父节点前置编码
	 */
	private String prekey;

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public String getDepart_code() {
		return depart_code;
	}

	public void setDepart_code(String depart_code) {
		this.depart_code = depart_code;
	}

	public String getParent_uuid() {
		return parent_uuid;
	}

	public void setParent_uuid(String parent_uuid) {
		this.parent_uuid = parent_uuid;
	}

	public String getOrgan_uuid() {
		return organ_uuid;
	}

	public void setOrgan_uuid(String organ_uuid) {
		this.organ_uuid = organ_uuid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getPrekey() {
		return prekey;
	}

	public void setPrekey(String prekey) {
		this.prekey = prekey;
	}
	
}