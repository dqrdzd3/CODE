package com.hw.hwsafe.smart.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 联动关系po 如：智慧家
 * @author Administrator
 *
 */
public class LinkagePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7453528118890332707L;

	private String id;     //主键
	private String userId;   //用户id
	private String masterDevice;    //主控设备
	private String linkageIds;
	private String name;    //设备名称
	private List<String> deviceList;  //联动的设备列表
	private int linkageState;   //联动状态，肩负着自动控制和手动控制锁的控制
	private Map<String, Object> param;    //置换阴阳置换，例如：
	/*加湿器高60则关，低59则开
	yinYangReverse <HUMIDITY, true>
	paramYang <HUMIDITY,60>
	paramYin <HUMIDITY,59>

	增氧机CO2高501则开，低502则关
	yinYangReverse <CO2, false>
	paramYang <CO2,501>
	paramYin <CO2,502>*/

	private Map<String, Object> paramYin;
/*	阳发阀值联动的属性以及属性范围，e:26度开空调
	yinYangReverse = false;
	paramYang <temperater,26>*/

	private Map<String, Object> paramYang;
//	关发阀值联动的属性以及属性范围，e:低于25度关空调

	private String bgnTime;   //托管/联动的起始时间
	private String endTime;   //结束时间
	private String createTime;    //产生时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMasterDevice() {
		return masterDevice == null?"":masterDevice;
	}
	public void setMasterDevice(String masterDevice) {
		this.masterDevice = masterDevice;
	}
	public String getName() {
		return name == null?"":name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getDeviceList() {
		return deviceList;
	}
	public void setDeviceList(List<String> deviceList) {
		this.deviceList = deviceList;
	}
	public int getLinkageState() {
		return linkageState;
	}
	public void setLinkageState(int linkageState) {
		this.linkageState = linkageState;
	}
	public Map<String, Object> getParam() {
		return param;
	}
	public void setParam(Map<String, Object> param) {
		this.param = param;
	}
	public Map<String, Object> getParamYin() {
		return paramYin;
	}
	public void setParamYin(Map<String, Object> paramYin) {
		this.paramYin = paramYin;
	}
	public Map<String, Object> getParamYang() {
		return paramYang;
	}
	public void setParamYang(Map<String, Object> paramYang) {
		this.paramYang = paramYang;
	}
	public String getBgnTime() {
		return bgnTime == null?"":bgnTime;
	}
	public void setBgnTime(String bgnTime) {
		this.bgnTime = bgnTime;
	}
	public String getEndTime() {
		return endTime== null?"":endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCreateTime() {
		return createTime == null?"":createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLinkageIds() {
		return linkageIds==null?"":linkageIds;
	}
	public void setLinkageIds(String linkageId) {
		this.linkageIds = linkageId;
	}

	
	
}
