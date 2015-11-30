package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;

public class UpdatePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String app_base_id;   //用户信息
	private String current_version;    //当前版本
	private String last_version;     //上一个版本
	private String update_time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApp_base_id() {
		return app_base_id;
	}
	public void setApp_base_id(String app_base_id) {
		this.app_base_id = app_base_id;
	}
	public String getCurrent_version() {
		return current_version;
	}
	public void setCurrent_version(String current_version) {
		this.current_version = current_version;
	}
	public String getLast_version() {
		return last_version;
	}
	public void setLast_version(String last_version) {
		this.last_version = last_version;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	
}
