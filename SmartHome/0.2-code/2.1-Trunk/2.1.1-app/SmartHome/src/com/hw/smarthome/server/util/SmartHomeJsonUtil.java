package com.hw.smarthome.server.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.hw.smarthome.po.S007PO;
import com.hw.smarthome.po.ScenePO;
import com.hw.smarthome.po.SensorDetailList;
import com.hw.smarthome.po.UserPO;

/**
 * 智能家居项目专用json转换类
 * 
 * @author 曾凡
 * @time 2014年7月1日 下午4:24:59
 */
public class SmartHomeJsonUtil {
	/**
	 * 将json串转成传感器列表po
	 * 
	 * @author 曾凡
	 * @param json
	 * @return
	 * @time 2014年7月1日 下午4:26:39
	 */
	public static UserPO getUserPO(String json) {
		UserPO userPO = null;
		try {
			userPO = new Gson().fromJson(getDataObject(json),
					UserPO.class);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return userPO;
	}

	public static UserPO getUserPOFrom(String json) {
		UserPO userPO = null;
		try {
			userPO = new Gson().fromJson(json, UserPO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return userPO;
	}

	/**
	 * 获取场景列表
	 * 
	 * @author 曾凡
	 * @param json
	 * @return
	 * @time 2015年5月12日 下午3:29:56
	 */
	public static List<ScenePO> getScenes(String json) {
		List<ScenePO> scenes = null;
		String objJson = "["+getDataObject(json)+"]";
		if (objJson != null && !"".equals(objJson)) {
			scenes = new Gson().fromJson(objJson,
					new TypeToken<List<ScenePO>>() {
					}.getType());
		}
		return scenes;

	}

	/**
	 * 将json串转成传感器列表po
	 * 
	 * @author 曾凡
	 * @param json
	 * @return
	 * @time 2014年7月1日 下午4:26:39
	 */
	public static SensorDetailList getSensorList(String json) {
		SensorDetailList detailList = null;
		String objJson = getDataObject(json);
		if (objJson != null && !"".equals(objJson)) {
			detailList = new Gson().fromJson(objJson,
					SensorDetailList.class);
		}
		return detailList;
	}

	public static List<S007PO> getSolution(String json) {
		List<S007PO> array = new Gson().fromJson("["
				+ getDataObject(json) + "]",
				new TypeToken<List<S007PO>>() {
				}.getType());
		return array;
	}

	/**
	 * 获取提示信息
	 * 
	 * @author 曾凡
	 * @param json
	 * @return
	 * @time 2014年7月23日 下午12:56:23
	 */
	public static String getMessage(String json) {
		String messageStr = getAttr(json, "message");
		return messageStr.replace("\"", "");
	}

	/**
	 * 获取DataObject字段
	 * 
	 * @author 曾凡
	 * @param json
	 * @return
	 * @time 2014年7月1日 下午4:30:50
	 */
	public static String getDataObject(String json) {
		String objString = getAttr(json, "dataObject");
		objString = objString.substring(1,
				objString.length() - 1);
		return objString;
	}

	private static String getData(String json) {
		String objString = getAttr(json, "data");
		objString = objString.substring(1,
				objString.length() - 1);
		return objString;
	}

	/**
	 * 返回json属性的内容
	 * 
	 * @author 曾凡
	 * @param attr
	 * @return
	 * @time 2014年7月23日 下午12:53:11
	 */
	public static String getAttr(String json, String attr) {
		String objString = null;
		JsonElement jsonElement = new JsonParser().parse(json);
		if (jsonElement.isJsonObject()) {
			JsonObject jsonobj = (JsonObject) jsonElement;
			objString = jsonobj.get(attr) + "";
		}
		return objString;
	}
}
