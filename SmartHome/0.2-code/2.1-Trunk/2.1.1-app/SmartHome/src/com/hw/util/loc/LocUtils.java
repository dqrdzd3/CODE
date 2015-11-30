package com.hw.util.loc;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author 曾凡
 * @time 2015年3月23日 下午7:05:27
 */
public class LocUtils {
	public static LocPo parserLocPo(String json)
			throws JSONException {
		LocPo po = new LocPo();
		JSONObject jsonObject = new JSONObject(json);
		jsonObject = jsonObject.getJSONObject("result");
		if (jsonObject.has("location")) {
			po.setLat(jsonObject.getJSONObject("location")
					.getString("lat"));
			po.setLng(jsonObject.getJSONObject("location")
					.getString("lng"));

			po.setLoc(jsonObject.getString("formatted_address"));
		}
		String city = "";
		if (jsonObject.has("addressComponent")) {
			po.setProvince(jsonObject.getJSONObject(
					"addressComponent").getString("province"));
			city = jsonObject.getJSONObject("addressComponent")
					.getString("city");
			if (city.endsWith("市")) {
				city = city.substring(0, city.length() - 1);
			}
			po.setCity(city);
			po.setArea(jsonObject.getJSONObject(
					"addressComponent").getString("district"));
		}
		return po;
	}

	public static LocPo parserIpLocPo(String json)
			throws JSONException {
		LocPo po = new LocPo();
		JSONObject jsonObject = new JSONObject(json);
		JSONObject content = jsonObject.getJSONObject("content");
		String city = "";
		if (content.has("address_detail")) {
			po.setProvince(content.getJSONObject(
					"address_detail").getString("province"));
			city = content.getJSONObject("address_detail")
					.getString("city");
			if (city.endsWith("市")) {
				city = city.substring(0, city.length() - 1);
			}
			po.setCity(city);
		}

		if (content.has("point")) {
			po.setLat(content.getJSONObject("point").getString(
					"x"));
			po.setLng(content.getJSONObject("point").getString(
					"y"));
		}
		return po;
	}
}
