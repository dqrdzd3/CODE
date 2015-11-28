package com.hw.smarthome.daq.po;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.Gson;
import com.hw.smarthome.daq.po.base.DataItem;

public class DataItemAFN09FN01 extends DataItem implements
		Serializable {

	private static final long serialVersionUID = 4837911393292153476L;
	/** 设备型号 */
	private String deviceType;
	/** 序列号 */
	private String serialNum;
	/** 设备ID号 */
	private String deviceID;
	/** 硬件版本 */
	private String hardVer;
	/** 软件版本 */
	private String softVer;
	/** 生产日期 */
	private Date productDate;

	@Override
	public String getWsJson(String sensorId) {
		return new Gson().toJson(this);
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getHardVer() {
		return hardVer;
	}

	public void setHardVer(String hardVer) {
		this.hardVer = hardVer;
	}

	public String getSoftVer() {
		return softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceID == null) ? 0 : deviceID.hashCode());
		result = prime
				* result
				+ ((deviceType == null) ? 0 : deviceType
						.hashCode());
		result = prime * result
				+ ((hardVer == null) ? 0 : hardVer.hashCode());
		result = prime
				* result
				+ ((productDate == null) ? 0 : productDate
						.hashCode());
		result = prime
				* result
				+ ((serialNum == null) ? 0 : serialNum
						.hashCode());
		result = prime * result
				+ ((softVer == null) ? 0 : softVer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataItemAFN09FN01 other = (DataItemAFN09FN01) obj;
		if (deviceID == null) {
			if (other.deviceID != null)
				return false;
		} else if (!deviceID.equals(other.deviceID))
			return false;
		if (deviceType == null) {
			if (other.deviceType != null)
				return false;
		} else if (!deviceType.equals(other.deviceType))
			return false;
		if (hardVer == null) {
			if (other.hardVer != null)
				return false;
		} else if (!hardVer.equals(other.hardVer))
			return false;
		if (productDate == null) {
			if (other.productDate != null)
				return false;
		} else if (!productDate.equals(other.productDate))
			return false;
		if (serialNum == null) {
			if (other.serialNum != null)
				return false;
		} else if (!serialNum.equals(other.serialNum))
			return false;
		if (softVer == null) {
			if (other.softVer != null)
				return false;
		} else if (!softVer.equals(other.softVer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataItemAFN09FN01 [deviceType=" + deviceType
				+ ", serialNum=" + serialNum + ", deviceID="
				+ deviceID + ", hardVer=" + hardVer
				+ ", softVer=" + softVer + ", productDate="
				+ productDate + "]";
	}

}
