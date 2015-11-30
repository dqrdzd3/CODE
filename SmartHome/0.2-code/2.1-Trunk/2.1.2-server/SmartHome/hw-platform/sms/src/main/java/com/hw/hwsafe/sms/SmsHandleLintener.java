package com.hw.hwsafe.sms;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hw.hwsafe.sms.czd.outer.ISMSCat;
import com.hw.hwsafe.sms.czd.outer.impl.SMSCatImpl;

public class SmsHandleLintener implements ServletContextListener {

	/**
	 * 实例化一个对象
	 */
	private static AlarmHandle smsHandle = new AlarmHandle();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		new Thread(smsHandle).start();
		ISMSCat smsCat = new SMSCatImpl();
//		ISendMessage sendMessage = new SendMessageImpl();
		// 初始化短信猫参数
		try {
			smsCat.initCatService();
			// 启动短信猫服务
			smsCat.startService();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 获取数据库的数据放入队列
		//sendMessage.getHighQueue();
		//sendMessage.getLowQueue();
		// 启动线程
		smsCat.startThread();

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// do nothing
	}

	public static AlarmHandle getSmsHandle() {
		return smsHandle;
	}
}
