package com.hw.hwsafe.javamail.outer.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.javamail.dao.IMailDao;
import com.hw.hwsafe.javamail.outer.ISendMail;
import com.hw.hwsafe.javamail.po.AttachementPO;
import com.hw.hwsafe.javamail.po.MailPO;
import com.hw.hwsafe.javamail.util.ThreadUtil;
import com.hw.hwsafe.platform.constants.ConfConstants;
/**
 * 项目名称：hw-javamail
 * 类名称：SendMailImpl
 * 类描述：邮件发送接口实现类
 * 创建人：陈浙东
 * 创建时间：2013-4-10
 *
 */
public class SendMailImpl implements ISendMail {
	@Autowired
	private IMailDao mailDao;
	
	@Override
	public int sendOuterMail(MailPO mail) {
		int result = 1;
		try {
			//保存发送记录
			String uuid = UUID.randomUUID().toString();
			mail.setId(uuid);
			mail.setSendMail(ConfConstants.MAIL_ADDRESS);
			mail.setPort(ConfConstants.MAIL_PORT);
			mail.setSendTime(new Date());
			mailDao.insertOuterMailSend(mail);
			
			if(mail.getReceiveMailList() != null){
				List<String> list = mail.getReceiveMailList();
				for(String receive : list){
					MailPO po = new MailPO();
					po.setContent(mail.getContent());
					po.setTitle(mail.getTitle());
					po.setReceive(receive);
					po.setMode(mail.getMode());
					po.setAttachmentList(mail.getAttachmentList());
	
					ThreadUtil.execute(po);
					
					mail.setId(UUID.randomUUID().toString());
					mail.setSendId(uuid);
					mail.setSendTag("0");
					mail.setReceive(receive);
					mailDao.insertOuterMailReceive(mail);
				}
			}
			result = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public String sendInnerMail(MailPO mail) {
		int result = 1;
		String uuid = UUID.randomUUID().toString();
		try {
			
			mail.setId(uuid);
			mail.setSendTime(new Date());
			mail.setDeleteTag("0");
			mailDao.insertInnerMailSend(mail);
			if(mail.getReceiveMailList() != null){
				List<String> list = mail.getReceiveMailList();
				for(String receive : list){
					mail.setId(UUID.randomUUID().toString());
					mail.setSendId(uuid);
					mail.setReadTag("0");
					mail.setReceive(receive);
					mailDao.insertInnerMailReceive(mail);
				}
			}
			result = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uuid;
	}

	@Override
	public List<MailPO> getOuterListByPage(MailPO mail) {
		List<MailPO> list = null;
		try {
			int curPage = mail.getCurPage();
			int num = mail.getNum();
			int beginNum = (curPage - 1) * num + 1;
			int endNum = curPage * num;
			
			mail.setBeginNum(beginNum);
			mail.setEndNum(endNum);
			list = mailDao.getOuterListByPage(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<MailPO> getInnerListByPage(MailPO mail, String mode) {
		List<MailPO> list = null;
		try {
			int curPage = mail.getCurPage();
			int num = mail.getNum();
			int beginNum = (curPage - 1) * num + 1;
			int endNum = curPage * num;
			
			mail.setBeginNum(beginNum);
			mail.setEndNum(endNum);
			if("send".equals(mode)){
				list = mailDao.getInnerListByPageSend(mail);
			}else if("receive".equals(mode)){
				list = mailDao.getInnerListByPageReceive(mail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public MailPO getMailInfo(String id , String mode) {
		MailPO po = null;
		try {
			if("send".equals(mode)){
				po = mailDao.getMailInfoSend(id);
			}else if("receive".equals(mode)){
				po = mailDao.getMailInfoReceive(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return po;
	}

	/*
	   (non-Javadoc)
	 * @see com.hw.hwsafe.javamail.outer.ISendMail#deleteInnerMailReceive(java.lang.String)
	 */
		
	@Override
	public void deleteInnerMailReceive(String id) throws Exception{
		mailDao.updateReceiveDelete(id);
		
	}

	/*
	   (non-Javadoc)
	 * @see com.hw.hwsafe.javamail.outer.ISendMail#deleteInnerMailSend(java.lang.String)
	 */
		
	@Override
	public void deleteInnerMailSend(String id) throws Exception{
		mailDao.updateSendDelete(id);
		
	}

}
