package com.hw.smarthome.mom.test.ws;

import java.text.SimpleDateFormat;

import com.hw.smarthome.mom.ws.ISmartService;






public class Entry {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

//		Boolean bt = DateUtil.isLastOneDay(sdf.parse("2015-01-29 10:20:00"), sdf.parse("2015-01-29 01:20:00"), 7);
//		System.out.println(bt);

		String data ="{'protocol':'1','data':[{'address':'1','chanlvalue':'565','gprs_id':'50000105A8DF','media':'C9','status':'00','unit':'03'}, {'address':'1','chanlvalue':'17','gprs_id':'50000105A8DF','media':'C9','status':'00','unit':'05'}, {'address':'1','chanlvalue':'20','gprs_id':'50000105A8DF','media':'CA','status':'00','unit':'06'}, {'address':'1','chanlvalue':'120','gprs_id':'50000105A8DF','media':'D8','status':'00','unit':'13'}, {'address':'1','chanlvalue':'3','gprs_id':'50000105A8DF','media':'D9','status':'00','unit':'00'}]}";
    	
		//data = "{\"protocol\":\"1\"}";
		SmartClientProxy proxy = new SmartClientProxy();
    	ISmartService service1 = proxy.getSmartBeanPort();
    	while (true){
		//System.out.println(service1.retrieveHisStautsByGPRSId("500000011DAC", "C9", Integer.valueOf(24),Integer.valueOf(1)));
    		service1.setRealtimeList(data);
    		Thread.sleep(1000);
    	}
		//调用外部webservice	
//		 String wsdlUrl = "http://192.168.111.186:8080/SmartBeanService/SmartBean?wsdl";//"http://127.0.0.1:8080/services/AndroidPush.server?wsdl";
//	      // String wsdlUrl = "http://192.168.111.192:8080/services/AndroidPush.server?wsdl";
//          try {
//       	// 1.创建service对象，通过axis自带的类创建
//       	   org.apache.axis.client.Service service = new org.apache.axis.client.Service();
//          	// 请求服务的URL
//       	   URL url = new URL(wsdlUrl);
//       	   // 2.创建服务方法的调用者对象call，设置call对象的属性
//			Call call = (Call) service.createCall();
//			
//			call.setTargetEndpointAddress(url);  // 给call对象设置请求的URL属性
//			
//			 String serviceName = "SmartBeanService";//"SmartBeanService";//webservice
//		     call.setOperationName(serviceName);
//			
//
//		   while (true){
//		   	Object rec=call.invoke("http://ws.mom.smarthome.hw.com/", "sayHello", new Object[] {data});
//		    
//				// Object rec=call.invoke(new Object[] {"dd"}); 
//			  System.out.println(rec);
//			   Thread.sleep(2000);
//				    
//				  
//				    
//		   }
//		  
//  		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (RemoteException e){
//			e.printStackTrace();
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		SmartBean bean = new SmartBean();
//		while(true){
//			bean.setRealtimeList(data);
//			Thread.sleep(200);
//			System.out.println("dd");
//		}
//
//               String nameSpaceUri ="http://ws.mom.smarthome.hw.com/";
//             String serviceName = "SmartBeanService";    
//              String portName = "SmartBeanPort";    
                   
//           ServiceFactory serviceFactory;
//		try {
//			serviceFactory = ServiceFactory.newInstance();
//			  SmartClientProxy afService =new SmartClientProxy();    
//	           ISmartService proxy = (ISmartService)afService.getPort(new QName(nameSpaceUri, portName),ISmartService.class);    
//	             System.out.println("return value is "+proxy.sayHello("dd") ) ;   
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}    
         

	}

	
	 
}
