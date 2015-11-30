package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;

/**
 * @author 曾凡
 * @time 2015年4月20日 上午9:09:24
 */
public class CtrlContent implements Serializable{
	private static final long serialVersionUID = -9142520442086673436L;
	/**
	 * 开关类型 TYPE_NONE = 0x00, //无连接 TYPE_LEMP = 0x01, //灯 TYPE_FAN = 0x02, //风扇
	 * TYPE_HUMIDIFIER = 0x03, //加湿器 TYPE_AIRPURIFIER = 0x04, //空气净化器
	 * TYPE_AIRCONDITIONER = 0x05, //空调
	 */
	private String switchType;
	/**
	 * 开关状态 STATUS_CLOSE =0x00, STATUS_OPEN =0x01,
	 * */
	private String switchState;

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

}

