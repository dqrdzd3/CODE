package test;

import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import com.hw.hwsafe.bolster.webservice.invoker.service.IBolsterWebservice;

public class MyTest {
	public static void main(String[] args) {
		 String url = "http://192.168.111.193:8080/bolster/webservice/bolsterWebservice";
		 Service service = new ObjectServiceFactory().create(IBolsterWebservice.class);  
		 XFireProxyFactory factory = new XFireProxyFactory() ; 
		 try {
			IBolsterWebservice bolsterWebservice = (IBolsterWebservice) factory.create(service,url);
			
			System.out.println(bolsterWebservice.isActivable("admin", "ZCPT"));
			System.out.println(bolsterWebservice.isActivable("admin1", "ZCPT"));
			
			System.out.println(bolsterWebservice.isAllowable("admin", "ZCPT"));
			System.out.println(bolsterWebservice.isAllowable("admin1", "ZCPT"));
			
			System.out.println(bolsterWebservice.getUserInfo("admin"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
