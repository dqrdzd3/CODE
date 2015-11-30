package com.hw.hwsafe.smart.action;

import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.pojo.W001PO;
import com.hw.hwsafe.smart.service.IW001Service;
import com.hw.hwsafe.utils.UUIDGenerater;

public class W001Action extends BaseAction {

	@Autowired
	private IW001Service w001Service;
	
	private W001PO w001PO;

	public void doCreateInfo(){
		try {
			w001PO = new W001PO();
			w001PO.setMa001(UUIDGenerater.getUUID());
			w001PO.setMa002(request.getParameter("MA002"));
			w001PO.setMa003(request.getParameter("MA003"));
			w001PO.setMa004(request.getParameter("MA004"));
			w001PO.setMa005(request.getParameter("MA005"));
			w001PO.setMa006(request.getParameter("MA006"));
			w001PO.setMa007(request.getParameter("MA007"));
			w001PO.setMa008(request.getParameter("MA008"));
			w001PO.setMa009(request.getParameter("MA009"));
			w001PO.setMa010(request.getParameter("MA010"));
			w001PO.setMa011(request.getParameter("MA011"));
			w001PO.setMa012(request.getParameter("MA012"));
			w001PO.setMa013(request.getParameter("MA013"));
			w001PO.setMa014(new Date());
			w001PO.setMa015(request.getParameter("MA015"));
			w001PO.setMa016(request.getParameter("MA016"));
			w001Service.insertInstance(w001PO);
			//callbackDataPO = new CallbackDataPO("1", "用户注册成功",1,new Gson().toJson(w001PO) ,JSONArray.fromObject(w001PO) , "W001");
			callbackDataPO = new CallbackDataPO("1", "登录成功",1,new Gson().toJson(w001PO),JSONObject.fromObject(w001PO), "W001");
		} catch (Exception e) {
			
			e.printStackTrace();
			callbackDataPO = new CallbackDataPO("0", "输入用户名和密码错误",0,null ,null , "W001");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public W001PO getW001PO() {
		return w001PO;
	}

	public void setW001PO(W001PO w001po) {
		w001PO = w001po;
	}
	
}
