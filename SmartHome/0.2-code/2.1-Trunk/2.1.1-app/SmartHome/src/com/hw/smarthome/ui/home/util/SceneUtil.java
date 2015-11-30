package com.hw.smarthome.ui.home.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;

import com.hw.smarthome.R;
import com.hw.smarthome.po.ScenePO;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.SensorDetailList;
import com.hw.smarthome.server.deal.DealWithScene;
import com.hw.smarthome.server.deal.DealWithSensor;
import com.hw.smarthome.ui.home.po.SceneUI;

/**
 * 场景工具类
 * 
 * @author 曾凡
 * @time 2015年5月12日 下午4:40:26
 */
public class SceneUtil {
	/**
	 * 删除场景的列表
	 * 
	 * @author 曾凡
	 * @param scene
	 * @param sensor
	 * @time 2015年5月14日 下午6:41:38
	 */
	public static void deleteSceneDevice(ScenePO scene,
			SensorDetail sensor) {
		String temp = "";
		String tempContent = "";
		if (scene != null && sensor != null) {
			/* 更新控制设备 */
			if (sensor.getCtrl() != null
					&& sensor.getCtrl().getDeviceId() != null
					&& !"".equals(sensor.getCtrl().getDeviceId())) {
				temp = scene.getMa003();
				tempContent = deleteDevice(temp, sensor);
				scene.setMa003(tempContent);
				scene.setMa002(scene.getMa002());
			} else {
				temp = scene.getMa002();
				tempContent = deleteDevice(temp, sensor);
				scene.setMa002(tempContent);
				scene.setMa003(scene.getMa003());
			}
		}
	}

	/**
	 * 删除列中的设备
	 * 
	 * @author 曾凡
	 * @param deviceList
	 * @param sensor
	 * @return
	 * @time 2015年5月14日 下午6:40:05
	 */
	private static String deleteDevice(String deviceList,
			SensorDetail sensor) {
		String res = "";
		if (deviceList != null && !"".equals(deviceList)
				&& deviceList.contains(sensor.getSensorId())) {
			String[] sensors = deviceList.split(";");
			List<String> sensorList = new LinkedList<String>();
			if (sensors != null && sensors.length > 1) {
				for (String sensorStr : sensors) {
					if (sensorStr != null
							&& sensorStr.contains(",")
							&& !sensorStr.contains(sensor
									.getSensorId())) {
						sensorList.add(sensorStr);
					}
				}
				StringBuilder sb = new StringBuilder();
				for (String str : sensorList) {
					sb.append(str).append(";");
				}
				res = sb.toString()
						.substring(0, sb.length() - 1);
			}
		}
		return res;
	}

	/**
	 * 更新场景的设备列表
	 * 
	 * @author 曾凡
	 * @param scene
	 * @param sensor
	 * @time 2015年5月14日 下午6:12:29
	 */
	public static void addSeceneDevice(ScenePO scene,
			SensorDetail sensor) {
		String temp = "";
		String tempContent = "";
		if (scene != null && sensor != null) {
			/* 更新控制设备 */
			if (sensor.getCtrl() != null
					&& sensor.getCtrl().getDeviceId() != null
					&& !"".equals(sensor.getCtrl().getDeviceId())) {
				tempContent = sensor.getCtrl().getDeviceType()
						+ "," + sensor.getSensorId();
				temp = scene.getMa003();
				if (temp == null || "".equals(temp)) {
					scene.setMa003(tempContent);
				} else {
					scene.setMa003(scene.getMa003() + ";"
							+ tempContent);
				}
				scene.setMa002(scene.getMa002());
			} else {
				temp = scene.getMa002();
				tempContent = "AR" + "," + sensor.getSensorId();
				if (temp == null || "".equals(temp)) {
					scene.setMa002(tempContent);
				} else {
					scene.setMa002(scene.getMa002() + ";"
							+ tempContent);
				}
				scene.setMa003(scene.getMa003());
			}

		}
	}

