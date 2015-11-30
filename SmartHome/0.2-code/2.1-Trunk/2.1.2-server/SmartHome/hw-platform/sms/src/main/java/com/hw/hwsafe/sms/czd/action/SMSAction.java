/**
 * 文件名：SMSAction.java
 *
 * 版本信息：
 * 日期：2013-4-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2013 
 * 版权所有
 *
 */
package com.hw.hwsafe.sms.czd.action;

import java.io.IOException;
import java.io.PrintWriter;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.sms.czd.outer.ISendMessage;
import com.hw.hwsafe.sms.czd.po.MessagePO;

/**
 * 
 * 项目名称：hw-sms
 * 类名称：SMSAction
 * 类描述：短信action，用来展示短消息内容，主要用于业务消息提醒
 * 创建人：陈浙东
 * 创建时间：2013-4-12 上午11:13:44
 * 修改人：陈浙东
 * 修改时间：2013-4-12 上午11:13:44
 * 修改备注：
 * @version 
 * 
 */
public class SMSAction extends BaseAction {
	private ISendMessage messageService;
	public String doView(){
		String id = getRequest().getParameter("id");
		MessagePO po = messageService.getMessageInfoReceive(id);
		try {
			getResponse().setCharacterEncoding("utf-8");
			PrintWriter out = getResponse().getWriter();
			out.write(po.getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public ISendMessage getMessageService() {
		return messageService;
	}
	public void setMessageService(ISendMessage messageService) {
		this.messageService = messageService;
	}
	
	
}
