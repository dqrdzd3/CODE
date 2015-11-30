package com.hw.hwsafe.javamail.util;

import javax.mail.*;

/**
 * 项目名称：hw-javamail
 * 类名称：MyAuthenticator
 * 类描述：外部邮件发送时身份验证类
 * 创建人：陈浙东
 * 创建时间：2013-4-10
 *
 */
public class MyAuthenticator extends Authenticator {
	String userName = null;
	String password = null;

	public MyAuthenticator() {
	}

	public MyAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
