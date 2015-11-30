package com.hw.hwsafe.sms.czd.outer;

import java.util.List;

import org.smslib.InboundMessage;

/**
 * <pre>
 * 项目名称：hw-sms
 * 类名称：ISMSService
 * 类描述：TODO
 * 创建人：仝泳
 * 创建时间：2014-4-28
 * 修改人:
 * 修改时间:
 * </pre>
 */
public interface ISMSService {

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

	public void initService();

	public void startService();

	/**
	 * 接受消息
	 * @param flag 
	 * 1 只读取未读短信，2读取所有短信，3读取已读短信
	 * @return
	 */
	public List<InboundMessage> readMsg(int flag);

	/**
	 * 发送短信
	 * @param phoneNum 手机号
	 * @param content 短信内容
	 */
	public boolean sendMsg(String phoneNum, String content);

	public void stopService();

}