package com.hw.smarthome.daq.po;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;

import com.hw.hwsafe.smart.util.ByteUtils;
import com.hw.smarthome.daq.po.base.DataItem;


/**
 * 一个操作的生命周期PO
 * 
 * @author 曾凡
 * @time 2015年1月4日 下午4:59:04
 */
public class DAqPo implements Serializable {

	private static final long serialVersionUID = -9076888473014555782L;

	/** 任务调度或前置机生成的每次操作的唯一ID */
	private String id;
	private String sensorId;
	private byte afn;

	/** 接收到的数据 */
	private byte[] receiveData;
	/** 发送出的数据 */
	private byte[] sendData;

	private List<DataItem> dataItems;

	/** 源端地址 */
	private InetAddress ip;

	private int port;
	/** 允许延迟时间 */
	private int delaySecond;
	/** 下发日期或者上报日期 */
	private Date date;

	private boolean isChkSumLegal;
	/** 0，表示此帧报文是由主站发出的下行报文；1，由终端发出的上行报文 */
	private boolean dir;
	/** 1，表示此帧报文来自启动站；0，表示此帧报文来自从动站 */
	private boolean prm;
	private boolean fcb;
	private boolean fcv;
	private byte ctrlCount;

	/** 表示该帧内含有无时间标签 */
	private boolean tpv;
	/** 报文的第一帧 */
	private boolean fir;
	/** 报文的最后一帧 */
	private boolean fin;
	/** 1,表示该帧必须回应，0，表示该帧无须回应 */
	private boolean con;
	/** 记录SEQ数 */
	private byte seqCount;
	/** 响应码 0:硬件没有连接上服务器 */
	private int responseCode;

	/**
	 * JMS直接传PO
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2015年1月12日 下午2:58:56
	 */
	public DAqPo getJMS() {
		return this;
	}

	public String getWsJson() {
		StringBuilder sb = new StringBuilder(
				"{'protocol':'1','data':[");
		int count = 1;
		if (dataItems != null && dataItems.size() > 0) {
			for (DataItem item : dataItems) {
				sb.append(item.getWsJson(sensorId));
				if (count++ < dataItems.size()) {
					sb.append(",");
				}
			}
		}
		sb.append("]}");
		return sb.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getReceiveData() {
		return receiveData;
	}

	public void setReceiveData(byte[] receiveData) {
		this.receiveData = receiveData;
	}

	public byte[] getSendData() {
		return sendData;
	}

	public void setSendData(byte[] sendData) {
		this.sendData = sendData;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public byte getAfn() {
		return afn;
	}

	public void setAfn(byte afn) {
		this.afn = afn;
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isChkSumLegal() {
		return isChkSumLegal;
	}

	public void setChkSumLegal(boolean isChkSumLegal) {
		this.isChkSumLegal = isChkSumLegal;
	}

	public boolean isTpv() {
		return tpv;
	}

	public void setTpv(boolean tpv) {
		this.tpv = tpv;
	}

	public boolean isFir() {
		return fir;
	}

	public void setFir(boolean fir) {
		this.fir = fir;
	}

	public boolean isFin() {
		return fin;
	}

	public void setFin(boolean fin) {
		this.fin = fin;
	}

	public boolean isCon() {
		return con;
	}

	public void setCon(boolean con) {
		this.con = con;
	}

	public List<DataItem> getDataItems() {
		return dataItems;
	}

	public void setDataItems(List<DataItem> dataItems) {
		this.dataItems = dataItems;
	}

	public int getDelaySecond() {
		return delaySecond;
	}

	public void setDelaySecond(int delaySecond) {
		this.delaySecond = delaySecond;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public boolean isDir() {
		return dir;
	}

	public void setDir(boolean dir) {
		this.dir = dir;
	}

	public boolean isPrm() {
		return prm;
	}

	public void setPrm(boolean prm) {
		this.prm = prm;
	}

	public byte getSeqCount() {
		return seqCount;
	}

	public void setSeqCount(byte seqCount) {
		this.seqCount = seqCount;
	}

	public boolean isFcb() {
		return fcb;
	}

	public void setFcb(boolean fcb) {
		this.fcb = fcb;
	}

	public boolean isFcv() {
		return fcv;
	}

	public void setFcv(boolean fcv) {
		this.fcv = fcv;
	}

	public byte getCtrlCount() {
		return ctrlCount;
	}

	public void setCtrlCount(byte ctrlCount) {
		this.ctrlCount = ctrlCount;
	}

	@Override
	public String toString() {
		return "DAqPo [id=" + id + ", sensorId=" + sensorId
				+ ", afn=" + afn + ", receiveData="
				+ ByteUtils.toHex(receiveData) + ", sendData="
				+ ByteUtils.toHex(sendData) + ", dataItems="
				+ dataItems + ", ip=" + ip + ", port=" + port
				+ ", delaySecond=" + delaySecond + ", date="
				+ date + ", isChkSumLegal=" + isChkSumLegal
				+ ", dir=" + dir + ", prm=" + prm + ", fcb="
				+ fcb + ", fcv=" + fcv + ", ctrlCount="
				+ ctrlCount + ", tpv=" + tpv + ", fir=" + fir
				+ ", fin=" + fin + ", con=" + con
				+ ", seqCount=" + seqCount + ", responseCode="
				+ responseCode + "]";
	}

}
