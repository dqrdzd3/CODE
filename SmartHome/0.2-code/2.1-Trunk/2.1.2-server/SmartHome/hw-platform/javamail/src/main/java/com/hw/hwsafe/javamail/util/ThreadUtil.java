package com.hw.hwsafe.javamail.util;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.hw.hwsafe.javamail.po.AttachementPO;
import com.hw.hwsafe.javamail.po.MailPO;
import com.hw.hwsafe.javamail.po.MailSenderPO;
import com.hw.hwsafe.platform.constants.ConfConstants;

/**
 * 项目名称：hw-javamail
 * 类名称：ThreadUtil
 * 类描述：线程池，通过线程来实现javamail发送邮件
 * 创建人：陈浙东
 * 创建时间：2013-4-10
 *
 */
public class ThreadUtil {
	/**
	 * 私有构造方法，防止别人初始化
	 */
	private ThreadUtil(){
		
	}
	
	private static final Executor threadPool = new ScheduledThreadPoolExecutor(10);   //线程池

	/**
	 * 创建线程执行任务
	 * @return Runnable
	 */
	private static Runnable createTask(final MailPO mail) {
		return new Runnable() {
			public void run() {
				//发送邮件
				MailSenderPO po = new MailSenderPO();
				po.setFromAddress(ConfConstants.MAIL_ADDRESS);
				po.setMailServerHost(ConfConstants.MAIL_HOST);
				po.setMailServerPort(ConfConstants.MAIL_PORT);
				po.setSubject(mail.getTitle());
				po.setContent(mail.getContent());
				po.setPassword(ConfConstants.MAIL_PASSWORD);
				po.setUserName(ConfConstants.MAIL_USERNAME);
				po.setToAddress(mail.getReceive());
				po.setValidate(ConfConstants.MAIL_VALIDATE);
				
				//循环添加附件
				if(mail.getAttachmentList() != null){
					String[] names = new String[mail.getAttachmentList().size()];
					String[] paths = new String[mail.getAttachmentList().size()];
					int count = 0;
					for(AttachementPO map : mail.getAttachmentList()){
						names[count] = map.getName();
						paths[count] = map.getPath();
						count++;
					}
					po.setAttachFileNames(names);
					po.setAttachFilePaths(paths);
				}
				
				if("text".equals(mail.getMode())){
					MailSenderUtil.sendTextMail(po);
				}
				else if("html".equals(mail.getMode())){
					MailSenderUtil.sendHtmlMail(po);
				}
			}
		};
	}
	
	public static void execute(MailPO mail){
		threadPool.execute(createTask(mail));
	}
}
