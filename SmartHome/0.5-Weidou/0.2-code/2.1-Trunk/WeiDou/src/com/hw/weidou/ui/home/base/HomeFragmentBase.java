package com.hw.weidou.ui.home.base;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hw.util.Ln;
import com.hw.weidou.R;
import com.hw.weidou.constant.SignalConstant;
import com.hw.weidou.parser.ParserDeamon;
import com.hw.weidou.parser.util.ParserUtil;
import com.hw.weidou.po.WeidouPo;
import com.hw.weidou.server.constant.ServerConstant;
import com.hw.weidou.ui.MainActivity;
import com.hw.weidou.ui.constant.UIConstant;
import com.hw.weidou.ui.home.HomeFragment;
import com.hw.weidou.ui.home.HomeUtil;
import com.hw.weidou.ui.home.adapter.HomeFragmentAdapter;
import com.hw.weidou.ui.home.adapter.pager.Current2Fragment;
import com.hw.weidou.ui.home.adapter.pager.HistoryFragment;
import com.hw.weidou.ui.home.adapter.pager.MatchFragment;
import com.hw.weidou.ui.home.adapter.pager.ShareFragment;
import com.hw.weidou.ui.home.adapter.pager.alcohol.AlcoholUIFragment;
import com.hw.weidou.ui.suggestion.SuggestionFragment;
import com.hw.weidou.ui.util.MainAcUtil;
import com.special.ResideMenu.ResideMenu;

/**
 * @author 曾凡
 * @time 2014年7月9日 下午4:54:42
 */
public abstract class HomeFragmentBase extends Fragment {
	/** 必须要用这个MainActivity，这样才能配置菜单 */
	protected MainActivity mMainActivity;
	protected View parentView;
	protected View uiHomeLayout;
	private MyReceiver mReceiver;
	private ResideMenu resideMenu;

	protected TextView homeEquipName;
	protected TextView homeVersion;

	protected TextView homeIsLinked;

	protected ViewPager mPager;
	private HomeFragmentAdapter upViewAdapter;
	private List<Fragment> homeViewList = new ArrayList<Fragment>();

	protected ImageView homeCurrent;
	protected ImageView homeHistory;
	protected ImageView homeMatch;
	protected ImageView homeShare;

	/**
	 * 用于解决方案页面的气体类型 [co:8,ch20:6,alcohol:7]
	 */
	public List<String> gasList = new ArrayList<String>();
	/**
	 * 用于解决方案页面的气体状态 [0低, 1正常, 高]
	 */
	public List<Integer> stateList = new ArrayList<Integer>();
	/** 判断耳机口是否连接上 */
	public static boolean isWeidouOn = true;
	protected AudioManager am;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMainActivity = (MainActivity) getActivity();
		am = (AudioManager) mMainActivity
				.getSystemService(Context.AUDIO_SERVICE);
		isWeidouOn = am.isWiredHeadsetOn();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

