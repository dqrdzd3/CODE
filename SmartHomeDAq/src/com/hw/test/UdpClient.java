package com.hw.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN07FN01;
import com.hw.smarthome.daq.po.DataItemAFN07FN02new;
import com.hw.smarthome.daq.po.DataItemAFN07FN03;
import com.hw.smarthome.daq.po.base.DataItem;
import com.hw.smarthome.daq.po.base.DataItemAFN07;
import com.hw.smarthome.daq.server.jms.JMSSender;
import com.hw.util.ByteUtils;

public class UdpClient {
	private static final int UDP_PORT = 59999;
	private DatagramSocket udp = null;
	private DatagramPacket recdp = null;

	public UdpClient(int sleepTimes, byte[] bytes) {
		try {
			udp = new DatagramSocket();
			// 设置广播开关
			udp.setBroadcast(false);
			recdp = new DatagramPacket(bytes, bytes.length,
					// InetAddress.getByName("219.150.156.51"), UDP_PORT);
					InetAddress.getByName("127.0.0.1"), UDP_PORT);
			udp.send(recdp);
			udp.close();

		} catch (SocketException e) {
			System.out.println("创建端口监听客户端失败" + e);
			e.printStackTrace();
		} catch (UnknownHostException ex) {
			System.out.println("UnknownHostException" + ex);
			ex.printStackTrace();
		} catch (IOException e) {
			System.out.println("发送失败" + e);
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		/* afn 0x08 主动上报 空气电台 */
		//String hexString08 = "68004068B6735B0E30F4B00D7FCDBC47A22057372C7BC56C341F3229DD103F08C54427C9C69FE8AE7840526BD41068ACBA91DF7AA7D8FA44214F4D8664CCC74345A41C92A216";
		/* afn 0x08 主动上报 燃气 */
		 String hexString08 =
		 "680020684E6F530ABF6AFC5B388EB2F7C407B33DC408779BCF8B18921756CD6836CA13F55016";
		/* afn 0x07 被控主动上报 空气电台 */
		// String hexString07 =
		// "68004068589442D716014D3E7C9445C5B0C14A37312985349E94F3B60E566C2829B88A15CB18A634C2DB361F2C78C6CC41E07F06BCCDC2F126BA4D492D736DEADB14ED77A016";
		byte[] bytes08 = ByteUtils.toBytes(hexString08);
		// byte[] bytes07 = ByteUtils.toBytes(hexString07);
		while (true) {
			new UdpClient(1, bytes08);
			// new UdpClient(1, bytes07);//500000011DAC
			Thread.sleep(20 * 1000);
		}
	}

/*	public static void main(String[] args) throws InterruptedException{
		while(true){
			DAqPo daq = new DAqPo();
			daq.setAfn((byte)0x07);
			
			DataItemAFN07FN02new afn07fn02fake1 = new DataItemAFN07FN02new();
			//voc
			afn07fn02fake1.setControlNum(1);
			afn07fn02fake1.setControlObject(1);
			afn07fn02fake1.setControlType("D9");//voc
			afn07fn02fake1.setControlAmountLowBound(0);
			afn07fn02fake1.setControlAmountBound(100);
			afn07fn02fake1.setControlValue("30");
			afn07fn02fake1.setUnit("13");//UGM3 毫克/m3
			//co2
			DataItemAFN07FN02new afn07fn02fake2 = new DataItemAFN07FN02new();
			afn07fn02fake2.setControlNum(2);
			afn07fn02fake2.setControlObject(1);
			afn07fn02fake2.setControlType("30");//co2
			afn07fn02fake2.setControlAmountLowBound(0);
			afn07fn02fake2.setControlAmountBound(2000);
			afn07fn02fake2.setControlValue("800");
			afn07fn02fake2.setUnit("13");//UGM3 毫克/m3
			//pm2.5
			DataItemAFN07FN02new afn07fn02fake3 = new DataItemAFN07FN02new();
			afn07fn02fake3.setControlNum(2);
			afn07fn02fake3.setControlObject(2);
			afn07fn02fake3.setControlType("D8");//pm2.5
			afn07fn02fake3.setControlAmountLowBound(0);
			afn07fn02fake3.setControlAmountBound(500);
			afn07fn02fake3.setControlValue("78");
			afn07fn02fake3.setUnit("13");//UGM3 毫克/m3
			//noise
			DataItemAFN07FN02new afn07fn02fake4 = new DataItemAFN07FN02new();
			afn07fn02fake4.setControlNum(3);
			afn07fn02fake4.setControlObject(1);
			afn07fn02fake4.setControlType("D1");//noise
			afn07fn02fake4.setControlAmountLowBound(20);
			afn07fn02fake4.setControlAmountBound(200);
			afn07fn02fake4.setControlValue("50");
			afn07fn02fake4.setUnit("0C");//db
			//temperature
			DataItemAFN07FN02new afn07fn02fake5 = new DataItemAFN07FN02new();
			afn07fn02fake5.setControlNum(3);
			afn07fn02fake5.setControlObject(1);
			afn07fn02fake5.setControlType("C9");// temperature
			afn07fn02fake5.setControlAmountLowBound(0);
			afn07fn02fake5.setControlAmountBound(50);
			afn07fn02fake5.setControlValue("25");
			afn07fn02fake5.setUnit("05");// c
			//湿度
			DataItemAFN07FN02new afn07fn02fake6 = new DataItemAFN07FN02new();
			afn07fn02fake6.setControlNum(3);
			afn07fn02fake6.setControlObject(1);
			afn07fn02fake6.setControlType("CA");// 湿度
			afn07fn02fake6.setControlAmountLowBound(0);
			afn07fn02fake6.setControlAmountBound(100);
			afn07fn02fake6.setControlValue("30");
			//光照
			DataItemAFN07FN03 afn07fn03fake1 = new DataItemAFN07FN03();
			afn07fn03fake1.setSensorNum(1);
			afn07fn03fake1.setSensorType("D0");
			afn07fn03fake1.setControlValue("52");
			afn07fn03fake1.setUnit("0A");
			//co
			DataItemAFN07FN03 afn07fn03fake2 = new DataItemAFN07FN03();
			afn07fn03fake2.setSensorNum(1);
			afn07fn03fake2.setSensorType("04");//co
			afn07fn03fake2.setControlValue("50");
			afn07fn03fake2.setUnit("13");//UGM3 毫克/m3
			//ch4
			DataItemAFN07FN03 afn07fn03fake3 = new DataItemAFN07FN03();
			afn07fn03fake3.setSensorNum(1);
			afn07fn03fake3.setSensorType("01");//ch4
			afn07fn03fake3.setControlValue("45");
			afn07fn03fake3.setUnit("13");//UGM3 毫克/m3
			//甲醛
			DataItemAFN07FN03 afn07fn03fake4 = new DataItemAFN07FN03();
			afn07fn03fake4.setSensorNum(1);
			afn07fn03fake4.setSensorType("17");// HCHO
			afn07fn03fake4.setControlValue("45");
			afn07fn03fake4.setUnit("13");// UGM3 毫克/m3
			
			//
			DataItemAFN07FN02new dataItem0702_1 = new DataItemAFN07FN02new();
			dataItem0702_1.setControlNum(2);
			dataItem0702_1.setControlObject(1);
			dataItem0702_1.setControlType("D8");// pm25
			dataItem0702_1.setControlAmountBound(1111);
			dataItem0702_1.setControlAmountLowBound(0);
			dataItem0702_1.setControlValue("12");
			DataItemAFN07FN02new dataItem0702_2 = new DataItemAFN07FN02new();
			dataItem0702_2.setControlNum(2);
			dataItem0702_2.setControlObject(2);
			dataItem0702_2.setControlType("D9");// voc
			dataItem0702_2.setControlAmountBound(1111);
			dataItem0702_2.setControlAmountLowBound(0);
			dataItem0702_2.setControlValue("16");
			DataItemAFN07FN03 dataItemAFN07FN03_1 = new DataItemAFN07FN03();
			dataItemAFN07FN03_1.setSensorNum(1);
			dataItemAFN07FN03_1.setSensorType("D8");// co2
			dataItemAFN07FN03_1.setControlValue("123");
			DataItemAFN07FN03 dataItemAFN07FN03_2 = new DataItemAFN07FN03();
			dataItemAFN07FN03_2.setSensorNum(2);
			dataItemAFN07FN03_2.setSensorType("30");// co2
			dataItemAFN07FN03_2.setControlValue("123");
			DataItemAFN07FN03 dataItemAFN07FN03_3 = new DataItemAFN07FN03();
			dataItemAFN07FN03_1.setSensorNum(1);
			dataItemAFN07FN03_1.setSensorType("D9");// co2
			dataItemAFN07FN03_1.setControlValue("123");
			DataItemAFN07FN03 dataItemAFN07FN03_4 = new DataItemAFN07FN03();
			dataItemAFN07FN03_2.setSensorNum(2);
			dataItemAFN07FN03_2.setSensorType("30");// co2
			dataItemAFN07FN03_2.setControlValue("123");
			//
			List<DataItem> dataItemList = new ArrayList<DataItem>();
			dataItemList.add((DataItemAFN07)dataItem0702_1);
			dataItemList.add(dataItem0702_2);
			dataItemList.add(dataItemAFN07FN03_1);
			dataItemList.add(dataItemAFN07FN03_2);
			dataItemList.add(dataItemAFN07FN03_3);
			dataItemList.add(dataItemAFN07FN03_4);
			dataItemList.add(afn07fn02fake1);
			dataItemList.add(afn07fn02fake2);
			dataItemList.add(afn07fn02fake3);
			dataItemList.add(afn07fn02fake4);
			dataItemList.add(afn07fn02fake5);
			dataItemList.add(afn07fn02fake6);
			dataItemList.add(afn07fn03fake1);
			dataItemList.add(afn07fn03fake2);
			dataItemList.add(afn07fn03fake3);
			dataItemList.add(afn07fn03fake4);		
			daq.setDataItems(dataItemList);
			daq.setSensorId("C893464D17A4");
			JMSSender jMSSender = new JMSSender();
			jMSSender.send(daq);
			Thread.sleep(60 * 1000);
		}

	}*/
}
