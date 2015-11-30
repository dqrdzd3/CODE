package com.hw.hwsafe.javamail.util;

import java.util.ArrayList;
import java.util.List;

import com.hw.hwsafe.javamail.outer.ISendMail;
import com.hw.hwsafe.javamail.outer.impl.SendMailImpl;
import com.hw.hwsafe.javamail.po.AttachementPO;
import com.hw.hwsafe.javamail.po.MailPO;
/**
 * 项目名称：hw-javamail
 * 类名称：TestThread
 * 类描述：测试类
 * 创建人：陈浙东
 * 创建时间：2013-4-10
 *
 */
public class TestThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MailPO mail = new MailPO();
		mail.setContent("测试带附件的邮件！");
		mail.setTitle("测试");
		mail.setMode("html");
		List<AttachementPO> attachmentList = new ArrayList<AttachementPO>();
		attachmentList.add(new AttachementPO("软件部绩效考核方案.rar","d:/软件部绩效考核方案.rar"));
		attachmentList.add(new AttachementPO("aaa.sql","d:/aaa.sql"));
		mail.setAttachmentList(attachmentList);
		
		List<String> list = new ArrayList<String>();
		list.add("7262662@qq.com");
		//list.add("250704989@qq.com");
		
		mail.setReceiveMailList(list);
		
		ISendMail sendMail = new SendMailImpl();
		sendMail.sendOuterMail(mail);
	}
}
