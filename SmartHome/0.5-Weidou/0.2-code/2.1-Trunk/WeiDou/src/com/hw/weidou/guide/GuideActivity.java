package com.hw.weidou.guide;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hw.weidou.R;
import com.hw.weidou.ui.MainActivity;


/**
 * 
 * 
 * 项目名称：Hw2DCode4 类名称：GuideActivity 类描述： 首次启动引导界面 创建人：lijing 创建时间：2013-10-10
 * 上午8:59:31 修改人：lijing 修改时间：2013-10-10 上午8:59:31 修改备注：
 * 
 * @version
 * 
 */
public class GuideActivity extends Activity {
	private static final String tag = GuideActivity.class.getSimpleName();
	private ViewPager viewPager;
	private ArrayList<View> pageViews;
	private ImageView imageView,imageView2;
	private int[] imageResId; // 图片ID
	// 当前页码
	private int currentIndex;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		
		
		initInfo();
	}

	private void initInfo() {
	
		pageViews = new ArrayList<View>();
		imageResId = new int[]{R.drawable.guide_tip1,R.drawable.guide_tip2,R.drawable.guide_tip3,R.drawable.guide_tip4};
		viewPager = (ViewPager) findViewById(R.id.guidePages);
		 // 初始化图片资源  
        LayoutInflater viewInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
        int length = imageResId.length;
        for(int i=0;i<length;i++){
        	  View convertView = viewInflater.inflate(R.layout.item_guide, null);  
        	//	imageView2 = (ImageView)findViewById(R.id.imageView1);
              LinearLayout linearLayout = (LinearLayout) convertView  
                      .findViewById(R.id.guide_item);  
              linearLayout.setBackgroundResource(imageResId[i]);  
              pageViews.add(linearLayout);  
              if(i==length-1){
            	//  imageView2.setVisibility(View.GONE);
            	  Button btn = (Button) convertView.findViewById(R.id.start);  
                  btn.setVisibility(View.VISIBLE);  
                  btn.setOnClickListener(new OnClickListener() {  
            
                      public void onClick(View v) {  
                          // TODO Auto-generated method stub  
                    	  enterMain();  
                      }  
                  });  
              }
        }
        
        viewPager.setAdapter(new GuidePageAdapter());// 设置填充ViewPager页面的适配器 
	}

	class GuidePageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).removeView(pageViews.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).addView(pageViews.get(arg1));
			return pageViews.get(arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}
	}
	
	private void enterMain(){
		Intent intent = new Intent();		
		intent.setClass(this, MainActivity.class);

		Log.e(tag, "enterMain");

		startActivity(intent);
		finish();
	}

}
