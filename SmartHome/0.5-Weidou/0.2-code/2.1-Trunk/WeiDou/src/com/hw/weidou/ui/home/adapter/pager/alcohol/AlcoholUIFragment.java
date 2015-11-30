package com.hw.weidou.ui.home.adapter.pager.alcohol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hw.util.WebViewUtil;
import com.hw.weidou.R;
import com.hw.weidou.constant.EquipConstant;
import com.hw.weidou.parser.ParserDeamon;
import com.hw.weidou.parser.alcohol.AlcoholAlgorithm;
import com.hw.weidou.po.WeidouPo;
import com.hw.weidou.ui.constant.UIConstant;
import com.hw.weidou.ui.home.HomeFragment;
import com.hw.weidou.ui.home.HomeUtil;
import com.hw.weidou.ui.home.adapter.pager.Current2Fragment;
import com.hw.weidou.ui.home.adapter.pager.base.CurrentFragmentBase;
import com.hw.weidou.ui.home.adapter.util.CurrentUtil;

/**
 * 酒精专用页面
 * 
 * @author 曾凡
 * @time 2015年2月10日 下午2:57:15
 */
public class AlcoholUIFragment extends CurrentFragmentBase {
	private static AlcoholUIFragment instance = null;
	private SoundPool sndPool;
	private Map<String, Integer> sounds = new HashMap<String, Integer>();

	public static AlcoholUIFragment getInstance() {
		if (instance == null) {
			instance = new AlcoholUIFragment();
		}
		return instance;
	}

	@Override
	protected void initParentView(LayoutInflater inflater,
			ViewGroup container) {
		AlcoholAlgorithm.getData();
		View parentView = inflater.inflate(
				R.layout.ui_home_page_alcohol_ui, container,
				false);
		mParentView = parentView;
		sndPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

		sounds.put("danger",
				sndPool.load(parentContext, R.raw.danger, 1));
		sounds.put("high",
				sndPool.load(parentContext, R.raw.high, 1));
		sounds.put("normal",
				sndPool.load(parentContext, R.raw.normal, 1));
		sounds.put("readyTest",
				sndPool.load(parentContext, R.raw.ready_test, 1));
		sounds.put("startTest",
				sndPool.load(parentContext, R.raw.start_test, 1));
		sounds.put("glass",
				sndPool.load(parentContext, R.raw.glass, 1));
		
		// sounds.put("high", sndPool.load());
		// sounds.put("normal", sndPool.load());
		// sounds.put("ready", sndPool.load());
		// sounds.put("startTest", sndPool.load());

		// sounds.put("danger", sndPool.load(
		// "file:///android_asset/res/danger.mp3", 1));
		// sounds.put("high", sndPool.load(
		// "file:///android_asset/res/high.mp3", 1));
		// sounds.put("normal", sndPool.load(
		// "file:///android_asset/res/normal.mp3", 1));
		// sounds.put("ready", sndPool.load(
		// "file:///android_asset/res/readyTest.mp3", 1));
		// sounds.put("startTest", sndPool.load(
		// "file:///android_asset/res/startTest.mp3", 1));
	}

	@Override
	protected void initView() {
		currentUsage = (TextView) mParentView
				.findViewById(R.id.currentUsage);

		currentPowerLevel = (ImageView) mParentView
				.findViewById(R.id.currentPowerLevel);

		currentValue = (TextView) mParentView
				.findViewById(R.id.currentValue);
		currentDataHigh = (TextView) mParentView
				.findViewById(R.id.currentDataHigh);
		currentDataAvg = (TextView) mParentView
				.findViewById(R.id.currentAvg);

		currentData = (WebView) mParentView
				.findViewById(R.id.currentData);
		currentMeans = (TextView) mParentView
				.findViewById(R.id.currentMeans);
		currentData
				.addJavascriptInterface(this, "alcoholAction");

		WebViewUtil webUtil = new WebViewUtil();
		webUtil.initWebview(currentData,
				UIConstant.HOME_ALCOHOL_ADDR);

		// createChart();

	}

	@Override
	protected void updateFragment(WeidouPo weidou) {
		List<Fragment> viewList = HomeFragment.getInstance()
				.getHomeViewList();
		if (weidou.getEquip() != 0) {
			if (HomeUtil.isAlcohol(weidou)) {
				if (!(viewList.get(0) instanceof AlcoholUIFragment)) {
					Log.i("replaceFragment", "AlcoholUIFragment"
							+ weidou.toString());
					viewList.set(0,
							AlcoholUIFragment.getInstance());
					HomeFragment.getInstance()
							.getUpViewAdapter()
							.notifyDataSetChanged();
				}
			} else {
				if (!(viewList.get(0) instanceof Current2Fragment)) {

					Log.i("replaceFragment", "Current2Fragment"
							+ weidou.toString());
					viewList.set(0,
							Current2Fragment.getInstance());
					HomeFragment.getInstance()
							.getUpViewAdapter()
							.notifyDataSetChanged();
					// HomeFragment.getInstance().initPager();
				}
			}

		}
	}

	@Override
	protected void updateChart() {
		currentData.loadUrl("javascript:update('" + updateData()
				+ "')");
	}

	/**
	 * 初始化信息
	 * 
	 * @author 曾凡
	 * @time 2014年11月5日 下午5:26:35
	 */
	private String updateData() {
		WeidouPo weidou = ParserDeamon.getCurrentWeidou();
		switch (weidou.getEquip()) {
		case EquipConstant.EQUIP_CH2O:// 甲醛
			weidou.setUnit(UIConstant.HOME_UNIT_CH2O);
			break;
		case EquipConstant.EQUIP_CO:// 一氧化碳
			weidou.setUnit(UIConstant.HOME_UNIT_CH2O);
			break;
		case EquipConstant.EQUIP_ALCOHOL:// 酒精
			weidou.setUnit(UIConstant.HOME_UNIT_ALCOHOL);
			break;
		}
		String param = CurrentUtil.updateHtmlDatas(
				getActivity(), currentData, weidou, maxValue);
		return param;
	}

	@JavascriptInterface
	public void setCurrentValue(String str) {
		Log.i("action", "setCurrentValue");
		AlcoholAlgorithm.currentValue = str;
	}

	@JavascriptInterface
	public void playDanger() {
		Log.i("action", "playDanger");
		playSound("danger", 1);
	}

	@JavascriptInterface
	public void playHigh() {
		Log.i("action", "playHigh");
		playSound("high", 1);
	}

	@JavascriptInterface
	public void playNormal() {
		Log.i("action", "playNormal");
		playSound("normal", 1);
	}

	@JavascriptInterface
	public void playReadyTest() {
		Log.i("action", "playReadyTest");
		playSound("readyTest", 1);
	}

	@JavascriptInterface
	public void playStartTest() {
		Log.i("action", "playStartTest");
		playSound("startTest", 1);
	}
	@JavascriptInterface
	public void playGlass(){
		Log.i("action", "glass");
		playSound("glass", 1);
	}

	private void playSound(String sound, int number) {
		float audioMaxVolum = am
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);// 音效最大值
		float audioCurrentVolum = am
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float audioRatio = audioCurrentVolum / audioMaxVolum;
		sndPool.play(sounds.get(sound), audioRatio,// 左声道音量
				audioRatio,// 右声道音量
				1, // 优先级
				number,// 循环播放次数
				1);// 回放速度，该值在0.5-2.0之间 1为正常速度
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		sndPool.release();
	}
}
