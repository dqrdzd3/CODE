package com.hw.hwsafe.sms.czd.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.smslib.Library;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.Message.MessageEncodings;
import org.smslib.modem.SerialModemGateway;

import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.sms.CallNotification;
import com.hw.hwsafe.sms.InboundNotification;
import com.hw.hwsafe.sms.OutboundNotification;
import com.hw.hwsafe.sms.czd.exception.SMSException;
import com.hw.hwsafe.sms.czd.po.MessagePO;
import com.hw.hwsafe.sms.czd.po.SMSCatPO;

/**
 * 项目名称：hw-sms
 * 类名称：SMSCatUtil
 * 类描述：短信猫工具类
 * 创建人：陈浙东
 * 创建时间：2013-4-09
 *
 */
public final class SMSCatUtil {
	
	private static Service smsCatService = null;
	private static boolean isStart = false;
	public static Queue<MessagePO> highQueue = new ConcurrentLinkedQueue<MessagePO>();
	public static Queue<MessagePO> lowQueue = new ConcurrentLinkedQueue<MessagePO>();
	
	private SMSCatUtil(){}
	
	/**
	 * 获取配置文件中短信猫的配置
	 * @return
	 */
	public static SMSCatPO getSmsCatInfo(){
		SMSCatPO po = new SMSCatPO();
		
		po.setGateway(ConfConstants.SMS_GATEWAY);
		po.setPin(ConfConstants.SMS_PIN);
		po.setPort(ConfConstants.SMS_PORT);
		po.setMode(ConfConstants.SMS_MODE);
		po.setManufacturer(ConfConstants.SMS_MANUFACTURER);
		
		try{
			po.setBaudrate(ConfConstants.SMS_BAUDRATE);
		}catch (Exception e) {
			e.printStackTrace();
			throw new SMSException("短信猫配置错误，请检查配置文件！");
		}
		
		po.setInBound(ConfConstants.SMS_IN_BOUND);
		po.setOutBound(ConfConstants.SMS_OUT_BOUND);
		return po;
	}
	
	/**
	 * 初始化短信猫服务
	 * @param smsCat
	 */
	public static void initCatService(SMSCatPO smsCat) {
		System.out.println(Library.getLibraryDescription());
		System.out.println("SMSLib Version: " + Library.getLibraryVersion());


		smsCatService = new Service();
		
		SerialModemGateway gateway = new SerialModemGateway(smsCat.getGateway(), smsCat.getPort(),
				smsCat.getBaudrate(), smsCat.getManufacturer(), smsCat.getMode());
		
		gateway.setInbound(smsCat.isInBound());
		gateway.setOutbound(smsCat.isOutBound());
		gateway.setSimPin(smsCat.getPin());

		OutboundNotification outboundNotification = new OutboundNotification();
		InboundNotification inboundNotification = new InboundNotification();
		CallNotification callNotification = new CallNotification();
		gateway.setInboundNotification(inboundNotification);
		gateway.setCallNotification(callNotification);
		gateway.setOutboundNotification(outboundNotification);

		smsCatService.addGateway(gateway);
	}

	/**
	 * 判断短信猫服务是否初始化
	 * @return
	 */
	public static boolean isInitSMSCat(){
		return smsCatService == null ? false : true;
	}
	
	/**
	 * 启动短信猫服务
	 */
	public static void startService() {
		try {
			if (!isStart) {
				smsCatService.startService();
				isStart = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SMSException("启动SMS服务异常！");
		} 
	}
	
	/**
	 * 判断短信猫服务是否启动
	 * @return
	 */
	public static boolean isStartSMSCat(){
		return isStart;
	}
	
	/**
	 * 停止短信猫服务
	 */
	public static void stopService() {
		try {
			if (isStart) {
				smsCatService.stopService();
				isStart = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SMSException("停止SMS服务异常！");
		} 
	}
	
	/**
	 * 发送短信
	 * @param telphone
	 * @param content
	 * @return
	 */
	public static boolean sendMessage(String telphone , String content) {
		OutboundMessage outBoundMessage = null;
		boolean tag = false;
		try{
			outBoundMessage = new OutboundMessage(telphone, content);
			// 这句话是发中文短信必须的
			outBoundMessage.setEncoding(MessageEncodings.ENCUCS2);
			tag = smsCatService.sendMessage(outBoundMessage);
		}catch (Exception e) {
			e.printStackTrace();
			throw new SMSException("停止SMS服务异常！");
		}
		return tag;
	}

}
