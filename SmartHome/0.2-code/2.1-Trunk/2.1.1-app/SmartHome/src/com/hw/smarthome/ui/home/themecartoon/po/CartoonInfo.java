package com.hw.smarthome.ui.home.themecartoon.po;

/**
 * 卡通的图片和语言
 * 
 * @author 曾凡
 * @time 2014年7月31日 下午4:14:21
 */
public class CartoonInfo {
	/** 图片id */
	private int imageId;
	/** 开通要说的话 */
	private String talkText;
	/** 单个传感器的名称 */
	private String sensorName;
	/** 传感器的内容 */
	private String sensorValue;

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getTalkText() {
		return talkText;
	}

	public void setTalkText(String talkText) {
		this.talkText = talkText;
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public String getSensorValue() {
		return sensorValue;
	}

	public void setSensorValue(String sensorValue) {
		this.sensorValue = sensorValue;
	}

	@Override
	public String toString() {
		return "CartoonInfo [imageId=" + imageId + ", talkText="
				+ talkText + ", sensorName=" + sensorName
				+ ", sensorValue=" + sensorValue + "]";
	}

}
