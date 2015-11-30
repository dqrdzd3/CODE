/**
 * 文件名：SMSUtilsService.java
 *
 * 版本信息：
 * 日期：2012-8-6
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.sms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.InboundMessage;
import org.smslib.Library;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.InboundMessage.MessageClasses;
import org.smslib.Message.MessageEncodings;
import org.smslib.modem.SerialModemGateway;

/**
 * 
 * 项目名称：monitor
 * 类名称：SMSUtilsService
 * 类描述：java调用短信猫通用接口类
 * 
 * 创建人：Guowm
 * 创建时间：2012-8-6 上午10:02:58
 * 修改人：Guowm
 * 修改时间：2012-8-6 上午10:02:58
 * 修改备注：
 * @version 
 * 
 */
public class SMSUtils {

	public static Service srv = null;
	
	/**
	 * sms service是否启动
	 */
	private static Boolean isStart = false;

	/**
	 * * 初始化服务
	 * 
	 * @param gateWayId
	 *            网关Id
	 * @param port
	 *            串口
	 * @param baudrate
	 *            传输速率
	 * @param manufacturer
	 *            制造商
	 * @param model
	 *            模式
	 * @param inBound
	 *            是否设置为入站网关
	 * @param outBound
	 *            是否设置为出战网关
	 * @param pin
	 *            PIN码
	 */
	public static void initService(String gateWayId, String port, int baudrate,
			String manufacturer, String model, boolean inBound,
			boolean outBound, String pin) {
		System.out.println(Library.getLibraryDescription());
		System.out.println("SMSLib Version: " + Library.getLibraryVersion());

		// Create new Service object - the parent of all and the main interface
		// to you.
		if (srv == null) {
			srv = new Service();
		}

		// Create the Gateway representing the serial GSM modem.
		SerialModemGateway gateway = new SerialModemGateway(gateWayId, port,
				baudrate, manufacturer, model);
		// Do we want the Gateway to be used for Inbound messages? If not,
		// SMSLib will never read messages from this Gateway.
		gateway.setInbound(inBound);
		// Do we want the Gateway to be used for Outbound messages? If not,
		// SMSLib will never send messages from this Gateway.
		gateway.setOutbound(outBound);
		// PIN 码
		gateway.setSimPin(pin);

		OutboundNotification outboundNotification = new OutboundNotification();
		InboundNotification inboundNotification = new InboundNotification();
		CallNotification callNotification = new CallNotification();
		// Set up the notification methods. rec msg
		gateway.setInboundNotification(inboundNotification);
		gateway.setCallNotification(callNotification);

		// Set up the notification methods. send msg
		gateway.setOutboundNotification(outboundNotification);

		// Add the Gateway to the Service object.
		srv.addGateway(gateway);
	}

	/**
	 * * 初始化服务
	 * 
	 * @param gateWayId
	 *            网关Id
	 * @param port
	 *            串口
	 * @param baudrate
	 *            传输速率
	 * @param manufacturer
	 *            制造商
	 * @param model
	 *            模式
	 * @param inBound
	 * @param outBound
	 * @param pin
	 * @param outboundNotification
	 * @param inboundNotification
	 * @param callNotification
	 */
	public static void initService(String gateWayId, String port, int baudrate,
			String manufacturer, String model, boolean inBound,
			boolean outBound, String pin,
			OutboundNotification outboundNotification,
			InboundNotification inboundNotification,
			CallNotification callNotification) {
		System.out.println(Library.getLibraryDescription());
		System.out.println("SMSLib Version: " + Library.getLibraryVersion());

		// Create new Service object - the parent of all and the main interface
		// to you.
		srv = new Service();
		// Create the Gateway representing the serial GSM modem.
		SerialModemGateway gateway = new SerialModemGateway(gateWayId, port,
				baudrate, manufacturer, model);
		// Do we want the Gateway to be used for Inbound messages? If not,
		// SMSLib will never read messages from this Gateway.
		gateway.setInbound(inBound);
		// Do we want the Gateway to be used for Outbound messages? If not,
		// SMSLib will never send messages from this Gateway.
		gateway.setOutbound(outBound);
		// PIN 码
		gateway.setSimPin(pin);

		// Set up the notification methods. rec msg
		gateway.setInboundNotification(inboundNotification);
		gateway.setCallNotification(callNotification);

		// Set up the notification methods. send msg
		gateway.setOutboundNotification(outboundNotification);

		// Add the Gateway to the Service object.
		srv.addGateway(gateway);
	}

	public static Service getSrv() {
		return srv;
	}

	public static void setSrv(Service srv) {
		SMSUtils.srv = srv;
	}

