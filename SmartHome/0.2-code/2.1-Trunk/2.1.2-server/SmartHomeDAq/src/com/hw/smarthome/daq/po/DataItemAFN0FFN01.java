package com.hw.smarthome.daq.po;

import java.io.Serializable;
import java.util.Arrays;

import com.google.gson.Gson;
import com.hw.smarthome.daq.po.base.DataItem;

public class DataItemAFN0FFN01 extends DataItem implements
		Serializable {

	private static final long serialVersionUID = 1649127881909447028L;
	/** 设备类型编号 */
	private String deviceType;
	/** 硬件版本 */
	private String hwVersion;
	/** 软件版本 */
	private String swVersion;
	/** 传输类型 */
	private String transDir;
	/** 传输状态 */
	private String TransState;
	/** 文件名 */
	private String fileName;
	/** 总文件大小 */
	private long fileSize;
	/** 包偏移地址 */
	private long fileOffset;
	/** 包长度 */
	private long dataLen;
	/** 校验 */
	private String dataCheck;

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	public String getWsJson(String sensorId) {
		return new Gson().toJson(this);
	}

	public String getHwVersion() {
		return hwVersion;
	}

	public void setHwVersion(String hwVersion) {
		this.hwVersion = hwVersion;
	}

	public String getSwVersion() {
		return swVersion;
	}

	public void setSwVersion(String swVersion) {
		this.swVersion = swVersion;
	}

	public String getTransDir() {
		return transDir;
	}

	public void setTransDir(String transDir) {
		this.transDir = transDir;
	}

	public String getTransState() {
		return TransState;
	}

	public void setTransState(String transState) {
		TransState = transState;
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

	public long getFileOffset() {
		return fileOffset;
	}

	public void setFileOffset(long fileOffset) {
		this.fileOffset = fileOffset;
	}

	public long getDataLen() {
		return dataLen;
	}

	public void setDataLen(long dataLen) {
		this.dataLen = dataLen;
	}

	public String getDataCheck() {
		return dataCheck;
	}

	public void setDataCheck(String dataCheck) {
		this.dataCheck = dataCheck;
	}

	@Override
	public String toString() {
		return "DataItemAFN0FFN01 [deviceType=" + deviceType
				+ ", hwVersion=" + hwVersion + ", swVersion="
				+ swVersion + ", transDir=" + transDir
				+ ", TransState=" + TransState + ", fileName="
				+ fileName + ", fileSize=" + fileSize
				+ ", fileOffset=" + fileOffset + ", dataLen="
				+ dataLen + ", dataCheck=" + dataCheck + "]";
	}

}
