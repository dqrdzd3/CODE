package com.hw.smarthome.login.reg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.hw.smarthome.R;
import com.hw.smarthome.login.reg.util.RegUtils;
import com.hw.util.application.SHApplication;
import com.zf.view.TitleBarView;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.MinLength;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

public class RegisterInfoActivity extends Activity {
	private Activity mContext;
	private BootstrapButton submit;
	private TitleBarView mTitleBarView;
	private String phone;
	@NotEmpty(messageId = R.string.infor_null, order = 1)
	@MinLength(value = 6, messageId = R.string.login_reg_pass_min, order = 2)
	private EditText password;

	@NotEmpty(messageId = R.string.infor_null, order = 4)
	private EditText name;

	private SHApplication application;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		Bundle pass = intent.getExtras();
		phone = pass.getString(RegisterPhoneActivity.PHONE_KEY);

		setContentView(R.layout.login_register_userinfo_activity);
		mContext = this;

		findView();
		initTitleView();
		init();
	}

	private void findView() {
		mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);

		password = (EditText) findViewById(R.id.password);
		password.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
				13) });
		name = (EditText) findViewById(R.id.name);
		name.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
				13) });
		submit = (BootstrapButton) findViewById(R.id.register_complete);

		application = (SHApplication) getApplication();
		application.mTv = (TextView) findViewById(R.id.lat);
	}

	private void init() {
		submit.setOnClickListener(completeOnClickListener);
	}

	private void initTitleView() {
		mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE,
				View.GONE, View.GONE);
		mTitleBarView.setTitleText(R.string.title_register_info);
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

	private OnClickListener completeOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			boolean isValid = FormValidator
					.validate(mContext,
							new SimpleErrorPopupCallback(
									mContext, true));
			if (isValid) {
				RegUtils.submitReg(mContext, phone,
						password.getText() + "", name.getText()
								+ "", application);
			}
		}
	};

}
