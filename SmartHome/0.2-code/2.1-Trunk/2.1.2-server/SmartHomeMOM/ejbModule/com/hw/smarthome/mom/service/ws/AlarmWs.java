package com.hw.smarthome.mom.service.ws;

import java.net.URL;
import java.util.Date;

import org.codehaus.xfire.client.Client;

import com.hw.smarthome.mom.constant.CloudConstants;
import com.hw.smarthome.mom.constant.HintConstant;
import com.hw.smarthome.mom.po.DataItemAFN08MomPo;
import com.hw.smarthome.mom.service.util.MOMServiceUtil;
import com.hw.smarthome.util.Ln;

public class AlarmWs extends Thread {
	// private static String addr;
	private DataItemAFN08MomPo frame = null;

	public AlarmWs(DataItemAFN08MomPo frame) {
		this.frame = frame;
	}

	@Override
	public void run() {
		Client client;

		try {
			// if (addr == null || "".equals(addr)) {
			// Context jndiContext = new InitialContext();
			// addr = (String) jndiContext
			// .lookup("ApplicationWsAddr");
			// }
			client = new Client(new URL(
					CloudConstants.APPLICATION_SERVER_WSDL));

			// "http://192.168.111.186:8080/smart/services/saveAddDeviceAlarm?wsdl"));
			Object[] inParams = new Object[] {
					frame.getGprs_id(),
					frame.getMedia(),
					frame.getStatus(),
					MOMServiceUtil
							.getXMLGregorianCalendar(new Date()),
					Double.parseDouble(frame.getChanlvalue()) };
			Object[] results = client
					.invoke(CloudConstants.APPLICATION_SERVER_METHOD_ALERT,
							inParams);
		} catch (Exception e) {
			Ln.e(AlarmWs.class, HintConstant.SEND_WS_FAIL, e);
		}
	}

}
