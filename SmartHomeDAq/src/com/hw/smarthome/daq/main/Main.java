package com.hw.smarthome.daq.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.front.receive.AtmosphericDetectorReciver;
import com.hw.smarthome.daq.front.receive.FrontReceiveHandler;
import com.hw.smarthome.daq.front.receive.FrontReceiver;
import com.hw.smarthome.daq.front.send.FrontSender;
import com.hw.smarthome.daq.main.ui.console.ConsoleUI;
import com.hw.smarthome.daq.monitor.FilesMonitor;
import com.hw.smarthome.daq.monitor.JVMMonitor;
import com.hw.smarthome.daq.scheduler.receive.SchedulerReceiveHandler;
import com.hw.smarthome.daq.scheduler.send.SchedulerSendHandler;
import com.hw.smarthome.daq.server.Scheduler2Server;
import com.hw.smarthome.daq.server.Server2Scheduler;
import com.hw.smarthome.daq.server.socket.SocketReceiver;
import com.hw.smarthome.daq.server.socket.SocketSender;
import com.hw.smarthome.daq.server.ws.WSSender;

public class Main {
	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		DOMConfigurator.configure("conf/log4j.xml");
		initThread();
		initMonitor();
		initUI();
	}

	private static void initUI() {
		/* 控制台UI */
		new ConsoleUI(1000).start();
	}

	private static void initMonitor() {
		log.info("文件夹变动监控线程启动");
		new FilesMonitor(60 * 1000).start();
		log.info("内存监控线程启动");
		new JVMMonitor(1 * 1000).start();
	}

	private static void initThread() {

		log.info("前置机监听线程启动");
		ExecutorService frontReceiverExec = Executors
				.newCachedThreadPool();
		for (int i = 0; i < SysConstant.FRONTRECEIVER_THREAD_SIZE; i++) {
			frontReceiverExec.execute(new FrontReceiver(1));
		}
		log.info("前置机监听处理线程启动");
		ExecutorService frontReceiveHandler = Executors
				.newCachedThreadPool();
		for (int i = 0; i < SysConstant.FRONTRECEIVEHANDLER_THREAD_SIZE; i++) {
			frontReceiveHandler.execute(new FrontReceiveHandler(
					1));
		}
		log.info("前置机发送线程启动");
		ExecutorService frontSenderExec = Executors
				.newCachedThreadPool();
		for (int i = 0; i < SysConstant.FRONTSENDER_THREAD_SIZE; i++) {
			frontSenderExec.execute(new FrontSender(1));
		}
		log.info("任务调度接收线程启动");
		ExecutorService schedulerReceiveHandlerExec = Executors
				.newCachedThreadPool();
		for (int i = 0; i < SysConstant.SCHEDULERRECEIVEHANDLER_THREAD_SIZE; i++) {
			schedulerReceiveHandlerExec
					.execute(new SchedulerReceiveHandler(1));
		}
		log.info("任务调度发送线程启动");
		ExecutorService schedulerSendHandlerExec = Executors
				.newCachedThreadPool();
		for (int i = 0; i < SysConstant.SCHEDULERSENDHANDLER_THREAD_SIZE; i++) {
			schedulerSendHandlerExec
					.execute(new SchedulerSendHandler(1));
		}
		log.info("服务端接收线程启动");
		ExecutorService scheduler2ServerExec = Executors
				.newCachedThreadPool();
		WSSender.initClient(SysConstant.SCHEDULER2SERVER_THREAD_SIZE);
		for (int i = 0; i < SysConstant.SCHEDULER2SERVER_THREAD_SIZE; i++) {
			scheduler2ServerExec.execute(new Scheduler2Server(1,
					i));
		}
		log.info("服务端发送线程启动");
		new Server2Scheduler();
		log.info("任务调度Socket监听线程启动");
		ExecutorService socketReceiverExec = Executors
				.newCachedThreadPool();
		for (int i = 0; i < SysConstant.SOCKETRECEIVER_THREAD_SIZE; i++) {
			socketReceiverExec.execute(new SocketReceiver(1));
		}	
		log.info("任务调度Socket发送线程启动");
		ExecutorService socketSenderExec = Executors
				.newCachedThreadPool();
		for (int i = 0; i < SysConstant.SOCKETSENDER_THREAD_SIZE; i++) {
			socketSenderExec.execute(new SocketSender(1));
		}
		log.info("分布式大气监测监听启动");
		AtmosphericDetectorReciver atmosphericDetectorReciver = new AtmosphericDetectorReciver(
				1);
		atmosphericDetectorReciver.start();
	}
}
