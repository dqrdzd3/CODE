package com.hw.hwsafe.sms.czd.po;

/**
 * 项目名称：hw-sms
 * 类名称：SMSCatPO
 * 类描述：短信猫PO类，存储和传递短信猫的网关，端口，pin等信息
 * 创建人：陈浙东
 * 创建时间：2013-4-09
 *
 */
public class SMSCatPO {
	private String gateway; // 网关
	private String port; // 端口
	private String manufacturer; // 制造商
	private String pin; // pin码
	private int baudrate;
	private boolean inBound;
	private boolean outBound;
	private String mode;

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public int getBaudrate() {
		return baudrate;
	}

	public void setBaudrate(int baudrate) {
		this.baudrate = baudrate;
	}

	public boolean isInBound() {
		return inBound;
	}

	public void setInBound(boolean inBound) {
		this.inBound = inBound;
	}

	public boolean isOutBound() {
		return outBound;
	}

	public void setOutBound(boolean outBound) {
		this.outBound = outBound;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
