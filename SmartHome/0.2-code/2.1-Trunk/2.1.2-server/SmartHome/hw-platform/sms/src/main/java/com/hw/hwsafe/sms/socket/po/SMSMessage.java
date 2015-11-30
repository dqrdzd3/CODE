package com.hw.hwsafe.sms.socket.po;

import java.io.Serializable;

public class SMSMessage implements Serializable {
	private static final long serialVersionUID = -3249524139003717779L;
	private String phoneNo;
	private String content;

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "SMSMessage [phoneNo=" + phoneNo + ", content="
				+ content + "]";
	}

}
