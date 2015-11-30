package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;
import java.util.Map;

/**
 * @author 曾凡
 * @time 2015年4月20日 上午9:04:38
 */
public class SensorCtrlDetail implements Serializable{
	private static final long serialVersionUID = 2804365854430612195L;
	/** 关联的控制端设备（A1），可以空 */
	private String ctrlId;
	/** 区分设备的唯一标识，一般是地址 */
	private String deviceId;
	/** 别名 */
	private String name;
	/** 设备类型 */
	private String deviceType;
	private String token;
	/**
	 * 关联的A1传感器 见"SensorConstant.java"的"MEDIA_TYPE_*"常量
	 */
	private String linkSensor;
	/** 传感器产生本条数据的创建日期 "yyyy-MM-dd HH:mm:ss" */
	private String createTime;
	/** 被控制的参数内容:开关的序号和开关的内容 */
	private Map<String, CtrlContent> ctrlContent;

	public String getCtrlId() {
		return ctrlId;
	}

	public void setCtrlId(String ctrlId) {
		this.ctrlId = ctrlId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, CtrlContent> getCtrlContent() {
		return ctrlContent;
	}

	public void setCtrlContent(
			Map<String, CtrlContent> ctrlContent) {
		this.ctrlContent = ctrlContent;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLinkSensor() {
		return linkSensor;
	}

	public void setLinkSensor(String linkSensor) {
		this.linkSensor = linkSensor;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "SensorCtrlDetail [ctrlId=" + ctrlId
				+ ", deviceId=" + deviceId + ", name=" + name
				+ ", deviceType=" + deviceType + ", token="
				+ token + ", linkSensor=" + linkSensor
				+ ", createTime=" + createTime
				+ ", ctrlContent=" + ctrlContent + "]";
	}

}