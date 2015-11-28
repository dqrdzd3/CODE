package com.hw.smarthome.daq.po;

import java.io.Serializable;

import com.google.gson.Gson;
import com.hw.smarthome.daq.po.base.DataItemAFN07;

public class DataItemAFN07FN02 extends DataItemAFN07 implements
		Serializable {

	private static final long serialVersionUID = 5870820815858028529L;

	/** 开关数量 */
	private int switchAmount;
	/** 开关序号 */
	private int switchNum;
	/** 开关类型 
	 * TYPE_NONE             = 0x00, //无连接
	 * TYPE_LEMP             = 0x01, //灯
	 * TYPE_FAN              = 0x02, //风扇
	 * TYPE_HUMIDIFIER       = 0x03, //加湿器
	 * TYPE_AIRPURIFIER      = 0x04, //空气净化器
	 * TYPE_AIRCONDITIONER   = 0x05, //空调
	 */
	private String switchType;
	/** 开关状态  
	 * STATUS_CLOSE =0x00,
	 * STATUS_OPEN  =0x01,
	 * */
	private String switchState;
	/** 保留2字节 */
	private String reserve2;

	public int getSwitchAmount() {
		return switchAmount;
	}

	public void setSwitchAmount(int switchAmount) {
		this.switchAmount = switchAmount;
	}

	public int getSwitchNum() {
		return switchNum;
	}

	public void setSwitchNum(int switchNum) {
		this.switchNum = switchNum;
	}

	public String getSwitchType() {
		return switchType;
	}

	public void setSwitchType(String switchType) {
		this.switchType = switchType;
	}

	public String getSwitchState() {
		return switchState;
	}

	public void setSwitchState(String switchState) {
		this.switchState = switchState;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	@Override
	public String getWsJson(String sensorId) {
		return new Gson().toJson(this);
	}

	@Override
	public String toString() {
		return "DataItemAFN07FN02 [switchAmount=" + switchAmount
				+ ", switchNum=" + switchNum + ", switchType="
				+ switchType + ", switchState=" + switchState
				+ ", reserve2=" + reserve2 + "]";
	}

}
