package com.hw.smarthome.service.wifi;

import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.MulticastLock;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.easylink.android.utils.EasyLinkConstants;
import com.hw.smarthome.R;
import com.mxchip.ftc_service.FTC_Listener;
import com.mxchip.ftc_service.FTC_Service;

/**
 * EasyLink 3.1
 * 
 * @author 曾凡
 * @time 2015年9月10日 下午2:14:50
 */
public class DealWithWiFiNew {

	private static DealWithWiFiNew instance;
	private Context mContext;

	private DealWithWiFiNew(Context context) {
		mContext = context;
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		multicastLock = wifiManager
				.createMulticastLock("multicast.test");
	}

	public static DealWithWiFiNew getInstance(Context context) {
		return (instance == null) ? new DealWithWiFiNew(context)
				: instance;
	}

	public boolean isCalled = false;
	public boolean isCalled2 = false;
	private static MulticastLock multicastLock;

	private FTC_Service ftcService = null;

	public void sendPacketData2(String ssid, String passwd,
			int ipAddr) throws Exception {

		if (!isCalled2) {
			isCalled2 = true;
			ftcService = FTC_Service.getInstence();
			multicastLock.acquire();
			ftcService.transmitSettings(ssid, passwd, ipAddr,
					ftc_Listener);
		} else {
			stopPacketData2();
			multicastLock.release();
		}
	}

	private FTC_Listener ftc_Listener = new FTC_Listener() {
		@Override
		public void onFTCfinished(Socket s, String data) {
			Log.e("====", "onFTCfinished()");
			if (!"".equals(data)) {
				JSONObject jsonObj;
				try {
					jsonObj = new JSONObject(data);
					String deviceName = jsonObj.getString("N");
					EasyLinkConstants.findedDevicesList.put(
							deviceName.toString(),
							data.toString());
					EasyLinkConstants.findedDevicesSocketList
							.put(deviceName.toString(), s);
					// Message msg = new Message();
					// msg.what = EasyLinkConstants.DEVICE_FIND_SUCCESS;

					Toast.makeText(
							mContext,
							mContext.getResources().getString(
									R.string.wifi_succuess),
							Toast.LENGTH_SHORT).show();

				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}

		@Override
		public void isSmallMTU(int MTU) {
		}
	};

	public void stopPacketData2() {
		if (isCalled2) {
			isCalled2 = false;
			ftcService.stopTransmitting();
		}
	}
}
