package com.hw.smarthome.ctrl.po;

import antistatic.spinnerwheel.AbstractWheel;

/**
 * 用来保存wheel的状态
 * 
 * @author 曾凡
 * @time 2015年4月23日 上午9:16:23
 */
public class SettingStatusPo {
	private AbstractWheel wheel9999;
	private AbstractWheel wheel99;

	public SettingStatusPo(AbstractWheel wheel9999,
			AbstractWheel wheel99) {
		super();
		this.wheel9999 = wheel9999;
		this.wheel99 = wheel99;
	}

	public AbstractWheel getWheel9999() {
		return wheel9999;
	}

	public void setWheel9999(AbstractWheel wheel9999) {
		this.wheel9999 = wheel9999;
	}

	public AbstractWheel getWheel99() {
		return wheel99;
	}

	public void setWheel99(AbstractWheel wheel99) {
		this.wheel99 = wheel99;
	}

	@Override
	public String toString() {
		return "SettingStatusPo [wheel9999=" + wheel9999
				+ ", wheel99=" + wheel99 + "]";
	}

}
