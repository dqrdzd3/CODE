/**
 * 文件名：S001PO.java
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
 * S001PO类
 *
 */
public class S001PO implements Serializable {

	private static final long serialVersionUID = 1L;

	// -------------- fields --------------

	/**
	 * 常见问题标识
	 */
	private String ma001;

	/**
	 * 标题
	 */
	private String ma002;

	/**
	 * 解决方法
	 */
	private String ma003;

	/**
	 * 添加日期
	 */
	private Date ma004;

	/**
	 * 用户id
	 */
	private String ma005;

	/**
	 * 备注1
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

	@Override
	public String toString() {
		return "S001PO [ma001=" + ma001 + ", ma002=" + ma002 + ", ma003=" + ma003 + ", ma004=" + ma004 + ", ma005="
				+ ma005 + ", ma006=" + ma006 + "]";
	}
	

}