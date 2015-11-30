package com.hw.smarthome.server.deal;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.hw.smarthome.po.UserPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.util.SmartHomeJsonUtil;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.Ln;
import com.hw.util.PreferenceUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author 闫威
 * @time 2014年6月26日 下午4:46:35
 */
public class DealWithAccount {
	public static final String SAVE_FILE_NAME = "ACCOUNT";
	public static final String SAVE_NAME_PASS = "ACCOUNTANDPASS";
	public static final String SAVE_PIC_PATH = "MYPIC";
	
	/**
	
		 *登陆页保存账号和密码
		 * 
		 * @author 曾凡
		 * @param context
		 * @param json
		 * @time 2014年6月26日 下午4:45:53
		 */
	
	public static void saveAccountAndPwd(Context context,String name,String pass) {
		Editor userData = context.getSharedPreferences(
				SAVE_NAME_PASS, 0).edit();
		userData.putString("name", name);
		userData.putString("pass", pass);
	
		userData.commit();
	}
	/**
	
	 *保存个人头像路径
	 * 
	 * @author 闫威
	 * @param context
	 * @param json
	 * @time 2014年6月26日 下午4:45:53
	 */

public static void saveMypic(Context context,String path) {
	Editor userData = context.getSharedPreferences(
			SAVE_PIC_PATH, 0).edit();
	userData.putString("path", path);
	

	userData.commit();
}
	/**
	
	 *获取保存账号和密码
	 * 
	 * @author 曾凡
	 * @param context
	 * @param json
	 * @time 2014年6月26日 下午4:45:53
	 */
	public static String[] getAccountAndPwd(Context context) {
		
		SharedPreferences userData = context
				.getSharedPreferences(SAVE_NAME_PASS, 0);
		String name = userData.getString("name", "");
		String pass = userData.getString("pass", "");
		return new String[]{name,pass};
	}
/**
	
	 *获取个人头像路径
	 * 
	 * @author 闫威
	 * @param context
	 * @param json
	 * @time 2014年6月26日 下午4:45:53
	 */
	public static String getMypic(Context context) {
		
		SharedPreferences userData = context
				.getSharedPreferences(SAVE_PIC_PATH, 0);
		String name = userData.getString("path", "");
		
		return name;
	}
	/**
	
	 *清除账号和密码
	 * 
	 * @author 曾凡
	 * @param context
	 * @param json
	 * @time 2014年6月26日 下午4:45:53
	 */
	public static void clearAccount(Context context){
		Editor userData = context.getSharedPreferences(
				SAVE_NAME_PASS, 0).edit();
		userData.putString("name", "");
		userData.putString("pass", "");
		userData.commit();
	}
	/**
	 * 处理[SH01_03_01_02]更改账号
	 * 
	 * @author 曾凡
	 * @param context
	 * @param json
	 * @time 2014年6月26日 下午4:45:53
	 */
	public static void updateUser(Context context,String json) {
		boolean result = false;
if (json!=null && !json.equals("null")) {
			
			try {
				JSONObject jsonObject = new JSONObject(json);
				String code = jsonObject.getString("code");
				int count = jsonObject.getInt("count");

				if (code.equals("1") && count == 1){
					
					/* 1.解析json */
					
					String userString = jsonObject.getString("data");
					UserPO userPO = SmartHomeJsonUtil
							.getUserPOFrom(userString);
					if (userPO != null) {
						/* 2.将结果持久化 */
						saveUser(context, userPO);
						result = true;
					}
				}else if (code.equals("0")) {
					result = false;
				}
					
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = false;
			}finally{
				/* 3.发送处理结果广播，页面随之更新 */
				MainAcUtil.send2Activity(context,
						ServerConstant.SH01_03_01_02, 0, result);
			}
			
			
		}
		
		
	}
	/**

	 * 处理[SH01_01_01_01]登录
	 * 
	 * @author 闫威
	 * @param context
	 * @param json
	 * @time 2014年6月26日 下午4:45:53
	 */
	public static void doLoginAccount(Context context,String json) {
		boolean result = false;
		if (json!=null && !json.equals("null")) {
			
			try {
				JSONObject jsonObject = new JSONObject(json);
				String code = jsonObject.getString("code");
				int count = jsonObject.getInt("count");

				if (code.equals("1") && count == 1){
					
					/* 1.解析json */
					
					String userString = jsonObject.getString("data");
					UserPO userPO = SmartHomeJsonUtil
							.getUserPOFrom(userString);
					if (userPO != null) {
						/* 2.将结果持久化 */
						saveUser(context, userPO);
						result = true;
					}
				}else if (code.equals("0")) {
					result = false;
				}
					
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = false;
			}finally{
				/* 3.发送处理结果广播，页面随之更新 */
				MainAcUtil.send2Activity(context,
						ServerConstant.SH01_03_02_04, 0, result);
			}
			
			
		}
	}
	/**
	 * 处理[SH01_03_01_01] 查看用户信息
	 * @author 曾凡
	 * @param mContext
	 * @param json
	 * @time 2014年7月14日 上午10:06:57
	 */
	public static void doViewAccount(MainActivity mContext,
			String json) {
		//FIXME 处理[SH01_03_01_01] 查看用户信息
	}
	/**

	 * 处理[SH01_03_02_01]注册账号
	 * 
	 * @author 闫威
	 * @param context
	 * @param json
	 * @time 2014年6月26日 下午4:45:53
	 */
	public static void doRegAccount(Context context,String json) {
		boolean result = false;
		if (json!=null && !json.equals("null")) {
			
			try {
				JSONObject jsonObject = new JSONObject(json);
				String code = jsonObject.getString("code");
				int count = jsonObject.getInt("count");

				if (code.equals("1") && count == 1){
					
					/* 1.解析json */
					
					String userString = jsonObject.getString("data");
					UserPO userPO = SmartHomeJsonUtil
							.getUserPOFrom(userString);
					if (userPO != null) {
						/* 2.将结果持久化 */
						saveUser(context, userPO);
						result = true;
					}
				}else if (code.equals("0")) {
					result = false;
				}
					
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = false;
			}finally{
				/* 3.发送处理结果广播，页面随之更新 */
				MainAcUtil.send2Activity(context,
						ServerConstant.SH01_03_02_01, 0, result);
			}
			
			
		}
	}
	/**
	 * 处理[SH01_03_02_02]忘记密码
	 * 
	 * @author 闫威
	 * @param context
	 * @param json
	 * @time 2014年6月26日 下午4:45:53
	 */
	public static void doResetPw(Context context,String json) {
		boolean result = false;
		/* 1.解析json */
		UserPO userPO = SmartHomeJsonUtil
				.getUserPO(json);
		if (userPO != null) {
			/* 2.将结果持久化 */
			saveUser(context, userPO);
			SmartHomeService.initAccount(userPO.getMa001(), userPO.getMa010());
			result = true;
		}
		/* 3.发送处理结果广播，页面随之更新 */
		MainAcUtil.send2Activity(context,
				ServerConstant.SH01_03_02_02, 0, result);
	}
	/**
	 * 处理[SH01_03_02_03]修改密码
	 * 
	 * @author 闫威
	 * @param context
	 * @param json
	 * @throws JSONException 
	 * @time 2014年6月26日 下午4:45:53
	 */
	public static void doEditPw(Context context,String json) throws JSONException {
		boolean result = false;
		/* 1.解析json */
		JSONObject jsonObject = new JSONObject(json);
		String userString = jsonObject.getString("data");
		UserPO userPO = SmartHomeJsonUtil.getUserPOFrom(userString);
	
		if (userPO != null) {
			/* 2.将结果持久化 */
			saveUser(context, userPO);
			SmartHomeService.setUser(userPO.getMa001(),userPO.getMa010());
			result = true;
		}
		/* 3.发送处理结果广播，页面随之更新 */
		MainAcUtil.send2Activity(context,
				ServerConstant.SH01_03_02_03, 0, result);
	}
	/**
	 * 保存[用户信息]
	 * 
	 * @author 闫威
	 * @param context
	 * @param userpo
	 * @time 2014年7月1日 下午5:18:13
	 */
	public static void saveUser(Context context,UserPO userpo){
		String saveByteStr = null;
		try {
			saveByteStr = PreferenceUtil.obj2String(userpo);
			Editor userData = context.getSharedPreferences(
					SAVE_FILE_NAME, 0).edit();
			userData.putString("user", saveByteStr);
			userData.commit();
		} catch (IOException e) {
			Ln.e("存储" + userpo + "异常！", e);
			e.printStackTrace();
		}
	
	}
	public static UserPO getUser(Context context) {
		SharedPreferences userData = context
				.getSharedPreferences(SAVE_FILE_NAME, 0);
		String saveStr = userData.getString("user", "");
		UserPO userPO = null;
		if (saveStr != null && !"".equals(saveStr)) {
			try {
				userPO = (UserPO) PreferenceUtil
						.String2Object(saveStr);
			} catch (Exception e) {
				Ln.e("获取" + saveStr + "异常！", e);
				e.printStackTrace();
			}
		}
		return userPO;

	}
	

}
