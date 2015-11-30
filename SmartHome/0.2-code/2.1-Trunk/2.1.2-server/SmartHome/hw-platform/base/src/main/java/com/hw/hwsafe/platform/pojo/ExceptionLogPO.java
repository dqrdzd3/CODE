/**
 * 文件名：ExceptionLogPO.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 异常日志PO类
 * 
 * @author 马宁
 * @创建时间: 2013-03-20
 */
public class ExceptionLogPO implements Serializable{

	private static final long serialVersionUID = 5342195629428899185L;

	/**
	 * 日志编号长度
	 */
	public static final int CODE_LENGTH = 10;

	/**
	 * 日志编号前缀
	 */
	public static final char CODE_FILL_PREFIX = '0';

	// ---------------- Fields ----------------

	/**
	 * ID
	 */
	private String id;

	/**
	 * 编号
	 */
	private String code;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 操作人ID
	 */
	private String operatorId;

	/**
	 * 操作人姓名
	 */
	private String operatorName;

	/**
	 * 记录时间
	 */
	private Date recordingTime;

	/**
	 * 消息
	 */
	private String msg;

	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * ip
	 */
	private String ip;

	/**
	 * 查询起始时间
	 */
	private Date queryStartDate;

	/**
	 * 查询结束时间
	 */
	private Date queryEndDate;

	// ------------- Constructor ---------------

	public ExceptionLogPO() {
	}

	public ExceptionLogPO(String id, String code, String operatorId,
			String operatorName, Date recordingTime, String msg, String content, String ip) {
		super();
		this.id = id;
		this.code = code;
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.recordingTime = recordingTime;
		this.msg = msg;
		this.content = content;
		this.ip = ip;
	}

	// ------------- getter and setter ---------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Date getRecordingTime() {
		return recordingTime;
	}

	public void setRecordingTime(Date recordingTime) {
		this.recordingTime = recordingTime;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getQueryStartDate() {
		return queryStartDate;
	}

	public void setQueryStartDate(Date queryStartDate) {
		this.queryStartDate = queryStartDate;
	}

	public Date getQueryEndDate() {
		return queryEndDate;
	}

	public void setQueryEndDate(Date queryEndDate) {
		this.queryEndDate = queryEndDate;
	}

}
