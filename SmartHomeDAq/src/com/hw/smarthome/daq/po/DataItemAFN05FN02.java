package com.hw.smarthome.daq.po;

import java.io.Serializable;

import com.google.gson.Gson;
import com.hw.smarthome.daq.po.base.DataItemAFN05;

public class DataItemAFN05FN02 extends DataItemAFN05 implements Serializable {

	private static final long serialVersionUID = 1551687828162725312L;
	private int controlMode;

	public int getControlMode() {
		return controlMode;
	}

	public void setControlMode(int controlMode) {
		this.controlMode = controlMode;
	}

	@Override
	public String getWsJson(String sensorId) {
		return new Gson().toJson(this);
	}

}
