package com.hwsensor.permission.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;
import com.hwsensor.permission.action.SysResourceAction;
import com.hwsensor.permission.dao.ISysModuleDao;
import com.hwsensor.permission.dao.ISysOperationDao;
import com.hwsensor.permission.dao.ISysResourceDao;
import com.hwsensor.permission.pojo.SysModulePO;
import com.hwsensor.permission.pojo.SysOperationPO;
import com.hwsensor.permission.pojo.SysResourcePO;
import com.hwsensor.permission.service.ISysResourceService;

@SuppressWarnings("unchecked")
public class SysResourceServiceImpl extends BaseServiceImpl implements
		ISysResourceService {

	@Autowired
	private ISysResourceDao sysResourceDao;

	@Autowired
	private ISysModuleDao sysModuleDao;

	@Autowired
	private ISysOperationDao sysOperationDao;

	private String errMsg;

	@Override
	public Integer insertSysResource(SysResourcePO sysResourcePO)
			throws SystemException {
		return sysResourceDao.insertSysResource(sysResourcePO);
	}

	@Override
	public Integer updateSysResource(SysResourcePO sysResourcePO)
			throws SystemException {
		return sysResourceDao.updateSysResource(sysResourcePO);
	}

	@Override
	public Integer updateSysResourceByMap(Map map) throws SystemException {
		return sysResourceDao.updateSysResourceByMap(map);
	}

	@Override
	public Integer deleteSysResource(SysResourcePO sysResourcePO)
			throws SystemException {
		return sysResourceDao.deleteSysResource(sysResourcePO);
	}

	@Override
	public Integer deleteSysResourceByMap(Map map) throws SystemException {
		return sysResourceDao.deleteSysResourceByMap(map);
	}

	@Override
	public List<SysResourcePO> retrieveByCondition(Map map)
			throws SystemException {
		return sysResourceDao.retrieveByCondition(map);
	}

	@Override
	public SysResourcePO retrieveSysResourceByMap(Map map)
			throws SystemException {
		return sysResourceDao.retrieveSysResourceByMap(map);
	}

	@Override
	public void deleteSysResourceBP(SysResourceAction sysResourceAction)
			throws SystemException {
		try {
			HttpServletRequest request = sysResourceAction.getRequest();
			String idstr = request.getParameter("ids");
			idstr = idstr == null ? "" : idstr;

			String[] ids = idstr.split(",");

			for (int i = 0; i < ids.length; i++) {
				String id = ids[i];
				if (id.isEmpty()) {
					continue;
				}

				SysResourcePO sysResourcePO = new SysResourcePO();
				sysResourcePO.setUUID(id);
				sysResourceAction.setSysResourcePO(sysResourcePO);

				// 1.判断是否存在
				SysResourcePO oldSysResourcePO = retrieveSysResourceBP(sysResourceAction);

				String IS_DEFAULT = oldSysResourcePO.getIS_DEFAULT();

				String resourceName = oldSysResourcePO.getRESOURCE_NAME();

				if (IS_DEFAULT.equals("0")) {
					errMsg = "『" + resourceName + "』是默认资源，不可删除！";
					throw new SystemException(errMsg);
				}

				deleteSysResource(oldSysResourcePO);

			}

		} catch (Exception e) {
			if (getErrMsg() == null || getErrMsg().isEmpty()) {
				sysResourceAction.getMessage().set(Constants.MSG_ERROR,
						"删除失败！", Constants.SERVER_MSG);
			} else {
				sysResourceAction.getMessage().set(Constants.MSG_ERROR,
						getErrMsg(), Constants.SERVER_MSG);
			}
			throw new SystemException(e.getMessage());
		}
		sysResourceAction.getMessage().set(Constants.MSG_OK, "删除成功！",
				Constants.SERVER_MSG);
		return;
	}

	@Override
	public void updateSysResourceBP(SysResourceAction sysResourceAction)
			throws SystemException {
		// 1.判断是否存在，不存在抛出异常，存在取出原始信息

		SysResourcePO oldSysResourcePO = retrieveSysResourceBP(sysResourceAction);
		try {
			String isDefault = oldSysResourcePO.getIS_DEFAULT();

			String resourceName = oldSysResourcePO.getRESOURCE_NAME();
			if (isDefault.equals("0")) {
				sysResourceAction.getMessage().set(Constants.MSG_INFO,
						"『" + resourceName + "』是默认资源，不可修改！",
						Constants.SERVER_MSG);
				return;
			}

			SysResourcePO sysResourcePO = sysResourceAction.getSysResourcePO();

			UserPO userPO = sysResourceAction.getSessionUserPO();
			String CREATOR = userPO.getId();

			Date CREATE_DATE = DateTimeUtils.getCurFormatDate();

			oldSysResourcePO.setRESOURCE_NAME(sysResourcePO.getRESOURCE_NAME());
			oldSysResourcePO.setURL(sysResourcePO.getURL());
			oldSysResourcePO.setBIND_FUNC(sysResourcePO.getBIND_FUNC());
			oldSysResourcePO.setCREATOR(CREATOR);
			oldSysResourcePO.setCREATE_DATE(CREATE_DATE);

			updateSysResource(oldSysResourcePO);
		} catch (Exception e) {
			sysResourceAction.getMessage().set(Constants.MSG_ERROR, "修改失败！",
					Constants.SERVER_MSG);
			throw new SystemException("修改失败!");
		}
		sysResourceAction.getMessage().set(Constants.MSG_OK, "修改成功！",
				Constants.SERVER_MSG);
		return;
	}

	@Override
	public SysResourcePO retrieveSysResourceBP(
			SysResourceAction sysResourceAction) throws SystemException {
		SysResourcePO sysResourcePO = sysResourceAction.getSysResourcePO();

		String UUID = sysResourcePO.getUUID();

		Map map = new HashMap();
		map.put("UUID", UUID);

		SysResourcePO resourcePO = retrieveSysResourceByMap(map);
		if (resourcePO == null) {
			sysResourceAction.getMessage().set(Constants.MSG_ERROR,
					"信息已不存在，请刷新后重试！", Constants.SERVER_MSG);
			throw new SystemException("信息已不存在，请刷新后重试！");
		}
		return resourcePO;
	}

	@Override
	public void insertSysResourceBP(SysResourceAction sysResourceAction)
			throws SystemException {
		try {
			SysResourcePO sysResourcePO = sysResourceAction.getSysResourcePO();

			UserPO userPO = sysResourceAction.getSessionUserPO();

			String CREATOR = userPO.getId();// 创建人
			Date CREATE_DATE = DateTimeUtils.getCurFormatDate();// 创建日期

			String UUID = UUIDGenerater.getUUID();// 生成主键

			sysResourcePO.setUUID(UUID);

			sysResourcePO.setIS_DEFAULT("1");// 自定义资源
			sysResourcePO.setCREATOR(CREATOR);
			sysResourcePO.setCREATE_DATE(CREATE_DATE);

			// 添加资源
			insertSysResource(sysResourcePO);
		} catch (Exception e) {
			e.printStackTrace();
			sysResourceAction.getMessage().set(Constants.MSG_ERROR, "添加失败！",
					Constants.SERVER_MSG);
			throw new SystemException("添加失败！");
		}
		sysResourceAction.getMessage().set(Constants.MSG_OK, "添加成功！",
				Constants.SERVER_MSG);
		return;
	}

	@Override
	public void retrieveSysResourceList(SysResourceAction sysResourceAction)
			throws SystemException {
		HttpServletRequest request = sysResourceAction.getRequest();
		String MODULE_OPERA_UUID = request.getParameter("id");

		Map map = new HashMap();
		map.put("MODULE_OPERA_UUID", MODULE_OPERA_UUID);

		List<SysResourcePO> sysResourcePOs = retrieveByCondition(map);

		sysResourceAction.setResourceList(sysResourcePOs);
	}

	@Override
	public void buildTree(SysResourceAction sysResourceAction)
			throws SystemException {
		JSONArray jsonArray = new JSONArray();
		// 1.查询所有有效的目录
		Map moduleMap = new HashMap();
		// moduleMap.put("MENU_TYPE", "10");
		moduleMap.put("IS_VALID", "10");
		List<SysModulePO> sysModulePOs = sysModuleDao
				.retrieveByCondition(moduleMap);

		// 2.将所有有效目录构成目录树
		JSONArray moduleArr = buildModuleTreeByList(sysModulePOs);

		jsonArray.addAll(moduleArr);

		for (int i = 0; i < sysModulePOs.size(); i++) {
			SysModulePO modulePO = sysModulePOs.get(i);

			String moduleUUID = modulePO.getUUID();// 目录主键
			String moduleType = modulePO.getMENU_TYPE();// 类型

			if (moduleType == null || moduleType.equals("10")) {
				continue;
			}
			//
			// //3.查询每个目录下的所有有效页面
			// Map pageMap = new HashMap();
			// pageMap.put("PARENT_UUID", moduleUUID);
			// pageMap.put("MENU_TYPE", "20");
			// pageMap.put("IS_VALID", "10");
			//
			// List<SysModulePO> sysPages =
			// sysModuleDao.retrieveByCondition(pageMap);
			//
			// //4.将每个目录下所有有效的页面构成目录树
			// JSONArray pageArr = buildModuleTreeByList(sysPages);
			//
			// jsonArray.addAll(pageArr);

			// for (int j = 0; j < sysPages.size(); j++) {
			// SysModulePO pagePO = sysPages.get(j);
			// String pageUUID = pagePO.getUUID();//页面主键

			// 5.查询每个页面下所有有效的操作
			Map operMap = new HashMap();
			operMap.put("MENU_UUID", moduleUUID);
			operMap.put("IS_VALID", "10");

			List<SysOperationPO> sysOperationPOs = sysOperationDao
					.retrieveByCondition(operMap);

			// 6.将每个页面下有效的操作构成操作树

			JSONArray operArr = buildOperTreeByList(sysOperationPOs);

			jsonArray.addAll(operArr);

			// }
		}
		System.out.println(jsonArray.toString());
		sysResourceAction.setOperTree(jsonArray.toString());
	}

	@Override
	public JSONArray buildModuleTreeByList(List<SysModulePO> list)
			throws SystemException {
		JSONArray jsonArray = new JSONArray();

		// 2.将所有有效目录构成目录树
		for (int i = 0; i < list.size(); i++) {
			SysModulePO modulePO = list.get(i);

			String moduleUUID = modulePO.getUUID();// 目录主键
			String modulePARENT_UUID = modulePO.getPARENT_UUID();// 目录父目录
			
			// 名称上加上备注以区分
			String remark = modulePO.getREMARK();
			String moduleMENU_NAME = modulePO.getMENU_NAME() + (StringUtils.isNotBlank(remark) ? " 【" + remark + "】" : "");// 目录名称
			// Integer ordernum = modulePO.getORDERNUM();
			//
			// int index = (ordernum == null || ordernum.equals("")) ? 0 :
			// ordernum.intValue();

			JSONObject moduleNode = new JSONObject();

			moduleNode.put("id", moduleUUID);
			moduleNode.put("name", moduleMENU_NAME);
			moduleNode.put("pId", modulePARENT_UUID);

			if (moduleUUID.equals(modulePARENT_UUID)) {
				moduleNode.put("type", "module");
				moduleNode.put("isParent", true);
				moduleNode.put("iconSkin", "pIcon");
			} else {
				moduleNode.put("type", "page");
				moduleNode.put("isParent", false);
				moduleNode.put("iconSkin", "cIcon");
			}

			// jsonArray.add(index,moduleNode);
			jsonArray.add(moduleNode);
		}
		return jsonArray;
	}

	@Override
	public JSONArray buildOperTreeByList(List<SysOperationPO> list)
			throws SystemException {
		JSONArray jsonArray = new JSONArray();

		for (int k = 0; k < list.size(); k++) {
			SysOperationPO operationPO = list.get(k);

			String operUUID = operationPO.getUUID();// 操作主键
			String operMENU_UUID = operationPO.getMENU_UUID();// 操作父节点
			String operACTION_NAME = operationPO.getACTION_NAME();// 操作名称

			String actionCode = operationPO.getACTION_CODE();
			// Integer ordernum = operationPO.getORDERNUM();

			// int index = (ordernum == null || ordernum.equals("")) ? 0 :
			// ordernum.intValue();

			JSONObject operNode = new JSONObject();
			operNode.put("id", operUUID);
			operNode.put("pId", operMENU_UUID);
			operNode.put("name", operACTION_NAME);
			operNode.put("type", "operation");
			operNode.put("iconSkin", actionCode + "Icon");
			// jsonArray.add(index,operNode);
			jsonArray.add(operNode);
		}
		return jsonArray;
	}

	@Override
	public List<String> retrieveAllUrl() throws SystemException {
		return sysResourceDao.retrieveAllUrl();
	}

	@Override
	public List<String> retrieveUrlsByUserId(String userId) {
		return sysResourceDao.retrieveUrlByUserId(userId);
	}
	
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
