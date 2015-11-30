package com.hw.hwsafe.smart.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.plot.UtilPlot;
import com.hw.hwsafe.smart.pojo.D005PO;
import com.hw.hwsafe.smart.service.ID005Service;
import com.hw.hwsafe.utils.UUIDGenerater;

public class D005Action extends BaseAction {
	
	@Autowired
	private ID005Service d005Service;
	
	private List<HashMap<String, Object>> mapList;
	
	private D005PO d005PO;
	
	private String ids;
	
	
	public String doSaveAdd(){
		d005PO = new D005PO();
		d005PO.setMa001(UUIDGenerater.getUUID());
		d005PO.setMa002(request.getParameter("ma002"));
		d005PO.setMa003(request.getParameter("ma003"));
		d005PO.setMa004(request.getParameter("ma004"));
		d005PO.setMa005(request.getParameter("ma005"));
		d005PO.setMa006(request.getParameter("ma006"));
		d005PO.setMa007(request.getParameter("ma007"));
		d005PO.setMa008(request.getParameter("ma008"));
		d005PO.setMa009(request.getParameter("ma009"));
		d005PO.setMa010(request.getParameter("ma010"));
		d005PO.setMa011(request.getParameter("ma011"));
		try {
			d005Service.insertInstance(d005PO);
			callbackDataPO = new CallbackDataPO("1","新增日志成功！",1,null,null,"D005");
		} catch (Exception e) {
			e.printStackTrace();
			callbackDataPO = new CallbackDataPO("0","新增日志失败！",0,null,null,"D005");
		}
		return JSON_DATA;
	}
	
	
	public String doShow() {
		try {
			d005PO = d005Service.retrieveInstanceById(d005PO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	
	
	public String doDelete() {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("idArray", ids.split(","));
			d005Service.delBatchD005(map);
			setDelSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	
	
	public String queryData1() throws Exception {
		pagerData = initPagerData();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pagerData", pagerData);
		paramMap.put("po", d005PO);
		if (!"true".equals(request.getParameter("qc"))) {
			pagerData.setRows(d005Service.retrieveByPage1(paramMap));
		}
		paramMap.put("selectCount", true);
		pagerData.setRecords(d005Service.retrieveByPage1(paramMap));
		return JSON_PAGER;
	}
	
	public String doGroupByMA002() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		mapList = d005Service.groupByMA002(map);
		setJstr(UtilPlot.setColPieData(mapList));
		return JSON_STR;
	}
	
	public String doGroupByMA003() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		mapList =  d005Service.groupByMA003(map);
		setJstr(UtilPlot.setColPieData(mapList));
		return JSON_STR;
	}
	
	public String doGroupByMA007() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		mapList =  d005Service.groupByMA007(map);
		setJstr(UtilPlot.setColPieData(mapList));
		return JSON_STR;
	}
	
	public String doGetGroup(){
		return "group";
	}
	
	public String doGetGroup1(){
		return "group1";
	}
	


	public ID005Service getD005Service() {
		return d005Service;
	}

	public void setD005Service(ID005Service d005Service) {
		this.d005Service = d005Service;
	}

	public D005PO getD005PO() {
		return d005PO;
	}

	public void setD005PO(D005PO d005po) {
		d005PO = d005po;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	

}
