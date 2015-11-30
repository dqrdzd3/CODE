package com.hw.hwsafe.smart.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.smart.pojo.A001PO;
import com.hw.hwsafe.smart.service.IA001Service;
import com.hw.hwsafe.smart.service.IDeviceAlarmService;
import com.hw.hwsafe.utils.UUIDGenerater;


public class DeviceAlarmServiceImpl implements IDeviceAlarmService {
	
	
	@Autowired
	private IA001Service a001Service;

	@Override
	public void saveAddDeviceAlarm(String deviceId, String deviceType,
			String deviceStauts, Date occurTime, Double currentValue)  {
		try {
			Date convetTime = convetTime(occurTime);
			A001PO a001PO = new A001PO();
			a001PO.setMa006(deviceId);
			List<A001PO> list = a001Service.retrieveInstanceByPO(a001PO);
			if(list != null && list.size() > 0){
				a001PO = list.get(0);
				if(a001PO.getMa005() != null){
					if(convetTime.after(a001PO.getMa005())){
						a001PO.setMa001(UUIDGenerater.getUUID());
						a001PO.setMa002(currentValue);
						a001PO.setMa003(deviceStauts);
						a001PO.setMa004(deviceType);
						a001PO.setMa005(occurTime);
						a001PO.setMa007("00");
						a001Service.insertInstance(a001PO);
						System.out.println("新增设备报警信息成功！");
					}else{
						System.out.println("半小时前已经有该设备的报警信息！");
					}
				}
			}else{
				a001PO.setMa001(UUIDGenerater.getUUID());
				a001PO.setMa002(currentValue);
				a001PO.setMa003(deviceStauts);
				a001PO.setMa004(deviceType);
				a001PO.setMa005(occurTime);
				a001PO.setMa007("00");
				a001Service.insertInstance(a001PO);
				System.out.println("新增设备报警信息成功！");
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("新增设备报警信息失败！");
		}
	}
	/**
	 * 得到所给时间后30分钟的时间
	 * @throws ParseException 
	 */
	private Date convetTime(Date obtainTimes) throws ParseException{
		java.util.Calendar Cal=java.util.Calendar.getInstance();   
		Cal.setTime(obtainTimes);   
		Cal.add(java.util.Calendar.MINUTE,ConfConstants.HW_INTERVAL_TIME);   
		return Cal.getTime();
	}

}
