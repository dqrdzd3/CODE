/**
 * 文件名：K00801PO.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.pojo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * K00801PO类
 *
 */
public class K00801PO implements Serializable {

	private static final long serialVersionUID = 1L;

	// -------------- fields --------------

	/**
	 * 主键
	 */
	private String ma001;

	/**
	 * 法律法规表主键
	 */
	private String ma002;

	/**
	 * 名称
	 */
	private String ma003;

	/**
	 * 章节顺序(如：第一章)
	 */
	private String ma004;

	/**
	 * 创建人
	 */
	private String ma005;

	/**
	 * 创建时间
	 */
	private Date ma006;

	/**
	 * 页面顺序
	 */
	private Integer ma007;

	/**
	 * 备用2
	 */
	private String ma008;

	/**
	 * 备用3
	 */
	private String ma009;
	
	private List<K0080101PO> k0080101List;

	// -------------- getter and setter --------------

	public String getMa001() {
		return ma001;
	}

	public void setMa001(String ma001) {
		this.ma001 = ma001;
	}

	public String getMa002() {
		return ma002;
	}

	public void setMa002(String ma002) {
		this.ma002 = ma002;
	}

	public String getMa003() {
		return ma003;
	}

	public void setMa003(String ma003) {
		this.ma003 = ma003;
	}

	public String getMa004() {
		return ma004;
	}

	public void setMa004(String ma004) {
		this.ma004 = ma004;
	}

	public String getMa005() {
		return ma005;
	}

	public void setMa005(String ma005) {
		this.ma005 = ma005;
	}

	public Date getMa006() {
		return ma006;
	}

	public void setMa006(Date ma006) {
		this.ma006 = ma006;
	}

	public Integer getMa007() {
		return ma007;
	}

	public void setMa007(Integer ma007) {
		this.ma007 = ma007;
	}

	public String getMa008() {
		return ma008;
	}

	public void setMa008(String ma008) {
		this.ma008 = ma008;
	}

	public String getMa009() {
		return ma009;
	}

	public void setMa009(String ma009) {
		this.ma009 = ma009;
	}

	public List<K0080101PO> getK0080101List() {
		return k0080101List;
	}

	public void setK0080101List(List<K0080101PO> k0080101List) {
		this.k0080101List = k0080101List;
	}

}