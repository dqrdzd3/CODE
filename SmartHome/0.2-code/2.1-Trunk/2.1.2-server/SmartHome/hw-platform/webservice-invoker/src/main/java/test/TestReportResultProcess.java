package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import com.hw.hwsafe.bolster.webservice.invoker.service.IReportResultProcessService;
import com.hw.hwsafe.bolster.webservice.invoker.service.ReportResultProcessWebServiceClient;

public class TestReportResultProcess {
	public static void main(String[] args) {
		
//		 String url = "http://192.168.111.194:8080/hwsafe/webservice/reportResultProcessWebService";
//		 Service service = new ObjectServiceFactory().create(IReportResultProcessService.class);  
//		 XFireProxyFactory factory = new XFireProxyFactory() ; 
//		 try {
//			IReportResultProcessService reportResultProcessService = (IReportResultProcessService) factory.create(service,url);
//			String s = reportResultProcessService.receiveProcessResult("3d7da6d8-f65d-4ed7-a59c-3092efb4d61e","1");
//			System.out.println(s);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		String service1 = "http://192.168.111.194:8080/hwsafe/webservice/reportResultProcessWebService";
		String service2 = "http://192.168.111.194:8080/hwsafe/webservice/reportResultProcessWebService";
		ReportResultProcessWebServiceClient client1 = new ReportResultProcessWebServiceClient(service1, "client", "123456");
		ReportResultProcessWebServiceClient client2 = new ReportResultProcessWebServiceClient(service2, "client", "123456");
		try {
			String s1 = client1.sendPrecessResult("3d7da6d8-f65d-4ed7-a59c-3092efb4d61e","1");
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("id", "3d7da6d8-f65d-4ed7-a59c-3092efb4d61e");
			map1.put("result", "2");
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("id", "784a0118-70af-486a-a2a6-dc4bf70cd087");
			map2.put("result", "2");
			list.add(map1);
			list.add(map2);
			String s2 = client2.sendPrecessResult("3d7da6d8-f65d-4ed7-a59c-3092efb4d61e","1");
			System.out.println(s1);
			System.out.println(s2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
