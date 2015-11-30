package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;

/**
 * 燃气传感器明细
 * 
 * @author 曾凡
 * @time 2014年6月25日 下午12:19:04
 */
public class SensorGasDetail implements Serializable{
	private static final long serialVersionUID = 8933774119191857002L;
	/** 传感器唯一标识ID */
	private String sensorId;
	/** 用户定义的传感器名称 */
	private String name;
	/** 开关状态 开/关 */
	private String switchStatus;
	/** 一氧化碳 */
	private String co;
	/** 甲烷 */
	private String ch4;
	/** 传感器产生本条数据的创建日期 "yyyy-MM-dd HH:mm:ss" */
	private String createTime;

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getSwitchStatus() {
		return switchStatus;
	}

	public void setSwitchStatus(String switchStatus) {
		this.switchStatus = switchStatus;
	}

	public String getCo() {
		return (co == null || "".equals(co)) ? "0" : co;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public String getCh4() {
		return (ch4 == null || "".equals(ch4)) ? "0" : ch4;
	}

	public void setCh4(String ch4) {
		this.ch4 = ch4;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SensorGasDetail [sensorId=" + sensorId
				+ ", name=" + name + ", switchStatus="
				+ switchStatus + ", co=" + co + ", ch4=" + ch4
				+ ", createTime=" + createTime + "]";
	}

}
