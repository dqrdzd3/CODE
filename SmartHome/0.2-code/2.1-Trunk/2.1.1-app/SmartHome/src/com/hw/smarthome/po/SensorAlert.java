package com.hw.smarthome.po;

import java.io.Serializable;

/**
 * 传感器报警（终端是否在线，设备是否连接正常）
 * 
 * @author 曾凡
 * @time 2014年6月25日 下午2:00:54
 */
public class SensorAlert implements Serializable {
	private static final long serialVersionUID = -5147930224908739976L;
	/** 传感器唯一标识ID */
	private String sensorId;

	private String name;
	/** 终端是否在线   可以在本机判断：如果获取到数据采集端明细则为true，默认为false*/
	private boolean isOnline = false;
	/** 报警状态：正常/不正常 */
	private String alertStatus;
	/** 报警字段类型：空气温度湿度二氧化碳甲烷一氧化碳等 */
	private String alertType;
	/** 报警时的具体指 */
	private String alertValue;
	/** 传感器报警时间 */
	private String creatTime;

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getAlertStatus() {
		return alertStatus;
	}

	public void setAlertStatus(String alertStatus) {
		this.alertStatus = alertStatus;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public String getAlertValue() {
		return alertValue;
	}

	public void setAlertValue(String alertValue) {
		this.alertValue = alertValue;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	@Override
	public String toString() {
		return "SensorAlert [sensorId=" + sensorId + ", name="
				+ name + ", isOnline=" + isOnline
				+ ", alertStatus=" + alertStatus
				+ ", alertType=" + alertType + ", alertValue="
				+ alertValue + ", creatTime=" + creatTime + "]";
	}

}
