package com.hw.smarthome.ui.home.po;

import java.io.Serializable;
import java.util.List;

/**
 * @author 曾凡
 * @time 2014年7月4日 上午11:08:19
 */
public class HomeHistoryList implements Serializable{

	private static final long serialVersionUID = -2675044301227851976L;

	private List<HomeAirHistory> airSensors;

	private List<HomeGasHistory> gasSensors;

	private List<HomeAlertHistory> alerts;

	public List<HomeAirHistory> getAirSensors() {
		return airSensors;
	}

	public void setAirSensors(List<HomeAirHistory> airSensors) {
		this.airSensors = airSensors;
	}

	public List<HomeGasHistory> getGasSensors() {
		return gasSensors;
	}

	public void setGasSensors(List<HomeGasHistory> gasSensors) {
		this.gasSensors = gasSensors;
	}

	public List<HomeAlertHistory> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<HomeAlertHistory> alerts) {
		this.alerts = alerts;
	}

}
