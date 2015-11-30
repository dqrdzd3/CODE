package com.hw.weidou.ui.home.adapter.po;


/**
 * @author 曾凡
 * @time 2014年10月21日 上午10:37:07
 */
public class HistoryChartPo {
	/** 设备类型(第二个short高三位) =[甲醛:0x02,酒精:0x03,一氧化碳:0x04] */
	private short equip;
	private String name;
	private short type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public short getEquip() {
		return equip;
	}

	public void setEquip(short equip) {
		this.equip = equip;
	}

}
