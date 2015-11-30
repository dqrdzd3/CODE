/**
 * 文件名：AccidentCases.java
 *
 * 版本信息：
 * 日期：2012-5-28
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：AccidentCases
 * 类描述：事故案例
 * 创建人：张成
 * 创建时间：2012-5-28 下午2:27:36
 * 修改人：张成
 * 修改时间：2012-5-28 下午2:27:36
 * 修改备注：
 * @version 
 * 
 */
public class TaccidentcasePO implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 主键Id标识
	 */
	private String objid;
	/**
	 * 事故名称
	 */
	private String name;
	/**
	 * 事故发生时间
	 */
	private Date createtime;	
	/**
	 * 事故发生地点
	 */
	private String address;		
	/**
	 * 事故类型
	 */
	private String type;		
	/**
	 * 事故行业
	 */
	private String casetrade;	
	/**
	 * 事故模型
	 */
	private String module;	
	/**
	 * 死亡原因
	 */
	private String cause;	
	/**
	 * 解决办法
	 */
	private String way;
	/**
	 * 死亡人数
	 */
	private Integer casesw;
	/**
	 * 重伤人数
	 */
	private Integer casezs;	
	/**
	 * 轻伤人数
	 */
	private Integer caseqs;		
	/**
	 * 财产损失
	 */
	private Double caseproperty;		
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 填表人
	 */
	private String  creator;		
	/**
	 * 填表日期
	 */
	private Date creatdate;
	/**
	 * objId
	 *
	 * @return  the objId
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public String getObjid() {
		return objid;
	}
	/**
	 * @param objId the objId to set
	 */
	public void setObjid(String objid) {
		this.objid = objid;
	}
	/**
	 * name
	 *
	 * @return  the name
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * createTime
	 *
	 * @return  the createTime
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	@JSON(format="yyyy-MM-dd")
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * address
	 *
	 * @return  the address
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * type
	 *
	 * @return  the type
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * caseTrade
	 *
	 * @return  the caseTrade
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public String getCasetrade() {
		return casetrade;
	}
	/**
	 * @param caseTrade the caseTrade to set
	 */
	public void setCasetrade(String casetrade) {
		this.casetrade = casetrade;
	}
	/**
	 * module
	 *
	 * @return  the module
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public String getModule() {
		return module;
	}
	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}
	/**
	 * cause
	 *
	 * @return  the cause
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public String getCause() {
		return cause;
	}
	/**
	 * @param cause the cause to set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}
	/**
	 * way
	 *
	 * @return  the way
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public String getWay() {
		return way;
	}
	/**
	 * @param way the way to set
	 */
	public void setWay(String way) {
		this.way = way;
	}
	/**
	 * casesw
	 *
	 * @return  the casesw
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public Integer getCasesw() {
		return casesw;
	}
	/**
	 * @param casesw the casesw to set
	 */
	public void setCasesw(Integer casesw) {
		this.casesw = casesw;
	}
	/**
	 * casezs
	 *
	 * @return  the casezs
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public Integer getCasezs() {
		return casezs;
	}
	/**
	 * @param casezs the casezs to set
	 */
	public void setCasezs(Integer casezs) {
		this.casezs = casezs;
	}
	/**
	 * caseqs
	 *
	 * @return  the caseqs
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public Integer getCaseqs() {
		return caseqs;
	}
	/**
	 * @param caseqs the caseqs to set
	 */
	public void setCaseqs(Integer caseqs) {
		this.caseqs = caseqs;
	}

	public Double getCaseproperty() {
		return caseproperty;
	}
	public void setCaseproperty(Double caseproperty) {
		this.caseproperty = caseproperty;
	}
	/**
	 * remarks
	 *
	 * @return  the remarks
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreatdate() {
		return creatdate;
	}
	public void setCreatdate(Date creatdate) {
		this.creatdate = creatdate;
	}
		
	
	
}
