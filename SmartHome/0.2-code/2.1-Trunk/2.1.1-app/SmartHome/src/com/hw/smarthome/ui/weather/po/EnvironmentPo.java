package com.hw.smarthome.ui.weather.po;

import java.io.Serializable;

/**
 * @author 曾凡
 * @time 2015年7月3日 下午12:09:27
 */
public class EnvironmentPo implements Serializable {
	private static final long serialVersionUID = 6999526736405217232L;
	private String aqi;
	private String pm25;
	private String suggest;
	private String quality;
	private String majorPollutants;
	private String o3;
	private String co;
	private String pm10;
	private String so2;
	private String no2;
	private String updateTime;

	public String getAqi() {
		return aqi;
	}

	public void setAqi(String aqi) {
		this.aqi = aqi;
	}

	public String getPm25() {
		return pm25;
	}

	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getMajorPollutants() {
		return majorPollutants;
	}

	public void setMajorPollutants(String majorPollutants) {
		this.majorPollutants = majorPollutants;
	}

	public String getO3() {
		return o3;
	}

	public void setO3(String o3) {
		this.o3 = o3;
	}

	public String getCo() {
		return co;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public String getPm10() {
		return pm10;
	}

	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}

	public String getSo2() {
		return so2;
	}

	public void setSo2(String so2) {
		this.so2 = so2;
	}

	public String getNo2() {
		return no2;
	}

	public void setNo2(String no2) {
		this.no2 = no2;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Environment [aqi=" + aqi + ", pm25=" + pm25
				+ ", suggest=" + suggest + ", quality="
				+ quality + ", MajorPollutants="
				+ majorPollutants + ", o3=" + o3 + ", co=" + co
				+ ", pm10=" + pm10 + ", so2=" + so2 + ", no2="
				+ no2 + ", updateTime=" + updateTime + "]";
	}
}
