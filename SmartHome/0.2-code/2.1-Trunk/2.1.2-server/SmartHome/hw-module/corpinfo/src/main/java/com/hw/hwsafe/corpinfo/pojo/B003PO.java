/**
 * 文件名：B003PO.java
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
 * 项目名称：hwsafe
 * 类名称：B003PO
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:04:53
 * 修改人：
 * 修改时间：2012-6-11 下午6:04:53
 * 修改备注：
 * @version 
 * 
 */
public class B003PO {

	/**
	 * 唯一标志
	 */
	private String MA001;
	/**
	 * 人员编号
	 */
	private String MA002;
	/**
	 * 企业id
	 */
	private String MA003;
	/**
	 * 姓名
	 */
	private String MA004;
	/**
	 * 职务
	 */
	private String MA005;
	/**
	 * 安全人员类别 
	 */
	private String MA006;
	/**
	 * 人员安全资质证书
	 */	
	private Integer MA007;	
	/**
	 * 注册情况
	 */
	private Integer MA008;
	/**
	 * 办公电话
	 */	
	private String MA009;
	/**
	 * 传真
	 */
	private String MA010;
	/**
	 * 级别
	 */	
	private Integer MA011;
	/**
	 *  状态
	 */
	private Integer MA012;
	/**
	 * 登记时间
	 */	
	private Date MA013;
	/**
	 * 更新时间
	 */
	private Date MA014;
	/**
	 * 备注
	 */
	private String MA015;
	/**
	 * 性别
	 */	
	private Integer MA016;
	/**
	 *  民族
	 */
	private Integer MA017;
	/**
	 * 出生日期
	 */	
	private Date MA018;
	/**
	 * 学历
	 */
	private Integer MA019;
	/**
	 * 技术职称
	 */
	private Integer MA020;
	
	/**
	 * 是否农民工  默认0:表示否  1:是
	 */
	private String MA021;
	/**
	 * 填表人
	 */
	private String MA022;
	/**
	 * 特殊工种人员
	 */
	private String MA023;
	
	private String type;
	
	public String getMA001() {
		return MA001;
	}
	public void setMA001(String mA001) {
		MA001 = mA001;
	}
	public String getMA002() {
		return MA002;
	}
	public void setMA002(String mA002) {
		MA002 = mA002;
	}
	public String getMA003() {
		return MA003;
	}
	public void setMA003(String mA003) {
		MA003 = mA003;
	}
	public String getMA004() {
		return MA004;
	}
	public void setMA004(String mA004) {
		MA004 = mA004;
	}
	public String getMA005() {
		return MA005;
	}
	public void setMA005(String mA005) {
		MA005 = mA005;
	}
	public String getMA006() {
		return MA006;
	}
	public void setMA006(String mA006) {
		MA006 = mA006;
	}
	public Integer getMA007() {
		return MA007;
	}
	public void setMA007(Integer mA007) {
		MA007 = mA007;
	}
	public Integer getMA008() {
		return MA008;
	}
	public void setMA008(Integer mA008) {
		MA008 = mA008;
	}
	public String getMA009() {
		return MA009;
	}
	public void setMA009(String mA009) {
		MA009 = mA009;
	}
	public String getMA010() {
		return MA010;
	}
	public void setMA010(String mA010) {
		MA010 = mA010;
	}
	public Integer getMA011() {
		return MA011;
	}
	public void setMA011(Integer mA011) {
		MA011 = mA011;
	}
	public Integer getMA012() {
		return MA012;
	}
	public void setMA012(Integer mA012) {
		MA012 = mA012;
	}
	public Date getMA013() {
		return MA013;
	}
	public void setMA013(Date mA013) {
		MA013 = mA013;
	}
	public Date getMA014() {
		return MA014;
	}
	public void setMA014(Date mA014) {
		MA014 = mA014;
	}
	public String getMA015() {
		return MA015;
	}
	public void setMA015(String mA015) {
		MA015 = mA015;
	}
	public Integer getMA016() {
		return MA016;
	}
	public void setMA016(Integer mA016) {
		MA016 = mA016;
	}
	public Integer getMA017() {
		return MA017;
	}
	public void setMA017(Integer mA017) {
		MA017 = mA017;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getMA018() {
		return MA018;
	}
	public void setMA018(Date mA018) {
		MA018 = mA018;
	}
	public Integer getMA019() {
		return MA019;
	}
	public void setMA019(Integer mA019) {
		MA019 = mA019;
	}
	public Integer getMA020() {
		return MA020;
	}
	public void setMA020(Integer mA020) {
		MA020 = mA020;
	}
	public String getMA021() {
		return MA021;
	}
	public void setMA021(String mA021) {
		MA021 = mA021;
	}
	public String getMA022() {
		return MA022;
	}
	public void setMA022(String mA022) {
		MA022 = mA022;
	}
	public String getMA023() {
		return MA023;
	}
	public void setMA023(String mA023) {
		MA023 = mA023;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
