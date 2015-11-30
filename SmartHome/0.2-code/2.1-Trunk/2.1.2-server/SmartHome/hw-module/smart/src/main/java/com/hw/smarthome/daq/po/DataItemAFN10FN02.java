package com.hw.smarthome.daq.po;

import java.io.Serializable;

import com.google.gson.Gson;
import com.hw.smarthome.daq.po.base.DataItemAFN10;
import com.hw.smarthome.daq.po.base.TransData;

/**
 * 串口通讯用
 * 
 * @author 曾凡
 * @time 2015年4月14日 下午2:12:46
 */
public class DataItemAFN10FN02 extends DataItemAFN10 implements
		Serializable {
	private static final long serialVersionUID = 455384793386835875L;
	/** 控制令牌，用来防止重放攻击 */
	private String token;
	/** 传输方向 00:Wi-Fi->Server, 01:Server-Wi-Fi */
	private String transDir;
	/** 端口号 */
	private int portNum;
	/** 端口配置 */
	private String portConfig;
	/** 超时时间 单位为秒 */
	private int timeOut;
	/** 透传数据长度 */
	private int dataLen;
	/** 透传数据 */
	private TransData userData;

	@Override
	public String getWsJson(String sensorId) {
		setSensorId(sensorId);
		return new Gson().toJson(this);
	}

	public String getTransDir() {
		return transDir;
	}

	public void setTransDir(String transDir) {
		this.transDir = transDir;
	}

	public int getPortNum() {
		return portNum;
	}

	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}

	public String getPortConfig() {
		return portConfig;
	}

	public void setPortConfig(String portConfig) {
		this.portConfig = portConfig;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public int getDataLen() {
		return dataLen;
	}

	public void setDataLen(int dateLen) {
		this.dataLen = dateLen;
	}

	public TransData getUserData() {
		return userData;
	}

	public void setUserData(TransData userData) {
		this.userData = userData;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "DataItemAFN10FN02 [token=" + token
				+ ", transDir=" + transDir + ", portNum="
				+ portNum + ", portConfig=" + portConfig
				+ ", timeOut=" + timeOut + ", dataLen="
				+ dataLen + ", userData=" + userData + "]";
	}

}
