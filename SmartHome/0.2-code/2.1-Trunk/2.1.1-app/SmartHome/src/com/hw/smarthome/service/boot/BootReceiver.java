package com.hw.smarthome.service.boot;



import com.hanwei.androidpn.ServiceManager;
import com.hw.smarthome.R;
import com.hw.smarthome.po.UserPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithAccount;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		  if(arg1.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
			  UserPO user = DealWithAccount
						.getUser(arg0);
				if (user != null) {
					String phone = user.getMa006();
					if (!TextUtils.isEmpty(phone)) {

						ServiceManager serviceManager = new ServiceManager(arg0);
						serviceManager.setUserName(phone);
						serviceManager.setPassword("123456");
						serviceManager
								.setXmppHost(ServerConstant.PUSH_URI);
						serviceManager
								.setNotificationIcon(R.drawable.ic_launcher);
						serviceManager.startService();
					}
				}
		  } 
	}

}
