package com.hw.smarthome.ui.setting.sub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.ProgressBar;

import com.hw.smarthome.R;

/**
 * @author 曾凡
 * @time 2014年6月9日 下午2:44:49
 */
public class SettingQuesFragment extends Fragment {
	private static SettingQuesFragment instance;
	private WebView webview;
	private View layout;
	ProgressBar pb;
	String url = "";

	public static SettingQuesFragment getInstance() {
		if (instance == null) {
			instance = new SettingQuesFragment();
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		layout = inflater.inflate(R.layout.ui_settings_ques,
				container, false);
		initInfo();
		return layout;
	}

	/**
	 * 
	 * @author lijing
	 * @time 2014-7-22 下午3:31:38
	 */
	private void initInfo() {
		webview = (WebView) layout.findViewById(R.id.webview);

		WebSettings webSettings = webview.getSettings(); // webView: 类WebView的实例
		webSettings.setJavaScriptEnabled(true);
		webSettings.setUseWideViewPort(true);
		webSettings.setSupportZoom(true);
		// webSettings.setBuiltInZoomControls(true);
		webSettings.setLoadWithOverviewMode(true);

		webSettings
				.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); // 就是这句
		pb = (ProgressBar) layout.findViewById(R.id.pb);
		pb.setMax(100);
		if (TextUtils.isEmpty(url))
			return;
		webview.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(
					WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

		});
		webview.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view,
					int newProgress) {
				pb.setProgress(newProgress);
				if (newProgress == 100) {
					pb.setVisibility(View.GONE);
				}
				super.onProgressChanged(view, newProgress);
			}
		});
		webview.loadUrl(url);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
