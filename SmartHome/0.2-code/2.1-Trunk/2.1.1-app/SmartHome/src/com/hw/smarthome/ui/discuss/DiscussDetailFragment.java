package com.hw.smarthome.ui.discuss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hw.smarthome.R;
import com.hw.smarthome.po.S004PO;
import com.hw.smarthome.po.S005PO;
import com.hw.smarthome.po.UserPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithAccount;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.discuss.adapter.DiscussDetailListAdapter;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.DateUtils;
import com.hw.util.Ln;
import com.hw.util.UIUtil;
import com.zf.view.TitleBarView;

/**
 * 某一主题的讨论内容
 * 
 * @author lijing
 * @time 2014-7-15 下午5:24:51
 */
public class DiscussDetailFragment extends Fragment {
	private static DiscussDetailFragment instance;
	private View mView;
	private ListView lv;
	private DiscussDetailListAdapter mAdapter;
	private S004PO discussTheme;
	int textNumLimit = 128;
	private EditText mFeedBackEt;
	TextView textNumView;
	Map<String, String> paramMap = new HashMap<String, String>();
	DiscussDetailReceiver receiver;
	private View lv_header;

	private TitleBarView mTitleBarView;

	// private PopProgress popProgress = null;
	public static DiscussDetailFragment getInstance() {
		if (instance == null) {
			instance = new DiscussDetailFragment();
		}
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 注册广播接收器
		receiver = new DiscussDetailReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SmartHomeService.class.getName());
		getActivity().registerReceiver(receiver, filter);
		requestListData();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView = inflater.inflate(R.layout.ui_discuss_detail,
				container, false);
		initViews();
		return mView;
	}

	private void initViews() {
		try {
			mTitleBarView = (TitleBarView) mView
					.findViewById(R.id.title_bar);
			textNumView = (TextView) mView
					.findViewById(R.id.text_num);
			String s = getString(R.string.text_num_limit_left,
					textNumLimit);
			textNumView.setText(s);
			mFeedBackEt = (EditText) mView
					.findViewById(R.id.content);
			mFeedBackEt
					.addTextChangedListener(new TextWatcher() {

						@Override
						public void onTextChanged(
								CharSequence s, int start,
								int before, int count) {
							// TODO Auto-generated method stub
							String str = mFeedBackEt.getText()
									.toString();
							int strCount = str.length();
							int textNumLeft = textNumLimit
									- strCount;
							textNumView
									.setText(getString(
											R.string.text_num_limit_left,
											textNumLeft));
						}

						@Override
						public void beforeTextChanged(
								CharSequence s, int start,
								int count, int after) {

						}

						@Override
						public void afterTextChanged(Editable s) {

						}
					});
			lv_header = getActivity().getLayoutInflater()
					.inflate(R.layout.ui_discuss_theme_content,
							null);
			TextView themeView = (TextView) lv_header
					.findViewById(R.id.theme);
			themeView.setText("主题：" + discussTheme.getMa002());
			TextView themeContentView = (TextView) lv_header
					.findViewById(R.id.theme_content);
			themeContentView.setText(discussTheme.getMa003());
			lv = (ListView) mView
					.findViewById(R.id.uiDiscussDetail);
			lv.addHeaderView(lv_header);
			mAdapter = new DiscussDetailListAdapter(
					getActivity());
			lv.setAdapter(mAdapter);
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent,
						View view, int position, long id) {

				}
			});

			Button postBtn = (Button) mView
					.findViewById(R.id.post);
			postBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					post();
				}
			});
			initTitleView();
		} catch (Exception e) {
			Ln.e(e);
		}

	}

	private void initTitleView() {
		mTitleBarView.setCommonTitle(View.VISIBLE, View.GONE,
				View.GONE, View.GONE);
		mTitleBarView.setBtnLeft(
				R.drawable.boss_unipay_icon_back, R.string.back);
		mTitleBarView
				.setTitleText(R.string.ui_discuss_detail_title);
		mTitleBarView
				.setBtnLeftOnclickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						getActivity().onBackPressed();
					}
				});
	}

	public class DiscussDetailReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Bundle bundle = intent.getBundleExtra("action");
			if (ServerConstant.SH01_05_01_01_03.equals(bundle
					.getString("name"))) {
				// if(popProgress!=null){
				// popProgress.hiddenProgerss();
				// }
				String jsonData = bundle.getString("data");
				List<S005PO> list = new Gson().fromJson(
						jsonData, new TypeToken<List<S005PO>>() {
						}.getType());
				if (list != null)
					mAdapter.setList(list);
			} else if ((ServerConstant.SH01_05_01_01_02)
					.equals(bundle.getString("name"))) {
				boolean result = bundle.getBoolean("result");
				if (result) {
					S005PO discussContent = getDiscussContent();
					if (discussContent != null) {
						List<S005PO> l = mAdapter.getList();
						l.add(0, discussContent);
						mAdapter.setList(l);
						lv.setSelection(0);
					}
					Toast.makeText(getActivity(), "评论成功",
							Toast.LENGTH_SHORT).show();
					mFeedBackEt.setText("");
					mFeedBackEt.clearFocus();
					// popProgress.setText("查询最新评论");
					UIUtil.hideSoftKeyboard(getActivity());
					requestListData();
				} else {
					Toast.makeText(getActivity(), "评论失败",
							Toast.LENGTH_SHORT).show();
					// popProgress.hiddenProgerss();
				}
			}
		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		getActivity().unregisterReceiver(receiver);
	}

	private void post() {
		String content = mFeedBackEt.getText().toString();
		if (TextUtils.isEmpty(content)) {
			mFeedBackEt.setError("必填");
			mFeedBackEt.requestFocus();
			return;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("REPLYMSG", content);
		map.put("TITLEID", discussTheme.getMa001());
		// popProgress = PopProgress.getInstance(
		// getActivity(), mView);
		// popProgress.setText("提交评论中");
		// popProgress.showProgress();

		MainAcUtil.send2Service(getActivity(),
				ServerConstant.SH01_05_01_01_02, 0, map);
	}

	private S005PO getDiscussContent() {
		String content = mFeedBackEt.getText().toString();
		if (TextUtils.isEmpty(content)) {
			return null;
		}
		UserPO user = DealWithAccount.getUser(getActivity()
				.getApplicationContext());
		if (user != null) {
			S005PO discussContent = new S005PO();
			discussContent.setMa003(content);
			discussContent.setMa008(user.getMa008());
			discussContent.setMa004(DateUtils.getCurrentTime());
			return discussContent;
		}
		return null;
	}

	private void requestListData() {
		if (UIUtil.networkConnectedHint(getActivity())) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("TITLEID", discussTheme.getMa001());
			MainAcUtil.send2Service(getActivity(),
					ServerConstant.SH01_05_01_01_03, 0, map);
		}
	}

	public S004PO getDiscussTheme() {
		return discussTheme;
	}

	public void setDiscussTheme(S004PO discussTheme) {
		this.discussTheme = discussTheme;
	}

}
