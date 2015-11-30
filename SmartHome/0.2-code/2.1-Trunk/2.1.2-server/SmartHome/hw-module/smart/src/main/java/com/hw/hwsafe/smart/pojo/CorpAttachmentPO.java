package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;

public class CorpAttachmentPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1995857119683763694L;
	
	
	private String ma001;
	private byte[] ma002;    //内容
	private String ma003;     //base64 编码
	private String ma004;    //图片路径
	private int ma005;        //类型   1  图片     2 文件  等等
	private String ma006;
	private String ma007;
	private String ma008;
	public String getMa001() {
		return ma001;
	}
	public void setMa001(String ma001) {
		this.ma001 = ma001;
	}
	
	public byte[] getMa002() {
		return ma002;
	}
	public void setMa002(byte[] ma002) {
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
	public int getMa005() {
		return ma005;
	}
	public void setMa005(int ma005) {
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
	
	

}
