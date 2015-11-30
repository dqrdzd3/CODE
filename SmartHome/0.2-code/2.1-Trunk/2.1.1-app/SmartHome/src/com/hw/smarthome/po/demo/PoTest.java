package com.hw.smarthome.po.demo;

import java.util.LinkedList;
import java.util.List;

import com.hw.smarthome.po.SensorAirDetail;
import com.hw.smarthome.po.SensorAlert;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.SensorDetailList;
import com.hw.smarthome.po.SensorGasDetail;

/**
 * 测试时使用的临时
 * 
 * @author 曾凡
 * @time 2014年7月1日 下午1:45:27
 */
public class PoTest {
	public static SensorDetail returnDetail() {
		SensorDetail detail = new SensorDetail();
		SensorAirDetail air = new SensorAirDetail();
		air.setCo2("12");
		air.setHumidity("15");
		air.setPm25("155");
		air.setSensorId("5FFFFFFF");
		air.setTemperature("34");
		air.setCreateTime("2014-06-25 13:45");
		SensorGasDetail gas = new SensorGasDetail();
		gas.setCh4("12");
		gas.setCo("14");
		gas.setSensorId("199");
		gas.setSwitchStatus("关");
		gas.setCreateTime("2014-06-25 13:45");
		SensorAlert alert = new SensorAlert();
		alert.setSensorId("1111");
		alert.setAlertStatus("不正常");
		alert.setAlertType("温度");
		alert.setAlertValue("60");
		alert.setCreatTime("2014-06-25 13:45");

		detail.setAir(air);
		detail.setGas(gas);
		detail.setAlert(alert);
		return detail;

	}

	public static SensorDetailList returnList() {
		SensorDetailList detailList = new SensorDetailList();
		List<SensorDetail> list = new LinkedList<SensorDetail>();
		list.add(returnDetail());
		list.add(returnDetail());

		detailList.setSensorList(list);
		return detailList;
	}
}
