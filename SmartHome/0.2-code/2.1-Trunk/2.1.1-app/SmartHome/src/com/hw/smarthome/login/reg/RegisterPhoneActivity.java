package com.hw.smarthome.login.reg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hw.smarthome.R;
import com.hw.smarthome.login.ProtocolActivity;
import com.hw.util.UIUtil;
import com.zf.view.TextURLView;
import com.zf.view.TitleBarView;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.MinLength;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

public class RegisterPhoneActivity extends Activity {

	private Activity mContext;
	private TitleBarView mTitleBarView;
	private TextURLView regProtocol;
	private CheckBox regChk;
	@NotEmpty(messageId = R.string.infor_null)
	@MinLength(value = 9, messageId = R.string.phone_wrong, order = 3)
	private TextView phone;
	private Button next;
	protected static String PHONE_KEY = "PHONE_KEY";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_register_phone_activity);
		mContext = this;
		findView();
		initTitleView();
		init();
	}

	private void findView() {
		mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);
		regProtocol = (TextURLView) findViewById(R.id.regProtocol);
		regChk = (CheckBox) findViewById(R.id.regChk);
		phone = (TextView) findViewById(R.id.et_phoneNumber);
		phone.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
				13) });
		next = (Button) findViewById(R.id.btn_next);
	}

	private void init() {
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				next();
			}
		});
		regProtocol.setText(R.string.tv_protocol_url);
		regProtocol.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(mContext,
						ProtocolActivity.class);
				startActivityForResult(i, 0);
			}
		});
	}

	private void initTitleView() {
		mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE,
				View.GONE, View.GONE);
		mTitleBarView.setBtnLeft(
				R.drawable.boss_unipay_icon_back, R.string.back);
		mTitleBarView.setTitleText(R.string.title_phoneNumber);
		mTitleBarView
				.setBtnLeftOnclickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				});
	}

	private void next() {
		boolean isValid = FormValidator.validate(mContext,
				new SimpleErrorPopupCallback(mContext, true));
		if (!regChk.isChecked()) {
			UIUtil.ToastMessage(mContext, "请仔细查看本协议并同意");
			return;
		}
		if (isValid) {
			Bundle bundle = new Bundle();
			bundle.putString(PHONE_KEY, phone.getText() + "");
			Intent intent = new Intent(this,
					RegisterInfoActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}

	@Override
	protected void onActivityResult(int requestCode,
			int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == ProtocolActivity.AGREE) {
			regChk.setChecked(true);
		}
		if (resultCode == ProtocolActivity.DISAGREE) {
			regChk.setChecked(false);
		}
	}

}
