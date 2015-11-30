package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;
import java.util.Date;

public class CorpRankPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 766818514976553199L;
	
	
	private String MA001;
	private String MA002;    //传感器类型
	private int MA003;     //平均值
	private int MA004;     //地理类别  1省    2市
	private String MA005;   //省市名称
	private int MA006;     //名次
	private String MA007;   //企业id
	private String MA008;
	private Date MA009;   //创建日期
	private String MA010;
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
	public int getMA003() {
		return MA003;
	}
	public void setMA003(int mA003) {
		MA003 = mA003;
	}
	public int getMA004() {
		return MA004;
	}
	public void setMA004(int mA004) {
		MA004 = mA004;
	}
	public String getMA005() {
		return MA005;
	}
	public void setMA005(String mA005) {
		MA005 = mA005;
	}
	public int getMA006() {
		return MA006;
	}
	public void setMA006(int mA006) {
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
	public Date getMA009() {
		return MA009;
	}
	public void setMA009(Date mA009) {
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
	
	

}
