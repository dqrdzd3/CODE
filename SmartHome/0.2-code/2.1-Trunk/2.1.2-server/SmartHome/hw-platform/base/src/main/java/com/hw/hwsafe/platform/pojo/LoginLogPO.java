
/**
 * @title LoginLogPO.java
 * @package com.hw.hwsafe.platform.pojo
 * @author 孟繁波
 * @create_time 2013-6-20 下午4:08:31
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.platform.pojo;

import java.util.Date;


/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */

public class LoginLogPO {

	private String id;
	
	private int type;
	
	private Date recordingTime;
	
	private String userId;
	
	private String userName;
	
	private String ip;
	
	private String govId;
	
	private String corpId;
	
	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getRecordingTime() {
		return recordingTime;
	}

	public void setRecordingTime(Date recordingTime) {
		this.recordingTime = recordingTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getGovId() {
		return govId;
	}

	public void setGovId(String govId) {
		this.govId = govId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
