package com.hw.smarthome.login;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.hw.smarthome.R;
import com.hw.smarthome.crash.action.CrashUploadAction;
import com.hw.smarthome.crash.po.D005PO;
import com.hw.smarthome.login.reg.RegisterPhoneActivity;
import com.hw.smarthome.login.server.LoginServerReq;
import com.hw.smarthome.po.UserPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithAccount;
import com.hw.smarthome.ui.MainActivity;
import com.hw.util.PreferenceUtil;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

/**
 * 修改 20150506 曾凡 改为QQ样式
 * 
 * @author 闫威
 * @time 2014年6月10日 下午3:34:37
 */
public class LoginActivity extends FragmentActivity {
	private View loginView;
	@NotEmpty(messageId = R.string.infor_null, order = 1)
	private EditText account;
	@NotEmpty(messageId = R.string.infor_null, order = 2)
	private EditText pass;
	private TextView changepwTextView;
	private TextView regTextView;
	private ImageView loginAvatar;

	public static LoginActivity mContext;

	// private CheckBox cb;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 曾凡测试异常主动上报
		// String u = null;
		// u.getBytes();
		Bundle bundle = getIntent().getExtras();
		if (bundle != null && bundle.getString("first") != null) {
			Intent intent = new Intent(LoginActivity.this,
					RegActivity.class);
			startActivity(intent);
		}
		setContentView(R.layout.login_activity);
		mContext = this;
		PreferenceUtil.saveIsShowGuide(mContext, false);
		findViews();
		init();
	}

	private void findViews() {
		loginView = findViewById(R.id.rl_user);
		loginAvatar = (ImageView) findViewById(R.id.loginAvatar);
		account = (EditText) findViewById(R.id.account);
		account.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
				13) });
		account.setHint(getString(R.string.login_tel));
		pass = (EditText) findViewById(R.id.et_password);
		pass.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
				16) });
		pass.setHint(getString(R.string.login_forgetpw_passwd_hint));
		changepwTextView = (TextView) findViewById(R.id.changepw);
		regTextView = (TextView) findViewById(R.id.reg);

		Animation anim = AnimationUtils.loadAnimation(mContext,
				R.anim.login_anim);
		anim.setFillAfter(true);
		loginView.startAnimation(anim);

	}

	private void init() {
		account.setOnClickListener(new LoginButtonListener());
		pass.setOnClickListener(new LoginButtonListener());
		changepwTextView
				.setOnClickListener(new LoginButtonListener());
		regTextView
				.setOnClickListener(new LoginButtonListener());
	}

	private class LoginButtonListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.changepw: {
				Intent intent = new Intent();
				intent.putExtra("usertel", account.getText()
						.toString());
				intent.setClass(LoginActivity.this,
						ForgetPassActivity.class);

				startActivity(intent);
			}
				break;
			case R.id.reg: {
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this,
						RegisterPhoneActivity.class);
				startActivity(intent);

			}
				break;

			case R.id.account: {
				DealWithAccount.clearAccount(mContext);
			}
				break;
			case R.id.pass: {
				DealWithAccount.clearAccount(mContext);
			}
				break;
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		account.setText(DealWithAccount
				.getAccountAndPwd(mContext)[0]);
		pass.setText(DealWithAccount.getAccountAndPwd(mContext)[1]);
		UserPO userPO = DealWithAccount.getUser(this);
		if (userPO != null && userPO.getMa017() != null) {
			loginAvatar.setImageBitmap(BitmapFactory
					.decodeByteArray(userPO.getMa017(), 0,
							userPO.getMa017().length));
		}
		// if (account.getText().toString().trim().length() > 0) {
		// cb.setChecked(true);
		// } else {
		// cb.setChecked(false);
		// }
	}

	public void handleLogin(View view) {
		/* 验证逻辑 */
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		// imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

		boolean isValid = FormValidator.validate(this,
				new SimpleErrorPopupCallback(this, true));
		if (isValid) {

			if (pass.getText().toString().length() > 5) {
				// AjaxParams paramMap = new AjaxParams();
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("PHONE", account.getText()
						.toString());
				paramMap.put("PASSWORD", pass.getText()
						.toString());

				// if (cb.isChecked()) {

				// } else {
				// DealWithAccount.clearAccount(mContext);
				// }

				LoginServerReq req = new LoginServerReq(
						mContext, findViewById(R.id.parentView));
				req.requestServerGetLogin(paramMap,
						"u001!doLogin",
						ServerConstant.SH01_03_02_04);

				DealWithAccount.saveAccountAndPwd(mContext,
						account.getText().toString(), pass
								.getText().toString());
				// 增加设备信息
				D005PO po = new D005PO();
				CrashUploadAction.initCrashInfo(po);
				po.setMa011("10");
				po.setMa008(account.getText().toString());
				CrashUploadAction.sendCrash(po);

				// onAuthenticationResult(true);
			} else {

				pass.setError("密码长度不对");
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {
			dialog();
			return false;
		}
		return false;
	}

	protected void dialog() {

		new SweetAlertDialog(mContext,
				SweetAlertDialog.WARNING_TYPE)
				.setTitleText("确定要退出吗?")
				.setCancelText("取消")
				.setConfirmText("确认")
				.showCancelButton(true)
				.setCancelClickListener(
						new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetAlertDialog sDialog) {
								sDialog.dismiss();
							}
						})
				.setConfirmClickListener(
						new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetAlertDialog sDialog) {
								sDialog.dismiss();
								Intent startMain = new Intent(
										Intent.ACTION_MAIN);
								startMain
										.addCategory(Intent.CATEGORY_HOME);
								startMain
										.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								startActivity(startMain);
							}
						}).show();
	}

	/**
	 * 测试时无论对错都是可以跳转 FIXME 正式使用时改正
	 * 
	 * @author 曾凡
	 * @param result
	 * @time 2014年5月15日 下午3:53:19
	 */
	public static void onAuthenticationResult(
			final boolean result) {

		if (result) {
			Intent intent = new Intent();
			intent.setClass(mContext, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mContext.startActivity(intent);

			mContext.finish();
		} else {

		}
	}

}
