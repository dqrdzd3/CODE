package com.hw.smarthome.mom.constant;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.hw.util.Ln;

public class CloudConstants {

	public static String MEDIA_TYPE_CO = "04";
	public static String MEDIA_TYPE_CO2 = "30";

	public static String MEDIA_TYPE_CH4 = "01";

	public static String MEDIA_TYPE_C6H6 = "1B";

	public static String MEDIA_TYPE_CH2O = "17";

	public static String MEDIA_TYPE_PM25 = "D8";

	public static String MEDIA_TYPE_TEMPERATURE = "C9";

	public static String MEDIA_TYPE_HUMIDITY = "CA";

	public static String MEDIA_TYPE_VOC = "D9";

	public static String APPLICATION_SERVER_WSDL = "http://192.168.111.186:8080/smart/services/saveAddDeviceAlarm?wsdl";
	public static String APPLICATION_SERVER_NAMESPACE = "http://service.smart.hwsafe.hw.com";
	public static String APPLICATION_SERVER_METHOD_ALERT = "saveAddDeviceAlarm";

	public static Properties p;
	static {
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(
					"SmartHomeMOMConf.properties"));
			p = new Properties();
			p.load(in);
			APPLICATION_SERVER_WSDL = p.getProperty(
					"application.server.wsdl").trim();
			APPLICATION_SERVER_NAMESPACE = p.getProperty(
					"application.server.namespace").trim();
			APPLICATION_SERVER_METHOD_ALERT = p.getProperty(
					"application.server.method.alert").trim();

		} catch (Exception e) {
			Ln.e(CloudConstants.class, HintConstant.READ_CONF_FAIL,
					e);
		}
	}

}
