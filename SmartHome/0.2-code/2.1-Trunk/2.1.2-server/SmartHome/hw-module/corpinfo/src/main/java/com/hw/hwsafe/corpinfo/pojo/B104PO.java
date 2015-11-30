/**
 * 文件名：B104PO.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.pojo;
import java.io.Serializable;
import java.util.Date;

/**
 * B104PO类
 *
 */
public class B104PO implements Serializable {

	private static final long serialVersionUID = 1L;

	// -------------- fields --------------

	/**
	 * 主键
	 */
	private String ma001;

	/**
	 * 企业id
	 */
	private String ma002;

	/**
	 * 申请人
	 */
	private String ma003;

	/**
	 * 申请理由
	 */
	private String ma004;

	/**
	 * 申请时间
	 */
	private Date ma005;

	/**
	 * 内容,分割，如：基本信息,规章制度
	 */
	private String ma006;

	/**
	 * 表名,分割 如：B001,B004
	 */
	private String ma007;

	/**
	 * 状态 0待审 1通过 2未通过
	 */
	private String ma008;

	/**
	 * 备用1
	 */
	private String ma009;

	/**
	 * 备用2
	 */
	private String ma010;

	/**
	 * 备用3
	 */
	private String ma011;

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

	public Date getMa005() {
		return ma005;
	}

	public void setMa005(Date ma005) {
		this.ma005 = ma005;
	}

	public String getMa006() {
		return ma006;
	}

	public void setMa006(String ma006) {
		this.ma006 = ma006;
	}

	public String getMa007() {
		return ma007;
	}

	public void setMa007(String ma007) {
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

	public String getMa010() {
		return ma010;
	}

	public void setMa010(String ma010) {
		this.ma010 = ma010;
	}

	public String getMa011() {
		return ma011;
	}

	public void setMa011(String ma011) {
		this.ma011 = ma011;
	}

}