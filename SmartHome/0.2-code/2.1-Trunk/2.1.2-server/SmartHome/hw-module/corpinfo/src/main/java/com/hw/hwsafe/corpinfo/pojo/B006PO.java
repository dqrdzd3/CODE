/**
 * 文件名：B007PO.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.pojo;

import java.util.Date;
import java.util.List;

import com.hw.hwsafe.attachment.pojo.C004PO;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：B007PO
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-8-23 上午10:31:06
 * 修改人：李玉梅
 * 修改时间：2012-8-23 上午10:31:06
 * 修改备注：
 * @version 
 *
 */
public class B006PO {
	/**
	 * 唯一标志
	 */
	private String ma001;
	/**
	 * 企业ID
	 */
	private String ma002;
	/**
	 * 企业名称
	 */
	private String ma003;
	/**
	 * 方位
	 */
	private String ma004;
	/**
	 * 单位个数
	 */
	private Integer ma005;
	/**
	 * 单位名称
	 */
	private String ma006;
	/**
	 * 离企业距离
	 */
	private Double ma007;
	/**
	 * 建筑结构
	 */
	private String ma008;
	/**
	 * 建筑高度
	 */
	private Double ma009;
	/**
	 * 人员类型
	 */
	private String ma010;
	/**
	 * 人员数量
	 */
	private Integer ma011;
	/**
	 * 有关图片
	 */
	private String ma012;
	/**
	 * 有关图片路径
	 */
	private String ma013;
	/**
	 * 备注
	 */
	private String ma014;
	
	/**
	 * 填表人
	 */
	private String ma015;
	/**
	 * 填表日期
	 */
	private Date ma016;
	/**
	 * 是否标注
	 */
	private Integer ma017;
	/**
	 * 附件列表的id数组
	 */
	private String[] c004ids;
	
	private List<C004PO> c004poList;
	
	public static final int BZ = 0;
	public static final int WBZ = 1;
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
	public Integer getMa005() {
		return ma005;
	}
	public void setMa005(Integer ma005) {
		this.ma005 = ma005;
	}
	public String getMa006() {
		return ma006;
	}
	public void setMa006(String ma006) {
		this.ma006 = ma006;
	}
	public Double getMa007() {
		return ma007;
	}
	public void setMa007(Double ma007) {
		this.ma007 = ma007;
	}
	public String getMa008() {
		return ma008;
	}
	public void setMa008(String ma008) {
		this.ma008 = ma008;
	}
	public Double getMa009() {
		return ma009;
	}
	public void setMa009(Double ma009) {
		this.ma009 = ma009;
	}
	public String getMa010() {
		return ma010;
	}
	public void setMa010(String ma010) {
		this.ma010 = ma010;
	}
	public Integer getMa011() {
		return ma011;
	}
	public void setMa011(Integer ma011) {
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
	public Integer getMa017() {
		return ma017;
	}
	public void setMa017(Integer ma017) {
		this.ma017 = ma017;
	}
	
}
