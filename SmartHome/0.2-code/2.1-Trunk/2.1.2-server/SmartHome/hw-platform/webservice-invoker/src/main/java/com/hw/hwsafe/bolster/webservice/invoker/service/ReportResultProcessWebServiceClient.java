
/**
 * @title ReportResultProcessWebServiceInvoker.java
 * @package com.hw.hwsafe.bolster.webservice.invoker.service
 * @author 杜群星
 * @create_time 2013-11-14 下午3:10:23
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.bolster.webservice.invoker.service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import com.hw.hwsafe.bolster.webservice.exception.ConnectException;


/**
 * <p>
 * 隐患上报返回结果处理的客户端
 * </p>
 */

public final class ReportResultProcessWebServiceClient {
	private String service;
	private String user;
	private String password;
	
	private IReportResultProcessService reportResultProcessService;
	public ReportResultProcessWebServiceClient(String service,String user,String password){
		this.service = service;
		this.user = user;
		this.password = password;
	}
	
	private void createService() throws ConnectException{
		Service serv = new ObjectServiceFactory().create(IReportResultProcessService.class);
		XFireProxyFactory factory = new XFireProxyFactory();
		try {
			reportResultProcessService = (IReportResultProcessService) factory
					.create(serv, this.service);
			
		} catch (MalformedURLException e) {
			throw new ConnectException();
		}
	}
	
	public String sendPrecessResult(String id,String result) throws Exception {
		createService();
 		String s = reportResultProcessService.receiveProcessResult(id,result,user,password);
		return s;
	}
	
	public String sendPrecessResult(List<Map<String, String>> list) throws Exception {
		createService();
		String s = reportResultProcessService.receiveProcessResult(list,user,password);
		return s;
	}
	
	
}
