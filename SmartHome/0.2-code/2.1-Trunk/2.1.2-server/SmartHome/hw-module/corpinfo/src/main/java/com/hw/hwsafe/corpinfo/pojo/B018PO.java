package com.hw.hwsafe.corpinfo.pojo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

public class B018PO {

	/**
	 * 主键
	 */
	private String MA001;
	/**
	 * 企业id
	 */
	private String MA002;
	/**
	 * 急救药品资源代码
	 */
	private String MA003;
	/**
	 * 药品名称
	 */
	private String MA004;
	/**
	 * 管理部门
	 */
	private String MA005;
	/**
	 * 计量单位
	 */
	private String MA006;
	/**
	 * 数量
	 */	
	private Integer MA007;	
	/**
	 * 单价
	 */
	private Double MA008;
	/**
	 * 金额
	 */	
	private Double MA009;
	/**
	 * 存放场所
	 */
	private String MA010;
	/**
	 * 有效期
	 */	
	private Date MA011;
	/**
	 * 主要用途
	 */
	private String MA012;
	/**
	 * 使用方法
	 */	
	private String MA013;
	/**
	 * 具体负责人-姓名
	 */
	private String MA014;
	/**
	 * 具体负责人-办公电话
	 */
	private String MA015;
	/**
	 * 具体负责人-手机
	 */	
	
	private String MA016;
	/**
	 * 具体负责人-家庭电话
	 */
	private String MA017;
	/**
	 * 单位负责人-姓名
	 */
	private String MA018;
	/**
	 * 单位负责人-办公电话
	 */
	private String MA019;
	/**
	 * 单位负责人-手机
	 */
	private String MA020;
	/**
	 * 单位负责人-家庭电话
	 */	
	private String MA021;
	/**
	 * 状态  根据有效期，自动执行 0-失效；1-有效
	 */
	private Integer MA022;
	/**
	 * 备注
	 */
	private String MA023;
	
	/**
	 * 填表人
	 */
	private String MA024;
	/**
	 * 调表日期
	 */
	private Date MA025;
	
	public static final Integer YX=1;//有效
	public static final Integer WX=0;//无效
	
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
	public Double getMA008() {
		return MA008;
	}
	public void setMA008(Double mA008) {
		MA008 = mA008;
	}
	public Double getMA009() {
		return MA009;
	}
	public void setMA009(Double mA009) {
		MA009 = mA009;
	}
	public String getMA010() {
		return MA010;
	}
	public void setMA010(String mA010) {
		MA010 = mA010;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getMA011() {
		return MA011;
	}
	public void setMA011(Date mA011) {
		MA011 = mA011;
	}
	public String getMA012() {
		return MA012;
	}
	public void setMA012(String mA012) {
		MA012 = mA012;
	}
	public String getMA013() {
		return MA013;
	}
	public void setMA013(String mA013) {
		MA013 = mA013;
	}
	public String getMA014() {
		return MA014;
	}
	public void setMA014(String mA014) {
		MA014 = mA014;
	}
	public String getMA015() {
		return MA015;
	}
	public void setMA015(String mA015) {
		MA015 = mA015;
	}
	public String getMA016() {
		return MA016;
	}
	public void setMA016(String mA016) {
		MA016 = mA016;
	}
	public String getMA017() {
		return MA017;
	}
	public void setMA017(String mA017) {
		MA017 = mA017;
	}
	public String getMA018() {
		return MA018;
	}
	public void setMA018(String mA018) {
		MA018 = mA018;
	}
	public String getMA019() {
		return MA019;
	}
	public void setMA019(String mA019) {
		MA019 = mA019;
	}
	public String getMA020() {
		return MA020;
	}
	public void setMA020(String mA020) {
		MA020 = mA020;
	}
	public String getMA021() {
		return MA021;
	}
	public void setMA021(String mA021) {
		MA021 = mA021;
	}
	public Integer getMA022() {
		return MA022;
	}
	public void setMA022(Integer mA022) {
		MA022 = mA022;
	}
	public String getMA023() {
		return MA023;
	}
	public void setMA023(String mA023) {
		MA023 = mA023;
	}
	public String getMA024() {
		return MA024;
	}
	public void setMA024(String mA024) {
		MA024 = mA024;
	}
	public Date getMA025() {
		return MA025;
	}
	public void setMA025(Date mA025) {
		MA025 = mA025;
	}
	
}
