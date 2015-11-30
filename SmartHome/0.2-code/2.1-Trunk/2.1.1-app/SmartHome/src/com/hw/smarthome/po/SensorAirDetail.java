package com.hw.smarthome.po;

import java.io.Serializable;

import android.text.TextUtils;

import com.hw.smarthome.ui.constant.UIConstant;

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
		return ch2o;
	}

	public void setCh2o(String ch2o) {
		this.ch2o = ch2o;
	}

	public String getC6h6() {
		return c6h6;
	}

	public void setC6h6(String c6h6) {
		this.c6h6 = c6h6;
	}

	public String getShareHrefContent() {

		StringBuilder sb = new StringBuilder();
		if (!TextUtils.isEmpty(temperature)) {
			sb.append("temperature=").append(temperature);
			// sb.append(":").append(
			// UIConstant.HOME_UNIT_TEMPERATURE);
			sb.append("&");
		}
		if (!TextUtils.isEmpty(humidity)) {
			sb.append("humidity=").append(humidity);
			// sb.append(":").append(UIConstant.HOME_UNIT_HUMIDITY);
			sb.append("&");
		}
		if (!TextUtils.isEmpty(co2)) {
			sb.append("co2=").append(co2);
			// sb.append(":").append(UIConstant.HOME_UNIT_CO);
			sb.append("&");
		}
		if (!TextUtils.isEmpty(pm25)) {
			sb.append("pm25=").append(pm25);
			// sb.append(":").append(UIConstant.HOME_UNIT_PM25);
			sb.append("&");
		}
		if (!TextUtils.isEmpty(ch2o)) {
			sb.append("ch2o=").append(ch2o);
			// sb.append(":").append(UIConstant.HOME_UNIT_CH2O);
			sb.append("&");
		}
		if (!TextUtils.isEmpty(c6h6)) {
			sb.append("c6h6=").append(c6h6);
			// sb.append(":").append(UIConstant.HOME_UNIT_C6H6);
			sb.append("&");
		}
		if (!TextUtils.isEmpty(voc)) {
			sb.append("voc=").append(voc);
			// sb.append(":").append(UIConstant.HOME_UNIT_C6H6);
			sb.append("&");
		}
		return sb.toString();
	}

	public String getShareContent() {
		StringBuilder sb = new StringBuilder("");
		if (!TextUtils.isEmpty(temperature)) {
			sb.append("温度[").append(temperature)
					.append(UIConstant.HOME_UNIT_TEMPERATURE)
					.append("]");
		}
		if (!TextUtils.isEmpty(humidity)) {
			sb.append("湿度[").append(humidity)
					.append(UIConstant.HOME_UNIT_HUMIDITY)
					.append("]");
		}
		if (!TextUtils.isEmpty(co2)) {
			sb.append("二氧化碳[").append(co2)
					.append(UIConstant.HOME_UNIT_CO).append("]");
		}
		if (!TextUtils.isEmpty(pm25)) {
			sb.append("PM2.5[").append(pm25)
					.append(UIConstant.HOME_UNIT_PM25)
					.append("]");
		}
		if (!TextUtils.isEmpty(ch2o)) {
			sb.append("甲醛[").append(ch2o)
					.append(UIConstant.HOME_UNIT_CH2O)
					.append("]");
		}
		if (!TextUtils.isEmpty(c6h6)) {
			sb.append("苯[").append(c6h6)
					.append(UIConstant.HOME_UNIT_C6H6)
					.append("]");
		}
		if (!TextUtils.isEmpty(voc)) {
			sb.append("VOC[").append(voc)
					.append(UIConstant.HOME_UNIT_C6H6)
					.append("]");
		}
		return sb.toString();
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
