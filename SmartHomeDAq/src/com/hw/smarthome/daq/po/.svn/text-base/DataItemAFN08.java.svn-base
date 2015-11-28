package com.hw.smarthome.daq.po;

import java.io.Serializable;

import com.hw.smarthome.daq.po.base.DataItem;

public class DataItemAFN08 extends DataItem implements
		Serializable {

	private static final long serialVersionUID = 7343780255458361380L;
	/** 编号 */
	private int sensorCount;
	/** 传感器类型 */
	private String media;
	/** ID号 */
	private int addresss;
	/** 单位 */
	private String unit;
	/** 状态 */
	private String status;
	/** 数据 */
	private String chanlvalue;

	@Override
	public String getWsJson(String sensorId) {

		StringBuilder sb = new StringBuilder();
		sb.append("{'address':").append("'").append(addresss)
				.append("',");
		sb.append("'chanlvalue':").append("'")
				.append(chanlvalue).append("',");
		sb.append("'gprs_id':").append("'").append(sensorId)
				.append("',");
		sb.append("'media':").append("'").append(media)
				.append("',");
		sb.append("'status':").append("'").append(status)
				.append("',");
		sb.append("'unit':").append("'").append(unit)
				.append("'}");
		return sb.toString();

	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public int getSensorCount() {
		return sensorCount;
	}

	public void setSensorCount(int sensorCount) {
		this.sensorCount = sensorCount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChanlvalue() {
		return chanlvalue;
	}

	public void setChanlvalue(String chanlvalue) {
		this.chanlvalue = chanlvalue;
	}

	public int getAddresss() {
		return addresss;
	}

	public void setAddresss(int addresss) {
		this.addresss = addresss;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + addresss;
		result = prime
				* result
				+ ((chanlvalue == null) ? 0 : chanlvalue
						.hashCode());
		result = prime * result
				+ ((media == null) ? 0 : media.hashCode());
		result = prime * result + sensorCount;
		result = prime * result
				+ ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((unit == null) ? 0 : unit.hashCode());
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
		DataItemAFN08 other = (DataItemAFN08) obj;
		if (addresss != other.addresss)
			return false;
		if (chanlvalue == null) {
			if (other.chanlvalue != null)
				return false;
		} else if (!chanlvalue.equals(other.chanlvalue))
			return false;
		if (media == null) {
			if (other.media != null)
				return false;
		} else if (!media.equals(other.media))
			return false;
		if (sensorCount != other.sensorCount)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataItemAFN08 [sensorCount=" + sensorCount
				+ ", media=" + media + ", addresss=" + addresss
				+ ", unit=" + unit + ", status=" + status
				+ ", chanlvalue=" + chanlvalue + "]";
	}

}
