package com.hw.hwsafe.smart.service.impl;
/**
 * 文件名：D002DaoImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IControlDeviceDao;
import com.hw.hwsafe.smart.dao.ID002Dao;
import com.hw.hwsafe.smart.pojo.BinUpdatePo;
import com.hw.hwsafe.smart.pojo.ControlDevicePO;
import com.hw.hwsafe.smart.pojo.CtrlContent;
import com.hw.hwsafe.smart.pojo.D002PO;
import com.hw.hwsafe.smart.pojo.NoisePO;
import com.hw.hwsafe.smart.pojo.SensorAirDetail;
import com.hw.hwsafe.smart.pojo.SensorCtrlDetail;
import com.hw.hwsafe.smart.pojo.SensorDetail;
import com.hw.hwsafe.smart.pojo.SensorGasDetail;
import com.hw.hwsafe.smart.service.ID002Service;
import com.hw.hwsafe.smart.util.SocketSender;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN05FN01;
import com.hw.smarthome.daq.po.DataItemAFN09FN01;
import com.hw.smarthome.daq.po.DataItemAFN10FN01;
import com.hw.smarthome.daq.po.base.DataItem;

/**
 * D002Dao层实现类
 *
 */
public class D002ServiceImpl extends BaseServiceImpl implements ID002Service{
	private static Map<String, BinUpdatePo> binVersionCacheMap = new ConcurrentHashMap<String, BinUpdatePo>();
	public static Map<String, BinUpdatePo> getBinVersionCacheMap() {
		return binVersionCacheMap;
	}
	//噪音内存数据
	private static Map<String, NoisePO> noiseCacheMap = new ConcurrentHashMap<String, NoisePO>();
	public static Map<String, NoisePO> getNoiseCacheMap() {
		return noiseCacheMap;
	}

	@Autowired
	private ID002Dao d002Dao;
	
