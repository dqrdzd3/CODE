package com.hw.smarthome.daq.server;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.scheduler.queue.SchedulerQueue;
import com.hw.smarthome.daq.scheduler.send.encode.FrameAFN05Encodernew;
import com.hw.smarthome.daq.scheduler.send.encode.FrameAFN07Encoder;
import com.hw.smarthome.daq.scheduler.send.encode.FrameAFN08Encoder;
import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

public class Server2Scheduler implements MessageListener {

	private static Logger log = Logger
			.getLogger(Server2Scheduler.class);
	
	public Server2Scheduler() {
		this(SysConstant.SERVER_JMS_USER,
				SysConstant.SERVER_JMS_PASSWD,
				SysConstant.SERVER_JMS_MQ_ADDR);
	}
	
	public Server2Scheduler(String login, String password,
			String address) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setProperty(
					ConnectionConfiguration.imqAddressList,
					address);
			QueueConnection connection = factory
					.createQueueConnection(login, password);
			connection.start();
			Session session = connection.createQueueSession(
					false, Session.AUTO_ACKNOWLEDGE);
			Queue ioQueue = session
					.createQueue("ToSchedulerQueue");
			MessageConsumer consumer = session
					.createConsumer(ioQueue);
			consumer.setMessageListener(this);
			System.out.println("Listening to "+Server2Scheduler.class.getSimpleName());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	@Override
	public void onMessage(Message msg) {
		String msgText;
		DAqPo fromServer = null;
		try {
			if (msg instanceof TextMessage) {
				msgText = ((TextMessage) msg).getText();
				System.out.println("Anna: " + msgText);
			} else if (msg instanceof ObjectMessage) {
				/*new*/
				fromServer = (DAqPo) ((ObjectMessage) msg)
						.getObject();
				if (log.isDebugEnabled()) {
					log.debug("Debug获取JMS数据" + "："
							+ fromServer.getDataItems());
				}
				System.out.println("fromServer=="+fromServer);
				handler(fromServer);
				SchedulerQueue.addFromServer(fromServer);
			} else {
				System.out.println("Got a non-text message");
			}
		} catch (JMSException e) {
			System.out
					.println("Error while consuming a message: "
							+ e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @author 王珂
	 * @param fromServer
	 * @throws Exception
	 * @time 2015年10月29日 上午11:21:45
	 */
	private void handler(DAqPo fromServer) throws Exception {
		if (fromServer != null && fromServer.getDataItems() != null
				&& fromServer.getDataItems().size() > 0) {
			if (fromServer.getAfn() == 0x08) {
				FrameAFN08Encoder frameAFN08Encoder = new FrameAFN08Encoder();
				frameAFN08Encoder.handleContent(fromServer);
			} else if (fromServer.getAfn() == 0x07) {
				FrameAFN07Encoder frameAFN07Encoder = new FrameAFN07Encoder();
				frameAFN07Encoder.handleContent(fromServer);
			} else if(fromServer.getAfn() == 0x05){
				FrameAFN05Encodernew frameAFN05Encoder = new FrameAFN05Encodernew();
				frameAFN05Encoder.handleContent(fromServer);
			}
		} else {
			throw new Exception("从server接收到的JMS异常");
		}
	}
}
