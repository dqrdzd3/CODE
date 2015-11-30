/**
 * 文件名：C002PO.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.gov.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 政府机构PO类
 * 
 * @author 马宁
 * @since 2012-9-29 上午10:58:42
 */
public class C002PO implements Serializable {

	private static final long serialVersionUID = 655064644898209945L;

	/**
	 * 删除标记_有效
	 */
	public static final String MA017_VALID="1";
	
	/**
	 * 删除标记_删除
	 */
	public static final String MA017_DEL="0";
	
	/**
	 * 是否有效 :有效：10
	 */
	public static final String MA008_VALID="10";
	/**
	 * 是否有效 :无效：00
	 */
	public static final String MA008_NO="00";
	
	/**
	 * ID
	 */
	private String ma001;

	/**
	 * 政府单位编号
	 */
	private String ma002;

	/**
	 * 政府单位编号前缀
	 */
	private String preMa002;

	/**
	 * 政府单位名称
	 */
	private String ma003;

	/**
	 * 上级主管单位ID
	 */
	private String ma004;

	/**
	 * 联系电话(固话)
	 */
	private String ma005;

	/**
	 * 手机号
	 */
	private String ma006;

	/**
	 * 邮箱
	 */
	private String ma007;

	/**
	 * 是否有效(无效：00，有效：10)
	 */
	private String ma008;

	/**
	 * 备注
	 */
	private String ma009;

	/**
	 * 创建人
	 */
	private String ma010;

	/**
	 * 创建日期
	 */
	private Date ma011;

	/**
	 * 修改人
	 */
	private String ma012;

	/**
	 * 修改日期
	 */
	private Date ma013;

	/**
	 * 同时在线人数
	 */
	private Integer ma014;

	/**
	 * 行政区划代码
	 */
	private String ma015;

	/**
	 * 省直辖市汉字简称
	 */
	private String ma016;

	/**
	 * 删除标记 1：有效；0：删除
	 */
	private String ma017;

	private String ma018; // 备用字段1

	private String ma019; // 备用字段2

	// ------------ methods ---------
	
	/**
	 * 判断C002PO是否有效
	 */
	public boolean isValid(){
		return MA008_VALID.equals(ma008);
	}
	
	// ----------getters and setters-----------

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

	public String getPreMa002() {
		return preMa002;
	}

	public void setPreMa002(String preMa002) {
		this.preMa002 = preMa002;
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

	public Date getMa011() {
		return ma011;
	}

	public void setMa011(Date ma011) {
		this.ma011 = ma011;
	}

	public String getMa012() {
		return ma012;
	}

	public void setMa012(String ma012) {
		this.ma012 = ma012;
	}

	public Date getMa013() {
		return ma013;
	}

	public void setMa013(Date ma013) {
		this.ma013 = ma013;
	}

	public Integer getMa014() {
		return ma014;
	}

	public void setMa014(Integer ma014) {
		this.ma014 = ma014;
	}

	public String getMa015() {
		return ma015;
	}

	public void setMa015(String ma015) {
		this.ma015 = ma015;
	}

	public String getMa016() {
		return ma016;
	}

	public void setMa016(String ma016) {
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

	public String getMa019() {
		return ma019;
	}

	public void setMa019(String ma019) {
		this.ma019 = ma019;
	}

}