	@Autowired
	private IControlDeviceDao controlDeviceDao;

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return d002Dao.retrieveByPage(map);
	}

	@Override
	public D002PO retrieveInstanceById(String ma001) throws Exception{
		return d002Dao.retrieveInstanceById(ma001);
	}
	
	@Override
	public List<D002PO> retrieveInstanceByPO(D002PO d002po) throws Exception {
		return d002Dao.retrieveInstanceByPO(d002po);
	}

	@Override
	public List<SensorDetail> insertInstance(D002PO d002PO) throws Exception{
		d002Dao.insertInstance(d002PO);
		List<SensorDetail> sensorList = this.covertionDriver(d002PO.getMa002());
		return sensorList;
	}

	@Override
	public List<SensorDetail> updateInstance(D002PO d002PO) throws Exception{
	
			d002Dao.updateInstance(d002PO);
			List<SensorDetail> sensorList = this.covertionDriver(d002PO.getMa002());
			return sensorList;

	}

	@Override
	public List<SensorDetail> deleteInstanceById(D002PO d002PO,String userId) throws Exception{
		d002Dao.deleteInstanceById(d002PO);
		List<SensorDetail> sensorList = this.covertionDriver(userId);
		return sensorList;
	}

	@Override
	public List<SensorDetail> covertionDriver(String userId)throws Exception {
		D002PO d002PO1 = new D002PO();
		d002PO1.setMa002(userId);
		List<D002PO> d002List = d002Dao.retrieveInstanceByPO(d002PO1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:hh:mm");
		List<SensorDetail> sensorList = new ArrayList<SensorDetail>();
		for(D002PO d002PO :d002List){
			SensorDetail sensorDetail = new SensorDetail();
			sensorDetail.setSensorId(d002PO.getMa004());
			sensorDetail.setName(d002PO.getMa008());
			if("1".equals(d002PO.getMa003())){
				SensorGasDetail s1 = new SensorGasDetail();
				s1.setSensorId(d002PO.getMa004());
				s1.setName(d002PO.getMa008());
				s1.setCreateTime(df.format(d002PO.getMa005()));
				sensorDetail.setGas(s1);
			}
			if("2".equals(d002PO.getMa003())){
				SensorAirDetail sa1 = new SensorAirDetail();
				sa1.setSensorId(d002PO.getMa004());
				sa1.setName(d002PO.getMa008());
				sa1.setCreateTime(df.format(d002PO.getMa005()));
				sensorDetail.setAir(sa1);
			}
			sensorList.add(sensorDetail);
		}
		//查询控制设备
		ControlDevicePO controlDevicePO = new ControlDevicePO();
		controlDevicePO.setMa002(userId);
		List<ControlDevicePO> listControlDevices = controlDeviceDao.retrieveInstanceByPO(controlDevicePO);
		if (listControlDevices!=null) {
			for (ControlDevicePO po:listControlDevices) {
				SensorDetail sensorDetail = new SensorDetail();
				sensorDetail.setSensorId(po.getMa003());
				SensorCtrlDetail sensorCtrlDetail = new SensorCtrlDetail();
				sensorCtrlDetail.setCtrlId(po.getMa003());
				sensorCtrlDetail.setDeviceId(po.getMa004());
				sensorCtrlDetail.setDeviceType(po.getMa005());
				sensorCtrlDetail.setToken(po.getMa009());
				sensorCtrlDetail.setCreateTime(po.getMa010().toString());
				sensorCtrlDetail.setLinkSensor(po.getMa011());
				sensorCtrlDetail.setName(po.getMa012());
				sensorDetail.setCtrl(sensorCtrlDetail);
				sensorDetail.setSensorId(po.getMa004());
				sensorList.add(sensorDetail);
			}
		}

		
		return sensorList;
	}
	
	/**
	 * 测试AFN09
	 * 
	 * @author 曾凡
	 * @param po
	 * @param dataItems
	 * @time 2015年1月22日 下午5:22:49
	 */
	public void query09(String sensorId) {
		DAqPo po = new DAqPo();
		po.setAfn((byte) 0x09);
		po.setSensorId(sensorId);
		DataItemAFN09FN01 query = new DataItemAFN09FN01();
		query.setDeviceID(po.getSensorId());
		query.setFn((byte) 0x01);
		query.setProductDate(new Date());
		List<DataItem> dataItems = new ArrayList<DataItem>();
		dataItems.add(query);
		po.setDataItems(dataItems);
		
		SocketSender.getInstance().send(po);
	}
	/**
	 * setConf05   设置
	 * 
	 * @author 曾凡
	 * @param po
	 * @param dataItems
	 * @time 2015年1月22日 下午5:22:49
	 */
	public void setConf05(String sensorId,String deviceType,String localHardVer,String localSoftVer
			) {
		DAqPo po = new DAqPo();
		po.setAfn((byte) 0x05);
		po.setSensorId(sensorId);
		DataItemAFN05FN01 query = new DataItemAFN05FN01();
		query.setDeviceType(deviceType);           //0A01  空气    0B01  燃气
		query.setFn((byte) 0x01);
		query.setHardVer(localHardVer);     //localversion
		query.setSoftVer(localSoftVer);     
		List<DataItem> dataItems = new ArrayList<DataItem>();
		dataItems.add(query);
		po.setDataItems(dataItems);
		
		SocketSender.getInstance().send(po);
	}
	

	
	public void testJob(){
		System.out.println("定时任务执行中……"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

	@Override
	public List<D002PO> retrieveDuplicateInstanceByPO(D002PO d002po)
			throws Exception {
		return d002Dao.retrieveDuplicateInstanceByPO(d002po);
	}

	@Override
	public void insertD002(D002PO d002po) throws Exception {
		d002Dao.insertInstance(d002po);
	}

	@Override
	public void updateD002(D002PO d002po) throws Exception {
		d002Dao.updateInstance(d002po);
	}

	@Override
	public Integer delBatchD002(Map<String, Object> map) throws Exception {
		return d002Dao.delBatchD002(map);
	}

	@Override
	public List<D002PO> retrieveInstanceByDevId(String ma004) throws Exception {
		return d002Dao.retrieveInstanceByDevId(ma004);
	}

	@Override
	public Integer retrieveDeviceFromP001(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return d002Dao.retrieveDeviceFromP001(map);
	}

	@Override
	public void insertInstanceP(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		d002Dao.insertInstanceP(map);
	}
}