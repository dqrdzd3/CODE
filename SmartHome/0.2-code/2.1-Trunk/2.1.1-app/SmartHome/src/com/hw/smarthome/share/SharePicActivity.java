package com.hw.smarthome.share;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hw.smarthome.R;

public class SharePicActivity extends FragmentActivity {

	private GridView mGrid;
	private List<ResolveInfo> mApps;
	private Uri picPath;
	private FragmentActivity mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		loadApps(); // do this in onresume?

		setContentView(R.layout.share_main);
		mGrid = (GridView) findViewById(R.id.shareGrid);
		mGrid.setNumColumns(4);
		mGrid.setAdapter(new AppsAdapter());
		mGrid.setOnItemClickListener(new ShareListener());
	}

	@Override
	protected void onResume() {
		super.onResume();
		picPath = (Uri) (getIntent().getExtras().get("picPath"));
	}

	private class ShareListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent,
				View view, int position, long id) {

			ResolveInfo launchable = mApps.get(position);
			ActivityInfo activity = launchable.activityInfo;
			ComponentName name = new ComponentName(
					activity.applicationInfo.packageName,
					activity.name);
			Intent targeted = new Intent(Intent.ACTION_SEND);
			targeted.setType("image/jpg");
			targeted.putExtra(Intent.EXTRA_STREAM, picPath);
			targeted.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			targeted.setPackage(name.getPackageName());
//			targeted.setComponent(name);
			Intent chooserIntent = Intent.createChooser(
					targeted, getString(R.string.share));
			mContext.startActivity(chooserIntent);

		}
	}

	private void loadApps() {
		PackageManager pm = getPackageManager();
		Intent main = new Intent(Intent.ACTION_MAIN, null);
		main.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> launchables = pm
				.queryIntentActivities(main, 0);
		Collections.sort(launchables,
				new ResolveInfo.DisplayNameComparator(pm));
		launchables = filter(launchables);
		mApps = launchables;
	}

	private List<ResolveInfo> filter(
			List<ResolveInfo> launchables) {
		ActivityInfo activityInfo = null;
		List<ResolveInfo> resList = new ArrayList<ResolveInfo>();
		boolean flag = false;
		String packageName = "";
		for (ResolveInfo info : launchables) {
			flag = false;
			activityInfo = info.activityInfo;
			packageName = activityInfo.packageName;
			if (packageName.contains("qq")) {
				flag = true;
			}
			if (packageName.contains("tencent")) {
				flag = true;
			} else if (packageName.contains("weibo")) {
				flag = true;
			} else if (packageName.contains("renren")) {
				flag = true;
			} else if (packageName.contains("yixin")) {
				flag = true;
			} else if (packageName.contains("kaixin")) {
				flag = true;
			} else if (packageName.contains("douban")) {
				flag = true;
			}
			if (flag) {
				resList.add(info);
			}
		}
		return resList;
	}

	public class AppsAdapter extends BaseAdapter {
		public AppsAdapter() {
		}

		public View getView(int position, View convertView,
				ViewGroup parent) {
			ImageView shareIcon = null;
			TextView textView = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext)
						.inflate(R.layout.share_item, null);
				shareIcon = (ImageView) convertView
						.findViewById(R.id.shareIcon);
				textView = (TextView) convertView
						.findViewById(R.id.shareLabel);
				ResolveInfo info = mApps.get(position);
				shareIcon.setImageDrawable(info.activityInfo
						.loadIcon(getPackageManager()));
				textView.setText(info.activityInfo
						.loadLabel(getPackageManager()));
			}

			return convertView;
		}

		public final int getCount() {
			return mApps.size();
		}

		public final Object getItem(int position) {
			return mApps.get(position);
		}

		public final long getItemId(int position) {
			return position;
		}
	}
}