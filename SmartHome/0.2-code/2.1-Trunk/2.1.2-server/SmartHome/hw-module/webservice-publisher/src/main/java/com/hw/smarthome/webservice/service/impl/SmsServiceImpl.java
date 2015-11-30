package com.hw.smarthome.webservice.service.impl;


import com.hw.hwsafe.sms.SMSService;
import com.hw.smarthome.webservice.service.ISmsService;

public class SmsServiceImpl implements ISmsService{

	@Override
	public void sendMsg(String phoneNum, String content) {
		SMSService smsService =new SMSService();
		smsService.sendMsg(phoneNum, content); //4：向用户手机号发送短信，短信内容为报警信息
		System.out.println("向用户手机号发送短信，短信内容为报警信息");
	}
}
