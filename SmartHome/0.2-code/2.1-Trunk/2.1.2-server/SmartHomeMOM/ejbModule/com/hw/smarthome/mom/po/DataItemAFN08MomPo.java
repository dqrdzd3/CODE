package com.hw.smarthome.mom.po;

import java.io.Serializable;
import java.util.Date;

public class DataItemAFN08MomPo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7989745127149169150L;
	
	private String address;
	private String chanlvalue;
	private String gprs_id;
	private String media;
	private String status;
	private String unit;
	private int hourInDay;                 //24小时制
	private int dayInMonth;                   //一个月的几号
	private Date reciveDate;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getChanlvalue() {
		return chanlvalue;
	}
	public void setChanlvalue(String chanlvalue) {
		this.chanlvalue = chanlvalue;
	}
	public String getGprs_id() {
		return gprs_id;
	}
	public void setGprs_id(String gprs_id) {
		this.gprs_id = gprs_id;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getHourInDay() {
		return hourInDay;
	}
	public void setHourInDay(int hourInDay) {
		this.hourInDay = hourInDay;
	}
	public int getDayInMonth() {
		return dayInMonth;
	}
	public void setDayInMonth(int dayInMonth) {
		this.dayInMonth = dayInMonth;
	}
	public Date getReciveDate() {
		return reciveDate;
	}
	public void setReciveDate(Date reciveDate) {
		this.reciveDate = reciveDate;
	}
	@Override
	public String toString() {
		return "DataItemAFN08MomPo [address=" + address
				+ ", chanlvalue=" + chanlvalue + ", gprs_id="
				+ gprs_id + ", media=" + media + ", status="
				+ status + ", unit=" + unit + ", hourInDay="
				+ hourInDay + ", dayInMonth=" + dayInMonth
				+ ", reciveDate=" + reciveDate + "]";
	}
}
