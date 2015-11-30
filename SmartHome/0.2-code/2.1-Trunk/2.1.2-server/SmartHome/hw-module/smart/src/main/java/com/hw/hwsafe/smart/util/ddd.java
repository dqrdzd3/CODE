package com.hw.hwsafe.smart.util;

import it.sauronsoftware.base64.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javapns.devices.Device;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;





public class ddd {

	public static void main(String[] args) {
		  try
	        {
	            //从客户端获取的deviceToken，在此为了测试简单，写固定的一个测试设备标识。
	           String deviceToken = "b890838830e9fafecc2011ce5fd1d9a6cd2f5da43f61eaab96d9869d16035137";

	            System.out.println("Push Start deviceToken:" + deviceToken);
	            //定义消息模式
	            PushNotificationPayload payLoad = new PushNotificationPayload();
	            payLoad.addAlert("hi 徐薛波 ,this is test!");
	            payLoad.addBadge(0);//消息推送标记数，小红圈中显示的数字。
	            payLoad.addSound("default");
	            //注册deviceToken
	            PushNotificationManager pushManager = new PushNotificationManager();
	            pushManager.addDevice("iPhone", deviceToken);
	           
	            //连接APNS
	            String host = "gateway.sandbox.push.apple.com";
	            //String host = "gateway.push.apple.com";
	            int port = 2195;

	            String certificatePath = "f:/push_test.p12";//前面生成的用于JAVA后台连接APNS服务的*.p12文件位置
	            String certificatePassword = "123456";//p12文件密码。
	            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, false));
	            //发送推送
	            Device client = pushManager.getDevice("iPhone");
	            System.out.println("推送消息: " + client.getToken()+"\n"+payLoad.toString() +" ");
	            List<PushedNotification> notifications = new ArrayList<PushedNotification>();
	          //notifications=pushManager.sendNotification(client, payLoad);
	            notifications = pushManager.sendNotifications(payLoad, client);
	      
	            List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
	            List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
	           int  failed = failedNotifications.size();   //错误推送数目
	           int  successful = successfulNotifications.size();  //成功推送
	            System.out.println("错误数："+failed +",成功数:"+successful);
	            //停止连接APNS
	            pushManager.stopConnection();
	            //删除deviceToken
	            pushManager.removeDevice("iPhone");
	            System.out.println("Push End");
	        }
	        catch (Exception ex)
	        {
	            ex.printStackTrace();
	        }
	


		// TODO Auto-generated method stub
		//IosPush.getPushSuccessed("4473f058bcd1fd88420ddadea62b1760f57929b2b65db32d62914707cce5696e", "dddd", 5, null);
//		try {
//			String phone="13592605164";
//			String content="dadfa";
//			String url ="http://192.168.111.231:8080/smart/services/SmsService?wsdl";
//			Client client = new Client(new URL(url));
//			client.invoke("sendMsg", new Object[] { phone, content });
//		
//			Push.doWebservice(phone, "空气电台报警通知", content, "10");
//		
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		
//		for(String str:get24HoursBeforeCn()){
//			System.out.println(str);
//		}
		
	/*	
		URL url;
		try {
			url = new URL("http://www.baidu.com");
			 System.out.println("==================以下为网站内容==================");   
		     URLConnection urlcon = url.openConnection();   
	     int i = urlcon.getContentLength();   
		      if (i > 0) {   
		         InputStream is = urlcon.getInputStream();   
		      int a;   
		        while ((a = is.read()) != -1) {   
	             System.out.print((char) a);   
		       }   
		           is.close();   
	       } else {   
	          System.out.println("响应内容为空...");   
	       }  
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		*/
		//System.out.println(Base64.encode);
