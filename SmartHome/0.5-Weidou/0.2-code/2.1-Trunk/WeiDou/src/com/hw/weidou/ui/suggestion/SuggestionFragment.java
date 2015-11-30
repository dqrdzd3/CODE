package com.hw.weidou.ui.suggestion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hw.weidou.R;

public class SuggestionFragment extends Fragment {

	private static SuggestionFragment instance;
	private WebView webview;
	private View layout;
	ProgressBar pb;
	String url = "";

	public static SuggestionFragment getInstance() {
		if (instance == null) {
			instance = new SuggestionFragment();
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		layout = inflater.inflate(R.layout.ui_settings_help,
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
		Log.e("AboutFragment", "=========url=======" + url);
		webview.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(
					WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}

		});
		webview.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view,
					int newProgress) {
				// TODO Auto-generated method stub
				// super.onProgressChanged(view, newProgress);
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
