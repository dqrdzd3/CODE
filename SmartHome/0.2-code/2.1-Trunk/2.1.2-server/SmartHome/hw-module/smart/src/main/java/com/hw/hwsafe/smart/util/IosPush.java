package com.hw.hwsafe.smart.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hw.hwsafe.platform.constants.ConfConstants;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

public class IosPush {
	public static String keystore = null;
	public static String password = null;
	static {

		try {

			keystore =getP12Path();//"f:/ios/push.p12"; 
			password = ConfConstants.IOS_CERTIFI_PASS;// "123456";

			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static int getPushSuccessed(List<String> tokens,String alert,int badge,String sound){
		 int successful = 0;
		 int failed = 0;
		 Boolean production = ConfConstants.IOS_PRODUCTION;
		 try
	        {
	            PushNotificationPayload payLoad = new PushNotificationPayload();
	            payLoad.addAlert(alert); // 消息内容
	            payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值
	            if (!StringUtils.isBlank(sound))
	            {
	                payLoad.addSound(sound);//铃音
	            }else {
					payLoad.addSound("default");
				}
	            PushNotificationManager pushManager = new PushNotificationManager();
	            //true：表示的是产品发布推送服务  gateway.push.apple.com        false ：表示的是产品测试推送服务 gateway.sandbox.push.apple.com
	            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(keystore, password, production));
	            List<PushedNotification> notifications = new ArrayList<PushedNotification>();
	            // 发送push消息
//	            if (sendCount)
//	            {
//	                Device device = new BasicDevice();
//	                device.setToken(tokens.get(0));
//	                PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
//	                notifications.add(notification);
//	            }
//	            else
//	            {
	                List<Device> device = new ArrayList<Device>();
	                for (String token : tokens)
	                {
	                    device.add(new BasicDevice(token));
	                }
	                notifications = pushManager.sendNotifications(payLoad, device);
	        //    }
	            List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
	            List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
	            failed = failedNotifications.size();   //错误推送数目
	            successful = successfulNotifications.size();  //成功推送
	            System.out.println("错误数："+failed +",成功数:"+successful);
	            pushManager.stopConnection();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
		 return successful;
	}
	public static int getPushSuccessed(String token,String alert,int badge,String sound){
		 int successful = 0;
		 int failed = 0;
		 try
	        {
	            PushNotificationPayload payLoad = new PushNotificationPayload();
	            payLoad.addAlert(alert); // 消息内容
	            payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值
	            if (!StringUtils.isBlank(sound))
	            {
	                payLoad.addSound(sound);//铃音
	            }
	            PushNotificationManager pushManager = new PushNotificationManager();
	            //true：表示的是产品发布推送服务  gateway.push.apple.com        false ：表示的是产品测试推送服务 gateway.sandbox.push.apple.com
	            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(keystore, password, ConfConstants.IOS_PRODUCTION));
	            List<PushedNotification> notifications = new ArrayList<PushedNotification>();
	            // 发送push消息
//	            if (sendCount)
//	            {
	                Device device = new BasicDevice();
	                device.setToken(token);
	                PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
	                notifications.add(notification);
//	            }
//	            else
//	            {
//	                List<Device> device = new ArrayList<Device>();
//	                for (String token : tokens)
//	                {
//	                    device.add(new BasicDevice(token));
//	                }
//	                notifications = pushManager.sendNotifications(payLoad, device);
	        //    }
	            List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
	            List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
	            failed = failedNotifications.size();   //错误推送数目
	            successful = successfulNotifications.size();  //成功推送
	            System.out.println("错误数："+failed +",成功数:"+successful);
	            pushManager.stopConnection();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
		 return successful;
	}
	/**
	 * 获取WEB-INF目录下面苹果证书文件的路径
	 * @return
	 */
	public static String getP12Path()
	{
	//	file:/D:/JavaWeb/.metadata/.me_tcat/webapps/TestBeanUtils/WEB-INF/classes/ 
		//E:\eclipse4.3\eclipse4.3\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\smart\WEB-INF\push.p12
		String path=Thread.currentThread().getContextClassLoader().getResource("").toString();
		path=path.replace('/', '\\'); // 将/换成\
		path=path.replace("file:", ""); //去掉file:
		path=path.replace("classes\\", ""); //去掉class\
		path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb...
		path+=ConfConstants.IOS_CERTIFI_PATH;
	
		return path;
		

	}

}
