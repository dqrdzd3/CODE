package com.hw.hwsafe.smart.weixin.advanced;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.mail.iap.Response;

public class Authed2WeiMa {
	private static Logger log = LoggerFactory.getLogger(Authed2WeiMa.class);
	// 创建二维码Ticket
	public final static String POST_EWM = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";

	// 创建二维码Ticket
	public final static String POST_GET_EWM = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
	
	// 长连接变短连接
	public final static String COMPRESS_LINK = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";

	// 获得临时TICKET expire <1800 scene_id 为32位非0整型
	public static Map<String, Object> GetTemporaryTicket(String tocken,
			int expire, String scene_id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();

		String requestUrl = POST_EWM.replace("TOKEN", tocken);
		paramMap.put("expire_seconds", expire);
		paramMap.put("action_name", "QR_SCENE");

		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("scene",
				new HashMap<String, String>().put("scene_id", scene_id));
		paramMap.put("action_info", tempMap);

		String jsonParam = JSONObject.fromObject(paramMap).toString();
		JSONObject jsonObject = AuthedWeixin.httpRequest(requestUrl, "POST",
				jsonParam);
		if (null != jsonObject) {
			try {
				if (jsonObject.has("ticket")) {
					resultMap.put("ticket", jsonObject.getString("ticket"));
					resultMap.put("expire_seconds",
							jsonObject.getString("expire_seconds"));
					resultMap.put("url", jsonObject.getString("url"));

					return resultMap;
				} else
					return null;

			} catch (JSONException e) {
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
				return null;

			}
		}
		return null;
	}

	// 获得永久TICKET
	public static Map<String, Object> GetPermenentTicket(String tocken,
			String scene_id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();

		String requestUrl = POST_EWM.replace("TOKEN", tocken);

		paramMap.put("action_name", "QR_LIMIT_SCENE");

		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("scene",
				new HashMap<String, String>().put("scene_id", scene_id));
		paramMap.put("action_info", tempMap);

		String jsonParam = JSONObject.fromObject(paramMap).toString();
		JSONObject jsonObject = AuthedWeixin.httpRequest(requestUrl, "POST",
				jsonParam);
		if (null != jsonObject) {
			try {
				if (jsonObject.has("ticket")) {
					resultMap.put("ticket", jsonObject.getString("ticket"));
					resultMap.put("expire_seconds",
							jsonObject.getString("expire_seconds"));
					resultMap.put("url", jsonObject.getString("url"));

					return resultMap;
				} else
					return null;

			} catch (JSONException e) {
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
				return null;

			}
		}
		return null;
	}

	// 获得永久字符串TICKET
	public static Map<String, Object> GetPermenentStrTicket(String tocken,
			String scene_id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();

		String requestUrl = POST_EWM.replace("TOKEN", tocken);

		paramMap.put("action_name", "QR_LIMIT_STR_SCENE");

		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("scene",
				new HashMap<String, String>().put("scene_str", scene_id));
		paramMap.put("action_info", tempMap);

		String jsonParam = JSONObject.fromObject(paramMap).toString();
		JSONObject jsonObject = AuthedWeixin.httpRequest(requestUrl, "POST",
				jsonParam);
		if (null != jsonObject) {
			try {
				if (jsonObject.has("ticket")) {
					resultMap.put("ticket", jsonObject.getString("ticket"));
					resultMap.put("expire_seconds",
							jsonObject.getString("expire_seconds"));
					resultMap.put("url", jsonObject.getString("url"));

					return resultMap;
				} else
					return null;

			} catch (JSONException e) {
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
				return null;

			}
		}
		return null;
	}

	// 通过TICKET换取二维码
	public static void getEwmImage(String ticket){
		try {
			String requestUrl = POST_GET_EWM.replace("TICKET", URLEncoder.encode(ticket));
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5*1000);
			InputStream inputStream = conn.getInputStream();
			byte data[] = readInputStream(inputStream);
			inputStream.read(data);
			inputStream.close();
			/*
			 *  response.setContentType("image/jpg"); //设置返回的文件类型      
18.        OutputStream os = response.getOutputStream();    
19.        os.write(data);    
20.        os.flush();    
21.        os.close();   

			 */
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public static byte[] readInputStream(InputStream inStream)
			throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[2048];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
	
	
	
	//长连接变短连接
	// 获得临时TICKET expire <1800 scene_id 为32位非0整型
		public static Map<String, Object> CompressLinks(String tocken,
				String longurl) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, Object> paramMap = new HashMap<String, Object>();

			String requestUrl = POST_EWM.replace("ACCESS_TOKEN", tocken);
			paramMap.put("long_url", longurl);
			paramMap.put("action", "long2short");	

			String jsonParam = JSONObject.fromObject(paramMap).toString();
			JSONObject jsonObject = AuthedWeixin.httpRequest(requestUrl, "POST",
					jsonParam);
			if (null != jsonObject) {
				try {
					if (jsonObject.has("errcode") && jsonObject.getInt("errcode")==0) {
						resultMap.put("short_url", jsonObject.getString("short_url"));
						resultMap.put("errmsg", jsonObject.getString("errmsg"));

						return resultMap;
					} else
						return null;

				} catch (JSONException e) {
					// 获取token失败
					log.error("获取token失败 errcode:{} errmsg:{}",
							jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
					return null;

				}
			}
			return null;
		}
}
