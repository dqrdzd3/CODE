package com.hw.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * @author 曾凡
 * @time 2014年10月16日 上午11:08:17
 */
public class WifiUtils {
	
	private String getSSID(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		if (wifi.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
			String ssid = info.getSSID();
			if (ssid != null && ssid.contains("\"")) {
				ssid = ssid.replace("\"", "");
			}
			return ssid;
		}
		return null;
	}

	/**
	 * 获取Mac地址
	 * 
	 * @author 曾凡
	 * @param context
	 * @return
	 * @time 2014年10月16日 上午11:09:18
	 */
	public static String getLocalMacAddress(Context context) {

		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);

		WifiInfo info = wifi.getConnectionInfo();

		return info.getMacAddress();

	}
	
	/**
	 * 获取IP地址
	 * 
	 * @author 曾凡
	 * @param context
	 * @return
	 * @time 2014年10月16日 上午11:09:18
	 */
	public static String getLocalIpAddress()  
    {  
        try  
        {  
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)  
            {  
               NetworkInterface intf = en.nextElement();  
               for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)  
               {  
                   InetAddress inetAddress = enumIpAddr.nextElement();  
                   if (!inetAddress.isLoopbackAddress())  
                   {  
                	  
                       return inetAddress.getHostAddress().toString();  
                   }  
               }  
           }  
        }  
        catch (SocketException ex)  
        {  
            
        }  
        return null;  
    }  
	
	public static String getWIFILocalIpAddress(Context context) throws UnknownHostException {
        WifiManager wifiManager = (WifiManager) context.getSystemService(android.content.Context.WIFI_SERVICE );
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        return InetAddress.getByName(String.format("%d.%d.%d.%d",
                        (ipAddress & 0xff), (ipAddress >> 8 & 0xff),
                        (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff))).toString();
}


}
