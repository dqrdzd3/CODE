package com.hw.smarthome.daq.server;

import org.apache.log4j.Logger;

import com.hw.smarthome.daq.constant.FrameConstant;
import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN08;
import com.hw.smarthome.daq.scheduler.queue.SchedulerQueue;
import com.hw.smarthome.daq.server.jms.JMSSender;
import com.hw.smarthome.daq.server.ws.WSSender;
import com.hw.smarthome.daq.util.ClearUtils;
import com.hw.util.Ln;
import com.hw.util.ThreadClassTemplet;

/**
 * 数据服务端
 * 
 * @author 曾凡
 * @time 2015年1月12日 下午5:13:20
 */
public class Scheduler2Server extends ThreadClassTemplet {
	private static Logger log = Logger
			.getLogger(Scheduler2Server.class);
	private int index;

	public Scheduler2Server(int sleepTimes, int index) {
		super(sleepTimes);
		this.index = index;
	}

	@Override
	protected void runFun() {
		DAqPo frame = null;
		if (!SchedulerQueue.getToServer().isEmpty()) {
			frame = SchedulerQueue.pollToServer();
			if (frame != null) {
				if (chkNeed2SendServer(frame)) {
					if (SysConstant.SERVER_JMS) {
						if (Ln.IS_DEBUG) {
							log.debug(frame.getSensorId()
									+ " "
									+ HintConstant.SEND_JMS_SUCCESS
									+ " " + frame.getWsJson());
						}
						/* FIXME 测试报警 */
//						((DataItemAFN08)frame.getDataItems().get(0)).setStatus(2+"");
						jms2Server(frame);
					}
					if (SysConstant.SERVER_WS) {
						if (Ln.IS_DEBUG) {
							log.debug(frame.getSensorId()
									+ " "
									+ HintConstant.SEND_WS_SUCCESS
									+ " " + frame.toString());
						}
						ws2Server(frame);
						// WebServiceOld.send(frame.getWsJson());
					}
					ClearUtils.clearFrame(frame);
				}
			}
		}
	}

	/**
	 * 判断是否需要发送给服务端
	 * 
	 * @author 曾凡
	 * @param frame
	 * @return
	 * @time 2015年1月13日 下午4:06:11
	 */
	private boolean chkNeed2SendServer(DAqPo frame) {
		boolean res = false;
		switch (frame.getAfn()) {
		case FrameConstant.AFN_ACTIVE_UPLOAD:
			res = true;
			break;
		case FrameConstant.AFN_CTRL_DEVICE_UPLOAD:
			res = true;
			break;
		case 0x0C:// 老主动上报
			res = true;
			break;
		default:
			res = false;
			break;
		}
		return res;
	}

	public void jms2Server(DAqPo frame) {
		JMSSender.getInstance().send(frame);
	}

	/**
	 * 返回服务端
	 */
	public void ws2Server(final DAqPo frame) {
		try {
			//
			// String wsdlUrl = SysConstant.SERVER_WS_ADDR;
			// String namespace = SysConstant.SERVER_WS_NAMESPACE;
			// String methodName = SysConstant.SERVER_WS_METHOD;
			//
			// RPCServiceClient client = new RPCServiceClient();
			//
			// // 设置webservice服务地址
			// Options options = client.getOptions();
			// EndpointReference targetEPR = new EndpointReference(
			// wsdlUrl);
			// options.setTo(targetEPR);
			//
			// // 设置要调用的方法名、命名空间
			// QName qName = new QName(namespace, methodName);
			// FIXME 测试
			// {
			//
			// List<DataItem> dataItems = frame.getDataItems();
			// if (dataItems.size() > 4) {
			// DataItemAFN08 item = (DataItemAFN08) dataItems
			// .get(0);
			// item.setChanlvalue(new Date().getDay() + ""
			// + new Date().getHours() + ""
			// + new Date().getMinutes());
			// DataItemAFN08 item2 = (DataItemAFN08) dataItems
			// .get(1);
			// item2.setChanlvalue(new Date().getDay() + ""
			// + new Date().getHours() + ""
			// + new Date().getMinutes());
			// DataItemAFN08 item3 = (DataItemAFN08) dataItems
			// .get(2);
			// item3.setChanlvalue(new Date().getDay() + ""
			// + new Date().getHours() + ""
			// + new Date().getMinutes());
			// DataItemAFN08 item4 = (DataItemAFN08) dataItems
			// .get(3);
			// item4.setChanlvalue(new Date().getDay() + ""
			// + new Date().getHours() + ""
			// + new Date().getMinutes());
			//
			// }
			// }
			//
			// String res = frame.getWsJson();
			// if ("getUDAData"
			// .equals(SysConstant.SERVER_WS_METHOD)) {
			// res = "[" + res + "]";
			// }
			// // 设置入参
			// Object[] inParams = new Object[] { res };
			// client.invokeBlocking(qName, inParams);
			// 开始异步调用
			// client.invokeNonBlocking(qName, inParams,
			// new AxisCallback() {
			//
			// public void onComplete() {
			// if (Ln.IS_DEBUG) {
			// log.debug(HintConstant.WEBSERVICE_INVOKING_COMPLET
			// + ":" + frame.toString());
			// }
			// }
			//
			// public void onError(Exception ex) {
			// Ln.e(Scheduler2Server.class,
			// HintConstant.WEBSERVICE_INVOKING_ERROR
			// + ":"
			// + frame.toString(),
			// ex);
			// }
			//
			// public void onFault(MessageContext arg0) {
			// log.warn(HintConstant.WEBSERVICE_INVOKING_FAULT
			// + ":"
			// + arg0
			// + ","
			// + frame.toString());
			// }
			//
			// @Override
			// public void onMessage(
			// MessageContext msgContext) {
			// log.debug(msgContext);
			// }
			// });
			new WSSender(frame, index);
		} catch (Exception e) {
			Ln.e(Scheduler2Server.class,
					HintConstant.SEND_WS_FAIL + ":"
							+ frame.toString(), e);
		} finally {
			ClearUtils.clearFrame(frame);
		}
	}

	// public static void main(String[] args) {
	// // DAqPo frame = new DAqPo();
	// // ws2Server(frame);
	//
	// OMElement ome = null;
	// try {
	//
	// String wsdlUrl =
	// "http://192.168.111.186:8080/SmartBeanService/SmartBean?wsdl";//
	// ConfConstants.HWCLOUD_EQUIPMENT_URL;
	// String namespace = "http://ws.mom.smarthome.hw.com/";//
	// ConfConstants.HWCLOUD_EQUIPMENT_NAMESPACE;
	//
	// RPCServiceClient client = new RPCServiceClient();
	//
	// // 设置webservice服务地址
	// Options options = client.getOptions();
	// EndpointReference targetEPR = new EndpointReference(
	// wsdlUrl);
	// options.setTo(targetEPR);
	//
	// // 设置要调用的方法名、命名空间
	// QName qName = new QName(namespace,
	// "retrieveHisStautsByGPRSId");
	//
	// // 设置入参
	// Object[] inParams = new Object[] { "500000035F8C",
	// "2", Integer.valueOf(15), Integer.valueOf(2) };
	// ome = client.invokeBlocking(qName, inParams);
	// // 开始异步调用
	// System.out.println(ome.toString());
	//
	// } catch (Exception e) {
	//
	// }
	//
	// }
}
