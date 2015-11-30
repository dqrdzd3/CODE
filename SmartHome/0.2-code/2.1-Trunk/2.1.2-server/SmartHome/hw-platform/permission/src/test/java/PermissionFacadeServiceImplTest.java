


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.utils.ConverUtil;

public class PermissionFacadeServiceImplTest {
	
	@Test
	public void TestPO2Map() throws Exception{
		UserPO user = new UserPO();
		user.setUUID("aaaa");
		user.setAGE(12);
		user.setCREATE_DATE(new Date());
		Map<String, Object> map = ConverUtil.PO2Map(user);
		System.out.println(map.get("UUID"));
		System.out.println(map.get("AGE"));
		System.out.println(map.get("CREATE_DATE"));
		
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("UUID", "bbbb");
		map1.put("AGE", 22);
		map1.put("CREATE_DATE", new Date());
		UserPO user2 = new UserPO();
		user2 = (UserPO)ConverUtil.map2PO(map1,user2);
		System.out.println(user2.getUUID());
		System.out.println(user2.getAGE());
		System.out.println(user2.getCREATE_DATE());
	}


}
