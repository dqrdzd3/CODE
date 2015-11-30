package com.hwsensor.permission.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;
import com.hwsensor.permission.action.SysOperationAction;
import com.hwsensor.permission.dao.ISysOperationDao;
import com.hwsensor.permission.pojo.SysOperationPO;
import com.hwsensor.permission.pojo.SysResourcePO;
import com.hwsensor.permission.service.ISysOperationService;
import com.hwsensor.permission.service.ISysResourceService;
import com.hwsensor.permission.service.ISysRolePermService;

@SuppressWarnings("unchecked")
public class SysOperationServiceImpl extends BaseServiceImpl implements
		ISysOperationService {

	@Autowired
	private ISysOperationDao sysOperationDao;
	
	@Autowired
	private ISysResourceService sysResourceService;
	
	@Autowired
	private ISysRolePermService sysRolePermService;

	@Override
	public Integer insertSysOperation(SysOperationPO sysOperationPO)
			throws SystemException {
		return sysOperationDao.insertSysOperation(sysOperationPO);
	}

	@Override
	public Integer updateSysOperation(SysOperationPO sysOperationPO)
			throws SystemException {
		return sysOperationDao.updateSysOperation(sysOperationPO);
	}

	@Override
	public Integer updateSysOperationByMap(Map map) throws SystemException {
		return sysOperationDao.updateSysOperationByMap(map);
	}

	@Override
	public Integer deleteSysOperation(SysOperationPO sysOperationPO)
			throws SystemException {
		return sysOperationDao.deleteSysOperation(sysOperationPO);
	}

	@Override
	public Integer deleteSysOperationByMap(Map map) throws SystemException {
		return sysOperationDao.deleteSysOperationByMap(map);
	}

	@Override
	public List<SysOperationPO> retrieveByCondition(Map map)
			throws SystemException {
		return sysOperationDao.retrieveByCondition(map);
	}

	@Override
	public SysOperationPO retrieveSysOperationByMap(Map map)
			throws SystemException {
		return sysOperationDao.retrieveSysOperationByMap(map);
	}

	@Override
	public void retrieveSysOperationList(SysOperationAction sysOperationAction)
			throws SystemException {
		HttpServletRequest request = sysOperationAction.getRequest();

		String MENU_UUID = request.getParameter("MENU_UUID");// 页面Id

		Map map = new HashMap();
		map.put("MENU_UUID", MENU_UUID);

		List<SysOperationPO> sysOperationPOs = retrieveByCondition(map);

		sysOperationAction.setOperationList(sysOperationPOs);

		SysOperationPO sysOperationPO = new SysOperationPO();
		sysOperationPO.setMENU_UUID(MENU_UUID);
		sysOperationAction.setSysOperationPO(sysOperationPO);
	}

	@Override
	public void insertSysOperationBP(SysOperationAction sysOperationAction)
			throws SystemException {
		try {
			HttpServletRequest request = sysOperationAction.getRequest();

			UserPO userPO = SessionUtil.getUserPO();
			String CREATOR = userPO.getId();
			Date CREAT_DATE = DateTimeUtils.getCurFormatDate();

			String MENU_UUID = request.getParameter("MENU_UUID");

			Map oldMap = new HashMap();
			oldMap.put("MENU_UUID", MENU_UUID);

			// 查询目前页面下的所有操作
			List<SysOperationPO> oldOpers = retrieveByCondition(oldMap);

			String[] UUIDS = request.getParameterValues("uuid");
			String[] ACTION_NAMES = request.getParameterValues("action_name");
			String[] ACTION_CODES = request.getParameterValues("action_code");
			String[] STYLE_NAMES = request.getParameterValues("style_name");
			String[] URLS = request.getParameterValues("url");
			String[] BIND_FUNCS = request.getParameterValues("bind_func");
			String[] REMARKS = request.getParameterValues("remark");
			String[] ORDERNUMS = request.getParameterValues("ordernum");

			List<String> editList = new ArrayList<String>();

			List<String> oldList = new ArrayList<String>();

			for (SysOperationPO oldOperation : oldOpers) {
				String oldUUID = oldOperation.getUUID();

				if (!oldList.contains(oldUUID)) {
					oldList.add(oldUUID);
				}
			}

			// 1.遍历操作
			for (int i = 1; i < ACTION_NAMES.length; i++) {
				String UUID = UUIDS[i];
				UUID = UUID == null ? "" : UUID;
				String ACTION_NAME = ACTION_NAMES[i];
				String ACTION_CODE = ACTION_CODES[i];
				String STYLE_NAME = STYLE_NAMES[i];
				String URL = URLS[i];
				String IS_VALID = request.getParameter("is_valid_" + i);
				String BIND_FUNC = BIND_FUNCS[i];
				String REMARK = REMARKS[i];
				String ORDERNUM = ORDERNUMS[i];
				ORDERNUM = ORDERNUM.isEmpty() ? "0" : ORDERNUM;

				// 操作主键为空时，添加
				if (UUID.isEmpty()) {
					String OPER_UUID = UUIDGenerater.getUUID();// 操作主键

					SysOperationPO sysOperationPO = new SysOperationPO();
					sysOperationPO.setUUID(OPER_UUID);
					sysOperationPO.setACTION_NAME(ACTION_NAME);
					sysOperationPO.setACTION_CODE(ACTION_CODE);
					sysOperationPO.setMENU_UUID(MENU_UUID);
					sysOperationPO.setSTYLE_NAME(STYLE_NAME);
					sysOperationPO.setURL(URL);
					sysOperationPO.setIS_VALID(IS_VALID);
					sysOperationPO.setBIND_FUNC(BIND_FUNC);
					sysOperationPO.setREMARK(REMARK);
					sysOperationPO.setORDERNUM(Integer.parseInt(ORDERNUM));
					sysOperationPO.setCREATOR(CREATOR);
					sysOperationPO.setCREATE_DATE(CREAT_DATE);

					insertSysOperation(sysOperationPO);

					// 4.为每个操作添加默认资源
					SysResourcePO oper_resourcePO = new SysResourcePO();
					oper_resourcePO.setUUID(UUIDGenerater.getUUID());
					oper_resourcePO.setRESOURCE_NAME(ACTION_NAME);
					oper_resourcePO.setRESOURCE_TYPE("20");
					oper_resourcePO.setMODULE_OPERA_UUID(OPER_UUID);
					oper_resourcePO.setBIND_FUNC(BIND_FUNC.isEmpty() ? "#"
							: BIND_FUNC);// 默认为#
					oper_resourcePO.setURL(URL.isEmpty() ? "#" : URL);
					oper_resourcePO.setIS_DEFAULT("0");
					oper_resourcePO.setCREATOR(CREATOR);
					oper_resourcePO.setCREATE_DATE(CREAT_DATE);

					sysResourceService.insertSysResource(oper_resourcePO);
				}

				// 操作主键不为空时，取出要修改操作的Id
				if (!UUID.isEmpty()) {

					if (oldList.contains(UUID)) {
						editList.add(UUID);

						Map editOperMap = new HashMap();
						editOperMap.put("UUID", UUID);
						// 修改操作
						SysOperationPO editOperationPO = retrieveSysOperationByMap(editOperMap);
						editOperationPO.setACTION_NAME(ACTION_NAME);
						editOperationPO.setACTION_CODE(ACTION_CODE);
						editOperationPO.setMENU_UUID(MENU_UUID);
						editOperationPO.setSTYLE_NAME(STYLE_NAME);
						editOperationPO.setURL(URL);
						editOperationPO.setIS_VALID(IS_VALID);
						editOperationPO.setBIND_FUNC(BIND_FUNC);
						editOperationPO.setREMARK(REMARK);
						editOperationPO.setORDERNUM(Integer.parseInt(ORDERNUM));

						updateSysOperation(editOperationPO);

						// 4.修改操作的默认资源
						Map resMap = new HashMap();
						resMap.put("MODULE_OPERA_UUID", UUID);
						resMap.put("IS_DEFAULT", "0");

						SysResourcePO edit_resourcePO = sysResourceService
								.retrieveSysResourceByMap(resMap);

						if (edit_resourcePO != null) {

							Map editResMap = new HashMap();
							editResMap.put("RESOURCE_NAME", ACTION_NAME);
							editResMap.put("RESOURCE_TYPE", "20");
							editResMap.put("URL", URL.isEmpty() ? "#" : URL);
							editResMap.put("BIND_FUNC",
									BIND_FUNC.isEmpty() ? "#" : BIND_FUNC);
							editResMap.put("MODULE_OPERA_UUID", UUID);
							editResMap.put("IS_DEFAULT", "0");
							editResMap.put("UUID", edit_resourcePO.getUUID());

							sysResourceService
									.updateSysResourceByMap(editResMap);

						}
					}
				}
			}
			// 取出要删除操作的Id
			for (String oldId : oldList) {
				if (!editList.contains(oldId)) {
					// 删除操作，操作资源和角色操作关联
					Map delOperMap = new HashMap();
					delOperMap.put("UUID", oldId);

					deleteSysOperationByMap(delOperMap);

					Map delResMap = new HashMap();
					delResMap.put("MODULE_OPERA_UUID", oldId);

					sysResourceService.deleteSysResourceByMap(delResMap);
					sysRolePermService.deleteSysRolePermByMap(delOperMap);
				}
			}
		} catch (Exception e) {
			sysOperationAction.getMessage().set(Constants.MSG_ERROR, "保存失败！");
			throw new SystemException(e);
		}
		sysOperationAction.getMessage().set(Constants.MSG_OK, "保存成功！");
		return;
	}

	@Override
	public void deleteSysOperationBP(SysOperationAction sysOperationAction)
			throws SystemException {
		try {
			HttpServletRequest request = sysOperationAction.getRequest();
			String UUIDStr = request.getParameter("ids") == null ? "" : request
					.getParameter("ids");
			String[] UUIDS = UUIDStr.split(",");

			for (int i = 0; i < UUIDS.length; i++) {
				String UUID = UUIDS[i];

				Map operMap = new HashMap();
				operMap.put("UUID", UUID);

				SysOperationPO sysOperationPO = retrieveSysOperationByMap(operMap);

				if (sysOperationPO == null) {
					sysOperationAction.getMessage().set(Constants.MSG_ERROR,
							"操作信息已不存在，请刷新后重试！");
					return;
				}

				// 删除操作以及角色操作关联
				deleteSysOperation(sysOperationPO);

				Map delOperMap = new HashMap();
				delOperMap.put("OPERA_UUID", UUID);

				sysRolePermService.deleteSysRolePermByMap(delOperMap);

				// 删除操作下的资源
				Map resourceMap = new HashMap();
				resourceMap.put("MODULE_OPERA_UUID", UUID);

				sysResourceService.deleteSysResourceByMap(resourceMap);
			}
		} catch (Exception e) {
			sysOperationAction.getMessage().set(Constants.MSG_ERROR, "删除失败！");
			throw new SystemException(e);
		}
		sysOperationAction.getMessage().set(Constants.MSG_OK, "删除成功！");
		return;
	}

	@Override
	public void deleteSysOperationsByMENUID(String id) throws SystemException {
		// 删除操作以及操作下的资源

		Map operMap = new HashMap();
		operMap.put("MENU_UUID", id);

		List<SysOperationPO> operList = retrieveByCondition(operMap);

		for (int i = 0; i < operList.size(); i++) {
			SysOperationPO sysOperationPO = operList.get(i);
			String OPER_UUID = sysOperationPO.getUUID();

			// 删除操作和角色操作关联
			deleteSysOperation(sysOperationPO);

			Map delOperMap = new HashMap();
			delOperMap.put("OPERA_UUID", OPER_UUID);

			sysRolePermService.deleteSysRolePermByMap(delOperMap);

			// 删除操作下的资源
			Map resourceMap = new HashMap();
			resourceMap.put("MODULE_OPERA_UUID", OPER_UUID);

			sysResourceService.deleteSysResourceByMap(resourceMap);
		}
	}

	@Override
	public List<SysOperationPO> retrieveAllOperations() throws SystemException {
		return sysOperationDao.retrieveAllOperations();
	}

	@Override
	public List<SysOperationPO> retrieveOperationsByMap(Map map)
			throws SystemException {
		return sysOperationDao.retrieveOperationsByMap(map);
	}

	@Override
	public List<SysOperationPO> retrieveAllOperationsByMap(Map map)
			throws SystemException {
		return sysOperationDao.retrieveAllOperationsByMap(map);
	}
}
