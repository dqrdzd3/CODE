package com.hw.hwsafe.smart.weixin.advanced;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.hw.hwsafe.smart.weixin.main.WeixinConstant;

public class AuthedWebpage {

	// 获取code Scope为snsapi_base
	public final static String GET_CODE_BASE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=PARAM_APPID&redirect_uri=PARAM_URL&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	// 获取code Scope为snsapi_userinfo
	public final static String GET_CODE_USERINFO = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=PARAM_APPID&redirect_uri=PARAM_URL&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

	public static String getBaseUrl(String url) {
		String requestUrl = GET_CODE_BASE.replace("PARAM_APPID",
				WeixinConstant.appId);
		requestUrl = requestUrl.replace("PARAM_URL", URLEncoder.encode(url));
		return requestUrl;
	}

	public static String getUserInfoUrl(String url) {
		String requestUrl = GET_CODE_BASE.replace("PARAM_APPID",
				WeixinConstant.appId);
		requestUrl = requestUrl.replace("PARAM_URL", URLEncoder.encode(url));
		requestUrl = requestUrl.replace("STATE", "3ef");
		return requestUrl;
	}

	// 获取access_token
	public final static String GET_TOKEN_BY_CODE = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	public static Map<String, Object> getTokenByCode(String code) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String requestUrl = GET_TOKEN_BY_CODE
				.replace("APPID", WeixinConstant.appId)
				.replace("SECRET", WeixinConstant.appSecret)
				.replace("CODE", code);

		JSONObject jsonObject = AuthedWeixin.httpRequest(requestUrl, "GET", "");
		if (null != jsonObject) {
			try {
				if (jsonObject.has("access_token")) {
					resultMap.put("access_token",
							jsonObject.getString("access_token"));
					resultMap
							.put("expires_in", jsonObject.getInt("expires_in"));
					resultMap.put("refresh_token",
							jsonObject.getString("refresh_token"));
					resultMap.put("openid", jsonObject.getString("openid"));
					resultMap.put("scope", jsonObject.getString("scope"));
					resultMap.put("unionid", jsonObject.getString("unionid"));

					return resultMap;
				} else
					return null;

			} catch (JSONException e) {
				// 获取token失败

				return null;

			}
		}
		return null;
	}

	// 刷新access_token
	public final static String REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

	public static Map<String, Object> refreshToken(String refreshToken) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String requestUrl = REFRESH_TOKEN
				.replace("APPID", WeixinConstant.appId).replace(
						"REFRESH_TOKEN", refreshToken);

		JSONObject jsonObject = AuthedWeixin.httpRequest(requestUrl, "GET", "");
		if (null != jsonObject) {
			try {
				if (jsonObject.has("access_token")) {
					resultMap.put("access_token",
							jsonObject.getString("access_token"));
					resultMap
							.put("expires_in", jsonObject.getInt("expires_in"));
					resultMap.put("refresh_token",
							jsonObject.getString("refresh_token"));
					resultMap.put("openid", jsonObject.getString("openid"));
					resultMap.put("scope", jsonObject.getString("scope"));

					return resultMap;
				} else
					return null;

			} catch (JSONException e) {
				// 获取token失败

				return null;

			}
		}
		return null;
	}

	// 拉取用户信息(需scope为 snsapi_userinfo)
	
	//检验授权凭证（access_token）是否有效
	public final static String CHECK_TOKEN = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
	public static boolean checkToken(String token,String openid) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String requestUrl = CHECK_TOKEN
				.replace("ACCESS_TOKEN", token).replace(
						"OPENID", openid);

		JSONObject jsonObject = AuthedWeixin.httpRequest(requestUrl, "GET", "");
		if (null != jsonObject) {
			try {
				if (jsonObject.has("errcode") && jsonObject.getInt("errcode")==0) {
					return true;
				} else
					return false;

			} catch (JSONException e) {
				// 获取token失败

				return false;

			}
		}
		return false;
	}

}
