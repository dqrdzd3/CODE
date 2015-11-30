/**
 * 文件名：B005PO.java
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

import org.apache.struts2.json.annotations.JSON;

import com.hw.hwsafe.attachment.pojo.C004PO;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B005PO
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:05:24
 * 修改人：
 * 修改时间：2012-6-11 下午6:05:24
 * 修改备注：
 * @version 
 * 
 */
public class B005PO {

	
	/**
	 * 主键
	 */
	private String MA001;
	/**
	 * 执照编号
	 */
	private String MA002;
	/**
	 * 企业ID
	 */
	private String MA003;
	/**
	 * 执照名称
	 */
	private String MA004;
	/**
	 * 发证单位
	 */
	private String MA005;
	/**
	 * 发证时间
	 */
	private Date MA006;
	/**
	 * 有效期
	 */	
	private Date MA007;	
	/**
	 * 图片类型
	 */
	private Integer MA008;
	/**
	 * 备注
	 */	
	private String MA009;
	/**
	 * 状态
	 */
	private Integer MA010;
	/**
	 * 图片名称
	 */	
	private String MA011;
	/**
	 * 临时文件名
	 */
	private String MA012;
	
	/**
	 * 填表人
	 */	
	private String MA013;
	/**
	 * 填表日期
	 */
	private Date MA014;
	/**
	 * 附件列表的id数组
	 */
	private String[] c004ids;
	
	private List<C004PO> c004poList;
	/**
	 * 初始状态为有效
	 */
	public static final Integer YX=1;
	/**
	 * 状态为无效
	 */
	public static final Integer WX=1;
	
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
	public Integer getMA010() {
		return MA010;
	}
	public void setMA010(Integer mA010) {
		MA010 = mA010;
	}
	public String getMA011() {
		return MA011;
	}
	public void setMA011(String mA011) {
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
	public Date getMA014() {
		return MA014;
	}
	public void setMA014(Date mA014) {
		MA014 = mA014;
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
