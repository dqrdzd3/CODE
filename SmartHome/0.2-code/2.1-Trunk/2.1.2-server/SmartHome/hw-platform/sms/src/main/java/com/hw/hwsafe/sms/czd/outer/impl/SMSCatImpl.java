package com.hw.hwsafe.sms.czd.outer.impl;

import com.hw.hwsafe.sms.czd.outer.ISMSCat;
import com.hw.hwsafe.sms.czd.thread.SendMessageThread;
import com.hw.hwsafe.sms.czd.util.SMSCatUtil;

/**
 * 项目名称：hw-sms
 * 类名称：ISMSCat
 * 类描述：短信猫对外接口实现类
 * 创建人：陈浙东
 * 创建时间：2013-4-09
 *
 */
public class SMSCatImpl implements ISMSCat {
	 
	@Override
	public void initCatService() throws Exception{
		
		SMSCatUtil.initCatService(SMSCatUtil.getSmsCatInfo());
	}

	@Override
	public void startService() throws Exception{
		SMSCatUtil.startService();
	}

	@Override
	public void stopService() throws Exception{
		SMSCatUtil.stopService();
	}

	@Override
	public void startThread() {
		SendMessageThread t = new SendMessageThread();
		t.start();
	}

}
