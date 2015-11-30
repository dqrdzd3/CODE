package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;





/**
 * 空气质量传感器明细
 * 
 * @author 曾凡
 * @time 2014年6月25日 下午12:19:04
 */
public class SensorAirDetail implements Serializable {
	private static final long serialVersionUID = 7125861941849202383L;
	/** 传感器唯一标识ID */
	private String sensorId;
	/** 用户定义的传感器名称 */
	private String name;
	/** 温度 0-50℃ */
	private String temperature;
	/** 湿度:0-100RH% */
	private String humidity;
	/** 二氧化碳:0-5000ppm */
	private String co2;
	/** PM2.5:0-1000ug/m3； */
	private String pm25;
	private String voc;
	/** 单位、范围未定义 */
	private String ch2o;
	/** 单位、范围未定义 */
	private String c6h6;
	/** 传感器产生本条数据的创建日期 "yyyy-MM-dd HH:mm:ss" */
	private String createTime;
	/* 分享内容 */
	private String shareContent;

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getTemperature() {
		return (temperature == null || "".equals(temperature)) ? "0"
				: temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return (humidity == null || "".equals(humidity)) ? "0"
				: humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getCo2() {
		return (co2 == null || "".equals(co2)) ? "0" : co2;
	}

	public void setCo2(String co2) {
		this.co2 = co2;
	}

	public String getPm25() {
		return (pm25 == null || "".equals(pm25)) ? "0" : pm25;
	}

	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCh2o() {
		return (ch2o == null || "".equals(ch2o)) ? "0" : ch2o;
	}

	public void setCh2o(String ch2o) {
		this.ch2o = ch2o;
	}

	public String getC6h6() {
		return (c6h6 == null || "".equals(c6h6)) ? "0" : c6h6;
	}

	public void setC6h6(String c6h6) {
		this.c6h6 = c6h6;
	}

	

	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}

	public String getVoc() {
		return (voc == null || "".equals(voc)) ? "0" : voc;
	}

	public void setVoc(String voc) {
		this.voc = voc;
	}
	
	public String getShareContent() {
		return shareContent;
	}

	@Override
	public String toString() {
		return "SensorAirDetail [sensorId=" + sensorId
				+ ", name=" + name + ", temperature="
				+ temperature + ", humidity=" + humidity
				+ ", co2=" + co2 + ", pm25=" + pm25 + ", voc="
				+ voc + ", ch2o=" + ch2o + ", c6h6=" + c6h6
				+ ", createTime=" + createTime
				+ ", shareContent=" + shareContent + "]";
	}

}
