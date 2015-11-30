package com.hw.hwsafe.sms.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.hw.hwsafe.sms.SMSService;
import com.hw.hwsafe.sms.czd.outer.ISMSService;
import com.hw.hwsafe.sms.socket.SMSSender;


/**
 * <pre>
 * 项目名称：hw-sms
 * 类名称：AutoStartSMSServiceServlet
 * 类描述：启动SMS服务
 * 创建人：仝泳
 * 创建时间：2014-4-29
 * 修改人:
 * 修改时间:
 * </pre>
 */
public class AutoStartSMSServiceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		SMSSender.getInstant().start(); // 发送短信线程
		ISMSService smsService =new SMSService();
		// 初始化短信猫服务
		smsService.initService();
		/*System.out.println("\n\n\n\n\n");
		System.out.println("自启动servlet 运行...............");
		System.out.println("\n\n\n\n\n");*/
		
	}
	

}
