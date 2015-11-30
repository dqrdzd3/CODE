package com.hw.hwsafe.sms;

/**
 * 
 * 
 * 项目名称：framework
 * 类名称：MessagePO
 * 类描述：短信的实体类：号码和发送内容
 * 创建人：李玉梅
 * 创建时间：2013-1-8 下午2:12:41
 * 修改人：李玉梅
 * 修改时间：2013-1-8 下午2:12:41
 * 修改备注：
 * @version 
 *
 */
public class SMSPO {

	/**
	 * 手机号码
	 */
	private String telNum;

	/**
	 * 发送短信内容
	 */
	private String telContent;

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getTelContent() {
		return telContent;
	}

	public void setTelContent(String telContent) {
		this.telContent = telContent;
	}

}
