package com.hw.smarthome.ui.shopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hw.smarthome.R;

/**
 * @author 曾凡
 * @time 2014年6月9日 下午2:44:49
 */
public class ShoppingFragment extends Fragment {
	private static ShoppingFragment instance;
	private WebView webview;
	private View layout;
	private ProgressBar pb;
	private static String ONELINE_MARKET_URI = "http://harwest.m.tmall.com/?spm=0.0.0.0.1bcidM";

	public static ShoppingFragment getInstance() {
		if (instance == null) {
			instance = new ShoppingFragment();
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		layout = inflater.inflate(R.layout.ui_shopping,
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
		webview = (WebView) layout
				.findViewById(R.id.shoppingWebview);

		WebSettings webSettings = webview.getSettings(); // webView: 类WebView的实例
		webSettings.setJavaScriptEnabled(true);
		webSettings.setUseWideViewPort(true);
		webSettings.setSupportZoom(true);
		// webSettings.setBuiltInZoomControls(true);
		webSettings.setLoadWithOverviewMode(true);

		webSettings
				.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); // 就是这句
		pb = (ProgressBar) layout.findViewById(R.id.shoppingPb);
		pb.setMax(100);
		webview.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(
					WebView view, String ONELINE_MARKET_URI) {
				// TODO Auto-generated method stub
				view.loadUrl(ONELINE_MARKET_URI);
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
		// 点击后退按钮,让WebView后退一页(也可以覆写Activity的onKeyDown方法)
		webview.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode,
					KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_BACK
							&& webview.canGoBack()) { // 表示按返回键时的操作
						webview.goBack(); // 后退

						// webview.goForward();//前进
						return true; // 已处理
					}
				}
				return false;
			}
		});

		webview.loadUrl(ONELINE_MARKET_URI);
	}
}