	/**
	 * 根据sensorId搜索关联的场景的信息
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2015年5月14日 下午1:15:51
	 */
	public static ScenePO findSeceneBySensorId(
			List<ScenePO> scenes, String sensorId) {
		boolean tempFlag = false;

		if (scenes != null && scenes.size() > 0) {
			for (ScenePO scene : scenes) {
				tempFlag = isContainSensorId(scene.getMa002(),
						sensorId);
				if (!tempFlag) {
					tempFlag = isContainSensorId(
							scene.getMa003(), sensorId);
				}
				if (tempFlag) {
					return scene;
				}
			}
		}
		return null;
	}

	/**
	 * 判断当前场景是否包含了这个设备
	 * 
	 * @author 曾凡
	 * @param tempStr
	 * @param sensorId
	 * @return
	 * @time 2015年5月14日 下午1:21:47
	 */
	private static boolean isContainSensorId(String tempStr,
			String sensorId) {
		if (tempStr != null && !"".equals(tempStr)) {
			return tempStr.contains(sensorId);
		}
		return false;
	}

	/**
	 * 获取场景实体布局
	 * 
	 * @author 曾凡
	 * @param context
	 * @return
	 * @time 2015年5月12日 下午5:38:12
	 */
	public static SceneUI getSceneEntry(Context context) {
		/* 获取注册设备列表 */
		SensorDetailList sensorsList = DealWithSensor
				.getSensorList(context);

		/* 获取场景列表 */
		List<ScenePO> scenes = DealWithScene.getScenes(context);
		SceneUI ui = initArrays(context, scenes);

		/* 单个场景关联的所有设备 */
		List<SensorDetail> singleSceneSensors = null;
		ScenePO tempScene = null;

		Map<String, SensorDetail> signedSensorMap = initSingMap(sensorsList);

		/* 匹配场景和设备列表 */
		if (scenes != null) {
			for (int i = 0; i < scenes.size(); i++) {
				tempScene = scenes.get(i);
				singleSceneSensors = new ArrayList<SensorDetail>();
				/* 关联A1和R1 */
				updateSceneSensors(tempScene.getMa002(),
						singleSceneSensors, sensorsList);
				/* 关联被控设备 */
				updateSceneSensors(tempScene.getMa003(),
						singleSceneSensors, sensorsList);
				/* 初始化设备信息 */
				updateSceneData(context, i, ui,
						singleSceneSensors);
				/* 获取所有已经添加过场景的设备 */
				for (SensorDetail sensorDetail : singleSceneSensors) {

					if (signedSensorMap.containsKey(sensorDetail
							.getSensorId())) {
						signedSensorMap.remove(sensorDetail
								.getSensorId());
					}
				}
			}
		}
		/* 添加未设置场景的设备 */
		updateUnSignSensors(context, scenes, ui, sensorsList,
				signedSensorMap);

		return ui;
	}

	private static Map<String, SensorDetail> initSingMap(
			SensorDetailList sensorsList) {
		Map<String, SensorDetail> signedSensorMap = new HashMap<String, SensorDetail>();
		if (sensorsList != null
				&& sensorsList.getSensorList() != null) {
			for (SensorDetail sensor : sensorsList
					.getSensorList()) {
				signedSensorMap
						.put(sensor.getSensorId(), sensor);
			}

		}
		return signedSensorMap;
	}

	/**
	 * 添加未设置场景的设备
	 * 
	 * @author 曾凡
	 * @param context
	 * @param scenes
	 * @param ui
	 * @param sensorsList
	 * @param signedSensorMap
	 * @time 2015年5月12日 下午6:33:23
	 */
	private static void updateUnSignSensors(Context context,
			List<ScenePO> scenes, SceneUI ui,
			SensorDetailList sensorsList,
			Map<String, SensorDetail> signedSensorMap) {
		List<SensorDetail> unSignSensorList = new ArrayList<SensorDetail>();
		/* 剔除所有已经添加过场景的设备 */

		for (Entry<String, SensorDetail> entry : signedSensorMap
				.entrySet()) {
			unSignSensorList.add(entry.getValue());
		}
		/* 添加最后一个场景为未分配场景 */
		int lastIndex = ((scenes != null) && (scenes.size() != 0)) ? (scenes
				.size()) : 0;
		updateSceneData(context, lastIndex, ui, unSignSensorList);
	}

