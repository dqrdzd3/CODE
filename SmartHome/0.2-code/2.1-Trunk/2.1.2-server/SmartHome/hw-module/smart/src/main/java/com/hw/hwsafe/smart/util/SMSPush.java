package com.hw.hwsafe.smart.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;


public class SMSPush {
	static String wsdlUrl ="http://192.168.111.231:8081/smart/services/SmsService?wsdl";
	public static boolean SmsSendContent(String phone,String content) {
		
		Object ret = "false";
		try {
			// 1.创建service对象，通过axis自带的类创建
			org.apache.axis.client.Service service = new org.apache.axis.client.Service();
			// 请求服务的URL
			URL url = new URL(wsdlUrl);
			// 2.创建服务方法的调用者对象call，设置call对象的属性
			Call call = (Call) service.createCall();

			call.setTargetEndpointAddress(url); // 给call对象设置请求的URL属性

			String serviceName = "sendMsg";// webservice的方法名
			call.setOperationName(serviceName);
			ret = call
					.invoke(new Object[] {phone,content});

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
		return Boolean.parseBoolean(ret.toString());
	}
}
