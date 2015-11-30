package com.hw.hwsafe.smart.util;

import com.hw.hwsafe.smart.service.SmsCallBack;

public class SmsVerifyKit {
	private String appkey;
	private String phone ;
	private String zone ;
	private String code ;
	private SmsCallBack callBack;
	/**
	 * 
	 * @param appkey 应用KEY
	 * @param phone 电话号码 xxxxxxxxx
	 * @param zone 区号 86
	 * @param code 验证码 xx
	 */
	public SmsVerifyKit(String appkey, String phone, String zone, String code) {
		super();
		this.appkey = appkey;
		this.phone = phone;
		this.zone = zone;
		this.code = code;
	
	}
	public SmsVerifyKit(String appkey, String phone, String zone, String code,SmsCallBack callback) {
		super();
		this.appkey = appkey;
		this.phone = phone;
		this.zone = zone;
		this.code = code;
		this.callBack = callback;
	}

	/**
	 * 服务端发起验证请求验证移动端(手机)发送的短信
	 * @return
	 * @throws Exception
	 */
	public  String  go() throws Exception{
		String result = "";
		String result1 = "";
		String address = "https://api.sms.mob.com/sms/verify";
		String address1 =  "https://web.sms.mob.com/sms/verify";
		MobClient client = null;
		MobClient client1 = null;
		try {
			client = new MobClient(address);
			client.addParam("appkey", appkey).addParam("phone", phone)
					.addParam("zone", zone).addParam("code", code);
			client.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			client.addRequestProperty("Accept", "application/json");
			result = client.post();
			//callBack.execute(result);
			
			client1 = new MobClient(address1);
			client1.addParam("appkey", appkey).addParam("phone", phone)
					.addParam("zone", zone).addParam("code", code);
			client1.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			client1.addRequestProperty("Accept", "application/json");
			result1 = client1.post();
			
		
		
		} finally {
			client.release();
		}
		
		if (result.contains("200")) {
			return result;
		}else {
			return result1;
		}
		
	}
	public void sendYzm(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					go();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}
