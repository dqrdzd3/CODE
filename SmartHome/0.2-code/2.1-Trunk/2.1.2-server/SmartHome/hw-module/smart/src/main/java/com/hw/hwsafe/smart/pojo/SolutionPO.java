package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;
import java.util.Date;

public class SolutionPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1029870531186859558L;

	private String ma001;
	/** WG0XXX */
	private String ma002;
	/** 检测评估（CHK）、解决方案(SOLU)、综合结论（SUM） */
	private int ma003;
	/** 1-3 正常-中-高（仅作为评估的参数，不作为页面的显示） */
	private int ma004;
	private int ma005;
	private int ma006;
	/** 优、良、重度污染 */
	private int ma007;
	/** 场景 */
	private int ma008;
	/** 文档内容 */
	private byte[] ma009;
	/** 最后一次更新时间 */
	private Date ma010;
	
	//备用
	private String ma011;
	private String ma012;
	private String ma013;
	private String ma014;
	private String ma015;
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
	public int getMa003() {
		return ma003;
	}
	public void setMa003(int ma003) {
		this.ma003 = ma003;
	}
	public int getMa004() {
		return ma004;
	}
	public void setMa004(int ma004) {
		this.ma004 = ma004;
	}
	public int getMa005() {
		return ma005;
	}
	public void setMa005(int ma005) {
		this.ma005 = ma005;
	}
	public int getMa006() {
		return ma006;
	}
	public void setMa006(int ma006) {
		this.ma006 = ma006;
	}
	public int getMa007() {
		return ma007;
	}
	public void setMa007(int ma007) {
		this.ma007 = ma007;
	}
	public int getMa008() {
		return ma008;
	}
	public void setMa008(int ma008) {
		this.ma008 = ma008;
	}
	public byte[] getMa009() {
		return ma009;
	}
	public void setMa009(byte[] ma009) {
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
	public String getMa015() {
		return ma015;
	}
	public void setMa015(String ma015) {
		this.ma015 = ma015;
	}
	
	
	
}
