package com.hw.hwsafe.pushlet.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.pushlet.service.IPushletMsgService;
import com.hw.hwsafe.pushlet.utils.EventManagerHw;

/**
 * pushletMsg的action类
 * 
 * @author 马宁
 * @创建时间 2013-09-04
 */
public class PushletMsgAction extends BaseAction {

	@Autowired
	private IPushletMsgService pushletMsgService;

	// ------------------ methods -------------------
	
	/**
	 * 单播推送当前用户的消息
	 */
	public void unicastCurrentUserMsg() throws Exception{
		pushletMsgService.unicastCurrentUserMsg();
	}
	
	/**
	 * 删除消息
	 */
	public void delMsg() throws Exception{
		String msgId = (String) request.getParameter(EventManagerHw.HW_MESSAGE_ID_PARAM);
		pushletMsgService.deleteMsg(msgId);
	}
	
	// --------------- getter and setter -----------------
	
	public void setPushletMsgService(IPushletMsgService pushletMsgService) {
		this.pushletMsgService = pushletMsgService;
	}
}
