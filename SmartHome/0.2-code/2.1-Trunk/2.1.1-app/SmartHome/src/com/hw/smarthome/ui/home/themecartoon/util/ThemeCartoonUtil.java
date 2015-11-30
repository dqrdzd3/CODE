package com.hw.smarthome.ui.home.themecartoon.util;

import java.util.LinkedList;
import java.util.List;

import com.hw.smarthome.R;
import com.hw.smarthome.po.SensorAirDetail;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.SensorGasDetail;
import com.hw.smarthome.ui.home.themecartoon.po.CartoonInfo;
import com.hw.util.Ln;

/**
 * @author 曾凡
 * @time 2014年7月31日 下午4:09:16
 */
public class ThemeCartoonUtil {

	public static final int[] IMAGE_IDS = {
			R.drawable.ui_home_001, R.drawable.ui_home_002,
			R.drawable.ui_home_003, R.drawable.ui_home_004,
			R.drawable.ui_home_005, R.drawable.ui_home_006,
			R.drawable.ui_home_007, R.drawable.ui_home_008,
			R.drawable.ui_home_009, R.drawable.ui_home_010,
			R.drawable.ui_home_011, R.drawable.ui_home_012 };
	private static final int CO2_NORMAL = 800;
	private static final int CO2_MIDDLE = 1200;

	private static final int TEMPERATURE_THRESHOLD = 25;
	private static final int HUMIDITY_THRESHOLD = 45;
	public static final int PM25_THRESHOLD = 200;
	public static final int PM25_VERY_GOOD = 35;
	public static final int PM25_GOOD = 75;
	public static final int PM25_WEAK = 115;
	public static final int PM25_BAD = 150;
	public static final int PM25_VERY_BAD = 250;
	public static final int PM25_HIGH_THRESHOLD = 400;

	public static final int VOC_NORMAL = 20;
	public static final int VOC_MIDDLE = 40;

	public static final int CH4_THRESHOLD = 7;
	// public static final int CH4_HIGH_THRESHOLD = 7;

	public static final int CO_THRESHOLD = 100;

	// public static final int CO_HIGH_THRESHOLD = 100;
	/**
	 * 返回所有的值字符串
	 * @author 曾凡
	 * @param detail
	 * @return
	 * @time 2015年7月6日 下午4:37:27
	 */
	public static String getInfoStrs(SensorDetail detail) {
		StringBuilder sb = new StringBuilder();
		for (CartoonInfo info : getCartoonInfos(detail)) {
			sb.append(info.getTalkText());
			sb.append("  ");
		}
		return sb.toString();
	}

	public static List<CartoonInfo> getCartoonInfos(
			SensorDetail detail) {
		List<SensorDetail> detailList = new LinkedList<SensorDetail>();
		detailList.add(detail);
		return getCartoonInfos(detailList);
	}

	/**
	 * 根据现在获取到的传感器内容得到卡通需要说的话
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年7月31日 下午4:19:54
	 */
	public static List<CartoonInfo> getCartoonInfos(
			List<SensorDetail> detailList) {
		/* 初始化卡通链表 */
		List<CartoonInfo> resList = new LinkedList<CartoonInfo>();
		// CartoonInfo tempInfo = new CartoonInfo();
		// tempInfo.setImageId(IMAGE_IDS[0]);
		// tempInfo.setSensorName("hello");
		// tempInfo.setSensorValue("hello");
		// tempInfo.setTalkText("您好，接下来我会为您进行播报");
		// resList.add(tempInfo);
		resList.addAll(getCartoonInfo(detailList));

		return resList;
	}

	@SuppressWarnings("finally")
	private static List<CartoonInfo> getCartoonInfo(
			List<SensorDetail> detailList) {
		List<CartoonInfo> cartoonInfos = new LinkedList<CartoonInfo>();
		CartoonInfo tempInfo = null;
		int tempImage = 0;

		try {
			if (detailList.size() == 0) {
				tempInfo = new CartoonInfo();
				tempImage = IMAGE_IDS[11];
				tempInfo.setImageId(tempImage);
				tempInfo.setTalkText("当前传感器没有上传数据，请检查传感器是否联网成功。");
				cartoonInfos.add(tempInfo);
			} else {
				for (SensorDetail detail : detailList) {
					initDetail(detail, tempInfo, cartoonInfos);
				}
			}
		} catch (Exception e) {
			Ln.e(e, "卡通说话的内容数据转换错误");
		} finally {
			if (cartoonInfos.size() == 0) {
				tempInfo = new CartoonInfo();
				tempImage = IMAGE_IDS[11];
				tempInfo.setImageId(tempImage);
				tempInfo.setTalkText("设备没有数据上传，您家的网线被猫咪踢掉了吗。");
				cartoonInfos.add(tempInfo);
			}
			return cartoonInfos;
		}
	}

