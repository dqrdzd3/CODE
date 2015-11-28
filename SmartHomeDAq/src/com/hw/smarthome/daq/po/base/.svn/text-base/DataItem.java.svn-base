package com.hw.smarthome.daq.po.base;

import java.io.Serializable;

/**
 * 获取数据项的Json
 * 
 * @author 曾凡
 * @time 2015年1月4日 下午5:21:24
 */
public abstract class DataItem implements Serializable {
	private static final long serialVersionUID = 7625678579170339002L;
	private byte fn;

	public abstract String getWsJson(String sensorId);

	public byte getFn() {
		return fn;
	}

	public void setFn(byte fn) {
		this.fn = fn;
	}

}
