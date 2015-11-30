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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hw.smarthome.R;
import com.hw.smarthome.po.S006PO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithDiscuss;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.discuss.adapter.FeedbackListAdapter;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.smarthome.view.pop.progress.PopProgress;
import com.hw.util.DateUtils;
import com.hw.util.Ln;
import com.hw.util.UIUtil;

/**
 * 用户留言板
 * 
 * @author lijing
 * @time 2014-7-14 下午1:12:16
 */
public class FeedbackFragment extends Fragment {
	private static FeedbackFragment instance;
	private View mFeedBackView;
	private ListView lv;
	private FeedbackListAdapter mAdapter;
	int textNumLimit = 128;
	private EditText mFeedBackEt;
	TextView textNumFeedBackView;
	Map<String, String> paramMap = new HashMap<String, String>();
	DiscussThemeReceiver receiver;

	// private PopProgress popProgress = null;
	public static FeedbackFragment getInstance() {
		if (instance == null) {
			instance = new FeedbackFragment();
		}
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 注册广播接收器
		receiver = new DiscussThemeReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SmartHomeService.class.getName());
		getActivity().registerReceiver(receiver, filter);

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (UIUtil.networkConnectedHint(getActivity())) {
			/* 登陆这个页面后从服务器获取最新的列表 */
			MainAcUtil.send2Service(getActivity(),
					ServerConstant.SH01_05_01_02_02, 0, paramMap);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mFeedBackView = inflater
				.inflate(R.layout.ui_feedback, container, false);
		initViews();
		return mFeedBackView;
	}

	private void initViews() {
		// popProgress = PopProgress.getInstance(
		// getActivity(), mFeedBackView);
		// popProgress.hiddenProgerss();
		lv = (ListView) mFeedBackView.findViewById(R.id.list);
		mAdapter = new FeedbackListAdapter(getActivity());
		lv.setAdapter(mAdapter);
		textNumFeedBackView = (TextView) mFeedBackView
				.findViewById(R.id.text_num);
		String s = getString(R.string.text_num_limit_left, textNumLimit);
		textNumFeedBackView.setText(s);
		mFeedBackEt = (EditText) mFeedBackView.findViewById(R.id.content);
		mFeedBackEt.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				String str = mFeedBackEt.getText().toString();
				int strCount = str.length();
				int textNumLeft = textNumLimit - strCount;
				textNumFeedBackView.setText(getString(
						R.string.text_num_limit_left, textNumLeft));
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		Button postBtn = (Button) mFeedBackView.findViewById(R.id.post);
		postBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (UIUtil.networkConnectedHint(getActivity())) {
					post();
				}
			}
		});
		updateData(null, ServerConstant.SH01_05_01_02_02);
	}

	private void post() {
		String content = mFeedBackEt.getText().toString();
		if (TextUtils.isEmpty(content)) {
			mFeedBackEt.setError("必填");
			mFeedBackEt.requestFocus();
			return;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("MSG", content);
		MainAcUtil.send2Service(getActivity(), ServerConstant.SH01_05_01_02_01,
				0, map);
		// popProgress = PopProgress.getInstance(
		// getActivity(), mFeedBackView);
		// popProgress.setText("提交反馈中");
		// popProgress.showProgress();
	}

	public class DiscussThemeReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			try {
				Bundle bundle = intent.getBundleExtra("action");
				String name = bundle.getString("name");
				if (ServerConstant.SH01_05_01_02_02.equals(name)) {
					// if(popProgress!=null){
					// popProgress.hiddenProgerss();
					// }
					String jsonData = bundle.getString("data");
					updateData(jsonData, name);
				} else if (ServerConstant.SH01_05_01_02_01.equals(name)) {

					boolean result = bundle.getBoolean("result");
					if (result) {
						S006PO f = getFeedback();
						if (f != null) {
							List<S006PO> l = mAdapter.getList();
							l.add(f);
							mAdapter.setList(l);
						}
						Toast.makeText(getActivity(), "留言成功",
								Toast.LENGTH_SHORT).show();
						mFeedBackEt.setText("");
						mFeedBackEt.clearFocus();
						UIUtil.hideSoftKeyboard(getActivity());
						MainAcUtil.send2Service(getActivity(),
								ServerConstant.SH01_05_01_02_02, 0, paramMap);
						// popProgress.setText("查询最新数据");
					} else {
						// popProgress.hiddenProgerss();
						Toast.makeText(getActivity(), "留言失败",
								Toast.LENGTH_SHORT).show();
					}
				}
			} catch (Exception e) {
				Ln.e(e);
			}
		}

	}

	private void updateData(String data, String name) {
		if (TextUtils.isEmpty(data))
			data = DealWithDiscuss.getData(getActivity(), name);
		if (!TextUtils.isEmpty(data)) {
			List<S006PO> themeList = new Gson().fromJson(data,
					new TypeToken<List<S006PO>>() {
					}.getType());
			if (themeList != null)
				mAdapter.setList(themeList);
		}
	}

	private S006PO getFeedback() {
		String content = mFeedBackEt.getText().toString();
		if (TextUtils.isEmpty(content)) {
			return null;
		}

		S006PO feedback = new S006PO();
		feedback.setMa002(content);
		feedback.setMa003(DateUtils.getCurrentTime());
		return feedback;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		getActivity().unregisterReceiver(receiver);
	}
}