	private static void initDetail(SensorDetail detail,
			CartoonInfo tempInfo, List<CartoonInfo> cartoonInfos) {
		int tempImage = 0;
		/* 具体传感器的值，例如二氧化碳、温度、湿度和pm2.5 */
		String sensorName = null;
		String text = "";
		String sensorId = null;
		Double tempValue = 0.0;
		SensorAirDetail air = detail.getAir();
		SensorGasDetail gas = detail.getGas();
		/* 判断空气质量的内容 */
		if (air != null) {
			sensorId = air.getSensorId();
			if (sensorId != null && !"".equals(sensorId)) {
				if (air.getTemperature() != null
						&& !"".equals(air.getTemperature())) {
					tempInfo = new CartoonInfo();
					text = "";
					sensorName = "温度:";
					text += sensorName;
					tempValue = Double.valueOf(air
							.getTemperature());
					if (tempValue <= (TEMPERATURE_THRESHOLD + 3)
							&& tempValue >= (TEMPERATURE_THRESHOLD - 3)) {
						text += "主人，您的小窝待着实在太舒服了，我都不想出门了。";
						tempImage = IMAGE_IDS[1];
					} else if (tempValue >= TEMPERATURE_THRESHOLD) {
						text += "主淫！果果要发芽啦，热死了咩！";
						tempImage = IMAGE_IDS[6];
					} else if (tempValue < TEMPERATURE_THRESHOLD) {
						text += "主人啊，我都快冻感冒了啦，快给我拿件厚衣服";
						tempImage = IMAGE_IDS[4];
					}
					tempInfo.setImageId(tempImage);
					tempInfo.setSensorName(sensorName);
					tempInfo.setSensorValue(air.getCo2());
					tempInfo.setTalkText(text);
					cartoonInfos.add(tempInfo);
				}
				// text = name + ":";
				if (air.getHumidity() != null
						&& !"".equals(air.getHumidity())) {
					tempInfo = new CartoonInfo();
					text = "";
					sensorName = "湿度:";
					text += sensorName;
					tempValue = Double
							.valueOf(air.getHumidity());
					if (tempValue < (HUMIDITY_THRESHOLD + 20)
							&& tempValue > (HUMIDITY_THRESHOLD - 20)) {
						text += "小主人，多亏了这里很合适的湿度呦，我的皮肤现在超级棒！";
						tempImage = IMAGE_IDS[1];
					} else if (tempValue <= HUMIDITY_THRESHOLD) {
						text += "主人，您的小窝太干燥了，你看我的皮肤都干裂了，过来帮我打开加湿器呀！";
						tempImage = IMAGE_IDS[10];
					} else if (tempValue > HUMIDITY_THRESHOLD) {
						text += "主人，湿度太大了，你看看我都萎靡不振的，都没精神跟你说话了。";
						tempImage = IMAGE_IDS[11];
					}
					tempInfo.setImageId(tempImage);
					tempInfo.setSensorName(sensorName);
					tempInfo.setSensorValue(air.getCo2());
					tempInfo.setTalkText(text);
					cartoonInfos.add(tempInfo);
				}
				if (air.getCo2() != null
						&& !"".equals(air.getCo2())) {
					tempInfo = new CartoonInfo();
					text = "";
					sensorName = "二氧化碳:";
					text += sensorName;
					tempValue = Double.valueOf(air.getCo2());
					if (tempValue > CO2_MIDDLE) {
						text += "主银啊，浓度让我有点呼吸不顺畅啦！赶快来给我打开窗户。";
						tempImage = IMAGE_IDS[4];
					} else if ((tempValue <= CO2_MIDDLE)
							& (tempValue > CO2_NORMAL)) {
						text += "浓度有点高了，注意室内空气流通，植物在晚上也会释放二氧化碳。";
						tempImage = IMAGE_IDS[7];
					} else if (tempValue <= CO2_NORMAL) {
						text += "浓度正常，可以自由呼吸哦。";
						tempImage = IMAGE_IDS[1];
					}
					tempInfo.setImageId(tempImage);
					tempInfo.setSensorName(sensorName);
					tempInfo.setSensorValue(air.getCo2());
					tempInfo.setTalkText(text);
					cartoonInfos.add(tempInfo);
				}
				// text = name + ":";
				if (air.getPm25() != null
						&& !"".equals(air.getPm25())) {
					tempInfo = new CartoonInfo();
					text = "";
					sensorName = "PM2.5:";
					text += sensorName;
					tempValue = Double.valueOf(air.getPm25());
					if (tempValue <= PM25_THRESHOLD) {
						text += "主人，您的小窝清新宜人，沁人心脾，好好享受生活吧。";
						tempImage = IMAGE_IDS[1];
					} else if (tempValue > PM25_THRESHOLD
							&& tempValue <= PM25_HIGH_THRESHOLD) {
						text += "还没有报表，请在适当的时候开窗透透气。";
						tempImage = IMAGE_IDS[4];
					} else if (tempValue > PM25_HIGH_THRESHOLD) {
						text += "主人，您的小窝烟雾缭绕，尘飞土扬，是时候打开空气净化器啦！";
						tempImage = IMAGE_IDS[10];
					}
					tempInfo.setImageId(tempImage);
					tempInfo.setSensorName(sensorName);
					tempInfo.setSensorValue(air.getPm25());
					tempInfo.setTalkText(text);
					cartoonInfos.add(tempInfo);
				}
				if (air.getVoc() != null
						&& !"".equals(air.getVoc())) {
					tempInfo = new CartoonInfo();
					text = "";
					sensorName = "VOC:";
					text += sensorName;
					tempValue = Double.valueOf(air.getVoc());
					if (tempValue <= VOC_NORMAL) {
						text += "小窝很健康哦。";
						tempImage = IMAGE_IDS[1];
					} else if ((tempValue > VOC_NORMAL)
							&& (tempValue <= VOC_MIDDLE)) {
						text += "中度风险，容易出现感官刺激，头痛、乏力和昏昏欲睡。";
						tempImage = IMAGE_IDS[4];
					} else if (tempValue > VOC_MIDDLE) {
						text += "高危环境，可导致基因毒性和致癌性，影响染色体致其畸变。";
						tempImage = IMAGE_IDS[10];
					}
					tempInfo.setImageId(tempImage);
					tempInfo.setSensorName(sensorName);
					tempInfo.setSensorValue(air.getVoc());
					tempInfo.setTalkText(text);
					cartoonInfos.add(tempInfo);
				}

			}
			/* 判断燃气的内容 */
			if (gas != null) {
				sensorId = gas.getSensorId();
				if (sensorId != null && !"".equals(sensorId)) {
					if (gas.getCh4() != null
							&& !"".equals(gas.getCh4())) {
						tempInfo = new CartoonInfo();
						text = "";
						sensorName = "天然气";
						text += sensorName;
						tempValue = Double.valueOf(gas.getCh4());
						if (tempValue < CH4_THRESHOLD) {
							text += "正常";
							tempImage = IMAGE_IDS[3];
						} else if (tempValue >= CH4_THRESHOLD) {
							text += "泄漏，危险";
							tempImage = IMAGE_IDS[6];
						}
						tempInfo.setImageId(tempImage);
						tempInfo.setSensorName(sensorName);
						tempInfo.setSensorValue(gas.getCh4());
						tempInfo.setTalkText(text);
						cartoonInfos.add(tempInfo);
					}
					if (gas.getCo() != null
							&& !"".equals(gas.getCo())) {
						tempInfo = new CartoonInfo();
						text = "";
						sensorName = "一氧化碳";
						text += sensorName;
						tempValue = Double.valueOf(gas.getCo());
						if (tempValue < CO_THRESHOLD) {
							text += "正常";
							tempImage = IMAGE_IDS[3];
						} else if (tempValue >= CO_THRESHOLD) {
							text += "泄漏，危险";
							tempImage = IMAGE_IDS[11];
						}
						tempInfo.setImageId(tempImage);
						tempInfo.setSensorName(sensorName);
						tempInfo.setSensorValue(gas.getCo());
						tempInfo.setTalkText(text);
						cartoonInfos.add(tempInfo);
					}
				}
			}
		}

	}
}
