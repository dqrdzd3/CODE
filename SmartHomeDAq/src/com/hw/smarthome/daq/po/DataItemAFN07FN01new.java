package com.hw.smarthome.daq.po;

import java.io.Serializable;

import com.google.gson.Gson;
import com.hw.smarthome.daq.po.base.DataItemAFN07;

public class DataItemAFN07FN01new extends DataItemAFN07 implements
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
	/** 控制模式 */
	private int controlMode;
	/** 保留1byte */
	private String reserve1;

	
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

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getControlMode() {
		return controlMode;
	}

	public void setControlMode(int controlMode) {
		this.controlMode = controlMode;
	}

	@Override
	public String toString() {
		return "DataItemAFN07FN01new [deviceType=" + deviceType + ", hardVer=" + hardVer + ", softVer=" + softVer
				+ ", serialNum=" + serialNum + ", token=" + token + ", reserve6=" + reserve6 + ", switchAmount="
				+ switchAmount + ", portAmount=" + portAmount + ", controlMode=" + controlMode + ", reserve1="
				+ reserve1 + "]";
	}


}
