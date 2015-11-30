package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;


/**
 * @author 曾凡
 * @time 2014年7月4日 上午11:09:16
 */
public class HomeAirHistory implements Serializable {

	private static final long serialVersionUID = 1105458849110331255L;
	/** 传感器唯一标识ID */
	private String sensorId;
	/** 用户定义的传感器名称 */
	private String name;
	/** 温度 0-50℃ */
	private String[] temperatures;
	/** 湿度:0-100RH% */
	private String[] humiditys;
	/** 二氧化碳:0-5000ppm */
	private String[] co2s;
	/** PM2.5:0-1000ug/m3； */
	private String[] pm25s;
	/** 甲醛 */
	private String[] ch2os;
	/** 苯 */
	private String[] c6h6s;
	/** VOC */
	private String[] vocs;
	/** 过去的天数 "yyyy-mm-dd" */
	private String[] createTimes;

	
	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getTemperatures() {
		return temperatures;
	}

	public void setTemperatures(String[] temperatures) {
		this.temperatures = temperatures;
	}

	public String[] getHumiditys() {
		return humiditys;
	}

	public void setHumiditys(String[] humiditys) {
		this.humiditys = humiditys;
	}

	public String[] getCo2s() {
		return co2s;
	}

	public void setCo2s(String[] co2s) {
		this.co2s = co2s;
	}

	public String[] getPm25s() {
		return pm25s;
	}

	public void setPm25s(String[] pm25s) {
		this.pm25s = pm25s;
	}

	public String[] getCreateTimes() {
		return createTimes;
	}

	public void setCreateTimes(String[] createTimes) {
		this.createTimes = createTimes;
	}

	public String[] getCh2os() {
		return ch2os;
	}

	public void setCh2os(String[] ch2os) {
		this.ch2os = ch2os;
	}

	public String[] getC6h6s() {
		return c6h6s;
	}

	public void setC6h6s(String[] c6h6s) {
		this.c6h6s = c6h6s;
	}

	public String[] getVocs() {
		return vocs;
	}

	public void setVocs(String[] vocs) {
		this.vocs = vocs;
	}

	
}
