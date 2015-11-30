
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

import com.hw.hwsafe.sms.SMSUtils;
import com.hw.hwsafe.sms.czd.dao.ISMSDao;
import com.hw.hwsafe.sms.czd.po.MessagePO;
import com.hw.hwsafe.sms.czd.po.SMSCatPO;
import com.hw.hwsafe.sms.czd.util.SMSCatUtil;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//		locations={
//			"classpath*:test-applicationContext.xml", 
//			"classpath:applicationContext-mybatis-sms.xml"
//		}
//)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
//@Transactional
public class TestTest {

	@Autowired
	private ISMSDao smsDao;
	
//	@Test
	public void logTest(){
		/**测试发送内部短信*/
//		MessagePO message = new MessagePO();
//		message.setContent("This is a test message!!!");
//		message.setSendPeople("chen zhe dong");
//		message.setCorpId("汉威电子");
//		List<String> recievePeopleList = new ArrayList<String>();
//		recievePeopleList.add("ma ning");
//		recievePeopleList.add("fu qiang");
//		recievePeopleList.add("xiao du");
//		message.setRecievePeopleList(recievePeopleList);
//		
//		//接口内
//		message.setSendTime(new Date());
//		message.setSendDeleteFlag("0");
//		message.setReceiveReadFlag("0");
//		message.setReceiveDeleteFlag("0");
//		String uuid = UUID.randomUUID().toString();
//		message.setId(uuid);
//		smsDao.insertInnerSendMessage(message);
//		if(message.getRecievePeopleList() != null){
//			for(String recievePeople : message.getRecievePeopleList() ){
//				message.setSendPeople(uuid);
//				message.setId(UUID.randomUUID().toString());
//				message.setRecievePeople(recievePeople);
//				smsDao.insertInnerRecieveMessage(message);
//			}
//		}
		
		/**测试获取消息信息*/
//		MessagePO message = new MessagePO();
//		//设置查询条件
//		
//		//接口内
//		message = smsDao.getMessageInfoSend("96798e6c-38a0-4ae9-b276-82ad9f7d1499");
//		
//		message = smsDao.getMessageInfoReceive("d15bf8a9-ce1c-48ad-9414-8cb12cfa5d74");
		
		/**测试分页获取消息信息*/
//		MessagePO message = new MessagePO();
//		message.setCurPage(1);
//		message.setNum(10);
//		message.setRecievePeople("ma ning");
//		
//		//接口内
//		
//		
//		int curPage = message.getCurPage();
//		int num = message.getNum();
//		int beginNum = (curPage - 1) * num + 1;
//		int endNum = curPage * num;
//		
//		message.setBeginNum(beginNum);
//		message.setEndNum(endNum);
//		
//		List<MessagePO> list = smsDao.getInnerMessageByPageReceive(message);
//		
//		message.setCurPage(1);
//		message.setNum(10);
//		message.setSendPeople("chen zhe dong");
//
//		List<MessagePO> lis1t = smsDao.getInnerMessageByPageSend(message);
		
		
//		/**测试发送外部短信*/
//		MessagePO message = new MessagePO();
//		message.setContent("This is a test outer message!!!");
//		message.setCorpId("汉威电子");
//		List<String> recievePeopleList = new ArrayList<String>();
//		recievePeopleList.add("15803841976");
//		recievePeopleList.add("18545678912");
//
//		message.setRecievePeopleList(recievePeopleList);
//		
//		//接口内
//		try{
//			message.setSendTime(new Date());
//			String uuid = UUID.randomUUID().toString();
//			message.setId(uuid);
//			SMSCatPO catPO = SMSCatUtil.getSmsCatInfo();
//			message.setIp("localhost");
//			message.setPort(catPO.getPort());
//			message.setPin(catPO.getPin());
//			message.setGateway(catPO.getGateway());
//			message.setManufacturer(catPO.getManufacturer());
//			
//			smsDao.insertOuterMessageSend(message);
//			
//			if(message.getRecievePeopleList() != null){
//				for(String telphone : message.getRecievePeopleList() ){
//					message.setSendPeople(uuid);
//					message.setId(UUID.randomUUID().toString());
//					message.setTelphone(telphone);
//					message.setIp("0");
//					smsDao.insertOuterMessageReceive(message);
//				}
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		/**测试分页获取外部消息信息列表*/
		MessagePO message = new MessagePO();
		message.setCurPage(1);
		message.setNum(10);
		message.setCorpId("汉威电子");
		
		//接口内
		
		
		int curPage = message.getCurPage();
		int num = message.getNum();
		int beginNum = (curPage - 1) * num + 1;
		int endNum = curPage * num;
		
		message.setBeginNum(beginNum);
		message.setEndNum(endNum);
		
		try {
			List<MessagePO> list = smsDao.getOuterMessageByPage(message);
			System.out.println(list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
