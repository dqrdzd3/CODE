package com.hw.smarthome.daq.po;

import java.io.Serializable;
import java.util.Arrays;

public class FileInfo implements Serializable {
	private static final long serialVersionUID = -2638549502597348614L;
	/** 设备类型编号 */
	private String deviceType;
	/** 硬件版本 */
	private String hwVersion;
	/** 软件版本 */
	private String swVersion;
	/** 文件名 */
	private String fileName;
	private byte[] content;
	/** 总文件大小 */
	private long fileSize;
	/** 校验 */
	private int dataCheck;

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

	public int getDataCheck() {
		return dataCheck;
	}

	public void setDataCheck(int dataCheck) {
		this.dataCheck = dataCheck;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "FileInfo [deviceType=" + deviceType
				+ ", hwVersion=" + hwVersion + ", swVersion="
				+ swVersion + ", fileName=" + fileName
				+ ", content=" + Arrays.toString(content)
				+ ", fileSize=" + fileSize + ", dataCheck="
				+ dataCheck + "]";
	}


}
