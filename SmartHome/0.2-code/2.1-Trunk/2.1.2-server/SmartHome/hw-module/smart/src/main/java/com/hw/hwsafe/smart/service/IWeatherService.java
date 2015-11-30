package com.hw.hwsafe.smart.service;

import com.hw.hwsafe.platform.service.IBaseService;

public interface IWeatherService extends IBaseService {

	String ipToCity(String ip);
	
	String cityToId(String city);
	
	String getWeatherContent(String cityID);
	
	String getPm25(String city);
	
}
