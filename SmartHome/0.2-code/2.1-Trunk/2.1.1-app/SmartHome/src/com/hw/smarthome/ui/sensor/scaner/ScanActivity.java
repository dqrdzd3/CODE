package com.hw.smarthome.ui.sensor.scaner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.result.OnResultCaptureLinstener;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.service.SmartHomeService;

/**
 * 
 * 
 * 项目名称：common_test_demo 类名称：ScanActivity 类描述：二维码扫描 创建人：lijing 创建时间：2014-2-20
 * 上午11:40:09 修改人：lijing 修改时间：2014-2-20 上午11:40:09 修改备注：
 * 
 * @version
 * 
 */
public class ScanActivity extends CaptureActivity implements
		OnResultCaptureLinstener {
	public static final int RESULT_CAPTURE = 1001;

	@Override
	public void handleResult(String result) {
		// Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	}

	@Override
	protected void manualInput() {
		super.manualInput();
		finish();
	}

	private void handleResult() {

	}

	@Override
	public void onResultCapture(String result) {
		// TODO Auto-generated method stub
		super.onResultCapture(result);
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		/* SH01_01_01_03 看已发现设备 调用 */
		bundle.putString("name", ServerConstant.SH01_01_01_03);
		bundle.putString("2DBar", result);
		/* 第二个方法 */
		bundle.putInt("type", 1);
		intent.putExtra("action", bundle);
		intent.setAction(SmartHomeService.class.getName());
		this.sendBroadcast(intent);
		//
		// Intent intent = new Intent();
		// intent.putExtra("RESULT", result);
		// setResult(RESULT_CAPTURE, intent);
		finish();
	}

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		initInfo();

	}

	private void initInfo() {
		TextView hintView = (TextView) findViewById(com.google.zxing.client.R.id.status_view);
		hintView.setText("请将条码置于取景框内扫描。");
	}
}
