package com.hw.smarthome.ui.home;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hw.smarthome.R;
import com.hw.smarthome.po.HistoryAlarm;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.home.adapter.AlarmHistoryAdapter;
import com.hw.smarthome.ui.home.adapter.util.RealTimeUtil;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.smarthome.view.widget.PullToRefreshListView;
import com.hw.util.Ln;
import com.hw.util.StringUtil;
import com.hw.util.UIUtil;
import com.hw.util.context.AppContext;

/**
 * 历史报警
 * 
 * @author lijing
 * @time 2014-7-23 下午3:39:23
 */
public class AlarmHistoryFragment extends DialogFragment  {

	private static final String tag = AlarmHistoryFragment.class
			.getSimpleName();
	View layout;
	Activity mActivity;
	private PullToRefreshListView mlv;
	private View lvSurrounding_footer;
	private TextView lvSurrounding_foot_more;
	private ProgressBar lvSurrounding_foot_progress;
	private Handler lvHandler;
	int pageIndex = 1;
	AlarmHistoryAdapter mAdapter;
	private List<HistoryAlarm> lists = new ArrayList<HistoryAlarm>();
	private int lvSumData;
	private static final int HTTP_RESULT_ERROR = -100;
	private static final int HTTP_CONNECT_ERROR = -101;
	private static final int HTTP_RESULT_SUCCESS = 100;

	public static final int PAGE_SIZE = 10;
	AlarmHistoryReceiver receiver;
	private String sensorId;

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	private static AlarmHistoryFragment instance;

