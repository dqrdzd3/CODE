package com.hw.hwsafe.smart.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.pojo.A001PO;
import com.hw.hwsafe.smart.service.IA001Service;
import com.hw.hwsafe.smart.service.ID002Service;
import com.hw.hwsafe.smart.util.SmartCheckUser;
import com.hw.hwsafe.utils.UUIDGenerater;

public class A001Action extends BaseAction {
	
	@Autowired
	private IA001Service a001Service;
	@Autowired
	private ID002Service d002Service;
	private A001PO a001PO;
	
	private String ids;
	
	public String doAdd() {
		return "add";
	}

	public String doSaveAdd() {
		a001PO.setMa001(UUIDGenerater.getUUID());
		try {
			a001Service.insertInstance(a001PO);
			setAddSuccessMsg();
		} catch (Exception e) {
			setAddFailedMsg();
			e.printStackTrace();
		}
		return JSON_MSG;
	}

	public String doEdit() {
		try {
			a001PO = a001Service.retrieveInstanceById(a001PO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}

	public String doSaveEdit() {
		try {
			a001Service.updateInstance(a001PO);
			setUpdateSuccessMsg();
		} catch (Exception e) {
			setUpdateFailedMsg();
			e.printStackTrace();
		}
		return JSON_MSG;
	}

	public String doDelete() {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("idArray", ids.split(","));
			a001Service.delBatchInstance(map);
			setDelSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	
	public String doShow() {
		try {
			a001PO = a001Service.retrieveInstanceById(a001PO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	
	/**
	 * 接口：发送历史报警信息
	 */
	public String doHisAlarmList(){
		//1：根据用户ID和sessionID判断用户身份信息
		String userID=request.getParameter("USERID");
		String sessionID = request.getParameter("SESSIONID");
		String deviceID = request.getParameter("DRIVERID");
		String strPageNO  = request.getParameter("PAGENO");
		String strPageSize = request.getParameter("PAGESIZE");
        
		Map<String, Object> vertify = new SmartCheckUser().vertify(sessionID);
		callbackDataPO=(CallbackDataPO) vertify.get("callbackDataPO");
		if ((Boolean) vertify.get("isVertify")) {//验证通过
			List<HashMap<String, Object>> hisAlarmList = null;
			if (userID!=null&&deviceID!=null&&strPageNO!=null&&strPageSize!=null) {
				try {
					Map<String, Object>  map=new HashMap<String, Object>();
					int pageNO=Integer.valueOf(strPageNO);		 
					int pageSize=Integer.valueOf(strPageSize);		 
			        int start=(pageNO-1) *pageSize;
			        int end= pageNO * pageSize;
					map.put("start",start);
					map.put("end",end);
					map.put("ma007","10");
					map.put("ma006",deviceID);
					hisAlarmList=a001Service.getHisAlarmList(map);//2：调用service,查询数据库表A001表，根据设备ID查询得到该设备所有标识字段MA007为10的报警信息列表
					callbackDataPO = new CallbackDataPO("1", "操作成功", hisAlarmList.size(),null,JSONArray.fromObject(hisAlarmList), "a001");
				} catch (Exception e) {
					e.printStackTrace();
					callbackDataPO = new CallbackDataPO("0", "操作失败,发生异常", 0,null,null, "a001");
				}
			}
			else {
				callbackDataPO = new CallbackDataPO("0", "操作失败", 0,null ,null, "a001");
			}
		}
		return JSON_DATA ;
	}
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public IA001Service getA001Service() {
		return a001Service;
	}

	public void setA001Service(IA001Service a001Service) {
		this.a001Service = a001Service;
	}

	public A001PO getA001PO() {
		return a001PO;
	}

	public void setA001PO(A001PO a001po) {
		a001PO = a001po;
	}
	public ID002Service getD002Service() {
		return d002Service;
	}

	public void setD002Service(ID002Service d002Service) {
		this.d002Service = d002Service;
	}

}
