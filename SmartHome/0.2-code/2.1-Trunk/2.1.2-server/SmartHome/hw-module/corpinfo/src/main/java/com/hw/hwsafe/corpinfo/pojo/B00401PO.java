/**
 * 文件名：B004PO.java
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
 * 类名称：B004PO
 * 类描述：
 * 创建人：wy
 * 创建时间：2012-6-11 下午6:05:09
 * 修改人：
 * 修改时间：2012-6-11 下午6:05:09
 * 修改备注：
 * @version 
 * 
 */
public class B00401PO {


	/**
	 * 唯一标志
	 */
	private String MA001;
	/**
	 * 企业id
	 */
	private String MA002;
	/**
	 * 企业名称
	 */
	private String MA003;
	/**
	 * 设备名称
	 */
	private String MA004;
	/**
	 * 规格型号
	 */
	private String MA005;
	/**
	 * 设备类型
	 */
	private String MA006;
	/**
	 * 制造单位
	 */
	private String MA007;
	/**
	 * 制造日期
	 */	
	private Date MA008;	
	/**
	 * 安装单位
	 */
	private String MA009;
	/**
	 * 启用日期
	 */	
	private Date MA010;
	/**
	 * 有效截至日期
	 */
	private Date MA011;
	/**
	 * 监控管理负责人
	 */	
	private String MA012;
	/**
	 * 定期检测情况
	 */
	private String MA013;
	/**
	 * 保养及检查情况
	 */	
	private String MA014;
	/**
	 * 是否是应急资源
	 */
	private String MA015;
	/**
	 * 备注
	 */
	private String MA016;
	/**
	 * 填表人
	 */	
	private String MA017;
	/**
	 * 填表时间
	 */
	private Date MA018;
	/**
	 * 更新时间
	 */
	private Date MA019;
	/**
	 * 状态
	 */
	private String MA020;
	/**
	 * 设备数量
	 */
	private Integer MA021;
	/**
	 * 设备名称拼音首字母
	 */
	private String MA022;
	
	private String typeCode;
	private String wlevel;
	
	private String type;
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
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
	public String getMA007() {
		return MA007;
	}
	public void setMA007(String mA007) {
		MA007 = mA007;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getMA008() {
		return MA008;
	}
	public void setMA008(Date mA008) {
		MA008 = mA008;
	}
	public String getMA009() {
		return MA009;
	}
	public void setMA009(String mA009) {
		MA009 = mA009;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getMA010() {
		return MA010;
	}
	public void setMA010(Date mA010) {
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
	public Date getMA018() {
		return MA018;
	}
	public void setMA018(Date mA018) {
		MA018 = mA018;
	}
	public Date getMA019() {
		return MA019;
	}
	public void setMA019(Date mA019) {
		MA019 = mA019;
	}
	public String getMA020() {
		return MA020;
	}
	public void setMA020(String mA020) {
		MA020 = mA020;
	}
	public Integer getMA021() {
		return MA021;
	}
	public void setMA021(Integer mA021) {
		MA021 = mA021;
	}
	public String getMA022() {
		return MA022;
	}
	public void setMA022(String mA022) {
		MA022 = mA022;
	}
	public String getWlevel() {
		return wlevel;
	}
	public void setWlevel(String wlevel) {
		this.wlevel = wlevel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}