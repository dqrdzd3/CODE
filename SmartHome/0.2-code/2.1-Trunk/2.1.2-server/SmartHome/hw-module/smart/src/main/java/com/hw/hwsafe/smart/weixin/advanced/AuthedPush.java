package com.hw.hwsafe.smart.weixin.advanced;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hw.hwsafe.smart.weixin.message.resp.PushArticle;



//阅号提供了每天一条的群发权限，为服务号提供每月（自然月）4条的群发权限
public class AuthedPush {
	private static Logger log = LoggerFactory.getLogger(AuthedPush.class);

	// 上传图文消息素材 post
		public final static String PUSH_NEWS_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";  
   //根据分组进行群发
		public final static String PUSH_BY_TEAM_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";

   //根据OpenID列表群发
		public final static String PUSH_BY_OPENID_URL="https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
	//删除群发
	/*
	 * 只有已经发送成功的消息才能删除删除消息只是将消息的图文详情页失效，已经收到的用户，还是能在其本地看到消息卡片。 另外，删除群发消息只能删除图文消息和视频消息，其他类型的消息一经发送，无法删除。
	 */
		
		public final static String PUSH_DELETE_URL = "https://api.weixin.qq.com//cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN";
		
		//推送article
		public static Map<String,Object> pushArticls(String tocken,List<PushArticle> list){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("articles", list);
			String requestUrl = PUSH_NEWS_URL.replace("ACCESS_TOKEN", tocken);
			
			String jsonParam = JSONObject.fromObject(paramMap).toString();
			JSONObject jsonObject = AuthedWeixin.httpRequest(requestUrl, "POST", jsonParam);
			if (null != jsonObject) {
				try {
					if (jsonObject.has("type")){
						resultMap.put("created_at", jsonObject.getLong("created_at"));
						resultMap.put("media_id", jsonObject.getString("media_id"));
						 resultMap.put("type", jsonObject.getString("type"));
						return resultMap;
					}else
						return null;
					
				} catch (JSONException e) {
					// 获取token失败
					log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
					return null;
		
				}
			}
			return null;
		}
		
		
		//按组推送
		public static Map<String,Object> pushByGroup(String tocken,String groupid,String media_id,String type){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			String requestUrl = PUSH_BY_TEAM_URL.replace("ACCESS_TOKEN", tocken);
			paramMap.put("msgtype", type);
			Map<String,String> tempMap1 = new HashMap<String, String>();
			tempMap1.put("group_id", groupid);
			paramMap.put("filter", tempMap1);
			
			if(type.equals("mpnews")){
				
				Map<String,String> tempMap = new HashMap<String, String>();
				tempMap.put("media_id", media_id);
				paramMap.put("mpnews", tempMap);
			}
			if(type.equals("text")){
				
				Map<String,String> tempMap = new HashMap<String, String>();
				tempMap.put("content", media_id);
				paramMap.put("text", tempMap);
			}
			if(type.equals("voice")){
	
				Map<String,String> tempMap = new HashMap<String, String>();
				tempMap.put("media_id", media_id);
				paramMap.put("voice", tempMap);
			}
			if(type.equals("image")){
				
				Map<String,String> tempMap = new HashMap<String, String>();
				tempMap.put("media_id", media_id);
				paramMap.put("image", tempMap);
			}
			String jsonParam = JSONObject.fromObject(paramMap).toString();
			JSONObject jsonObject = AuthedWeixin.httpRequest(requestUrl, "POST", jsonParam);
			if (null != jsonObject) {
				try {
					if (jsonObject.has("errcode")){
						resultMap.put("errcode", jsonObject.getString("errcode"));
						resultMap.put("errmsg", jsonObject.getString("errmsg"));
						resultMap.put("msg_id", jsonObject.getString("msg_id"));
						if(jsonObject.has("type")) resultMap.put("type", jsonObject.getString("type"));
						return resultMap;
					}else
						return null;
					
				} catch (JSONException e) {
					// 获取token失败
					log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
					return null;
		
				}
			}
			return null;
		}
		
		
		
		//根据openid列表推送
		/*
		 * openids openid数组
		 * 
		 * type 包括：mpnews text voice image video（暂不支持）   注意都是小写
		 */
		public static Map<String,Object> pushOpenid(String tocken,String[] openids,String media_id,String type){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			String requestUrl = PUSH_BY_OPENID_URL.replace("ACCESS_TOKEN", tocken);
			paramMap.put("touser", openids);
			paramMap.put("msgtype", type);
			if(type.equals("mpnews")){
				
				Map<String,String> tempMap = new HashMap<String, String>();
				tempMap.put("media_id", media_id);
				paramMap.put("mpnews", tempMap);
			}
			if(type.equals("text")){
				
				Map<String,String> tempMap = new HashMap<String, String>();
				tempMap.put("content", media_id);
				paramMap.put("text", tempMap);
			}
			if(type.equals("voice")){
	
				Map<String,String> tempMap = new HashMap<String, String>();
				tempMap.put("media_id", media_id);
				paramMap.put("voice", tempMap);
			}
			if(type.equals("image")){
				
				Map<String,String> tempMap = new HashMap<String, String>();
				tempMap.put("media_id", media_id);
				paramMap.put("image", tempMap);
			}
			
			String jsonParam = JSONObject.fromObject(paramMap).toString();
			JSONObject jsonObject = AuthedWeixin.httpRequest(requestUrl, "POST", jsonParam);
			if (null != jsonObject) {
				try {
					if (jsonObject.has("errcode")){
						resultMap.put("errcode", jsonObject.getString("errcode"));
						resultMap.put("errmsg", jsonObject.getString("errmsg"));
						resultMap.put("msg_id", jsonObject.getString("msg_id"));
						if(jsonObject.has("type")) resultMap.put("type", jsonObject.getString("type"));
						return resultMap;
					}else
						return null;
					
				} catch (JSONException e) {
					// 获取token失败
					log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
					return null;
		
				}
			}
			return null;
		}
		
		
		
		
		//删除推送
		public static Map<String,Object>  deletePush(String tocken,String msgid){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("msgid", msgid);
			String jsonParam = JSONObject.fromObject(paramMap).toString();
			String requestUrl = PUSH_DELETE_URL.replace("ACCESS_TOKEN", tocken);
			System.out.println(jsonParam);
			JSONObject jsonObject = AuthedWeixin.httpRequest(requestUrl, "POST", jsonParam);
			if (null != jsonObject) {
				try {
					if (jsonObject.has("errcode")){
						resultMap.put("errcode", jsonObject.getString("errcode"));
						resultMap.put("errmsg", jsonObject.getString("errmsg"));
						return resultMap;
					}else
						return null;
					
				} catch (JSONException e) {
					// 获取token失败
					log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
					return null;
		
				}
			}
			return null;
			
		}

}
