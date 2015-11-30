package com.hw.hwsafe.smart.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.pojo.BusinessPO;
import com.hw.hwsafe.smart.pojo.CorpCrmPO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.ICorpBusinessService;
import com.hw.hwsafe.smart.service.ICorpOnlineService;

public class CorpOnlineAction extends BaseAction {

	@Autowired
	private ICorpOnlineService corpOnlineService;

	@Autowired
	private ICorpBusinessService corpBusinessService;

	private List<CorpCrmPO> crmPOList = new ArrayList<CorpCrmPO>();
	private List<U001PO> u001POList = new ArrayList<U001PO>();
	private BusinessPO businessPO;

	public String doQuery() {
		String date1 = request.getParameter("timeB");
		String date2 = request.getParameter("timeE");
		String deviceid = request.getParameter("device");
		String tel = request.getParameter("TEL");
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			BusinessPO po = corpBusinessService.doCorpLogin(map);
			if (po == null) {
				callbackDataPO = new CallbackDataPO("1", "不合法操作", 0, null,
						null, "U001");
				return JSON_DATA;
			}
			String corpId = po.getMa001();
			try {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("corpid", corpId);
				map2.put("deviceid", deviceid);
				map2.put("tel", tel);
				if (date1 != null && date1.length() > 0) {
					map2.put("date1", new Date(date1));
				}
				if (date2 != null && date2.length() > 0) {
					map2.put("date2", new Date(date2));
				}

				u001POList = corpOnlineService.doQuery(map2);
				callbackDataPO = new CallbackDataPO("1", "查询数据成功",
						u001POList.size(), null,
						JSONArray.fromObject(u001POList), "MaterialDetail");
			} catch (Exception e) {

				e.printStackTrace();
				callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
						null, "U001");
				return JSON_DATA;
			}

		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null, null,
					"U001");
			return JSON_DATA;
		}

		return "list";

	}
	public String getDataInPage(){
		String date1 = request.getParameter("timeB");
		String date2 = request.getParameter("timeE");
		String deviceid = request.getParameter("device");
		String tel = request.getParameter("TEL");
		
		
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
			paramMap.put("corpid", businessPO.getMa001());	
			
			paramMap.put("deviceid", deviceid);
			paramMap.put("tel", tel);
			if (date1 != null && date1.length() > 0) {
				paramMap.put("date1", new Date(date1));
			}
			if (date2 != null && date2.length() > 0) {
				paramMap.put("date2", new Date(date2));
			}

			
			pagerData.setRows(corpOnlineService.retrieveByPage(paramMap));
			paramMap.put("selectCount", true);
			
			pagerData.setRecords(corpOnlineService.retrieveByPage(paramMap));
			
		} catch (Exception e1) {
			e1.printStackTrace();

		}
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGER;
	}
	public String doList() {
		String account = request.getParameter("ACCOUNT");
		String keyString = request.getParameter("SESSIONID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ma009", account);
		map.put("ma011", keyString);
		try {
			businessPO = corpBusinessService.doCorpLogin(map);
			if (businessPO == null) {
				callbackDataPO = new CallbackDataPO("1", "不合法操作", 0, null,
						null, "U001");
				return JSON_DATA;
			}
			String corpId = businessPO.getMa001();
			try {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("corpid", corpId);
				u001POList = corpOnlineService.doQuery(map2);
			} catch (Exception e) {

				e.printStackTrace();
				callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
						null, "U001");
				return JSON_DATA;
			}

		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null, null,
					"U001");
			return JSON_DATA;
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
				if (businessPO == null) {
					callbackDataPO = new CallbackDataPO("1", "不合法操作", 0, null,
							null, "U001");
					return JSON_DATA;
				}
				String corpId = businessPO.getMa001();
				try {
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("corpid", corpId);
					u001POList = corpOnlineService.doQuery(map2);
				} catch (Exception e) {

					e.printStackTrace();
					callbackDataPO = new CallbackDataPO("0", "操作错误，请重试", 0, null,
							null, "U001");
					return JSON_DATA;
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
				
			}
			
			return "excelonline";
		}
	
	public List<CorpCrmPO> getCrmPOList() {
		return crmPOList;
	}

	public void setCrmPOList(List<CorpCrmPO> crmPOList) {
		this.crmPOList = crmPOList;
	}

	public ICorpOnlineService getCorpOnlineService() {
		return corpOnlineService;
	}

	public void setCorpOnlineService(ICorpOnlineService corpOnlineService) {
		this.corpOnlineService = corpOnlineService;
	}

	public ICorpBusinessService getCorpBusinessService() {
		return corpBusinessService;
	}

	public void setCorpBusinessService(ICorpBusinessService corpBusinessService) {
		this.corpBusinessService = corpBusinessService;
	}

	public List<U001PO> getU001POList() {
		return u001POList;
	}

	public void setU001POList(List<U001PO> u001poList) {
		u001POList = u001poList;
	}

	public BusinessPO getBusinessPO() {
		return businessPO;
	}

	public void setBusinessPO(BusinessPO businessPO) {
		this.businessPO = businessPO;
	}

}
