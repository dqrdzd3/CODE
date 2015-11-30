/**
 * 文件名：TregulationPO.java
 *
 * 版本信息：1.0
 * 日期：2012-6-6
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.hw.hwsafe.attachment.pojo.C004PO;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：TregulationPO
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-6 下午3:21:23
 * 修改人：
 * 修改时间：2012-6-6 下午3:21:23
 * 修改备注：
 * @version 
 * 
 */
public class TregulationPO implements Serializable{
	
	private static final long serialVersionUID = 8333171058566995957L;

	/**
	 * 主键Id标识
	 */
	private String MA001;
	

	/**
	 * 企业ID
	 */
	private String MA002;
	/**
	 * 制度编号
	 */
	private String MA003;
	/**
	 * 制度名称
	 */
	private String MA004;
	/**
	 * 内容摘要
	 */
	private String MA005;
	/**
	 * 编制单位
	 */
	private String MA006;
	/**
	 * 制度类型
	 */
	private String MA007;
	/**
	 * 编制日期
	 */
	private Date MA008;	
	/**
	 * 编制人
	 */
	private String MA009;		
	/**
	 * 生效日期
	 */
	private Date MA010;	
		
	/**
	 * 备注
	 */
	private String MA011;	
	/**
	 * 上传文件名称
	 */
	private String MA012;	
	/**
	 * 状态
	 */
	private Integer MA013;
	/**
	 * 填写日期
	 */
	private Date MA014;	
	/**
	 * 修改日期
	 */
	private Date MA015;
	/**
	 * 上传文件路径
	 */
	private String MA016;
	/**
	 * 填表人
	 */
	private String MA017;
	
	/**
	 * 附件列表的id数组
	 */
	private String[] c004ids;
	
	private List<C004PO> c004poList;
	
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
	public Integer getMA013() {
		return MA013;
	}
	public void setMA013(Integer mA013) {
		MA013 = mA013;
	}
	public Date getMA014() {
		return MA014;
	}
	public void setMA014(Date mA014) {
		MA014 = mA014;
	}
	public Date getMA015() {
		return MA015;
	}
	public void setMA015(Date mA015) {
		MA015 = mA015;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