	/**
	 * 初始化场景内容
	 * 
	 * @author 曾凡
	 * @param index
	 * @param ui
	 * @param singleSceneSensors
	 * @time 2015年5月12日 下午5:57:30
	 */
	private static void updateSceneData(Context context,
			int index, SceneUI ui,
			List<SensorDetail> singleSceneSensors) {
		/* 获取传感器的名称 */
		String[] singleData = new String[singleSceneSensors
				.size()];
		String[] singleListInfo = new String[singleSceneSensors
				.size()];
		for (int j = 0; j < singleSceneSensors.size(); j++) {
			singleData[j] = singleSceneSensors.get(j).getName();
		}
		for (int j = 0; j < singleSceneSensors.size(); j++) {
			singleListInfo[j] = context
					.getString(R.string.home_scene_sensor_id)
					+ "["
					+ singleSceneSensors.get(j).getSensorId()
					+ "]";// 对设备的描述
		}
		ui.getData()[index] = singleData;
		ui.getListinfo()[index] = singleListInfo;
	}

	/**
	 * 获取一个场景下所关联的设备
	 * 
	 * @author 曾凡
	 * @param rawSensorList
	 * @param singleSceneSensors
	 * @time 2015年5月12日 下午5:24:11
	 */
	private static void updateSceneSensors(String rawSensorList,
			List<SensorDetail> singleSceneSensors,
			SensorDetailList sensorsList) {

		String[] typeAndIds = null;
		String[] sensorStr = null;
		/* 遍历场景的设备ID缓存 */
		String tempSensorId = null;
		/* 设备类型,设备ID;deviceType,sensorId; */
		typeAndIds = rawSensorList.split(";");
		if (typeAndIds != null) {
			for (String sensor : typeAndIds) {
				sensorStr = sensor.split(",");
				if (sensorStr != null && sensorStr.length == 2) {
					// sensorDetail[0] FIXME 完成设备类型对场景列表的影响
					tempSensorId = sensorStr[1];
					updateSceneSenor(singleSceneSensors,
							sensorsList, tempSensorId);
				}
			}
		}
	}

	/**
	 * 对比添加场景对应的设备
	 * 
	 * @author 曾凡
	 * @param singleSceneSensors
	 * @param sensors
	 * @param sensorsList
	 * @param sensorId
	 * @time 2015年5月12日 下午6:18:13
	 */
	private static void updateSceneSenor(
			List<SensorDetail> singleSceneSensors,
			SensorDetailList sensorsList, String sensorId) {
		List<SensorDetail> sensors = (sensorsList != null) ? (sensorsList
				.getSensorList()) : null;
		/* 遍历一个场景中的所有关联的设备 */
		if (sensorsList != null) {
			for (SensorDetail sensorDetail : sensors) {
				if (sensorDetail.getSensorId().equals(sensorId)) {
					singleSceneSensors.add(sensorDetail);
				}
			}
		}
	}

	/**
	 * 初始化场景UI实体类
	 * 
	 * @author 曾凡
	 * @param context
	 * @param scenes
	 * @return
	 * @time 2015年5月12日 下午5:07:58
	 */
	private static SceneUI initArrays(Context context,
			List<ScenePO> scenes) {
		int groupNum = ((scenes != null) && (scenes.size() != 0)) ? scenes
				.size() + 1 : 1;
		String[] sceneIds = new String[groupNum];
		String[] groupname = new String[groupNum];
		String[][] data = new String[groupNum][];
		String[][] listinfo = new String[groupNum][];
		String[] type = new String[groupNum];
		if ((scenes != null) && (scenes.size() != 0)) {
			for (int i = 0; i < scenes.size(); i++) {
				sceneIds[i] = scenes.get(i).getMa001();
				groupname[i] = scenes.get(i).getMa004();
			}
		}
		/* 最后一个默认场景是“未分配” */
		groupname[groupNum - 1] = context
				.getString(R.string.scene_unsign);
		if ((scenes != null) && (scenes.size() != 0)) {
			for (int i = 0; i < scenes.size(); i++) {
				type[i] = scenes.get(i).getMa008();
			}
		}
		type[groupNum - 1] = "";
		SceneUI ui = new SceneUI(sceneIds, groupname, data,
				listinfo, type);
		return ui;
	}
}
