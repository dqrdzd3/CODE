package com.hw.smarthome.ui.home.adapter.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hw.smarthome.R;
import com.hw.smarthome.po.S007PO;
import com.hw.smarthome.po.SensorAirDetail;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.SensorGasDetail;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.home.adapter.GridviewAdapter;
import com.hw.smarthome.ui.home.adapter.homeup.pager.solution.SolutionUtils;
import com.hw.smarthome.ui.home.themecartoon.po.CartoonInfo;
import com.hw.smarthome.ui.home.themecartoon.pop.CartoonSaidPopWindow;
import com.hw.smarthome.ui.home.themecartoon.util.ThemeCartoonUtil;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.smarthome.ui.sensor.util.SensorUtil;
import com.hw.smarthome.view.widget.MyGridView;
import com.hw.util.Ln;
import com.hw.util.UIUtil;

/**
 * @author 曾凡
 * @time 2014年7月11日 下午2:37:35
 */
@SuppressLint("NewApi")
public class RealTimeUtil {
	/** 是否显示话语 */

	public static boolean isShowTip = false;

	/** 当前传感器实时数据 */
	private static SensorDetail currentDetail;

	/**
	 * 更新实时数据的页面数据（任务调度/自动更新线程使用）
	 * 
	 * @author 曾凡
	 * @param context
	 * @param viewList
	 *            实时传感器的页面
	 * @param gridView
	 * @time 2014年7月11日 上午10:08:34
	 */
	public static void updateDetailView(Context context,
			List<View> viewList, List<View> gridViewList) {
		try {
			/* [SH01_01_01_03] 查看已发现设备 ，sensorList位置和viewLists */
			List<SensorDetail> sensorList = SensorUtil
					.getSensorDetails(context);
			if (sensorList == null) {
				Ln.i("没有查询到关联此用户的设备");
				return;
			}
			/* [SH01_02_02_01] 查看设备实时数据 */
			List<SensorDetail> detailList = HomeUtil
					.getSensorDetails(context);
			View sensorView = null;
			SensorDetail equip = null;
			for (int i = 0; i < viewList.size(); i++) {
				sensorView = viewList.get(i);
				equip = sensorList.get(i);
				updateDetailView(context, sensorView, equip,
						detailList,
						(MyGridView) gridViewList.get(i));
			}
		} catch (Exception e) {
			Ln.e(e, "更新实时数据的页面数据异常");
		}
	}

	/**
	 * 更新当前显示Page的实时数据的页面显示内容（OnPageChangeListener使用）
	 * 
	 * @author 曾凡
	 * @param context
	 * @param homeUpView
	 *            当前View页面的位置
	 * @param gridView
	 * @time 2014年7月11日 上午10:02:45
	 */
	public static void updateCurrentDetailView(Context context,
			View homeUpView, View gridView, SensorDetail equip) {
		/* [SH01_01_01_03] 查看已发现设备 ，sensorList位置和viewLists */
		List<SensorDetail> sensorList = SensorUtil
				.getSensorDetails(context);
		if (sensorList == null) {
			Ln.i("没有发现设备");
			return;
		}
		/* [SH01_02_02_01] 查看设备实时数据 */
		List<SensorDetail> detailList = HomeUtil
				.getSensorDetails(context);

		updateDetailView(context, homeUpView, equip, detailList,
				(GridView) gridView);

	}

