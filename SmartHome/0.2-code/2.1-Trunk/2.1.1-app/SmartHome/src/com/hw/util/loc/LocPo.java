package com.hw.util.loc;

/**
 * @author 曾凡
 * @time 2015年3月23日 下午7:03:26
 */
public class LocPo {

	private String json;
	private String lat;
	private String lng;
	private String loc;
	private String province;
	private String city;
	private String area;
	/* 是否是吐槽状态 */
	private boolean isTalk = false;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public boolean isTalk() {
		return isTalk;
	}

	public void setTalk(boolean isTalk) {
		this.isTalk = isTalk;
	}

	@Override
	public String toString() {
		return "LocPo [json=" + json + ", lat=" + lat + ", lng="
				+ lng + ", loc=" + loc + ", province="
				+ province + ", city=" + city + ", area=" + area
				+ "]";
	}

}
