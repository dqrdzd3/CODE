package com.hw.smarthome.ui.weather;

import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hw.smarthome.R;
import com.hw.smarthome.ui.home.adapter.homeup.pager.HomeFragmentUpPager;
import com.hw.smarthome.ui.weather.db.CityDB;
import com.hw.smarthome.ui.weather.db.po.City;
import com.hw.smarthome.ui.weather.util.CityUtils;
import com.hw.smarthome.view.list.PinnedHeaderListView;
import com.hw.smarthome.view.search.BladeView;
import com.hw.smarthome.view.search.CityAdapter;
import com.hw.smarthome.view.search.SearchCityAdapter;
import com.nostra13.universalimageloader.utils.L;

public class CityFragment extends DialogFragment implements
		TextWatcher, OnClickListener, CityUtils.EventHandler {
	private View parentView;
	private EditText mSearchEditText;
	// private Button mCancelSearchBtn;
	private ImageButton mClearSearchBtn;
	private View mCityContainer;
	private View mSearchContainer;
	private PinnedHeaderListView mCityListView;
	private BladeView mLetter;
	private ListView mSearchListView;
	private List<City> mCities;
	private SearchCityAdapter mSearchCityAdapter;
	private CityAdapter mCityAdapter;
	// 首字母集
	private List<String> mSections;
	// 根据首字母存放数据
	private Map<String, List<City>> mMap;
	// 首字母位置集
	private List<Integer> mPositions;
	// 首字母对应的位置
	private Map<String, Integer> mIndexer;
	private CityDB mCityDB;
	private CityUtils mCity;
	private InputMethodManager mInputMethodManager;

	private TextView mTitleTextView;
	private ImageView mBackBtn;
	private static CityFragment instance;
	private static HomeFragmentUpPager pager;

	public static CityFragment getInstance() {
		if (instance == null) {
			instance = new CityFragment();
		}
		return instance;
	}

	/**
	 * 传值就靠它了
	 * 
	 * @author 曾凡
	 * @param pager
	 * @time 2015年7月6日 下午8:30:15
	 */
	public static void setPager(HomeFragmentUpPager pager) {
		CityFragment.pager = pager;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CityUtils.mListeners.add(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		parentView = inflater.inflate(
				R.layout.ui_home_pager_weather_select_city,
				container, false);
		getDialog().getWindow().requestFeature(
				Window.FEATURE_NO_TITLE);
		getDialog().getWindow().setBackgroundDrawableResource(
				R.color.transparent);
		initView();
		initData();
		return parentView;
	}

	private void initView() {
		mTitleTextView = (TextView) parentView
				.findViewById(R.id.title_name);
		mBackBtn = (ImageView) parentView
				.findViewById(R.id.title_back);
		mBackBtn.setOnClickListener(this);

		mSearchEditText = (EditText) parentView
				.findViewById(R.id.search_edit);
		mSearchEditText.addTextChangedListener(this);
		mClearSearchBtn = (ImageButton) parentView
				.findViewById(R.id.ib_clear_text);
		mClearSearchBtn.setOnClickListener(this);

		mCityContainer = parentView
				.findViewById(R.id.city_content_container);
		mSearchContainer = parentView
				.findViewById(R.id.search_content_container);
		mCityListView = (PinnedHeaderListView) parentView
				.findViewById(R.id.citys_list);
		mCityListView.setEmptyView(parentView
				.findViewById(R.id.citys_list_empty));
		mLetter = (BladeView) parentView
				.findViewById(R.id.citys_bladeview);
		mLetter.setOnItemClickListener(new BladeView.OnItemClickListener() {

			@Override
			public void onItemClick(String s) {
				if (mIndexer.get(s) != null) {
					mCityListView.setSelection(mIndexer.get(s));
				}
			}
		});
		mLetter.setVisibility(View.GONE);
		mSearchListView = (ListView) parentView
				.findViewById(R.id.search_list);
		mSearchListView.setEmptyView(parentView
				.findViewById(R.id.search_empty));
		mSearchContainer.setVisibility(View.GONE);
		mSearchListView
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v,
							MotionEvent event) {
						mInputMethodManager.hideSoftInputFromWindow(
								mSearchEditText.getWindowToken(),
								0);
						return false;
					}
				});
		mCityListView
				.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(
							AdapterView<?> parent, View view,
							int position, long id) {
						L.i(mCityAdapter.getItem(position)
								.toString());
						startActivity(mCityAdapter
								.getItem(position));
					}
				});

		mSearchListView
				.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(
							AdapterView<?> parent, View view,
							int position, long id) {
						L.i(mSearchCityAdapter.getItem(position)
								.toString());
						startActivity(mSearchCityAdapter
								.getItem(position));
					}
				});
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ib_clear_text:
			if (!TextUtils.isEmpty(mSearchEditText.getText()
					.toString())) {
				mSearchEditText.setText("");
				mInputMethodManager.hideSoftInputFromWindow(
						mSearchEditText.getWindowToken(), 0);
			}
			break;
		case R.id.title_back:
			dismiss();
			break;
		default:
			break;
		}

	}

	private void initData() {
		mCity = new CityUtils(getActivity());
		mCityDB = mCity.getCityDB(getActivity());
		mInputMethodManager = (InputMethodManager) getActivity()
				.getSystemService(
						getActivity().INPUT_METHOD_SERVICE);
		if (mCity.isCityListComplite()) {
			mCities = mCity.getCityList();
			mSections = mCity.getSections();
			mMap = mCity.getMap();
			mPositions = mCity.getPositions();
			mIndexer = mCity.getIndexer();

			mCityAdapter = new CityAdapter(getActivity(),
					mCities, mMap, mSections, mPositions);
			mCityListView.setAdapter(mCityAdapter);
			mCityListView.setOnScrollListener(mCityAdapter);
			mCityListView
					.setPinnedHeaderView(LayoutInflater
							.from(getActivity())
							.inflate(
									R.layout.biz_plugin_weather_list_group_item,
									mCityListView, false));
			mLetter.setVisibility(View.VISIBLE);

		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start,
			int count, int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start,
			int before, int count) {
		mSearchCityAdapter = new SearchCityAdapter(
				getActivity(), mCities);
		mSearchListView.setAdapter(mSearchCityAdapter);
		mSearchListView.setTextFilterEnabled(true);
		if (mCities.size() < 1 || TextUtils.isEmpty(s)) {
			mCityContainer.setVisibility(View.VISIBLE);
			mSearchContainer.setVisibility(View.INVISIBLE);
			mClearSearchBtn.setVisibility(View.GONE);
		} else {
			mClearSearchBtn.setVisibility(View.VISIBLE);
			mCityContainer.setVisibility(View.INVISIBLE);
			mSearchContainer.setVisibility(View.VISIBLE);
			mSearchCityAdapter.getFilter().filter(s);
		}

	}

	private void startActivity(City city) {
		pager.setCity(city);
		dismiss();
	}

	@Override
	public void afterTextChanged(Editable s) {
	}

	@Override
	public void onCityComplite() {
		// 城市列表加载完的回调函数
		mCities = mCity.getCityList();
		mSections = mCity.getSections();
		mMap = mCity.getMap();
		mPositions = mCity.getPositions();
		mIndexer = mCity.getIndexer();

		mCityAdapter = new CityAdapter(getActivity(), mCities,
				mMap, mSections, mPositions);
		mLetter.setVisibility(View.VISIBLE);
		mCityListView.setAdapter(mCityAdapter);
		mCityListView.setOnScrollListener(mCityAdapter);
		mCityListView.setPinnedHeaderView(LayoutInflater.from(
				getActivity()).inflate(
				R.layout.biz_plugin_weather_list_group_item,
				mCityListView, false));
	}

	@Override
	public void onCityWeatherComplite() {

	}

	@Override
	public void onNetChange() {
	}
}
