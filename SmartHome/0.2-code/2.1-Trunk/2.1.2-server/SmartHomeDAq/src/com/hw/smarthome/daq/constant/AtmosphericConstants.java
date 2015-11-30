package com.hw.smarthome.daq.constant;

public class AtmosphericConstants {

	/**
	 * 自动根据SN获取补零后的设备ID(SensorId)
	 * 
	 * @author 曾凡
	 * @param sn
	 * @param ip
	 * @return
	 * @time 2015年9月1日 下午7:53:44
	 */
	public static String getAtmospherSN(String sn, String ip) {
		String sensorId = "";
		int fs = 0;
		if (sn == null || "".equals(sn)) {
			sn = ip.replace(".", "");
		}
		fs = 11 - sn.length();
		sensorId = "5" + String.format("%0" + fs + "d", 0) + sn;
		return sensorId;
	}

	public static String MEDIA_TYPE_CO2 = "30";
	
	public static String MEDIA_TYPE_PM25 = "D8";

	public static String MEDIA_TYPE_TEMPERATURE = "C9";

	public static String MEDIA_TYPE_HUMIDITY = "CA";


	public static String VU_CO2 = "CO2";
	
	public static String VU_PM25 = "PM25";

	public static String VU_TEMPERATURE = "TEMP";

	public static String VU_HUMIDITY = "HUM";

	
//	public static void main(String[] args) {
//		System.out.println(getAtmospherSN("DQJC004",
//				"192.168.111.1"));
//		System.out.println(getAtmospherSN("", "192.168.111.1"));
//	}
}
