/**
 * 文件名：A001PO.java
 * 版本信息：
 * 日期：2012-12-21
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 */
package com.hw.hwsafe.register.pojo;

import java.util.Date;

/**
 * 
 * 
 * 项目名称：hw-cpnyreg
 * 类名称：C006PO
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-12-21 下午5:07:26
 * 修改人：李玉梅
 * 修改时间：2012-12-21 下午5:07:26
 * 修改备注：
 * @version 
 *
 */
public class C006PO {
	/**
	 * 主键
	 */
	private String ma001;
	/**
	 * 企业名称
	 */
	private String ma002;
	/**
	 * 组织机构代码
	 */
	private String ma003;
	private String oldMa003;
	/**
	 * 省份代码
	 */
	private String ma004;
	/**
	 * 市代码
	 */
	private String ma005;
	/**
	 * 区县代码
	 */
	private String ma006;
	/**
	 * 经济类型
	 */
	private String ma007;
	/**
	 * 经济类型Name
	 */
	private String ma007Name;
	/**
	 *  行业类型
	 */
	private String ma008;
	/**
	 * 主要产品
	 */
	private String ma009;
	/**
	 * 用户名
	 */
	private String ma010;
	/**
	 * 密码
	 */
	private String ma011;
	private String oldPassword;
	/**
	 * 负责人姓名
	 */
	private String ma012;
	/**
	 * 负责人电话
	 */
	private String ma013;
	/**
	 * 负责人手机
	 */
	private String ma014;
	/**
	 * 负责人邮箱
	 */
	private String ma015;
	/**
	 * 创建时间
	 */
	private Date ma016;
	/**
	 * 审核人主键
	 */
	private String ma017;
	/**
	 * 审核人姓名
	 */
	private String ma018;
	/**
	 * 审核时间
	 */
	private Date ma019;
	/**
	 * 状态 10：注册； 20：审核通过；30：审核未通过
	 */

	private String ma020;
	/**
	 * 状态 10：注册； 20：审核通过；30：审核未通过
	 */

	private String ma021;
	/**
	 * 注册地址1
	 */
	private String ma022;
	/**
	 * 注册地址2
	 */
	private String ma023;
	/**
	 * 扩展字段3
	 */
	private String ma024;
	/**
	 * 行业门类ID
	 */
	private String hymlID;
	/**
	 * 行业门类Name
	 */
	private String hymlName;
	/**
	 * 行业大类ID
	 */
	private String hydlID;
	/**
	 * 行业大类Name
	 */
	private String hydlName;
	/**
	 * 行业中类ID
	 */
	private String hyzlID;
	/**
	 * 行业中类Name
	 */
	private String hyzlName;
	/**
	 * 行业小类ID
	 */
	private String hyxlID;
	/**
	 * 行业小类Name
	 */
	private String hyxlName;
	
	private String lsgx;
	private String qylx;
	
	private String zgdwid;

	/**
	 * 删除标记_有效
	 */
	public static final String MA021_VALID="1";
	/**
	 * 删除标记_删除
	 */
	public static final String MA021_DEL="0";
	
	/**
	 * zz状态：注册
	 */
	public final static String STATUS_REG = "10";
	/**
	 * 状态：审核通过
	 */
	public final static String STATUS_YES = "20";
	/**
	 * 状态：审核未通过
	 */
	public final static String STATUS_NO = "30";
	/**
	 * 企业编号前缀ZK
	 */
	public final static String CORPCODE_PRE = "ZK_";
	/**
	 * 默认用户数是5
	 */
	public final static Integer USER_NUM = 5;
	
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
	public String getMa015() {
		return ma015;
	}
	public void setMa015(String ma015) {
		this.ma015 = ma015;
	}
	public Date getMa016() {
		return ma016;
	}
	public void setMa016(Date ma016) {
		this.ma016 = ma016;
	}
	public String getMa017() {
		return ma017;
	}
	public void setMa017(String ma017) {
		this.ma017 = ma017;
	}
	public String getMa018() {
		return ma018;
	}
	public void setMa018(String ma018) {
		this.ma018 = ma018;
	}
	public Date getMa019() {
		return ma019;
	}
	public void setMa019(Date ma019) {
		this.ma019 = ma019;
	}
	public String getMa020() {
		return ma020;
	}
	public void setMa020(String ma020) {
		this.ma020 = ma020;
	}
	public String getMa021() {
		return ma021;
	}
	public void setMa021(String ma021) {
		this.ma021 = ma021;
	}
	public String getMa022() {
		return ma022;
	}
	public void setMa022(String ma022) {
		this.ma022 = ma022;
	}
	public String getMa023() {
		return ma023;
	}
	public void setMa023(String ma023) {
		this.ma023 = ma023;
	}
	public String getMa024() {
		return ma024;
	}
	public void setMa024(String ma024) {
		this.ma024 = ma024;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getHymlID() {
		return hymlID;
	}
	public void setHymlID(String hymlID) {
		this.hymlID = hymlID;
	}
	public String getHydlID() {
		return hydlID;
	}
	public void setHydlID(String hydlID) {
		this.hydlID = hydlID;
	}
	public String getHyzlID() {
		return hyzlID;
	}
	public void setHyzlID(String hyzlID) {
		this.hyzlID = hyzlID;
	}
	public String getHyxlID() {
		return hyxlID;
	}
	public void setHyxlID(String hyxlID) {
		this.hyxlID = hyxlID;
	}
	public String getMa007Name() {
		return ma007Name;
	}
	public void setMa007Name(String ma007Name) {
		this.ma007Name = ma007Name;
	}
	public String getHymlName() {
		return hymlName;
	}
	public void setHymlName(String hymlName) {
		this.hymlName = hymlName;
	}
	public String getHydlName() {
		return hydlName;
	}
	public void setHydlName(String hydlName) {
		this.hydlName = hydlName;
	}
	public String getHyzlName() {
		return hyzlName;
	}
	public void setHyzlName(String hyzlName) {
		this.hyzlName = hyzlName;
	}
	public String getHyxlName() {
		return hyxlName;
	}
	public String getZgdwid() {
		return zgdwid;
	}
	public void setZgdwid(String zgdwid) {
		this.zgdwid = zgdwid;
	}
	public void setHyxlName(String hyxlName) {
		this.hyxlName = hyxlName;
	}
	public String getOldMa003() {
		return oldMa003;
	}
	public void setOldMa003(String oldMa003) {
		this.oldMa003 = oldMa003;
	}
	public String getLsgx() {
		return lsgx;
	}
	public void setLsgx(String lsgx) {
		this.lsgx = lsgx;
	}
	public String getQylx() {
		return qylx;
	}
	public void setQylx(String qylx) {
		this.qylx = qylx;
	}
	
}
