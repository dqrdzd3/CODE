package com.hw.smarthome.login;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import cn.smssdk.SMSSDK;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.hw.smarthome.R;
import com.hw.smarthome.login.server.LoginServerReq;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.util.NetUtil;
import com.hw.util.UIUtil;
import com.zf.view.TitleBarView;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.MaxLength;
import eu.inmite.android.lib.validations.form.annotations.MinLength;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

public class ForgetPassActivity extends FragmentActivity {
	public static ForgetPassActivity mContext;
	private TitleBarView mTitleBarView;

	@NotEmpty(messageId = R.string.infor_null)
	@MaxLength(value = 13, messageId = R.string.phone_wrong, order = 2)
	@MinLength(value = 9, messageId = R.string.phone_wrong, order = 3)
	private EditText telTextView;
	private BootstrapButton sendcode;
	// @NotEmpty(messageId = R.string.infor_null, order = 3)
	// private EditText pass2;
	@NotEmpty(messageId = R.string.infor_null, order = 4)
	@MaxLength(value = 8, messageId = R.string.login_forget_code, order = 5)
	@MinLength(value = 4, messageId = R.string.login_forget_code, order = 6)
	private EditText codeEditText;
	
	@NotEmpty(messageId = R.string.infor_null, order = 7)
	@MaxLength(value = 16, messageId = R.string.login_reg_pass_max, order = 8)
	@MinLength(value = 6, messageId = R.string.login_reg_pass_min, order = 9)
	private EditText pass1;

	private int recLen = 60; // 倒计时60s

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.login_forgetpw_activity);
		mContext = this;
		findView();
		initTitleView();
		init();
	}

	private void findView() {
		mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);
		telTextView = (EditText) findViewById(R.id.phone);
		codeEditText = (EditText) findViewById(R.id.code);

		pass1 = (EditText) findViewById(R.id.chgPwNew);
		// pass2 = (EditText) findViewById(R.id.pwNewRepeat);
		sendcode = (BootstrapButton) findViewById(R.id.fpSendcode);
	}

	private void init() {
		sendcode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (NetUtil.getNetworkType(mContext) == 0) {
					UIUtil.ToastMessage(mContext, "请打开网络...");
					return;
				}
				if (!NetUtil.isNetworkConnected(mContext)) {
					UIUtil.ToastMessage(mContext, "请检查网络状态...");
					return;
				}
				if (telTextView.length() < 9
						|| telTextView.length() > 13) {
					UIUtil.ToastMessage(mContext, "请检查手机号是否正确");
					return;
				}
				recLen = 60;
				Message msgMessage = handler.obtainMessage(1);
				handler.sendMessageDelayed(msgMessage, 1000);
				sendcode.setEnabled(false);
				telTextView.setEnabled(false);

				runOnUiThread(new Runnable() {
					public void run() {
						try {
							SMSSDK.initSDK(mContext,
									ServerConstant.APPKEY,
									ServerConstant.APPSECRET);
							/* FIXME 有国外用户再说 */
							SMSSDK.getVerificationCode("86",
									(telTextView.getText() + "")
											.trim());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

	}

	private void initTitleView() {
		mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE,
				View.GONE, View.GONE);
		mTitleBarView
				.setTitleText(R.string.login_forgetpw_title);
		mTitleBarView.setBtnLeft(
				R.drawable.boss_unipay_icon_back, R.string.back);
		mTitleBarView
				.setBtnLeftOnclickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();

					}
				});
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (getIntent().getExtras() != null) {
			telTextView.setText(getIntent().getExtras()
					.getString("usertel"));
		}
	}

	final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				recLen--;
				sendcode.setText(recLen + "秒");
				if (recLen > 0) {
					Message msgMessage = handler
							.obtainMessage(1);
					handler.sendMessageDelayed(msgMessage, 1000);
				} else {
					sendcode.setEnabled(true);
					sendcode.setText("获取验证码");
				}
				break;

			}
		}
	};

	/**
	 * 修改密码
	 * 
	 * @param view
	 */
	public void doChange(View view) {
		/* TODO 验证逻辑 */
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		boolean isValid = FormValidator.validate(this,
				new SimpleErrorPopupCallback(this, true));

		if (isValid) {
			if (pass1.getText().toString().trim().length() < 6) {
				UIUtil.ToastMessage(ForgetPassActivity.this,
						"新密码长度小于六位");
				return;
			}
			// if (pass1.getText().toString()
			// .equals(pass2.getText().toString())) {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("PHONE", telTextView.getText()
					.toString());
			paramMap.put("PASSWORD", pass1.getText().toString());
			// paramMap.put("YZM", codeEditText.getText()
			// .toString());
			paramMap.put("ZONE", "86");
			paramMap.put("CODE", codeEditText.getText()
					.toString());
			/* 登陆这个页面后从服务器获取最新的列表 */
			LoginServerReq req = new LoginServerReq(mContext);
			// req.requestServer(paramMap, "u001!doResetPw",
			req.requestServer(paramMap, "u001!shareSDKYzm",
					ServerConstant.SH01_03_02_02);
			// } else {
			// UIUtil.ToastMessage(mContext, "确认密码与设置的密码不一样");
			// return;
			// }

		}

	}

}
