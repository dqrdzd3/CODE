package com.hw.weidou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.hw.util.Ln;
import com.hw.util.PreferenceUtil;
import com.hw.weidou.crash.action.CrashUploadAction;
import com.hw.weidou.guide.GuideActivity;
import com.hw.weidou.ui.MainActivity;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		final View view = View.inflate(this,
				R.layout.activity_start, null);
		setContentView(view);
		/* 曾凡 20140813 自动上传异常日志 */
		CrashUploadAction.sendCrashes();

		/* 渐变动画 */
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

	private void enterMain() {
		Intent intent = new Intent();

		if (PreferenceUtil.getIsShowGuide(this)) {
			intent.setClass(this, GuideActivity.class);

		} else {
			intent.setClass(this, MainActivity.class);
		}
		Ln.i("enterMain");
		startActivity(intent);
		finish();
	}

}
