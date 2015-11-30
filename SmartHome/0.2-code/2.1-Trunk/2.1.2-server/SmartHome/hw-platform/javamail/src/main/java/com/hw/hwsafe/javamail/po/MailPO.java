package com.hw.hwsafe.javamail.po;

/**
 * 项目名称：hw-javamail
 * 类名称：MailPO
 * 类描述：邮件发送PO
 * 创建人：陈浙东
 * 创建时间：2013-4-10
 *
 */
import java.util.Date;
import java.util.List;

public class MailPO {
	private String id;
	private String sendId;
	private Date sendTime;
	private String sendTag;

	private List<String> receiveMailList;
	private List<AttachementPO> attachmentList;
	private String receive;

	private String sendMail;
	private String ip;
	private String port;
	private String title;
	private String content;
	
	private String mode;  //text和html 表示发送邮件的格式
	private int beginNum;
	private int endNum;
	private int curPage;
	private int num;
	
	private String deleteTag;
	private String readTag;
	private String receiveStr;
	private String corpId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendTag() {
		return sendTag;
	}

	public void setSendTag(String sendTag) {
		this.sendTag = sendTag;
	}

	public List<String> getReceiveMailList() {
		return receiveMailList;
	}

	public void setReceiveMailList(List<String> receiveMailList) {
		this.receiveMailList = receiveMailList;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public String getSendMail() {
		return sendMail;
	}

	public void setSendMail(String sendMail) {
		this.sendMail = sendMail;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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

	public String getDeleteTag() {
		return deleteTag;
	}

	public void setDeleteTag(String deleteTag) {
		this.deleteTag = deleteTag;
	}

	public String getReadTag() {
		return readTag;
	}

	public void setReadTag(String readTag) {
		this.readTag = readTag;
	}

	public String getReceiveStr() {
		return receiveStr;
	}

	public void setReceiveStr(String receiveStr) {
		this.receiveStr = receiveStr;
	}

	public List<AttachementPO> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<AttachementPO> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
