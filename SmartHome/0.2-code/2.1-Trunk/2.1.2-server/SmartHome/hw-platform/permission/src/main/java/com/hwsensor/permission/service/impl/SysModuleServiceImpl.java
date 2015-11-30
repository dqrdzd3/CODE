package com.hwsensor.permission.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;
import com.hwsensor.permission.action.SysModuleAction;
import com.hwsensor.permission.dao.ISysModuleDao;
import com.hwsensor.permission.pojo.SysModulePO;
import com.hwsensor.permission.pojo.SysOperationPO;
import com.hwsensor.permission.pojo.SysResourcePO;
import com.hwsensor.permission.service.ISysModuleService;
import com.hwsensor.permission.service.ISysOperationService;
import com.hwsensor.permission.service.ISysResourceService;
import com.hwsensor.permission.service.ISysRolePermService;

@SuppressWarnings("unchecked")
public class SysModuleServiceImpl extends BaseServiceImpl implements ISysModuleService {
  
	@Autowired
	private ISysModuleDao sysModuleDao;

	@Autowired
	private ISysOperationService sysOperationService;

	@Autowired
	private ISysResourceService sysResourceService;

	@Autowired
	private ISysRolePermService sysRolePermService;

	private String errMsg;
  
	@Override
	public Integer insertSysModule(SysModulePO sysModulePO)
			throws SystemException {
		return sysModuleDao.insertSysModule(sysModulePO);
	}

	@Override
	public Integer updateSysModule(SysModulePO sysModulePO)
			throws SystemException {
		return sysModuleDao.updateSysModule(sysModulePO);
	}

	@Override
	public Integer updateSysModuleByMap(Map map) throws SystemException {
		return sysModuleDao.updateSysModuleByMap(map);
	}

	@Override
	public Integer deleteSysModule(SysModulePO sysModulePO)
			throws SystemException {
		return sysModuleDao.deleteSysModule(sysModulePO);
	}

	@Override
	public Integer deleteSysModuleByMap(Map map) throws SystemException {
		return sysModuleDao.deleteSysModuleByMap(map);
	}

	@Override
	public List<SysModulePO> retrieveByCondition(Map map)
			throws SystemException {
		return sysModuleDao.retrieveByCondition(map);
	}

	@Override
	public SysModulePO retrieveSysModuleByMap(Map map) throws SystemException {
		return sysModuleDao.retrieveSysModuleByMap(map);
	}


	@Override
	public void bulidSysMoudleTree(SysModuleAction sysModuleAction)
			throws SystemException {

		List<SysModulePO> moduleList = new ArrayList();
		Map map = new HashMap();
		map.put("MENU_TYPE", "10");
		// map.put("IS_VALID", "10");

		moduleList = retrieveByCondition(map);

		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < moduleList.size(); i++) {
			SysModulePO sysModulePO = moduleList.get(i);
			String UUID = sysModulePO.getUUID();
			String PARENT_UUID = sysModulePO.getPARENT_UUID();
			String MENU_NAME = sysModulePO.getMENU_NAME();
			String MENU_TYPE = sysModulePO.getMENU_TYPE();
			String userType = sysModulePO.getUSER_TYPE();
			// Integer ordernum = sysModulePO.getORDERNUM();
			//
			// int index = (ordernum == null || ordernum.equals("")) ? 0 :
			// ordernum.intValue();

			JSONObject node = new JSONObject();
			node.put("id", UUID);
			node.put("pId", PARENT_UUID);
			node.put("name", MENU_NAME);
			node.put("userType", userType);

			if (MENU_TYPE != null && MENU_TYPE.equals("10")) {
				node.put("isParent", true);
			} else {
				node.put("isParent", false);
			}
			// jsonArray.add(index,node);
			jsonArray.add(node);
		}

