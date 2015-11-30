package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;
import java.util.Date;

public class P2BPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ma001;

	/**
	 * 值
	 */
	private Double ma002;

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
	private  String ma005;

	/**
	 * 设备id
	 */
	private Date ma006;

	public String getMa001() {
		return ma001;
	}

	public void setMa001(String ma001) {
		this.ma001 = ma001;
	}

	public Double getMa002() {
		return ma002;
	}

	public void setMa002(Double ma002) {
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

}
