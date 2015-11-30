package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;
import java.util.Map;

public class LogOpPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String ops;//操作信息
/*	操作，e: <[1.1.1]用户注册,一次>
    <[1.2.1.1]添加设备,两次>
存数据库就存这个map的json*/

	private String app_base_id;    //关联用户信息
	private String create_Time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getOps() {
		return ops;
	}
	public void setOps(String ops) {
		this.ops = ops;
	}
	public String getApp_base_id() {
		return app_base_id;
	}
	public void setApp_base_id(String app_base_id) {
		this.app_base_id = app_base_id;
	}
	public String getCreate_Time() {
		return create_Time;
	}
	public void setCreate_Time(String create_Time) {
		this.create_Time = create_Time;
	}
	

}
