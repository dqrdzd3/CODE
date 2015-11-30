package com.hw.smarthome.po;

import java.io.Serializable;

import android.text.TextUtils;

import com.hw.smarthome.ui.constant.UIConstant;

/**
 * 燃气传感器明细
 * 
 * @author 曾凡
 * @time 2014年6月25日 下午12:19:04
 */
public class SensorGasDetail implements Serializable {
	private static final long serialVersionUID = 8933774119191857002L;
	/** 传感器唯一标识ID */
	private String sensorId;
	/** 用户定义的传感器名称 */
	private String name;
	/** 开关状态 开/关 */
	private String switchStatus;
	/** 一氧化碳 */
	private String co;
	/** 甲烷 */
	private String ch4;
	/** 传感器产生本条数据的创建日期 "yyyy-MM-dd HH:mm:ss" */
	private String createTime;

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getSwitchStatus() {
		return switchStatus;
	}

	public void setSwitchStatus(String switchStatus) {
		this.switchStatus = switchStatus;
	}

	public String getCo() {
		
		return (co == null || "".equals(co)) ? "FF"
				: co;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public String getCh4() {
		return (ch4 == null || "".equals(ch4)) ? "FF"
				: ch4;
	}

	public void setCh4(String ch4) {
		this.ch4 = ch4;
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

	public String getShareHrefContent() {

		StringBuilder sb = new StringBuilder();
		if (!TextUtils.isEmpty(co)) {
			sb.append("co=").append(co);
			// sb.append(":").append(UIConstant.HOME_UNIT_CO);
			sb.append("&");
		}
		if (!TextUtils.isEmpty(ch4)) {
			sb.append("ch4=").append(ch4);
			// sb.append(":").append(UIConstant.HOME_UNIT_CH4);
			sb.append("&");
		}
		return sb.toString();
	}

	public String getShareContent() {
		StringBuilder sb = new StringBuilder("");
		if (!TextUtils.isEmpty(co)) {
			sb.append("一氧化碳[").append(co)
					.append(UIConstant.HOME_UNIT_CO).append("]");
		}
		if (!TextUtils.isEmpty(ch4)) {
			sb.append("天然气[").append(ch4)
					.append(UIConstant.HOME_UNIT_CH4)
					.append("]");
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return "SensorGasDetail [sensorId=" + sensorId
				+ ", name=" + name + ", switchStatus="
				+ switchStatus + ", co=" + co + ", ch4=" + ch4
				+ ", createTime=" + createTime + "]";
	}

}
