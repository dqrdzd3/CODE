package com.hw.util.widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hw.smarthome.R;


public class SelectCustomPopupWindow extends PopupWindow {
	//private Button btn_start, btn_stop,
		
		private View mMenuView;
		private GridView mGrid;
		private List<ResolveInfo> mApps;
		private Uri picPath;
		private Context mContext;
		
		public SelectCustomPopupWindow(Activity context,Uri picPath,int layout) {
			super(context);
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mMenuView = inflater.inflate(layout, null);
			mContext = context;
			this.picPath = picPath;
//			for (int i = 0; i < btns.length; i++) {
//				Button btn = (Button)mMenuView.findViewById(btns[i]);
//				btn.setOnClickListener(itemsOnClick);
//			}
			
//			btn_quit = (Button) mMenuView.findViewById(R.id.btn_quit);
//			btn_quit.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					dismiss();
//					
//				}
//			});
			
//			btn_start.setOnClickListener(itemsOnClick);
//			btn_stop.setOnClickListener(itemsOnClick);
			
			this.setContentView(mMenuView);
			
			this.setWidth(LayoutParams.FILL_PARENT);
			
			this.setHeight(LayoutParams.WRAP_CONTENT);
		
			this.setFocusable(true);
		
			this.setAnimationStyle(R.style.AnimBottom);
			
			ColorDrawable dw = new ColorDrawable(0xb0000000);

			this.setBackgroundDrawable(dw);
			
			this.setOutsideTouchable(true);
			
//			mMenuView.setOnTouchListener(new OnTouchListener() {
//				
//				public boolean onTouch(View v, MotionEvent event) {
//					
//					int height = mMenuView.findViewById(R.id.pop_layout).getTop();
//					int y=(int) event.getY();
//					if(event.getAction()==MotionEvent.ACTION_UP){
//						if(y<height){
//							dismiss();
//						}
//					}				
//					return true;
//				}
//			});
			loadApps();
			mGrid = (GridView) mMenuView.findViewById(R.id.shareGrid);
			mGrid.setAdapter(new AppsAdapter());
			mGrid.setNumColumns(4);
			mGrid.setOnItemClickListener(new ShareListener());

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
//				targeted.setComponent(name);
				Intent chooserIntent = Intent.createChooser(
						targeted, mContext.getString(R.string.share));
				mContext.startActivity(chooserIntent);

			}
		}

		private void loadApps() {
			PackageManager pm = mContext.getPackageManager();
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
							.loadIcon(mContext.getPackageManager()));
					textView.setText(info.activityInfo
							.loadLabel(mContext.getPackageManager()));
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
