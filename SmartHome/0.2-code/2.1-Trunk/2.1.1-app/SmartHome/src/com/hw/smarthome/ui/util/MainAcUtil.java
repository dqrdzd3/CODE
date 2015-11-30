package com.hw.smarthome.ui.util;

import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hw.smarthome.R;
import com.hw.smarthome.po.ParamMapPO;
import com.hw.smarthome.po.SensorCtrlDetail;
import com.hw.smarthome.po.SoluChkSumPO;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.home.HomeFragment;
import com.hw.util.Ln;
import com.special.ResideMenu.ResideMenu;

/**
 * MainUI的帮助类
 * 
 * @author 曾凡
 * @time 2014年6月9日 上午10:50:12
 */
public class MainAcUtil {

	/**
	 * 更换MainUI中的内容
	 * 
	 * @author 曾凡
	 * @param fragmentManager
	 * @param resideMenu
	 * @param targetFragment
	 * @time 2014年6月9日 下午3:41:26
	 */
	public static void changeFragment(
			FragmentManager fragmentManager,
			ResideMenu resideMenu, Fragment targetFragment) {
		if (!targetFragment.isAdded()) {
			int backStackCount = fragmentManager
					.getBackStackEntryCount();

			for (int i = 0; i < backStackCount; i++) {

				fragmentManager.popBackStack();

			}
			resideMenu.clearIgnoredViewList();
			fragmentManager
					.beginTransaction()
					.replace(R.id.main_fragment, targetFragment,
							"fragment")
					.setTransitionStyle(
							FragmentTransaction.TRANSIT_FRAGMENT_FADE)
					.commit();
		}
	}