	/**
	 * 
	 * 函数名：stopService 功能描述：停止服务 异常：
	 * 
	 * @创建人：Guowm 创建时间：2012-8-14 下午03:21:11
	 * @修改人：Guowm 修改时间：2012-8-14 下午03:21:11 修改原因描述：
	 */
	public static void stopService() {
		if (srv == null) {
			System.out.println("sms服务没有创建！");
		} else {
			try {
				if (isStart) {
					srv.stopService();
					isStart = false;
					System.out.println("sms服务已停止！");
				}
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (GatewayException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * 函数名：startService 功能描述：启动短信猫服务 异常：
	 * 
	 * @创建人：Guowm 创建时间：2012-8-14 下午03:12:43
	 * @修改人：Guowm 修改时间：2012-8-14 下午03:12:43 修改原因描述：
	 */
	public static void startService() {
		// Similarly, you may define as many Gateway objects, representing
		// various GSM modems, add them in the Service object and control all of
		// them.
		//
		// Start! (i.e. connect to all defined Gateways)
		if (srv == null) {
			System.out.println("sms服务没有创建！");
		} else if (srv.getGatewayList().size() < 1) {
			System.out.println("sms服务还没有加载网关信息！");
		} else {
			try {
				if (!isStart) {
					srv.startService();
					System.out.println("********************");
					System.out.println("sms服务启动！");
					isStart = true;
					Collection<AGateway> gateways = srv.getGatewayList();
					SerialModemGateway gateway = (SerialModemGateway) gateways
							.iterator().next();
					System.out.println();
					System.out
							.println("SMSService is started！\n Modem Information:");
					System.out.println("  Manufacturer: "
							+ gateway.getManufacturer());
					System.out.println("  Model: " + gateway.getModel());
					System.out.println("  Serial No: " + gateway.getSerialNo());
					System.out.println("  SIM IMSI: " + gateway.getImsi());
					System.out.println("  Signal Level: "
							+ gateway.getSignalLevel() + "%");
					System.out.println("  Battery Level: "
							+ gateway.getBatteryLevel() + "%");
					System.out.println();
					System.out.println("********************");
				}

			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (GatewayException e) {
				e.printStackTrace();
			} catch (SMSLibException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * 函数名：readMsg 功能描述：接受消息
	 * 
	 * @param flag
	 *            读取短信列表标志： 1 只读取未读短信，2读取所有短信，3读取已读短信
	 * @return 信息列表 @type List<InboundMessage> 异常：
	 * @创建人：Guowm 创建时间：2012-8-14 下午03:20:03
	 * @修改人：Guowm 修改时间：2012-8-14 下午03:20:03 修改原因描述：
	 */
	public static List<InboundMessage> readMsg(int flag) {
		List<InboundMessage> msgList = null; // 接受列表
		if (srv == null) {
			System.out.println("sms服务没有创建！");
		} else if (srv.getGatewayList().size() < 1) {
			System.out.println("sms服务还没有加载网关信息！");
		} else {
			msgList = new ArrayList<InboundMessage>();
			try {
				int sum = 0;
				if (flag == 1) {
					sum = srv.readMessages(msgList, MessageClasses.UNREAD);
				} else if (flag == 2) {
					sum = srv.readMessages(msgList, MessageClasses.ALL);
				} else if (flag == 3) {
					sum = srv.readMessages(msgList, MessageClasses.READ);
				}
				System.out.println("读取短信成功，共" + sum + "条！");
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (GatewayException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return msgList;
	}

	/**
	 * 
	 * 函数名：sendMsg 功能描述：发送短信
	 * 
	 * @param phoneNum
	 * @param content
	 * @return 短信封装类 @type OutboundMessage 异常：
	 * @创建人：Guowm 创建时间：2012-8-14 下午03:19:22
	 * @修改人：Guowm 修改时间：2012-8-14 下午03:19:22 修改原因描述：
	 */
	public static OutboundMessage sendMsg(String phoneNum, String content) {
		OutboundMessage msg = null;
		if (srv == null) {
			System.out.println("sms服务没有创建！");
		} else if (srv.getGatewayList().size() < 1) {
			System.out.println("sms服务还没有加载网关信息！");
		} else {
			try {
				msg = new OutboundMessage(phoneNum, content);
				msg.setEncoding(MessageEncodings.ENCUCS2);// 这句话是发中文短信必须的
				if (!srv.sendMessage(msg))
					msg = null;
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (GatewayException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}

	/**
	 * 
	 * 函数名：deleteMsg 功能描述：删除信息
	 * 
	 * @param msg
	 * @return 是否删除成功 @type boolean 异常：
	 * @创建人：Guowm 创建时间：2012-8-14 下午03:18:22
	 * @修改人：Guowm 修改时间：2012-8-14 下午03:18:22 修改原因描述：
	 */
	public static boolean deleteMsg(InboundMessage msg) {
		boolean isSuccess = false;
		if (srv == null) {
			System.out.println("sms服务没有创建！");
		} else if (srv.getGatewayList().size() < 1) {
			System.out.println("sms服务还没有加载网关信息！");
		} else {
			try {
				isSuccess = srv.deleteMessage(msg);
				if (isSuccess) {
					System.out.println("已经成功删除短信：\n" + msg);
				}
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (GatewayException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return isSuccess;
	}

	public static Boolean getIsStart() {
		return isStart;
	}

	public static void setIsStart(Boolean isStart) {
		SMSUtils.isStart = isStart;
	}
}
