package com.hw.smarthome.ui.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

import com.hw.smarthome.R;
import com.special.ResideMenu.ResideMenu;

public class ResideMenuSm extends ResideMenu {
	private static String TAG = ResideMenuSm.class
			.getSimpleName();
	private View mParent;

	public ResideMenuSm(Context context,String title) {
		super(context, title);
	}

	public void init(View parent) {
		mParent = parent;
	}

	public void setTitleImage(int src){
		super.setTitleImage(src);
	}
	public void setTitleImage(Bitmap src){
		super.setTitleImage(src);
	}
	public void setTel(String tel){
		super.setAcountTel(tel);
	}
	@Override
	public void openMenu(int direction) {
		super.openMenu(direction);
		clearIgnoredViewList();
		Log.i(TAG, "openMenu");
	}

	@Override
	public void closeMenu() {
		super.closeMenu();
		addIgnoredView(mParent.findViewById(R.id.uiHomeLayout));
		Log.i(TAG, "closeMenu");
	}
}
