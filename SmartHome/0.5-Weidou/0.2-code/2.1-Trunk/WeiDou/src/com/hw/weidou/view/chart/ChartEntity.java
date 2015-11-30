package com.hw.weidou.view.chart;

/**
 * 传感器的明细的po
 * 
 * @author 曾凡
 * @time 2014年6月10日 上午10:09:10
 */
public class ChartEntity {
	/** 传感器名称 */
	private String name;
	/** 传感器单位 */
	private String unit;
	private float curValue;
	private float maxValue;
	private String status;
	private String means;
	private byte func;
	private String funcName;
	/** 要显示控件的宽 */
	private int width;
	/** 要显示控件的高 */
	private int height;

	private boolean isStart;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getCurValue() {
		return curValue;
	}

	public void setCurValue(float curValue) {
		this.curValue = curValue;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMeans() {
		return means;
	}

	public void setMeans(String means) {
		this.means = means;
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

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

}
