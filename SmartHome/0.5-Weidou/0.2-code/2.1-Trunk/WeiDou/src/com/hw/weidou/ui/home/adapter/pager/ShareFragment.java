package com.hw.weidou.ui.home.adapter.pager;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import me.xiaopan.android.switchbutton.SwitchButton;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.sina.weibo.SinaWeibo.ShareParams;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.tencent.weibo.TencentWeibo;
import cn.sharesdk.wechat.utils.WechatClientNotExistException;
import cn.sharesdk.wechat.utils.WechatTimelineNotSupportedException;

import com.hw.util.DateUtils;
import com.hw.util.FileUtils;
import com.hw.weidou.R;
import com.hw.weidou.constant.EquipConstant;
import com.hw.weidou.constant.UnitConstant;
import com.hw.weidou.parser.ParserDeamon;
import com.hw.weidou.parser.alcohol.AlcoholAlgorithm;
import com.hw.weidou.po.WeidouPo;
import com.hw.weidou.server.constant.ServerConstant;
import com.hw.weidou.ui.constant.UIConstant;
import com.hw.weidou.ui.home.adapter.util.HistoryUtil;
import com.hw.weidou.ui.util.UnitUtil;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * @author 曾凡
 * @time 2014年10月17日 上午10:15:04
 */
