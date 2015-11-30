package com.hw.smarthome.ui.discuss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hw.smarthome.R;
import com.hw.smarthome.po.S004PO;
import com.hw.smarthome.po.UserPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithAccount;
import com.hw.smarthome.server.deal.DealWithDiscuss;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.discuss.adapter.DiscussThemeListAdapter;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.special.ResideMenu.ResideMenu;
import com.zf.view.CircleImageView;

/**
 * 
 * 
 * 项目名称：SmartHome 类名称：DiscussThemeFragment 类描述：讨论区主题列表 创建人：lijing 创建时间：2014-7-4
 * 上午11:36:17 修改人： 修改时间： 修改备注：
 * 
 * @version
 * 
 */
public class DiscussThemeFragment extends Fragment {
	private static DiscussThemeFragment instance;
	private View discussThemeView;
	protected MainActivity mMainActivity;
	protected CircleImageView leftMenu;
	private ResideMenu resideMenu;
	public TextView uiHomeSensorName;

	private ListView lv;
	private DiscussThemeListAdapter mAdapter;
	DiscussThemeReceiver receiver;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				String data = (String) msg.obj;
				if (!TextUtils.isEmpty(data)) {
					List<S004PO> themeList = new Gson()
							.fromJson(
									data,
									new TypeToken<List<S004PO>>() {
									}.getType());
					if (themeList != null)
						mAdapter.setList(themeList);
				}
				break;
			}
		};
	};

	public static DiscussThemeFragment getInstance() {
		if (instance == null) {
			instance = new DiscussThemeFragment();
		}
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mMainActivity = (MainActivity) getActivity();
		// 注册广播接收器
		receiver = new DiscussThemeReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SmartHomeService.class.getName());
		getActivity().registerReceiver(receiver, filter);

	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		discussThemeView = inflater.inflate(
				R.layout.ui_discuss_theme, container, false);
		initViews();
		initMenuView();
		return discussThemeView;
	}

	private void initViews() {
		leftMenu = (CircleImageView) discussThemeView
				.findViewById(R.id.title_bar_left_menu);
		resideMenu = ((MainActivity) getActivity())
				.getResideMenu();
		uiHomeSensorName = (TextView) discussThemeView
				.findViewById(R.id.uiHomeSensorName);
		uiHomeSensorName.setTextColor(mMainActivity
				.getResources().getColor(R.color.black));

		lv = (ListView) discussThemeView
				.findViewById(R.id.uiDiscussTheme);
		mAdapter = new DiscussThemeListAdapter(getActivity());
		lv.setAdapter(mAdapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				S004PO item = mAdapter.getItem(position);
				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();
				DiscussDetailFragment fragment = new DiscussDetailFragment();
				fragment.setDiscussTheme(item);
				transaction
						.replace(R.id.main_fragment, fragment);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		});
		updateData(null);
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
		mMainActivity.getResideMenu().init(discussThemeView);
		mMainActivity
				.getResideMenu()
				.addIgnoredView(
						discussThemeView
								.findViewById(R.id.uiHomeLayout));
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

	public class DiscussThemeReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			try {
				Bundle bundle = intent.getBundleExtra("action");
				if (ServerConstant.SH01_05_01_01_01
						.equals(bundle.getString("name"))) {
					boolean result = bundle.getBoolean("result");
					if (result) {
						String jsonData = bundle
								.getString("data");
						updateData(jsonData);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void updateData(final String data) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				Message msg = new Message();
				if (TextUtils.isEmpty(data)) {
					String saveData = DealWithDiscuss.getData(
							getActivity(),
							ServerConstant.SH01_05_01_01_01);
					msg.obj = saveData;
				} else {
					msg.obj = data;
				}
				msg.what = 0;
				mHandler.sendMessage(msg);
			}
		});

		thread.start();

	}

	@Override
	public void onResume() {
		super.onResume();
		/* 登陆这个页面后从服务器获取最新的列表 */
		Map<String, String> map = new HashMap<String, String>();

		MainAcUtil.send2Service(getActivity(),
				ServerConstant.SH01_05_01_01_01, 0, map);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		getActivity().unregisterReceiver(receiver);
	}
}
