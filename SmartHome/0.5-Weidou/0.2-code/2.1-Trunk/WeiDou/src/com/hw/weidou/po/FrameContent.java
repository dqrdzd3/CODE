package com.hw.weidou.po;


/**
 * 贯穿整个数据帧的实体类
 * 
 * @author 曾凡
 * @time 2014年10月11日 下午1:33:22
 */
public class FrameContent {
	private String strContent;
	private String[] strArray;
	private byte[] byteContent;
	private boolean isLegal;
	private String funcType;
	private String value;

	public String getStrContent() {
		return strContent;
	}

	public void setStrContent(String strContent) {
		this.strContent = strContent;
	}

	public byte[] getByteContent() {
		return byteContent;
	}

	public void setByteContent(byte[] byteContent) {
		this.byteContent = byteContent;
	}

	public boolean isLegal() {
		return isLegal;
	}

	public void setLegal(boolean isLegal) {
		this.isLegal = isLegal;
	}

	public String getFuncType() {
		return funcType;
	}

	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String[] getStrArray() {
		return strArray;
	}

	public void setStrArray(String[] strArray) {
		this.strArray = strArray;
	}

}
