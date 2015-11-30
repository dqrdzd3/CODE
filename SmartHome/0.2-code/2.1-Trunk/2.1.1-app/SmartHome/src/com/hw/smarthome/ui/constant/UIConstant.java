package com.hw.smarthome.ui.constant;

/**
 * @author 曾凡
 * @time 2014年6月9日 上午11:28:49
 */
public class UIConstant {
	public static final String SENSOR_ONLINE = "在线";
	public static final String SENSOR_OFFLINE = "不在线";

	public static final String HOME_NAME_CO2 = "二氧化碳";
	public static final String HOME_NAME_TEMPERATURE = "温度";
	public static final String HOME_NAME_HUMIDITY = "湿度";
	public static final String HOME_NAME_C6H6 = "苯";
	public static final String HOME_NAME_CH2O = "甲醛";
	public static final String HOME_NAME_PM25 = "Pm2.5";
	public static final String HOME_NAME_VOC = "VOC";
	public static final String HOME_NAME_CO = "一氧化碳";
	public static final String HOME_NAME_CH4 = "天然气";
	public static final String WAIT = "加载中";

	public static final String HOME_URL_CO2 = "co2";
	public static final String HOME_URL_TEMPERATURE = "temperature";
	public static final String HOME_URL_HUMIDITY = "humidity";
	public static final String HOME_URL_C6H6 = "c6h6";
	public static final String HOME_URL_CH2O = "ch2o";
	public static final String HOME_URL_PM25 = "pm25";
	public static final String HOME_URL_VOC = "voc";
	public static final String HOME_URL_CO = "co";
	public static final String HOME_URL_CH4 = "ch4";

	public static final String HOME_UNIT_NULL = "";
	public static final String HOME_UNIT_CO2 = "ppm";
	public static final String HOME_UNIT_TEMPERATURE = "℃";
	public static final String HOME_UNIT_HUMIDITY = "RH%";
	public static final String HOME_UNIT_C6H6 = "μg/m³";
	public static final String HOME_UNIT_CH2O = "μg/m³";
	public static final String HOME_UNIT_PM25 = "μg/m³";
	public static final String HOME_UNIT_VOC = "";
	public static final String HOME_UNIT_CO = "ppm";
	public static final String HOME_UNIT_CH4 = "LEL";

	public static final String HOME = "主页面    ";
	public static final String THEME = "更换主题";
	public static final String LOGOUT = "注销登录";
	public static final String DISCUSSAREA = "我要吐槽";
	public static final String FEEDBACK = "用户反馈";
	public static final String SHOPPING = "在线商城";
	public static final String PROFILE = "用户信息";
	public static final String SENSOR = "配置向导";
	public static final String QUESTIONS = "我要吐槽";
	public static final String DIY = " DIY课堂";
	public static final String MONITOR = "免费监测";
	public static final String BUSINESS = "我要创业";

	public static final String SENSOR_ONE_KEY = "设备管理";
	public static final String SENSOR_REG_SUCESS = "绑定成功";
	public static final String SENSOR_WIFI_SUCESS = "恭喜您配网成功";
	public static final String SENSOR_HARDWARE_OPEN_HINT = "配置设备需要打开手机/平板的Wi-Fi";
	public static final String SENSOR_HARDWARE_POWER_GAS_HINT = "1) 如上图所示，向右旋转插入转接头，并确认牢固<br>2) 将报警器插入电源，如果正常会发光";
	public static final String SENSOR_HARDWARE_POWER_AIR_HINT = "1) usb下口朝下，插入[空气电台]背后的电源口<br>2) 点击[空气电台]正面的圆形按钮启动设备";

	public static final String SENSOR_HARDWARE_GAS_HINT = "1) 用针戳一下[Radio]的<b>i</b><br>2) 如果[Air]的<b>i</b>会闪烁，则说明设备进入配网状态<br>3) 确认手机已连接Wi-Fi，点击<b>[下一步]</b>";
	public static final String SENSOR_HARDWARE_AIR_HINT = "1) 点击<b>圆形按钮</b>切换到<b>云形状</b>的界面<br>2) 在此页面双击<b>圆形按钮</b>进入Wi-Fi配网模式";

