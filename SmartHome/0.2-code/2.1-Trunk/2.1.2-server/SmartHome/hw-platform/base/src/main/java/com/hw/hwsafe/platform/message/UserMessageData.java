/**
 * 文件名：UserMessageData.java
 *
 * 版本信息：
 * 日期：2012-10-16
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.message;

import java.util.HashMap;
import java.util.Map;


/**
 * 项目名称：framework
 * 类名称：UserMessageData
 * 类描述：继承UserMessage类，新增
 * 创建人：杜群星
 * 创建时间：2012-10-16 下午2:23:52
 */
public class UserMessageData extends UserMessage {
	public UserMessageData(){
		super();
	}

	public UserMessageData(int type, String content) {
		super(type, content);
	}

	private Map map = new HashMap();

	public void set(int type, String msg, Map map, String... title) {
		setType(type);
		setContent(msg);
		setMap(map);
		if (title.length > 0){
			setTitle(title[0]);
		}
	}

	public void set(int type, String msg, Map map) {
		setType(type);
		setContent(msg);
		setMap(map);
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Map getMap() {
		return map;
	}
}
