package com.hw.smarthome.daq.monitor.service;

import java.util.List;

import com.hw.smarthome.daq.po.DAqPo;

public interface MonitorService {

	/** 获取前置机hour小时内的连接数，默认为1小时 */
	public int getFrontLinkCount(int hour);

	/** 获取前置机hour小时内的连接内容，默认为1小时 */
	public List<DAqPo> getFrontLinkDatas(int hour);

	/** 根据传感器的设备ID获取传感器的地址信息 */
	public DAqPo getFrontLinkBySensorId(String sensorId);

	/** 根据操作ID获取传感器的地址信息 */
	public DAqPo getFrontLinkById(String id);

	/** 获取解码后的缓冲队列大小 */
	public int getFrontCurrentDecryptQueueSize();

}
