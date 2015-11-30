package com.hw.smarthome.ui.home.adapter.homeup.po;

import java.util.Arrays;

/**
 * @author 曾凡
 * @time 2014年10月18日 下午2:03:18
 */
public class HomeHistoryViewPo {
	private int visibility;
	private String name;
	private String unit;
	private String[] values;

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

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

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((unit == null) ? 0 : unit.hashCode());
		result = prime * result + Arrays.hashCode(values);
		result = prime * result + visibility;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HomeHistoryViewPo other = (HomeHistoryViewPo) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		if (!Arrays.equals(values, other.values))
			return false;
		if (visibility != other.visibility)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HomeHistoryViewPo [visibility=" + visibility
				+ ", name=" + name + ", unit=" + unit
				+ ", values=" + Arrays.toString(values) + "]";
	}

}
