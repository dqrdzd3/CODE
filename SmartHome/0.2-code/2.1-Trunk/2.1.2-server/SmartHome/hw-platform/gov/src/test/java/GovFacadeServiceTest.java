import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.hw.hwsafe.gov.pojo.C002PO;
import com.hw.hwsafe.platform.pojo.UserPO;


public class GovFacadeServiceTest {

	@Test
	public void test() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("MA002", "22222");
		map.put("MA003", "33333");
		map.put("MA004", "44444");
		try {
			C002PO c002PO = map2PO(map);
			System.out.println(c002PO.getMa002());
			System.out.println(c002PO.getMa003());
			System.out.println(c002PO.getMa004());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		C002PO c002po1 = new C002PO();
		c002po1.setMa001("111111");
		c002po1.setMa002("22222");
		c002po1.setMa003("333333");
		c002po1.setMa004("4444444");
		c002po1.setMa005("55555");
		try {
			Map<String, Object> map1 = PO2Map(c002po1);
			System.out.println(map1.get("MA001"));
			System.out.println(map1.get("MA002"));
			System.out.println(map1.get("MA003"));
			System.out.println(map1.get("MA004"));
			System.out.println(map1.get("MA005"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private C002PO map2PO(Map<String,Object> map) throws Exception{
	    C002PO c002PO = null;
		if (!map.isEmpty()) {
			c002PO = new C002PO();
			for (String k : map.keySet()) {
				Object v = "";
				if (!k.isEmpty()) {
					v = map.get(k);
				}
				Field[] fields = C002PO.class.getDeclaredFields();
				for (Field field : fields) {
					if (field.getName().toUpperCase().equals(k)) {
						int mod = field.getModifiers();
						if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
							continue;
						}
						field.setAccessible(true);
						field.set(c002PO, v);
					}

				}
			}
		}
		return c002PO;
	}
	private Map<String, Object> PO2Map(C002PO c002PO) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = C002PO.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String proName = field.getName();
			Object proValue = field.get(c002PO);
			map.put(proName.toUpperCase(), proValue);
		}
		return map;
	}
}
