package com.hw.smarthome.po;

import java.io.Serializable;
import java.util.Arrays;

public class ScenePO implements Serializable {
	private static final long serialVersionUID = -8294777212924456767L;
	private String ma001; // 主键
	private String ma002; // 关联设备
	private String ma003; // 关联控制设备
	private String ma004; // 场景名称
	private byte[] ma005; // 图片流
	private String ma006; // USERID
	private String ma007; // 排序字段
	private String ma008; // 场景类型 0000自定义，0001客厅，0002厨房，0003卧室
	private String ma009;
	private String ma010;

	public String getMa001() {
		return ma001;
	}

	public void setMa001(String ma001) {
		this.ma001 = ma001;
	}

	public String getMa002() {
		return ma002;
	}

	public void setMa002(String ma002) {
		this.ma002 = ma002;
	}

	public String getMa003() {
		return ma003;
	}

	public void setMa003(String ma003) {
		this.ma003 = ma003;
	}

	public String getMa004() {
		return ma004;
	}

	public void setMa004(String ma004) {
		this.ma004 = ma004;
	}

	public byte[] getMa005() {
		return ma005;
	}

	public void setMa005(byte[] ma005) {
		this.ma005 = ma005;
	}

	public String getMa006() {
		return ma006;
	}

	public void setMa006(String ma006) {
		this.ma006 = ma006;
	}

	public String getMa007() {
		return ma007;
	}

	public void setMa007(String ma007) {
		this.ma007 = ma007;
	}

	public String getMa008() {
		return ma008;
	}

	public void setMa008(String ma008) {
		this.ma008 = ma008;
	}

	public String getMa009() {
		return ma009;
	}

	public void setMa009(String ma009) {
		this.ma009 = ma009;
	}

	public String getMa010() {
		return ma010;
	}

	public void setMa010(String ma010) {
		this.ma010 = ma010;
	}

	@Override
	public String toString() {
		return "ScenePO [ma001=" + ma001 + ", ma002=" + ma002
				+ ", ma003=" + ma003 + ", ma004=" + ma004
				+ ", ma005=" + Arrays.toString(ma005)
				+ ", ma006=" + ma006 + ", ma007=" + ma007
				+ ", ma008=" + ma008 + ", ma009=" + ma009
				+ ", ma010=" + ma010 + "]";
	}

}
