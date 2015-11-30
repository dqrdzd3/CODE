package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 曾凡
 * @time 2014年6月25日 下午1:31:55
 */
public class SensorDetailList implements Serializable{
	private static final long serialVersionUID = -6054130309732972960L;
	/** 传感器历史列表 */
	private List<SensorDetail> sensorList;

	public List<SensorDetail> getSensorList() {
		return sensorList;
	}

	public void setSensorList(List<SensorDetail> sensorList) {
		this.sensorList = sensorList;
	}

	@Override
	public String toString() {
		if(sensorList.size()==0){
			return "[]";
		}
		String returnStr = "{";
		for (SensorDetail detail : sensorList) {
			returnStr += detail.toString() + ";";
		}
		returnStr += "{";
		return returnStr;
	}
}
