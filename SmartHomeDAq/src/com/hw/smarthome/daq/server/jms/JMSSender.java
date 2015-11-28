package com.hw.smarthome.daq.server.jms;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.util.Ln;
import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

public class JMSSender {
	private static Logger log = Logger
			.getLogger(JMSSender.class);
	private static JMSSender instance = null;

	public static JMSSender getInstance() {
		if (instance == null) {
			instance = new JMSSender();
		}
		return instance;
	}

	private Session session = null;
	private MessageProducer queueSender = null;
	private String login = "";
	private String password = "";
	private String address = "";

	public static void main(String[] args) {
		JMSSender jmsSender = new JMSSender();
		jmsSender.send("Hello world!");
		jmsSender.send("sdfsdf");
		jmsSender.send("2");
		jmsSender.closeConnection();
	}

	public JMSSender() {
		this(SysConstant.SERVER_JMS_USER,
				SysConstant.SERVER_JMS_PASSWD,
				SysConstant.SERVER_JMS_MQ_ADDR);
	}

	public JMSSender(String login, String password,
			String address) {
		this.login = login;
		this.password = password;
		this.address = address;
		init(login, password, address);
	}

	public void init(String login, String password,
			String address) {

		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setProperty(
					ConnectionConfiguration.imqAddressList,
					address);
			QueueConnection connection = factory
					.createQueueConnection(login, password);
			connection.start();
			session = connection.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Queue ioQueue = session
					.createQueue("FromSchedulerQueue");
			queueSender = session.createProducer(ioQueue);

		} catch (Exception e) {
			Ln.e(this.getClass(), e.getMessage(), e);
		}

	}

	public void send(DAqPo po) {
		ObjectMessage outMsg = null;
		try {
			if (session == null) {
				init(login, password, address);
			}
			outMsg = session.createObjectMessage(po);
			queueSender.send(outMsg);
			if (Ln.IS_DEBUG) {
				log.debug("JMS发送成功:" + po);
			}
		} catch (JMSException e) {
			init(login, password, address);
			Ln.e(this.getClass(), e.getMessage(), e);
		}
	}

	public void send(String msg) {
		TextMessage outMsg = null;
		try {
			outMsg = session.createTextMessage(msg);
			queueSender.send(outMsg);
			if (Ln.IS_DEBUG) {
				log.debug("JMS发送成功:" + outMsg);
			}
		} catch (JMSException e) {
			Ln.e(this.getClass(), e.getMessage(), e);
		}
	}

	public void closeConnection() {
		try {
			queueSender.close();
			session.close();
		} catch (JMSException e) {
			Ln.e(this.getClass(), e.getMessage(), e);
		}
	}
}
