package com.hw.smarthome.server.constant;

import java.io.File;

import com.hw.util.DateUtils;

import android.os.Environment;

/**
 * 服务器通讯常量
 * 
 * @author 曾凡
 * @time 2014年6月26日 下午3:52:48
 */
public class ServerConstant {
	/** [SH01_01_01_02] 发现设备 上传传感器 */
	public static final String SH01_01_01_02 = "010102";
	/** [SH01_01_01_03] 查看已发现设备 */
	public static final String SH01_01_01_03 = "010103";
	/** [SH01_01_01_04] 添加/删除设备 */
	public static final String SH01_01_01_04 = "010104";
	/** [SH01_01_01_05] 配网 */
	public static final String SH01_01_01_05 = "010105";
	/** [SH01_01_01_06] 关闭配网 */
	public static final String SH01_01_01_06 = "010106";
	/** [SH01_01_01_07] 修改设备信息 */
	public static final String SH01_01_01_07 = "010107";

	/** [SH01_02_01_03] 过去7天历史信息 */
	public static final String SH01_02_01_03 = "01020103";
	/** [SH01_02_01_04] 过去24小时历史信息 */
	public static final String SH01_02_01_04 = "01020104";
	/** [SH01_02_01_04] 过去30小时历史信息 */
	public static final String SH01_02_01_05 = "01020105";
	/** 历史类型：小时 */
	public static final int HISTORY_TYPE_HOUR = 1;
	/** 历史类型：天 */
	public static final int HISTORY_TYPE_DAY = 2;
	/** [SH01_02_01_02] 查看历史报警信息 */
	public static final String SH01_02_01_02 = "a001!doHisAlarmList";
	/** [SH01_02_02_01] 手动更新 */
	public static final String SH01_02_02_01 = "01020201";
	/** [SH01_01_01_01] 登录 */
	public static final String SH01_03_02_04 = "030204";

	/** [SH01_03_01_01] 查看用户信息 */
	public static final String SH01_03_01_01 = "030101";
	/** [SH01_03_01_02] 编辑账号 */
	public static final String SH01_03_01_02 = "030102";
	/** [SH01_03_02_01] 注册账号 */
	public static final String SH01_03_02_01 = "030201";
	/** [SH01_03_02_02] 忘记密码 */
	public static final String SH01_03_02_02 = "030202";
	/** [SH01_03_02_03] 修改密码 */
	public static final String SH01_03_02_03 = "030203";
	/** [SH01_04_02] 查看推送信息 */
	public static final String SH01_04_02 = "010402";

	/** [SH01_05_02_02] 帮助 */
	public static final String SH01_05_02_02 = "help";
	/** 调查问卷 弹出 */
	public static final String QUES = "http://www.sojump.com/jq/5499554.aspx";
	/** [SH01_05_01_01_01] 讨论区主题 */
	public static final String SH01_05_01_01_01 = "s004!doView";
	/** [SH01_05_01_02_01] 留言板.提交留言板信息 */
	public static final String SH01_05_01_02_01 = "s006!doAddMessage";
	/** [SH01_05_01_02_02] 留言板.留言板列表信息 */
	public static final String SH01_05_01_02_02 = "s006!doListMessage";
	/** [SH01_05_01_01_02] 讨论区.提交留言信息 */
	public static final String SH01_05_01_01_02 = "s005!doReply";
	/** [SH01_05_01_01_03] 讨论区.讨论区主题详情 */
	public static final String SH01_05_01_01_03 = "s005!doGetReplayList";
	/** [SH01_02_03_01] 查看知识和解决方案 */
	public static final String SH01_02_03_01 = "s007!getAllSolution";
	/** [SH01_02_03_02] 获取单条解决方案数据 */
	public static final String SH01_02_03_02 = "s007!getSolutionsByCondition";
	/** [SH01_02_03_02_01] 查看在线商城 */
	public static final String SH01_02_03_02_01 = "d002!doGetWebsite?TYPE=5";
	/** [SH01_02_03_03_02] DIY课堂 */
	public static final String SH01_02_03_02_02 = "d002!doGetWebsite?TYPE=3";
	/** [SH01_02_03_04_03] 我要创业 */
	public static final String SH01_02_03_02_03 = "d002!doGetWebsite?TYPE=6";
	/** [SH01_06_02_01] 手动控制 */
	public static final String SH01_06_02_01 = "control!controlDevice";

