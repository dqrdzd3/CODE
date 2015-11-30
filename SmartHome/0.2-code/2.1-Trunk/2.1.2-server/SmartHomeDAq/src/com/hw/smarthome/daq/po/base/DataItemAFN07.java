package com.hw.smarthome.daq.po.base;

public abstract class DataItemAFN07 extends DataItem {

	private static final long serialVersionUID = 6911408626179732726L;
	protected String sensorId;

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

}
