package com.hw.smarthome.daq.po;

import java.io.Serializable;

import com.google.gson.Gson;
import com.hw.smarthome.daq.po.base.DataItem;

public class DataItemAFN0FFN01new extends DataItem implements
Serializable {

	private static final long serialVersionUID = 7066670032930663870L;

	@Override
	public String getWsJson(String sensorId) {
		return new Gson().toJson(this);
	}
	
	/** 单元数量 */
	private String UnitAmount;
	/** 单元标识 */
	private String UnitMark;
	/** 硬件版本 */
	private String HW_Version;
	/** 软件版本 */
	private String SW_Version;
	/** 传输类型 */
	private String TransDir;
	/** 保留 */
	private String Reserve;
	/** 文件名 */
	private String FileName;
	/** 总文件大小 */
	private String FileSize;
	/** 包偏移地址 */
	private String FileOffset;
	/** 包长度 */
	private String DataLen;
	/** 时间标签 */
	private String Date;

	
	
	public String getUnitAmount() {
		return UnitAmount;
	}
	public void setUnitAmount(String unitAmount) {
		UnitAmount = unitAmount;
	}
	public String getUnitMark() {
		return UnitMark;
	}
	public void setUnitMark(String unitMark) {
		UnitMark = unitMark;
	}
	public String getHW_Version() {
		return HW_Version;
	}
	public void setHW_Version(String hW_Version) {
		HW_Version = hW_Version;
	}
	public String getSW_Version() {
		return SW_Version;
	}
	public void setSW_Version(String sW_Version) {
		SW_Version = sW_Version;
	}
	public String getTransDir() {
		return TransDir;
	}
	public void setTransDir(String transDir) {
		TransDir = transDir;
	}
	public String getReserve() {
		return Reserve;
	}
	public void setReserve(String reserve) {
		Reserve = reserve;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public String getFileSize() {
		return FileSize;
	}
	public void setFileSize(String fileSize) {
		FileSize = fileSize;
	}
	public String getFileOffset() {
		return FileOffset;
	}
	public void setFileOffset(String fileOffset) {
		FileOffset = fileOffset;
	}
	public String getDataLen() {
		return DataLen;
	}
	public void setDataLen(String dataLen) {
		DataLen = dataLen;
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
		result = prime * result + ((DataLen == null) ? 0 : DataLen.hashCode());
		result = prime * result + ((Date == null) ? 0 : Date.hashCode());
		result = prime * result + ((FileName == null) ? 0 : FileName.hashCode());
		result = prime * result + ((FileOffset == null) ? 0 : FileOffset.hashCode());
		result = prime * result + ((FileSize == null) ? 0 : FileSize.hashCode());
		result = prime * result + ((HW_Version == null) ? 0 : HW_Version.hashCode());
		result = prime * result + ((Reserve == null) ? 0 : Reserve.hashCode());
		result = prime * result + ((SW_Version == null) ? 0 : SW_Version.hashCode());
		result = prime * result + ((TransDir == null) ? 0 : TransDir.hashCode());
		result = prime * result + ((UnitAmount == null) ? 0 : UnitAmount.hashCode());
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
		DataItemAFN0FFN01new other = (DataItemAFN0FFN01new) obj;
		if (DataLen == null) {
			if (other.DataLen != null)
				return false;
		} else if (!DataLen.equals(other.DataLen))
			return false;
		if (Date == null) {
			if (other.Date != null)
				return false;
		} else if (!Date.equals(other.Date))
			return false;
		if (FileName == null) {
			if (other.FileName != null)
				return false;
		} else if (!FileName.equals(other.FileName))
			return false;
		if (FileOffset == null) {
			if (other.FileOffset != null)
				return false;
		} else if (!FileOffset.equals(other.FileOffset))
			return false;
		if (FileSize == null) {
			if (other.FileSize != null)
				return false;
		} else if (!FileSize.equals(other.FileSize))
			return false;
		if (HW_Version == null) {
			if (other.HW_Version != null)
				return false;
		} else if (!HW_Version.equals(other.HW_Version))
			return false;
		if (Reserve == null) {
			if (other.Reserve != null)
				return false;
		} else if (!Reserve.equals(other.Reserve))
			return false;
		if (SW_Version == null) {
			if (other.SW_Version != null)
				return false;
		} else if (!SW_Version.equals(other.SW_Version))
			return false;
		if (TransDir == null) {
			if (other.TransDir != null)
				return false;
		} else if (!TransDir.equals(other.TransDir))
			return false;
		if (UnitAmount == null) {
			if (other.UnitAmount != null)
				return false;
		} else if (!UnitAmount.equals(other.UnitAmount))
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
		return "DataItemAFN0FFN01new [UnitAmount=" + UnitAmount + ", UnitMark=" + UnitMark + ", HW_Version="
				+ HW_Version + ", SW_Version=" + SW_Version + ", TransDir=" + TransDir + ", Reserve=" + Reserve
				+ ", FileName=" + FileName + ", FileSize=" + FileSize + ", FileOffset=" + FileOffset + ", DataLen="
				+ DataLen + ", Date=" + Date + "]";
	}

}
