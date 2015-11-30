package com.hw.weidou.po;

import java.util.ArrayList;
import java.util.List;

import com.hw.weidou.ui.home.HomeFragment;

/**
 * @author 曾凡
 * @time 2014年10月13日 下午5:13:16
 */
public class WeidouPo {
	/** 设备类型(第二个byte高三位) =[甲醛:0x02,酒精:0x03,一氧化碳:0x04] */
	private byte equip;
	private String equipName;
	/** 功能类型(第二个byte低5位) =[威豆版本号:0x02,传输数据:0x15,电量数据:0xA,威豆使用时间:0xB] */
	private byte func;
	private String funcName;
	private String version;
	private String dataContent;
	private int power;
	/** 威豆使用时间（单位为分钟） */
	private long usage;

	private String status;
	private String means;
	private int color;
	private String unit;
	/** 最高值 */
	private String maxValue;
	/** 平均值 */
	private String avgValue;

	/** 判断威豆是否已经启动 */
	private boolean isStart;

	/** 判断系统是否工作正常，如果正常则返回true反之返回false */
	public boolean isOnline() {
		return (func != 0 && equip != 0)
				&& HomeFragment.isWeidouOn;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDataContent() {
		return (dataContent == null || "".equals(dataContent)) ? "0"
				: dataContent;
	}

	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int i) {
		this.power = i;
	}

	public long getUsage() {
		return usage;
	}

	public void setUsage(long usage) {
		this.usage = usage;
	}

	public byte getEquip() {
		return equip;
	}

	public void setEquip(byte equip) {
		this.equip = equip;
	}

	public String getEquipName() {
		return equipName == null ? "" : equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public byte getFunc() {
		return func;
	}

	public void setFunc(byte func) {
		this.func = func;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getStatus() {
		return status == null ? "" : status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMeans() {
		return means == null ? "" : means;
	}

	public void setMeans(String means) {
		this.means = means;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAvgValue() {
		return avgValue;
	}

	public void setAvgValue(String avgValue) {
		this.avgValue = avgValue;
	}

	public String toShareString() {
		String res = "&curValue=" + getDataContent()
				+ "&maxValue=" + maxValue + "&avgValue="
				+ avgValue + "&equip=" + equip + "&unit=" + unit;
		res = res.replace("/", "_"); // 转移字符
		return res;
	}

	public String shareContent() {
		String res = "当前的" + getEquipName() + "值为"
				+ getDataContent() + "（" + getStatus() + "），"
				+ getMeans();
		return res;
	}

	public List<String> getGasCode() {
		List<String> gascode = new ArrayList<String>();
		if (equip == 2) {
			gascode.add("6");

		}
		if (equip == 3) {
			gascode.add("7");
		}
		if (equip == 4) {
			gascode.add("8");
		}
		return gascode;
	}

	public List<String> getGasSta() {
		List<String> gascode = new ArrayList<String>();
		if (status.equals("正常")) {
			gascode.add("1");

		} else
			gascode.add("2");
		return gascode;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	@Override
	public String toString() {
		return "WeidouPo [equip=" + equip + ", equipName="
				+ equipName + ", func=" + func + ", funcName="
				+ funcName + ", version=" + version
				+ ", dataContent=" + dataContent + ", power="
				+ power + ", usage=" + usage + ", status="
				+ status + ", means=" + means + ", color="
				+ color + ", unit=" + unit + ", maxValue="
				+ maxValue + ", avgValue=" + avgValue
				+ ", isStart=" + isStart + "]";
	}

}
