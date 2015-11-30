package com.hw.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * WebView的基类
 * 
 * @author 曾凡
 * @time 2014年5月16日 上午10:48:32
 */
public class WebViewUtil {
	private WebView mWebView = null;
	/**
	 * 是否在web链接中启动新的Activity
	 */
	private boolean startNewActivity = false;
	/**
	 * 是否启动内部监听back按钮
	 */
	private boolean enableBack = false;
    private Context mContext;
	/**
	 * 用户必须初始化类时必须调用，可以放到onCreate方法里去执行
	 * 
	 * @author 曾凡
	 * @param contentViewId
	 *            layout
	 * @param webViewId
	 *            WebView的ID
	 * @param loadUrl
	 *            网页的地址（网络/本地）
	 * @time 2014年5月16日 上午11:03:03
	 */
	@SuppressLint("JavascriptInterface")
	public void initWebview(WebView webView, String loadUrl) {
		this.mWebView = webView;
		WebSettings set = mWebView.getSettings();
		/* 启动js */
		set.setJavaScriptEnabled(true);
		/* 支持缩放 */
		set.setSupportZoom(false);
		set.supportMultipleWindows();
		if (!isEnableBack()) {
			mWebView.setOnKeyListener(new MainWebViewOnKeyListener());
		}
		if (!isStartNewActivity()) {
			mWebView.setWebViewClient(new MainWebViewClient());
		}
		mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		mWebView.setWebChromeClient(new MainWebChromeClient());
		mWebView.loadUrl(loadUrl);

	}
	@JavascriptInterface  
	public void historyShare(final String content){
		//Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
		String str = "汉威空气电台播报,"+content;
		ShareUtil.localShare(mContext, str);
	
    }  

	private class MainWebChromeClient extends WebChromeClient {

		@Override
		public boolean onJsAlert(WebView view, String url,
				String message, JsResult result) {
			Ln.i("message");
			result.confirm();
			return true;
		}

		/*
		 * Since API Level 8
		 */
		@Override
		public boolean onConsoleMessage(
				ConsoleMessage consoleMessage) {
			return super.onConsoleMessage(consoleMessage);
		}

		@Override
		public void onProgressChanged(WebView view,
				int newProgress) {

		}
	}

	private class MainWebViewOnKeyListener implements
			OnKeyListener {

		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			if (event.getAction() == KeyEvent.ACTION_DOWN) {

				if (keyCode == KeyEvent.KEYCODE_BACK
						&& mWebView.canGoBack()) { // 表示按返回键时的操作
					mWebView.goBack(); // 后退
					// mWebView.goForward();//前进
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 如果页面中链接，如果希望点击链接继续在当前browser中响应， 而不是新开Android的系统browser中响应该链接，必须覆盖
	 * webview的WebViewClient对象。
	 */
	public class MainWebViewClient extends WebViewClient {

		public boolean shouldOverviewUrlLoading(WebView view,
				String url) {
			view.loadUrl(url);
			return true;
		}

		public void onPageStarted(WebView view, String url,
				Bitmap favicon) {
		}

		public void onPageFinished(WebView view, String url) {

		}

		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
		}
	}

	public boolean isStartNewActivity() {
		return startNewActivity;
	}

	public void setStartNewActivity(boolean startNewActivity) {
		this.startNewActivity = startNewActivity;
	}

	public boolean isEnableBack() {
		return enableBack;
	}

	public void setEnableBack(boolean enableBack) {
		this.enableBack = enableBack;
	}

	public Context getmContext() {
		return mContext;
	}

	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}
	
	
}