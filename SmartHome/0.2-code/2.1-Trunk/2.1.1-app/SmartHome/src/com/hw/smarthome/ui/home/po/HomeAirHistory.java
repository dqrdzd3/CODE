package com.hw.smarthome.ui.home.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.home.adapter.homeup.InitDatas;
import com.hw.util.DateUtils;

/**
 * @author 曾凡
 * @time 2014年7月4日 上午11:09:16
 */
public class HomeAirHistory implements Serializable {

	private static final long serialVersionUID = 1105458849110331255L;
	/** 传感器唯一标识ID */
	private String sensorId;
	/** 用户定义的传感器名称 */
	private String name;
	/** 温度 0-50℃ */
	private String[] temperatures;
	/** 湿度:0-100RH% */
	private String[] humiditys;
	/** 二氧化碳:0-5000ppm */
	private String[] co2s;
	/** PM2.5:0-1000ug/m3； */
	private String[] pm25s;
	/** 甲醛 */
	private String[] ch2os;
	/** 苯 */
	private String[] c6h6s;
	/** VOC */
	private String[] vocs;
	/** 过去的天数 "yyyy-mm-dd" */
	private String[] createTimes;

	private static List<String> names = new ArrayList<String>();
	private static List<String> dates = new ArrayList<String>();
	// private static List<String> units = new ArrayList<String>();

	static {

		names.add(UIConstant.HOME_URL_TEMPERATURE);
		names.add(UIConstant.HOME_URL_HUMIDITY);
		names.add(UIConstant.HOME_URL_CO2);
		names.add(UIConstant.HOME_URL_PM25);
		names.add(UIConstant.HOME_URL_VOC);

		// units.add(UIConstant.HOME_UNIT_TEMPERATURE);
		// units.add(UIConstant.HOME_UNIT_HUMIDITY);
		// units.add(UIConstant.HOME_UNIT_CO2);
		// units.add(UIConstant.HOME_UNIT_PM25);
		// units.add(UIConstant.HOME_UNIT_VOC);
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

	public String[] getTemperatures() {
		return temperatures;
	}

	public void setTemperatures(String[] temperatures) {
		this.temperatures = temperatures;
	}

	public String[] getHumiditys() {
		return humiditys;
	}

	public void setHumiditys(String[] humiditys) {
		this.humiditys = humiditys;
	}

	public String[] getCo2s() {
		return co2s;
	}

	public void setCo2s(String[] co2s) {
		this.co2s = co2s;
	}

	public String[] getPm25s() {
		return pm25s;
	}

	public void setPm25s(String[] pm25s) {
		this.pm25s = pm25s;
	}

	public String[] getCreateTimes() {
		return createTimes;
	}

	public void setCreateTimes(String[] createTimes) {
		this.createTimes = createTimes;
	}

	public String[] getCh2os() {
		return ch2os;
	}

	public void setCh2os(String[] ch2os) {
		this.ch2os = ch2os;
	}

	public String[] getC6h6s() {
		return c6h6s;
	}

	public void setC6h6s(String[] c6h6s) {
		this.c6h6s = c6h6s;
	}

	public String[] getVocs() {
		return vocs;
	}

	public void setVocs(String[] vocs) {
		this.vocs = vocs;
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
		StringBuilder sb = new StringBuilder("空气电台过去7天播报：");
		if (getTemperatures() != null
				&& getTemperatures().length == 7) {
			sb.append("[");
			sb.append(UIConstant.HOME_NAME_TEMPERATURE)
					.append(" ")
					.append(UIConstant.HOME_UNIT_TEMPERATURE);
			sb.append("]{");
			for (int i = 0; i < dates.size(); i++) {
				sb.append(dates.get(i) + ":"
						+ getTemperatures()[i] + "  ");
			}
			sb.append("}");
		}

		if (getHumiditys() != null && getHumiditys().length == 7) {
			sb.append("[");
			sb.append(UIConstant.HOME_NAME_HUMIDITY).append(" ")
					.append(UIConstant.HOME_UNIT_HUMIDITY);
			sb.append("]{");
			for (int i = 0; i < dates.size(); i++) {
				sb.append(dates.get(i) + ":" + getHumiditys()[i]
						+ "  ");
			}
			sb.append("}");
		}

		if (getCo2s() != null && getCo2s().length == 7) {
			sb.append("[");
			sb.append(UIConstant.HOME_NAME_CO2).append(" ")
					.append(UIConstant.HOME_UNIT_CO2);
			sb.append("]{");
			for (int i = 0; i < dates.size(); i++) {
				sb.append(dates.get(i) + ":" + getCo2s()[i]
						+ "  ");
			}
			sb.append("}");
		}

		if (getPm25s() != null && getPm25s().length == 7) {
			sb.append("[");
			sb.append(UIConstant.HOME_NAME_PM25).append(" ")
					.append(UIConstant.HOME_UNIT_PM25);
			sb.append("]{");
			for (int i = 0; i < dates.size(); i++) {
				sb.append(dates.get(i) + ":" + getPm25s()[i]
						+ "  ");
			}
			sb.append("}");
		}
		if (getVocs() != null && getVocs().length == 7) {
			sb.append("[");
			sb.append(UIConstant.HOME_NAME_VOC).append(" ")
					.append(UIConstant.HOME_UNIT_VOC);
			sb.append("]{");
			for (int i = 0; i < dates.size(); i++) {
				sb.append(dates.get(i) + ":" + getVocs()[i]
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
		List<String> data3 = new ArrayList<String>();
		List<String> data4 = new ArrayList<String>();
		List<String> data5 = new ArrayList<String>();
		if (getTemperatures() != null
				&& getTemperatures().length > 0) {
			for (String dat : getTemperatures()) {
				data1.add(dat.trim().length() > 0 ? dat : "0");
			}
		}
		if (getHumiditys() != null && getHumiditys().length > 0) {
			for (String dat : getHumiditys()) {
				data2.add(dat.trim().length() > 0 ? dat : "0");
			}
		}
		if (getCo2s() != null && getCo2s().length > 0) {
			for (String dat : getCo2s()) {
				data3.add(dat.trim().length() > 0 ? dat : "0");
			}
		}
		if (getPm25s() != null && getPm25s().length > 0) {
			for (String dat : getPm25s()) {
				data4.add(dat.trim().length() > 0 ? dat : "0");
			}
		}
		if (getVocs() != null && getVocs().length > 0) {

			for (String dat : getVocs()) {
				data5.add(dat.trim().length() > 0 ? dat : "0");
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
		sb.append("&dates=" + dates.toString());
		sb.append("&data1=" + data1.toString());
		sb.append("&data2=" + data2.toString());
		sb.append("&data3=" + data3.toString());
		sb.append("&data4=" + data4.toString());
		sb.append("&data5=" + data5.toString());
		// sb.append("&unit=" + units.toString());
		return sb.toString().replace(" ", "");
	}

}
