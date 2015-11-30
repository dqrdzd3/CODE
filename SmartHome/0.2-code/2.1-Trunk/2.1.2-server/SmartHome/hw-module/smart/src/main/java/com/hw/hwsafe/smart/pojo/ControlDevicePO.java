package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;
import java.sql.Date;

public class ControlDevicePO implements Serializable{

	private static final long serialVersionUID = -1372518743831910466L;
	//UUID
	private String ma001;
	//用户ID
	private String ma002;
	//设备标识   5*******
	private String ma003;
	//被控设备标识(MAC 等唯一信息)
	private String ma004;
	//设备类型  空调等
	private String ma005;
	//厂商
	private String ma006;
	//硬件版本
	private String ma007;
	//软件版本
	private String ma008;
	//token
	private String ma009;
	//创建日期
	private Date ma010;
	//备用
	private String ma011;     //关联的传感器类型：如温度   湿度等 详见SensorConstant.java
	private String ma012;    //设备别名
	private String ma013;
	private String ma014;
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
	public String getMa004() {
		return ma004;
	}
	public void setMa004(String ma004) {
		this.ma004 = ma004;
	}
	public String getMa005() {
		return ma005;
	}
	public void setMa005(String ma005) {
		this.ma005 = ma005;
	}
	public String getMa006() {
		return ma006;
	}
	public void setMa006(String ma006) {
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
	public Date getMa010() {
		return ma010;
	}
	public void setMa010(Date ma010) {
		this.ma010 = ma010;
	}
	public String getMa011() {
		return ma011;
	}
	public void setMa011(String ma011) {
		this.ma011 = ma011;
	}
	public String getMa012() {
		return ma012;
	}
	public void setMa012(String ma012) {
		this.ma012 = ma012;
	}
	public String getMa013() {
		return ma013;
	}
	public void setMa013(String ma013) {
		this.ma013 = ma013;
	}
	public String getMa014() {
		return ma014;
	}
	public void setMa014(String ma014) {
		this.ma014 = ma014;
	}
	
}
