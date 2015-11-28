package com.hw.smarthome.daq.po;

import java.io.Serializable;

import com.google.gson.Gson;
import com.hw.smarthome.daq.po.base.DataItemAFN05;

public class DataItemAFN05FN01 extends DataItemAFN05 implements
		Serializable {

	private static final long serialVersionUID = 2931091784159858450L;
	/** 设备类型编号 */
	private String deviceType;
	/** 设备类型 */
	private String deviceTypeName;
	/** 硬件版本 */
	private String hardVer;
	/** 软件版本 */
	private String softVer;
	/** 文件名 */
	private String fileName;
	/** 文件大小 */
	private long fileSize;
	private int checkSum;

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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(int checkSum) {
		this.checkSum = checkSum;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + checkSum;
		result = prime
				* result
				+ ((deviceType == null) ? 0 : deviceType
						.hashCode());
		result = prime
				* result
				+ ((deviceTypeName == null) ? 0 : deviceTypeName
						.hashCode());
		result = prime * result
				+ ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result
				+ (int) (fileSize ^ (fileSize >>> 32));
		result = prime * result
				+ ((hardVer == null) ? 0 : hardVer.hashCode());
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
		DataItemAFN05FN01 other = (DataItemAFN05FN01) obj;
		if (checkSum != other.checkSum)
			return false;
		if (deviceType == null) {
			if (other.deviceType != null)
				return false;
		} else if (!deviceType.equals(other.deviceType))
			return false;
		if (deviceTypeName == null) {
			if (other.deviceTypeName != null)
				return false;
		} else if (!deviceTypeName.equals(other.deviceTypeName))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (fileSize != other.fileSize)
			return false;
		if (hardVer == null) {
			if (other.hardVer != null)
				return false;
		} else if (!hardVer.equals(other.hardVer))
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
		return "DataItemAFN05FN01 [deviceType=" + deviceType
				+ ", deviceTypeName=" + deviceTypeName
				+ ", hardVer=" + hardVer + ", softVer="
				+ softVer + ", fileName=" + fileName
				+ ", fileSize=" + fileSize + ", checkSum="
				+ checkSum + "]";
	}

}
