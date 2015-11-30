/**
 * 
 */
package com.hw.hwsafe.gov.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.gov.pojo.C002PO;
import com.hw.hwsafe.gov.service.IC002Service;
import com.hw.hwsafe.gov.service.IGovFacadeService;
import com.hw.hwsafe.platform.pojo.UserPO;

/**
 * @author 马宁
 *
 */
public class GovFacadeServiceImpl implements IGovFacadeService {
	private static Logger log = Logger.getLogger(GovFacadeServiceImpl.class);
	@Autowired
	private IC002Service c002Service;
	/* (non-Javadoc)
	 * @see com.hw.hwsafe.gov.service.IGovFacadeService#retrieveAllORG(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> retrieveAllORG() throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<C002PO> c002List = c002Service.retrieveAll();
		for (C002PO	c002PO  : c002List) {
			Map<String, Object> map = new HashMap<String, Object>();
			map = PO2Map(c002PO);
			list.add(map);
		}
		log.info("查询组织结构的列表数量："+list.size());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.gov.service.IGovFacadeService#retrieveById(java.lang.String)
	 */
	@Override
	public Map<String, Object> retrieveById(String id) throws Exception {
		C002PO c002PO = c002Service.retrieveC002ByMa001(id);
		log.info("查询组织结构对象："+c002PO.getMa001());
		return PO2Map(c002PO);
	}

	@Override
	public List<C002PO> retrieveAllValid() throws Exception {
		return c002Service.retrieveAllValid();
	}
	/* (non-Javadoc)
	 * @see com.hw.hwsafe.gov.service.IGovFacadeService#insertORG(java.util.Map)
	 */
	@Override
	public String insertORG(Map<String, Object> map) throws Exception {
		C002PO c002PO = map2PO(map);
		log.info("添加组织结构:"+c002PO.getMa002());
		return c002Service.insertORG(c002PO);
	}

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.gov.service.IGovFacadeService#updateORG(java.util.Map)
	 */
	@Override
	public boolean updateORG(Map<String, Object> map) throws Exception {
		C002PO c002PO = map2PO(map);
		log.info("修改组织结构:"+c002PO.getMa001());
		return c002Service.updateORG(c002PO);
	}

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.gov.service.IGovFacadeService#isUniqueName(java.util.Map)
	 */
	@Override
	public boolean isUniqueName(Map<String, Object> map) throws Exception {
		C002PO c002PO = map2PO(map);
		return c002Service.isUniqueName(c002PO);
	}

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.gov.service.IGovFacadeService#isUniqueCode(java.util.Map)
	 */
	@Override
	public boolean isUniqueCode(Map<String, Object> map) throws Exception {
		C002PO c002PO = map2PO(map);
		return c002Service.isUniqueCode(c002PO);
	}

	/* (non-Javadoc)
	 * @see com.hw.hwsafe.gov.service.IGovFacadeService#deleteORGS(java.lang.String)
	 */
	@Override
	public boolean deleteORGS(List<String> list) throws Exception {
		String ids = "";
		for (String s : list) {
			ids +=  s+",";
		}
		log.info("删除组织机构："+ids);
		return c002Service.deleteORGByIds(ids);
	}
	
	private Map<String, Object> PO2Map(C002PO c002PO) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = C002PO.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String proName = field.getName();
			Object proValue = field.get(c002PO);
			map.put(proName.toUpperCase(), proValue);
			log.info("*****组织结构转map*****");
			log.info("key:"+proName+"--value:"+proValue);
			log.info("*****组织结构转map*****");
		}
		return map;
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
				log.info("*****map转组织结构*****");
				log.info("key:"+k+"value："+v);
				log.info("*****map转组织结构*****");
				Field[] fields = C002PO.class.getDeclaredFields();
				for (Field field : fields) {
					int mod = field.getModifiers();
					if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
						continue;
					}
					if (field.getName().toUpperCase().equals(k)) {
						field.setAccessible(true);
						field.set(c002PO, v);
					}

				}
			}
		}
		log.info("*****map转组织结构*****");
		log.info("ma001:"+c002PO.getMa001());
		log.info("ma002:"+c002PO.getMa002());
		log.info("ma003:"+c002PO.getMa003());
		log.info("ma004:"+c002PO.getMa004());
		log.info("ma005:"+c002PO.getMa005());
		log.info("ma006:"+c002PO.getMa006());
		log.info("ma007:"+c002PO.getMa007());
		log.info("ma008:"+c002PO.getMa008());
		log.info("ma009:"+c002PO.getMa009());
		log.info("ma010:"+c002PO.getMa010());
		log.info("ma011:"+c002PO.getMa011());
		log.info("ma012:"+c002PO.getMa012());
		log.info("ma013:"+c002PO.getMa013());
		log.info("*****map转组织结构*****");
		return c002PO;
	}



}
