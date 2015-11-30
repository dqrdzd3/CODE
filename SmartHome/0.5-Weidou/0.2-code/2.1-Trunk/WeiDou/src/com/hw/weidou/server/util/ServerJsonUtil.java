package com.hw.weidou.server.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hw.util.Ln;

/**
 * 智能家居项目专用json转换类
 * 
 * @author 曾凡
 * @time 2014年7月1日 下午4:24:59
 */
public class ServerJsonUtil {

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
	private static String getDataObject(String json) {
		String objString = getAttr(json, "dataObject");
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
