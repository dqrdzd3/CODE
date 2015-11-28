package com.hw.smarthome.daq.po;

import java.io.Serializable;

import com.google.gson.Gson;
import com.hw.smarthome.daq.po.base.DataItemAFN07;

public class DataItemAFN07FN02new extends DataItemAFN07 implements Serializable {

	private static final long serialVersionUID = -7281328175247627857L;


	/**控制量数量*/
	private int controlNum;
	/** 控制对象 */
	private int controlObject;
	/** 控制量类型 */
	private String controlType;
	/** 控制量范围下界 */
	private int controlAmountBound;
	/** 控制量范围上界 */
	private int controlAmountLowBound;
	/**3bit 小数位*/
	private int pointer;
	/**5bit 单位 */
	private String unit;
	/**4byte 控制值*/
	private String controlValue;
	/**5byte 控制量*/
	private String controlAmount;
	
	public int getControlObject() {
		return controlObject;
	}

	public void setControlObject(int controlObject) {
		this.controlObject = controlObject;
	}

	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	public int getControlAmountBound() {
		return controlAmountBound;
	}

	public void setControlAmountBound(int controlAmountBound) {
		this.controlAmountBound = controlAmountBound;
	}

	public int getControlAmountLowBound() {
		return controlAmountLowBound;
	}

	public void setControlAmountLowBound(int controlAmountLowBound) {
		this.controlAmountLowBound = controlAmountLowBound;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getControlNum() {
		return controlNum;
	}

	public void setControlNum(int controlNum) {
		this.controlNum = controlNum;
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

	@Override
	public String getWsJson(String sensorId) {
		setSensorId(sensorId);
		return new Gson().toJson(this);
	}

	@Override
	public String toString() {
		return "DataItemAFN07FN02new [controlNum=" + controlNum + ", controlObject=" + controlObject + ", controlType="
				+ controlType + ", controlAmountBound=" + controlAmountBound + ", controlAmountLowBound="
				+ controlAmountLowBound + ", pointer=" + pointer + ", unit=" + unit + ", controlValue=" + controlValue
				+ ", controlAmount=" + controlAmount + "]";
	}

	
}
