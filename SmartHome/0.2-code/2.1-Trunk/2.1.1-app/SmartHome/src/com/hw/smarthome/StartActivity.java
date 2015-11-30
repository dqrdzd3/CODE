package com.hw.smarthome;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.hw.smarthome.crash.action.CrashUploadAction;
import com.hw.smarthome.guide.GuideActivity;
import com.hw.smarthome.login.LoginActivity;
import com.hw.smarthome.server.deal.DealWithAccount;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.MainActivity;
import com.hw.util.Ln;
import com.hw.util.PreferenceUtil;
import com.hw.util.WindowTools;

public class StartActivity extends Activity {
	public static Context context;
	@Override
	protected void onCreate(Bundle arg0) {
		context = this;
		super.onCreate(arg0);

		final View view = View.inflate(this,
				R.layout.activity_start, null);
		setContentView(view);
		WindowTools.initSystemBar(getWindow());
		/* 曾凡 20140813 自动上传异常日志 */
		CrashUploadAction.sendCrashes();
		if (!isServiceWorked()) {
			startService(new Intent(this, SmartHomeService.class));
		}
		// String s = null;
		// s.toCharArray();
		if (DealWithAccount.getAccountAndPwd(StartActivity.this)[0]
				.trim().length() > 0) {
			Intent intent = new Intent();
			intent.setClass(StartActivity.this,
					MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			finish();
		}
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f,
				1.0f);
		alphaAnimation.setDuration(2000);
		view.startAnimation(alphaAnimation);
		alphaAnimation
				.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(
							Animation animation) {

					}

					@Override
					public void onAnimationRepeat(
							Animation animation) {

					}

					@Override
					public void onAnimationEnd(
							Animation animation) {

						enterMain();

					}
				});

	}

	public boolean isServiceWorked() {
		ActivityManager myManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		ArrayList<RunningServiceInfo> runningService = (ArrayList<RunningServiceInfo>) myManager
				.getRunningServices(30);
		for (int i = 0; i < runningService.size(); i++) {
			if (runningService.get(i).service.getClassName()
					.toString()
					.equals(SmartHomeService.class.getName())) {
				return true;
			}
		}
		return false;
	}

	private void enterMain() {
		Intent intent = new Intent();

		if (PreferenceUtil.getIsShowGuide(this)) {
			intent.setClass(this, GuideActivity.class);

		} else {
			intent.setClass(this, LoginActivity.class);
		}
		Ln.i("enterMain");

		startActivity(intent);
		finish();
	}

}
