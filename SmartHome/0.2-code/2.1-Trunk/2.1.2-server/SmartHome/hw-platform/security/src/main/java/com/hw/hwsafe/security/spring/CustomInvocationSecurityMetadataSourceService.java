package com.hw.hwsafe.security.spring;

//import com.hwsensor.permission.service.ISysModuleService;

import java.util.ArrayList;
import java.util.Collection;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

@Service
public class CustomInvocationSecurityMetadataSourceService implements
		FilterInvocationSecurityMetadataSource {

	// @Autowired
	// private ISysModuleService sysModuleService;
	// private static Map<String, Collection<ConfigAttribute>> resourceMap =
	// null;

	public CustomInvocationSecurityMetadataSourceService() {
		loadResourceDefine();
	}

	private void loadResourceDefine() {
		// try
		// {
		// resourceMap = new HashMap();
		//
		// Map allPermArgs = new HashMap();
		// allPermArgs.put("IS_VALID", "10");
		// allPermArgs.put("IS_SHOW", "10");
		//
		// if (this.sysModuleService == null) {
		// this.sysModuleService =
		// ((ISysModuleService)ServiceBeanUtils.getBean("sysModuleService"));
		// }
		//
		// List<SysModulePO> permPoList =
		// sysModuleService.retrieveByCondition(allPermArgs);
		//
		// for (SysModulePO permPo : permPoList)
		// {
		// if ((permPo.getURL() == null) || ("".equals(permPo.getURL())) ||
		// (permPo.getMENU_CODE() == null) ||
		// ("".equals(permPo.getMENU_CODE()))) continue;
		// ConfigAttribute ca = new SecurityConfig(
		// permPo.getMENU_CODE());
		// String url = permPo.getURL();
		//
		// if (resourceMap.containsKey(url)) {
		// Collection value =
		// (Collection)resourceMap
		// .get(url);
		// value.add(ca);
		// resourceMap.put(url, value);
		// } else {
		// Collection atts = new ArrayList();
		// atts.add(ca);
		// resourceMap.put(url, atts);
		// }
		// }
		// }
		// catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();

		int firstQuestionMarkIndex = url.indexOf("?");

		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}

		Collection<ConfigAttribute> noRole = new ArrayList<ConfigAttribute>();
		SecurityConfig sc = new SecurityConfig("ROLE_NO_USER");
		noRole.add(sc);
		return noRole;
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

	// public ISysModuleService getSysModuleService() {
	// return this.sysModuleService;
	// }
	//
	// public void setSysModuleService(ISysModuleService sysModuleService) {
	// this.sysModuleService = sysModuleService;
	// }
}