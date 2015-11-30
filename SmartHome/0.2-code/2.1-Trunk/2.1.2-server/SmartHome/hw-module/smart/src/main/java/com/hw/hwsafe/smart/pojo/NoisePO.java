package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 噪音
 * @author 曾凡
 * @time 2015年8月27日 上午10:15:10
 */
public class NoisePO implements Serializable {
	private static final long serialVersionUID = 5189148149060834025L;
	private String id;
	private String sensorId;
	private double dBValue;
	private Date createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSensorId() {
		return sensorId;
	}
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
	public double getdBValue() {
		return dBValue;
	}
	public void setdBValue(double dBValue) {
		this.dBValue = dBValue;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
