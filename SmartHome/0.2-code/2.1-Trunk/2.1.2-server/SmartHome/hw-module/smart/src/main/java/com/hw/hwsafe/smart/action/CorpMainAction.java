package com.hw.hwsafe.smart.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPopupMenu;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.pojo.BusinessPO;
import com.hw.hwsafe.smart.pojo.CorpCrmPO;
import com.hw.hwsafe.smart.service.ICorpBusinessService;
import com.hw.hwsafe.smart.service.ICorpCrmService;
import com.hw.hwsafe.smart.service.ICorpMaterialService;
import com.hw.hwsafe.smart.service.ICorpOnlineService;
import com.hw.hwsafe.smart.service.ICorpRankService;

public class CorpMainAction extends BaseAction {

	@Autowired
	private ICorpOnlineService corpOnlineService;
	
	@Autowired
	private ICorpRankService corpRankService;
	
	@Autowired
	private ICorpBusinessService corpBusinessService;
	
	@Autowired
	private ICorpMaterialService corpMaterialService;
	
	@Autowired
	private ICorpCrmService corpCrmService;
	
	public String doindex(){
		return "main";
	}
	
	public String cityInRank(){
		
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			BusinessPO po = corpBusinessService.doCorpLogin(map);
			if (po==null) {
				callbackDataPO = new CallbackDataPO("1", "不合法操作", 0, null,
						null, "U001");
				return JSON_DATA;
			}
			
			Map<String, Object> conditionMap = new HashMap<String, Object>();
	
		
			conditionMap.put("corpid", po.getMa001());

			List<Map<String, Object>> result = corpRankService.retrieveCityInRank(conditionMap);
			callbackDataPO = new CallbackDataPO("1", "成功", 0, null,
					JSONArray.fromObject(result), "corprank");
			
		} catch (Exception e1) {
			e1.printStackTrace();
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "U001");
			return JSON_DATA;
		}
		
		return JSON_DATA;
		
	}
	// 排名
	public String rankWith(){

		
		
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			BusinessPO po = corpBusinessService.doCorpLogin(map);
			if (po==null) {
				callbackDataPO = new CallbackDataPO("1", "不合法操作", 0, null,
						null, "U001");
				return JSON_DATA;
			}
			String sensorType = request.getParameter("SENSOR");
			String type = request.getParameter("TYPE");    // 1 是省  2 是市
			String name = request.getParameter("NAME");   //省或市的名称
			String rankBy = request.getParameter("RANKTYPE");   //month week
			
			Map<String, Object> conditionMap = new HashMap<String, Object>();
			conditionMap.put("name", name);
			if (type.toLowerCase().equals("province")) {
				conditionMap.put("type", "1");
			}else {
				conditionMap.put("type", "2");
			}
			
			conditionMap.put("sensor", sensorType);
			conditionMap.put("corpid", po.getMa001());
			
			if (rankBy.toLowerCase().equals("month")) {
				corpRankService.retrieveRankByMonth(conditionMap);
			}
			if (rankBy.toLowerCase().equals("week")) {
				corpRankService.retrieveRankByWeek(conditionMap);
			}
			
		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "U001");
			return JSON_DATA;
		}
		
		return JSON_DATA;

		
	}
	// 区域排名
	public String rankWithGeo(){
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			BusinessPO po = corpBusinessService.doCorpLogin(map);
			if (po==null) {
				callbackDataPO = new CallbackDataPO("1", "不合法操作", 0, null,
						null, "U001");
				return JSON_DATA;
			}
			String sensorType = request.getParameter("SENSOR");

			String geotype = request.getParameter("TYPE1");    // 1 是省  2 是市 
	
			String dateType = request.getParameter("TYPE2");   //month week
			
			Map<String, Object> conditionMap = new HashMap<String, Object>();

			if (geotype==null || geotype.equals("1")) {
				geotype = "province";
			}
			if (geotype!=null && geotype.equals("2")) {
				geotype = "city";
			}
			if (dateType==null || dateType.equals("1")) {
				dateType = "week";
			}
			if (dateType!=null && dateType.equals("2")) {
				dateType = "month";
			}
			conditionMap.put("dateType", dateType);
			conditionMap.put("geotype", geotype);
			
			conditionMap.put("sensor", sensorType);//气体类型 MEDIA_TYPE_CO = "04";MEDIA_TYPE_CO2 = "30";MEDIA_TYPE_CH4 = "01";MEDIA_TYPE_C6H6 = "1B";MEDIA_TYPE_CH2O = "17";MEDIA_TYPE_PM25 = "D8";MEDIA_TYPE_TEMPERATURE = "C9";MEDIA_TYPE_HUMIDITY = "CA";MEDIA_TYPE_VOC = "D9";
			conditionMap.put("corpid", po.getMa001());
			

			List<Map<String, Object>>	result = corpRankService.rankOnCondition(conditionMap);
	
			callbackDataPO = new CallbackDataPO("1", "成功", 0, null,
					JSONArray.fromObject(result), "corprank");
			
		} catch (Exception e1) {
			e1.printStackTrace();
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "U001");
			return JSON_DATA;
		}
		
		return JSON_DATA;
	}
	//分页
	
	public String getDataInPage(){
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			BusinessPO po = corpBusinessService.doCorpLogin(map);
			
			if (po==null) {
				callbackDataPO = new CallbackDataPO("1", "不合法操作", 0, null,
						null, "U001");
				return JSON_DATA;
			}
			
			pagerData = initPagerData();
			String sensorType = request.getParameter("SENSOR");

			
			Map<String, Object> conditionMap = new HashMap<String, Object>();

			
			conditionMap.put("sensor", sensorType);//气体类型 MEDIA_TYPE_CO = "04";MEDIA_TYPE_CO2 = "30";MEDIA_TYPE_CH4 = "01";MEDIA_TYPE_C6H6 = "1B";MEDIA_TYPE_CH2O = "17";MEDIA_TYPE_PM25 = "D8";MEDIA_TYPE_TEMPERATURE = "C9";MEDIA_TYPE_HUMIDITY = "CA";MEDIA_TYPE_VOC = "D9";
			conditionMap.put("corpid", po.getMa001());
			conditionMap.put("pagerData", pagerData);
			if (request.getParameter("TYPE")!=null){
					conditionMap.put("dateType", "month");    //月
			}

			pagerData.setRows(corpRankService.rankByUser(conditionMap));
			
			conditionMap.put("selectCount", true);
			
			pagerData.setRecords(corpRankService.retrieveByPage(conditionMap));
			
		
			
		} catch (Exception e1) {
			e1.printStackTrace();
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "U001");
			return JSON_DATA;
		}
	
		
		return JSON_PAGER;
	}
	
	
	// 用户排名
		public String rankWithUser(){
			String account = request.getParameter("ACCOUNT");
			String keyString = request.getParameter("SESSIONID");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ma009", account);
			map.put("ma011", keyString);
			try {
				BusinessPO po = corpBusinessService.doCorpLogin(map);
				if (po==null) {
					callbackDataPO = new CallbackDataPO("1", "不合法操作", 0, null,
							null, "U001");
					return JSON_DATA;
				}
				String sensorType = request.getParameter("SENSOR");


				
				Map<String, Object> conditionMap = new HashMap<String, Object>();

				
				
				conditionMap.put("sensor", sensorType);//气体类型 MEDIA_TYPE_CO = "04";MEDIA_TYPE_CO2 = "30";MEDIA_TYPE_CH4 = "01";MEDIA_TYPE_C6H6 = "1B";MEDIA_TYPE_CH2O = "17";MEDIA_TYPE_PM25 = "D8";MEDIA_TYPE_TEMPERATURE = "C9";MEDIA_TYPE_HUMIDITY = "CA";MEDIA_TYPE_VOC = "D9";
				conditionMap.put("corpid", po.getMa001());
				

				List<Map<String, Object>>	result = corpRankService.rankByUser(conditionMap);
		
				callbackDataPO = new CallbackDataPO("1", "成功", 0, null,
						JSONArray.fromObject(result), "corprank");
				
			} catch (Exception e1) {
				e1.printStackTrace();
				callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
						null, "U001");
				return JSON_DATA;
			}
			
			return JSON_DATA;
		}
		
	//首页获得个数
	public String doGetCount() {
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			BusinessPO po = corpBusinessService.doCorpLogin(map);
			if (po==null) {
				callbackDataPO = new CallbackDataPO("1", "不合法操作", 0, null,
						null, "U001");
				return JSON_DATA;
			}
			
			
			Map<String, Object> conditionMap = new HashMap<String, Object>();
			
			conditionMap.put("corpid", po.getMa001());
			
			Integer crmTotal = corpCrmService.countAll(conditionMap);
			Integer crmFocusTotal = corpCrmService.countFocus(conditionMap);
			Integer onlineTotal = corpOnlineService.countAll(conditionMap);
			Integer materialTotal = corpMaterialService.countAll(conditionMap);
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("crm", crmTotal);
			result.put("focus", crmFocusTotal);
			result.put("online", onlineTotal);
			result.put("material", materialTotal);
			
			callbackDataPO = new CallbackDataPO("1", "操作成功", 1, result.toString(),
					JSONObject.fromObject(result), "corpmain");
			
		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "corpmain");
			return JSON_DATA;
		}
		return JSON_DATA;
	}
	
	

	public ICorpOnlineService getCorpOnlineService() {
		return corpOnlineService;
	}


	public void setCorpOnlineService(ICorpOnlineService corpOnlineService) {
		this.corpOnlineService = corpOnlineService;
	}


	public ICorpRankService getCorpRankService() {
		return corpRankService;
	}


	public void setCorpRankService(ICorpRankService corpRankService) {
		this.corpRankService = corpRankService;
	}


	public ICorpBusinessService getCorpBusinessService() {
		return corpBusinessService;
	}


	public void setCorpBusinessService(ICorpBusinessService corpBusinessService) {
		this.corpBusinessService = corpBusinessService;
	}


	public ICorpMaterialService getCorpMaterialService() {
		return corpMaterialService;
	}


	public void setCorpMaterialService(ICorpMaterialService corpMaterialService) {
		this.corpMaterialService = corpMaterialService;
	}


	public ICorpCrmService getCorpCrmService() {
		return corpCrmService;
	}


	public void setCorpCrmService(ICorpCrmService corpCrmService) {
		this.corpCrmService = corpCrmService;
	}
	
	
}
