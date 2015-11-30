/**
 * 文件名：D003PO.java
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
 * D003PO类
 *
 */
public class D003PO implements Serializable {

	private static final long serialVersionUID = 1L;

	// -------------- fields --------------

	/**
	 * 传感器操作标识
	 */
	private String ma001;

	/**
	 * 用户标识
	 */
	private String ma002;

	/**
	 * 操作内容
	 */
	private String ma003;

	/**
	 * 操作时间
	 */
	private Date ma004;

	/**
	 * 备用1
	 */
	private String ma005;

	/**
	 * 备用2
	 */
	private String ma006;

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

	public Date getMa004() {
		return ma004;
	}

	public void setMa004(Date ma004) {
		this.ma004 = ma004;
	}

	public String getMa005() {
		return ma005;
	}

	public void setMa005(String ma005) {
		this.ma005 = ma005;
	}

	public String getMa006() {
		return ma006;
	}

	public void setMa006(String ma006) {
		this.ma006 = ma006;
	}

}