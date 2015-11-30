package com.hw.smarthome.ui.home.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hw.smarthome.server.deal.DealWithHome;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.home.adapter.homeup.InitDatas;
import com.hw.util.DateUtils;

/**
 * @author 曾凡
 * @time 2014年7月4日 上午11:09:23
 */
public class HomeGasHistory implements Serializable {

	private static final long serialVersionUID = -2704998577808580331L;
	/** 传感器唯一标识ID */
	private String sensorId;
	/** 用户定义的传感器名称 */
	private String name;
	/** 开关状态 开/关 */
	private String[] switchStatuses;
	/** 一氧化碳 */
	private String[] cos;
	/** 甲烷 */
	private String[] ch4s;
	/** 过去的天数 "yyyy-mm-dd" */
	private String[] createTimes;
	private static List<String> names = new ArrayList<String>();
	private static List<String> dates = new ArrayList<String>();
	// private static List<String> units = new ArrayList<String>();

	static {

		names.add(UIConstant.HOME_URL_CO);
		names.add(UIConstant.HOME_URL_CH4);
		// units.add(UIConstant.HOME_UNIT_CO);
		// units.add(UIConstant.HOME_UNIT_CH4);
	}

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getSwitchStatuses() {
		return switchStatuses;
	}

	public void setSwitchStatuses(String[] switchStatuses) {
		this.switchStatuses = switchStatuses;
	}

	public String[] getCos() {
		return cos;
	}

	public void setCos(String[] cos) {
		this.cos = cos;
	}

	public String[] getCh4s() {
		return ch4s;
	}

	public void setCh4s(String[] ch4s) {
		this.ch4s = ch4s;
	}

	public String[] getCreateTimes() {
		return createTimes;
	}

	public void setCreateTimes(String[] createTimes) {
		this.createTimes = createTimes;
	}

	public String getShareContent() {
		dates.clear();
		String type = InitDatas.getHistoryType().get(sensorId);
		if (type.equals(InitDatas.DAY_30)) {
			for (Integer dat : DateUtils.get30DaysBefore()) {
				dates.add(dat + "");
			}
		} else if (type.equals(InitDatas.DAY_7)) {
			for (Integer dat : DateUtils.get7DaysBefore()) {
				dates.add(dat + "");
			}
		} else if (type.equals(InitDatas.HOUR_24)) {
			for (Integer dat : DateUtils.get24HoursBefore()) {
				dates.add(dat + "");
			}
		}

		StringBuilder sb = new StringBuilder("空气电台历史播报：");
		if (getCos() != null) {
			sb.append("[");
			sb.append(UIConstant.HOME_NAME_CO).append(" ")
					.append(UIConstant.HOME_UNIT_CO);
			sb.append("]{");
			for (int i = 0; i < dates.size(); i++) {
				sb.append(dates.get(i) + ":" + getCos()[i]
						+ "  ");
			}
			sb.append("}");
		}

		if (getCh4s() != null) {
			sb.append("[");
			sb.append(UIConstant.HOME_NAME_CH4).append(" ")
					.append(UIConstant.HOME_NAME_CH4);
			sb.append("]{");
			for (int i = 0; i < dates.size(); i++) {
				sb.append(dates.get(i) + ":" + getCh4s()[i]
						+ "  ");
			}
			sb.append("}");
		}

		return sb.toString().replace(" ", "");
	}

	/**
	 * 获取一个url
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年10月29日 上午11:33:13
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		List<String> data1 = new ArrayList<String>();
		List<String> data2 = new ArrayList<String>();
		// sb.append("&unit=" + units.toString());
		if (getCos() != null && getCos().length > 0) {
			for (String dat : getCos()) {
				data1.add(dat.trim().length() > 0 ? dat : "0");
			}
		}
		if (getCh4s() != null && getCh4s().length > 0) {
			for (String dat : getCh4s()) {
				data2.add(dat.trim().length() > 0 ? dat : "0");
			}
		}
		dates.clear();
		String type = InitDatas.getHistoryType().get(sensorId);
		if (type.equals(InitDatas.DAY_30)) {
			for (Integer dat : DateUtils.get30DaysBefore()) {
				dates.add(dat + "");
			}
		} else if (type.equals(InitDatas.DAY_7)) {
			for (Integer dat : DateUtils.get7DaysBefore()) {
				dates.add(dat + "");
			}
		} else if (type.equals(InitDatas.HOUR_24)) {
			for (Integer dat : DateUtils.get24HoursBefore()) {
				dates.add(dat + "");
			}
		}

		sb.append("name=" + names.toString());
		// sb.append("&unit=" + units.toString());
		sb.append("&dates=" + dates.toString());
		sb.append("&data1=" + data1.toString());
		sb.append("&data2=" + data2.toString());
		return sb.toString().replace(" ", "");
	}
}
