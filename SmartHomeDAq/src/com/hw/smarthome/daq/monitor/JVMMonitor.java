package com.hw.smarthome.daq.monitor;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.main.po.QueueViewPo;
import com.hw.smarthome.daq.main.service.DAqService;
import com.hw.smarthome.daq.main.service.impl.DAqServiceImpl;
import com.hw.util.ThreadClassTemplet;

public class JVMMonitor extends ThreadClassTemplet {

	private static Logger log = Logger
			.getLogger(JVMMonitor.class);
	private DAqService service = DAqServiceImpl.getInstance();

	public JVMMonitor(int sleepTimes) {
		super(sleepTimes);
	}

	@Override
	protected void runFun() {
		scheduleClose();
		QueueViewPo view = service.monitorQueue();
		boolean isExit = false;
		if (view.getReceiveCache() > SysConstant.RECEIVE_CACHE_SIZE) {
			log.error("[前置机]接受的[连接缓存]队列 "
					+ view.getReceiveCache());
			isExit = true;
		}

		if (view.getToScheduler() > SysConstant.TO_SCHEDULER_SIZE) {
			log.error("[连接缓存]发送到[任务调度]的队列 "
					+ view.getToScheduler());
			isExit = true;
		}
		if (view.getToTerminal() > SysConstant.TO_TERMINAL_SIZE) {
			log.error("[前置机]发送给[硬件]的队列 "
					+ view.getReceiveCache());
			isExit = true;
		}
		if (view.getToFrontWait() > SysConstant.TO_FRONTWAIT_SIZE) {
			log.error("[任务调度]发送给[前置机]待处理队列 "
					+ view.getToFrontWait());
			isExit = true;
		}
		if (view.getToFront() > SysConstant.TO_FRONT_SIZE) {
			log.error("[任务调度]发送给[前置机]的已处理队列 "
					+ view.getToFront());
			isExit = true;
		}
		if (view.getToServer() > SysConstant.TO_SERVER_SIZE) {
			log.error("[任务调度]发送给[服务端]队列" + view.getToServer());
			isExit = true;
		}
		if (view.getFromServer() > SysConstant.FROM_SERVER_SIZE) {
			log.error("[任务调度]接收[服务端]队列 " + view.getFromServer());
			isExit = true;
		}
		if (isExit) {
			System.gc();
			log.error("队列阻塞异常退出");
			System.exit(0);
		}

	}

	/**
	 * 定时关闭程序
	 * zengfan
	 * 20151003
	 */
	private void scheduleClose(){
		Calendar calendar = Calendar.getInstance();
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
/*		if((min%10==0)&(sec==59)){
			log.fatal("min="+min+"sec="+sec+" 系统自动退出");
			System.exit(1);
			
		}*/
		
	}
	
}
