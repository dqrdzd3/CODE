package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;

public class BigScreenPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String userId;
	private String displayName;
	private String displayModel;
	private String sceneId;
	private String deviceIdIn;
	private String deviceIdOut;
	private String createTime;
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
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDisplayModel() {
		return displayModel;
	}
	public void setDisplayModel(String displayModel) {
		this.displayModel = displayModel;
	}
	public String getSceneId() {
		return sceneId;
	}
	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}
	public String getDeviceIdIn() {
		return deviceIdIn;
	}
	public void setDeviceIdIn(String deviceIdIn) {
		this.deviceIdIn = deviceIdIn;
	}
	public String getDeviceIdOut() {
		return deviceIdOut;
	}
	public void setDeviceIdOut(String deviceIdOut) {
		this.deviceIdOut = deviceIdOut;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
