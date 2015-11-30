/**
 * 文件名：OutboundNotification.java
 *
 * 版本信息：
 * 日期：2012-8-6
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.sms;

import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;

/**
 * 
 * 
 * 项目名称：monitor
 * 类名称：OutboundNotification
 * 类描述：发送短信调用
 * 创建人：Guowm
 * 创建时间：2012-8-6 上午10:08:33
 * 修改人：Guowm
 * 修改时间：2012-8-6 上午10:08:33
 * 修改备注：
 * @version 
 *
 */
public class OutboundNotification implements IOutboundMessageNotification {

	@Override
	public void process(String gatewayId, OutboundMessage msg) {
		System.out.println("Outbound handler called from Gateway: " + gatewayId);

		System.out.println(msg);
	}

}
