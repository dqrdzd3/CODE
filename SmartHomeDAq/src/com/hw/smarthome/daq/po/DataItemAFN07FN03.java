package com.hw.smarthome.daq.po;

import java.io.Serializable;

import com.google.gson.Gson;
import com.hw.smarthome.daq.po.base.DataItemAFN07;

public class DataItemAFN07FN03 extends DataItemAFN07 implements Serializable {

	private static final long serialVersionUID = -4925636326218401224L;
	
	/** 传感器数量*/
	private int sensorNum;
	/** 传感器类型 */
	private String sensorType;
	/**3bit 小数位*/
	private int pointer;	
	/**5bit 单位 */
	private String unit;
	/**4byte 控制值*/
	private String controlValue;
	/**5byte 控制量*/
	private String controlAmount;
	@Override
	public String getWsJson(String sensorId) {
		return new Gson().toJson(this);
	}

	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getSensorNum() {
		return sensorNum;
	}

	public void setSensorNum(int sensorNum) {
		this.sensorNum = sensorNum;
	}

	public int getPointer() {
		return pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}

	public String getControlValue() {
		return controlValue;
	}

	public void setControlValue(String controlValue) {
		this.controlValue = controlValue;
	}

	public String getControlAmount() {
		return controlAmount;
	}

	public void setControlAmount(String controlAmount) {
		this.controlAmount = controlAmount;
	}
	
}