	public static AlarmHistoryFragment getInstance() {
		if (instance == null) {
			instance = new AlarmHistoryFragment();
		}
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		receiver = new AlarmHistoryReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SmartHomeService.class.getName());
		getActivity().registerReceiver(receiver, filter);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		layout = inflater
				.inflate(R.layout.ui_home_alarm_history,
						container, false);
		mActivity = getActivity();
		//
		initInfo(layout);
		getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
		return layout;
	}

	@Override
	public void onViewCreated(View view,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		layout = view;

	}

	private void initInfo(View v) {
		mlv = (PullToRefreshListView) layout
				.findViewById(R.id.list);

		lvSurrounding_footer = mActivity.getLayoutInflater()
				.inflate(R.layout.view_listview_footer, null);
		lvSurrounding_foot_more = (TextView) lvSurrounding_footer
				.findViewById(R.id.listView_foot_more);
		lvSurrounding_foot_progress = (ProgressBar) lvSurrounding_footer
				.findViewById(R.id.listView_foot_progress);
		ImageButton closeButton = (ImageButton)layout.findViewById(R.id.serversetting_close_button);
		closeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});
		mlv.addFooterView(lvSurrounding_footer);// 添加底部视图，必须在setAdapter之前
		
		mAdapter = new AlarmHistoryAdapter(mActivity);
		mlv.setAdapter(mAdapter);// 添加适配器
		mlv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0,
					View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (arg2 < 1)
					return;
				if (arg2 == lists.size() + 1) {
					int lvDataState = StringUtil.toInt(mlv
							.getTag());
					if (lvDataState == UIUtil.LISTVIEW_DATA_MORE) {
						mlv.setTag(UIUtil.LISTVIEW_DATA_LOADING);
						lvSurrounding_foot_more
								.setText(R.string.load_ing);
						lvSurrounding_foot_progress
								.setVisibility(View.VISIBLE);
						// 当前pageIndex
						pageIndex = lvSumData
								/ AppContext.PAGE_SIZE + 1;
						loadLvData(lvHandler,
								UIUtil.LISTVIEW_ACTION_SCROLL);
					}
					return;
				}

			}
		});
		// 监听刷新事件
		mlv.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
			@Override
			public void onRefresh() {
				pageIndex = 1;
				loadLvData(lvHandler,
						UIUtil.LISTVIEW_ACTION_REFRESH);
			}
		});
		// listView滑动事件
		mlv.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view,
					int scrollState) {
				mlv.onScrollStateChanged(view, scrollState);
				// 如果数据为空，则停止执行
				if (lists.isEmpty())
					return;

				// 判断是否滚动到底部
				boolean scrollEnd = false;
				try {
					if (view.getPositionForView(lvSurrounding_footer) == view
							.getLastVisiblePosition())
						scrollEnd = true;
				} catch (Exception e) {
					scrollEnd = false;
				}
				// 判断滑动状态，数据状态，判断滑动到底部时是否继续加载数据
				int lvDataState = StringUtil.toInt(mlv.getTag());
				if (scrollEnd
						&& lvDataState == UIUtil.LISTVIEW_DATA_MORE) {
					mlv.setTag(UIUtil.LISTVIEW_DATA_LOADING);
					lvSurrounding_foot_more
							.setText(R.string.load_ing);
					lvSurrounding_foot_progress
							.setVisibility(View.VISIBLE);
					// 当前pageIndex
					pageIndex = lvSumData / AppContext.PAGE_SIZE
							+ 1;
					loadLvData(lvHandler,
							UIUtil.LISTVIEW_ACTION_SCROLL);
				}
			}

			@Override
			public void onScroll(AbsListView view,
					int firstVisibleItem, int visibleItemCount,
					int totalItemCount) {
				mlv.onScroll(view, firstVisibleItem,
						visibleItemCount, totalItemCount);
			}
		});
		lvHandler = this.getLvHandler(mlv, mAdapter,
				lvSurrounding_foot_more,
				lvSurrounding_foot_progress,
				AppContext.PAGE_SIZE);
		loadLvData(lvHandler, UIUtil.LISTVIEW_ACTION_REFRESH);
	}

	private void loadLvData(final Handler handler,
			final int action) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			//this.sensorId = "10000002";
			//SensorDetail detail = RealTimeUtil.getCurrentDetail();
			//this.sensorId = detail.getSensorId();
			map.put("DRIVERID", this.sensorId);
			map.put("PAGENO", pageIndex + "");
			map.put("PAGESIZE", PAGE_SIZE + "");

			MainAcUtil.send2Service(getActivity(),
					ServerConstant.SH01_02_01_02, action, map);
		} catch (Exception e) {
			// TODO: handle exception
			Ln.e(e,"获取历史报警异常");
		}
		
	}

	private Handler getLvHandler(final PullToRefreshListView lv,
			final BaseAdapter adapter, final TextView more,
			final ProgressBar progress, final int pageSize) {
		return new Handler() {
			public void handleMessage(Message msg) {
				
					if (isAdded()) {
						if (msg.what == HTTP_RESULT_SUCCESS) {
							try{
							List<HistoryAlarm> l = new Gson()
									.fromJson(
											msg.obj.toString(),
											new TypeToken<List<HistoryAlarm>>() {
											}.getType());
							int dataSize = l.size();
							handleLvData(dataSize, msg.obj,
									msg.arg2, msg.arg1);
							if (dataSize < pageSize) {
								lv.setTag(UIUtil.LISTVIEW_DATA_FULL);
								adapter.notifyDataSetChanged();
								more.setText(R.string.load_full);
							} else if (dataSize == pageSize) {
								lv.setTag(UIUtil.LISTVIEW_DATA_MORE);
								adapter.notifyDataSetChanged();
								more.setText(R.string.load_more);
							}
							}catch(Exception e){
								Ln.e(e);
							}

						} else if (msg.what == HTTP_RESULT_ERROR) {
							lv.setTag(UIUtil.LISTVIEW_DATA_MORE);
							more.setText(R.string.load_error);
							// ((Exception) msg.obj).makeToast(this);
						} else if (msg.what == HTTP_CONNECT_ERROR) {// 联网失败
							if (msg.arg1 == UIUtil.LISTVIEW_ACTION_REFRESH) {
								// 刷新
								UIUtil.ToastMessage(
										mActivity,
										R.string.network_not_connected_view_local);
								more.setText(R.string.load_error);
							} else {
								UIUtil.ToastMessage(
										mActivity,
										R.string.network_not_connected);
								lv.setTag(UIUtil.LISTVIEW_DATA_MORE);
								more.setText(R.string.load_error);
							}

						}
						if (adapter.getCount() == 0
								&& msg.what == HTTP_RESULT_SUCCESS) {
							lv.setTag(UIUtil.LISTVIEW_DATA_EMPTY);
							more.setText(R.string.load_empty);
						}
						progress.setVisibility(ProgressBar.GONE);
						if (msg.arg1 == UIUtil.LISTVIEW_ACTION_REFRESH) {
							lv.onRefreshComplete(getString(R.string.pull_to_refresh_update)
									+ new Date()
											.toLocaleString());
							lv.setSelection(0);
						} else if (msg.arg1 == UIUtil.LISTVIEW_ACTION_CHANGE_CATALOG) {
							lv.onRefreshComplete();
							lv.setSelection(0);
						}

					}
				
			}

		};
	}

	private void handleLvData(int what, Object obj, int objtype,
			int actiontype) {

		switch (actiontype) {
		case UIUtil.LISTVIEW_ACTION_INIT:
		case UIUtil.LISTVIEW_ACTION_REFRESH:
			List<HistoryAlarm> rList = new Gson().fromJson(
					obj.toString(),
					new TypeToken<List<HistoryAlarm>>() {
					}.getType());

			lvSumData = what;
			lists.clear();
			lists = rList;
			mAdapter.setData(lists);
			break;
		case UIUtil.LISTVIEW_ACTION_CHANGE_CATALOG:

			@SuppressWarnings("unchecked")
			List<HistoryAlarm> vList = new Gson().fromJson(
					obj.toString(),
					new TypeToken<List<HistoryAlarm>>() {
					}.getType());
			lvSumData = what;
			vList.clear();
			vList.addAll(vList);
			break;

		case UIUtil.LISTVIEW_ACTION_SCROLL:

			List<HistoryAlarm> sList = new Gson().fromJson(
					obj.toString(),
					new TypeToken<List<HistoryAlarm>>() {
					}.getType());
			lvSumData += what;
			if (lists.size() > 0) {
				for (HistoryAlarm vf : sList) {
					lists.add(vf);
				}
			} else {
				lists.addAll(sList);
			}

			break;
		default:
			break;
		}
	}

	public class AlarmHistoryReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Bundle bundle = intent.getBundleExtra("action");
			String name = bundle.getString("name");
			if (ServerConstant.SH01_02_01_02.equals(name)) {

				Message msg = new Message();
				boolean result = bundle.getBoolean("result");
				msg.arg1 = bundle.getInt("type");

				if (result) {
					String jsonData = bundle.getString("data");
					msg.obj = jsonData;
					msg.what = HTTP_RESULT_SUCCESS;
				} else {
					msg.what = HTTP_RESULT_ERROR;
				}

				lvHandler.sendMessage(msg);
			}
		}

	}
}
