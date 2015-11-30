/**
 * 文件名：SessionRecorder.java
 *
 * 版本信息：1.0
 * 日期：2012-5-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.session.recorder;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 会话记录器
 * 
 * @author 马宁
 * @since 2013-01-06 16:53
 */
public final class SessionRecorder {

	/**
	 * 记录器
	 */
	private static Map<String, String> recorder = new HashMap<String, String>(0);

	private SessionRecorder(){}
	
	/**
	 * 添加
	 * 
	 * @param loginId
	 *            - 登录ID
	 * @param sessionId
	 *            - 会话id
	 * 
	 * @author 马宁
	 */
	public static void put(String loginId, String sessionId) {
		recorder.put(loginId, sessionId);
	}

	/**
	 * 移除
	 * 
	 * @param sessionId
	 *            - 会话id
	 *            
	 * @author 马宁
	 */
	public static void remove(String sessionId) {
		if (sessionId == null) {
			return;
		}

		Set<Entry<String, String>> entrys = recorder.entrySet();
		for (Entry<String, String> entry : entrys) {
			if (sessionId.equals(entry.getValue())) {
				recorder.remove(entry.getKey());
				break;
			}
		}
	}

	/**
	 * 判断会话id是否合法
	 * 
	 * @param sessionId
	 *            - 会话id
	 * @return boolean
	 * 
	 * @author 马宁
	 */
	public static boolean isSessionLawful(String sessionId) {
		return recorder.containsValue(sessionId);
	}
	
	/**
	 * 判断是否该移除实例
	 * 
	 * @param loginId
	 *            - 登录名
	 * @param sessionId
	 *            - 会话id
	 * @return
	 * 
	 * @author 马宁
	 */
	public static boolean isTimeToRemove(String loginId, String sessionId){
		String sessionIdStored = recorder.get(loginId);
		return sessionIdStored == null
				? false
				: sessionIdStored.equals(sessionId);
	}
	
	/**
	 * 判断记录是否合法
	 * @param loginId - 登录名
	 * @param sessionId - 会话id
	 * @return
	 * 
	 * @author 马宁
	 */
	public static boolean isRecorderLawful(String loginId, String sessionId){
		String sessionIdStored = recorder.get(loginId);
		return sessionIdStored == null
				? true
				: sessionIdStored.equals(sessionId);
	}
}
