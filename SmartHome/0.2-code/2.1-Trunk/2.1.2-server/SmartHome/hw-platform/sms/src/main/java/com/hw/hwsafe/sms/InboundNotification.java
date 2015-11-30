/**
 * 文件名：InboundNotification.java
 *
 * 版本信息：
 * 日期：2012-8-6
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.sms;

import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageTypes;

/**
 * 
 * 
 * 项目名称：monitor
 * 类名称：InboundNotification
 * 类描述：监听新短信类
 * 创建人：Guowm
 * 创建时间：2012-8-6 上午10:07:59
 * 修改人：Guowm
 * 修改时间：2012-8-6 上午10:07:59
 * 修改备注：
 * @version 
 *
 */
public class InboundNotification implements IInboundMessageNotification {

	/**
	 * 短信猫接收到信息后的操作，插入数据库，并删除该条短信
	 *
	 * @see org.smslib.IInboundMessageNotification#process(java.lang.String, org.smslib.Message.MessageTypes, org.smslib.InboundMessage)
	 */
	@Override
	public void process(String gatewayId, MessageTypes msgType,
			InboundMessage msg) {
		if (msgType == MessageTypes.INBOUND)
			System.out
					.println(">>> New Inbound message detected from Gateway: "
							+ gatewayId);
		else if (msgType == MessageTypes.STATUSREPORT)
			System.out
					.println(">>> New Inbound Status Report message detected from Gateway: "
							+ gatewayId);
		System.out.println(msg);
		/*
		 * SMSMessageService ss = new SMSMessageService(); SMSMessage s = new
		 * SMSMessage(); s.setSendPhoneNum(msg.getOriginator());
		 * if(!"ENC8BIT".equals(msg.getEncoding())){
		 * s.setSmsText(msg.getText()); } s.setSendDate(msg.getDate());
		 * s.setGateWayId(msg.getGatewayId());
		 * s.setRecName("短信猫 "+msg.getGatewayId()); s.setState("成功");
		 * ss.insert(s);
		 */
		SMSUtils.deleteMsg(msg);
//		try {
			// Uncomment following line if you wish to delete the message upon
			// arrival.
			// srv.deleteMessage(msg);
//		} catch (Exception e) {
//			System.out.println("Oops!!! Something gone bad...");
//			e.printStackTrace();
//		}
	}

}
