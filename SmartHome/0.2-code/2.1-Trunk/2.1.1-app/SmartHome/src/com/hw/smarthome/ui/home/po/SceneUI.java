package com.hw.smarthome.ui.home.po;

/**
 * 场景UI的实体类
 * 
 * @author 曾凡
 * @time 2015年5月12日 下午4:41:21
 */
public class SceneUI {
	public SceneUI() {

	}

	public SceneUI(String[] sceneIds, String[] groupname,
			String[][] data, String[][] listinfo, String[] type) {
		super();
		this.sceneIds = sceneIds;
		this.groupname = groupname;
		this.data = data;
		this.listinfo = listinfo;
		this.type = type;
	}

	private String[] sceneIds;
	private String[] groupname;

	private String[][] data;

	private String[][] listinfo;

	private String[] type;

	public String[] getGroupname() {
		return groupname;
	}

	public void setGroupname(String[] groupname) {
		this.groupname = groupname;
	}

	public String[][] getData() {
		return data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

	public String[][] getListinfo() {
		return listinfo;
	}

	public void setListinfo(String[][] listinfo) {
		this.listinfo = listinfo;
	}

	public String[] getType() {
		return type;
	}

	public void setType(String[] type) {
		this.type = type;
	}

	public String[] getSceneIds() {
		return sceneIds;
	}

	public void setSceneIds(String[] sceneIds) {
		this.sceneIds = sceneIds;
	}

}
