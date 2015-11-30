package com.hw.hwsafe.smart.service;

import java.util.Date;


public interface IDeviceAlarmService {
	

	void saveAddDeviceAlarm(String deviceId, String deviceType,
			String deviceStauts, Date occurTime, Double currentValue);

}
