package com.hw.hwsafe.javamail.outer;

import java.util.List;

import com.hw.hwsafe.javamail.po.MailPO;
/**
 * 项目名称：hw-javamail
 * 类名称：ISendMail
 * 类描述：邮件发送接口
 * 创建人：陈浙东
 * 创建时间：2013-4-10
 *
 */
public interface ISendMail {
	/**
	 * 发送外部邮件，成功返回0     不成功返回1
	 * @param message
	 * @return int
	 * @author 陈浙东
	 */
	public int sendOuterMail(MailPO mail);
	
	/**
	 * 发送内部邮件，成功返回0     不成功返回1
	 * @param message
	 * @return int
	 * @author 陈浙东
	 */
	public String sendInnerMail(MailPO mail);
	
	/**
	 * 分页查询外部邮件发送记录
	 * @param mail
	 * @return
	 */
	public List<MailPO> getOuterListByPage(MailPO mail);
	
	/**
	 * 分页查询内部邮件列表
	 * @param mode send表示获取发送方的邮件（也就是发件箱）            receive表示收件箱获取邮件信息（就是收件箱）
	 * @param mail
	 * @return
	 */
	public List<MailPO> getInnerListByPage(MailPO mail, String mode);
	
	/**
	 * 按id查询邮件信息 
	 * @param id
	 * @param mode send表示获取发送方的邮件            receive表示收件箱获取邮件信息
	 * @return
	 * @throws Exception
	 */
	public MailPO getMailInfo(String id , String mode);

	/**
	 * 按收件箱删除邮件
	 * @param id          
	 * @author 陈浙东
	 * @create_time 2013-5-17 上午10:00:35
	 */
		
	public void deleteInnerMailReceive(String id) throws Exception;

	/**
	 * 按发件箱删除邮件
	 * @param id          
	 * @author 陈浙东
	 * @create_time 2013-5-17 上午10:00:54
	 */
		
	public void deleteInnerMailSend(String id) throws Exception;
}