	public static void changeFragmentWithBack(
			FragmentManager fragmentManager,
			Fragment targetFragment) {
		if (!targetFragment.isAdded()) {
			int backStackCount = fragmentManager
					.getBackStackEntryCount();

			for (int i = 0; i < backStackCount; i++) {

				fragmentManager.popBackStack();

			}

			fragmentManager
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.main_fragment, targetFragment,
							"fragment")
					.setTransitionStyle(
							FragmentTransaction.TRANSIT_FRAGMENT_FADE)
					.commit();
		}
	}

	public static void changeFragment(
			FragmentManager fragmentManager,
			Fragment targetFragment) {
		if (!targetFragment.isAdded()) {
			int backStackCount = fragmentManager
					.getBackStackEntryCount();

			for (int i = 0; i < backStackCount; i++) {

				fragmentManager.popBackStack();

			}

			fragmentManager
					.beginTransaction()
					.replace(R.id.main_fragment, targetFragment,
							"fragment")
					.setTransitionStyle(
							FragmentTransaction.TRANSIT_FRAGMENT_FADE)
					.commit();
		}
	}

	public static void toHome(MainActivity mainActivity) {
		mainActivity.startActivity(new Intent(mainActivity,
				HomeFragment.class));
	}

	/**
	 * 发送Activity到Service的广播
	 * 
	 * @author 曾凡
	 * @param context
	 * @param name
	 *            例如：ServerConstant.SH01_01_01_02
	 * @param type
	 *            接口内的方法类型，如果只有一个接口方法则type为0
	 * @time 2014年7月1日 下午10:51:30
	 */
	public static void send2Service(Context context,
			String name, int type, String sensorId) {
		try {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("name", name);
			bundle.putString("sensorId", sensorId);
			bundle.putInt("type", type);
			intent.putExtra("action", bundle);
			intent.setAction(MainActivity.class.getName());
			context.sendBroadcast(intent);
		} catch (Exception e) {
			Ln.e(e, "catch异常");
		}
	}

	/**
	 * 发送Activity到Service的广播
	 * 
	 * @author 曾凡
	 * @param context
	 * @param name
	 *            例如：ServerConstant.SH01_01_01_02
	 * @param type
	 *            接口内的方法类型，如果只有一个接口方法则type为0
	 * @time 2014年7月1日 下午10:51:30
	 */
	public static void send2Service(Context context,
			String name, int type) {
		try {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("name", name);
			bundle.putInt("type", type);
			intent.putExtra("action", bundle);
			intent.setAction(MainActivity.class.getName());
			context.sendBroadcast(intent);
		} catch (Exception e) {
			Ln.e(e, "发送到Service异常");
		}

	}

	public static void send2Service(Context context,
			String name, int type, Map<String, String> paraMap) {
		try {
			ParamMapPO po = new ParamMapPO();
			po.setParamMap(paraMap);
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("name", name);
			bundle.putInt("type", type);
			bundle.putSerializable("param", po);
			intent.putExtra("action", bundle);
			intent.setAction(MainActivity.class.getName());
			// intent.setAction(SmartHomeService.class.getName());
			context.sendBroadcast(intent);
		} catch (Exception e) {
			Ln.e(e, "catch异常");
		}
	}

	public static void send2Activity(Context context,
			String name, int type, boolean result, String message) {
		try {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putBoolean("result", result);
			bundle.putString("name", name);
			bundle.putString("message", message);
			bundle.putInt("type", type);
			intent.putExtra("action", bundle);
			intent.setAction(SmartHomeService.class.getName());
			context.sendBroadcast(intent);
		} catch (Exception e) {
			Ln.e(e, "catch异常");
		}
	}

	/**
	 * 返回Activity
	 * 
	 * @author 曾凡
	 * @param context
	 * @param result
	 *            是否可以更新页面值
	 * @time 2014年7月1日 下午10:59:30
	 */
	public static void send2Activity(Context context,
			String name, int type, boolean result) {
		try {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putBoolean("result", result);
			bundle.putString("name", name);
			bundle.putInt("type", type);
			intent.putExtra("action", bundle);
			intent.setAction(SmartHomeService.class.getName());
			context.sendBroadcast(intent);
		} catch (Exception e) {
			Ln.e(e, "catch异常");
		}

	}

	/**
	 * 
	 * 函 数 名：send2ActivityData 功能描述：直接返回数据给ui 输入参数：
	 * 
	 * @param context
	 * @param name
	 *            接口名
	 * @param type
	 *            类型
	 * @param result
	 *            是否直接显示
	 * @param data
	 *            json数据 创建人：lijing 创建时间：2014-7-7 上午9:47:08 修改人： 修改时间： 修改原因描述：
	 */
	public static void send2ActivityData(Context context,
			String name, int type, boolean result, String data) {
		try {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putBoolean("result", result);
			bundle.putString("name", name);
			bundle.putInt("type", type);
			bundle.putString("data", data);
			intent.putExtra("action", bundle);
			// intent.putExtras(bundle);
			intent.setAction(SmartHomeService.class.getName());
			context.sendBroadcast(intent);
		} catch (Exception e) {
			Ln.e(e, "catch异常");
		}

	}

	/**
	 * 控制设备Action
	 * 
	 * @author 曾凡
	 * @param context
	 * @param name
	 * @param type
	 * @param sensorId
	 * @time 2015年4月22日 下午6:11:11
	 */
	public static void send2CtrlService(Context context,
			String name, SensorCtrlDetail ctrl) {
		try {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putSerializable("ctrl", ctrl);
			bundle.putString("name", name);
			intent.putExtra("action", bundle);
			intent.setAction(MainActivity.class.getName());
			context.sendBroadcast(intent);
		} catch (Exception e) {
			Ln.e(e, "catch异常");
		}
	}

	/**
	 * 解决方案
	 * 
	 * @author 曾凡
	 * @param context
	 * @param name
	 * @param ctrl
	 * @time 2015年8月19日 下午2:11:10
	 */
	public static void send2SuluService(Context context,
			String name, SoluChkSumPO po) {
		try {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putSerializable("soluChkSum", po);
			bundle.putString("name", name);
			intent.putExtra("action", bundle);
			intent.setAction(MainActivity.class.getName());
			context.sendBroadcast(intent);
		} catch (Exception e) {
			Ln.e(e, "catch异常");
		}
	}
}
