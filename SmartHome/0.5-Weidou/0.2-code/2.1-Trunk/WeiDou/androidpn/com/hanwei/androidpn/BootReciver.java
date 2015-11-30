package com.hanwei.androidpn;

import com.hanwei.androidpn.ServiceManager;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * 
 * 
 * 项目名称：SmartMonitor
 * 类名称：BootReciver
 * 类描述：开机启动广播接收者
 * 创建人：lijing
 * 创建时间：2014-5-27 下午4:14:55
 * 修改人：lijing
 * 修改时间：2014-5-27 下午4:14:55
 * 修改备注：
 * @version 
 *
 */
public class BootReciver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		  if(arg1.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
//			   Intent newIntent = new Intent(arg0,StartService.class);
//			   arg0.startService(newIntent);
		        ServiceManager serviceManager = new ServiceManager(arg0);
		       // serviceManager.setNotificationIcon(R.drawable.notification);
		        serviceManager.startService();
		  } 
	}

}
