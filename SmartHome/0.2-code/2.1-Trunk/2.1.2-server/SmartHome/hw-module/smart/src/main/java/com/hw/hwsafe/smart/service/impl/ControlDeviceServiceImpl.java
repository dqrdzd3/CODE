package com.hw.hwsafe.smart.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IControlDeviceDao;
import com.hw.hwsafe.smart.pojo.ControlDevicePO;
import com.hw.hwsafe.smart.pojo.CtrlContent;
import com.hw.hwsafe.smart.pojo.SensorCtrlDetail;
import com.hw.hwsafe.smart.service.IControlDeviceService;
import com.hw.hwsafe.smart.util.SocketSender;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN10FN01;
import com.hw.smarthome.daq.po.base.DataItem;

public class ControlDeviceServiceImpl extends BaseServiceImpl implements
		IControlDeviceService {

	@Autowired
	private IControlDeviceDao controlDeviceDao ;
	
	@Override
	public ControlDevicePO retrieveInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return controlDeviceDao.retrieveInstanceById(ma001);
	}

	@Override
	public List<ControlDevicePO> retrieveInstanceByPO(
			ControlDevicePO controlDevicePO) throws Exception {
		// TODO Auto-generated method stub
		return controlDeviceDao.retrieveInstanceByPO(controlDevicePO);
	}

	@Override
	public void insertInstance(ControlDevicePO controlDevicePO)
			throws Exception {
		// TODO Auto-generated method stub
		controlDeviceDao.insertInstance(controlDevicePO);
	}

	@Override
	public void updateInstance(ControlDevicePO controlDevicePO)
			throws Exception {
		// TODO Auto-generated method stub
		controlDeviceDao.updateInstance(controlDevicePO);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		controlDeviceDao.deleteInstanceById(ma001);
	}
	
	/**
	 * 发送控制命令
	 * 
	 * @author 曾凡
	 * @param ctrlDetail
	 * @time 2015年4月24日 上午11:24:32
	 */
	public void ctrlAFN10F01(
			SensorCtrlDetail ctrlDetail) throws Exception  {
		DAqPo po = new DAqPo();
		po.setAfn((byte) 0x10);
		po.setSensorId(ctrlDetail.getDeviceId());
		DataItemAFN10FN01 dataItem = null;
		List<DataItem> dataItems = new ArrayList<DataItem>();
		for (Entry<String, CtrlContent> content : ctrlDetail
				.getCtrlContent().entrySet()) {
			dataItem = new DataItemAFN10FN01();
			dataItem.setFn((byte) 0x01);
			dataItem.setToken(ctrlDetail.getToken());
			dataItem.setSensorId(ctrlDetail.getDeviceId());
			dataItem.setSwitchType(content.getValue()
					.getSwitchType());
			dataItem.setSwitchAmount(ctrlDetail.getCtrlContent()
					.size());
			dataItem.setSwitchNum(Integer.valueOf(
					content.getKey(), 16));
			dataItem.setSwitchState(content.getValue()
					.getSwitchState());
			dataItems.add(dataItem);
		}
		po.setDataItems(dataItems);
		SocketSender.getInstance().send(po);
	}

	@Override
	public void updateInstanceName(ControlDevicePO controlDevicePO)
			throws Exception {
		controlDeviceDao.updateInstanceName(controlDevicePO);
		
	}

}
