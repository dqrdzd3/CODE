package com.hw.smarthome.daq.constant;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.hw.smarthome.daq.po.FileInfo;
import com.hw.util.FileUtils;
import com.hw.util.Ln;

public class SysConstant {
	/** 是否加密 */
	public static boolean IS_DECODE_ENCODE = true;

	public static int FRONT_UDP_PORT = 59999;

	/** 户外器数据链路 */
	public static int FRONT_TCP_PORT = 60002;

	public static int SCHEDULER_SOCKET_RECEIVE_PORT = 49999;
	public static int SCHEDULER_SOCKET_SEND_PORT = 39999;
	public static boolean SERVER_WS = true;

	public static String SERVER_WS_ADDR = "http://weiguo.hanwei.cn:8081/cloud/services/udaWebService?WSDL";
	public static String SERVER_WS_NAMESPACE = "http://service.webservice.bolster.hwsafe.hw.com";
	public static String SERVER_WS_METHOD = "getUDAData";

	public static boolean SERVER_JMS = false;
	public static String SERVER_JMS_MQ_ADDR = "mq://127.0.0.1,mq://127.0.0.1:7676";
	public static String SERVER_JMS_USER = "admin";
	public static String SERVER_JMS_PASSWD = "admin";
	public static String SERVER_JMS_PRODUCER_QUEUE = "fromFront";
	public static String SERVER_JMS_CUSTOMER_QUEUE = "toFront";

	public static String UPDATE_FILE_ADDR = "F:/汉威/智能家居/UDA/远程升级/update";
	public static String UPDATE_FILE_NAME = "update.bin";

	/** 前置机监听线程 */
	public static int FRONTRECEIVER_THREAD_SIZE;
	/** 前置机监听处理线程 */
	public static int FRONTRECEIVEHANDLER_THREAD_SIZE;
	/** 前置机发送线程 */
	public static int FRONTSENDER_THREAD_SIZE;
	/** 任务调度接收线程 */
	public static int SCHEDULERRECEIVEHANDLER_THREAD_SIZE;
	/** 任务调度发送线程 */
	public static int SCHEDULERSENDHANDLER_THREAD_SIZE;
	/** 服务端接收线程 */
	public static int SCHEDULER2SERVER_THREAD_SIZE;
	/** 服务端发送线程 */
	public static int SERVER2SCHEDULER_THREAD_SIZE;
	/** 任务调度Socket监听线程 */
	public static int SOCKETRECEIVER_THREAD_SIZE;
	/** 任务调度Socket监听线程 */
	public static int SOCKETSENDER_THREAD_SIZE;

	/** [前置机]接受的[连接缓存]队列 异常退出阀值 */
	public static int RECEIVE_CACHE_SIZE = 500;
	/** [连接缓存]发送到[任务调度]的队列 异常退出阀值 */
	public static int TO_SCHEDULER_SIZE = 500;
	/** [前置机]发送给[硬件]的队列 异常退出阀值 */
	public static int TO_TERMINAL_SIZE = 500;
	/** [任务调度]发送给[前置机]待处理队列 异常退出阀值 */
	public static int TO_FRONTWAIT_SIZE = 500;
	/** [任务调度]发送给[前置机]的已处理队列 异常退出阀值 */
	public static int TO_FRONT_SIZE = 500;
	/** [任务调度]发送给[服务端]队列 异常退出阀值 */
	public static int TO_SERVER_SIZE = 500;
	/** [任务调度]接收[服务端]队列 异常退出阀值 */
	public static int FROM_SERVER_SIZE = 500;

	public static final int RETURN_2_SERVER = 1;
	public static final int RETURN_2_SOCKET = 2;

	/** 设备未联网 */
	public static final int LINK_STATUS_DEVICE_NOT_LINK = 0;
	/** 需要远程升级的bin文件 */
	public static Map<String, FileInfo> binaryUpdateFiles = new ConcurrentHashMap<String, FileInfo>();

	public static Properties p;
	static {
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(
					"conf/config.properties"));
			p = new Properties();
			p.load(in);
			FRONT_UDP_PORT = Integer.valueOf(p.getProperty(
					"front.udp.port").trim());
			FRONT_TCP_PORT = Integer.valueOf(p.getProperty(
					"front.tcp.port").trim());
			SCHEDULER_SOCKET_RECEIVE_PORT = Integer
					.valueOf(p.getProperty(
							"scheduler.socket.receive.port")
							.trim());
			;
			SCHEDULER_SOCKET_SEND_PORT = Integer.valueOf(p
					.getProperty("scheduler.socket.send.port")
					.trim());

			SERVER_WS = Boolean.valueOf(p.getProperty(
					"server.ws").trim());
			IS_DECODE_ENCODE = Boolean.valueOf(p.getProperty(
					"is.decode.encode").trim());
			SERVER_WS_ADDR = p.getProperty("server.ws.addr")
					.trim();
			SERVER_WS_NAMESPACE = p.getProperty(
					"server.ws.namespace").trim();
			SERVER_WS_METHOD = p.getProperty("server.ws.method")
					.trim();
			SERVER_JMS = Boolean.valueOf(p.getProperty(
					"server.jms").trim());
			SERVER_JMS_MQ_ADDR = p.getProperty(
					"server.jms.mq.addr").trim();
			SERVER_JMS_USER = p.getProperty("server.jms.user")
					.trim();
			SERVER_JMS_PASSWD = p.getProperty(
					"server.jms.passwd").trim();
			SERVER_JMS_PRODUCER_QUEUE = p.getProperty(
					"server.jms.producer.queue").trim();
			SERVER_JMS_CUSTOMER_QUEUE = p.getProperty(
					"server.jms.customer.queue").trim();