		sysModuleAction.setModuleTree(jsonArray.toString());
	}

	@Override
	public void insertSysModuleBP(SysModuleAction sysModuleAction)
			throws SystemException {
		
		Map retMap = new HashMap();
		try {
			SysModulePO sysModulePO = sysModuleAction.getSysModulePO();
			UserPO userPO = sysModuleAction.getSessionUserPO();// 取出session中当前用户信息

			String MENU_UUID = UUIDGenerater.getUUID();// 模块主键
			String CREATOR = userPO.getId();// 创建人
			Date CREAT_DATE = DateTimeUtils.getCurDateTime();// 创建日期

			sysModulePO.setUUID(MENU_UUID);
			sysModulePO.setCREATOR(CREATOR);
			sysModulePO.setCREATE_DATE(CREAT_DATE);

			// 如果父模块节点ID为空，则设置父模块的节点ID=UUID ,使之为根模块,编码不变，否则，编码是父编码_子编码
			if (sysModulePO.getPARENT_UUID() == null
					|| sysModulePO.getPARENT_UUID().isEmpty()) {
				sysModulePO.setPARENT_UUID(MENU_UUID);
			} else {
				Map pMap = new HashMap();
				pMap.put("UUID", sysModulePO.getPARENT_UUID());
				SysModulePO pModule = retrieveSysModuleByMap(pMap);
				String pCode = pModule.getMENU_CODE();

				String subCode = pCode + "_" + sysModulePO.getMENU_CODE();
				sysModulePO.setMENU_CODE(subCode);
			}

			// 1.添加模块或目录
			insertSysModule(sysModulePO);
			// 2.为模块或目录添加相应的默认资源
			SysResourcePO sysResourcePO = new SysResourcePO();
			sysResourcePO.setUUID(UUIDGenerater.getUUID());
			sysResourcePO.setRESOURCE_NAME(sysModulePO.getMENU_NAME());
			sysResourcePO.setRESOURCE_TYPE("10");
			sysResourcePO.setMODULE_OPERA_UUID(MENU_UUID);
			// sysResourcePO.setBIND_FUNC("#");//默认为#
			sysResourcePO.setURL(sysModulePO.getURL().isEmpty() ? "#"
					: sysModulePO.getURL());
			sysResourcePO.setIS_DEFAULT("0");
			sysResourcePO.setCREATOR(CREATOR);
			sysResourcePO.setCREATE_DATE(CREAT_DATE);

			sysResourceService.insertSysResource(sysResourcePO);

			String MENUTYPE = sysModulePO.getMENU_TYPE();

			// 如果为页面，则需要添加操作
			if (MENUTYPE.equals("20")) {
				HttpServletRequest request = sysModuleAction.getRequest();

				String[] ACTION_NAMES = request
						.getParameterValues("action_name");
				String[] ACTION_CODES = request
						.getParameterValues("action_code");
				String[] STYLE_NAMES = request.getParameterValues("style_name");
				String[] URLS = request.getParameterValues("url");
				// String[] IS_VALIDS = request.getParameterValues("is_valid");
				String[] BIND_FUNCS = request.getParameterValues("bind_func");
				String[] REMARKS = request.getParameterValues("remark");
				String[] ORDERNUMS = request.getParameterValues("ordernum");

				// 3.遍历添加操作
				for (int i = 1; i < ACTION_NAMES.length; i++) {
					String ACTION_NAME = ACTION_NAMES[i];
					String ACTION_CODE = ACTION_CODES[i];
					String STYLE_NAME = STYLE_NAMES[i];
					String URL = URLS[i];
					String IS_VALID = request.getParameter("is_valid_" + i);
					String BIND_FUNC = BIND_FUNCS[i];
					String REMARK = REMARKS[i];
					String ORDERNUM = ORDERNUMS[i];
					ORDERNUM = ORDERNUM.isEmpty() ? "0" : ORDERNUM;

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

					sysOperationService.insertSysOperation(sysOperationPO);

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
			}

			// 设置返回数据
			retMap.put("sysModulePO", sysModulePO);
		} catch (Exception e) {
			sysModuleAction.getMessage().set(Constants.MSG_ERROR, "添加失败！",
					Constants.SERVER_MSG);
			throw new SystemException(e.getMessage());
		}

		sysModuleAction.getMessage().set(Constants.MSG_OK, "添加成功！", retMap,
				Constants.SERVER_MSG);
		return;
	}

	@Override
	public void updateSysModuleBP(SysModuleAction sysModuleAction)
			throws SystemException {
		
		Map retMap = new HashMap();
		// 1.查询原来的模块信息
		SysModulePO oldSysModulePO = retrieveSysModuleBP(sysModuleAction);
		try {
			SysModulePO sysModulePO = sysModuleAction.getSysModulePO();

			UserPO userPO = SessionUtil.getUserPO();
			String EDITOR = userPO.getId();
			Date MODIFIED_DATE = DateTimeUtils.getCurDateTime();

			// 2.修改原来的模块信息
			oldSysModulePO.setMENU_NAME(sysModulePO.getMENU_NAME());
			oldSysModulePO.setURL(sysModulePO.getURL());
			// oldSysModulePO.setUSER_TYPE(sysModulePO.getUSER_TYPE());
			oldSysModulePO.setIS_VALID(sysModulePO.getIS_VALID());
			oldSysModulePO.setREMARK(sysModulePO.getREMARK());
			oldSysModulePO.setMENU_CODE(sysModulePO.getMENU_CODE());
			oldSysModulePO.setIS_SHOW(sysModulePO.getIS_SHOW());
			oldSysModulePO.setORDERNUM(sysModulePO.getORDERNUM());

			oldSysModulePO.setEDITOR(EDITOR);
			oldSysModulePO.setMODIFIED_DATE(MODIFIED_DATE);

			// 如果父id和id一样，编码不变，否则编码=父编码_子编码
			if (!oldSysModulePO.getPARENT_UUID().equals(
					oldSysModulePO.getUUID())) {
				Map pMap = new HashMap();
				pMap.put("UUID", oldSysModulePO.getPARENT_UUID());
				SysModulePO pModule = retrieveSysModuleByMap(pMap);
				String pCode = pModule.getMENU_CODE();

				String menuCode = pCode + "_" + oldSysModulePO.getMENU_CODE();
				oldSysModulePO.setMENU_CODE(menuCode);
			}

			updateSysModule(oldSysModulePO);

			// 3.删除模块原来的默认资源

			Map map = new HashMap();

			map.put("MODULE_OPERA_UUID", oldSysModulePO.getUUID());
			map.put("IS_DEFAULT", "0");
			sysResourceService.deleteSysResourceByMap(map);

			// 4.为模块或目录重新添加相应的默认资源
			SysResourcePO sysResourcePO = new SysResourcePO();
			sysResourcePO.setUUID(UUIDGenerater.getUUID());
			sysResourcePO.setRESOURCE_NAME(oldSysModulePO.getMENU_NAME());
			sysResourcePO.setRESOURCE_TYPE("10");
			sysResourcePO.setMODULE_OPERA_UUID(oldSysModulePO.getUUID());
			// sysResourcePO.setBIND_FUNC("#");//默认为#
			sysResourcePO.setURL(oldSysModulePO.getURL().isEmpty() ? "#"
					: oldSysModulePO.getURL());
			sysResourcePO.setIS_DEFAULT("0");
			sysResourcePO.setCREATOR(EDITOR);
			sysResourcePO.setCREATE_DATE(MODIFIED_DATE);

			sysResourceService.insertSysResource(sysResourcePO);

			retMap.put("sysModulePO", oldSysModulePO);
		} catch (Exception e) {
			sysModuleAction.getMessage().set(Constants.MSG_ERROR, "修改失败！",
					Constants.SERVER_MSG);
			throw new SystemException(e);
		}
		sysModuleAction.getMessage().set(Constants.MSG_OK, "修改成功！", retMap,
				Constants.SERVER_MSG);
		return;
	}

	@Override
	public void deleteSysModuleBP(SysModuleAction sysModuleAction)
			throws SystemException {
		// 判断模块是否存在,存在取出信息
		Map retMap = new HashMap();
		HttpServletRequest request = sysModuleAction.getRequest();
		String idstr = request.getParameter("ids") == null ? "" : request
				.getParameter("ids");
		String[] ids = idstr.split(",");
		try {
			String retIds = "";
			for (int m = 0; m < ids.length; m++) {
				String id = ids[m];
				if (!id.isEmpty()) {
					retIds += id + ",";
				} else {
					continue;
				}
				SysModulePO delModulePO = new SysModulePO();
				delModulePO.setUUID(id);
				sysModuleAction.setSysModulePO(delModulePO);
				SysModulePO sysModulePO = retrieveSysModuleBP(sysModuleAction);

				String MENUNAME = sysModulePO.getMENU_NAME();// 部门名称
				MENUNAME = MENUNAME == null ? "" : MENUNAME;
				String MENUTYPE = sysModulePO.getMENU_TYPE();// 模块类型
				String MENU_UUID = sysModulePO.getUUID();// 主键

				// 判断模块类型
				// 如果为目录，判断目录下是否有子目录或页面，有则不能删，反之删除模块以及默认资源
				// 如果为页面，判断页面下是否有页面，有则不能删，反之，则把页面下相应的操作和资源全部删

				// 1.判断
				Map map = new HashMap();
				map.put("PARENT_UUID", MENU_UUID);
				map.put("ISEQUAL", "0");

				List moduleList = retrieveByCondition(map);
				if (moduleList != null && moduleList.size() > 0) {
					// sysModuleAction.getMessage().set(Constants.MSG_INFO,
					// "该目录或页面下有子目录或页面，不能删除！", Constants.SERVER_MSG);
					errMsg = "『" + MENUNAME + "』下有子目录或页面，不能删除！";
					throw new SystemException(errMsg);
				}
				// 2.删除目录或页面同时删除角色模块的关联
				deleteSysModule(sysModulePO);

				Map delModuleMap = new HashMap();
				delModuleMap.put("MODULE_UUID", MENU_UUID);
				sysRolePermService.deleteSysRolePermByMap(delModuleMap);

				// 3.删除操作和资源
				if (MENUTYPE.equals("10")) {
					// 删除目录的默认和自定义资源
					Map dirMap = new HashMap();
					dirMap.put("MODULE_OPERA_UUID", MENU_UUID);
					sysResourceService.deleteSysResourceByMap(dirMap);
				}

				if (MENUTYPE.equals("20")) {
					// 删除页面的默认和自定义资源
					Map pageMap = new HashMap();
					pageMap.put("MODULE_OPERA_UUID", MENU_UUID);
					sysResourceService.deleteSysResourceByMap(pageMap);

					// 删除操作以及操作下的资源

					Map operMap = new HashMap();
					operMap.put("MENU_UUID", MENU_UUID);

					List<SysOperationPO> operList = sysOperationService
							.retrieveByCondition(operMap);

					for (int i = 0; i < operList.size(); i++) {
						SysOperationPO sysOperationPO = operList.get(i);
						String OPER_UUID = sysOperationPO.getUUID();

						// 删除操作和角色操作关联
						sysOperationService.deleteSysOperation(sysOperationPO);

						Map delOperMap = new HashMap();
						delOperMap.put("OPERA_UUID", OPER_UUID);
						sysRolePermService.deleteSysRolePermByMap(delOperMap);

						// 删除操作下的资源
						Map resourceMap = new HashMap();
						resourceMap.put("MODULE_OPERA_UUID", OPER_UUID);

						sysResourceService.deleteSysResourceByMap(resourceMap);
					}
				}
			}
			retMap.put("ids", retIds.substring(0, retIds.lastIndexOf(",")));
		} catch (Exception e) {
			if (getErrMsg() != null && !getErrMsg().isEmpty()) {
				sysModuleAction.getMessage().set(Constants.MSG_ERROR,
						getErrMsg(), Constants.SERVER_MSG);
				
			} else {
				sysModuleAction.getMessage().set(Constants.MSG_ERROR, "删除失败！",
						Constants.SERVER_MSG);
				
			}
			throw new SystemException(e.getMessage());
		}
		sysModuleAction.getMessage().set(Constants.MSG_OK, "删除成功！", retMap,
				Constants.SERVER_MSG);
		return;
	}

	@Override
	public SysModulePO retrieveSysModuleBP(SysModuleAction sysModuleAction)
			throws SystemException {

		SysModulePO sysModulePO = sysModuleAction.getSysModulePO();
		String UUID = sysModulePO.getUUID();

		Map map = new HashMap();
		map.put("UUID", UUID);
		SysModulePO modulePO = retrieveSysModuleByMap(map);
		if (modulePO == null) {
			sysModuleAction.getMessage().set(Constants.MSG_ERROR,
					"信息已不存在，请刷新后重试!", Constants.SERVER_MSG);
			throw new SystemException("信息已不存在，请刷新后重试!");
		}
		String subCode = "";

		if (!modulePO.getUUID().equals(modulePO.getPARENT_UUID())) {

			String menuCode = modulePO.getMENU_CODE();

			Map pMap = new HashMap();
			pMap.put("UUID", modulePO.getPARENT_UUID());
			SysModulePO pModule = retrieveSysModuleByMap(pMap);
			String pCode = pModule.getMENU_CODE();

			if (menuCode != null && pCode != null && menuCode.startsWith(pCode)) {
				subCode = menuCode.replace(pCode + "_", "");
			}
		} else {
			subCode = modulePO.getMENU_CODE();
		}
		modulePO.setSubCode(subCode);

		return modulePO;
	}

	@Override
	public List<SysModulePO> retrieveAllUserPermByUserId(String userId)
			throws SystemException {
		return sysModuleDao.retrieveAllUserPermByUserId(userId);
	}

	@Override
	public List<SysModulePO> queryRootPer(Map map) throws SystemException {
		return sysModuleDao.queryRootPer(map);
	}

	@Override
	public List<SysModulePO> retrieveAllUserPermByMap(Map map)
			throws SystemException {
		return sysModuleDao.retrieveAllUserPermByMap(map);
	}

	@Override
	public List<SysModulePO> retrieveOrgUserPermByMap(Map map)
			throws SystemException {
		return sysModuleDao.retrieveOrgUserPermByMap(map);
	}

	@Override
	public int queryModuleOrderNum() throws SystemException {
		return sysModuleDao.queryModuleOrderNum();
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getErrMsg() {
		return errMsg;
	}
}
