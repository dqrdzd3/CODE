package com.hw.smarthome.ui.home.po;

import java.io.Serializable;

/**
 * @author 曾凡
 * @time 2014年7月4日 下午12:13:23
 */
public class HomeAlertHistory implements Serializable {
	private static final long serialVersionUID = 7431581197301100462L;

	/** 传感器唯一标识ID */
	private String sensorId;

	private String name;
	/** 报警状态：正常/不正常 */
	private String[] alertStatus;
	/** 报警字段类型：空气温度湿度二氧化碳甲烷一氧化碳等 */
	private String[] alertType;
	/** 报警时的具体指 */
	private String[] alertValue;
	/** 传感器报警时间 */
	private String[] creatTime;

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getAlertStatus() {
		return alertStatus;
	}

	public void setAlertStatus(String[] alertStatus) {
		this.alertStatus = alertStatus;
	}

	public String[] getAlertType() {
		return alertType;
	}

	public void setAlertType(String[] alertType) {
		this.alertType = alertType;
	}

	public String[] getAlertValue() {
		return alertValue;
	}

	public void setAlertValue(String[] alertValue) {
		this.alertValue = alertValue;
	}

	public String[] getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String[] creatTime) {
		this.creatTime = creatTime;
	}

}