	/**
	 * 更新实时数据的页面显示内容
	 * 
	 * @author 曾凡
	 * @param context
	 *            上下文
	 * @param sensorView
	 *            实时传感器的页面
	 * @param detailList
	 *            [SH01_02_02_01] 查看设备实时数据
	 * @param equip
	 *            [SH01_01_01_03] 查看已发现设备
	 * @param gridView
	 * @time 2014年7月11日 上午9:59:45
	 */
	private static void updateDetailView(Context context,
			View sensorView, SensorDetail equip,
			List<SensorDetail> detailList, GridView gridView) {
		try {
			/* 设置点击效果为透明 */
			int type = SmartHomeServiceUtil
					.getSensorTypeWithId(equip.getSensorId());

			// if (equip.getLocalSoftVersion()!=null){
			// if (equip.getLocalSoftVersion().trim().length()>0 &&
			// equip.getRemoteSoftVersion().trim().length()>0){
			// int localV = Integer.parseInt(equip.getLocalHardVersion(), 2);
			// int remoteV = Integer.parseInt(equip.getRemoteSoftVersion(), 2);
			// if (localV>remoteV){
			// SweetAlertDialog dialog = new SweetAlertDialog(context);
			// dialog.setTitleText(equip.getSensorId()+"有硬件更新");
			// dialog.show();
			// }
			//
			// }}

			if (type == SensorConstant.SENSOR_TYPE_AIR) {

				SensorAirDetail airDetail = HomeUtil
						.getAirSensorDetail(detailList, equip);

				if (airDetail == null) {
					Ln.w("没有查询到本页设备标识为[" + equip.getSensorId()
							+ "]的实时明细数据");
					// return;
					airDetail = new SensorAirDetail();
				}
				// updateTime(sensorView, airDetail.getCreateTime());
				RealTimeUtil.initAirView(context, airDetail,
						gridView);
			} else if (type == SensorConstant.SENSOR_TYPE_GAS) {
				SensorGasDetail gasDetail = HomeUtil
						.getGasSensorDetail(detailList, equip);
				if (gasDetail == null) {
					Ln.w("没有查询到本页设备标识为[" + equip.getSensorId()
							+ "]的实时明细数据");
					gasDetail = new SensorGasDetail();
					// return;
				}
				// updateTime(sensorView, gasDetail.getCreateTime());
				RealTimeUtil.initGasView(context, gasDetail,
						gridView);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化空气传感器实时数据的页面
	 * 
	 * @author 曾凡
	 * @param context
	 *            上下文
	 * @param type
	 *            传感器类型
	 * @param airDetail
	 *            [实时数据]
	 * @param gridView
	 *            实时数据展示区的View
	 * @time 2014年7月11日 下午2:41:49
	 */
	public static void initAirView(final Context context,
			final SensorAirDetail airDetail, GridView gridView) {

		final List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		/* 获取此传感器的全部的解决方案 */
		SensorDetail detail = new SensorDetail();
		detail.setSensorId(airDetail.getSensorId());
		detail.setAir(airDetail);
		Map<Integer, S007PO> solutions = SolutionUtils
				.getSolutionMap(detail);

		/* 温度 */
		setGridValue(
				solutions.get(UIConstant.SOLUTION_TEMPERATURE),
				airDetail.getSensorId(), lists,
				airDetail.getTemperature(),
				UIConstant.HOME_UNIT_NULL,
				R.drawable.ui_home_unit_temperature);
		/* 湿度 */
		setGridValue(
				solutions.get(UIConstant.SOLUTION_HUMIDITY),
				airDetail.getSensorId(), lists,
				airDetail.getHumidity(),
				UIConstant.HOME_UNIT_NULL,
				R.drawable.ui_home_unit_humidity);

		/* 二氧化碳 */
		setGridValue(solutions.get(UIConstant.SOLUTION_CO2),
				airDetail.getSensorId(), lists,
				airDetail.getCo2(), UIConstant.HOME_UNIT_NULL,
				R.drawable.ui_home_unit_co2);
		/* PM2.5 */
		setGridValue(solutions.get(UIConstant.SOLUTION_PM25),
				airDetail.getSensorId(), lists,
				airDetail.getPm25(), UIConstant.HOME_UNIT_NULL,
				R.drawable.ui_home_unit_pm25);
		/* VOC */
		setGridValue(solutions.get(UIConstant.SOLUTION_VOC),
				airDetail.getSensorId(), lists,
				airDetail.getVoc(), UIConstant.HOME_UNIT_NULL,
				R.drawable.ui_home_unit_voc);
		setGridValue(solutions.get(UIConstant.HOME_UNIT_NULL),
				"", lists, null, UIConstant.HOME_UNIT_NULL, 0);
		// }

		// /* ch2o */
		// setListValue(airDetail.getSensorId(), lists,
		// airDetail.getCh2o(), UIConstant.HOME_UNIT_NULL,
		// R.drawable.ui_home_unit_ch2o);
		//
		// /* C6H6 */
		// setListValue(airDetail.getSensorId(), lists,
		// airDetail.getC6h6(), UIConstant.HOME_UNIT_NULL,
		// R.drawable.ui_home_unit_c6h6);

		gridView.setNumColumns(1);
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) gridView
				.getLayoutParams(); // 取控件mGrid当前的布局参数
		linearParams.height = UIUtil.dip2px(context, 240);// 当控件的高强制设成75象素
		gridView.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件mGrid2
		final GridviewAdapter adapter = new GridviewAdapter(
				context, lists,
				R.layout.ui_home_page_detail_item_v2);
		gridView.setAdapter(adapter);
		gridView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		/* FIXME 此处注销点击事件 */
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {
				if (isShowTip) {
					SensorDetail detail = new SensorDetail();
					detail.setSensorId(airDetail.getSensorId());
					detail.setAir(airDetail);
					initItemClick(adapter, context, position,
							detail);
				}
				// s.scrollTo(0, gridviewHeight+position*heights);

			}
		});
	}

