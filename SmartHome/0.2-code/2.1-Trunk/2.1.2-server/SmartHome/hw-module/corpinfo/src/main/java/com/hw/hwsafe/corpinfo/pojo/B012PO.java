/**
 * 文件名：B012PO.java
 *
 * 版本信息：1.0
 * 日期：2012-7-2
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.pojo;

import java.util.Date;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B012PO
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-7-2 上午10:56:32
 * 修改人：
 * 修改时间：2012-7-2 上午10:56:32
 * 修改备注：
 * @version 
 * 
 */
public class B012PO {
	
	/**
	 * 唯一标志
	 */
	private String MA001;
	/**
	 * 货物单位
	 */
	private String MA002;
	/**
	 * 企业id
	 */
	private String MA003;
	/**
	 * 中文名称
	 */
	private String MA004;
	/**
	 * 别名
	 */
	private String MA005;
	/**
	 * 危险货物编号
	 */
	private String MA006;
	/**
	 * 数量
	 */	
	private Double MA007;	
	/**
	 * 产品类型
	 */
	private String MA008;
	/**
	 * 危险物质名称说明
	 */	
	private String MA009;
	/**
	 * 登记时间
	 */
	private Date MA010;
	/**
	 * 登记时间
	 */	
	private Date MA011;
	/**
	 * 状态
	 */
	private String MA012;
	/**
	 * 备注
	 */	
	private String MA013;
	/**
	 * 填表人
	 */	
	private String MA014;
	
	/**
	 * 是否标注
	 */	
	private Integer MA015;
	public static final int BZ = 0;
	public static final int WBZ = 1;
	public static final String ZT0 = "0";//未通过
	public static final String ZT1 = "1";//未申报
	public static final String ZT2 = "2";//待审核
	public static final String ZT3 = "3";//通过
	
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
	public Double getMA007() {
		return MA007;
	}
	public void setMA007(Double mA007) {
		MA007 = mA007;
	}
	public String getMA008() {
		return MA008;
	}
	public void setMA008(String mA008) {
		MA008 = mA008;
	}
	public String getMA009() {
		return MA009;
	}
	public void setMA009(String mA009) {
		MA009 = mA009;
	}
	public Date getMA010() {
		return MA010;
	}
	public void setMA010(Date mA010) {
		MA010 = mA010;
	}
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
	public Integer getMA015() {
		return MA015;
	}
	public void setMA015(Integer mA015) {
		MA015 = mA015;
	}

}
