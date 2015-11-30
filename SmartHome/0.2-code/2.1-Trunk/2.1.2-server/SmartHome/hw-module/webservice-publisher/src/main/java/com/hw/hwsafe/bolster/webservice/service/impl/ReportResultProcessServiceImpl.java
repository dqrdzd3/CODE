
/**
 * @title ReportResultProcessServiceImpl.java
 * @package com.hw.hwsafe.bolster.webservice.service.impl
 * @author 杜群星
 * @create_time 2013-11-13 下午3:14:16
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.bolster.webservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hw.hwsafe.bolster.webservice.service.IReportResultProcessService;


/**
 * <p>
 * 对隐患上报处理后返回的结果处理的服务实现
 * </p>
 */

public class ReportResultProcessServiceImpl implements IReportResultProcessService{
	private static final String USER = "client";
	private static final String PASSWORD = "123456";
	
	@Override
	public String receiveProcessResult(String id,String result,String user,String password) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("result", result);
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		list.add(map);
		return null;
	}


	private String auth(String user, String password){
		String s = "003";
		if(USER.equals(user) && PASSWORD.equals(password)){
			s = "004";
		}
		return s;
	}
	

}
