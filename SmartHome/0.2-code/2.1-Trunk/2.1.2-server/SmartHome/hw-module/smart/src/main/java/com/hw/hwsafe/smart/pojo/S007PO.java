/**
 * 文件名：S007PO.java
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
 * S007PO类
 *
 */
public class S007PO implements Serializable {

	private static final long serialVersionUID = 1L;

	// -------------- fields --------------

	/**
	 * 措施id
	 */
	private String ma001;

	/**
	 * 气体类型
	 */
	private int ma002;

	/**
	 * 状态
	 */
	private int ma003;

	/**
	 * 标题
	 */
	private String ma004;

	/**
	 * 内容
	 */
	private String ma005;

	/**
	 * 开启状态 0 关闭  1 开启
	 */
	private int ma006;

	/**
	 * 使用时间
	 */
	private int ma007;
	
	//备注
	private String ma008;
	
	private String ma009;

	public String getMa001() {
		return ma001;
	}

	public void setMa001(String ma001) {
		this.ma001 = ma001;
	}

	public int getMa002() {
		return ma002;
	}

	public void setMa002(int ma002) {
		this.ma002 = ma002;
	}

	public int getMa003() {
		return ma003;
	}

	public void setMa003(int ma003) {
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

	public int getMa006() {
		return ma006;
	}

	public void setMa006(int ma006) {
		this.ma006 = ma006;
	}

	public int getMa007() {
		return ma007;
	}

	public void setMa007(int ma007) {
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

	// -------------- getter and setter --------------

	

}