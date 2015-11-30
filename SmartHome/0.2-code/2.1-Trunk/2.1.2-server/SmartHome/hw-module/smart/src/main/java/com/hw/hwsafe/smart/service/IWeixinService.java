package com.hw.hwsafe.smart.service;

/**
 * 微信接口
 * @author Administrator
 *
 */
public interface IWeixinService {

	/*
	 * 通过手机号查找该设备信息
	 */
	String getSensorDetailToWX(String tel) throws Exception;
	/*
	 * 通过SensorId获得该数据的历史数据
	 */
	String getSensorHistoryToWX(String sensorid) throws Exception;
}
