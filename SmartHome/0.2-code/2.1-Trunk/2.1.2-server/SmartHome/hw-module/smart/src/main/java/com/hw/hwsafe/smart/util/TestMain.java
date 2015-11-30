package com.hw.hwsafe.smart.util;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.hw.hwsafe.platform.constants.ConfConstants;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OMElement ome = null;
		try {

			String wsdlUrl = "http://192.168.111.217:8080/SmartBeanService/SmartBean?wsdl";//ConfConstants.HWCLOUD_EQUIPMENT_URL;
			String namespace = "http://ws.mom.smarthome.hw.com/";//ConfConstants.HWCLOUD_EQUIPMENT_NAMESPACE;
			

			RPCServiceClient client = new RPCServiceClient();

			// 设置webservice服务地址
			Options options = client.getOptions();
			EndpointReference targetEPR = new EndpointReference(
					wsdlUrl);
			options.setTo(targetEPR);

			// 设置要调用的方法名、命名空间
			QName qName = new QName(namespace, "retrieveHisStautsByGPRSId");

		
			// 设置入参
			Object[] inParams = new Object[] { "50000105A8DF","2", Integer.valueOf(15),Integer.valueOf(2)};
			ome =  client.invokeBlocking(qName, inParams);
			// 开始异步调用
			System.out.println(ome.toString());
			
		} catch (Exception e) {
			
		}
//		try {
//
//			String wsdlUrl = "http://192.168.111.186:8080/SmartBeanService/SmartBean?wsdl";// SysConstant.SERVER_WS_ADDR;
//			String namespace = "http://ws.mom.smarthome.hw.com/";// SysConstant.SERVER_WS_NAMESPACE;
//			String methodName = "retrieveHisStautsByGPRSId";//"retrieveCurStautsByGPRSId";// "retrieveHisStautsByGPRSId";// SysConstant.SERVER_WS_METHOD;
//
//			RPCServiceClient client = new RPCServiceClient();
//
//			// 设置webservice服务地址
//			Options options = client.getOptions();
//			EndpointReference targetEPR = new EndpointReference(wsdlUrl);
//			options.setTo(targetEPR);
//
//			// 设置要调用的方法名、命名空间
//			QName qName = new QName(namespace, methodName);
//
//			
//			// 设置入参
//			Object[] inParams = new Object[] { "500000035F8C", "2", Integer.valueOf(15),Integer.valueOf(2)}; //{"50000105A8DF","2","空气质量"};// { "50000105A8DF", "C9", Integer.valueOf(24),Integer.valueOf(1)};
//			OMElement ome = client.invokeBlocking(qName, inParams);
//			System.out.println(ome.toString());
//			
//			// 开始异步调用
//			// client.invokeNonBlocking(qName, inParams,
//			// new AxisCallback() {
//			//
//			// public void onComplete() {
//			// if (Ln.IS_DEBUG) {
//			// log.debug(HintConstant.WEBSERVICE_INVOKING_COMPLET
//			// + ":" + frame.toString());
//			// }
//			// }
//			//
//			// public void onError(Exception ex) {
//			// Ln.e(Scheduler2Server.class,
//			// HintConstant.WEBSERVICE_INVOKING_ERROR
//			// + ":"
//			// + frame.toString(),
//			// ex);
//			// }
//			//
//			// public void onFault(MessageContext arg0) {
//			// log.warn(HintConstant.WEBSERVICE_INVOKING_FAULT
//			// + ":"
//			// + arg0
//			// + ","
//			// + frame.toString());
//			// }
//			//
//			// @Override
//			// public void onMessage(
//			// MessageContext msgContext) {
//			// log.debug(msgContext);
//			// }
//			// });
//			
//		} catch (Exception e) {
//			
//		}
	}

}
