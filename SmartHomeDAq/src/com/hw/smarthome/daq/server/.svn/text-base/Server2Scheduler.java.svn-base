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

import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.scheduler.queue.SchedulerQueue;
import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

public class Server2Scheduler implements MessageListener {

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
				fromServer = (DAqPo) ((ObjectMessage) msg)
						.getObject();
				SchedulerQueue.addFromServer(fromServer);
			} else {
				System.out.println("Got a non-text message");
			}
		} catch (JMSException e) {
			System.out
					.println("Error while consuming a message: "
							+ e.getMessage());
		}
	}
}
