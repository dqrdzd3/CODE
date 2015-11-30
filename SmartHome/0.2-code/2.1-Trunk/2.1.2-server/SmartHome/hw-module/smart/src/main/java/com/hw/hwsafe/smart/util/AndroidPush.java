package com.hw.hwsafe.smart.util;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.hw.hwsafe.platform.constants.ConfConstants;

public class AndroidPush {

	static String wsdlUrl =ConfConstants.HWCLOUD_ANDROIDPUSH_URL ;
	static String APIKEY = "12345678901234567890";
	public static boolean pushContent(String deviceName,
			String title, String message, String url1) {
		
		Object ret = "false";
		try {
			// 1.创建service对象，通过axis自带的类创建
			org.apache.axis.client.Service service = new org.apache.axis.client.Service();
			// 请求服务的URL
			URL url = new URL(wsdlUrl);
			// 2.创建服务方法的调用者对象call，设置call对象的属性
			Call call = (Call) service.createCall();

			call.setTargetEndpointAddress(url); // 给call对象设置请求的URL属性

			String serviceName = "pushOneDevice";// webservice的方法名
			call.setOperationName(serviceName);
			ret = call
					.invoke(new Object[] { APIKEY,
							deviceName, title,
							message, url1 });
//			ret = call
//					.invoke(new Object[] { "1234567890",
//							"bbb4525e132e4438bf8ffd206913fc08", "123456d",
//							"dddd", "" });

			//System.out.println(ret.toString());
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
	public static boolean pushAll(String title, String message, String url1) {
		
		Object ret = "false";
		try {
			// 1.创建service对象，通过axis自带的类创建
			Service service = new Service();
			// 请求服务的URL
			URL url = new URL(wsdlUrl);
			// 2.创建服务方法的调用者对象call，设置call对象的属性
			Call call = (Call) service.createCall();

			call.setTargetEndpointAddress(url); // 给call对象设置请求的URL属性

			String serviceName = "pushManyDevice";// webservice的方法名
			call.setOperationName(serviceName);
			ret = call
					.invoke(new Object[] { APIKEY,
							title,
							message, url1 });


			
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
	
	public static boolean isOnlineByUsername(String username){
		try {
			// 1.创建service对象，通过axis自带的类创建
			org.apache.axis.client.Service service = new org.apache.axis.client.Service();
			// 请求服务的URL
			URL url = new URL(wsdlUrl);
			// 2.创建服务方法的调用者对象call，设置call对象的属性
			Call call = (Call) service.createCall();

			call.setTargetEndpointAddress(url); // 给call对象设置请求的URL属性

			String serviceName = "isOnlineByUsername";// webservice的方法名
			call.setOperationName(serviceName);
			return (Boolean) call.invoke(new Object[] { username});


			
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
	
	}
	public static boolean register(String username,String password){
		try {
			// 1.创建service对象，通过axis自带的类创建
			org.apache.axis.client.Service service = new org.apache.axis.client.Service();
			// 请求服务的URL
			URL url = new URL(wsdlUrl);
			// 2.创建服务方法的调用者对象call，设置call对象的属性
			Call call = (Call) service.createCall();

			call.setTargetEndpointAddress(url); // 给call对象设置请求的URL属性

			String serviceName = "registerDevice";// webservice的方法名
			call.setOperationName(serviceName);
			return (Boolean) call.invoke(new Object[] { username,password});


			
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
	
	}
}
