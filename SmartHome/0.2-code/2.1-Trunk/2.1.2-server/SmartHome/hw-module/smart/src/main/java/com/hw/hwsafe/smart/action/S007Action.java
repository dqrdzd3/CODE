package com.hw.hwsafe.smart.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.constants.SmartConstants;
import com.hw.hwsafe.smart.pojo.S007PO;
import com.hw.hwsafe.smart.pojo.SolutionPO;
import com.hw.hwsafe.smart.service.IS007Service;
import com.hw.hwsafe.utils.UUIDGenerater;

public class S007Action extends BaseAction {

	private S007PO s007po;
	private String ids;
	private List<S007PO> resultList = new ArrayList<S007PO>();
	@Autowired
	private IS007Service s007Service;

	public String doIndex() {

		return SUCCESS;
	}

	public String doAdd() {
		return "add";
	}

	public String doSaveAdd() {
		try {
			s007po.setMa001(UUIDGenerater.getUUID());
			s007po.setMa006(1);
			s007po.setMa007(0);
			s007Service.insertInstance(s007po);
			setAddSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}

	public String doEdit() {
		try {
			s007po = s007Service.retrieveInstanceById(s007po.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}

	public String doSaveEdit() {
		try {
			s007po.setMa006(1);
			s007po.setMa007(0);
			s007Service.updateInstance(s007po);

			setUpdateSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}

	public String doDelete() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idArray", ids.split(","));
			s007Service.delBatchS007(map);
			setDelSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}

	/**
	 * 
	 * 点击查看详细
	 * 
	 * @return
	 * @author liyuhao
	 * @create_time 2014年7月23日下午6:51:41
	 */
	public String doShow() {
		try {
			s007po = s007Service.retrieveInstanceById(s007po.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}

	public String getSolution() {
		
		
		
		
		return "solution";
	}

	public String getAllSolution() {
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");

		String message = null;
		if (StringUtils.isBlank(sessionId)) {
			message = SmartConstants.SESSION_ID_ISNULL;
		} else if (StringUtils.isBlank(userId)) {
			message = SmartConstants.USER_ID_ISNULL;
		}
		if (message != null) {
			callbackDataPO = new CallbackDataPO("0", message, 0, null, "s007");
		} else {
			try {
				List<S007PO> solutionList = new LinkedList<S007PO>();
				solutionList = s007Service.retrieveAllInstance();
				callbackDataPO = new CallbackDataPO("1", "查询数据成功",
						solutionList.size(), null,
						JSONArray.fromObject(solutionList), "s007");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return JSON_DATA;
	}

	//新的解决方案
	public String getSolutionsByCondition(){
		String MA003 = request.getParameter("FILETYPE");
		String MA004 = request.getParameter("CO2");
		String MA005 = request.getParameter("VOC");
		String MA006 = request.getParameter("PM25");
		String MA008 = request.getParameter("SCENE");


		String message = null;
		/*if (StringUtils.isBlank(sessionId)) {
			message = SmartConstants.SESSION_ID_ISNULL;
		} else if (StringUtils.isBlank(userId)) {
			message = SmartConstants.USER_ID_ISNULL;
		}*/
		if (StringUtils.isBlank(MA003)) {
			message = "文件类型参数为空";
		} else if (StringUtils.isBlank(MA004)) {
			message = "二氧化碳值为空";
		}
		if (StringUtils.isBlank(MA005)) {
			message = "VOC值为空";
		} else if (StringUtils.isBlank(MA006)) {
			message = "PM2.5值为空";
		}
		if (StringUtils.isBlank(MA008)) {
			message = "场景为空";
		} 
	if (message != null) {
			callbackDataPO = new CallbackDataPO("0", message, 0, null, "s007");
		} else {
			try {
				List<SolutionPO> solutionList = new LinkedList<SolutionPO>();
				SolutionPO po = new SolutionPO();
				po.setMa003(Integer.parseInt(MA003));
				po.setMa004(Integer.parseInt(MA004));
				po.setMa005(Integer.parseInt(MA005));
				po.setMa006(Integer.parseInt(MA006));
				po.setMa008(Integer.parseInt(MA008));
		
				
				solutionList = s007Service.retrieveAllSolution(po);
				 String content = "";
				 String report = "";
				if (solutionList.size()>0) {
					content = new String(solutionList.get(0).getMa009(),"utf-8"); 
					report = Integer.toString(solutionList.get(0).getMa007());
				}
				
				callbackDataPO = new CallbackDataPO("1", "查询数据成功",
						solutionList.size(), report,
						content, "s007");
			} catch (Exception e) {
				e.printStackTrace();
				callbackDataPO = new CallbackDataPO("0", "查询数据失败",
						0, null,
						null, "s007");
			}
		}
		return JSON_DATA;
	}
	
	
	public String getGasName(int n) {
		String[] gasNameStrings = { "温度", "湿度", "二氧化碳", "PM2.5", "voc", "C6H6",
				"甲醛", "酒精", "一氧化碳", "甲烷" };
		return gasNameStrings[n];
	}

	public String getGasState(int n) {
		String[] gasStateStrings = { "低", "正常", "高" };
		return gasStateStrings[n];
	}

	public IS007Service getS007Service() {
		return s007Service;
	}

	public void setS007Service(IS007Service s007Service) {
		this.s007Service = s007Service;
	}

	public S007PO getS007po() {
		return s007po;
	}

	public void setS007po(S007PO s007po) {
		this.s007po = s007po;
	}

	public List<S007PO> getResultList() {
		return resultList;
	}

	public void setResultList(List<S007PO> resultList) {
		this.resultList = resultList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
