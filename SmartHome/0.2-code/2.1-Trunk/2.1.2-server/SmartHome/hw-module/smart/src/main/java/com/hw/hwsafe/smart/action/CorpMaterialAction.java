package com.hw.hwsafe.smart.action;

import java.util.ArrayList;
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
import com.hw.hwsafe.smart.pojo.CorpMaterialDetailPO;
import com.hw.hwsafe.smart.pojo.CorpMaterialPO;
import com.hw.hwsafe.smart.service.ICorpBusinessService;
import com.hw.hwsafe.smart.service.ICorpMaterialService;

public class CorpMaterialAction extends BaseAction {

	
	@Autowired
	private ICorpMaterialService corpMaterialService;
	
	@Autowired
	private ICorpBusinessService corpBusinessService;
	
	
	private CorpMaterialDetailPO corpMaterialDetailPO = new CorpMaterialDetailPO();
	private CorpMaterialPO corpMaterialPO = new CorpMaterialPO();
	private List<CorpMaterialPO> materialPOList = new ArrayList<CorpMaterialPO>();
	private List<CorpMaterialDetailPO> materialDetailPOList = new ArrayList<CorpMaterialDetailPO>();
	
	private String ids;
	
	private BusinessPO businessPO;
	
	public String doAdd(){
		return "add";
	}
	public String doEdit(){
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
			if (businessPO!=null) {
				CorpMaterialPO tempPO = new CorpMaterialPO();
				tempPO.setMa004(businessPO.getMa001());
				materialPOList = corpMaterialService.retrieveInstanceByPO(tempPO);
				
				CorpMaterialDetailPO tempDetailPO = new CorpMaterialDetailPO();
				tempDetailPO.setMa007(businessPO.getMa001());
				materialDetailPOList = corpMaterialService.retrieveDetailByPO(tempDetailPO);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		
		}
		return "list";
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
				String ma002 = request.getParameter("MA002");
				String ma003 = request.getParameter("MA003");
				String ma004 = request.getParameter("MA004");
				String ma005 = request.getParameter("MA005");
				
				String tel = request.getParameter("TEL");

				
				Map<String, Object> conditionMap = new HashMap<String, Object>();

				
				conditionMap.put("ma002", ma002);
				conditionMap.put("ma003", ma003);
				conditionMap.put("ma004", ma004);
				conditionMap.put("ma005", ma005);
				
				conditionMap.put("ma007", po.getMa001());
				conditionMap.put("ma009", tel);
				conditionMap.put("pagerData", pagerData);
				


				pagerData.setRows(corpMaterialService.retrieveByPage(conditionMap));
				
				conditionMap.put("selectCount", true);
				
				pagerData.setRecords(corpMaterialService.retrieveByPage(conditionMap));
				
			
				
			} catch (Exception e1) {
				e1.printStackTrace();
				callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
						null, "U001");
				return JSON_DATA;
			}
		
			
			return JSON_PAGER;
		}
		
		
	public String expExcel(){
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			businessPO = corpBusinessService.doCorpLogin(map);
			if (businessPO!=null) {
				CorpMaterialPO tempPO = new CorpMaterialPO();
				tempPO.setMa004(businessPO.getMa001());
				materialPOList = corpMaterialService.retrieveInstanceByPO(tempPO);
				
				CorpMaterialDetailPO tempDetailPO = new CorpMaterialDetailPO();
				tempDetailPO.setMa007(businessPO.getMa001());
				materialDetailPOList = corpMaterialService.retrieveDetailByPO(tempDetailPO);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		
		}
		return "excelmaterial";
	}
	
	public String doSaveAdd(){
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		String materialName = request.getParameter("materialType");
		String materialDay = request.getParameter("materialTime");	
		String corpidString = "";
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
			corpidString = po.getMa001();
		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "U001");
			return JSON_DATA;
		}
		
		CorpMaterialPO checkMaterialPO = new CorpMaterialPO();
		checkMaterialPO.setMa002(materialName);
		try {
			checkMaterialPO.setMa003(Integer.parseInt(materialDay));
		} catch (Exception e) {
			callbackDataPO = new CallbackDataPO("0", "数据类型错误", 0, null,
					null, "Material");
			return JSON_DATA;
		}
		checkMaterialPO.setMa004(corpidString);
		List<CorpMaterialPO> resultMaterialPOs;
		try {
			resultMaterialPOs = corpMaterialService.retrieveInstanceByPO(checkMaterialPO);
			if (resultMaterialPOs!=null && resultMaterialPOs.size()>0) {
				callbackDataPO = new CallbackDataPO("0", "数据重复，请重试", 0, null,
						null, "CORP_MATERIAL");
				return JSON_DATA;
			}
		} catch (Exception e1) {
			
			e1.printStackTrace();
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "CORP_MATERIAL");
			return JSON_DATA;
		}
		
		
		corpMaterialPO.setMa001(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		corpMaterialPO.setMa002(materialName);
		corpMaterialPO.setMa003(Integer.parseInt(materialDay));
		corpMaterialPO.setMa004(corpidString);
		try {
			corpMaterialService.insertInstance(corpMaterialPO);
			callbackDataPO = new CallbackDataPO("1", "新增成功", 0, null,
					null, "U001");
		} catch (Exception e) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "U001");
			e.printStackTrace();
		}
		return JSON_DATA;
	}
	
	
	public String doSaveEdit() {
		
		String ma001 = request.getParameter("materialId");
		String ma002 = request.getParameter("materialType");
		String ma003 = request.getParameter("materialTime");
		String ma004 = "";
		
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
			ma004 = po.getMa001();
		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "U001");
			return JSON_DATA;
		}
	
		CorpMaterialPO checkMaterialPO = new CorpMaterialPO();
		checkMaterialPO.setMa002(ma002);
		try {
			checkMaterialPO.setMa003(Integer.parseInt(ma003));
		} catch (Exception e) {
			callbackDataPO = new CallbackDataPO("0", "数据类型错误", 0, null,
					null, "Material");
			return JSON_DATA;
		}
		
		checkMaterialPO.setMa004(ma004);
		List<CorpMaterialPO> resultMaterialPOs;
		try {
			resultMaterialPOs = corpMaterialService.retrieveInstanceByPO(checkMaterialPO);
			if (resultMaterialPOs!=null && resultMaterialPOs.size()>0) {
				callbackDataPO = new CallbackDataPO("0", "数据重复，请重试", 0, null,
						null, "CORP_MATERIAL");
				return JSON_DATA;
			}
		} catch (Exception e1) {
			
			e1.printStackTrace();
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "CORP_MATERIAL");
			return JSON_DATA;
		}
		
		
		try {
			corpMaterialPO.setMa001(ma001);
			corpMaterialPO.setMa002(ma002);
			corpMaterialPO.setMa003(Integer.parseInt(ma003));
			corpMaterialPO.setMa004(ma004);
			corpMaterialService.updateInstance(corpMaterialPO);
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
			corpMaterialService.delBatchInstance(map1);
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
			corpMaterialPO = corpMaterialService.retrieveInstanceById(corpMaterialPO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	
	public String doStatus(){
		String ma001 = request.getParameter("MA001");
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
			CorpMaterialDetailPO changeDetailPO = new CorpMaterialDetailPO();
			changeDetailPO.setMa001(ma001);
			changeDetailPO.setMa006(0);
			changeDetailPO.setMa005(new Date());
			corpMaterialService.updateDetail(changeDetailPO);
			
			changeDetailPO = corpMaterialService.retrieveDetailById(ma001);
			//增加新纪录
			CorpMaterialDetailPO newDetailPO = new CorpMaterialDetailPO();
			newDetailPO.setMa001(UUID.randomUUID().toString().trim().replaceAll("-", ""));
			newDetailPO.setMa002(changeDetailPO.getMa002());
			newDetailPO.setMa003(changeDetailPO.getMa003());
			CorpMaterialPO materialPO = corpMaterialService.retrieveInstanceById(changeDetailPO.getMa002());
			newDetailPO.setMa004(new Date());
			
			Date date=new   Date();//取时间 
		     Calendar   calendar   =   new   GregorianCalendar(); 
		     calendar.setTime(date); 
		     calendar.add(calendar.DATE,materialPO.getMa003());//把日期往后增加一天.整数往后推,负数往前移动 
		     date=calendar.getTime();   //这个时间就是日期往后推一天的结果 


		     newDetailPO.setMa005(date);
		
			newDetailPO.setMa006(1);
			newDetailPO.setMa007(changeDetailPO.getMa007());
			try {
				corpMaterialService.insertDetail(newDetailPO);
				callbackDataPO = new CallbackDataPO("1", "新增成功", 0, null,
						null, "U001");
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
						null, "U001");
				e.printStackTrace();
			}
			
			
			
			callbackDataPO = new CallbackDataPO("1", "设置成功", 0, null,
					null, "MaterialDetail");
		
			
			
		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "MaterialDetail");
			return JSON_DATA;
		}
		
		
		
		return JSON_DATA;
	}
	
	public String doHistory(){
		String ma002 = request.getParameter("MA002");
		String ma003 = request.getParameter("MA003");
		String ma004 = request.getParameter("MA004");
		String ma005 = request.getParameter("MA005");
		
		String tel = request.getParameter("TEL");

	
		
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
			CorpMaterialDetailPO changeDetailPO = new CorpMaterialDetailPO();
			changeDetailPO.setMa002(ma002);
			changeDetailPO.setMa003(ma003);
			if (ma004 != null && ma004.length()>0) {
				changeDetailPO.setMa004(new Date(ma004));
			}
			if (ma005 != null && ma005.length()>0) {
				changeDetailPO.setMa005(new Date(ma005));
			}
		
			changeDetailPO.setMa009(tel);
			changeDetailPO.setMa007(po.getMa001());

			List<CorpMaterialDetailPO> result = corpMaterialService.retrieveDetailByPO(changeDetailPO);
			callbackDataPO = new CallbackDataPO("1", "查询数据成功",
					result.size(), null,
					JSONArray.fromObject(result), "MaterialDetail");
			
		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "MaterialDetail");
			return JSON_DATA;
		}
		
		
		
		return JSON_DATA;
	}
	public String doSearch(){
		String ma002 = request.getParameter("MA002");
		String ma003 = request.getParameter("MA003");
		String ma004 = request.getParameter("MA004");
		String ma005 = request.getParameter("MA005");
		
		String tel = request.getParameter("TEL");

	
		
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
			CorpMaterialDetailPO changeDetailPO = new CorpMaterialDetailPO();
			changeDetailPO.setMa002(ma002);
			changeDetailPO.setMa003(ma003);
			if (ma004 != null && ma004.length()>0) {
				changeDetailPO.setMa004(new Date(ma004));
			}
			if (ma005 != null && ma005.length()>0) {
				changeDetailPO.setMa005(new Date(ma005));
			}
		
			changeDetailPO.setMa009(tel);
			changeDetailPO.setMa007(po.getMa001());

			materialDetailPOList = corpMaterialService.retrieveDetailByPO(changeDetailPO);
			
			CorpMaterialPO tempPO = new CorpMaterialPO();
			tempPO.setMa004(po.getMa001());
			materialPOList = corpMaterialService.retrieveInstanceByPO(tempPO);
			
			
		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
					null, "MaterialDetail");
			return JSON_DATA;
		}
		
		
		
		return "list";
	}
	
	public ICorpMaterialService getCorpMaterialService() {
		return corpMaterialService;
	}
	public void setCorpMaterialService(ICorpMaterialService corpMaterialService) {
		this.corpMaterialService = corpMaterialService;
	}
	public ICorpBusinessService getCorpBusinessService() {
		return corpBusinessService;
	}
	public void setCorpBusinessService(ICorpBusinessService corpBusinessService) {
		this.corpBusinessService = corpBusinessService;
	}
	public CorpMaterialDetailPO getCorpMaterialDetailPO() {
		return corpMaterialDetailPO;
	}
	public void setCorpMaterialDetailPO(CorpMaterialDetailPO corpMaterialDetailPO) {
		this.corpMaterialDetailPO = corpMaterialDetailPO;
	}
	public CorpMaterialPO getCorpMaterialPO() {
		return corpMaterialPO;
	}
	public void setCorpMaterialPO(CorpMaterialPO corpMaterialPO) {
		this.corpMaterialPO = corpMaterialPO;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public List<CorpMaterialPO> getMaterialPOList() {
		return materialPOList;
	}
	public void setMaterialPOList(List<CorpMaterialPO> materialPOList) {
		this.materialPOList = materialPOList;
	}
	public List<CorpMaterialDetailPO> getMaterialDetailPOList() {
		return materialDetailPOList;
	}
	public void setMaterialDetailPOList(
			List<CorpMaterialDetailPO> materialDetailPOList) {
		this.materialDetailPOList = materialDetailPOList;
	}
	public BusinessPO getBusinessPO() {
		return businessPO;
	}
	public void setBusinessPO(BusinessPO businessPO) {
		this.businessPO = businessPO;
	}
	
	
	
}
