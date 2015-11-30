package com.hw.hwsafe.sms.czd.po;

import java.util.Date;
import java.util.List;

/**
 * PO类，封装短信内容，收信人等信息，用来做信息传递
 * @author 陈浙东
 */
/**
 * 项目名称：hw-sms
 * 类名称：MessagePO
 * 类描述：PO类，封装短信内容，收信人等信息，用来做信息传递
 * 创建人：陈浙东
 * 创建时间：2013-4-09
 *
 */
public class MessagePO {
	private String id; // id
	private String content; // 发送内容
	private Date sendTime; // 发送时间
	private Date receiveTime; // 接收时间

	private List<String> recievePeopleList; // 收信人列表
	private String telphone; //收信人手机号
	
	private String recievePeople; // 收信人，用来做查询用
	private String sendPeople; // 发信人，用来做查询用
	
	private String sendDeleteFlag;	//发送人删除标记
	private String receiveDeleteFlag; //收信人删除标记
	private String receiveReadFlag;	//收信人已读标记
	
	private String ip;	//发送ip
	private String gateway;	//网关
	private String port;	//端口
	private String manufacturer;	//制造商
	private String pin;	//pin码
	
	private int curPage; //当前页
	private int beginNum;
	private int endNum;
	private int num;
	
	private String corpId;
	private String mode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public List<String> getRecievePeopleList() {
		return recievePeopleList;
	}

	public void setRecievePeopleList(List<String> recievePeopleList) {
		this.recievePeopleList = recievePeopleList;
	}

	public String getRecievePeople() {
		return recievePeople;
	}

	public void setRecievePeople(String recievePeople) {
		this.recievePeople = recievePeople;
	}

	public String getSendPeople() {
		return sendPeople;
	}

	public void setSendPeople(String sendPeople) {
		this.sendPeople = sendPeople;
	}

	public String getSendDeleteFlag() {
		return sendDeleteFlag;
	}

	public void setSendDeleteFlag(String sendDeleteFlag) {
		this.sendDeleteFlag = sendDeleteFlag;
	}

	public String getReceiveDeleteFlag() {
		return receiveDeleteFlag;
	}

	public void setReceiveDeleteFlag(String receiveDeleteFlag) {
		this.receiveDeleteFlag = receiveDeleteFlag;
	}

	public String getReceiveReadFlag() {
		return receiveReadFlag;
	}

	public void setReceiveReadFlag(String receiveReadFlag) {
		this.receiveReadFlag = receiveReadFlag;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getBeginNum() {
		return beginNum;
	}

	public void setBeginNum(int beginNum) {
		this.beginNum = beginNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
}
