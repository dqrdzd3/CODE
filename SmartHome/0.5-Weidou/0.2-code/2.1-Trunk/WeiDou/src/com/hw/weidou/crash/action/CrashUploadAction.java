package com.hw.weidou.crash.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.hw.util.DateUtils;
import com.hw.util.Ln;
import com.hw.util.NetUtil;
import com.hw.util.SafeAsyncTask;
import com.hw.weidou.crash.po.D005PO;
import com.hw.weidou.server.constant.ServerConstant;
import com.hw.weidou.server.util.ServerJsonUtil;

/**
 * @author 曾凡
 * @time 2014年8月12日 下午5:09:16
 */
public class CrashUploadAction {
	private final static String PARAM_URI = "d005!doSaveAdd";

	public static void sendCrashes() {

		File dir = new File(ServerConstant.UPLOAD_LOGS_ADDR);
		File files[] = null;
		if (dir.exists()) {
			files = dir.listFiles();
		}
		if (files != null) {
			for (File log : files) {
				sendCrash(log);
			}
		}

	}

	public static void sendCrash(final File log) {
		D005PO crash = getCrash(log);
		final Map<String, String> params = new HashMap<String, String>();
		params.put("ma002", crash.getMa002());
		params.put("ma003", crash.getMa003());
		params.put("ma004", crash.getMa004());
		params.put("ma005", crash.getMa005());
		params.put("ma006", crash.getMa006());
		params.put("ma007", crash.getMa007());
		params.put("ma008", crash.getMa008());
		params.put("ma009", crash.getMa009());
		params.put("ma010", crash.getMa010());
		params.put("ma011", "00"); // 异常
		SafeAsyncTask<Boolean> requestTask = new SafeAsyncTask<Boolean>() {
			public Boolean call() throws Exception {
				String url = null;
				boolean res = false;
				try {
					url = ServerConstant.SERVER_BASE_URI
							+ PARAM_URI;

					String json = NetUtil.http_post(url, params,
							null);
					/* 1是成功 0是失败 */
					String code = ServerJsonUtil.getAttr(
							json, "code");
					if (code != null && code.equals("1")) {
						res = true;
					} else {
						res = true;
					}
				} catch (Exception e) {
					res = false;
					Ln.e(e, "发送错误日志异常");
				}
				if (res) {
					log.delete();
					Ln.i("删除异常日志：" + log.getPath());
				}
				return true;
			}
		};
		requestTask.execute();
	}
	public static void sendCrash(D005PO crash) {
		
		final Map<String, String> params = new HashMap<String, String>();
		params.put("ma002", crash.getMa002());
		params.put("ma003", crash.getMa003());
		params.put("ma004", crash.getMa004());
		params.put("ma005", crash.getMa005());
		params.put("ma006", crash.getMa006());
		params.put("ma007", crash.getMa007());
		params.put("ma008", crash.getMa008());
		params.put("ma009", crash.getMa009());
		params.put("ma010", crash.getMa010());
		params.put("ma011", crash.getMa011());
		SafeAsyncTask<Boolean> requestTask = new SafeAsyncTask<Boolean>() {
			public Boolean call() throws Exception {
				String url = null;
				boolean res = false;
				try {
					url = ServerConstant.SERVER_BASE_URI
							+ PARAM_URI;

					String json = NetUtil.http_post(url, params,
							null);
					/* 1是成功 0是失败 */
					String code = ServerJsonUtil.getAttr(
							json, "code");
					if (code != null && code.equals("1")) {
						res = true;
					} else {
						res = true;
					}
				} catch (Exception e) {
					res = false;
					Ln.e(e, "发送错误日志异常");
				}
				if (res) {
		
				
				}
				return true;
			}
		};
		requestTask.execute();
	}

	@SuppressWarnings("finally")
	private static D005PO getCrash(File file) {
		D005PO crash = new D005PO();
		initCrashInfo(crash);

		FileInputStream fin = null;
		InputStreamReader inR = null;
		BufferedReader bfR = null;
		StringBuilder sb = new StringBuilder();
		try {
			fin = new FileInputStream(file);
			inR = new InputStreamReader(fin);
			bfR = new BufferedReader(inR);
			String temp = "";// 临时缓存 保存读取到的每一行记录

			while ((temp = bfR.readLine()) != null) {
				sb.append(temp).append("\r\n");
			}
		} catch (FileNotFoundException e) {
			Ln.e(e, "异常日志文件读取异常");
		} finally {
			try {
				if (fin != null) {
					fin.close();
				}
				if (inR != null) {
					inR.close();
				}
				if (bfR != null) {
					bfR.close();
				}
			} catch (IOException e) {
				Ln.e(e, "关闭流异常");
			}
			crash.setMa005(sb.toString());
			return crash;
		}
	}

	/**
	 * 初始化设备信息
	 * 
	 * @author 曾凡
	 * @param crash
	 * @time 2014年8月12日 下午5:25:38
	 */
	public static void initCrashInfo(D005PO crash) {
		crash.setMa010(getDeviceInfo());
		crash.setMa002(android.os.Build.MANUFACTURER);
		String sdkVersion = "SDK_INT: "
				+ android.os.Build.VERSION.SDK_INT;
		sdkVersion += ", VERSION.RELEASE: "
				+ android.os.Build.VERSION.RELEASE;
		crash.setMa003(sdkVersion);
		crash.setMa004("com.hw.weidou");
		crash.setMa006(android.os.Build.MANUFACTURER);
		crash.setMa007(android.os.Build.MODEL);
		crash.setMa008("crash");
		crash.setMa009(DateUtils.getCurrentTime());
	}

	private static String getDeviceInfo() {
		String phoneInfo = "Product: "
				+ android.os.Build.PRODUCT;
		phoneInfo += ", CPU_ABI: " + android.os.Build.CPU_ABI;
		phoneInfo += ", TAGS: " + android.os.Build.TAGS;
		phoneInfo += ", VERSION_CODES.BASE: "
				+ android.os.Build.VERSION_CODES.BASE;
		phoneInfo += ", MODEL: " + android.os.Build.MODEL;
		phoneInfo += ", SDK: "
				+ android.os.Build.VERSION.SDK_INT;
		phoneInfo += ", VERSION.RELEASE: "
				+ android.os.Build.VERSION.RELEASE;
		phoneInfo += ", DEVICE: " + android.os.Build.DEVICE;
		phoneInfo += ", DISPLAY: " + android.os.Build.DISPLAY;
		phoneInfo += ", BRAND: " + android.os.Build.BRAND;
		phoneInfo += ", BOARD: " + android.os.Build.BOARD;
		phoneInfo += ", FINGERPRINT: "
				+ android.os.Build.FINGERPRINT;
		phoneInfo += ", ID: " + android.os.Build.ID;
		phoneInfo += ", MANUFACTURER: "
				+ android.os.Build.MANUFACTURER;
		phoneInfo += ", USER: " + android.os.Build.USER;
		return phoneInfo;
	}

	
}
