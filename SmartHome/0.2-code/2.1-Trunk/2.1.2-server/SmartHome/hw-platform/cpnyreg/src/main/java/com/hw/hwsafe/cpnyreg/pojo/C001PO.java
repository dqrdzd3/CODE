/**
 * 文件名：C001PO.java
 *
 * 版本信息：
 * 日期：2012-8-6
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.cpnyreg.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：C001PO
 * 类描述：公司
 * 创建人：付强
 * 创建时间：2012-8-6 上午11:40:26
 * 
 */
public class C001PO implements Serializable{

	private static final long serialVersionUID = -5389033373347000282L;

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
	 * 主键
	 */
	private String MA001;
	
	/**
	 * 企业名称
	 */
	private String MA002;
	
	/**
	 * 营业执照注册号
	 */
	private String MA003;
	
	/**
	 * 主管政府部门ID
	 */
	private String MA004;
	
	/**
	 * 企业联系电话
	 */
	private String MA005;
	
	/**
	 * 手机号
	 */
	private String MA006;
	
	/**
	 * 邮箱
	 */
	private String MA007;
	
	/**
	 * 是否有效
	 */
	private String MA008;
	
	/**
	 * 同时在线人数
	 */
	private Integer MA009;
	
	/**
	 * 备注
	 */
	private String MA010;
	
	/**
	 * 创建人
	 */
	private String MA011;
	
	/**
	 * 创建日期
	 */
	private Date MA012;
	
	/**
	 * 修改人
	 */
	private String MA013;
	
	/**
	 * 修改日期
	 */
	private Date MA014;
	
	/**
	 * 行政区划代码
	 */
	private String MA015;
	
	/**
	 * 省直辖市汉字简称
	 */
	private String MA016;
	
	/**
	 * 删除标记
	 */
	private String MA017;
	
	/**
	 * 备用字段1
	 */
	private String MA018;
	
	/**
	 * 备用字段2
	 */
	private String MA019;

	// --------- methods ---------
	
	/**
	 * 判断C001PO是否有效
	 */
	public boolean isValid(){
		return MA008_VALID.equals(MA008);
	}
	
	// ----------- getter and setter ----------
	
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

	public String getMA008() {
		return MA008;
	}

	public void setMA008(String mA008) {
		MA008 = mA008;
	}

	public Integer getMA009() {
		return MA009;
	}

	public void setMA009(Integer mA009) {
		MA009 = mA009;
	}

	public String getMA010() {
		return MA010;
	}

	public void setMA010(String mA010) {
		MA010 = mA010;
	}

	public String getMA011() {
		return MA011;
	}

	public void setMA011(String mA011) {
		MA011 = mA011;
	}

	public Date getMA012() {
		return MA012;
	}

	public void setMA012(Date mA012) {
		MA012 = mA012;
	}

	public String getMA013() {
		return MA013;
	}

	public void setMA013(String mA013) {
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
	
}