//		String POST_URL = "http://services.unisiot.com:8080/upload/5c535b916c8ac0c7/env/20150426-history-env_1";
//		final String si = "0001067abfebfbff";
//		String sn = "5c535b916c8ac0c7";
//	    final String mdsi =MD5Util.MD5_16(si).toLowerCase();
//		final String mdsn = MD5Util.MD5_16(sn).toLowerCase();
//		System.out.println(si+"  mdsi:"+mdsi);
//		System.out.println(sn+"  mdsn:"+mdsn);
//
//       
//       byte[] result = getHttpData_BaseAuthorization(POST_URL, si, sn);
//      
//        Authenticator.setDefault(new Authenticator() {
//
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				// TODO Auto-generated method stub
//				return new PasswordAuthentication(mdsi, mdsn.toCharArray());
//			}
//        	
//		});
//       
//		 try {
//	            URL url = new URL(POST_URL);
//	            
//	            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
//	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中
//	            
//	            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
//	            connection.setDoOutput(true);
//	            
//	            // 设置连接输入流为true
//	            connection.setDoInput(true);
//	            connection.setRequestProperty("accept", "*/*");
//	            connection.setRequestProperty("connection", "Keep-Alive");
//	            connection.setRequestProperty("user-agent",
//	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//	            // 设置请求方式为post
//	            connection.setRequestMethod("POST");
//	            
//	            // post请求缓存设为false
//	            connection.setUseCaches(false);
//	            
//	            // 设置该HttpURLConnection实例是否自动执行重定向
//	           // connection.setInstanceFollowRedirects(true);
//	            
//	            // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
//	            // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
//	         connection.setRequestProperty("Authorization", "Basic "+Base64.encode(si+":"+mdsn));
//
//	            System.out.println("Basic "+Base64.encode(mdsi+":"+mdsn));
//	            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
//	            connection.connect();
//	          
//	            int i = connection.getContentLength();   
//			      if (i > 0) {   
//			         InputStream is = connection.getInputStream();   
//			      int a;   
//			        while ((a = is.read()) != -1) {   
//		             System.out.print((char) a);   
//			       }   
//			           is.close();   
//		       } else {   
//		          System.out.println("响应内容为空...");   
//		       }  
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
		    
	}

	private static final SimpleDateFormat hhssmm = new SimpleDateFormat(
			"HHmmss");

	private static final SimpleDateFormat yyyyMMddFt = new SimpleDateFormat(
			"yyyyMMdd");
	private static final SimpleDateFormat yyyymmddhhssmmFt = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static Integer[] get24HoursBefore() {
		Integer[] days = new Integer[24];
		Calendar now = Calendar.getInstance();

		Integer tempDay = 0;
		for (int i = days.length - 1; i >= 0; i--) {
			now.add(Calendar.HOUR_OF_DAY, -1);
			tempDay = getHhSsMm(now.getTime());
			days[i] = tempDay;
		}
		return days;
	}

	public static Integer getHhSsMm(Date date) {
		return Integer.valueOf(hhssmm.format(date));
	}

	public static String[] get24HoursBeforeCn() {
		String[] res = new String[24];
		Integer[] times = get24HoursBefore();
		int hour = 0;
		String temp = "";
		for (int i = 0; i < 24; i++) {
			hour = times[i];
			if (hour < 10000) {
				temp = "00" + hour;
			}else if(hour<100000){
				temp = "0" + hour;
			}else{
				temp = hour+"";
			}
			res[i] = temp.substring(0, 2)+"时";
		}

		return res;
	}
	
	
	 /**
	    * @方法: GetHttpData_BaseAuthorization
	    * @描述: TODO(http基本认证下载文件)
	    * @参数 @param url：请求文件的地址
	    * @参数 @param user：用户名
	    * @参数 @param pwd：密码
	    * @参数 @return
	    * @返回 byte[]:返回得到的数据
	    * @throws
	    * @作者    赵庆旭
	    * @日期 2014-2-20 上午11:34:19 
	    */
	    public static byte[] getHttpData_BaseAuthorization(String url,String user,String pwd) {
	    	
	        ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
	        try {
	            String urlNameString = url ;
	            URL realUrl = new URL(urlNameString);
	            // 打开和URL之间的连接
	            URLConnection connection = realUrl.openConnection();
	            // 设置通用的请求属性
	            connection.setRequestProperty("accept", "*/*");
	            connection.setRequestProperty("connection", "Keep-Alive");
	            connection.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            
	            //http BASE 认证  ：需要输入用户名、密码  用户名密码经过base64加密后以报文的形式传递
	            //用户名（MD5） ：密码（MD5）>>>转BASE64加密
	            
	           // String MD5_16=Encrypt.stringToMD52(user).substring(8, 24)+":"+Encrypt.stringToMD52(pwd).substring(8, 24);//base64加密前的 用户名：密码数据（只取16位MD5）
	            String MD5_16=MD5Util.MD5_16(user)+":"+MD5Util.MD5_16(pwd);//base64加密前的 用户名：密码数据（MD5）
	            
	            String base64 =  Base64.encode(MD5_16);//base64加密
	    
				connection.setRequestProperty("Authorization","Basic " + base64);
				
				 /* connection.setRequestProperty("Authorization",
	            "Basic ZTJmYzcxNGM0NzI3ZWU5Mzk1ZjMyNGNkMmU3ZjMzMWY6ZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2U=");*/
				
			/*	System.out.println("明文=" + MD5_16);
				System.out.println("base4密文=" + base64);*/

				// 建立实际的连接
	            connection.connect();
	            
				// 解析响应数据
				Map<String, List<String>> map = connection.getHeaderFields();
				// 遍历所有的响应头字段
				for (String key : map.keySet()) {
					System.out.println(key + "--->" + map.get(key));
				}
				byte[] buffer = new byte[1024];
				int len = -1;
				while ((len = connection.getInputStream().read(buffer)) != -1) {
					outputstream.write(buffer, 0, len);
				}

			} catch (Exception e) {
				//LogMsg.w("http", "发送http get  基本认证请求出现异常！" + e);
				e.printStackTrace();
			}
			// 使用finally块来关闭输入流
			finally {
				try {
					outputstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return outputstream.toByteArray();
		}
		
}
