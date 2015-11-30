package com.hw.smarthome.ui.weather.po;

import java.io.Serializable;

/**
 * <p>
 * VO表单
 * </p>
 * 
 * @author 曾凡
 * @date 2012-3-7上午11:35:34
 */
public class WeatherPo implements Serializable {
	private static final long serialVersionUID = 733610581515666293L;
	private String city;
	private String updateTime;
	private String temp;
	private String hum;
	private String windDegree;
	private String windDerection;
	private String sunRise;
	private String sunSet;
	private EnvironmentPo environment;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getHum() {
		return hum;
	}

	public void setHum(String hum) {
		this.hum = hum;
	}

	public String getWindDegree() {
		return windDegree;
	}

	public void setWindDegree(String windDegree) {
		this.windDegree = windDegree;
	}

	public String getWindDerection() {
		return windDerection;
	}

	public void setWindDerection(String windDerection) {
		this.windDerection = windDerection;
	}

	public String getSunRise() {
		return sunRise;
	}

	public void setSunRise(String sunRise) {
		this.sunRise = sunRise;
	}

	public String getSunSet() {
		return sunSet;
	}

	public void setSunSet(String sunSet) {
		this.sunSet = sunSet;
	}

	public EnvironmentPo getEnvironment() {
		return environment;
	}

	public void setEnvironment(EnvironmentPo environment) {
		this.environment = environment;
	}

	@Override
	public String toString() {
		return "WeatherPo [city=" + city + ", updateTime="
				+ updateTime + ", temp=" + temp + ", hum=" + hum
				+ ", windDegree=" + windDegree
				+ ", windDerection=" + windDerection
				+ ", sunRise=" + sunRise + ", sunSet=" + sunSet
				+ ", environment=" + environment + "]";
	}

}
