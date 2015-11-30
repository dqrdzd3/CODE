package com.hw.smarthome.daq.po;

import java.io.Serializable;

import com.google.gson.Gson;
import com.hw.smarthome.daq.po.base.DataItemAFN07;

public class DataItemAFN07FN01 extends DataItemAFN07 implements
		Serializable {

	private static final long serialVersionUID = -8042771278696005426L;
	/**
	 * 设备类型 A代表空气电台,如:A101 C表示控制类设备,如:C101
	 */
	private String deviceType;
	/** 硬件版本 */
	private String hardVer;
	/** 软件版本 */
	private String softVer;
	/** 序列号(MAC) */
	private String serialNum;
	/** 控制令牌，用来防止重放攻击 */
	private String token;
	/** 保留6byte */
	private String reserve6;
	/** 开关数量 */
	private int switchAmount;
	/** 串口数量 */
	private int portAmount;
	/** 保留2byte */
	private String reserve2;

	@Override
	public String getWsJson(String sensorId) {
		setSensorId(sensorId);
		return new Gson().toJson(this);
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getHardVer() {
		return hardVer;
	}

	public void setHardVer(String hardVer) {
		this.hardVer = hardVer;
	}

	public String getSoftVer() {
		return softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getReserve6() {
		return reserve6;
	}

	public void setReserve6(String reserve6) {
		this.reserve6 = reserve6;
	}

	public int getSwitchAmount() {
		return switchAmount;
	}

	public void setSwitchAmount(int switchAmount) {
		this.switchAmount = switchAmount;
	}

	public int getPortAmount() {
		return portAmount;
	}

	public void setPortAmount(int portAmount) {
		this.portAmount = portAmount;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "DataItemAFN07FN01 [deviceType=" + deviceType
				+ ", hardVer=" + hardVer + ", softVer="
				+ softVer + ", serialNum=" + serialNum
				+ ", token=" + token + ", reserve6=" + reserve6
				+ ", switchAmount=" + switchAmount
				+ ", portAmount=" + portAmount + ", reserve2="
				+ reserve2 + "]";
	}

}
