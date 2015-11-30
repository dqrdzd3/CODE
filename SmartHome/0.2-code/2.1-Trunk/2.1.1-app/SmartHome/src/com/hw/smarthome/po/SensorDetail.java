package com.hw.smarthome.po;

import java.io.Serializable;

/**
 * @author 曾凡
 * @time 2014年6月25日 下午12:41:11
 */
public class SensorDetail implements Serializable {
	private static final long serialVersionUID = -1777275064855820473L;
	private String sensorId;
	private String name;

	private String localHardVersion;
	private String localSoftVersion;
	private String remoteHardVersion;
	private String remoteSoftVersion;

	private SensorAirDetail air;
	private SensorGasDetail gas;
	private SensorCtrlDetail ctrl;
	private SensorAlert alert;

	/**
	 * 根据传感器报警去获取终端是否在线的信息 <br>
	 * 可以在本机判断：如果获取到数据采集端明细则为true，默认为false
	 */
	public boolean isOnline() {
		return alert != null ? alert.isOnline() : false;
	}

	/** 在view中 显示传感器名称 */
	private String viewName;

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	/* 分享内容 */
	private String shareContent;

	public String getShareContent() {
		return shareContent;
	}

	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}

	public String getName() {
		if (name == null || "".equals(name)) {
			if (air != null&&air.getSensorId()!=null&&!"".equals(air.getSensorId())) {
				return air.getName();
			} else if (gas != null&&gas.getSensorId()!=null&&!"".equals(gas.getSensorId())) {
				return gas.getName();
			} else if (ctrl != null&&ctrl.getCtrlId()!=null&&!"".equals(ctrl.getCtrlId())) {
				return ctrl.getName();
			} else {
				return null;
			}
		}
		return this.name;
	}

	public SensorAirDetail getAir() {
		return air;
	}

	public void setAir(SensorAirDetail air) {
		this.air = air;
	}

	public SensorGasDetail getGas() {
		return gas;
	}

	public void setGas(SensorGasDetail gas) {
		this.gas = gas;
	}

	public String getSensorId() {
		if (sensorId == null || "".equals(sensorId)) {
			if (air != null) {
				return air.getSensorId();
			} else if (gas != null) {
				return gas.getSensorId();
			} else if (ctrl != null) {
				return ctrl.getDeviceId();
			} else {
				return null;
			}
		}
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public SensorAlert getAlert() {
		return alert;
	}

	public void setAlert(SensorAlert alert) {
		this.alert = alert;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalHardVersion() {
		return localHardVersion;
	}

	public void setLocalHardVersion(String localHardVersion) {
		this.localHardVersion = localHardVersion;
	}

	public String getLocalSoftVersion() {
		return localSoftVersion;
	}

	public void setLocalSoftVersion(String localSoftVersion) {
		this.localSoftVersion = localSoftVersion;
	}

	public String getRemoteHardVersion() {
		return remoteHardVersion;
	}

	public void setRemoteHardVersion(String remoteHardVersion) {
		this.remoteHardVersion = remoteHardVersion;
	}

	public String getRemoteSoftVersion() {
		return remoteSoftVersion;
	}

	public void setRemoteSoftVersion(String remoteSoftVersion) {
		this.remoteSoftVersion = remoteSoftVersion;
	}

	public SensorCtrlDetail getCtrl() {
		return ctrl;
	}

	public void setCtrl(SensorCtrlDetail ctrl) {
		this.ctrl = ctrl;
	}

	@Override
	public String toString() {
		return "SensorDetail [sensorId=" + sensorId + ", name="
				+ name + ", localHardVersion="
				+ localHardVersion + ", localSoftVersion="
				+ localSoftVersion + ", remoteHardVersion="
				+ remoteHardVersion + ", remoteSoftVersion="
				+ remoteSoftVersion + ", air=" + air + ", gas="
				+ gas + ", ctrl=" + ctrl + ", alert=" + alert
				+ ", viewName=" + viewName + ", shareContent="
				+ shareContent + "]";
	}

}
