package com.hw.smarthome.mom.service;

import java.util.Date;

import com.hw.hwsafe.smart.pojo.SensorDetail;
import com.hw.smarthome.daq.po.base.DataItemAFN07;
import com.hw.smarthome.mom.dao.shhistoryhour.ShHistoryHour;
import com.hw.smarthome.mom.dao.shhistorymonth.ShHistoryMonth;
import com.hw.smarthome.mom.po.DataItemAFN08MomPo;

public interface MOMService {
	/**
	 * 设置被控设备的实时和历史记录
	 * 
	 * @author 曾凡
	 * @param po
	 * @time 2015年4月21日 上午11:13:19
	 */
	public void setCtrlCurHisSensors(DataItemAFN07 po);

	/**
	 * 获取被控设备的实时和历史记录
	 * 
	 * @author 曾凡
	 * @param po
	 * @time 2015年4月21日 上午11:13:19
	 */
	public SensorDetail getCtrlCurHisSensors(String deviceId);

	/**
	 * 设置实时和历史数据
	 * 
	 * @author 曾凡
	 * @param po
	 * @time 2015年1月27日 上午11:32:26
	 */
	public void setCurHisSensors(DataItemAFN08MomPo po);

	/**
	 * 设置实时数据
	 * 
	 * @author 曾凡
	 * @param deviceId
	 * @return
	 * @time 2015年1月27日 上午10:32:44
	 */
	public void setCurSensors(DataItemAFN08MomPo po);

	/**
	 * 获取实时数据
	 * 
	 * @author 曾凡
	 * @param deviceId
	 * @return
	 * @time 2015年1月27日 上午10:33:33
	 */
	public SensorDetail getCurSensors(String deviceId,
			String driverType, String driverName);

	/**
	 * 报警推送
	 * 
	 * @author 曾凡
	 * @param po
	 * @time 2015年1月27日 上午10:33:53
	 */
	public void pushAlarm(DataItemAFN08MomPo po);

	/**
	 * 更新小时记录
	 * 
	 * @author 曾凡
	 * @param po
	 * @time 2015年1月27日 上午10:34:21
	 */
	public void updateSensorHourOfDay(DataItemAFN08MomPo po);

	/**
	 * 获取小时记录
	 * 
	 * @author 曾凡
	 * @param deviceId
	 * @param media
	 * @param nowDate
	 * @param hours
	 * @time 2015年1月27日 上午10:34:31
	 */
	public ShHistoryHour getSensorHourOfDay(String deviceId,
			String media, Date nowDate, int hours);

	public void updateSensorDayOfMonth(DataItemAFN08MomPo po);

	public ShHistoryMonth getSensorDayOfMonth(String deviceId,
			String media, Date nowDate, int hours);

}
