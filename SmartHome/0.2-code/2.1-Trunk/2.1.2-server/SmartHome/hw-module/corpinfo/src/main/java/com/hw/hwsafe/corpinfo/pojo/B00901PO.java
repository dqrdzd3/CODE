/**
 * 文件名：B001PO.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.pojo;

import java.util.Date;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B001PO
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:04:19
 * 修改人：
 * 修改时间：2012-6-11 下午6:04:19
 * 修改备注：
 * @version 
 * 
 */
public class B00901PO {
	/**
	 * 唯一标志
	 */
	private String MA001;
	/**
	 * 危险源id
	 */
	private String MA002;
	/**
	 * 图片名称
	 */
	private String MA003;
	/**
	 * 图片类型
	 */
	private Integer MA004;
	/**
	 * 图片表述
	 */
	private String MA005;
	/**
	 * 图片文件
	 */
	private String MA006;
	/**
	 * 上传时间
	 */	
	private Date MA007;	
	/**
	 * 备注
	 */
	private String MA008;
	/**
	 * 图片别名-程序自定义名称,防止不同危险源同名图片重复
	 */	
	private String MA009;
	
	
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
	public Integer getMA004() {
		return MA004;
	}
	public void setMA004(Integer mA004) {
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
	public String getMA009() {
		return MA009;
	}
	public void setMA009(String mA009) {
		MA009 = mA009;
	}
	
}
