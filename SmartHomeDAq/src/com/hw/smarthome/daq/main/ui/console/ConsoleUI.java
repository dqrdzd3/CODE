package com.hw.smarthome.daq.main.ui.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.hw.smarthome.daq.front.queue.FrontQueue;
import com.hw.smarthome.daq.front.receive.FrontReceiver;
import com.hw.smarthome.daq.main.po.QueueViewPo;
import com.hw.smarthome.daq.main.service.DAqService;
import com.hw.smarthome.daq.main.service.impl.DAqServiceImpl;
import com.hw.smarthome.daq.main.ui.constant.ConsoleConstant;
import com.hw.util.ThreadClassTemplet;

public class ConsoleUI extends ThreadClassTemplet {
	private DAqService service = DAqServiceImpl.getInstance();
	private Scanner scanner = new Scanner(System.in);

	private static Logger log = Logger.getLogger(ConsoleUI.class);
	public ConsoleUI(int sleepTimes) {
		super(sleepTimes);
		mainUI();
	}

	@Override
	protected void runFun() {
		int in = 0;
		try {
			in = Integer.valueOf(scanner.nextLine());
		} catch (Exception e) {
			inputError();
		}
		System.out
				.println("================================================================");
		switch (in) {
		case ConsoleConstant.MENU:
			menu();
			break;
		case ConsoleConstant.QUEUY_QUEUE:
			queryQueue();
			break;
		/*wk 2015.9.8  增加sensorId查询 */
		case ConsoleConstant.SENSOR_QUEUE:
			sensorQueue();
			break;
		default:
			break;
		}
		System.out
				.println("================================================================");
	}

	private void inputError() {
		System.out.println("请输入规范的的数字指令");
		menu();
	}

	private void mainUI() {
		System.out.println("*************************");
		System.out.println("*  欢迎进入曾的数据采集客户端    *");
		System.out.println("*************************");
		menu();
	}

	private void menu() {
		System.out.println("输入【】内数字可以选择进入对应的功能：");
		System.out.println();
		System.out.println("【1】返回菜单");
		System.out.println("【2】查询缓存队列");
		/*wk 2015.9.8  增加sensorId查询 */
		System.out.println("【3】设备查询");
	}

	private void queryQueue() {
		QueueViewPo view = service.monitorQueue();
		log.info("前置机累计连接数:" + FrontReceiver.sumLink);
		log.info("[前置机]接受的[连接缓存]队列 ................."
				+ view.getReceiveCache());
		log.info("[连接缓存]发送到[任务调度]的队列 .............."
				+ view.getToScheduler());
		log.info("[前置机]发送给[硬件]的队列 .................."
				+ view.getToTerminal());
		log.info("任务调度");
		log.info("[任务调度]发送给[前置机]待处理队列 ............"
				+ view.getToFrontWait());
		log.info("[任务调度]发送给[前置机]的已处理队列 ..........."
				+ view.getToFront());
		log.info("服务端");
		log.info("[任务调度]发送给[服务端]队列 ................."
				+ view.getToServer());
		log.info("[任务调度]接收[服务端]队列 ..................."
				+ view.getFromServer());
		log.info("[采集]发送给[服务端]任务的未处理队列 ..................."
						+ view.getSocketToServer());
	}
	
	/*wk 2015.9.8  增加sensorId查询 */
	private void sensorQueue() {
		System.out.println("请输入sensorId");
		BufferedReader str = new BufferedReader(new InputStreamReader(System.in));  
		String sensorId;
		try {
			sensorId = str.readLine();
			System.out.println("输入的sersorId为："+sensorId);
			//Scanner sensorId = new Scanner(System.in);
			System.out.println(FrontQueue.getCurrentLinksMap().get(sensorId));
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
}
