
/**
 * @title Push.java
 * @package com.hw.hwsafe.smart.util
 * @author liyuhao
 * @create_time 2014年7月17日 上午11:44:37
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2014</p>
 */
	
package com.hw.hwsafe.smart.util;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.hw.hwsafe.platform.constants.ConfConstants;


/**
 * <p>
 * 推送
 * </p>
 */

public final class Push {
	/**
	 * 
	 * 调用webService推送
	 * @param phoneList 推送人列表
	 * @param title  推送标题
	 * @param message  推送内容
	 * @throws Exception          
	 * @author liyuhao
	 * @create_time 2014年7月17日上午11:45:28
	 */
	public static void doWebservice(List<String> phoneList,String title,String message,String remoteUrl) throws Exception{
		String url=ConfConstants.HWCLOUD_ANDROIDPUSH_URL ;
		if(phoneList.size()<0){
			return;
		}
		org.apache.axis.client.Service axisservice = new org.apache.axis.client.Service();
		URL url2 = new URL(url);
		org.apache.axis.client.Call call = (org.apache.axis.client.Call) axisservice.createCall();
		call.setTargetEndpointAddress(url2); 
		String serviceName = "pushOneDevice";
		call.setOperationName(serviceName);
		for (int i = 0; i < phoneList.size(); i++) {
			Object ret = call.invoke(new Object[] { "12345678901234567890", phoneList.get(i),title,message,remoteUrl });
			System.out.println(ret.toString());
		}
	}
	public static void doWebservice(String phone,String title,String message,String remoteUrl) throws Exception{
		List<String> lst = new ArrayList<String>();
		lst.add(phone);
		doWebservice(lst,title,message,remoteUrl);
	}
}
