/**
 * 文件名：K006PO.java
 *
 * 版本信息：1.0
 * 日期：2012-6-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.pojo;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.hw.hwsafe.attachment.pojo.C004PO;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：K006PO
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-8 下午2:56:29
 * 修改人：
 * 修改时间：2012-6-8 下午2:56:29
 * 修改备注：
 * @version 
 * 
 */
public class K004PO {
	/**
	 * 主键Id标识
	 */
	private String ma001;
	/**
	 * 标准名称
	 */
	private String ma002;	
	/**
	 * 标准编号
	 */
	private String ma003;
	/**
	 *标准类别
	 */
	private String ma004;		
	/**
	 * 发布日期
	 */
	private Date ma005;
	/**
	 * 实施日期
	 */
	private Date ma006;		
	/**
	 * 状态 过滤字段 1-有效；0--作废
	 */
	private String ma007;	
	/**
	 * 取消日期
	 */
	private Date ma008;
	/**
	 *颁布部门
	 */
	private String ma009;	
	/**
	 *颁布地区
	 */	
	private String ma010;
	/**
	 * 保存文件地址
	 */
	private String ma011;
	/**
	 * 法规内容
	 */
	private String ma012;	
	/**
	 *临时文件名称
	 */
	private String ma013;
	/**
	 * 填表人
	 */
	private String ma014;	
	/**
	 * 填表日期
	 */
	private Date ma015;
	
	/**
	 * 附件列表的id数组
	 */
	private String[] c004ids;
	
	private List<C004PO> c004poList;
	
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
	@JSON(format="yyyy-MM-dd")
	public Date getMa005() {
		return ma005;
	}
	public void setMa005(Date ma005) {
		this.ma005 = ma005;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getMa006() {
		return ma006;
	}
	public void setMa006(Date ma006) {
		this.ma006 = ma006;
	}
	public String getMa007() {
		return ma007;
	}
	public void setMa007(String ma007) {
		this.ma007 = ma007;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getMa008() {
		return ma008;
	}
	public void setMa008(Date ma008) {
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
	public String getMa011() {
		return ma011;
	}
	public void setMa011(String ma011) {
		this.ma011 = ma011;
	}
	public String getMa012() {
		return ma012;
	}
	public void setMa012(String ma012) {
		this.ma012 = ma012;
	}
	public String getMa013() {
		return ma013;
	}
	public void setMa013(String ma013) {
		this.ma013 = ma013;
	}
	public String getMa014() {
		return ma014;
	}
	public void setMa014(String ma014) {
		this.ma014 = ma014;
	}
	public Date getMa015() {
		return ma015;
	}
	public void setMa015(Date ma015) {
		this.ma015 = ma015;
	}
	public String[] getC004ids() {
		return c004ids;
	}
	public void setC004ids(String[] c004ids) {
		this.c004ids = c004ids;
	}
	public List<C004PO> getC004poList() {
		return c004poList;
	}
	public void setC004poList(List<C004PO> c004poList) {
		this.c004poList = c004poList;
	}
	
	
}
