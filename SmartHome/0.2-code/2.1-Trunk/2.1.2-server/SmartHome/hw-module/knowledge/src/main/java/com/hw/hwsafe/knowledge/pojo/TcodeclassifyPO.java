/**
 * 文件名：Classification.java
 *
 * 版本信息：
 * 日期：2012-5-28
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.pojo;

import java.io.Serializable;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：Classification
 * 类描述：分类
 * 创建人：张成
 * 创建时间：2012-5-28 下午2:38:30
 * 修改人：张成
 * 修改时间：2012-5-28 下午2:38:30
 * 修改备注：
 * @version 
 * 
 */
public class TcodeclassifyPO  implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 主键Id标识
	 */
	private String objId;
	/**
	 * 编码标识
	 */
	private String codeTableId;
	/**
	 * 分类名称
	 */
	private String codeTableName;	
	/**
	 * 编码长度
	 */
	private Integer codeLen;		
	/**
	 * 分类规则
	 */
	private String divideRule;
	/**
	 * objId
	 *
	 * @return  the objId
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public String getObjId() {
		return objId;
	}
	/**
	 * @param objId the objId to set
	 */
	public void setObjId(String objId) {
		this.objId = objId;
	}
	/**
	 * codeTableId
	 *
	 * @return  the codeTableId
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public String getCodeTableId() {
		return codeTableId;
	}
	/**
	 * @param codeTableId the codeTableId to set
	 */
	public void setCodeTableId(String codeTableId) {
		this.codeTableId = codeTableId;
	}
	/**
	 * codeTableName
	 *
	 * @return  the codeTableName
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public String getCodeTableName() {
		return codeTableName;
	}
	/**
	 * @param codeTableName the codeTableName to set
	 */
	public void setCodeTableName(String codeTableName) {
		this.codeTableName = codeTableName;
	}
	/**
	 * codeLen
	 *
	 * @return  the codeLen
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public Integer getCodeLen() {
		return codeLen;
	}
	/**
	 * @param codeLen the codeLen to set
	 */
	public void setCodeLen(Integer codeLen) {
		this.codeLen = codeLen;
	}
	/**
	 * divideRule
	 *
	 * @return  the divideRule
	 * @since   CodingExample Ver(编码范例查看) 1.0
	 */
	
	public String getDivideRule() {
		return divideRule;
	}
	/**
	 * @param divideRule the divideRule to set
	 */
	public void setDivideRule(String divideRule) {
		this.divideRule = divideRule;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("Classification [objId=");
		str.append(objId);
		str.append(", codeTableId=");
		str.append(codeTableId);
		str.append(", codeTableName=");
		str.append(codeTableName);
		str.append(", codeLen=");
		str.append(codeLen);
		str.append(", divideRule=");
		str.append(divideRule);
		str.append("]");
		
		return str.toString();
	}
	
	
}
