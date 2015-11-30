package com.hw.smarthome.view.chart;

/**
 * 历史明细的po
 * 
 * @author 曾凡
 * @time 2014年6月10日 上午10:09:10
 */
public class ChartEntity {
	/** 传感器名称 */
	private String name;
	/** 传感器单位 */
	private String unit;
	/** 7天前，不是周1 */
	private String monday;
	/** 6天前，不是周2 */
	private String tuesday;
	/** 5天前，不是周3 */
	private String wednesday;
	/** 4天前，不是周4 */
	private String thursday;
	/** 3天前，不是周5 */
	private String friday;
	/** 2天前，不是周6 */
	private String saturday;
	/** 1天前，不是周日 */
	private String sunday;

	/** 要显示控件的宽 */
	private String width;
	/** 要显示控件的高 */
	private String height;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMonday() {
		return monday;
	}

	public void setMonday(String monday) {
		this.monday = monday;
	}

	public String getTuesday() {
		return tuesday;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}

	public String getWednesday() {
		return wednesday;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}

	public String getThursday() {
		return thursday;
	}

	public void setThursday(String thursday) {
		this.thursday = thursday;
	}

	public String getFriday() {
		return friday;
	}

	public void setFriday(String friday) {
		this.friday = friday;
	}

	public String getSaturday() {
		return saturday;
	}

	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}

	public String getSunday() {
		return sunday;
	}

	public void setSunday(String sunday) {
		this.sunday = sunday;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

}
