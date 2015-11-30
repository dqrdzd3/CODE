package com.hw.smarthome.daq.front.receive;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.hw.smarthome.daq.constant.AtmosphericConstants;
import com.hw.smarthome.daq.constant.FrameConstant;
import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.front.queue.FrontQueue;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN08;
import com.hw.smarthome.daq.po.base.DataItem;
import com.hw.smarthome.daq.scheduler.queue.SchedulerQueue;
import com.hw.util.ByteUtils;
import com.hw.util.Ln;
import com.hw.util.ThreadClassTemplet;

/**
 * 分布室外大气监测监听器
 * 
 * @author 曾凡
 * @time 2015年9月1日 下午8:37:56
 */
public class AtmosphericDetectorReciver extends
		ThreadClassTemplet {
	private static Logger log = Logger
			.getLogger(AtmosphericDetectorReciver.class);
	private ServerSocket server = null;
	private byte[] bytes = new byte[10 * 1024];

	public AtmosphericDetectorReciver(int sleepTimes) {
		super(sleepTimes);
		try {
			server = new ServerSocket(SysConstant.FRONT_TCP_PORT);
		} catch (IOException e) {
			Ln.e(this.getClass(),
					HintConstant.SOCKET_SERVER_ERROR, e);
		}
	}

	@Override
	protected void runFun() {
		try {
			if (server != null) {
				Socket client = server.accept();
				log.debug("接收到新的连接"
						+ client.getInetAddress().toString());
				new ReadFromTerminal(client).start();
			}
		} catch (Exception e) {
			Ln.e(this.getClass(),
					HintConstant.SOCKET_SERVER_ERROR, e);
		}
	}

	private class ReadFromTerminal extends Thread {

		private Socket mClient;

		public ReadFromTerminal(Socket socket) {
			mClient = socket;
		}

		@Override
		public void run() {
			super.run();
			_FOR: while (mClient != null && !mClient.isClosed()) {
				try {
					/* 睡眠2秒再发 */
					TimeUnit.SECONDS.sleep(2);
					DAqPo daq = new DAqPo();
					daq.setCon(false);
					int len = mClient.getInputStream().read(
							bytes);
					log.debug("接收到新的数据，长度为" + len);
					// 关闭了输入流
					if (len == -1) {
						break _FOR;
					}
					byte[] raw = new byte[len];
					System.arraycopy(bytes, 0, raw, 0, len);
					log.debug("解析的分布式大气监测测试为：" + new String(raw));
					String sensorId = handler(daq, new String(
							raw), mClient.getInetAddress()
							.getHostAddress());
					daq.setSensorId(sensorId);
					log.debug("解析的分布式大气监测测试[" + sensorId + "]为："
							+ daq.toString());
					/* 添加到Socket连接缓存里 */
					FrontQueue.getAtmospherCache().put(sensorId,
							mClient);

					if (daq.getAfn() == ((byte) (FrameConstant.AFN_ACTIVE_UPLOAD & 0xFF))) {
						
						/* 发送回执信息 */
						if (mClient != null) {
							/* 直接发给服务器，跳过了前置机 */
							SchedulerQueue.addToServer(daq);
							String back = "";
							try {
								back = toTerminal();
								/* 睡眠2秒再发 */
								TimeUnit.SECONDS.sleep(2);
								OutputStream out = mClient
										.getOutputStream();
								out.write(back.getBytes());
								out.flush();
								/* 睡眠2秒再发 */
								TimeUnit.SECONDS.sleep(1);
								log.debug("返回分布式大气监测数据为："
										+ new String(back));
								log.debug("返回分布式大气监测数据为："
										+ ByteUtils.toHex(back
												.getBytes()));
							} catch (Exception e) {
								log.error("发送回执信息[" + back
										+ "]异常", e);
							}
						}
						// new Send2Terminal(client).start();
						/* 发送回执信息 */
						// new Send2Terminal(client).start();
					} else {
						log.debug("关闭连接[" + new String(raw) + "]");
						/* 关闭连接不再回执 */
						OutputStream out = FrontQueue
								.getAtmospherCache()
								.get(sensorId).getOutputStream();
						out.close();
					}
				} catch (Exception e) {
					Ln.e(this.getClass(),
							HintConstant.SOCKET_SERVER_ERROR, e);
				}
			}
		}

	}

	// private class Send2Terminal extends Thread {
	// private Socket mClient;
	//
	// public Send2Terminal(Socket socket) {
	// mClient = socket;
	// }
	//
	// @Override
	// public void run() {
	// super.run();
	// if (mClient != null) {
	// String back = "";
	// try {
	// /* 睡眠2秒再发 */
	// TimeUnit.SECONDS.sleep(2);
	// back = toTerminal();
	// OutputStream out = mClient.getOutputStream();
	// byte[] tBytes = back.getBytes("ISO-8859-1");
	// out.write(tBytes);
	// out.flush();
	// log.warn("返回分布式大气监测数据为：" + new String(back));
	// log.warn("返回分布式大气监测数据为："
	// + ByteUtils.toHex(tBytes));
	// } catch (Exception e) {
	// log.error("发送回执信息[" + back + "]异常", e);
	// }
	// }
	// }
	// }

	/**
	 * 回复主动上报的数据
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2015年9月6日 下午2:58:53
	 */
	private static String toTerminal() {
		StringBuilder sb = new StringBuilder("D&T,");
		SimpleDateFormat sf = new SimpleDateFormat(
				"yy/MM/dd,HH:mm:ss+SSS");
		Calendar now = Calendar.getInstance();
		sb.append(sf.format(now.getTime()));
		sb.append(",96,15,");
		String chk = generateChk(sb.toString());
		sb.append(";").append(chk).append("\r\n");
		return sb.toString();
	}

	/**
	 * 校验和源代码
	 * 
	 * @author 曾凡
	 * @param str
	 * @return
	 * @time 2015年9月6日 下午3:15:55
	 */
	private static String generateChk(String str) {
		int sum = 0;
		for (byte b : str.getBytes()) {
			sum += b;
		}
		return (0xFF & sum) + "";
	}

	/**
	 * 我tmd受够了，不放缓存了，就放到这里了！！！！ 直接解析SN作为唯一标识
	 * 
	 * @author 曾凡
	 * @param raw
	 * @param ip
	 * @time 2015年9月1日 下午8:50:11
	 */
	private String handler(DAqPo daq, String raw, String ip) {
		String sensorId = "";
		if (raw.contains("*S")) {
			sensorId = parse08(daq, raw, ip);
		} else if (raw.contains("Y#")) {
			sensorId = parseCon(raw);
		}

		return sensorId.toUpperCase();
	}

	private String parseCon(String raw) {
		String[] strs = raw.split(",");
		if (strs != null && strs.length > 4) {
			return strs[2];
		} else {
			return "000000000000";
		}
	}

	private String parse08(DAqPo daq, String raw, String ip) {
		String sensorId = "";
		/* 编号 */
		int sensorCount = 0;
		/* 传感器类型 */
		String media = "";
		/* ID号 */
		int addresss = 0;
		/* 单位 */
		String unit = "";
		/* 状态 */
		String status = "0";
		/* 数据 */
		String chanlvalue = "";

		String[] strs = raw.split("\r\n");
		String[] temp = null;
		for (String str : strs) {
			temp = str.split(":");
			if (temp.length == 2) {
				if ("SN".equalsIgnoreCase(temp[0])) {
					sensorId = temp[1].replace(",", "");
					sensorId = AtmosphericConstants
							.getAtmospherSN(sensorId, ip);
				}
				if ("PN".equalsIgnoreCase(temp[0])) {
					try {
						addresss = Integer.valueOf(temp[1]
								.replace(",", ""));
					} catch (NumberFormatException e) {
						addresss = 0;
					}
				} else if ("VU".equalsIgnoreCase(temp[0])) {
					media = temp[1].replace(",", "");
					unit = temp[1].replace(",", "");
					media = media.substring(0,
							media.indexOf("("));
					unit = unit.substring(unit.indexOf("(") + 1,
							unit.indexOf(")"));
					if (AtmosphericConstants.VU_PM25
							.equalsIgnoreCase(media)) {
						media = AtmosphericConstants.MEDIA_TYPE_PM25;
						sensorCount = 1;
					} else if (AtmosphericConstants.VU_TEMPERATURE
							.equalsIgnoreCase(media)) {
						media = AtmosphericConstants.MEDIA_TYPE_TEMPERATURE;
						sensorCount = 2;
					} else if (AtmosphericConstants.VU_HUMIDITY
							.equalsIgnoreCase(media)) {
						media = AtmosphericConstants.MEDIA_TYPE_HUMIDITY;
						sensorCount = 3;
					} else if (AtmosphericConstants.VU_CO2
							.equalsIgnoreCase(media)) {
						media = AtmosphericConstants.MEDIA_TYPE_CO2;
						sensorCount = 4;
					}
				}
			}
			/* 如果不含S和E就是数值了 */
			else if (!(str.contains(("S")) || str
					.contains(("E")))) {
				chanlvalue = str.replace(",", "");
			}
		}

		daq.setAfn((byte) (FrameConstant.AFN_ACTIVE_UPLOAD & 0xFF));

		List<DataItem> dataItems = new LinkedList<DataItem>();
		daq.setDataItems(dataItems);
		DataItemAFN08 dataItem = new DataItemAFN08();
		dataItem.setSensorCount(sensorCount);
		dataItem.setMedia(media);
		dataItem.setUnit(unit);
		dataItem.setStatus(status);
		dataItem.setAddresss(addresss);
		dataItem.setChanlvalue(chanlvalue);
		dataItems.add(dataItem);

		return sensorId;
	}
}
