package com.hw.weidou.ui.util;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;

import com.hw.util.Ln;
import com.hw.weidou.R;
import com.hw.weidou.StartActivity;
import com.hw.weidou.po.ParamMapPO;
import com.hw.weidou.server.constant.ServerConstant;
import com.hw.weidou.service.WeidouService;
import com.hw.weidou.ui.MainActivity;
import com.hw.weidou.ui.constant.UIConstant;
import com.hw.weidou.ui.setting.AboutFragment;
import com.hw.weidou.ui.setting.HelpCenterFragment;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

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

	/**
	 * 处理[选项]时的点击事件
	 * 
	 * @author 曾凡
	 * @param fManager
	 * @param context
	 * @param menuItems
	 * @param resideMenu
	 * @time 2014年6月9日 上午10:52:08
	 */
	public static void settingsOnClick(FragmentManager fManager,
			Context context, List<ResideMenuItem> menuItems,
			ResideMenu resideMenu) {
		if (menuItems.size() == 2) {
			ResideMenuItem itemAccountSetting = new ResideMenuItem(
					context, UIConstant.ACCOUNT_SETTING);
			ResideMenuItem itemPushSetting = new ResideMenuItem(
					context, UIConstant.PUSH_SETTING);
			ResideMenuItem itemhelpCenter = new ResideMenuItem(
					context, UIConstant.HELP_CENTER);
			ResideMenuItem itemAbout = new ResideMenuItem(
					context, UIConstant.ABOUT);

			itemAccountSetting
					.setOnClickListener(new SettingOnClick(
							fManager, resideMenu));
			itemPushSetting
					.setOnClickListener(new SettingOnClick(
							fManager, resideMenu));
			itemhelpCenter
					.setOnClickListener(new SettingOnClick(
							fManager, resideMenu));
			itemAbout.setOnClickListener(new SettingOnClick(
					fManager, resideMenu));

			// menuItems.add(itemAccountSetting);
			// menuItems.add(itemPushSetting);
			menuItems.add(itemhelpCenter);
			menuItems.add(itemAbout);
			resideMenu.setMenuItems(menuItems,
					ResideMenu.DIRECTION_LEFT);
		} else {
			menuItems.remove(3);
			menuItems.remove(2);
			resideMenu.setMenuItems(menuItems,
					ResideMenu.DIRECTION_LEFT);
		}

		return;
	}

	/**
	 * 配置中心点击事件
	 * 
	 * @author 曾凡
	 * @time 2014年6月9日 下午5:05:18
	 */
	private static class SettingOnClick implements
			OnClickListener {
		private ResideMenu resideMenu;
		private FragmentManager fManager;

		public SettingOnClick(FragmentManager fManager,
				ResideMenu resideMenu) {
			this.resideMenu = resideMenu;
			this.fManager = fManager;
		}

		@Override
		public void onClick(View v) {
			String text = ((ResideMenuItem) v).getText();

			if (UIConstant.HELP_CENTER.equals(text)) {
				HelpCenterFragment helpFragment = HelpCenterFragment
						.getInstance();
				String url = ServerConstant.SERVER_BASE_URI
						+ ServerConstant.SH01_05_02_02;
				helpFragment.setUrl(url);
				changeFragment(fManager, resideMenu,
						helpFragment);
			} else if (UIConstant.ABOUT.equals(text)) {
				changeFragment(fManager, resideMenu,
						AboutFragment.getInstance());
			}
			resideMenu.closeMenu();
		}
	}

	public static void toHome(Activity context) {
		context.startActivity(new Intent(context,
				MainActivity.class));
		context.finish();
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
			intent.setAction(WeidouService.class.getName());
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
			intent.setAction(WeidouService.class.getName());
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
			intent.setAction(WeidouService.class.getName());
			context.sendBroadcast(intent);
		} catch (Exception e) {
			Ln.e(e, "catch异常");
		}

	}

}
