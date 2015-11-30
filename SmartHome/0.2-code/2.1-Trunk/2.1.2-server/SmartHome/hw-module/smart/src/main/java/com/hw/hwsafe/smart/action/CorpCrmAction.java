package com.hw.hwsafe.smart.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.pojo.BusinessPO;
import com.hw.hwsafe.smart.pojo.CorpCrmPO;
import com.hw.hwsafe.smart.pojo.CorpMaterialDetailPO;
import com.hw.hwsafe.smart.pojo.CorpMaterialPO;
import com.hw.hwsafe.smart.service.ICorpBusinessService;
import com.hw.hwsafe.smart.service.ICorpCrmService;
import com.hw.hwsafe.smart.service.ICorpMaterialService;
import com.hw.hwsafe.smart.service.ID002Service;
import com.hw.hwsafe.utils.StringUtil;
import com.ibm.wsdl.util.StringUtils;

public class CorpCrmAction extends BaseAction {
	@Autowired
	private ICorpCrmService corpCrmService;
	
	@Autowired
	private ICorpBusinessService corpBusinessService;
	
	@Autowired
	private ICorpMaterialService corpMaterialService;
	
	@Autowired
	private ID002Service d002Service;
	
	private CorpCrmPO corpCrmPO = new CorpCrmPO();
	
	private CorpMaterialPO corpMaterialPO = new CorpMaterialPO();
	
	private CorpMaterialDetailPO corpMaterialDetailPO = new CorpMaterialDetailPO();
	
	private List<CorpCrmPO> corpCrmPOList = new ArrayList<CorpCrmPO>();
	
	private List<CorpMaterialPO> materialPOList = new ArrayList<CorpMaterialPO>();
	
	private String ids="";
	
