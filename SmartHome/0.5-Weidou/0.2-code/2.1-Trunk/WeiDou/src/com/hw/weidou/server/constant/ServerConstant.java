package com.hw.weidou.server.constant;

import java.io.File;

import android.os.Environment;

/**
 * 服务器通讯常量
 * 
 * @author 曾凡
 * @time 2014年6月26日 下午3:52:48
 */
public class ServerConstant {

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
	/** [WD_01_03] 分享 */
	public static final String WD_01_03 = "share/shareW.html";
	/** [WD_01_04] 解决方案 */
	public static final String WD_01_04 = "s007!getSolution";

	/** [WD_01_04] 威豆上报 */
	public static final String WD_01_01 = "w001!doCreateInfo";

	/** [WD_01_01_02]MIC口接受并解调传感器值信号 */
	public static final String WD_01_01_02 = "WD_01_01_02";

	/** 测试环境地址，不要删除提交 */
	// public static final String BASE_URI =
	// "http://192.168.111.186:8080/smart/";
	// public static final String BASE_URI = "http://121.40.94.44/smart/";
	public static final String BASE_URI = "http://weiguo.hanwei.cn/smart/";
	public static final String PUSH_URI = "";

	// 百度定位逆地理信息
	public static final String LOC_INFO = "http://api.map.baidu.com/geocoder/v2/?ak=RI36Dvkx20tD0Ooa3XXucVj1&output=json";

	public static final String SERVER_BASE_URI = BASE_URI
			+ "hwmobile/smart/";

	private static String getSDPath() {
		File sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
		return sdDir.toString();

	}

	public static final String UPLOAD_LOGS_ADDR = getSDPath()
			+ "/smarthome/crash/";
	public static final String UPDATE_URI = BASE_URI
			+ "apksplace/version_weidou.xml";
	public static final String HEADER_PARSE_REST_API_KEY = "X-Parse-REST-API-Key";
	public static final String HEADER_PARSE_APP_ID = "X-Parse-Application-Id";
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String SESSION_TOKEN = "sessionToken";

}
