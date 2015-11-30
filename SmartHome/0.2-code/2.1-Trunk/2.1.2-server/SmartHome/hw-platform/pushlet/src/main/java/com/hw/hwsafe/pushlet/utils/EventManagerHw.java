package com.hw.hwsafe.pushlet.utils;

import org.apache.commons.lang.StringUtils;

import com.hw.hwsafe.pushlet.po.PushletMsgPO;

import nl.justobjects.pushlet.core.Event;

/**
 * Event管理类
 * 
 * @author 马宁
 * @创建时间 2013-09-04
 */
public final class EventManagerHw {

	/**
	 * 单播事件参数
	 */
	public static final String UNICAST_EVENT_PARAM = "hwUnicastEvent";
	
	/**
	 * 消息标识参数
	 */
	public static final String HW_MESSAGE_ID_PARAM = "msgId";
	
	/**
	 * 消息内容参数
	 */
	public static final String HW_MESSAGE_PARAM = "msg";
	
	/**
	 * 消息url参数
	 */
	public static final String HW_MESSAGE_URL_PARAM = "msgUrl";
	
	// ------------------- Constructor -----------------
	
	private EventManagerHw() {
		super();
	}
	
	// ----------------- methods -----------------
	
	/**
	 * 创建单播事件
	 */
	public static Event createHwUnicastEvent(PushletMsgPO po){
		Event result = Event.createDataEvent(UNICAST_EVENT_PARAM);
		result.setField(HW_MESSAGE_ID_PARAM, po.getId());
		result.setField(HW_MESSAGE_PARAM, po.getMsg());
		if(!StringUtils.isBlank(po.getUrl())){
			result.setField(HW_MESSAGE_URL_PARAM, po.getUrl());
		}
		return result; 
	}

}
