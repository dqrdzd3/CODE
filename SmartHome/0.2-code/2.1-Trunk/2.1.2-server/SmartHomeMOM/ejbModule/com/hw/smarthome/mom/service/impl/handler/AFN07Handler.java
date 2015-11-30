package com.hw.smarthome.mom.service.impl.handler;

import java.util.HashMap;
import java.util.Map;

import com.hw.hwsafe.smart.pojo.CtrlContent;
import com.hw.hwsafe.smart.pojo.SensorCtrlDetail;
import com.hw.hwsafe.smart.pojo.SensorDetail;
import com.hw.smarthome.daq.po.DataItemAFN07FN01;
import com.hw.smarthome.daq.po.DataItemAFN07FN02;
import com.hw.smarthome.daq.po.base.DataItemAFN07;
import com.hw.smarthome.mom.cache.MOMCache;
import com.hw.smarthome.util.DateUtil;

public class AFN07Handler {
	public void saveCtrlData(DataItemAFN07 po) {
		SensorDetail detail = MOMCache
				.getCurrentCtrlDevicesList().get(
						po.getSensorId());
		if (detail == null) {
			detail = new SensorDetail();
			detail.setSensorId(po.getSensorId());
			MOMCache.getCurrentCtrlDevicesList().put(
					po.getSensorId(), detail);
		}
		SensorCtrlDetail ctrlDetail = null;
		if (detail.getCtrl() == null) {
			ctrlDetail = new SensorCtrlDetail();
			detail.setCtrl(ctrlDetail);
		} else {
			ctrlDetail = detail.getCtrl();
		}
		ctrlDetail.setCreateTime(DateUtil.getTime());
		Map<String, CtrlContent> ctrlContent = null;
		if (ctrlDetail.getCtrlContent() == null) {
			ctrlContent = new HashMap<String, CtrlContent>();
			ctrlDetail.setCtrlContent(ctrlContent);
		} else {
			ctrlContent = ctrlDetail.getCtrlContent();
		}

		if (po instanceof DataItemAFN07FN01) {
			DataItemAFN07FN01 f1 = (DataItemAFN07FN01) po;
			detail.setRemoteHardVersion(f1.getHardVer());
			detail.setRemoteSoftVersion(f1.getSoftVer());
			ctrlDetail.setDeviceId(po.getSensorId());
			ctrlDetail.setDeviceType(f1.getDeviceType());
			ctrlDetail.setCreateTime(DateUtil.getTime());
			ctrlDetail.setToken(f1.getToken());
		} else if (po instanceof DataItemAFN07FN02) {
			DataItemAFN07FN02 f2 = (DataItemAFN07FN02) po;
			if(f2.getSwitchAmount()==1){
				ctrlContent.clear();
			}
			CtrlContent cc = new CtrlContent();
			cc.setSwitchState(f2.getSwitchState());
			cc.setSwitchType(f2.getSwitchType());
			ctrlContent.put(f2.getSwitchNum() + "", cc);
		}
	}

	public SensorDetail queryCtrlData(String deviceId) {
		return MOMCache.getCurrentCtrlDevicesList()
				.get(deviceId);
	}
}
