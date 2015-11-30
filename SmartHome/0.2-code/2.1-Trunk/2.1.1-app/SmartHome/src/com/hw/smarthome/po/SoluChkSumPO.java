package com.hw.smarthome.po;

import java.io.Serializable;

/**
 * @author 曾凡
 * @time 2015年8月19日 下午1:10:34
 */
public class SoluChkSumPO implements Serializable {
	private static final long serialVersionUID = 8418515351396998744L;
	private String fileType;
	private String co2;
	private String pm25;
	private String voc;
	private String scene;
	private String centent;

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getCo2() {
		return co2;
	}

	public void setCo2(String co2) {
		this.co2 = co2;
	}

	public String getPm25() {
		return pm25;
	}

	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}

	public String getVoc() {
		return voc;
	}

	public void setVoc(String voc) {
		this.voc = voc;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getCentent() {
		return centent;
	}

	public void setCentent(String centent) {
		this.centent = centent;
	}

	@Override
	public String toString() {
		return "FILETYPE=" + fileType + "&CO2=" + co2 + "&PM25="
				+ pm25 + "&VOC=" + voc + "&SCENE=" + scene;
	}

}
