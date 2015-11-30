package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;
import java.util.Date;

public class BinUpdatePo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7696367414915692379L;
	private String sensorId;
	private String localHardVersion;       //本地硬件版本
	private String localSoftVersion;
	private String remoteHardVersion;       //远端硬件版本
	private String remoteSoftVersion;
	
	/** 总文件大小 */
	private long fileSize;
	/** 包偏移地址 */
	private long fileOffset;
	/** 包长度 */
	private long dataLen;
	/** 最近一次查询版本信息时间 */
	private Date date;
	
	
	
	
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

	
	
	public String getSensorId() {
		return sensorId;
	}
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "BinUpdatePo [sensorId=" + sensorId + ", localHardVersion="
				+ localHardVersion + ", localSoftVersion=" + localSoftVersion
				+ ", remoteHardVersion=" + remoteHardVersion
				+ ", remoteSoftVersion=" + remoteSoftVersion + ", date=" + date
				+ "]";
	}
	
	
}
