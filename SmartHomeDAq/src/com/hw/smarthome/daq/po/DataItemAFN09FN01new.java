package com.hw.smarthome.daq.po;

import java.io.Serializable;

import com.google.gson.Gson;
import com.hw.smarthome.daq.po.base.DataItem;

public class DataItemAFN09FN01new extends DataItem implements
Serializable{

	private static final long serialVersionUID = 2110468259785609964L;
	
	/** 单元标识 */
	private String UnitMark;
	/** 设备型号 */
	private String DeviceType;
	/** 序列号 */
	private String SerialNum;
	/** 设备ID号 */
	private String DeviceID;
	/** 硬件版本 */
	private String HardVer;
	/** 软件版本 */
	private String SoftVer;
	/** 生产日期 */
	private String ProductDate;
	/** 时间标签 */
	private String Date;
	
	@Override
	public String getWsJson(String sensorId) {
		return new Gson().toJson(this);
	}

	public String getUnitMark() {
		return UnitMark;
	}

	public void setUnitMark(String unitMark) {
		UnitMark = unitMark;
	}

	public String getDeviceType() {
		return DeviceType;
	}

	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}

	public String getSerialNum() {
		return SerialNum;
	}

	public void setSerialNum(String serialNum) {
		SerialNum = serialNum;
	}

	public String getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
	}

	public String getHardVer() {
		return HardVer;
	}

	public void setHardVer(String hardVer) {
		HardVer = hardVer;
	}

	public String getSoftVer() {
		return SoftVer;
	}

	public void setSoftVer(String softVer) {
		SoftVer = softVer;
	}

	public String getProductDate() {
		return ProductDate;
	}

	public void setProductDate(String productDate) {
		ProductDate = productDate;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Date == null) ? 0 : Date.hashCode());
		result = prime * result + ((DeviceID == null) ? 0 : DeviceID.hashCode());
		result = prime * result + ((DeviceType == null) ? 0 : DeviceType.hashCode());
		result = prime * result + ((HardVer == null) ? 0 : HardVer.hashCode());
		result = prime * result + ((ProductDate == null) ? 0 : ProductDate.hashCode());
		result = prime * result + ((SerialNum == null) ? 0 : SerialNum.hashCode());
		result = prime * result + ((SoftVer == null) ? 0 : SoftVer.hashCode());
		result = prime * result + ((UnitMark == null) ? 0 : UnitMark.hashCode());
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
		DataItemAFN09FN01new other = (DataItemAFN09FN01new) obj;
		if (Date == null) {
			if (other.Date != null)
				return false;
		} else if (!Date.equals(other.Date))
			return false;
		if (DeviceID == null) {
			if (other.DeviceID != null)
				return false;
		} else if (!DeviceID.equals(other.DeviceID))
			return false;
		if (DeviceType == null) {
			if (other.DeviceType != null)
				return false;
		} else if (!DeviceType.equals(other.DeviceType))
			return false;
		if (HardVer == null) {
			if (other.HardVer != null)
				return false;
		} else if (!HardVer.equals(other.HardVer))
			return false;
		if (ProductDate == null) {
			if (other.ProductDate != null)
				return false;
		} else if (!ProductDate.equals(other.ProductDate))
			return false;
		if (SerialNum == null) {
			if (other.SerialNum != null)
				return false;
		} else if (!SerialNum.equals(other.SerialNum))
			return false;
		if (SoftVer == null) {
			if (other.SoftVer != null)
				return false;
		} else if (!SoftVer.equals(other.SoftVer))
			return false;
		if (UnitMark == null) {
			if (other.UnitMark != null)
				return false;
		} else if (!UnitMark.equals(other.UnitMark))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataItemAFN09FN01new [UnitMark=" + UnitMark + ", DeviceType="
				+ DeviceType + ", SerialNum=" + SerialNum + ", DeviceID=" + DeviceID + ", HardVer=" + HardVer
				+ ", SoftVer=" + SoftVer + ", ProductDate=" + ProductDate + ", Date=" + Date + "]";
	}

	
}
