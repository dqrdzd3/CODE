package com.hw.hwsafe.bolster.webservice.invoker.service;

import java.net.MalformedURLException;

import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import com.hw.hwsafe.platform.constants.ConfConstants;

public final class BolsterWebserviceInvoker {
	
	private static final String WEBSERVICE_PATH = "/webservice/bolsterWebservice";
//	private static final String WEBSERVICE_URL = ConfConstants.BOLSTER_SERVER + WEBSERVICE_PATH;
	private static final String WEBSERVICE_URL =  WEBSERVICE_PATH;
	
	public static final boolean isAllowable(String loginName,
			String subsystemCode) throws MalformedURLException, Exception {
		return Boolean.valueOf(getBolsterWebservice().isAllowable(loginName, subsystemCode));
	}
	
	public static final boolean isActivable(String loginName,
			String subsystemCode) throws MalformedURLException, Exception {
		return Boolean.valueOf(getBolsterWebservice().isActivable(loginName, subsystemCode));
	}

	public static final void updateActiveFlag(String loginName,
			String subsystemCode, boolean activeFlag)
			throws MalformedURLException, Exception {
		getBolsterWebservice().updateActiveFlag(loginName, subsystemCode,
				Boolean.valueOf(activeFlag).toString());
	}
	
	public static final String getUserInfo(String loginName) 
			throws MalformedURLException, Exception{
		return getBolsterWebservice().getUserInfo(loginName);
	}
	
	private static IBolsterWebservice getBolsterWebservice() throws MalformedURLException {
		Service service = new ObjectServiceFactory().create(IBolsterWebservice.class);
		XFireProxyFactory factory = new XFireProxyFactory();
		return (IBolsterWebservice) factory.create(service,WEBSERVICE_URL);
	}

}
