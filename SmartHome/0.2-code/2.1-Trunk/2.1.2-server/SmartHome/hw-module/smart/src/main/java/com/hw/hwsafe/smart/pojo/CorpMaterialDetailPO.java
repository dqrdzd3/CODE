package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;
import java.util.Date;

public class CorpMaterialDetailPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4568394674011774686L;

	private String ma001;    //主键
	private String ma002;   //耗材id
	private String ma003;    //设备id
	private Date ma004;      //使用开始日期
	private Date ma005;      //使用截至日期
	private int ma006;       //状态     1 ：使用中    0：废弃  已更换     2：超期
	private String ma007;    //corpid
	private String ma008;    //已用  CORP_MATERIAL 材料名称
	private String ma009;   //已用  CORP_CRM 的手机号
	private String ma010;
	private String ma011;
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
	public Date getMa004() {
		return ma004;
	}
	public void setMa004(Date ma004) {
		this.ma004 = ma004;
	}
	public Date getMa005() {
		return ma005;
	}
	public void setMa005(Date ma005) {
		this.ma005 = ma005;
	}
	public int getMa006() {
		return ma006;
	}
	public void setMa006(int ma006) {
		this.ma006 = ma006;
	}
	public String getMa007() {
		return ma007;
	}
	public void setMa007(String ma007) {
		this.ma007 = ma007;
	}
	public String getMa008() {
		return ma008;
	}
	public void setMa008(String ma008) {
		this.ma008 = ma008;
	}
	public String getMa009() {
		return ma009;
	}
	public void setMa009(String ma009) {
		this.ma009 = ma009;
	}
	public String getMa010() {
		return ma010;
	}
	public void setMa010(String ma010) {
		this.ma010 = ma010;
	}
	public String getMa011() {
		return ma011;
	}
	public void setMa011(String ma011) {
		this.ma011 = ma011;
	}
	
}
