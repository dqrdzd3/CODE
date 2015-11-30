/**
 * 文件名：CallNotification.java
 *
 * 版本信息：
 * 日期：2012-8-6
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.sms;

import org.smslib.ICallNotification;

/**
 * 
 * 
 * 项目名称：monitor
 * 类名称：CallNotification
 * 类描述：短信猫接口类用到的通知方法
 * 创建人：Guowm
 * 创建时间：2012-8-6 上午10:07:20
 * 修改人：Guowm
 * 修改时间：2012-8-6 上午10:07:20
 * 修改备注：
 * @version 
 *
 */
public class CallNotification implements ICallNotification{

	@Override
	public void process(String gatewayId, String callerId) {
		System.out.println(">>> New call detected from Gateway: " + gatewayId + " : " + callerId);
	}

}
