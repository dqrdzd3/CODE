/**
 * 文件名：B00103PO.java
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
 * B00103PO类
 *
 */
public class B00103PO implements Serializable {

	private static final long serialVersionUID = 1L;

	// -------------- fields --------------

	/**
	 * 企业id
	 */
	private String ma001;

	/**
	 * 是否非药品类易制毒化学品企业  0否 1是
	 */
	private String ma002;

	/**
	 * 如果MA002是1，则存放有哪些易制毒物品
	 */
	private String ma003;

	/**
	 * 标准化达标级别
	 */
	private String ma004;

	/**
	 * 标准化达标日期
	 */
	private Date ma005;

	/**
	 * null
	 */
	private String ma006;

	/**
	 * null
	 */
	private String ma007;

	/**
	 * null
	 */
	private String ma008;

	/**
	 * null
	 */
	private String ma009;

	/**
	 * null
	 */
	private String ma010;

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

}