public class ShareFragment extends Fragment implements
		PlatformActionListener {
	private static ShareFragment instance = null;

	public static ShareFragment getInstance() {
		if (instance == null) {
			instance = new ShareFragment();
		}
		return instance;
	}

	private View mParentView;
	/** 当前值 */
	private TextView curDataContent;
	private TextView dataUnit;
	private Context mContext;
	/** 分享实时数据和历史数据的开关 */
	private SwitchButton historySwitchButton,
			realtimeSwitchButton;
	/** 分享的图片按钮 */
	private ImageView qqImageView, qzoneImageView,
			sinaImageView, wxImageView, wxPyImageView,
			weboImageView;

	private boolean isShareRealTime = true;
	private boolean isShareHistory = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View parentView = inflater.inflate(
				R.layout.ui_home_page_share, container, false);

		mParentView = parentView;
		initView();
		return parentView;
	}

	public void initView() {
		curDataContent = (TextView) mParentView
				.findViewById(R.id.shareCurDataContent);
		dataUnit = (TextView) mParentView
				.findViewById(R.id.shareDataUnit);
		historySwitchButton = (SwitchButton) mParentView
				.findViewById(R.id.share_history);
		historySwitchButton
				.setOnCheckedChangeListener(new SwitchButtonListener());
		realtimeSwitchButton = (SwitchButton) mParentView
				.findViewById(R.id.share_current);
		realtimeSwitchButton
				.setOnCheckedChangeListener(new SwitchButtonListener());
		setpic();
		qqImageView = (ImageView) mParentView
				.findViewById(R.id.shareQQ);
		qzoneImageView = (ImageView) mParentView
				.findViewById(R.id.shareQZone);
		sinaImageView = (ImageView) mParentView
				.findViewById(R.id.shareSinaWeibo);
		wxImageView = (ImageView) mParentView
				.findViewById(R.id.shareWechat);
		wxPyImageView = (ImageView) mParentView
				.findViewById(R.id.shareFriendCircle);
		weboImageView = (ImageView) mParentView
				.findViewById(R.id.shareTencentWeibo);
		ShareSDK.initSDK(mContext);
		qqImageView
				.setOnClickListener(new ShareButtonListener());
		qzoneImageView
				.setOnClickListener(new ShareButtonListener());
		wxImageView
				.setOnClickListener(new ShareButtonListener());
		wxPyImageView
				.setOnClickListener(new ShareButtonListener());
		sinaImageView
				.setOnClickListener(new ShareButtonListener());
		weboImageView
				.setOnClickListener(new ShareButtonListener());
	}

	private class SwitchButtonListener implements
			OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			switch (buttonView.getId()) {
			case R.id.share_current:
				isShareRealTime = isChecked;
				break;
			case R.id.share_history:
				isShareHistory = isChecked;
				break;
			default:
				break;
			}
		}
	}

	private class ShareButtonListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			Platform qzone = null;
			ShareParams sp = null;
			switch (v.getId()) {
			case R.id.shareQQ:
				// sp = new ShareParams();
				// sp.setTitle("威豆分享");
				// sp.setTitleUrl("http://sharesdk.cn"); // 标题的超链接
				// sp.setText(share());
				// sp.setImageUrl("/sdcard/enose.png");
				// sp.setSite("发布分享的网站名称");
				// sp.setSiteUrl("发布分享网站的地址");
				sp = getShareParams(0);
				qzone = ShareSDK.getPlatform(QQ.NAME);
				qzone.setPlatformActionListener(ShareFragment.this); // 设置分享事件回调
				// 执行图文分享
				qzone.share(sp);
				break;
			case R.id.shareQZone:

				// sp = new ShareParams();
				// sp.setTitle("威豆分享");
				// sp.setTitleUrl("http://sharesdk.cn"); // 标题的超链接
				// sp.setText(share());
				// sp.setImageUrl("/sdcard/enose.png");
				// sp.setSite("发布分享的网站名称");
				// sp.setSiteUrl("发布分享网站的地址");

				sp = getShareParams(0);
				qzone = ShareSDK.getPlatform(QZone.NAME);
				qzone.setPlatformActionListener(ShareFragment.this); // 设置分享事件回调
				// 执行图文分享
				qzone.share(sp);

				break;
			case R.id.shareTencentWeibo:
				// sp = new ShareParams();
				// sp.setTitle("威豆");
				// sp.setText("威豆：" + share());
				//
				// sp.setImagePath("/sdcard/enose.png");
				// sp.setComment(share());
				sp = getShareParams(1);
				Platform weibo = ShareSDK
						.getPlatform(TencentWeibo.NAME);
				weibo.setPlatformActionListener(ShareFragment.this);

				weibo.share(sp);
				// share("fffffffff", null, TencentWeibo.NAME);

				break;
			case R.id.shareWechat:
				qzone = ShareSDK.getPlatform("Wechat");
				qzone.SSOSetting(false);
				qzone.setPlatformActionListener(ShareFragment.this); // 设置分享事件回调
				// 执行图文分享
				// qzone.share(sp);
				qzone.share(getShareParams(0));
				break;
			case R.id.shareFriendCircle:

				// sp = new ShareParams();
				// sp.setTitle("威豆分享");
				//
				// sp.setText(share());
				// sp.setImageUrl("/sdcard/enose.png");
				//
				// sp.setShareType(Platform.SHARE_WEBPAGE);
				// sp.setImagePath("/sdcard/enose.png");
				// sp.setExtInfo(share());
				// sp.setUrl("http://www.baidu.com");
				//
				// sp.setComment(share());
				sp = getShareParams(0);
				qzone = ShareSDK.getPlatform("WechatMoments");
				qzone.setPlatformActionListener(ShareFragment.this); // 设置分享事件回调
				// 执行图文分享
				qzone.share(sp);

				break;
			case R.id.shareSinaWeibo:
				// 关注新浪微博
				sp = new ShareParams();
				sp.setText("分享微博");
				// sp.setImagePath("/sdcard/enose.png");
				weibo = ShareSDK.getPlatform("SinaWeibo");
				weibo.setPlatformActionListener(ShareFragment.this);
				weibo.share(sp);

				break;
			default:
				break;
			}
		}
	}

	private ShareParams getShareParams(int type) { // 1 表示微博，0表示应用
		ShareParams sp = new ShareParams();
		sp.setTitle("威豆分享");
		sp.setTitleUrl("http://hanwei.cn"); // 标题的超链接
		if (type == 1) {
			sp.setText("威豆：" + getContent());
		}
		if (type == 0) {
			sp.setText(getContent());
		}

		sp.setImageUrl("/sdcard/enose.png");

		sp.setShareType(Platform.SHARE_WEBPAGE);
		sp.setImagePath("/sdcard/enose.png");
		sp.setExtInfo(getContent());
		sp.setUrl(share());
		return sp;
	}

	/**
	 * 分享的文字内容
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年11月25日 下午7:03:31
	 */
	private String getContent() {
		// 威豆[equip]分享:dataContent 单位(根据equip获得)
		StringBuffer contentString = new StringBuffer();
		WeidouPo weidou = ParserDeamon.getCurrentWeidou();
		contentString.append(weidou.shareContent());
		contentString.append("【该数据由空气电台提供】");
		return contentString.toString();
	}

	/** 更新当前值 */
	private Handler mUpdateCurHandler = new Handler();
	private Runnable mUpdateCurThread = new Runnable() {
		@Override
		public void run() {
			/* 获取最新威豆 */
			WeidouPo weidou = ParserDeamon.getCurrentWeidou();
			curDataContent.setText(UnitUtil.getValue(
					weidou.getEquip(), weidou.getDataContent()));
			String unit = "";
			switch (weidou.getEquip()) {
			case EquipConstant.EQUIP_CH2O:// 甲醛
				unit = UIConstant.HOME_UNIT_CH2O;
				curDataContent.setText(UnitUtil.getValue(
						weidou.getEquip(), weidou.getDataContent()));
				break;
			case EquipConstant.EQUIP_ALCOHOL:// 酒精
				unit = UIConstant.HOME_UNIT_ALCOHOL;
				curDataContent.setText(AlcoholAlgorithm.currentValue);
				break;
			case EquipConstant.EQUIP_CO:// 一氧化碳
				unit = UIConstant.HOME_UNIT_CO;
				curDataContent.setText(UnitUtil.getValue(
						weidou.getEquip(), weidou.getDataContent()));
				break;
			default:
				break;
			}
			weidou.setUnit(unit);
			dataUnit.setText(unit);
			mUpdateCurHandler.postDelayed(mUpdateCurThread, 100);
		}
	};

	/**
	 * 分享
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年10月28日 上午8:52:14
	 */
	private String share() {
		StringBuilder sb = new StringBuilder(
				ServerConstant.BASE_URI
						+ ServerConstant.WD_01_03); /* [WD_01_03] 分享 */
		String time = DateUtils.getCurrentTime();
		sb.append("?recordDate=").append(time.substring(5, 10));
		sb.append("&recordTime=").append(time.substring(11, 16));
		if (realtimeSwitchButton.isChecked()) {
			sb.append(shareCurrent());
		}
		if (historySwitchButton.isChecked()) {
			sb.append(shareHistory());
		}
		return sb.toString();
	}

	/**
	 * 分享实时数据
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年10月28日 上午8:52:23
	 */
	private String shareCurrent() {
		/* 获取最新威豆 */
		WeidouPo weidou = ParserDeamon.getCurrentWeidou();
		if (weidou != null) {
			return weidou.toShareString();
		} else {
			return "";
		}
	}

	/**
	 * 分享历史数据
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年10月28日 上午8:52:31
	 */
	private String shareHistory() {
		StringBuilder sb = new StringBuilder();
		List<Float> tempList = null;
		tempList = HistoryUtil.Service.getArray(mContext,
				HistoryUtil.TYPE_MINUTE_CHART);
		if (tempList != null && tempList.size() > 0) {
			sb.append("&minute=[");
			for (Float f : tempList) {
				sb.append(f).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
		}
		tempList = HistoryUtil.Service.getArray(mContext,
				HistoryUtil.TYPE_HOUR_CHART);
		sb.append("&hour=[");
		if (tempList != null && tempList.size() > 0) {

			for (Float f : tempList) {
				sb.append(f).append(",");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}

	@Override
	public void onResume() {
		super.onResume();
		/* 更新当前值 */
		if (mUpdateCurHandler != null) {
			mUpdateCurHandler.removeCallbacks(mUpdateCurThread);
			mUpdateCurHandler.post(mUpdateCurThread);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mUpdateCurHandler != null) {
			mUpdateCurHandler.removeCallbacks(mUpdateCurThread);
		}
	}

	@Override
	public void onCancel(Platform plat, int action) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		msg.arg1 = 3;
		msg.arg2 = action;
		msg.obj = plat;

	}

	@Override
	public void onComplete(Platform plat, int action,
			HashMap<String, Object> arg2) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		msg.arg1 = 1;
		msg.arg2 = action;
		msg.obj = plat;

	}

	@Override
	public void onError(Platform plat, int action, Throwable t) {
		t.printStackTrace();

		Message msg = new Message();
		msg.arg1 = 2;
		msg.arg2 = action;
		msg.obj = plat;

	}

	private void setpic() {
		// 图片保存在sd卡根目录里
		ByteArrayOutputStream baosArrayOutputStream = new ByteArrayOutputStream();
		Bitmap bitmap = BitmapFactory.decodeResource(
				mContext.getResources(), R.drawable.ic_launcher);
		bitmap.compress(Bitmap.CompressFormat.PNG, 100,
				baosArrayOutputStream);
		FileUtils.writeFile(baosArrayOutputStream.toByteArray(),
				"enose.png");
	}

	private int SHARE_FAIL = 10;
	private int SHARE_SUCCESS = 11;
	private int SHARE_CANCEL = 12;

	// 新浪微博分享 腾讯微博等只需修改 NAME
	public void share(String text, String photopath,
			String sharename) {
		Platform.ShareParams sp = new SinaWeibo.ShareParams();
		sp.text = text;
		if (photopath != null) {
			// sp.imagePath = "/mnt/sdcard/测试分享的图片.jpg";
			sp.imagePath = photopath;
		}
		Platform weibo = ShareSDK.getPlatform(mContext,
				sharename);
		// 设置分享事件回调
		weibo.setPlatformActionListener(new PlatformActionListener() {
			public void onError(Platform platform, int action,
					Throwable t) {
				// 操作失败的处理代码
				// Message m = handler.obtainMessage();
				// m.what = SHARE_FAIL;
				// TestShare.this.handler.sendMessage(m);

				Message msg = handler.obtainMessage();
				msg.what = 10;
				msg.arg1 = action;
				msg.obj = t;
				handler.sendMessage(msg);
			}

			public void onComplete(Platform platform,
					int action, HashMap<String, Object> res) {
				// 操作成功的处理代码
				Message m = handler.obtainMessage();
				m.what = 11;
				handler.sendMessage(m);

			}

			public void onCancel(Platform platform, int action) {
				// 操作取消的处理代码
				Message m = handler.obtainMessage();
				m.what = 12;
				handler.sendMessage(m);
			}
		});
		// 执行图文分享
		weibo.share(sp);
	}

	public Handler handler = new Handler() {
		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub

			switch (msg.what) {
			case 11:
				String successtext = getResources().getString(
						R.string.share_completed);

				break;
			case 10:
				String failtext = "";

				if (msg.obj instanceof WechatClientNotExistException) {
					failtext = getResources().getString(
							R.string.wechat_client_inavailable);
				} else if (msg.obj instanceof WechatTimelineNotSupportedException) {
					failtext = getResources().getString(
							R.string.wechat_client_inavailable);
				}
				// java.lang.Throwable:
				// {"ret":5,"seqid":5950018181724704141,"detailerrinfo":
				// {"timestamp":1385346563,"proctime":41,"cmd":1472,"accesstoken":"","clientip":"219.143.8.242","apiname":"weibo.t.add","ret2":5,"appkey":"801400858","ret1":20,"ret4":3515057674,"ret3":75},"msg":"prevent duplicate publication","errcode":75}
				else if (msg.obj instanceof java.lang.Throwable
						&& msg.obj.toString() != null
						&& msg.obj.toString().contains(
								"prevent duplicate publication")) {

					failtext = "sssssssss";
				} else if (msg.obj.toString().contains("error")) {
					failtext = "dddddddddddd";

				} else {
					failtext = getResources().getString(
							R.string.share_failed);
				}

				break;
			case 12:

				break;

			}
		}

	};

}
