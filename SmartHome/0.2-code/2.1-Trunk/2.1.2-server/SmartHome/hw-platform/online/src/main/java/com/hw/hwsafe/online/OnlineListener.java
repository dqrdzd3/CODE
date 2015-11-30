/**
 * 文件名：OnlineListener.java
 *
 * 版本信息：
 * 日期：2012-8-1
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.online;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.session.recorder.SessionRecorder;
import com.hw.hwsafe.platform.userinfo.UserSession;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：OnlineListener
 * 类描述：在线监听
 * 创建人：付强
 * 创建时间：2012-8-1 上午10:55:35
 */
public class OnlineListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		if(Constants.USER_SESSION_KEY.endsWith(event.getName())){
			UserSession userSession=(UserSession)event.getSession().getAttribute(Constants.USER_SESSION_KEY);
			//登录用户校验所属企业或政府单位的同时在线人数是否达到上限，达到上限就清除登录用户的会话信息。
			boolean status=OnlineCount.doUser(userSession, true);
			if(!status){
				event.getSession().removeAttribute(Constants.USER_SESSION_KEY);
			}
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		if(Constants.USER_SESSION_KEY.endsWith(event.getName())){
			UserSession userSession=(UserSession)event.getValue();
			UserPO userPo = userSession.getUserPO();
			if(SessionRecorder.isRecorderLawful(userPo.getId(), event.getSession().getId())){
				//注销用户修改所属企业或政府单位的同时在线人数。
				OnlineCount.doUser(userSession, false);
			}
			userSession=null;
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// DO NOTHING
	}

}
