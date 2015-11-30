package com.hw.smarthome.ui.home.scene.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ExpandableListAdapter;

import com.hw.smarthome.R;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.ui.home.HomeFragment;
import com.hw.smarthome.ui.home.scene.SceneUpdateActivity;
import com.hw.smarthome.ui.home.scene.constant.SceneConstant;
import com.hw.smarthome.ui.sensor.util.SensorUtil;
import com.hw.smarthome.ui.util.MainAcUtil;

public class CustomELVAdapter extends BaseExpandableListAdapter
		implements ExpandableListAdapter {

	private LayoutInflater vi;
	private String[][] data;
	private String[][] listinfo;
	private String[] groupname;
	private String[] groupIds;
	/**
	 * 场景类型 0000自定义，0001客厅，0002厨房，0003卧室
	 */
	private String[] type;
	private Context context;
	BounceInterpolator bounceInterpolator;
	View v;

	private static final int GROUP_ITEM_RESOURCE = R.layout.ui_home_list_group;
	private static final int CHILD_ITEM_RESOURCE = R.layout.ui_home_list_item;

	private Map<Integer, SceneViewHolder> sceneViewHolderMap = new HashMap<Integer, SceneViewHolder>();

	public CustomELVAdapter(Context context, Activity activity,
			String[] groupIds, String[] groupname,
			String[] type, String[][] listinfo, String[][] data) {
		this.context = context;
		this.groupIds = groupIds;
		this.groupname = groupname;
		this.type = type;
		this.listinfo = listinfo;
		this.data = data;

		vi = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		bounceInterpolator = new BounceInterpolator();
	}

	/**
	 * 更新列表数据
	 * 
	 * @author 曾凡
	 * @param groupname
	 * @param ImgBckgrnd
	 * @param listinfo
	 * @param data
	 * @time 2015年5月12日 下午5:40:16
	 */
	public void updateInfo(String[] groupIds,
			String[] groupname, String[] type,
			String[][] listinfo, String[][] data) {
		this.groupIds = groupIds;
		this.groupname = groupname;
		this.type = type;
		this.listinfo = listinfo;
		this.data = data;
	}

	@Override
	public View getChildView(final int groupPosition,
			final int childPosition, boolean isLastChild,
			View convertView, ViewGroup parent) {
		String child = getChild(groupPosition, childPosition);
		String list = getList(groupPosition, childPosition);
		v = convertView;
		v = vi.inflate(CHILD_ITEM_RESOURCE, null);
		final SceneViewHolder holder = new SceneViewHolder(v);

		if (child != null) {

			holder.getExpCol().setFocusable(false);
			/**
			 * TO SET CHILDLIST HEAD AND DETAIL *
			 * */
			holder.getSensorId().setText(
					list.substring(list.length() - 13,
							list.length() - 1));
			holder.getListHead().setText(Html.fromHtml(child));
			holder.getListDetail().setText(Html.fromHtml(list));

			/**
			 * EXPAND AND COLAPSE SECOND LEVEL CHILD LAYOUT WITH BOUNCE
			 * ANIMATION ; DEFINE SLIDEUP AND SLIDEDOWN ANIMATION PROPERTIES
			 * (SEE res/anim FOLDER) ; *PLAY WITH LAYOUT VISIBILITY PROPERTY ;
			 **/

			final Animation slidedown = AnimationUtils
					.loadAnimation(v.getContext(),
							R.anim.slide_down);
			final Animation slideup = AnimationUtils
					.loadAnimation(v.getContext(),
							R.anim.slide_up);

			/** SET BOUNCE INTERPOLATOR TO SLIDEDOWN **/
			slidedown.setInterpolator(bounceInterpolator);

			slideup.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
				}

				@Override
				public void onAnimationRepeat(Animation animation) {
				}

				@Override
				public void onAnimationEnd(Animation animation) {
					holder.getToggleLayout().setVisibility(
							View.GONE);
				}
			});

			/**
			 * EXPANDING AND COLAPSING SECOND LEVEL CHILD
			 * **/
			holder.getExpCol().setOnCheckedChangeListener(
					new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(
								CompoundButton buttonView,
								boolean isChecked) {
							// TODO Auto-generated method stub

							if (holder.getExpCol().isChecked()) {
								holder.getToggleLayout()
										.startAnimation(
												slidedown);
								holder.getToggleLayout()
										.setVisibility(
												View.VISIBLE);

							} else {

								holder.getToggleLayout()
										.startAnimation(slideup);

							}
						}

					});
			/**
			 * ON CLICK LISTENER FOR CHILD
			 * **/
			holder.getChildLayout().setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (holder.getExpCol().isChecked()) {
								holder.getExpCol().setChecked(
										false);
							} else {
								holder.getExpCol().setChecked(
										true);
							}

						}
					});

			/* 进入设备 */
			holder.getDetails().setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							List<SensorDetail> sensorList = SensorUtil
									.getSensorDetails(context);
							String hodlerId = holder
									.getSensorId().getText()
									+ "";
							int count = 0;
							for (SensorDetail sensorDetail : sensorList) {
								if (sensorDetail.getSensorId()
										.equals(hodlerId)) {
									HomeFragment.homeRadioGroup
											.check(R.id.homeDetailBtn);
									HomeFragment.homeFragment
											.setBackgroundResource(R.drawable.ui_home_bg);
									HomeFragment.mPager
											.setVisibility(View.VISIBLE);
									HomeFragment.homeDeviceList
											.setVisibility(View.GONE);
									HomeFragment.mPager
											.setCurrentItem(count);
								}
								count++;
							}

						}

					});
			/* 设备选项 */
			holder.getDirections().setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent intent = new Intent(context,
									SceneUpdateActivity.class);
							intent.putExtra("sensor", holder
									.getSensorId().getText());
							context.startActivity(intent);
						}

					});
		}
		v.setTag(holder);
		return v;
	}

	@Override
	public View getGroupView(int groupPosition,
			boolean isExpanded, View convertView,
			ViewGroup parent) {
		View v = convertView;
		SceneViewHolder holder;
		if (v == null) {
			v = vi.inflate(GROUP_ITEM_RESOURCE, null);
			holder = new SceneViewHolder(v);
			v.setTag(holder);
		} else {
			holder = (SceneViewHolder) v.getTag();
		}
		/* set一下button的焦点，button是会抢占Listview的焦点的。 */
		holder.getDelete().setFocusable(false);
		String sceneId = getSceneId(groupPosition);
		holder.getDelete().setOnClickListener(
				new DeleteListener(sceneId));
		sceneViewHolderMap.put(groupPosition, holder);
		holder.getDelete().setVisibility(View.GONE);
		if (isExpanded) {
			holder.getMainCol().setChecked(true);
		} else {

			holder.getMainCol().setChecked(false);
		}
		if (getGroupName(groupPosition) != null) {

			/** SET GROUP HEAD TEXT **/
			holder.getGroupHead().setText(
					getGroupName(groupPosition));

			holder.getLayoutBackground().setBackgroundResource(
					getImage(groupPosition));
		}
		return v;
	}

	private class DeleteListener implements OnClickListener {
		private String sceneId;

		public DeleteListener(String sceneId) {
			this.sceneId = sceneId;
		}

		@Override
		public void onClick(View v) {
			v.setVisibility(View.GONE);
			Map<String, String> paraMap = new HashMap<String, String>();
			paraMap.put("MA001", sceneId);
			MainAcUtil.send2Service(context,
					ServerConstant.SH01_06_04_04, 0, paraMap);
		}
	}

	public String[] getType() {
		return type;
	}

	public void setType(String[] type) {
		this.type = type;
	}

	public String getGroupName(int groupPosition) {
		return groupname[groupPosition];
	}

	public int getImage(int groupPosition) {
		String type = "";
		if (getType() != null && getType().length > 0) {
			type = getType()[groupPosition];
			/** 自定义 */
			if (SceneConstant.SCENE_TYPE_CUSTOM.equals(type)) {
				return R.drawable.ui_home_scene_default_custom;
			}
			/** 客厅 */
			else if (SceneConstant.SCENE_TYPE_LIVINGROOM
					.equals(type)) {
				return R.drawable.ui_home_scene_default_livingroom;
			}
			/** 厨房 */
			else if (SceneConstant.SCENE_TYPE_KITCHEN
					.equals(type)) {
				return R.drawable.ui_home_scene_default_kitchen;
			}
			/** 卧室 */
			else if (SceneConstant.SCENE_TYPE_BEDROOM
					.equals(type)) {
				return R.drawable.ui_home_scene_default_bedroom;
			} else {
				return R.drawable.ui_home_scene_default_unsign;
			}
		} else {
			return R.drawable.ui_home_scene_default_unsign;
		}
	}

	public String getSceneId(int groupPosition) {
		return groupIds[groupPosition];
	}

	@Override
	public String getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return data[groupPosition][childPosition];
	}

	public String getList(int groupPosition, int childPosition) {
		return listinfo[groupPosition][childPosition];

	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return data[groupPosition].length;
	}

	public int getGroupCount() {
		return groupname.length;
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition,
			int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getGroup(int groupPosition) {
		return "group-" + groupPosition;
	}

	public Map<Integer, SceneViewHolder> getSceneViewHolderMap() {
		return sceneViewHolderMap;
	}

	public void setSceneViewHolderMap(
			Map<Integer, SceneViewHolder> sceneViewHolderMap) {
		this.sceneViewHolderMap = sceneViewHolderMap;
	}

}