	/** [SH01_06_04_01] 添加场景 */
	public static final String SH01_06_04_01 = "scene!insertScene";
	/** [SH01_06_04_02] 修改场景 */
	public static final String SH01_06_04_02 = "scene!updateScene";
	/** [SH01_06_04_03] 查询场景 */
	public static final String SH01_06_04_03 = "scene!getAllScene";
	/** [SH01_06_04_04] 删除场景 */
	public static final String SH01_06_04_04 = "scene!deleteScene";

	/** 排名 */
	public static final String RANKING = "rank!doRank";

	/** 下载流 */
	public static final String DOWNLOADSTREAM = "u001!doDownLoad";

	public static final String SH01_02_04 = "share/share.html";
	/** 分享过去数据 */
	public static final String SHAREH = "share/share.html";

	/** 解决办法 */
	public static final String SOLUTION = "s007!getSolution";

	/** 更新设备程序 */
	public static final String UPGRADE = "d002!checkUpgrade";
	/** 查询更新硬件的信息 */
	public static final String UPGRADEINFO = "d002!checkUpgradeInfo";

	public static final String BASE_URI = "http://weiguo.hanwei.cn/smart/";

	/** 闫威 */
//	 public static final String BASE_URI =
//	 "http://192.168.111.186:8080/smart/";

	public static final String PUSH_URI = "weiguo.hanwei.cn";

	// 百度定位逆地理信息
	public static final String LOC_INFO = "http://api.map.baidu.com/geocoder/v2/?ak=RI36Dvkx20tD0Ooa3XXucVj1&output=json";
	public static final String LOC_BY_IP_INFO = "http://api.map.baidu.com/location/ip?ak=RI36Dvkx20tD0Ooa3XXucVj1";
	public static final String SERVER_BASE_URI = BASE_URI
			+ "hwmobile/smart/";

	private static String getSDPath() {
		File sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
		return sdDir.toString();
		// if (hasSdCard()) {
		// return sdDir.toString();
		// } else {
		// return "/sdcard";
		// }

	}

	// private static Boolean hasSdCard() {
	// String status = Environment.getExternalStorageState();
	// if (status.equals(Environment.MEDIA_MOUNTED)) {
	// return true;
	// } else {
	// return false;
	// }
	// }

	public static final String UPDATE_URI = BASE_URI
			+ "apksplace/version.xml";
	public static final String HEADER_PARSE_REST_API_KEY = "X-Parse-REST-API-Key";
	public static final String HEADER_PARSE_APP_ID = "X-Parse-Application-Id";
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String SESSION_TOKEN = "sessionToken";

	public static final String APP_FILE_ROOT = getSDPath() + "/";
	public static final String UPLOAD_LOGS_ADDR = APP_FILE_ROOT
			+ "crash/";
	/** 街景 截图保存位置 */
	public static String SHOT_PIC_LOC = APP_FILE_ROOT
			+ "DCIM/Camera/AirRadio_"
			+ DateUtils.getCurrentDateTime() + ".jpg";
	public static final String SHOT_CAM_TEMP_BG_LOC = APP_FILE_ROOT
			+ "DCIM/Camera/tempBg.jpg";
	public static final String SHOT_CAM_TEMP_LAYOUT_LOC = APP_FILE_ROOT
			+ "DCIM/Camera/tempLayout.jpg";
	public static String SHOT_CAM_LOC = APP_FILE_ROOT
			+ "DCIM/Camera/Cam_ScreenShot_"
			+ DateUtils.getCurrentDateTime() + ".jpg";
	/** 地理定位广播 */
	public static final String BROAD_LOC = "com.hw.loc";

	public static final String APPKEY = "677c2c4b00e0";
	public static final String APPSECRET = "a349ae105d2e00ddb5bfa672bf3ffd23";
	
	public static String SHOP_URL = "http://m.airradio.cn/Mall.asp";
	public static String DIY_URL = "http://m.airradio.cn/DIY.asp";
	public static String JOIN_URL = "http://m.airradio.cn/join.asp";

}
