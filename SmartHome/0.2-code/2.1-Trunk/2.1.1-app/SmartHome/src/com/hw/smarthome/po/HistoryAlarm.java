/**
 * 文件名：A001PO.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 北京威果智能科技有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.smarthome.po;
import java.io.Serializable;
import java.util.Date;

/**
 * A001PO类
 *
 */
public class HistoryAlarm implements Serializable {

	private static final long serialVersionUID = 1L;

	// -------------- fields --------------

	/**
	 * 历史报警标识
	 */
	private String ma001;

	/**
	 * 值
	 */
	private double ma002;

	/**
	 * 状态
	 */
	private String ma003;

	/**
	 * 类型
	 */
	private String ma004;

	/**
	 * 时间
	 */
	private String ma005;

	/**
	 * 设备id
	 */
	private String ma006;

	/**
	 * 备注1
	 */
	private String ma007;

	/**
	 * 备注2
	 */
	private String ma008;

	// -------------- getter and setter --------------

	public String getMa001() {
		return ma001;
	}

	public void setMa001(String ma001) {
		this.ma001 = ma001;
	}

	public double getMa002() {
		return ma002;
	}

	public void setMa002(double ma002) {
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