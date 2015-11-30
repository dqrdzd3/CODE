package com.hw.hwsafe.javamail.dao;

import java.util.HashMap;
import java.util.List;

import com.hw.hwsafe.javamail.po.MailPO;


/**
 * 项目名称：hw-javamail
 * 类名称：IMailDao
 * 类描述：邮件发送Dao接口
 * 创建人：陈浙东
 * 创建时间：2013-4-10
 *
 */
public interface IMailDao {
	/**
	 * 按条件获取需要发送的邮件信息
	 * @return
	 * @throws Exception
	 */
	public List getmailmsgforsend()throws Exception;
	
	/**
	 * 查询附件内容
	 * @param mailid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getattachment(String mailid)throws Exception;
	
	/**
	 * 查询正文件附件内容(图片)
	 * @param mailid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getimgcontent(String mailid)throws Exception;
	
	/**
	 * 查询邮件标题及正文内容
	 * @param mailid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, Object> getmailcontext(String mailid)throws Exception;
	
	/**
	 * 发送完成之后修改发送状态
	 * @param mailid
	 * @throws Exception
	 */
	public void updsendstate(String mailid)throws Exception;
	
	/**
	 * 保存内部邮件
	 * @param mail
	 * @throws Exception
	 */
	public void insertInnerMail(MailPO mail) throws Exception;
	
	/**
	 * 保存外部邮件记录（发）
	 * @param mail
	 * @throws Exception
	 */
	public void insertOuterMailSend(MailPO mail) throws Exception;
	
	/**
	 * 保存外部邮件记录（收）
	 * @param mail
	 * @throws Exception
	 */
	public void insertOuterMailReceive(MailPO mail) throws Exception;
	
	/**
	 * 分页查询外部邮件发送记录
	 * @param mail
	 * @return
	 * @throws Exception
	 */
	public List<MailPO> getOuterListByPage(MailPO mail) throws Exception;
	
	/**
	 * 保存内部邮件记录（发）
	 * @param mail
	 * @throws Exception
	 */
	public void insertInnerMailSend(MailPO mail) throws Exception;
	
	/**
	 * 保存内部邮件记录（收）
	 * @param mail
	 * @throws Exception
	 */
	public void insertInnerMailReceive(MailPO mail) throws Exception;
	
	/**
	 * 按发送方查询邮件信息 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public MailPO getMailInfoSend(String id) throws Exception; 
	
	/**
	 * 按接收方查询邮件信息 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public MailPO getMailInfoReceive(String id) throws Exception; 
	
	/**
	 * 分页查询内部邮件列表（收件箱）
	 * @param mail
	 * @return
	 * @throws Exception
	 */
	public List<MailPO> getInnerListByPageReceive(MailPO mail) throws Exception;
	
	/**
	 * 分页查询内部邮件列表（发件箱）
	 * @param mail
	 * @return
	 * @throws Exception
	 */
	public List<MailPO> getInnerListByPageSend(MailPO mail) throws Exception;

	/**
	 * 这里是函数说明
	 * @param id          
	 * @author 陈浙东
	 * @create_time 2013-5-17 上午10:04:13
	 */
		
	public void updateReceiveDelete(String id) throws Exception;

	/**
	 * 这里是函数说明
	 * @param id          
	 * @author 陈浙东
	 * @create_time 2013-5-17 上午10:04:17
	 */
		
	public void updateSendDelete(String id) throws Exception;
}
