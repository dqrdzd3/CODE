/**
 * 文件名：D004PO.java
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
 * D004PO类
 *
 */
public class D004PO implements Serializable {

	private static final long serialVersionUID = 1L;

	// -------------- fields --------------

	/**
	 * 传感器阀值明细标识
	 */
	private String ma001;

	/**
	 * 对照值最小
	 */
	private Integer ma002;

	/**
	 * 对照值最大
	 */
	private Integer ma003;

	/**
	 * 信息
	 */
	private String ma004;

	/**
	 * 是否启用
	 */
	private String ma005;

	/**
	 * 设备类型ID
	 */
	private String ma006;

	/**
	 * 创建时间
	 */
	private Date ma007;

	/**
	 * 备用1
	 */
	private String ma008;

	/**
	 * 备用2
	 */
	private String ma009;

	// -------------- getter and setter --------------

	public String getMa001() {
		return ma001;
	}

	public void setMa001(String ma001) {
		this.ma001 = ma001;
	}

	public Integer getMa002() {
		return ma002;
	}

	public void setMa002(Integer ma002) {
		this.ma002 = ma002;
	}

	public Integer getMa003() {
		return ma003;
	}

	public void setMa003(Integer ma003) {
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

	public String getMa006() {
		return ma006;
	}

	public void setMa006(String ma006) {
		this.ma006 = ma006;
	}

	public Date getMa007() {
		return ma007;
	}

	public void setMa007(Date ma007) {
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

}