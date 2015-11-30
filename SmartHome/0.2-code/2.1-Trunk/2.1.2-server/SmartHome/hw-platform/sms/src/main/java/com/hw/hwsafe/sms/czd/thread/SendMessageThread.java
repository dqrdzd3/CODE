package com.hw.hwsafe.sms.czd.thread;

import com.hw.hwsafe.sms.czd.po.MessagePO;
import com.hw.hwsafe.sms.czd.util.SMSCatUtil;

/**
 * 项目名称：hw-sms
 * 类名称：SendMessageThread
 * 类描述：线程类，扫描队列，获取要发送外部短信的手机号和内容，进行发送
 * 创建人：陈浙东
 * 创建时间：2013-4-09
 *
 */
public class SendMessageThread extends Thread {
	public void run(){
		while(true){
			MessagePO po = SMSCatUtil.highQueue.poll();
			if(po == null){
				po = SMSCatUtil.lowQueue.poll();
			}
			
			if(po != null){
				SMSCatUtil.sendMessage(po.getTelphone(), po.getContent());
//				ISendMessage sm = new SendMessageImpl();
				//sm.insertOuterMessageRecord(po);
			}
		}
	}
	
}