	/**
	 * 初始化空气传感器实时数据的页面
	 * 
	 * @author 曾凡
	 * @param context
	 *            上下文
	 * @param type
	 *            传感器类型
	 * @param gasDetail
	 *            [实时数据]
	 * @param gridView
	 *            实时数据展示区的View
	 * @time 2014年7月11日 下午2:41:49
	 */
	public static void initGasView(final Context context,
			final SensorGasDetail gasDetail, GridView gridView) {
		final List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		/* 获取此传感器的全部的解决方案 */
		SensorDetail detail = new SensorDetail();
		detail.setSensorId(gasDetail.getSensorId());
		detail.setGas(gasDetail);
		Map<Integer, S007PO> solutions = SolutionUtils
				.getSolutionMap(detail);

		int ch4TempImg = R.drawable.ui_home_unit_ch4;
		try {
			if (gasDetail.getCh4() != null
					&& !"".equals(gasDetail.getCh4())) {
				if (Double.valueOf(gasDetail.getCh4()) >= ThemeCartoonUtil.CH4_THRESHOLD) {
					ch4TempImg = R.drawable.ui_home_unit_ch4_high;
				}
			}
		} catch (NumberFormatException e) {
			Ln.e(e, gasDetail.getSensorId() + " 天然气转换异常");
		}
		/* 甲烷 */
		setGridValue(solutions.get(UIConstant.SOLUTION_CH4),
				gasDetail.getSensorId(), lists,
				gasDetail.getCh4(), UIConstant.HOME_UNIT_CH4,
				ch4TempImg);
		int coTempImg = R.drawable.ui_home_unit_co;
		try {
			if (gasDetail.getCo() != null
					&& !"".equals(gasDetail.getCo())) {
				if (Double.valueOf(gasDetail.getCo()) >= ThemeCartoonUtil.CO_THRESHOLD) {
					coTempImg = R.drawable.ui_home_unit_co_high;
				}
			}
		} catch (NumberFormatException e) {
			Ln.e(e, gasDetail.getSensorId() + " 一氧化碳转换异常");
		}
		/* 一氧化碳 */
		setGridValue(solutions.get(UIConstant.SOLUTION_CO),
				gasDetail.getSensorId(), lists,
				gasDetail.getCo(), UIConstant.HOME_UNIT_CO,
				coTempImg);
		// }
		gridView.setNumColumns(1);
		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) gridView
				.getLayoutParams(); // 取控件mGrid当前的布局参数
		linearParams.height = UIUtil.dip2px(context, 110);// 当控件的高强制设成75象素
		gridView.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件mGrid2
		final GridviewAdapter adapter = new GridviewAdapter(
				context, lists,
				R.layout.ui_home_page_detail_item_v2);
		gridView.setAdapter(adapter);
		gridView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		// gridView.setOnItemClickListener(new OnItemClickListener() {
		// @Override
		// public void onItemClick(AdapterView<?> parent,
		// View view, int position, long id) {
		// if (isShowTip) {
		// SensorDetail detail = new SensorDetail();
		// detail.setSensorId(gasDetail.getSensorId());
		// detail.setGas(gasDetail);
		//
		// initItemClick(adapter, context, position,
		// detail);
		// }
		// // ScrollView s=
		// // (ScrollView)parent.getRootView().findViewById(R.id.uiHomePageId);
		// // ViewPager view2 =
		// // (ViewPager)parent.getRootView().findViewById(R.id.homePager);
		// // ScrollView s=
		// //
		// (ScrollView)view2.getChildAt(view2.getCurrentItem()).findViewById(R.id.uiHomePageId);
		// // WebView w =
		// // (WebView)parent.getRootView().findViewById(R.id.coWebView);
		// // s.scrollTo(0, 600);
		// }
		// });
	}

	private static void initItemClick(GridviewAdapter adapter,
			Context context, int position, SensorDetail detail) {
		adapter.setSeclection(position);
		adapter.notifyDataSetChanged();
		try {
			CartoonSaidPopWindow mSaidPop = CartoonSaidPopWindow
					.getInstance(context);
			List<SensorDetail> sensorList = HomeUtil
					.getSensorDetails(MainActivity.mContext);
			List<CartoonInfo> cartoonList;
			if (RealTimeUtil.getCurrentDetail() == null) {
				cartoonList = ThemeCartoonUtil
						.getCartoonInfos(sensorList);
			} else {

				cartoonList = ThemeCartoonUtil
						.getCartoonInfos(detail);
			}
			CartoonInfo info = null;
			ImageView mImage = (ImageView) MainActivity.mContext
					.findViewById(R.id.uiHomeThemeImage);

			info = cartoonList.get(position);
			if (info != null) {

				Bitmap bitMap = UIUtil.readBitMap(
						MainActivity.mContext
								.getApplicationContext(), info
								.getImageId(), 1);

				mImage.setImageBitmap(bitMap);
				mSaidPop.getPopTv().setText(info.getTalkText());
				int y = mImage.getMeasuredHeight();
				// if (y > 0) {
				mSaidPop.getPopWindow().showAsDropDown(mImage,
						-200, -y - 500);
				// } else {
				// LayoutInflater inflater = (LayoutInflater)
				// MainActivity.mContext
				// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				// View layout = inflater.inflate(R.layout.ui_home, null);
				// View view1 = layout.findViewById(R.id.imageTop);
				// mSaidPop.showAtLocation(view1, Gravity.TOP,
				// view1.getLayoutParams().width,
				// view1.getLayoutParams().height);
				// }

				mSaidPop.getPopWindow().update();
			}
		} catch (Exception e) {
			Ln.e(e, "点击卡通形象异常！");
		}
	}

	// /**
	// * 更新更新时间
	// *
	// * @author 曾凡
	// * @param context
	// * @time 2014年7月23日 下午12:26:32
	// */
	// private static void updateTime(View view, String time) {
	// TextView updateTime = (TextView) view
	// .findViewById(R.id.lastestUpdateTime);
	// updateTime.setText("最后一次更新："+time);
	// }

	/**
	 * 设置设备的值
	 * 
	 * @author 曾凡
	 * @param lists
	 * @param value
	 *            参数值
	 * @param unitName
	 *            单位
	 * @param imgViewId
	 *            图片
	 * @time 2014年7月11日 下午3:01:35
	 */
	private static void setListValue(String sensorId,
			List<Map<String, Object>> lists, String value,
			String unitName, int imgViewId) {
		Map<String, Object> tempMap = null;
		value = (value == null || "".equals(value)) ? "0"
				: value;
		tempMap = new HashMap<String, Object>();
		tempMap.put("sensorId", sensorId);
		tempMap.put("img", Integer.valueOf(imgViewId));
		tempMap.put("text", value + " " + unitName);
		lists.add(tempMap);
	}

	private static void setGridValue(S007PO po, String sensorId,
			List<Map<String, Object>> lists, String value,
			String unitName, int imgViewId) {

		Map<String, Object> tempMap = null;
		value = (value == null || "".equals(value)) ? "0"
				: value;
		tempMap = new HashMap<String, Object>();
		if (po != null) {
			tempMap.put("solution", po.getMa005());
			tempMap.put(
					"status",
					SolutionUtils.getSensorStateName(
							po.getMa002(), po.getMa003()));
			tempMap.put("url", po.getMa008());
			updateProgress(tempMap, Integer.valueOf(value),
					po.getMa002(), po.getMa003());
		}
		tempMap.put("sensorId", sensorId);
		tempMap.put("img", Integer.valueOf(imgViewId));
		tempMap.put("text", value + " " + unitName);

		lists.add(tempMap);
	}

	private static void updateProgress(
			Map<String, Object> tempMap, int raw, int type,
			int state) {
		float degree = 0f;
		float value = Float.valueOf(raw + "");
		switch (type) {
		case UIConstant.SOLUTION_TEMPERATURE:
			if (raw <= 16) {
				state = UIConstant.SOLUTION_STATUS_HIGH;
			} else if (raw > 16 && raw <= 22) {
				state = UIConstant.SOLUTION_STATUS_MIDDLE;
			} else if (raw > 22 && raw <= 28) {
				state = UIConstant.SOLUTION_STATUS_LOW;
			} else if (raw > 28 && raw <= 36) {
				state = UIConstant.SOLUTION_STATUS_MIDDLE;
			} else {
				state = UIConstant.SOLUTION_STATUS_HIGH;
			}
			degree = value / 60;
			value = (int) (degree * 100);
			value = value > 30 ? value : value * 3;
			break;
		case UIConstant.SOLUTION_HUMIDITY:
			value = value > 20 ? value : value * 5;
			if (raw <= 20) {
				state = UIConstant.SOLUTION_STATUS_HIGH;
			} else if (raw > 20 && raw <= 40) {
				state = UIConstant.SOLUTION_STATUS_MIDDLE;
			} else if (raw > 40 && raw <= 65) {
				state = UIConstant.SOLUTION_STATUS_LOW;
			} else if (raw > 65 && raw <= 80) {
				state = UIConstant.SOLUTION_STATUS_MIDDLE;
			} else {
				state = UIConstant.SOLUTION_STATUS_HIGH;
			}
			break;
		case UIConstant.SOLUTION_CO2:
			degree = value / 5000;
			if (degree < 0.01) {
				value = (int) (degree * 1000);
			} else if (degree < 0.1) {
				value = (int) (degree * 500);
			} else if (degree < 0.2) {
				value = (int) (degree * 400);
			} else if (degree < 0.3) {
				value = (int) (degree * 300);
			} else if (degree < 0.4) {
				value = (int) (degree * 200);
			} else {
				value = (int) (degree * 100);
			}

			break;
		case UIConstant.SOLUTION_PM25:
			degree = value / 1000;
			if (degree < 0.1) {
				value = (int) (degree * 1000);
			} else {
				value = (int) (degree * 200);
			}
			break;
		case UIConstant.SOLUTION_VOC:
			value = value > 20 ? value : value * 5;
			break;
		case UIConstant.SOLUTION_CO:
			degree = value / 1000;
			if (degree < 0.1) {
				value = (int) (degree * 1000);
			} else {
				value = (int) (degree * 100);
			}
			break;
		case UIConstant.SOLUTION_CH4:
			value = value > 20 ? value : value * 5;
			break;
		}

		tempMap.put("progressCur", (int) value);
		tempMap.put("progressStatus", state);
	}

	/**
	 * 獲取傳感器的當前值，如果沒有則賦予初始化的第一個值
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年8月12日 上午11:11:59
	 */
	public static SensorDetail getCurrentDetail() {
		/* 如果是第一次進入，則分配第一個傳感器的內容 */
		List<SensorDetail> sensorList = SensorUtil
				.getSensorDetails(MainActivity.mContext);
		if (currentDetail == null) {

			SensorDetail detail = null;

			/* 更新当前的实时数据信息 */
			try {
				if (sensorList != null && sensorList.size() > 0) {
					detail = HomeUtil.getSensorDetail(
							MainActivity.mContext,
							sensorList.get(0));
					/* 保證存在傳感器的編號 */
					if (detail == null
							|| detail.getSensorId() == null
							|| "".equals(detail.getSensorId())) {
						detail = sensorList.get(0);
					}
					currentDetail = detail;

				}

			} catch (Exception e) {
				Ln.e(e, "設置第一個實時傳感器值異常");
			}
		} else {
			/* 判断当前传感器是否存在 */
			boolean isExist = false;
			for (SensorDetail sensor : sensorList) {
				if (sensor.getSensorId() != null
						&& currentDetail != null) {
					if (sensor.getSensorId().equals(
							currentDetail.getSensorId())) {
						isExist = true;
					}
				}
			}
			if (!isExist) {
				return null;
			}
			return currentDetail;
		}
		return currentDetail;
	}

	public static void setCurrentDetail(
			SensorDetail currentDetail) {
		RealTimeUtil.currentDetail = currentDetail;
	}
}
