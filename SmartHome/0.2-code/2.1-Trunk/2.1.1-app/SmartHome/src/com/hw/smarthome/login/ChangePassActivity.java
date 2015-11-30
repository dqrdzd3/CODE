package com.hw.smarthome.login;

import java.util.HashMap;
import java.util.Map;

import com.hw.smarthome.R;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithAccount;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.sensor.SensorOneKeyFragment.MyReceiver;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.MD5Util;
import com.hw.util.WindowTools;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassActivity extends FragmentActivity {
	public static ChangePassActivity mContext;
	private EditText pass1,pass2,pass3;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.login_changepw);
		WindowTools.initSystemBar(getWindow());
		
		pass1 = (EditText)findViewById(R.id.chgPw);
		pass2 = (EditText)findViewById(R.id.chgPwNew);
		pass3 = (EditText)findViewById(R.id.pwNewRepeat);
		
		// 注册广播接收器
				MyReceiver receiver = new MyReceiver();
				IntentFilter filter = new IntentFilter();
				filter.addAction(SmartHomeService.class.getName());
				this.registerReceiver(receiver, filter);
				/* 登陆这个页面后从服务器获取最新的列表 */
				MainAcUtil.send2Service(this,
						ServerConstant.SH01_03_02_03, 0);
	}
	public void changepw(View view) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);  
		 
		 if (pass1.getText().toString().trim().length()>0 && pass2.getText().toString().trim().length()>0 && pass3.getText().toString().trim().length()>0) {
			if(pass2.getText().toString().equals(pass3.getText().toString())){
				/* 登陆这个页面后从服务器获取最新的列表 */
				Map<String, String> paramMap = new HashMap<String, String>();
			
				  paramMap.put("MA009", pass2.getText().toString());
				  String testname = DealWithAccount.getUser(mContext).getMa008();
				  paramMap.put("MA010", MD5Util.md5(MD5Util.md5(testname)+pass2.getText().toString()));
				MainAcUtil.send2Service(this,
						ServerConstant.SH01_03_02_03, 0,paramMap);
			}
			
		}

	}
	
	
	/**
	 * 获取广播数据验证是否正确
	 */
	public class MyReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getBundleExtra("action");
			if (ServerConstant.SH01_03_02_03.equals(bundle
					.getString("name"))) {
				if (bundle.getBoolean("result")) {  //返回正确

					Toast.makeText(ChangePassActivity.this, "更新成功", Toast.LENGTH_LONG).show();
				}else {
					Toast.makeText(ChangePassActivity.this, "修改失败", Toast.LENGTH_LONG).show();
				}
			}

		}
	}
}
