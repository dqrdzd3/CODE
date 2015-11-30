package com.hw.smarthome.ui.solution;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hw.smarthome.R;
import com.hw.smarthome.po.UserPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithAccount;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.solution.constant.SolutionConstants;
import com.hw.util.UIUtil;
import com.special.ResideMenu.ResideMenu;
import com.zf.view.CircleImageView;

public class SmartSolutionsFragment extends Fragment {

	private static SmartSolutionsFragment instance;
	protected MainActivity mMainActivity;
	protected CircleImageView leftMenu;
	private ResideMenu resideMenu;
	public TextView uiHomeSensorName;
	private WebView webview;
	private ProgressBar pb;
	private int id;
	private View layout;
	private String url;

	public static SmartSolutionsFragment getInstance(int id) {
//		if (instance == null) {
			instance = new SmartSolutionsFragment();
//		}
		instance.id = id;
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMainActivity = (MainActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		layout = inflater.inflate(R.layout.ui_solutions,
				container, false);
		initView();
		initMenuView();
		initData();
		return layout;
	}

	private void initView() {
		leftMenu = (CircleImageView) layout
				.findViewById(R.id.title_bar_left_menu);
		resideMenu = ((MainActivity) getActivity())
				.getResideMenu();
		uiHomeSensorName = (TextView) layout
				.findViewById(R.id.uiHomeSensorName);
		uiHomeSensorName.setTextColor(mMainActivity
				.getResources().getColor(R.color.black));
		webview = (WebView) layout.findViewById(R.id.webView1);
	}

	private void initMenuView() {

		UserPO userPO = DealWithAccount.getUser(mMainActivity);
		if (userPO != null && userPO.getMa017() != null) {
			leftMenu.setImageBitmap(BitmapFactory
					.decodeByteArray(userPO.getMa017(), 0,
							userPO.getMa017().length));
		} else {
			resideMenu.setTitleImage(R.drawable.weibo_head);
		}
		leftMenu.setOnClickListener(new TitleBarMenuOnClick());
		mMainActivity.getResideMenu().init(layout);
		mMainActivity.getResideMenu().addIgnoredView(
				layout.findViewById(R.id.uiHomeLayout));
	}

	private class TitleBarMenuOnClick implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.title_bar_left_menu:
				((MainActivity) mMainActivity).updateHead();
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
				break;
			}
		}
	}

	private void initData() {
		switch (id) {
		case SolutionConstants.DIY: // DIY课堂
			setUrl(ServerConstant.DIY_URL);
			uiHomeSensorName.setText(UIConstant.DIY);
			break;
		// case SolutionConstants.MONITOR: // 免费监测
		// setUrl(ServerConstant.MONITOR_URL);
		// break;
		case SolutionConstants.SHOPPING: // 在线商城
			setUrl(ServerConstant.SHOP_URL);
			uiHomeSensorName.setText(UIConstant.SHOPPING);
			break;
		case SolutionConstants.BUSINESS: // 我要创业
			setUrl(ServerConstant.JOIN_URL);
			uiHomeSensorName.setText(UIConstant.BUSINESS);
			break;
		// case SolutionConstants.SOLUTION_TEST: // 发送监测
		// setUrl(getUrl());
		// break;
		default:
			break;
		}

		WebSettings webSettings = webview.getSettings(); // webView: 类WebView的实例
		webSettings.setJavaScriptEnabled(true);
		webSettings.setUseWideViewPort(true);
		webSettings.setSupportZoom(true);
//		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
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
