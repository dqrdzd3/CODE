package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;
import java.util.Map;

public class DevicePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;     //主键
	private String userId;   //用户id
	private String manufacturer;     //开发商
	private String deviceId;     //设备唯一标识，有的是MAC地址，有的是SN
	private String name;    //设备名称
	private String category;  //设备类别
	private String type;      //设备类型
	private Map<String, String> param;   //该设备值
	private int isOnline;     //是否在线
	private String createTime;    //创建日期
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, String> getParam() {
		return param;
	}
	public void setParam(Map<String, String> param) {
		this.param = param;
	}
	public int getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(int isOnline) {
		//this.isOnline = isOnline;
		//测试
		this.isOnline =1;
		//FIXME
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
