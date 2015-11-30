package com.hw.hwsafe.smart.weixin.pojo;


/**
 * ΢��ͨ�ýӿ�ƾ֤
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class AccessToken {
	// ��ȡ����ƾ֤
	private String token;
	// ƾ֤��Чʱ�䣬��λ����
	private int expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
