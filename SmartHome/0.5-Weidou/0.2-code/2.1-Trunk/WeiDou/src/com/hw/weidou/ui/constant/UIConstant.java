package com.hw.weidou.ui.constant;

/**
 * @author 曾凡
 * @time 2014年6月9日 上午11:28:49
 */
public class UIConstant {

	public static final String HOME = "主页面";
	public static final String THEME = "更换主题";
	public static final String LOGOUT = "注销登录";
	public static final String DISCUSSAREA = "讨论区";
	public static final String FEEDBACK = "用户反馈";
	public static final String PROFILE = "用户信息";
	public static final String TEST = "测试";
	public static final String SETTINGS = "酒精配置";

	public static final String ACCOUNT_SETTING = "账号管理";
	public static final String PUSH_SETTING = "推送设置";
	public static final String DEV_LIST = "团队成员";
	public static final String HELP_CENTER = "帮助中心";
	public static final String ABOUT = "关于";

	public static final String HOME_NAME_CO2 = "二氧化碳";
	public static final String HOME_NAME_TEMPERATURE = "温度";
	public static final String HOME_NAME_HUMIDITY = "湿度";
	public static final String HOME_NAME_C6H6 = "苯";
	public static final String HOME_NAME_CH2O = "甲醛";
	/** 服务端解决方案要用 */
	public static final String HOME_CH2O_TYPE = "6";
	public static final String HOME_NAME_PM25 = "Pm2.5";
	public static final String HOME_NAME_CO = "一氧化碳";
	/** 服务端解决方案要用 */
	public static final String HOME_CO_TYPE = "8";
	public static final String HOME_NAME_CH4 = "天然气";
	public static final String HOME_NAME_ALCOHOL = "酒精";
	/** 服务端解决方案要用 */
	public static final String HOME_ALCOHOL_TYPE = "7";

	public static final String HOME_UNIT_NULL = "";
	public static final String HOME_UNIT_CO2 = "ppm";
	public static final String HOME_UNIT_TEMPERATURE = "℃";
	public static final String HOME_UNIT_HUMIDITY = "RH%";
	public static final String HOME_UNIT_C6H6 = "μg/m³";
	public static final String HOME_UNIT_CH2O = "mg/m³";
	public static final String HOME_UNIT_PM25 = "μg/m³";
	public static String HOME_UNIT_ALCOHOL = "mg/L";
	public static final String HOME_UNIT_CO = "ppm";
	public static final String HOME_UNIT_CH4 = "LEL";

	public static final String CO_CONTENT = "您所在的环境正常";
	public static final float CO_1 = 35;
	public static final String CO_1_CONTENT = "您所在的环境有轻微泄漏，请通风";
	public static final float CO_2 = 100;
	public static final String CO_2_CONTENT = "您所在的环境有轻微泄漏，请通风";
	public static final float CO_3 = 200;
	public static final String CO_3_CONTENT = "2-3小时后会有有轻微的头痛、头晕、恶心";
	public static final float CO_4 = 400;
	public static final String CO_4_CONTENT = "2小时内前额痛,3h 后将有生命危";
	public static final float CO_5 = 800;
	public static final String CO_5_CONTENT = "45分钟内头痛、恶心,2-3小时内死亡";
	public static final float CO_6 = 1600;
	public static final String CO_6_CONTENT = "20分钟内头痛、恶心,1小时内死亡";

	public static final String C0_CONTENT_STATUS_N = "正常";
	public static final String C0_CONTENT_STATUS_D = "危险";
	public static final String C0_CONTENT_STATUS_DD = "非常危险";

	/** 0.06～0.07mg/m3 (0.05～0.06ppm)时，儿童发生气喘病 */
	public static final String CH2O_NORMAL_CONTENT = "保持的不错，注意通风";
	public static final float CH2O_NORMAL_VALUE = 0.06f;
	public static final String CH2O_MIDDLE_CONTENT = "儿童发生轻微气喘";
	public static final float CH2O_MIDDLE_VALUE = 0.07f;
	public static final String CH2O_MIDDLE_UP_CONTENT = "可刺激眼睛，引起流泪";
	/** 当甲醛浓度在 0.12～1.2mg/m3时即 (0.1～1ppm) */
	public static final float CH2O_HIGH_VALUE = 0.5f;
	public static final String CH2O_HIGH_CONTENT = "可引起咽喉不适或疼痛";
	public static final float CH2O_DEAD_VALUE = 0.6f;
	public static final String CH2O_HIGH_UP_CONTENT = "能致支气管哮喘，基因突变，胎儿畸形";

	public static final String CH2O_CONTENT_STATUS_N = "正常";
	public static final String CH2O_CONTENT_STATUS_D = "危险";
	public static final String CH2O_CONTENT_STATUS_DD = "非常危险";

	public static final float CH2O_1600 = 1600;
	public static final String CH2O_1600_CONTENT = "20分钟内头痛、恶心,1小时内死亡";

	// public static final float ALCOHOL_20 = 20;
	// public static final float ALCOHOL_80 = 80;
	// 0.090 mg/L饮酒
	public static final float ALCOHOL_BASE_LOW = 0.090f;
	// 0.363 mg/L醉酒
	public static final float ALCOHOL_BASE_MIDDLE = 0.363f;
	// 0.090饮酒
	public static float ALCOHOL_LOW = 0.090f;
	// 0.363醉酒
	public static float ALCOHOL_MIDDLE = 0.363f;

	public static final String ALCOHOL_CONTENT_STATUS_N = "正常.";
	public static final String ALCOHOL_CONTENT_STATUS_D = "饮酒!";
	public static final String ALCOHOL_CONTENT_STATUS_DD = "醉酒!!";
	/*
	 * 只要大于0.090mg/L，即20mg/100ml，告诉他饮酒 大于0.363mg/L,即80mg/100ml，告诉他醉酒
	 */
	public static final String ALCOHOL_CONTENT = "没有酒精哦，本次检测值仅作为参考，如果饮酒请勿开车";
	public static final String ALCOHOL_CONTENT_LOW = "酒后驾驶，能被检测出来了";
	public static final String ALCOHOL_CONTENT_MIDDLE = "醉酒驾驶，被警察蜀黍捉到的后果很严重";

	public static final String SENSOR_LINK_TRUE = "已连接";
	public static final String SENSOR_LINK_FALSE = "未连接";

	/** 甲醛最大值 */
	public static final float CH2O_HIGH = 2;
	/** 酒精最大值 mg/L = 2 */
	public static final float ALCOHOL_MGL_HIGH = 2;
	/** mg/100mL = 400或440（跟当地标准有关) */
	public static final float ALCOHOL_MG100ML_ZH_HIGH = 440;
	public static final float ALCOHOL_MG100ML_US_HIGH = 400;
	/** BAC% = 0.4 */
	public static final float ALCOHOL_BACH_HIGH = 0.4f;
	/** BAC‰ = 4 */
	public static final float ALCOHOL_BACT_HIGH = 0.4f;
	/** g/L = 4 */
	public static final float ALCOHOL_GL_HIGH = 4;
	/** 一氧化碳1000PPM */
	public static final float CO_HIGH = 1000;

	public static final String HOME_ADDR = "file:///android_asset/web/view/progressbar/PercentageLoader.htm";

	public static final String HOME_ALCOHOL_ADDR = "file:///android_asset/web/ui/Alcohol2.htm";

	public static final String HINT_CONTENT = "未检测到威豆启动，请重新插入威豆";

	public static final String HINT_MIC_STATUS = "适配MIC口";

	public static final String HINT_MIC_CONTENT = "如果长时间没有适配完成，请重新插入威豆";
}
