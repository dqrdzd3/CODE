/**
 * 文件名：D002PO.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.smart.pojo;
import java.io.Serializable;
import java.util.Date;

/**
 * D002PO类
 *
 */
public class D002PO implements Serializable {

	private static final long serialVersionUID = 1L;

	// -------------- fields --------------

	/**
	 * 用户设备明细标识
	 */
	private String ma001;

	/**
	 * 用户信息标识
	 */
	private String ma002;

	/**
	 * 设备类型标识
	 */
	private String ma003;

	/**
	 * 设备标识
	 */
	private String ma004;

	/**
	 * 创建时间
	 */
	private Date ma005;

	/**
	 * 备用1
	 */
	private String ma006;

	/**
	 * 备用2
	 */
	private String ma007;
	
	/**
	 * 设备名称
	 */
	private String ma008;

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

}