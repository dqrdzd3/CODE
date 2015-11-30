/**
 * 文件名：DictionaryPO.java
 *
 * 版本信息：
 * 日期：2012-8-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 项目名称：framework
 * 类名称：DictionaryPO
 * 类描述：系统字典
 * 创建人：付强
 * 创建时间：2012-8-8 上午11:11:14
 * 
 */
public class C007PO implements Serializable{

	private static final long serialVersionUID = -7691890467980148006L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 代码
	 */
	private String key;
	
	/**
	 * 值
	 */
	private String value;
	
	/**
	 * 显示内容
	 */
	private String display;
	
	/**
	 * 描述
	 */
	private String describe;
	
	/**
	 * 父节点
	 */
	private String parentid;
	
	/**
	 * 排序
	 */
	private Integer ordernum;
	
	/**
	 * 层级数
	 */
	private Integer levelnum;
	
	/**
	 * 删除标记
	 */
	private String delFlag;
	
	/**
	 * 删除标记_有效
	 */
	public static final String DEL_FLAG_VALID="1";
	
	/**
	 * 删除标记_删除
	 */
	public static final String DEL_FALG_DEL="0";
	
	/**
	 * key的前置词
	 */
	private String parentKey;
	
	/**
	 * 全编码
	 */
	private String fullKey;
	
	/**
	 * 是否是末端节点
	 */
	private Boolean termNode;
	
	private List<C007PO> childlist;
	
	public static final String SEPARATOR="_";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public Integer getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

	public Boolean getTermNode() {
		return termNode;
	}

	public void setTermNode(Boolean termNode) {
		this.termNode = termNode;
	}

	public Integer getLevelnum() {
		return levelnum;
	}

	public void setLevelnum(Integer levelnum) {
		this.levelnum = levelnum;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getFullKey() {
		return fullKey;
	}

	public void setFullKey(String fullKey) {
		this.fullKey = fullKey;
	}

	public List<C007PO> getChildlist() {
		return childlist;
	}

	public void setChildlist(List<C007PO> childlist) {
		this.childlist = childlist;
	}
	
}
