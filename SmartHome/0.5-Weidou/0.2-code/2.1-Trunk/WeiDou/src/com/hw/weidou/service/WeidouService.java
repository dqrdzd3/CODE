package com.hw.weidou.service;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.os.Handler;

import com.hw.weidou.po.ParamMapPO;
import com.hw.weidou.server.ServerResReq;
import com.hw.weidou.server.constant.ServerConstant;
import com.hw.weidou.service.base.WeidouServiceBase;

/**
 * @author 曾凡
 * @time 2014年6月26日 下午3:47:12
 */
public class WeidouService extends WeidouServiceBase {
	private Handler toastHandler = null;

	public static void setUser(String id1, String id2) {
		userId = id1;
		sessionId = id2;
	}

	public static String[] getUser() {
		return new String[] { userId, sessionId };
	}

	/**
	 * [SH01_03_01_01] 查看用户信息
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:31:48
	 */
	@Override
	public void executeSH01_03_01_01() {
		StringBuilder sb = new StringBuilder(
				getUsrSesStr("d002!doEditListEquip"));
		sb.append("&USERID=").append(userId);
		sb.append("&SESSIONID=").append(sessionId);
		ServerResReq.requestServer("doListUser",
				ServerConstant.SH01_03_01_01);
	}

	/**
	 * [SH01_03_02_02] 账号注册
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:31:48
	 */
	@Override
	public void executeSH01_03_02_01(
			Map<String, String> paramsMap) {

		ServerResReq.requestServer(paramsMap,
				"u001!doCreateAccount",
				ServerConstant.SH01_03_02_01);

	}

	/**
	 * [SH01_03_02_02] 查找密码
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:31:48
	 */
	@Override
	public void executeSH01_03_02_02(
			Map<String, String> paramsMap) {
		// TODO Auto-generated method stub
		if (userId != null && sessionId != null) {
			// 请求服务端

			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			ServerResReq.requestServer(paramsMap,
					"u001!doResetPw",
					ServerConstant.SH01_03_02_02);
		}
	}

	/**
	 * [SH01_03_02_03] 修改密码
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:31:48
	 */
	@Override
	public void executeSH01_03_02_03(
			Map<String, String> paramsMap) {
		// TODO Auto-generated method stub
		if (userId != null && sessionId != null) {

			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			ServerResReq.requestServerGet(paramsMap,
					"u001!doEditPw",
					ServerConstant.SH01_03_02_03);
		}
	}

	@Override
	public void executeSH01_05_01_02() {
		// TODO Auto-generated method stub
		if (userId != null && sessionId != null) {
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			ServerResReq.requestServer(paramsMap, "s004!doView",
					ServerConstant.SH01_05_01_01_01);
		}
	}

	@Override
	public void executeSH01_05_01_03() {
		// TODO Auto-generated method stub
		if (userId != null && sessionId != null) {
			/* FIXME [SH01_05_01_02]查看留言板的接口参数名 */
			StringBuilder sb = new StringBuilder(
					getUsrSesStr("doListDetailHistory"));
			ServerResReq.requestServer(sb.toString(),
					ServerConstant.SH01_05_01_01_02);
		}
	}

	/**
	 * 数据请求参数处理总方法
	 */
	@Override
	public void execute(Bundle bundle) {
		// TODO Auto-generated method stub
		if (userId != null && sessionId != null) {
			ParamMapPO po = (ParamMapPO) bundle.get("param");
			Map<String, String> paramsMap = po.getParamMap();
			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			String name = bundle.getString("name");
			int type = bundle.getInt("type");
			ServerResReq.requestServer(paramsMap, name, name,
					type);
		}
	}

	/**
	 * 编辑用户信息
	 */
	@Override
	public void executeSH01_03_01_02(
			Map<String, String> paramsMap) {
		// TODO Auto-generated method stub
		if (userId != null && sessionId != null) {

			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			ServerResReq.requestServer(paramsMap,
					"u001!doEditInfo",
					ServerConstant.SH01_03_01_02);
		}
	}

	/**
	 * 登录
	 */
	@Override
	public void executeSH01_03_02_04(
			Map<String, String> paramsMap) {
		// TODO Auto-generated method stub
		ServerResReq.requestServerGet(paramsMap, "u001!doLogin",
				ServerConstant.SH01_03_02_04);
	}

}
