package com.hw.smarthome.ui.sensor.po;

import android.widget.RadioButton;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

/**
 * 传感器列表
 * 
 * @author 曾凡
 * @date 2014-4-6下午5:25:50
 */
public class SensorListView {

	/** 设备ID*/
	private TextView sensorId;
	/** 传感器名称 */
	private TextView sensorName;
	/** 开关状态 */
	private TextView isOnline;

	private BootstrapButton deleteButton;

	public TextView getSensorName() {
		return sensorName;
	}

	public void setSensorName(TextView sensorName) {
		this.sensorName = sensorName;
	}

	public TextView getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(TextView isOnline) {
		this.isOnline = isOnline;
	}

	public BootstrapButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(BootstrapButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public TextView getSensorId() {
		return sensorId;
	}

	public void setSensorId(TextView sensorId) {
		this.sensorId = sensorId;
	}

}