		parentView = inflater.inflate(R.layout.ui_home,
				container, false);
		resideMenu = mMainActivity.getResideMenu();
		parentView.findViewById(R.id.title_bar_left_menu)
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						resideMenu
								.openMenu(ResideMenu.DIRECTION_LEFT);
					}
				});
		parentView.findViewById(R.id.title_bar_right_menu)
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						WeidouPo weidou = ParserDeamon
								.getCurrentWeidou();
						SuggestionFragment suggestionFragment = SuggestionFragment
								.getInstance();
						GregorianCalendar g = new GregorianCalendar();
						int month = (int) g.get(Calendar.MONTH) + 1;
						String url = ServerConstant.SERVER_BASE_URI
								+ ServerConstant.WD_01_04
								+ "?GASTYPE="
								+ weidou.getGasCode().toString()
								+ "&GASSTATE="
								+ weidou.getGasSta().toString()
								+ "&TIMEINUSE=" + month; // 参数：GASTYPE
															// GASSTATE
															// TIMEINUSE
						suggestionFragment.setUrl(url);
						MainAcUtil.changeFragment(getActivity()
								.getSupportFragmentManager(),
								resideMenu, suggestionFragment);
					}
				});
		resideMenu.addIgnoredView(parentView
				.findViewById(R.id.uiHomeLayout));
		initFragment();

		return parentView;
	}

	private void initFragment() {
		initReceiver();
		initViews();
		initThread();
	}

	private void initReceiver() {
		/* 注册广播接收器，用来接受返回的明细和历史信息，一定要最后去监听 */
		mReceiver = new MyReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter
				.addAction("android.intent.action.HEADSET_PLUG");
		mMainActivity.registerReceiver(mReceiver, intentFilter);
	}

	/**
	 * 监听耳机口插入
	 * 
	 * @author 曾凡
	 * @time 2014年11月11日 下午1:08:38
	 */
	private class MyReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// am.setMode(AudioManager.MODE_IN_CALL);
			if (intent.hasExtra("state")) {
				if (intent.getIntExtra("state", 0) == 0) {
					isWeidouOn = false;
					// Toast.makeText(context, "耳机口未连接",
					// Toast.LENGTH_SHORT).show();
					endTimer();
					WeidouPo weidou = ParserDeamon
							.getCurrentWeidou();
					weidou.setStart(false);
					Ln.d("耳机口未连接");
				} else if (intent.getIntExtra("state", 0) == 1) {
					isWeidouOn = true;
					/* 重新插入耳机口后对传感器数据进行清零操作 */
					ParserUtil.clearWeidou();
					startTimer();
					startMic();
					// Toast.makeText(context, "耳机口已连接",
					// Toast.LENGTH_SHORT).show();
					// WaveOutPower.getInstance().palyWaveZ();
					Ln.d("耳机口已连接");
				}
			}
		}
	}

	private static int LEN = 30;

	private void startTimer() {
		recLen = LEN;
		if (mTimerHandler != null) {
			mTimerHandler.removeCallbacks(mTimerThread);
			mTimerHandler.post(mTimerThread);
		}
	}

	private void endTimer() {
		recLen = LEN;
		if (mTimerHandler != null) {
			mTimerHandler.removeCallbacks(mTimerThread);
		}
	}

	private int recLen = LEN;// 预热、启动倒计时
	/** 用于执行页面数据更新 */
	private Handler mTimerHandler = new Handler();
	private Runnable mTimerThread = new Runnable() {
		@Override
		public void run() {
			WeidouPo weidou = ParserDeamon.getCurrentWeidou();
			Ln.i(weidou);
			String status = "";
			/* 如果在倒计时中拔出则停止计时 */
			if (HomeFragment.isWeidouOn) {
				/* 判断报文是否可以被解析，如果不能解析就不进行倒计时 */
				if (0 != weidou.getEquip()) {
					/* 判断逻辑 */
					if (recLen > 0) {
						if (recLen > (LEN / 2)) {
							status = "启动中："
									+ (recLen - (LEN / 2)) + "秒";
						} else if (recLen <= (LEN / 2)) {
							status = "预热中：" + recLen + "秒";
						}
						recLen--;
					} else {
						endTimer();
						status = "启动";
						weidou.setStart(true);
					}
				} else {
					endTimer();
					status = "";
					weidou.setStart(false);
				}
			} else {
				endTimer();
				status = "未启动";
				weidou.setStart(false);
			}
			weidou.setStatus(status);
			mTimerHandler.postDelayed(mTimerThread, 1000);
		}
	};

	private void startMic() {
		if (mChkMicHandler != null) {
			mChkMicHandler.removeCallbacks(mChkMicThread);
			mChkMicHandler.postDelayed(mChkMicThread, changeLen);
		}
	}

	private void endMic() {
		if (mChkMicHandler != null) {
			mChkMicHandler.removeCallbacks(mChkMicThread);
		}
	}

	private int changeLen = 5 * 1000;// 5秒钟时间判断是不是国标
	/** 用于执行页面数据更新 */
	private Handler mChkMicHandler = new Handler();
	private Runnable mChkMicThread = new Runnable() {
		@Override
		public void run() {
			WeidouPo weidou = ParserDeamon.getCurrentWeidou();
			String status = UIConstant.HINT_MIC_STATUS;
			/* 如果在倒计时中拔出则停止计时 */
			if (HomeFragment.isWeidouOn) {
				if (0 == weidou.getEquip()) {
					if (SignalConstant.IS_INTERNATIONAL_MIC == true) {
						SignalConstant.IS_INTERNATIONAL_MIC = false;
					} else {
						SignalConstant.IS_INTERNATIONAL_MIC = true;
					}
					ParserUtil.clearWeidou();
					weidou.setStatus(status);
				}
			} else {
				weidou.setStart(false);
			}

			mChkMicHandler.postDelayed(mChkMicThread, changeLen);
		}
	};

	protected abstract void initThread();

	private void initViews() {

		homeEquipName = (TextView) parentView
				.findViewById(R.id.homeEquipName);

		homeVersion = (TextView) parentView
				.findViewById(R.id.homeVersion);
		homeIsLinked = (TextView) parentView
				.findViewById(R.id.homeIsLinked);

		homeCurrent = (ImageView) parentView
				.findViewById(R.id.homeCurrent);
		homeCurrent.setOnClickListener(new HomeButtonLisener());
		homeHistory = (ImageView) parentView
				.findViewById(R.id.homeHistory);
		homeHistory.setOnClickListener(new HomeButtonLisener());
		homeMatch = (ImageView) parentView
				.findViewById(R.id.homeMatch);
		homeMatch.setOnClickListener(new HomeButtonLisener());
		homeShare = (ImageView) parentView
				.findViewById(R.id.homeShare);
		homeShare.setOnClickListener(new HomeButtonLisener());
		mPager = (ViewPager) parentView
				.findViewById(R.id.homePager);
		mPager.setOnPageChangeListener(new PagerListener());
		initPager();
	}

	public void initPager() {

		WeidouPo weidou = ParserDeamon.getCurrentWeidou();
		Log.i("replaceFragment",
				"HomeFragment" + weidou.toString());
		homeViewList = new ArrayList<Fragment>();
		if (HomeUtil.isAlcohol(weidou)) {
			Log.i("replaceFragment", "AlcoholUIFragment");
			homeViewList.add(AlcoholUIFragment.getInstance());
		} else {
			Log.i("replaceFragment", "Current2Fragment");
			homeViewList.add(Current2Fragment.getInstance());
		}
		homeViewList.add(HistoryFragment.getInstance());
		homeViewList.add(MatchFragment.getInstance());
		homeViewList.add(ShareFragment.getInstance());
		mPager.setAdapter(null);
		upViewAdapter = new HomeFragmentAdapter(
				getChildFragmentManager(), mMainActivity,
				homeViewList);
		upViewAdapter.notifyDataSetChanged();
		mPager.setAdapter(upViewAdapter);
		
		mPager.setOffscreenPageLimit(4);
	}

	private class PagerListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int position) {
			Ln.d("onPageScrollStateChanged position:" + position);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			Ln.d("onPageScrolled arg0:" + arg0 + " arg1:" + arg1
					+ " arg2:" + arg2);
		}

		/**
		 * 更新页面实时数据
		 * 
		 * @author 曾凡
		 * @param position
		 * @time 2014年7月11日 上午10:18:31
		 */
		@Override
		public void onPageSelected(int position) {
			Ln.d("onPageSelected position:" + position);
			switch (position) {
			case 0:
				selectCurrent();
				break;
			case 1:
				selectHistory();
				break;
			case 2:
				selectMatch();
				break;
			case 3:
				selectShare();
				break;
			default:
				break;
			}
		}
	}

	private class HomeButtonLisener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.homeCurrent:
				selectCurrent();
				mPager.setCurrentItem(0);
				break;
			case R.id.homeHistory:
				selectHistory();
				mPager.setCurrentItem(1);
				break;
			case R.id.homeMatch:
				selectMatch();
				mPager.setCurrentItem(2);
				break;
			case R.id.homeShare:
				selectShare();
				mPager.setCurrentItem(3);
				break;
			}
		}
	}

	private void selectCurrent() {
		homeCurrent
				.setImageResource(R.drawable.ui_home_current_on);
		homeHistory.setImageResource(R.drawable.ui_home_history);
		homeMatch.setImageResource(R.drawable.ui_home_match);
		homeShare.setImageResource(R.drawable.ui_home_share);
	}

	private void selectHistory() {
		homeCurrent.setImageResource(R.drawable.ui_home_current);
		homeHistory
				.setImageResource(R.drawable.ui_home_history_on);
		homeMatch.setImageResource(R.drawable.ui_home_match);
		homeShare.setImageResource(R.drawable.ui_home_share);
	}

	private void selectMatch() {
		homeCurrent.setImageResource(R.drawable.ui_home_current);
		homeHistory.setImageResource(R.drawable.ui_home_history);
		homeMatch.setImageResource(R.drawable.ui_home_match_on);
		homeShare.setImageResource(R.drawable.ui_home_share);
	}

	private void selectShare() {
		homeCurrent.setImageResource(R.drawable.ui_home_current);
		homeHistory.setImageResource(R.drawable.ui_home_history);
		homeMatch.setImageResource(R.drawable.ui_home_match);
		homeShare.setImageResource(R.drawable.ui_home_share_on);
	}

	public HomeFragmentAdapter getUpViewAdapter() {
		return upViewAdapter;
	}

	public void setUpViewAdapter(
			HomeFragmentAdapter upViewAdapter) {
		this.upViewAdapter = upViewAdapter;
	}

	public List<Fragment> getHomeViewList() {
		return homeViewList;
	}

	public void setHomeViewList(List<Fragment> homeViewList) {
		this.homeViewList = homeViewList;
	}

	@Override
	public void onResume() {
		super.onResume();
		/* 开始录音 */
		MainAcUtil.send2Service(mMainActivity,
				ServerConstant.WD_01_01_02, 1);
		startTimer();
		startMic();
	}

	@Override
	public void onPause() {
		super.onPause();
		Ln.d("onPause");
		/* 暂停录音 */
		MainAcUtil.send2Service(mMainActivity,
				ServerConstant.WD_01_01_02, 0);
		endMic();
		endTimer();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Ln.d("onDestroy");
		if (mReceiver != null) {
			try {
				mMainActivity.unregisterReceiver(mReceiver);
			} catch (Exception e) {
				Ln.e(e, "注销HomeFragment的监听器异常");
			}
		}
	}

	public ViewPager getPager() {
		return mPager;
	}

	public void setPager(ViewPager mPager) {
		this.mPager = mPager;
	}
}