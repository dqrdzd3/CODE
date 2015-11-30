//package com.hw.smarthome.ui.home.adapter.homeup.adpter;
//
//import java.util.List;
//
//import android.app.Activity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.WebView;
//import android.widget.BaseAdapter;
//
//import com.hw.smarthome.R;
//import com.hw.smarthome.ui.home.adapter.homeup.po.HomeHistoryViewPo;
//import com.hw.smarthome.ui.home.constant.HomeConstant;
//import com.hw.smarthome.view.chart.ChartAction;
//import com.hw.smarthome.view.chart.ChartEntity;
//import com.hw.util.Ln;
//import com.hw.util.UIUtil;
//import com.hw.util.WebViewUtil;
//
///**
// * 历史列表的适配器
// * 
// * @author 曾凡
// * @time 2014年10月18日 下午1:47:51
// */
//public class HomeHistoryListAdapter extends BaseAdapter {
//	private LayoutInflater mInflater;
//	/** 业务数据库获取的传感器 */
//	private List<HomeHistoryViewPo> mHistoryList;
//	private Activity mContext;
//
//	public static HomeHistoryListAdapter getInstance(
//			Activity context, List<HomeHistoryViewPo> historyList) {
//		return new HomeHistoryListAdapter(context, historyList);
//	}
//
//	public HomeHistoryListAdapter(Activity context,
//			List<HomeHistoryViewPo> historyList) {
//		mInflater = LayoutInflater.from(context);
//
//		this.mHistoryList = historyList;
//		this.mContext = context;
//
//	}
//
//	@Override
//	public int getCount() {
//		return mHistoryList.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		return null;
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return 0;
//	}
//
//	@Override
//	public View getView(int position, View convertView,
//			ViewGroup parent) {
//
//		if (convertView == null) {
//			convertView = mInflater.inflate(
//					R.layout.ui_home_history_item, null);
//
//			WebView tempWebView = (WebView) convertView
//					.findViewById(R.id.homeHistoryListItem);
//			HomeHistoryViewPo tempData = null;
//			try {
//				tempData = mHistoryList.get(position);
//
//				tempWebView.setVisibility(View.VISIBLE);
//				initHistoryDatas(tempWebView,
//						tempData.getName(), tempData.getUnit(),
//						tempData.getValues());
//				WebViewUtil webUtil = new WebViewUtil();
//				webUtil.initWebview(tempWebView,
//						HomeConstant.HOME_HISTORY_ADDR);
//			} catch (Exception e) {
//				Ln.e(e, "初始化历史View异常");
//			}
//		}
//		return convertView;
//	}
//
//	/**
//	 * 初始化并为页面加载数据
//	 * 
//	 * @author 曾凡
//	 * @param webView
//	 * @param homeUnitCo2
//	 * @param string
//	 * @param args
//	 *            [传感器名称,参数单位,周一....日]
//	 * @time 2014年5月16日 下午2:18:15
//	 */
//	protected void initHistoryDatas(WebView webView,
//			String name, String unit, String[] args) {
//		try {
//			/* FIXME 以下为测试数据 */
//			ChartEntity entity = new ChartEntity();
//			int[] screen = UIUtil.getScreenMetrics(mContext);
//			entity.setWidth(UIUtil.px2dip(mContext, screen[0])
//					- 20 + "");
//			entity.setHeight("180");
//			entity.setName(name);
//			entity.setUnit(unit);
//			entity.setMonday(args[0]);
//			entity.setTuesday(args[1]);
//			entity.setWednesday(args[2]);
//			entity.setThursday(args[3]);
//			entity.setFriday(args[4]);
//			entity.setSaturday(args[5]);
//			entity.setSunday(args[6]);
//			ChartAction action = new ChartAction(mContext,
//					entity);
//
//			/* 禁止ScrollView的childview自动滑动到底部 */
//			webView.setFocusable(false);
//			/* 返回值 */
//			webView.addJavascriptInterface(action, "action");
//		} catch (Exception e) {
//			Ln.e(e, "将历史数据塞入图标时失败！");
//		}
//
//	}
//}
