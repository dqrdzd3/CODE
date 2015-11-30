package com.hw.smarthome.daq.server.ws;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.log4j.Logger;

import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.server.Scheduler2Server;
import com.hw.smarthome.daq.util.ClearUtils;
import com.hw.util.Ln;

public class WSSender {
	private static Logger log = Logger.getLogger(WSSender.class);
	private static RPCServiceClient[] clients = null;
	private static QName qName = null;
	private Object[] inParams = new Object[1];
	private Class[] clazzs = { String.class };

	// private DAqPo frame;

	public WSSender(DAqPo frame, int index) {
		ws2Server(frame, index);
		// this.frame = frame;
	}

	//
	// @Override
	// public void run() {
	// super.run();
	// ws2Server(frame);
	// }
	public static void initClient(int sum) {
		try {
			clients = new RPCServiceClient[sum];
			for (int i = 0; i < sum; i++) {
				clients[i] = new RPCServiceClient();
				// 设置webservice服务地址
				initOption(clients[i]);
			}
		} catch (Exception e) {
			Ln.e(Scheduler2Server.class, "创建WS客户端失败", e);
		}

	}

	private static void initOption(RPCServiceClient client) {
		Options options = client.getOptions();
		EndpointReference targetEPR = new EndpointReference(
				SysConstant.SERVER_WS_ADDR);
		options.setTo(targetEPR);
		options.setTimeOutInMilliSeconds(5 * 60 * 1000); // 5分钟
	}

	/**
	 * 返回服务端
	 */
	private void ws2Server(final DAqPo frame, int index) {
		RPCServiceClient client = null;
		try {
			if (qName == null) {
				qName = new QName(
						SysConstant.SERVER_WS_NAMESPACE,
						SysConstant.SERVER_WS_METHOD);
			}

			if (clients[index] == null) {
				clients[index] = new RPCServiceClient();
				initOption(clients[index]);
			}
			client = clients[index];

			String res = frame.getWsJson();
			if ("getUDAData"
					.equals(SysConstant.SERVER_WS_METHOD)) {
				res = "[" + res + "]";
			}
			// 设置入参
			inParams[0] = res;
			Object[] resObjs = client.invokeBlocking(qName,
					inParams, clazzs);
			// 开始异步调用
			// client.invokeNonBlocking(qName, inParams,
			// new AxisCallback() {
			//
			// public void onComplete() {
			// if (Ln.IS_DEBUG) {
			// log.debug(HintConstant.WEBSERVICE_INVOKING_COMPLET
			// + ":" + frame.toString());
			// try {
			// client.cleanupTransport();
			// } catch (Exception e) {
			// Ln.e(WSSender.class,
			// HintConstant.WEBSERVICE_INVOKING_ERROR,
			// e);
			// }
			// }
			//
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
			if (resObjs != null && resObjs.length > 0) {
				if ("getUDAData"
						.equals(SysConstant.SERVER_WS_METHOD)) {
					log.debug(HintConstant.SEND_WS_RECEIVE + ":"
							+ resObjs[0].toString() + " "
							+ frame);
				}
			}
			resObjs = null;

			// 设置入参
			// Object[] inParams = new Object[] { res };
			//
			// // 设置入参
			// Object[] results = client.invoke(
			// SysConstant.SERVER_WS_METHOD, inParams);
			// if (log.isDebugEnabled()) {
			// log.debug(HintConstant.WEBSERVICE_INVOKING_COMPLET
			// + "：" + frame.getWsJson());
			// }
		} catch (Exception e) {
			Ln.e(Scheduler2Server.class,
					HintConstant.SEND_WS_FAIL + ":"
							+ frame.toString(), e);
		} finally {
			try {
				if(client!=null){
					client.cleanupTransport();
				}
			} catch (Exception e) {
				Ln.e(WSSender.class,
						HintConstant.WEBSERVICE_INVOKING_ERROR,
						e);
			}
			ClearUtils.clearDataItems(frame);
			ClearUtils.clearReceive(frame);
		}
	}
}
