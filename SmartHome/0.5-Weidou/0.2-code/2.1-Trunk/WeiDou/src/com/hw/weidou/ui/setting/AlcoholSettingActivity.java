package com.hw.weidou.ui.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.hw.weidou.R;
import com.hw.weidou.parser.alcohol.AlcoholAlgorithm;

/**
 * @author 曾凡
 * @time 2015年3月4日 下午6:13:16
 */
public class AlcoholSettingActivity extends Activity implements
		OnClickListener {
	private BootstrapButton cancel;
	private BootstrapButton submit;
	private String cunrrentUnit = "mg/L";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_settings_alcohol);
		findView();
	}

	private void findView() {
		submit = (BootstrapButton) findViewById(R.id.settingSubmit);
		submit.setOnClickListener(this);
		cancel = (BootstrapButton) findViewById(R.id.settingCancel);
		cancel.setOnClickListener(this);
		// 根据ID找到RadioGroup实例
		RadioGroup group = (RadioGroup) findViewById(R.id.alcoholRadioGroup);
		
		// 绑定一个匿名监听器
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0,
					int arg1) {
				// 获取变更后的选中项的ID
				int radioButtonId = arg0
						.getCheckedRadioButtonId();
				// 根据ID获取RadioButton的实例
				RadioButton rb = (RadioButton) findViewById(radioButtonId);
				cunrrentUnit = rb.getText() + "";
			}
		});
	}

	@Override
	public void onClick(View v) {
		if (v == submit) {
			AlcoholAlgorithm.saveData(cunrrentUnit);
			finish();
		} else if (v == cancel) {
			finish();
		}

	}
}