	private BusinessPO businessPO;
	//http://192.168.111.186:8080/smart/hwmobile/smart/corpcrm!getPageDa?page=1&rows=10&pk=ma001&sord=asc&sidx=&cellIndex=ma001,ma002,ma012
	//分页显示
	//必须参数page=1&rows=10&pk=ma001&cellIndex=ma001,ma002,ma012
	public String getDataInPage(){
		
		String ma002 = request.getParameter("MA002");
		
		String ma004 = request.getParameter("MA004");
		String date1 = request.getParameter("timeB");
		String date2 = request.getParameter("timeE");
		String ma007 = request.getParameter("pro");
		String ma008 = request.getParameter("city");
		String ma009 = request.getParameter("district");

		
		String ma005 = request.getParameter("TEL");
		
		
		
		
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			businessPO = corpBusinessService.doCorpLogin(map);
			pagerData = initPagerData();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pagerData", pagerData);
			paramMap.put("ma003", businessPO.getMa001());
			
			
			paramMap.put("ma002", ma002);

			paramMap.put("ma004", ma004);
			paramMap.put("ma005", ma005);
			paramMap.put("ma007", ma007);
			paramMap.put("ma008", ma008);
			paramMap.put("ma009", ma009);
			if (date1 != null && date1.length()>0) {
				paramMap.put("date1", new Date(date1));
			}
			if (date2 != null && date2.length()>0) {
				paramMap.put("date2", new Date(date2));
			}
			
			
			
			if(request.getParameter("focus")!=null){
				
				paramMap.put("ma011", 1);
			}
			pagerData.setRows(corpCrmService.retrieveByPage(paramMap));
			paramMap.put("selectCount", true);
			
			pagerData.setRecords(corpCrmService.retrieveByPage(paramMap));
			
		} catch (Exception e1) {
			e1.printStackTrace();

		}
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGER;
	}

	public String doAdd(){
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			BusinessPO po = corpBusinessService.doCorpLogin(map);
			CorpMaterialPO tempPO = new CorpMaterialPO();
			tempPO.setMa004(po.getMa001());
			materialPOList = corpMaterialService.retrieveInstanceByPO(tempPO);
			
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}
		return "add";
	}
	public String doEdit(){
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			BusinessPO po = corpBusinessService.doCorpLogin(map);
			
			if (po!=null) {
				String ma001 = request.getParameter("MA001");
				corpCrmPO = corpCrmService.retrieveInstanceById(ma001);

			}
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}

		return "edit";
	}
	public String doView(){
		return "view";
	}
	public String doList(){

		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			businessPO = corpBusinessService.doCorpLogin(map);
			CorpCrmPO crmPO = new CorpCrmPO();
			crmPO.setMa003(businessPO.getMa001());
			corpCrmPOList = corpCrmService.retrieveInstanceByPO(crmPO);
		
		} catch (Exception e1) {
			e1.printStackTrace();

		}
		
		return "list";
	}
	//导出excel
	public String expExcel() {
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			businessPO = corpBusinessService.doCorpLogin(map);
			CorpCrmPO crmPO = new CorpCrmPO();
			crmPO.setMa003(businessPO.getMa001());
			corpCrmPOList = corpCrmService.retrieveInstanceByPO(crmPO);
			
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}
		
		return "excelcrm";
	}
	//潜在用户list
	public String doListPotential(){
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			BusinessPO po = corpBusinessService.doCorpLogin(map);
			CorpCrmPO crmPO = new CorpCrmPO();
			crmPO.setMa003(po.getMa001());
			crmPO.setMa011(1);
			corpCrmPOList = corpCrmService.retrieveInstanceByPO(crmPO);
			
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}
		return "potentiallist";
	}
	//搜索
		public String doPotentialSearch(){
			String ma002 = request.getParameter("MA002");
		
			String ma004 = request.getParameter("MA004");
			String date1 = request.getParameter("timeB");
			String date2 = request.getParameter("timeE");
			String ma007 = request.getParameter("pro");
			String ma008 = request.getParameter("city");
			String ma009 = request.getParameter("district");

			
			String ma005 = request.getParameter("TEL");

		
			
			String account = request.getParameter("ACCOUNT");
			String keyString = request.getParameter("SESSIONID");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ma009", account);
			map.put("ma011", keyString);
			
			try {
				BusinessPO po = corpBusinessService.doCorpLogin(map);
				if (po==null) {
					callbackDataPO = new CallbackDataPO("1", "不合法操作", 0, null,
							null, "MaterialDetail");
					return JSON_DATA;
				}
		
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("ma002", ma002);
				map1.put("ma004", ma004);
				map1.put("ma005", ma005);
				map1.put("ma007", ma007);
				map1.put("ma008", ma008);
				map1.put("ma009", ma009);
				map1.put("ma011", 1);
				if (date1 != null && date1.length()>0) {
					map1.put("date1", new Date(date1));
				}
				if (date2 != null && date2.length()>0) {
				map1.put("date2", new Date(date2));
				}
				corpCrmPOList = corpCrmService.retrieveInstanceByMap(map1);
//				List<CorpCrmPO> result = corpCrmService.retrieveInstanceByMap(map1);
//				callbackDataPO = new CallbackDataPO("1", "查询数据成功",
//						result.size(), null,
//						JSONArray.fromObject(result), "MaterialDetail");
				
			} catch (Exception e1) {
				e1.printStackTrace();
				callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
						null, "MaterialDetail");
				return JSON_DATA;
			}
			
			
			
			return "potentiallist";
		}
	//导出潜在用户excel
	public String expFocusExcel() {
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			businessPO = corpBusinessService.doCorpLogin(map);
			CorpCrmPO crmPO = new CorpCrmPO();
			crmPO.setMa003(businessPO.getMa001());
			crmPO.setMa011(1);
			corpCrmPOList = corpCrmService.retrieveInstanceByPO(crmPO);
			
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}
		
		return "excelcrm";
	}
	public String doSaveAdd(){
		
		String ma007 = "";
		
		
		
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		
		List<String> errorDevice = new ArrayList<String>();   //存放添加失败或该设备未注册

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
			ma007= po.getMa001();
			corpCrmPO.setMa003(po.getMa001());
		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "U001");
			return JSON_DATA;
		}
		//判断是否批量加入
		List<String> deviceList = new ArrayList<String>();
		if (corpCrmPO.getMa002().contains("#")) {
			deviceList = Arrays.asList(corpCrmPO.getMa002().split("#"));
		}else {
			deviceList.add(corpCrmPO.getMa002());
		}
		
		for (String ma002 : deviceList) {
			//检查设备唯一性
			CorpCrmPO checkPO = new CorpCrmPO();
			checkPO.setMa002(ma002);
			List<CorpCrmPO> resultPo;
			try {
				resultPo = corpCrmService.retrieveInstanceByPO(checkPO);
				if (resultPo!=null && resultPo.size()>0) {
					errorDevice.add(ma002.toUpperCase());
					continue;
				}
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				errorDevice.add(ma002.toUpperCase());
				continue;
			}
			
			//检验该设备是否入库
			
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("ma002", ma002.toUpperCase());
			map2.put("ma004", corpCrmPO.getMa003());
			Integer intR;
			try {
				intR = d002Service.retrieveDeviceFromP001(map2);
				if (intR == 0) {
					errorDevice.add(ma002.toUpperCase());
					continue;
				}
			} catch (Exception e1) {
				errorDevice.add(ma002.toUpperCase());
			
				e1.printStackTrace();
				continue;
			}
			//增加
			corpCrmPO.setMa001(UUID.randomUUID().toString().trim().replaceAll("-", ""));
			corpCrmPO.setMa002(ma002);
			corpCrmPO.setMa012(new Date());
			try {
				corpCrmService.insertInstance(corpCrmPO);
				if (ids.length()<6) continue;
				String[] ma002s =  ids.split(",");
				for (int i = 0; i < ma002s.length; i++) {
					
					
					//获得耗材详细信息
					CorpMaterialDetailPO detailPO = new CorpMaterialDetailPO();
					detailPO.setMa007(ma007);
					CorpMaterialPO materialPO = null;
					try {
						materialPO = corpMaterialService.retrieveInstanceById(ma002s[i].trim());
					} catch (Exception e1) {
						
						e1.printStackTrace();
						callbackDataPO = new CallbackDataPO("0", "操作错误", 0, null,
								null, "U001");
						return JSON_DATA;
					}
					if (materialPO == null) {
						callbackDataPO = new CallbackDataPO("0", "不合法操作", 0, null,
								null, "U001");
						return JSON_DATA;
					}
					
					detailPO.setMa001(UUID.randomUUID().toString().trim().replaceAll("-", ""));
					detailPO.setMa006(0);
					detailPO.setMa004(new Date());
					Date date=new   Date();//取时间 
				     Calendar   calendar   =   new   GregorianCalendar(); 
				     calendar.setTime(date); 
				     calendar.add(calendar.DATE,materialPO.getMa003());//把日期往后增加一天.整数往后推,负数往前移动 
				     date=calendar.getTime();   //这个时间就是日期往后推一天的结果 


					detailPO.setMa005(date);
					detailPO.setMa002(ma002s[i].trim());
					detailPO.setMa003(corpCrmPO.getMa002());
					corpMaterialService.insertDetail(detailPO);
				}
				
			
				
			} catch (Exception e) {
				e.printStackTrace();
				errorDevice.add(ma002.toUpperCase());
				continue;
			
			}
		}
		if (errorDevice.size() == 0) {
			callbackDataPO = new CallbackDataPO("1", "新增成功", errorDevice.size(), null,
					JSONArray.fromObject(errorDevice), "U001");
		}else{
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试",  errorDevice.size(), null,
					JSONArray.fromObject(errorDevice), "U001");
		}
	
		return JSON_DATA;
	}
	public String doSaveEdit() {
		
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
		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "U001");
			return JSON_DATA;
		}
		
		//检查设备唯一性
				CorpCrmPO checkPO = new CorpCrmPO();
				checkPO.setMa002(corpCrmPO.getMa002());
				List<CorpCrmPO> resultPo;
				try {
					resultPo = corpCrmService.retrieveInstanceByPO(checkPO);
					if (resultPo==null || resultPo.size()>1) {
						callbackDataPO = new CallbackDataPO("0", "设备号重复", 0, null,
								null, "CORP_CRM");
						return JSON_DATA;
					}
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					callbackDataPO = new CallbackDataPO("0", "操作错误", 0, null,
							null, "CORP_CRM");
					return JSON_DATA;
				}
		
		

		try {
			corpCrmService.updateInstance(corpCrmPO);
			//corpMaterialService.insertDetail(corpMaterialDetailPO);
			callbackDataPO = new CallbackDataPO("1", "修改成功", 0, null,
					null, "U001");
		} catch (Exception e) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "U001");
			e.printStackTrace();
		}
		return JSON_DATA;
	}

	public String doDelete() {
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
		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "U001");
			return JSON_DATA;
		}
		try {
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("idArray", ids.split(","));

			corpCrmService.delBatchInstance(map1);
			callbackDataPO = new CallbackDataPO("1", "删除成功", 0, null,
					null, "U001");
		} catch (Exception e) {
			e.printStackTrace();
			callbackDataPO = new CallbackDataPO("1", "不合法操作", 0, null,
					null, "U001");
		}
		return JSON_DATA;
	}
	
	public String doShow() {
		try {
			corpCrmPO = corpCrmService.retrieveInstanceById(corpCrmPO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	
	//关注或取消关注
	public String doFocus() {
		
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
			String ma011 = request.getParameter("MA011");
			if (ids.length()>0) {
				Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("idArray", ids.split(","));
				map1.put("ma011", Integer.valueOf(ma011));
				corpCrmService.focusBatchInstance(map1);
				callbackDataPO = new CallbackDataPO("1", "操作成功", 0, null,
						null, "U001");
			}else {
				try {
					String ma001 = request.getParameter("MA001");
					
					CorpCrmPO upCrmPO = new CorpCrmPO();
					upCrmPO.setMa001(ma001);
					upCrmPO.setMa011(Integer.parseInt(ma011));
					corpCrmService.updateInstance(upCrmPO);
					callbackDataPO = new CallbackDataPO("1", "操作成功", 0, null,
							null, "U001");
				} catch (Exception e) {
					e.printStackTrace();
					callbackDataPO = new CallbackDataPO("1", "不合法操作", 0, null,
							null, "U001");
				}
				
			}
			
			
			
		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "U001");
			return JSON_DATA;
		}
		
		return JSON_DATA;
	}
	//搜索
	public String doSearch(){
		String ma002 = request.getParameter("MA002");
	
		String ma004 = request.getParameter("MA004");
		String date1 = request.getParameter("timeB");
		String date2 = request.getParameter("timeE");
		String ma007 = request.getParameter("pro");
		String ma008 = request.getParameter("city");
		String ma009 = request.getParameter("district");

		
		String ma005 = request.getParameter("TEL");

	
		
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		
		try {
			BusinessPO po = corpBusinessService.doCorpLogin(map);
			if (po==null) {
				callbackDataPO = new CallbackDataPO("1", "不合法操作", 0, null,
						null, "MaterialDetail");
				return JSON_DATA;
			}
	
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("ma002", ma002);
			map1.put("ma003", po.getMa001());
			map1.put("ma004", ma004);
			map1.put("ma005", ma005);
			map1.put("ma007", ma007);
			map1.put("ma008", ma008);
			map1.put("ma009", ma009);
			if (date1 != null && date1.length()>0) {
				map1.put("date1", new Date(date1));
			}
			if (date2 != null && date2.length()>0) {
			map1.put("date2", new Date(date2));
			}
			corpCrmPOList = corpCrmService.retrieveInstanceByMap(map1);
//			List<CorpCrmPO> result = corpCrmService.retrieveInstanceByMap(map1);
//			callbackDataPO = new CallbackDataPO("1", "查询数据成功",
//					result.size(), null,
//					JSONArray.fromObject(result), "MaterialDetail");
			
		} catch (Exception e1) {
			e1.printStackTrace();
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "MaterialDetail");
			return JSON_DATA;
		}
		
		
		
		return "list";
	}
	
	//发货地理信息分组 
	public String doListGeoAddrDevice() {
		
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
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ma003", po.getMa001());
			
			List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
			//result	=	corpCrmService.doListDeviceByGeo(paramMap);
			result = corpCrmService.doListMenu(paramMap);
			
			callbackDataPO = new CallbackDataPO("1", "操作成功", result.size(), null,
					JSONArray.fromObject(result), "CRM001");
			
		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "U001");
			return JSON_DATA;
		}
		return JSON_DATA;
	}
	//发货地理信息分组 
		public String doListMenu() {
			
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
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("ma003", po.getMa001());
				
				List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
				//result	=	corpCrmService.doListDeviceByGeo(paramMap);
				result = corpCrmService.doListMenu(paramMap);
				
				callbackDataPO = new CallbackDataPO("1", "操作成功", result.size(), null,
						JSONArray.fromObject(result), "CRM001");
				
			} catch (Exception e1) {
				callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
						null, "U001");
				return JSON_DATA;
			}
			return JSON_DATA;
		}
		public String doListMenuSensor() {
			
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
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("ma003", po.getMa001());
				paramMap.put("ma007", request.getParameter("PROVINCE"));
				paramMap.put("ma008", request.getParameter("CITY"));
				
				List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();

				result = corpCrmService.doListMenuSensor(paramMap);
				
				callbackDataPO = new CallbackDataPO("1", "操作成功", result.size(), null,
						JSONArray.fromObject(result), "CRM001");
				
			} catch (Exception e1) {
				callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
						null, "U001");
				return JSON_DATA;
			}
			return JSON_DATA;
		}
	//发货地理信息分组 查询发货量    
	public String doDeviceCountByGeo() {
		
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
			String geoType = request.getParameter("GEOTYPE");   //0 province 1 city
			String dateType = request.getParameter("DATETYPE");  //0 year  1 month 2 week
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("corpid", po.getMa001());
			paramMap.put("date1", new Date());
			if (geoType == null || geoType.equals("0")) {
				paramMap.put("groupby", "province");
			}
			if (geoType != null && geoType.equals("1")) {
				paramMap.put("groupby", "city");
			}
			if (dateType == null || dateType.equals("0")) {
				paramMap.put("haveon","year" );
			}
			if (dateType != null && dateType.equals("1")) {
				paramMap.put("haveon","month" );
			}
			if (dateType != null && dateType.equals("2")) {
				paramMap.put("haveon","week" );
			}
			
			List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
			result	=	corpCrmService.countGeoByTime(paramMap);
			
			callbackDataPO = new CallbackDataPO("1", "成功", result.size(), null,
					JSONArray.fromObject(result), "U001");
			
		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "U001");
			return JSON_DATA;
		}
		return JSON_DATA;
	}
	//通过设备号查找CRM信息
	public String getCrmInfoByCorpId(){
		
		String deviceId = request.getParameter("DEVICE");
		
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		if (org.apache.commons.lang.StringUtils.isBlank(deviceId)) {
			callbackDataPO = new CallbackDataPO("0", "参数为空", 0, null,
					null, "CRM");
			
		}else {
			try {
				BusinessPO po = corpBusinessService.doCorpLogin(map);
				if (po==null) {
					callbackDataPO = new CallbackDataPO("1", "不合法操作", 0, null,
							null, "U001");
					return JSON_DATA;
				}
				CorpCrmPO crmPOCondition = new CorpCrmPO();
				crmPOCondition.setMa002(deviceId);
				crmPOCondition.setMa003(po.getMa001());
				List<CorpCrmPO> result = corpCrmService.retrieveInstanceByPO(crmPOCondition);
				callbackDataPO = new CallbackDataPO("1", "成功", result.size(), null,
						JSONArray.fromObject(result), "U001");
				
			} catch (Exception e1) {
				callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
						null, "U001");
				return JSON_DATA;
			}
		}
		
		return JSON_DATA;
		
	}
	
	public ICorpCrmService getCorpCrmService() {
		return corpCrmService;
	}
	public void setCorpCrmService(ICorpCrmService corpCrmService) {
		this.corpCrmService = corpCrmService;
	}
	public CorpCrmPO getCorpCrmPO() {
		return corpCrmPO;
	}
	public void setCorpCrmPO(CorpCrmPO corpCrmPO) {
		this.corpCrmPO = corpCrmPO;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public ICorpBusinessService getCorpBusinessService() {
		return corpBusinessService;
	}
	public void setCorpBusinessService(ICorpBusinessService corpBusinessService) {
		this.corpBusinessService = corpBusinessService;
	}
	public List<CorpCrmPO> getCorpCrmPOList() {
		return corpCrmPOList;
	}
	public void setCorpCrmPOList(List<CorpCrmPO> corpCrmPOList) {
		this.corpCrmPOList = corpCrmPOList;
	}
	public ICorpMaterialService getCorpMaterialService() {
		return corpMaterialService;
	}
	public void setCorpMaterialService(ICorpMaterialService corpMaterialService) {
		this.corpMaterialService = corpMaterialService;
	}
	public List<CorpMaterialPO> getMaterialPOList() {
		return materialPOList;
	}
	public void setMaterialPOList(List<CorpMaterialPO> materialPOList) {
		this.materialPOList = materialPOList;
	}
	public CorpMaterialPO getCorpMaterialPO() {
		return corpMaterialPO;
	}
	public void setCorpMaterialPO(CorpMaterialPO corpMaterialPO) {
		this.corpMaterialPO = corpMaterialPO;
	}
	public CorpMaterialDetailPO getCorpMaterialDetailPO() {
		return corpMaterialDetailPO;
	}
	public void setCorpMaterialDetailPO(CorpMaterialDetailPO corpMaterialDetailPO) {
		this.corpMaterialDetailPO = corpMaterialDetailPO;
	}
	public BusinessPO getBusinessPO() {
		return businessPO;
	}
	public void setBusinessPO(BusinessPO businessPO) {
		this.businessPO = businessPO;
	}
	public ID002Service getD002Service() {
		return d002Service;
	}
	public void setD002Service(ID002Service d002Service) {
		this.d002Service = d002Service;
	}

	
}
