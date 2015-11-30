package com.hwsensor.permission.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hwsensor.permission.pojo.SysModulePO;
import com.hwsensor.permission.pojo.SysOperationPO;

/**
 * 
 * 项目名称：framework 
 * 类名称：PermUtil 
 * 类描述： 权限按钮处理类 
 * 创建人：孟繁波 
 * 创建时间：2012-8-11 下午11:13:34
 * 修改人：孟繁波 
 * 修改时间：2012-8-11 下午11:13:34 
 * 修改备注：
 * 
 * @version
 * 
 */

@SuppressWarnings("unchecked")
public class PermUtil {

	/**
	 * 构造操作按钮字符串 getPermButtons
	 * 
	 * @param name
	 * @return String
	 */
	public static String getPermButtons(List<SysOperationPO> operations) {

		StringBuffer sb = new StringBuffer();
		sb.append("<ul>");
		for (SysOperationPO sysOperationPO : operations) {

			String actionName = sysOperationPO.getACTION_NAME();
			String styleName = sysOperationPO.getSTYLE_NAME();
			String url = sysOperationPO.getURL();
			url = url.equals("#") ? "" : url;
			String bindFun = sysOperationPO.getBIND_FUNC();
			bindFun = bindFun == null ? "" :bindFun;

			sb.append("<li class=\"").append(styleName)
					.append("\"><a href=\"javascript:").append(url)
					.append(";\" onclick=\"").append(bindFun).append("\">")
					.append(actionName).append("</a></li>");

		}

		sb.append("</ul>");

		return sb.toString();
	}

	/**
	 * 检查是否拥有此url的访问权限（改成标签后将过期） checkPerm
	 * 
	 * @param name
	 * @return String
	 */
	public static SysModulePO checkPerm(String url,
			List<SysModulePO> sysModulePOs) {
		SysModulePO modulePO = null;

		for (int i = 0; i < sysModulePOs.size(); i++) {
			SysModulePO sysModulePO = sysModulePOs.get(i);
			String actionUrl = sysModulePO.getURL();
			actionUrl = actionUrl == null ? "" : actionUrl;
			if (actionUrl.equals(url)) {
				modulePO = sysModulePO;
				break;
			}
		}

		return modulePO;
	}

	public static List<SysOperationPO> getOperations(SysModulePO modulePO,
			List<SysOperationPO> operations) {

		List<SysOperationPO> sysOperationPOs = new ArrayList<SysOperationPO>();

		String UUID = modulePO.getUUID();
		for (SysOperationPO operation : operations) {
			String menuUUID = operation.getMENU_UUID();

			if (menuUUID.equals(UUID)) {
				sysOperationPOs.add(operation);
			}

		}
		return sysOperationPOs;
	}

	/**
	 * 通过模块URL判断用户是否拥有权限，并构造可用操作字符串（改成标签后将过期） buildPermButtonsStr
	 * 
	 * @param name
	 * @return String
	 */
	public static String buildPermButtonsStr(String actionUrl,
			List<SysModulePO> sysModulePOs, List<SysOperationPO> sysOperationPOs) {

		String permButtonStr = "";

		SysModulePO accessModuel = PermUtil.checkPerm(actionUrl, sysModulePOs);

		if (accessModuel != null) {

			List<SysOperationPO> operations = getOperations(accessModuel,
					sysOperationPOs);

			// 按钮字符串
			permButtonStr = PermUtil.getPermButtons(operations);
		}

		return permButtonStr;
	}

	/**
	 * 通过模块code来判断用户是否拥有权限，并构造操作字符串 buildPermButtonsStr
	 * 
	 * @param name
	 * @return String
	 */

	public static String buildPermButtonsStr(String code, String param,
			List<SysModulePO> sysModulePOs, List<SysOperationPO> sysOperationPOs) {

		String permButtonStr = "";

		SysModulePO accessModuel = PermUtil.checkModuleByCode(code,
				sysModulePOs);

		if (accessModuel != null) {

			List<SysOperationPO> operations = getOperations(accessModuel,
					sysOperationPOs);

			operations = filterNoOperations(param, operations);

			// 按钮字符串
			permButtonStr = PermUtil.getPermButtons(operations);
		}

		return permButtonStr;
	}

	/**
	 * 通过 filterNoOperations
	 * 
	 * @param name
	 * @return String
	 */
	private static List<SysOperationPO> filterNoOperations(String param,
			List<SysOperationPO> operations) {

		String[] params = param.split(",");
		Map paramMap = new HashMap();
		for (int i = 0; i < params.length; i++) {
			String str = params[i].trim().toLowerCase();
			paramMap.put(i, str);
		}

		List<SysOperationPO> sysOperationPOs = new ArrayList<SysOperationPO>();
		for (SysOperationPO operation : operations) {

			String actionCode = operation.getACTION_CODE();
			actionCode = actionCode == null ? "" : actionCode.trim()
					.toLowerCase();

			if (paramMap.containsValue(actionCode)) {
				sysOperationPOs.add(operation);
			}
		}
		return sysOperationPOs;
	}

	/**
	 * 通过模块ID判断用户是否拥有访问权限 checkPermById
	 * 
	 * @param name
	 * @return String
	 */
	private static SysModulePO checkModuleByCode(String code,
			List<SysModulePO> sysModulePOs) {
		SysModulePO modulePO = null;

		for (int i = 0; i < sysModulePOs.size(); i++) {
			SysModulePO sysModulePO = sysModulePOs.get(i);
			String menuCode = sysModulePO.getMENU_CODE();
			menuCode = menuCode == null ? "" : menuCode;
			if (menuCode.equals(code)) {
				modulePO = sysModulePO;
				break;
			}
		}
		return modulePO;
	}
}
