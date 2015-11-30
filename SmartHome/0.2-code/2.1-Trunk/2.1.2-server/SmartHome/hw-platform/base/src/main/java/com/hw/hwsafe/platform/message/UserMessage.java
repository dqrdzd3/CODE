package com.hw.hwsafe.platform.message;

import com.hw.hwsafe.platform.constants.Constants;

public class UserMessage {
	
	/**
	 * type: 消息类型
	 * 0：无图标
	 * 1：正确
	 * 2：信息
	 * 3：询问
	 * 4：警告
	 * 5：错误
	 */
	
	protected String IDENTITY = "message";
	
	protected int type = 0;
	protected String title = Constants.SERVER_MSG;
	protected String content = "";
	
	public UserMessage(){}
	
	public UserMessage(int type, String content) {
		this.type = type;
		this.content = content;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void set(int type, String msg, String... title) {
		setType(type);
		setContent(msg);
		if (title.length > 0)
			setTitle(title[0]);
	}

	public String getIDENTITY() {
		return IDENTITY;
	}

	public void setIDENTITY(String iDENTITY) {
		IDENTITY = iDENTITY;
	}
	
}
