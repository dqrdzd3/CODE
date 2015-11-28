package com.hw.smarthome.daq.po;

import java.io.Serializable;

import com.google.gson.Gson;
import com.hw.smarthome.daq.po.base.DataItemAFN10;

/**
 * IO口控制用
 * 
 * @author 曾凡
 * @time 2015年4月14日 下午2:12:27
 */
public class DataItemAFN10FN01 extends DataItemAFN10 implements
		Serializable {

	private static final long serialVersionUID = 7459575075379651031L;
	/** 控制令牌，用来防止重放攻击 */
	private String token;
	/** 开关数量 */
	private int switchAmount;
	/** 开关序号 */
	private int switchNum;
	/** 开关类型 */
	private String switchType;
	/** 开关状态 */
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
		setSensorId(sensorId);
		return new Gson().toJson(this);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "DataItemAFN10FN01 [token=" + token
				+ ", switchAmount=" + switchAmount
				+ ", switchNum=" + switchNum + ", switchType="
				+ switchType + ", switchState=" + switchState
				+ ", reserve2=" + reserve2 + "]";
	}

}
