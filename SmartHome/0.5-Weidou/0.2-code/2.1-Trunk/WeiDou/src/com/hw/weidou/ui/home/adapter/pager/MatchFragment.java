package com.hw.weidou.ui.home.adapter.pager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.hw.weidou.R;
import com.hw.weidou.constant.EquipConstant;
import com.hw.weidou.parser.ParserDeamon;
import com.hw.weidou.parser.alcohol.AlcoholAlgorithm;
import com.hw.weidou.po.WeidouPo;
import com.hw.weidou.ui.constant.UIConstant;
import com.hw.weidou.ui.util.UnitUtil;

/**
 * @author 曾凡
 * @time 2014年10月17日 上午10:15:04
 */
public class MatchFragment extends Fragment {
	private static MatchFragment instance = null;

	public static MatchFragment getInstance() {
		if (instance == null) {
			instance = new MatchFragment();
		}
		return instance;
	}

	private View mParentView;
	private TextView curDataContent;
	private TextView dataUnit;
	private BootstrapButton matchReset;

	private View matchUp1;
	private TextView matchUp1Content;
	private TextView matchUp1Status;
	private TextView matchUp1Rank;

	private View matchUp2;
	private TextView matchUp2Content;
	private TextView matchUp2Status;
	private TextView matchUp2Rank;

	private View matchUp3;
	private TextView matchUp3Content;
	private TextView matchUp3Status;
	private TextView matchUp3Rank;

	private View matchMiddle1;
	private TextView matchMiddle1Content;
	private TextView matchMiddle1Status;
	private TextView matchMiddle1Rank;

	private View matchMiddle2;
	private TextView matchMiddle2Content;
	private TextView matchMiddle2Status;
	private TextView matchMiddle2Rank;

	private View matchMiddle3;
	private TextView matchMiddle3Content;
	private TextView matchMiddle3Status;
	private TextView matchMiddle3Rank;

	private View matchDown1;
	private TextView matchDown1Content;
	private TextView matchDown1Status;
	private TextView matchDown1Rank;

	private View matchDown2;
	private TextView matchDown2Content;
	private TextView matchDown2Status;
	private TextView matchDown2Rank;

	private View matchDown3;
	private TextView matchDown3Content;
	private TextView matchDown3Status;
	private TextView matchDown3Rank;
	private List<TextView> contents = new ArrayList<TextView>();
	private List<TextView> ranks = new ArrayList<TextView>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View parentView = inflater.inflate(
				R.layout.ui_home_page_match, container, false);

