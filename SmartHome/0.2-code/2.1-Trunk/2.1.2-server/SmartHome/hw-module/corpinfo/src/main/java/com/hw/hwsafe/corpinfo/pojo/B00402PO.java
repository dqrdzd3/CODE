/**
 * 文件名：B00402PO.java
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
 * B00402PO类
 *
 */
public class B00402PO implements Serializable {

	private static final long serialVersionUID = 1L;

	// -------------- fields --------------

	/**
	 * 年检主键
	 */
	private String ma001;

	/**
	 * 特种设备主键
	 */
	private String ma002;

	/**
	 * 检验机构
	 */
	private String ma003;

	/**
	 * 检验机构主键(暂不用)
	 */
	private String ma004;

	/**
	 * 检验时间
	 */
	private Date ma005;

	/**
	 * 是否通过检验(1：通过；0：未通过)
	 */
	private Integer ma006;

	/**
	 * 检验结果
	 */
	private String ma007;

	/**
	 * 周期，单位月
	 */
	private Integer ma008;

	/**
	 * 创建人
	 */
	private String ma009;

	/**
	 * 创建时间
	 */
	private Date ma010;

	/**
	 * 企业主键
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

	public Integer getMa006() {
		return ma006;
	}

	public void setMa006(Integer ma006) {
		this.ma006 = ma006;
	}

	public String getMa007() {
		return ma007;
	}

	public void setMa007(String ma007) {
		this.ma007 = ma007;
	}

	public Integer getMa008() {
		return ma008;
	}

	public void setMa008(Integer ma008) {
		this.ma008 = ma008;
	}

	public String getMa009() {
		return ma009;
	}

	public void setMa009(String ma009) {
		this.ma009 = ma009;
	}

	public Date getMa010() {
		return ma010;
	}

	public void setMa010(Date ma010) {
		this.ma010 = ma010;
	}

	public String getMa011() {
		return ma011;
	}

	public void setMa011(String ma011) {
		this.ma011 = ma011;
	}

}