			/* 前置机监听线程 */
			FRONTRECEIVER_THREAD_SIZE = Integer.valueOf(p
					.getProperty("FrontReceiver.thread.size")
					.trim());
			/* 前置机监听处理线程 */
			FRONTRECEIVEHANDLER_THREAD_SIZE = Integer.valueOf(p
					.getProperty(
							"FrontReceiveHandler.thread.size")
					.trim());
			/* 前置机发送线程 */
			FRONTSENDER_THREAD_SIZE = Integer.valueOf(p
					.getProperty("FrontSender.thread.size")
					.trim());
			/* 任务调度接收线程 */
			SCHEDULERRECEIVEHANDLER_THREAD_SIZE = Integer
					.valueOf(p
							.getProperty(
									"SchedulerReceiveHandler.thread.size")
							.trim());
			/* 任务调度发送线程 */
			SCHEDULERSENDHANDLER_THREAD_SIZE = Integer.valueOf(p
					.getProperty(
							"SchedulerSendHandler.thread.size")
					.trim());
			/* 服务端接收线程 */
			SCHEDULER2SERVER_THREAD_SIZE = Integer.valueOf(p
					.getProperty("Scheduler2Server.thread.size")
					.trim());
			/* 服务端发送线程 */
			SERVER2SCHEDULER_THREAD_SIZE = Integer.valueOf(p
					.getProperty("Server2Scheduler.thread.size")
					.trim());
			/* 任务调度Socket监听线程 */
			SOCKETRECEIVER_THREAD_SIZE = Integer.valueOf(p
					.getProperty("SocketReceiver.thread.size")
					.trim());
			/* 任务调度Socket发送线程 */
			SOCKETSENDER_THREAD_SIZE = Integer.valueOf(p
					.getProperty("SocketSender.thread.size")
					.trim());

			/* [前置机]接受的[连接缓存]队列 异常退出阀值 */
			RECEIVE_CACHE_SIZE = Integer.valueOf(p.getProperty(
					"receive.cache.size").trim());
			/* [连接缓存]发送到[任务调度]的队列 异常退出阀值 */
			TO_SCHEDULER_SIZE = Integer.valueOf(p.getProperty(
					"to.scheduler.size").trim());
			/* [前置机]发送给[硬件]的队列 异常退出阀值 */
			TO_TERMINAL_SIZE = Integer.valueOf(p.getProperty(
					"to.terminal.size").trim());
			/* [任务调度]发送给[前置机]待处理队列 异常退出阀值 */
			TO_FRONTWAIT_SIZE = Integer.valueOf(p.getProperty(
					"to.frontwait.size").trim());
			/* [任务调度]发送给[前置机]的已处理队列 异常退出阀值 */
			TO_FRONT_SIZE = Integer.valueOf(p.getProperty(
					"to.front.size").trim());
			/* [任务调度]发送给[服务端]队列 异常退出阀值 */
			TO_SERVER_SIZE = Integer.valueOf(p.getProperty(
					"to.server.size").trim());
			/* [任务调度]接收[服务端]队列 异常退出阀值 */
			FROM_SERVER_SIZE = Integer.valueOf(p.getProperty(
					"from.serve.size").trim());

		} catch (Exception e) {
			Ln.e(SysConstant.class, HintConstant.READ_CONF_FAIL,
					e);
		}
	}
	private static Charset charset = Charset.forName("UTF-8");

	public static void initUpdateFiles() throws IOException {
		binaryUpdateFiles.clear();
		UPDATE_FILE_ADDR = new String(p.getProperty(
				"update.file.addr").getBytes("ISO-8859-1"),
				charset).trim();
		UPDATE_FILE_NAME = new String(p.getProperty(
				"update.file").getBytes("ISO-8859-1"), charset)
				.trim();
		File files = new File(UPDATE_FILE_ADDR);
		FileInfo info = null;
		String[] temp = null;
		byte[] fileByteArray = null;

		File updateFile = null;
		for (File file : files.listFiles()) {
			long chkSum = 0;
			if (!file.isDirectory()) {
				continue;
			}
			info = new FileInfo();
			temp = file.getName().split(",");
			/* 设备类型 */
			info.setDeviceType(temp[0]);
			/* 硬件版本 */
			info.setHwVersion(temp[1]);
			/* 软件版本 */
			info.setSwVersion(temp[2]);
			/* 文件名 */
			info.setFileName(UPDATE_FILE_NAME);
			updateFile = new File(file.getAbsolutePath() + "/"
					+ UPDATE_FILE_NAME);

			fileByteArray = FileUtils.toByteArray(updateFile
					.getAbsolutePath());
			/* FIXME 测试二进制 */
			// String test = ByteUtils.toHex(fileByteArray);
			info.setContent(fileByteArray);
			/* 总文件大小 */
			info.setFileSize(fileByteArray.length);
			/* 校验 */
			for (byte b : fileByteArray) {
				chkSum += (0xFF & b);
			}
			info.setDataCheck(Integer.valueOf((0xFFFF & chkSum)
					+ ""));
			binaryUpdateFiles.put(file.getName(), info);
		}
	}

	static {
		try {
			initUpdateFiles();
		} catch (IOException e) {
			Ln.e(SysConstant.class, HintConstant.READ_CONF_FAIL,
					e);
		}
	}

	public static FileInfo getUpdateFileInfo(String deviceType,
			String hwVersion, String swVersion) {
		StringBuilder sb = new StringBuilder();
		sb.append(deviceType).append(",");
		sb.append(hwVersion).append(",");
		sb.append(swVersion);

		for (Entry<String, FileInfo> entry : binaryUpdateFiles
				.entrySet()) {
			if (entry.getKey().contains(sb.toString())) {
				return entry.getValue();
			}
		}
		return null;
	}
}