	public static final String SENSOR_WIFI_GAS_HINT = "1) 输入Wi-Fi密码，点击下方<b>[设备配网]</b>按钮<br>2) <b>[Air]的<b>i</b>常亮说明报警器已经连接上了路由器<br><font color='red'>*<font>配网失败请重启设备和APP，并重新绑定设备";

	public static final String SENSOR_WIFI_GAS_SUCCESS_HINT = "1) 报警器插电后有三分钟时间预热，期间<b>不能</b>进行燃气检测<br>2) 如果报警器的[环形指示灯]变为呼吸灯(亮到暗的逐渐变化)状态，说明报警器进入可检测状态";

	public static final String SENSOR_WIFI_AIR_HINT = "1) 输入Wi-Fi密码，点击下方<b>[设备配网]</b>按钮<br>2) 如果配网成功，点阵屏的Wi-Fi图表会消失<br><font color='red'>*<font>配网失败请重启设备和APP，并重新绑定设备";

	public static final String SETTINGS = "配置中心";

	public static final String ACCOUNT_SETTING = "账号管理";
	public static final String PUSH_SETTING = "推送设置";
	public static final String DEV_LIST = "团队成员";
	public static final String HELP_CENTER = "帮助中心";
	public static final String ABOUT = "关于";

	public static final String ACCOUNT = "<font color='red'>*</font>账号";
	public static final String ACCOUNT_PASS = "<font color='red'>*</font>密码";
	public static final String ACCOUNT_PASSA = "<font color='red'>*</font>确认密码";

	// 节假日
	public static final String NEWYEAR = "元旦";
	public static final String COMMON = "普通日";

	/*
	 * 解决方案对应的设备类型 bgn
	 * 0:温度，1:湿度，2:co2，3:pm2.5，4:voc，5:c6h6，6:ch2o,7:酒精，8:co,9:ch4
	 */
	public static final int SOLUTION_TEMPERATURE = 0;
	public static final int SOLUTION_HUMIDITY = 1;
	public static final int SOLUTION_CO2 = 2;
	public static final int SOLUTION_PM25 = 3;
	public static final int SOLUTION_VOC = 4;

	public static final int SOLUTION_CO = 8;
	public static final int SOLUTION_CH4 = 9;

	public static final int SOLUTION_NULL = 999;

	public static final int SOLUTION_STATUS_LOW = 0;
	public static final int SOLUTION_STATUS_MIDDLE = 1;
	public static final int SOLUTION_STATUS_HIGH = 2;

	public static final String SOLUTION_GAS = "含量";
	public static final String SOLUTION_STATUS_LOW_NAME = "低";
	public static final String SOLUTION_STATUS_MIDDLE_NAME = "中";
	public static final String SOLUTION_STATUS_MIDDLE2_NAME = "适宜";
	public static final String SOLUTION_STATUS_MIDDLE3_NAME = "轻微污染";
	public static final String SOLUTION_STATUS_MIDDLE4_NAME = "泄漏";
	public static final String SOLUTION_STATUS_HIGH_NAME = "高";
	public static final String SOLUTION_STATUS_HIGH3_NAME = "污染";
	public static final String SOLUTION_STATUS_HIGH4_NAME = "严重泄漏";
	public static final String SOLUTION_STATUS_NORMAL = "正常";

	/** 温度 */
	public static final String SOLUTION_TEM_HIGH = "热";
	public static final String SOLUTION_TEM_MIDDLE = "舒适";
	public static final String SOLUTION_TEM_LOW = "冷";
	/** 湿度 */
	public static final String SOLUTION_HUM_HIGH = "潮湿";
	public static final String SOLUTION_HUM_MIDDLE = "适宜";
	public static final String SOLUTION_HUM_LOW = "干燥";
	/** CO2 */
	public static final String SOLUTION_CO2_HIGH = "缺氧";
	public static final String SOLUTION_CO2_MIDDLE = "沉闷";
	public static final String SOLUTION_CO2_LOW = "清新";
	/** Pm2.5 */
	public static final String SOLUTION_PM25_HIGH = "爆表";
	public static final String SOLUTION_PM25_MIDDLE = "污浊";
	public static final String SOLUTION_PM25_LOW = "干净";
	/** VOC */
	public static final String SOLUTION_VOC_HIGH = "有害";
	public static final String SOLUTION_VOC_MIDDLE = "宜居";
	public static final String SOLUTION_VOC_LOW = "清爽";

}
