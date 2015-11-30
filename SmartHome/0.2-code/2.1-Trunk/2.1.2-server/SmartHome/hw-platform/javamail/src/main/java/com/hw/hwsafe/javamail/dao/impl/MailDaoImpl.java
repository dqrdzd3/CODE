package com.hw.hwsafe.javamail.dao.impl;

import java.util.HashMap;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.hw.hwsafe.javamail.dao.IMailDao;
import com.hw.hwsafe.javamail.po.MailPO;
/**
 * 项目名称：hw-javamail
 * 类名称：MailDaoImpl
 * 类描述：邮件发送Dao实现类
 * 创建人：陈浙东
 * 创建时间：2013-4-10
 *
 */
public class MailDaoImpl extends SqlSessionDaoSupport implements IMailDao {

	@Override
	public List getmailmsgforsend() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Object> getattachment(String mailid)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Object> getimgcontent(String mailid)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Object> getmailcontext(String mailid)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updsendstate(String mailid) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertInnerMail(MailPO mail) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertOuterMailSend(MailPO mail) throws Exception {
		getSqlSession().getMapper(IMailDao.class).insertOuterMailSend(mail);

	}

	@Override
	public void insertOuterMailReceive(MailPO mail) throws Exception {
		getSqlSession().getMapper(IMailDao.class).insertOuterMailReceive(mail);

	}

	@Override
	public List<MailPO> getOuterListByPage(MailPO mail) throws Exception {
		return getSqlSession().getMapper(IMailDao.class).getOuterListByPage(mail);
	}

	@Override
	public void insertInnerMailSend(MailPO mail) throws Exception {
		getSqlSession().getMapper(IMailDao.class).insertInnerMailSend(mail);
		
	}

	@Override
	public void insertInnerMailReceive(MailPO mail) throws Exception {
		getSqlSession().getMapper(IMailDao.class).insertInnerMailReceive(mail);
		
	}

	@Override
	public MailPO getMailInfoSend(String id) throws Exception {
		return getSqlSession().getMapper(IMailDao.class).getMailInfoSend(id);
	}

	@Override
	public MailPO getMailInfoReceive(String id) throws Exception {
		return getSqlSession().getMapper(IMailDao.class).getMailInfoReceive(id);
	}

	@Override
	public List<MailPO> getInnerListByPageReceive(MailPO mail) throws Exception {
		return getSqlSession().getMapper(IMailDao.class).getInnerListByPageReceive(mail);
	}

	@Override
	public List<MailPO> getInnerListByPageSend(MailPO mail) throws Exception {
		return getSqlSession().getMapper(IMailDao.class).getInnerListByPageSend(mail);
	}

	/*
	   (non-Javadoc)
	 * @see com.hw.hwsafe.javamail.dao.IMailDao#deleteInnerMailReceive(java.lang.String)
	 */
		
	@Override
	public void updateReceiveDelete(String id) throws Exception {
		getSqlSession().getMapper(IMailDao.class).updateReceiveDelete(id);
		
	}

	/*
	   (non-Javadoc)
	 * @see com.hw.hwsafe.javamail.dao.IMailDao#deleteInnerMailSend(java.lang.String)
	 */
		
	@Override
	public void updateSendDelete(String id) throws Exception {
		getSqlSession().getMapper(IMailDao.class).updateSendDelete(id);
		
	}

}
