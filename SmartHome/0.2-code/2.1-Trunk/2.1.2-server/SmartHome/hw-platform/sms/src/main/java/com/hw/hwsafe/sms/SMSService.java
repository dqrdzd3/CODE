/**
 * 文件名：SMSService.java
 *
 * 版本信息：
 * 日期：2012-8-6
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.sms;

import java.util.List;

import org.smslib.InboundMessage;
import org.smslib.OutboundMessage;

import com.hw.hwsafe.sms.czd.outer.ISMSService;

/**
 * 
 * 项目名称：monitor
 * 类名称：SMSService
 * 类描述：调用短信猫服务类
 * 创建人：Guowm
 * 创建时间：2012-8-6 下午02:49:04
 * 修改人：Guowm
 * 修改时间：2012-8-6 下午02:49:04
 * 修改备注：
 * @version 
 * 
 */
public class SMSService implements ISMSService{
	
	/**
	 * 
	 * 函数名：initService
	 * 功能描述：初始化短信猫服务类，并启动服务。
	 * @param gateWayId 网关HW
	 * @param port 端口COM1
	 * @param baudrate 速率9600
	 * @param manufacturer 
	 * @param model
	 * @param inBound
	 * @param outBound
	 * @param pin 		@type void
	 * 异常： 
	 * @创建人：Guowm
	 * 创建时间：2012-8-14 下午03:07:30
	 * @修改人：Guowm
	 * 修改时间：2012-8-14 下午03:07:30
	 * 修改原因描述：
	 */
//	public  void initService(String gateWayId,String port,int baudrate,String manufacturer,String model,
//			boolean inBound,boolean outBound,String pin){
//		try {
//			if(SMSUtils.srv==null){
//				SMSUtils.initService(gateWayId, port, baudrate, manufacturer, model, inBound, outBound, pin);
//				if(!SMSUtils.getIsStart()){
//					SMSUtils.startService();
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	public void initService(){
		try {
			if(SMSUtils.srv==null){
				SMSUtils.initService("HWSOFT","COM1", 9600, "HWSOFT", "HWSOFT", true, true, "0000");
				if(!SMSUtils.getIsStart()){
					SMSUtils.startService();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startService() {
		if (SMSUtils.getSrv() != null) {
			if (!SMSUtils.getIsStart()) {
				SMSUtils.startService();
			} else {

			}
		}
	}
	
	/**
	 * 接受消息
	 * @param flag 
	 * 1 只读取未读短信，2读取所有短信，3读取已读短信
	 * @return
	 */
	public List<InboundMessage> readMsg(int flag) {
		return SMSUtils.readMsg(flag);
	}
	
	/**
	 * 发送短信
	 * @param phoneNum 手机号
	 * @param content 短信内容
	 */
	public boolean sendMsg(String phoneNum,String content){
		OutboundMessage msg = SMSUtils.sendMsg(phoneNum, content);
		if(msg==null){
			return false;
		}else{
			//SMSMessageService ss = new SMSMessageService();
			/*SMSMessage s = new SMSMessage();
			s.setRecPhoneNum(msg.getRecipient());
			if(msg.getText()!=null){
				s.setSmsText(msg.getText());
			}
			s.setSendDate(msg.getDate());
			if(msg.getGatewayId()!=null){
				s.setGateWayId(msg.getGatewayId());
				s.setSendName("短信猫 "+msg.getGatewayId());
			}else{
				s.setSendName("短信猫 ");
			}
			s.setState("成功");*/
			//ss.insert(s);
			return true;
		}
	}
	
	public static boolean deleteMsg(InboundMessage msg){
		return SMSUtils.deleteMsg(msg);
	}
	
	public void stopService(){
		SMSUtils.stopService();
	}
	
	public static void main(String[] args) {
		SMSService smsService =new SMSService();
		smsService.initService();
		smsService.sendMsg("13592605164", "短信猫测试");
		SMSUtils.stopService();
	}
	
}
