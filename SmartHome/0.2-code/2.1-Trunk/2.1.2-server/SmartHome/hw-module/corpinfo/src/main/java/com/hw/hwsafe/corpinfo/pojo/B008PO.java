/**
 * 文件名：B008PO.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.pojo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：B008PO
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-8-23 上午10:39:58
 * 修改人：李玉梅
 * 修改时间：2012-8-23 上午10:39:58
 * 修改备注：
 * @version 
 *
 */
public class B008PO {


	/**
	 * 唯一标志
	 */
	private String ma001;
	/**
	 * 企业id
	 */
	private String ma002;
	/**
	 * 企业名称
	 */
	private String ma003;
	/**
	 * 评价名称
	 */
	private String ma004;
	/**
	 * 评价单位
	 */
	private String ma005;
	/**
	 * 评价时间
	 */
	private Date ma006;
	/**
	 * 评价内容
	 */
	private String ma007;
	/**
	 * 评价报告
	 */
	private String ma008;
	/**
	 * 填表人
	 */
	private String ma009;
	/**
	 * 填表日期
	 */
	private Date ma010;
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
	public String getMa005() {
		return ma005;
	}
	public void setMa005(String ma005) {
		this.ma005 = ma005;
	}
	 @JSON(format="yyyy-MM-dd")
	public Date getMa006() {
		return ma006;
	}
	 @JSON(format="yyyy-MM-dd")
	public void setMa006(Date ma006) {
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
	public Date getMa010() {
		return ma010;
	}
	public void setMa010(Date ma010) {
		this.ma010 = ma010;
	}
	
}