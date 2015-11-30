package com.hw.smarthome.po;

import java.io.Serializable;
/**
 * 
 * 
 * 项目名称：SmartHome
 * 类名称：PushMessage
 * 类描述：推送消息
 * 创建人：lijing
 * 创建时间：2014-7-4 上午9:40:56
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 
 *
 */
public class PushMessage implements Serializable {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = -706881718554887159L;
	private String id;
	private String title;
	private String message;
	private int type;//2报警1预警0知识
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	
}
