package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;

public class DeviceSharePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String from_phone;   //从谁那分享
	private String to_phone;     //分享给谁
	private String deviceId;    //设备id
	private int is_shared;        //是否已经被分享
	private String create_time;
	private String userId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFrom_phone() {
		return from_phone;
	}
	public void setFrom_phone(String from_phone) {
		this.from_phone = from_phone;
	}
	public String getTo_phone() {
		return to_phone;
	}
	public void setTo_phone(String to_phone) {
		this.to_phone = to_phone;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public int getIs_shared() {
		return is_shared;
	}
	public void setIs_shared(int is_shared) {
		this.is_shared = is_shared;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
