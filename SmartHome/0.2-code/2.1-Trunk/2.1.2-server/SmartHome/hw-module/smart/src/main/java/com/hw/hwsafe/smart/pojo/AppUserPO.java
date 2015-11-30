package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;

public class AppUserPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userID;      //主键
	private String name;    //别名
	private String countryCode;      //国家代码
	private String phoneNo;    //手机
	private String email;    //邮件
	private String password;  
	private String token;     //令牌
	private String createTime;   
	private byte[] pic;       //头像
	private String picbs64;     //头像流
	private String idCode;       //验证码
	private UserBasePO baseInfo;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	public String getPicbs64() {
		return picbs64;
	}
	public void setPicbs64(String picbs64) {
		this.picbs64 = picbs64;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	public UserBasePO getBaseInfo() {
		return baseInfo;
	}
	public void setBaseInfo(UserBasePO baseInfo) {
		this.baseInfo = baseInfo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	
	
}