		mParentView = parentView;
		initView();
		return parentView;
	}

	public void initView() {

		contents.clear();
		ranks.clear();

		curDataContent = (TextView) mParentView
				.findViewById(R.id.matchCurDataContent);
		dataUnit = (TextView) mParentView
				.findViewById(R.id.matchDataUnit);
		matchReset = (BootstrapButton) mParentView
				.findViewById(R.id.matchReset);
		matchReset.setOnClickListener(new MatchOnClick());

		matchUp1 = mParentView.findViewById(R.id.matchUp1);
		matchUp1.setOnClickListener(new MatchOnClick());
		matchUp1Content = (TextView) mParentView
				.findViewById(R.id.matchUp1Content);
		matchUp1Status = (TextView) mParentView
				.findViewById(R.id.matchUp1Status);
		matchUp1Rank = (TextView) mParentView
				.findViewById(R.id.matchUp1Rank);
		contents.add(matchUp1Content);
		ranks.add(matchUp1Rank);

		matchUp2 = mParentView.findViewById(R.id.matchUp2);
		matchUp2.setOnClickListener(new MatchOnClick());
		matchUp2Content = (TextView) mParentView
				.findViewById(R.id.matchUp2Content);
		matchUp2Status = (TextView) mParentView
				.findViewById(R.id.matchUp2Status);
		matchUp2Rank = (TextView) mParentView
				.findViewById(R.id.matchUp2Rank);
		contents.add(matchUp2Content);
		ranks.add(matchUp2Rank);

		matchUp3 = mParentView.findViewById(R.id.matchUp3);
		matchUp3.setOnClickListener(new MatchOnClick());
		matchUp3Content = (TextView) mParentView
				.findViewById(R.id.matchUp3Content);
		matchUp3Status = (TextView) mParentView
				.findViewById(R.id.matchUp3Status);
		matchUp3Rank = (TextView) mParentView
				.findViewById(R.id.matchUp3Rank);
		contents.add(matchUp3Content);
		ranks.add(matchUp3Rank);

		matchMiddle1 = mParentView
				.findViewById(R.id.matchMiddle1);
		matchMiddle1.setOnClickListener(new MatchOnClick());
		matchMiddle1Content = (TextView) mParentView
				.findViewById(R.id.matchMiddle1Content);
		matchMiddle1Status = (TextView) mParentView
				.findViewById(R.id.matchMiddle1Status);
		matchMiddle1Rank = (TextView) mParentView
				.findViewById(R.id.matchMiddle1Rank);
		contents.add(matchMiddle1Content);
		ranks.add(matchMiddle1Rank);

		matchMiddle2 = mParentView
				.findViewById(R.id.matchMiddle2);
		matchMiddle2.setOnClickListener(new MatchOnClick());
		matchMiddle2Content = (TextView) mParentView
				.findViewById(R.id.matchMiddle2Content);
		matchMiddle2Status = (TextView) mParentView
				.findViewById(R.id.matchMiddle2Status);
		matchMiddle2Rank = (TextView) mParentView
				.findViewById(R.id.matchMiddle2Rank);
		contents.add(matchMiddle2Content);
		ranks.add(matchMiddle2Rank);

		matchMiddle3 = mParentView
				.findViewById(R.id.matchMiddle3);
		matchMiddle3.setOnClickListener(new MatchOnClick());
		matchMiddle3Content = (TextView) mParentView
				.findViewById(R.id.matchMiddle3Content);
		matchMiddle3Status = (TextView) mParentView
				.findViewById(R.id.matchMiddle3Status);
		matchMiddle3Rank = (TextView) mParentView
				.findViewById(R.id.matchMiddle3Rank);
		contents.add(matchMiddle3Content);
		ranks.add(matchMiddle3Rank);

		matchDown1 = mParentView.findViewById(R.id.matchDown1);
		matchDown1.setOnClickListener(new MatchOnClick());
		matchDown1Content = (TextView) mParentView
				.findViewById(R.id.matchDown1Content);
		matchDown1Status = (TextView) mParentView
				.findViewById(R.id.matchDown1Status);
		matchDown1Rank = (TextView) mParentView
				.findViewById(R.id.matchDown1Rank);
		contents.add(matchDown1Content);
		ranks.add(matchDown1Rank);

		matchDown2 = mParentView.findViewById(R.id.matchDown2);
		matchDown2.setOnClickListener(new MatchOnClick());
		matchDown2Content = (TextView) mParentView
				.findViewById(R.id.matchDown2Content);
		matchDown2Status = (TextView) mParentView
				.findViewById(R.id.matchDown2Status);
		matchDown2Rank = (TextView) mParentView
				.findViewById(R.id.matchDown2Rank);
		contents.add(matchDown2Content);
		ranks.add(matchDown2Rank);

		matchDown3 = mParentView.findViewById(R.id.matchDown3);
		matchDown3.setOnClickListener(new MatchOnClick());
		matchDown3Content = (TextView) mParentView
				.findViewById(R.id.matchDown3Content);
		matchDown3Status = (TextView) mParentView
				.findViewById(R.id.matchDown3Status);
		matchDown3Rank = (TextView) mParentView
				.findViewById(R.id.matchDown3Rank);
		contents.add(matchDown3Content);
		ranks.add(matchDown3Rank);

	}

	private class MatchOnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			/* 获取最新威豆 */
			WeidouPo weidou = ParserDeamon.getCurrentWeidou();
			switch (v.getId()) {
			case R.id.matchReset:
				matchUp1Content.setText("");
				matchUp1Status.setText("");
				matchUp1Rank.setText("");
				matchUp2Content.setText("");
				matchUp2Status.setText("");
				matchUp2Rank.setText("");
				matchUp3Content.setText("");
				matchUp3Status.setText("");
				matchUp3Rank.setText("");

				matchMiddle1Content.setText("");
				matchMiddle1Status.setText("");
				matchMiddle1Rank.setText("");
				matchMiddle2Content.setText("");
				matchMiddle2Status.setText("");
				matchMiddle2Rank.setText("");
				matchMiddle3Content.setText("");
				matchMiddle3Status.setText("");
				matchMiddle3Rank.setText("");

				matchDown1Content.setText("");
				matchDown1Status.setText("");
				matchDown1Rank.setText("");
				matchDown2Content.setText("");
				matchDown2Status.setText("");
				matchDown2Rank.setText("");
				matchDown3Content.setText("");
				matchDown3Status.setText("");
				matchDown3Rank.setText("");
				break;
			case R.id.matchUp1:
				setSensorValue(weidou, matchUp1Content,
						matchUp1Status);
				break;
			case R.id.matchUp2:
				setSensorValue(weidou, matchUp2Content,
						matchUp2Status);
				break;
			case R.id.matchUp3:
				setSensorValue(weidou, matchUp3Content,
						matchUp3Status);
				break;

			case R.id.matchMiddle1:
				setSensorValue(weidou, matchMiddle1Content,
						matchMiddle1Status);
				break;

			case R.id.matchMiddle2:
				setSensorValue(weidou, matchMiddle2Content,
						matchMiddle2Status);
				break;

			case R.id.matchMiddle3:
				setSensorValue(weidou, matchMiddle3Content,
						matchMiddle3Status);
				break;
			case R.id.matchDown1:
				setSensorValue(weidou, matchDown1Content,
						matchDown1Status);
				break;

			case R.id.matchDown2:
				setSensorValue(weidou, matchDown2Content,
						matchDown2Status);
				break;

			case R.id.matchDown3:
				setSensorValue(weidou, matchDown3Content,
						matchDown3Status);
				break;
			}

			updateRanks();
		}
	}

	/**
	 * 喵了个咪，自己写的自己都看不懂了
	 * 
	 * @author 曾凡
	 * @time 2014年11月6日 下午5:11:29
	 */
	private void updateRanks() {
		String temp = "";
		int tempRank = 0;
		Set<String> tempSet = new HashSet<String>();
		for (int j = 0; j < contents.size(); j++) {
			temp = contents.get(j).getText() + "";
			if (temp != null && !"".equals(temp)) {
				tempSet.clear();
				for (int i = 0; i < contents.size(); i++) {
					tempRank = temp.compareTo(contents.get(i)
							.getText() + "");
					if (tempRank < 0) {
						tempSet.add(contents.get(i).getText()
								+ "");
					}
				}
				/* +1显示第1、2、3、4、5、6、7、8、9名 */
				ranks.get(j).setText(
						"第" + (tempSet.size() + 1) + "名");
			}

		}
	}

	/**
	 * 设置传感器值
	 */
	private void setSensorValue(WeidouPo weidou,
			TextView matchContent, TextView matchStatus) {
		String status = "";
		float tempData = Float.valueOf(weidou.getDataContent());
		switch (weidou.getEquip()) {
		case EquipConstant.EQUIP_CH2O:// 甲醛
			if (tempData < UIConstant.CH2O_NORMAL_VALUE) {
				status = UIConstant.CH2O_CONTENT_STATUS_N;
			} else if (tempData >= UIConstant.CH2O_NORMAL_VALUE
					&& tempData <= UIConstant.CH2O_MIDDLE_VALUE) {
				status = UIConstant.CH2O_CONTENT_STATUS_D;
			} else if (tempData > UIConstant.CH2O_MIDDLE_VALUE
					&& tempData <= UIConstant.CH2O_HIGH_VALUE) {
				status = UIConstant.CH2O_CONTENT_STATUS_D;
			} else if (tempData > UIConstant.CH2O_HIGH_VALUE
					&& tempData <= UIConstant.CH2O_DEAD_VALUE) {
				status = UIConstant.CH2O_CONTENT_STATUS_DD;
			} else if (tempData >= UIConstant.CH2O_DEAD_VALUE) {
				status = UIConstant.CH2O_CONTENT_STATUS_DD;
			}
			break;
		case EquipConstant.EQUIP_ALCOHOL:// 酒精
			if (tempData <= UIConstant.ALCOHOL_LOW) {
				status = UIConstant.ALCOHOL_CONTENT_STATUS_N;
			} else if (tempData > UIConstant.ALCOHOL_LOW
					&& tempData < UIConstant.ALCOHOL_MIDDLE) {
				status = UIConstant.ALCOHOL_CONTENT_STATUS_D;
			} else if (tempData >= UIConstant.ALCOHOL_MIDDLE) {
				status = UIConstant.ALCOHOL_CONTENT_STATUS_DD;
			}
			break;
		case EquipConstant.EQUIP_CO:// 一氧化碳
			if (tempData <= UIConstant.CO_1) {
				status = UIConstant.C0_CONTENT_STATUS_N;
			} else if (tempData > UIConstant.CO_1
					&& tempData <= UIConstant.CO_2) {
				status = UIConstant.C0_CONTENT_STATUS_D;
			} else if (tempData > UIConstant.CO_2
					&& tempData <= UIConstant.CO_3) {
				status = UIConstant.C0_CONTENT_STATUS_D;
			} else if (tempData > UIConstant.CO_3
					&& tempData <= UIConstant.CO_4) {
				status = UIConstant.C0_CONTENT_STATUS_D;
			} else if (tempData > UIConstant.CO_4
					&& tempData <= UIConstant.CO_5) {
				status = UIConstant.C0_CONTENT_STATUS_DD;
			} else if (tempData > UIConstant.CO_5
					&& tempData <= UIConstant.CO_6) {
				status = UIConstant.C0_CONTENT_STATUS_DD;
			} else if (tempData >= UIConstant.CO_6) {
				status = UIConstant.C0_CONTENT_STATUS_DD;
			}
			break;
		}
		matchContent.setText(weidou.getDataContent());
		matchStatus.setText(status);
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
			dataUnit.setText(unit);
			mUpdateCurHandler.postDelayed(mUpdateCurThread, 100);
		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mUpdateCurHandler != null) {
			mUpdateCurHandler.removeCallbacks(mUpdateCurThread);
		}
	}
}
