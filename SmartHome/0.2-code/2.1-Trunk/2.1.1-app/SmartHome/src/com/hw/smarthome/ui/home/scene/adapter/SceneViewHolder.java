package com.hw.smarthome.ui.home.scene.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.hw.smarthome.R;

public class SceneViewHolder {
	private TextView sensorId;
	private ToggleButton ExpCol;
	private RelativeLayout toggleLayout;
	private RelativeLayout ChildLayout;
	private ImageView LayoutBackground;
	private TextView GroupHead;
	private TextView ListHead;
	private TextView ListDetail;
	private Button directions;
	private Button details;
	private ToggleButton mainCol;
	private Button delete;

	private static String GroupName;
	private static String ChildName;
	private static String list_info;

	public SceneViewHolder(View v) {
		this.sensorId = (TextView) v.findViewById(R.id.sensorId);
		this.LayoutBackground = (ImageView) v
				.findViewById(R.id.listbackground);
		this.ChildLayout = (RelativeLayout) v
				.findViewById(R.id.list_Item_layout);
		this.directions = (Button) v
				.findViewById(R.id.directions);
		this.details = (Button) v.findViewById(R.id.details);
		this.GroupHead = (TextView) v
				.findViewById(R.id.lblListHeader);
		this.ListHead = (TextView) v
				.findViewById(R.id.lblListItem);
		this.ListDetail = (TextView) v
				.findViewById(R.id.listItemInfo);
		this.toggleLayout = (RelativeLayout) v
				.findViewById(R.id.toggle_layout);
		this.ExpCol = (ToggleButton) v
				.findViewById(R.id.expand_colapse);
		this.mainCol = (ToggleButton) v
				.findViewById(R.id.sceneColapse);
		this.delete = (Button) v.findViewById(R.id.sceneDelete);
	}

	public ToggleButton getExpCol() {
		return ExpCol;
	}

	public void setExpCol(ToggleButton expCol) {
		ExpCol = expCol;
	}

	public RelativeLayout getToggleLayout() {
		return toggleLayout;
	}

	public void setToggleLayout(RelativeLayout toggleLayout) {
		this.toggleLayout = toggleLayout;
	}

	public RelativeLayout getChildLayout() {
		return ChildLayout;
	}

	public void setChildLayout(RelativeLayout childLayout) {
		ChildLayout = childLayout;
	}

	public ImageView getLayoutBackground() {
		return LayoutBackground;
	}

	public void setLayoutBackground(ImageView layoutBackground) {
		LayoutBackground = layoutBackground;
	}

	public TextView getGroupHead() {
		return GroupHead;
	}

	public void setGroupHead(TextView groupHead) {
		GroupHead = groupHead;
	}

	public TextView getListHead() {
		return ListHead;
	}

	public void setListHead(TextView listHead) {
		ListHead = listHead;
	}

	public TextView getListDetail() {
		return ListDetail;
	}

	public void setListDetail(TextView listDetail) {
		ListDetail = listDetail;
	}

	public Button getDirections() {
		return directions;
	}

	public void setDirections(Button directions) {
		this.directions = directions;
	}

	public Button getDetails() {
		return details;
	}

	public void setDetails(Button details) {
		this.details = details;
	}

	public ToggleButton getMainCol() {
		return mainCol;
	}

	public void setMainCol(ToggleButton mainCol) {
		this.mainCol = mainCol;
	}

	public Button getDelete() {
		return delete;
	}

	public void setDelete(Button delete) {
		this.delete = delete;
	}

	public static String getGroupName() {
		return GroupName;
	}

	public static void setGroupName(String groupName) {
		GroupName = groupName;
	}

	public static String getChildName() {
		return ChildName;
	}

	public static void setChildName(String childName) {
		ChildName = childName;
	}

	public static String getList_info() {
		return list_info;
	}

	public static void setList_info(String list_info) {
		SceneViewHolder.list_info = list_info;
	}

	public TextView getSensorId() {
		return sensorId;
	}

	public void setSensorId(TextView sensorId) {
		this.sensorId = sensorId;
	}

}
