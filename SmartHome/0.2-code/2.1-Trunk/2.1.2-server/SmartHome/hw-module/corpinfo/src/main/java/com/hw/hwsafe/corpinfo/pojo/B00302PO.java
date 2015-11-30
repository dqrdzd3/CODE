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
 * 类名称：B00302PO
 * 类描述：
 * 创建人：王勇
 * 创建时间：2012-11-26 下午3:04:53
 * 修改人：
 * 修改时间：2012-11-26 下午3:04:53
 * 修改备注：
 * @version 
 * 
 */
public class B00302PO {

	/**
	 * 唯一标志
	 */
	private String MA001;
	/**
	 * 人员编号
	 */
	private String MA002;
	/**
	 * 证书编号
	 */
	private String MA003;
	/**
	 * 证书名称
	 */	
	private String MA004;	
	/**
	 * 发证部门
	 */
	private String MA005;
	/**
	 * 发证日期
	 */	
	private Date MA006;
	/**
	 * 有效日期
	 */
	private Date MA007;
	/**
	 * 填表人
	 */	
	private String MA008;
	/**
	 *  填表时间
	 */
	private Date MA009;
	/**
	 * 更新时间
	 */	
	private Date MA010;
	/**
	 * 状态
	 */
	private String MA011;
	
	
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
	 @JSON(format="yyyy-MM-dd")
	public Date getMA006() {
		return MA006;
	}
	public void setMA006(Date mA006) {
		MA006 = mA006;
	}
	 @JSON(format="yyyy-MM-dd")
	public Date getMA007() {
		return MA007;
	}
	public void setMA007(Date mA007) {
		MA007 = mA007;
	}
	public String getMA008() {
		return MA008;
	}
	public void setMA008(String mA008) {
		MA008 = mA008;
	}
	public Date getMA009() {
		return MA009;
	}
	public void setMA009(Date mA009) {
		MA009 = mA009;
	}
	public Date getMA010() {
		return MA010;
	}
	public void setMA010(Date mA010) {
		MA010 = mA010;
	}
	public String getMA011() {
		return MA011;
	}
	public void setMA011(String mA011) {
		MA011 = mA011;
	}
	
	
}
