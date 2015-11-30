package com.hw.hwsafe.smart.action;

import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.constants.SmartConstants;
import com.hw.hwsafe.smart.pojo.ScenePO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.ISceneService;
import com.hw.hwsafe.smart.service.IU001Service;
import com.hw.hwsafe.utils.PasswordUtil;
import com.hw.hwsafe.utils.UUIDGenerater;
import com.hwsensor.permission.service.IPermissionFacadeService;

public class SceneAction extends BaseAction {

	private ScenePO scenePO;
	@Autowired
	private IU001Service u001Service;

	private U001PO u001PO;
	@Autowired
	private ISceneService sceneService;

	public String doShow() {
		try {
			scenePO = sceneService.retrieveInstanceById(scenePO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}

	/*
	 * 获得该用户下的所有场景
	 */
	public String getAllScene() {
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");

		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "Scene");
		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "Scene");
		} else {
			u001PO = new U001PO();
			u001PO.setMa001(userId);
			u001PO.setMa010(sessionId);

			List<U001PO> list = null;
			try {
				list = u001Service.retrieveInstanceByPO(u001PO);
				if (list == null || list.size() == 0) {
					callbackDataPO = new CallbackDataPO("0", "非法用户", 0, null,
							null, "Scene");
				} else {
					try {
						ScenePO condition = new ScenePO();
						condition.setMa006(userId);
						List<ScenePO> SceneList = new LinkedList<ScenePO>();
						SceneList = sceneService.retrieveInstanceByPO(condition);
						//SceneList = sceneService.retrieveAllInstance();
						callbackDataPO = new CallbackDataPO("1", "获得成功",
								SceneList.size(), new Gson().toJson(SceneList),
								JSONArray.fromObject(SceneList), "Scene");
					} catch (Exception e) {
						e.printStackTrace();
						callbackDataPO = new CallbackDataPO("0", "操作失败", 0,
								null, "Scene");
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				callbackDataPO = new CallbackDataPO("0", "查找该用户错误", 0, null,
						null, "Scene");
			}

		}
		return JSON_DATA;
	}

	// 修改场景

	public String updateScene() {
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		String po = request.getParameter("PO");
		String id = request.getParameter("NEWID");
		
		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "Scene");
		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "Scene");
		}else if (StringUtils.isBlank(po)) {
			callbackDataPO = new CallbackDataPO("0",
					"参数不合法", 0, null, null, "Scene");
		} else {
			scenePO = new Gson()
			.fromJson(po, ScenePO.class);

			u001PO = new U001PO();
			u001PO.setMa001(userId);
			u001PO.setMa010(sessionId);

			List<U001PO> list = null;
			try {
				list = u001Service.retrieveInstanceByPO(u001PO);
				if (list == null || list.size() == 0) {
					callbackDataPO = new CallbackDataPO("0", "非法用户", 0, null,
							null, "Scene");
				} else {
		
//						scenePO.setMa006(userId);
//						ScenePO tempOldPO = sceneService.retrieveInstanceById(scenePO.getMa001());
//						ScenePO condi = new ScenePO();
//				
//						condi.setMa001(id);
					
						try {
//							if (scenePO.getMa002() != null){
//								sceneService.clearAirAllInstance(scenePO);
//								condi.setMa002(tempOldPO.getMa002());
//							}
//							if (scenePO.getMa003() != null){
//								sceneService.clearGasAllInstance(scenePO);
//								condi.setMa003(tempOldPO.getMa003());
//							}
							//sceneService.updateInstance(condi);
							sceneService.updateInstance(scenePO);
							ScenePO condition = new ScenePO();
							condition.setMa006(userId);
							List<ScenePO> SceneList = new LinkedList<ScenePO>();
							SceneList = sceneService.retrieveInstanceByPO(condition);
							callbackDataPO = new CallbackDataPO("1", "更新成功",
									SceneList.size(), new Gson().toJson(SceneList),
									JSONArray.fromObject(SceneList), "Scene");
						} catch (Exception e) {
							callbackDataPO = new CallbackDataPO("0", "操作失败", 0, null, "Scene");
							e.printStackTrace();
						}
				}
			}catch (Exception e1) {
					// TODO Auto-generated catch block
					callbackDataPO = new CallbackDataPO("0", "查找该用户错误", 0, null,
							null, "Scene");
				}	
		}
		return JSON_DATA;

	}

	// 添加场景
	public String insertScene() {
		String po = request.getParameter("PO");

		
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");

		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "Scene");
		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "Scene");
		}else if (StringUtils.isBlank(po)) {
			callbackDataPO = new CallbackDataPO("0",
					"参数不合法", 0, null, null, "Scene");
		}else {
			
			
			scenePO = new Gson()
			.fromJson(po, ScenePO.class);
			u001PO = new U001PO();
			u001PO.setMa001(userId);
			u001PO.setMa010(sessionId);

			List<U001PO> list = null;
			try {
				list = u001Service.retrieveInstanceByPO(u001PO);
				if (list==null || list.size()==0) {
					callbackDataPO = new CallbackDataPO("0", "非法用户", 0, null,
							null, "Scene");
				}else {
					try {
						List<ScenePO> tempPo = sceneService.retrieveInstanceByPO(scenePO);
						if (tempPo != null && tempPo.size()>0) {
							callbackDataPO = new CallbackDataPO("0", "该场景已添加", 0, null,
									"Scene");
						} else {
							scenePO.setMa001(UUIDGenerater.getUUID());
							scenePO.setMa006(userId);
							sceneService.insertInstance(scenePO);
							ScenePO condition = new ScenePO();
							condition.setMa006(userId);
							List<ScenePO> SceneList = new LinkedList<ScenePO>();
							SceneList = sceneService.retrieveInstanceByPO(condition);
							callbackDataPO = new CallbackDataPO("1", "添加成功",
									SceneList.size(), new Gson().toJson(SceneList),
									JSONArray.fromObject(SceneList), "Scene");
						}
			
					} catch (Exception e) {
						callbackDataPO = new CallbackDataPO("0", "操作失败", 0, null, "Scene");
						e.printStackTrace();
					}
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				callbackDataPO = new CallbackDataPO("0", "查找该用户错误", 0, null,
						null, "Scene");
			}	
		}

		return JSON_DATA;

	}
	public String changeSceneName(){
		String ma004 = request.getParameter("MA004");
		String ma001 = request.getParameter("MA001");
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");

		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "Scene");
		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "Scene");
		}else if (StringUtils.isBlank(ma001)) {
			callbackDataPO = new CallbackDataPO("0",
					"参数不合法", 0, null, null, "Scene");
		}else {
			u001PO = new U001PO();
			u001PO.setMa001(userId);
			u001PO.setMa010(sessionId);

			List<U001PO> list = null;
			try {
				list = u001Service.retrieveInstanceByPO(u001PO);
				if (list==null || list.size()==0) {
					callbackDataPO = new CallbackDataPO("0", "非法用户", 0, null,
							null, "Scene");
				}else {
						try {
							ScenePO tempPo = new ScenePO();
							tempPo.setMa001(ma001);
							tempPo.setMa004(ma004);
							sceneService.updateSceneName(tempPo);
							ScenePO condition = new ScenePO();
							condition.setMa006(userId);
							List<ScenePO> SceneList = new LinkedList<ScenePO>();
							SceneList = sceneService.retrieveInstanceByPO(condition);
							callbackDataPO = new CallbackDataPO("1", "修改成功",
									SceneList.size(), new Gson().toJson(SceneList),
									JSONArray.fromObject(SceneList), "Scene");
						} catch (Exception e) {
							callbackDataPO = new CallbackDataPO("0", "修改失败", 0, null, "Scene");
							e.printStackTrace();
						}
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				callbackDataPO = new CallbackDataPO("0", "查找该用户错误", 0, null,
						null, "Scene");
			}
		}
		return JSON_DATA;
		
	}

	// 删除场景
	public String deleteScene() {
		String ma001 = request.getParameter("MA001");
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");

		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "Scene");
		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "Scene");
		}else if (StringUtils.isBlank(ma001)) {
			callbackDataPO = new CallbackDataPO("0",
					"参数不合法", 0, null, null, "Scene");
		}else {
			u001PO = new U001PO();
			u001PO.setMa001(userId);
			u001PO.setMa010(sessionId);

			List<U001PO> list = null;
			try {
				list = u001Service.retrieveInstanceByPO(u001PO);
				if (list==null || list.size()==0) {
					callbackDataPO = new CallbackDataPO("0", "非法用户", 0, null,
							null, "Scene");
				}else {
						try {
							sceneService.deleteInstanceById(ma001);
							ScenePO condition = new ScenePO();
							condition.setMa006(userId);
							List<ScenePO> SceneList = new LinkedList<ScenePO>();
							SceneList = sceneService.retrieveInstanceByPO(condition);
							callbackDataPO = new CallbackDataPO("1", "删除成功",
									SceneList.size(), new Gson().toJson(SceneList),
									JSONArray.fromObject(SceneList), "Scene");
						} catch (Exception e) {
							callbackDataPO = new CallbackDataPO("0", "删除失败", 0, null, "Scene");
							e.printStackTrace();
						}
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				callbackDataPO = new CallbackDataPO("0", "查找该用户错误", 0, null,
						null, "Scene");
			}
		}
		return JSON_DATA;
	}

	public ScenePO getScenePO() {
		return scenePO;
	}

	public void setScenePO(ScenePO scenePO) {
		this.scenePO = scenePO;
	}

	public ISceneService getSceneService() {
		return sceneService;
	}

	public void setSceneService(ISceneService sceneService) {
		this.sceneService = sceneService;
	}

	public IU001Service getU001Service() {
		return u001Service;
	}

	public void setU001Service(IU001Service u001Service) {
		this.u001Service = u001Service;
	}

}
