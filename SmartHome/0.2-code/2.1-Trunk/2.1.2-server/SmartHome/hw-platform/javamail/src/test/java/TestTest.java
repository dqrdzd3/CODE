/**
 * 项目名称：hw-javamail
 * 类名称：TestTest
 * 类描述：单元测试类
 * 创建人：陈浙东
 * 创建时间：2013-4-10
 *
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hw.hwsafe.javamail.dao.IMailDao;
import com.hw.hwsafe.javamail.outer.ISendMail;
import com.hw.hwsafe.javamail.outer.impl.SendMailImpl;
import com.hw.hwsafe.javamail.po.MailPO;
import com.hw.hwsafe.javamail.util.ThreadUtil;
import com.hw.hwsafe.platform.constants.ConfConstants;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//		locations={
//			"classpath*:test-applicationContext.xml", 
//			"classpath:applicationContext-mybatis-javamail.xml"
//		}
//)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
//@Transactional
public class TestTest {

	@Autowired
	private IMailDao mailDao;
	
//	@Test
	public void logTest(){
		/**测试发送外部邮件并保存记录*/
		MailPO mail = new MailPO();
		mail.setContent("测试带附件的邮件！");
		mail.setTitle("测试");
		mail.setMode("text");
		
		List<String> list = new ArrayList<String>();
//		list.add("250704989@qq.com");

		
		//保存发送记录
		String uuid = UUID.randomUUID().toString();
		mail.setId(uuid);
		mail.setSendMail(ConfConstants.MAIL_ADDRESS);
		mail.setPort(ConfConstants.MAIL_PORT);
		mail.setSendTime(new Date());
		try {
			mailDao.insertOuterMailSend(mail);
			
			for(String receive : list){
				MailPO po = new MailPO();
				po.setContent(mail.getContent());
				po.setTitle(mail.getTitle());
				po.setReceive(receive);
				po.setMode(mail.getMode());
				ThreadUtil.execute(po);
				
				mail.setId(UUID.randomUUID().toString());
				mail.setSendId(uuid);
				mail.setSendTag("0");
				mail.setReceive(receive);
				mailDao.insertOuterMailReceive(mail);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**测试发送内部邮件*/
//		MailPO mail = new MailPO();
//		mail.setContent("Test inner mail!");
//		mail.setTitle("czd test");
//		mail.setSendId("chen zhe dong");
//		
//		List<String> list = new ArrayList<String>();
//		list.add("ma ning");
//		list.add("fu qiang");
//		mail.setReceiveMailList(list);
//		
//		mail.setCorpId("汉威电子");
//
//		String uuid = UUID.randomUUID().toString();
//		mail.setId(uuid);
//		mail.setSendTime(new Date());
//		mail.setDeleteTag("0");
//		try {
//			mailDao.insertInnerMailSend(mail);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		for(String receive : list){
//			mail.setId(UUID.randomUUID().toString());
//			mail.setSendId(uuid);
//			mail.setReadTag("0");
//			mail.setReceive(receive);
//			try {
//				mailDao.insertInnerMailReceive(mail);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

		/**测试获取外部邮件发送记录*/
//		try {
//			MailPO po = new MailPO();
//			po.setCurPage(1);
//			po.setNum(10);
//			
//			int curPage = po.getCurPage();
//			int num = po.getNum();
//			int beginNum = (curPage - 1) * num + 1;
//			int endNum = curPage * num;
//			
//			po.setBeginNum(beginNum);
//			po.setEndNum(endNum);
//			List<MailPO> list = mailDao.getOuterListByPage(po);
//			System.out.println(list.size());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/**测试获取内部邮件信息*/
//		try {
//			String id = "81e56e45-2fea-4490-bc3d-f95992525b1a";
//			MailPO po = mailDao.getMailInfoSend(id);
//			System.out.println(po.getTitle());
//			
//			String id1 = "ae4a611b-6070-4c8c-af3e-d9446307c278";
//			MailPO po1 = mailDao.getMailInfoReceive(id1);
//			System.out.println(po1.getTitle());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		/**测试分页获取内部邮件列表*/
//		try {
//			MailPO po = new MailPO();
//			po.setCurPage(1);
//			po.setNum(10);
//			
//			int curPage = po.getCurPage();
//			int num = po.getNum();
//			int beginNum = (curPage - 1) * num + 1;
//			int endNum = curPage * num;
//			
//			po.setBeginNum(beginNum);
//			po.setEndNum(endNum);
//			
//			po.setReceive("ma ning");
//			List<MailPO> list = mailDao.getInnerListByPageReceive(po);
//			System.out.println(list.size());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
}
