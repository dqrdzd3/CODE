/**
 * 文件名：MailAction.java
 *
 * 版本信息：
 * 日期：2013-4-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2013 
 * 版权所有
 *
 */
package com.hw.hwsafe.javamail.action;

import java.io.IOException;
import java.io.PrintWriter;

import com.hw.hwsafe.javamail.outer.ISendMail;
import com.hw.hwsafe.javamail.po.MailPO;
import com.hw.hwsafe.platform.action.BaseAction;

/**
 * 
 * 项目名称：hw-javamail
 * 类名称：MailAction
 * 类描述：邮件action，用来展示邮件内容，主要用于业务消息提醒
 * 创建人：陈浙东
 * 创建时间：2013-4-12 上午9:51:37
 * 修改人：陈浙东
 * 修改时间：2013-4-12 上午9:51:37
 * 修改备注：
 * @version 
 * 
 */
public class MailAction extends BaseAction {
	private ISendMail mailService;
	
	public String doView(){
		String id = getRequest().getParameter("id");
		MailPO po = mailService.getMailInfo(id, "receive");
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

	
	public ISendMail getMailService() {
		return mailService;
	}

	public void setMailService(ISendMail mailService) {
		this.mailService = mailService;
	}
	
	
}
