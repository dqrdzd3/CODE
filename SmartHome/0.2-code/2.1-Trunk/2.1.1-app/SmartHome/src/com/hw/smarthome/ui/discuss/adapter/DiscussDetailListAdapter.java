package com.hw.smarthome.ui.discuss.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hw.smarthome.R;
import com.hw.smarthome.po.S005PO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
/**
 * 
 * 
 * 项目名称：SmartHome
 * 类名称：DiscussThemeListAdapter
 * 类描述：讨论区主题列表项适配器
 * 创建人：lijing
 * 创建时间：2014-7-4 下午1:40:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 
 *
 */
public class DiscussDetailListAdapter extends BaseAdapter {
	Context mContext;
	List<S005PO> list = new ArrayList<S005PO>();
	DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private ImageLoader imageLoader = ImageLoader.getInstance();

	public DiscussDetailListAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.weibo_head)
		.showImageForEmptyUri(R.drawable.weibo_head)
		.showImageOnFail(R.drawable.weibo_head)
		.cacheInMemory(false)
		.cacheOnDisk(false)
		.considerExifParams(false)
		.displayer(new RoundedBitmapDisplayer(20))
		.build();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	
	public List<S005PO> getList() {
		return list;
	}
	public void setList(List<S005PO> list) {
		this.list = list;
		notifyDataSetChanged();
	}
	@Override
	public S005PO getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(mContext).inflate(R.layout.ui_discuss_detail_item, null);	
		TextView themeView = (TextView) convertView.findViewById(R.id.content);
		TextView nameView = (TextView) convertView.findViewById(R.id.name);
		TextView timeView = (TextView) convertView.findViewById(R.id.time);
		
		ImageView imageView = (ImageView) convertView.findViewById(R.id.userImage);
		S005PO item = getItem(position);
		themeView.setText(item.getMa003());
		nameView.setText(item.getMa008());
		timeView.setText(item.getMa004());
		//if(!TextUtils.isEmpty(item.getMa009())){
			String imageUrl = ServerConstant.SERVER_BASE_URI+ServerConstant.DOWNLOADSTREAM+"?ma001="+item.getMa009();
			imageLoader.displayImage(imageUrl, imageView, options, animateFirstListener);
		
		//}
//		if (item.getMa009()!=null && item.getMa009().length>0) {
//			imageView.setImageBitmap(BitmapFactory
//					.decodeByteArray(item.getMa009(), 0,
//							item.getMa009().length));
//		}
		
//		String url = "https://lh3.googleusercontent.com/-D_5lNxnDN6g/URqu2Tk7HVI/AAAAAAAAAbs/p0ddca9W__Y/s1024/Lost%252520in%252520a%252520Field.jpg";
//		imageLoader.displayImage(url, imageView, options, animateFirstListener);
		return convertView;
	}
	
	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}
}
