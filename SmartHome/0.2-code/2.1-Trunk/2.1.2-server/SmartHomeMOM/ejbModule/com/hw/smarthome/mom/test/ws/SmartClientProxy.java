package com.hw.smarthome.mom.test.ws;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

import com.hw.smarthome.mom.ws.ISmartService;

//name 与 发布中的服务器的serice 名称一致

@WebServiceClient(name = "SmartBeanService", targetNamespace = "http://ws.mom.smarthome.hw.com/", wsdlLocation = "http://192.168.111.186:8080/SmartBeanService/SmartBean?wsdl")
public class SmartClientProxy extends Service {

	private final static URL USERMANAGERBEANSERVICE_WSDL_LOCATION;

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = SmartClientProxy.class.getResource(".");
			url = new URL(baseUrl,
					"http://192.168.111.186:8080/SmartBeanService/SmartBean?wsdl");
		} catch (MalformedURLException e) {

		}
		USERMANAGERBEANSERVICE_WSDL_LOCATION = url;
	}

	public SmartClientProxy() {
		super(USERMANAGERBEANSERVICE_WSDL_LOCATION, new QName(
				"http://ws.mom.smarthome.hw.com/",
				"SmartBeanService"));
	}

	public SmartClientProxy(URL wsdlDocumentLocation,
			QName serviceName) {
		super(wsdlDocumentLocation, serviceName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * WebEndpint 中的name 是从服务器发布后得到的消息 ，与 getPort方法中一致
	 */
	@WebEndpoint(name = "SmartBeanPort")
	public ISmartService getSmartBeanPort() {
		return super.getPort(new QName(
				"http://ws.mom.smarthome.hw.com/",
				"SmartBeanPort"), ISmartService.class);
	}

}
