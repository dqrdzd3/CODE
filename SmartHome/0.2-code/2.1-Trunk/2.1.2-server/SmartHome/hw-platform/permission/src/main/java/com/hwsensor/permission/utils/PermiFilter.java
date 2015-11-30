/**
 * 文件名：PermiFilter.java
 * 版本信息：
 * 日期：2012-12-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 */
package com.hwsensor.permission.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.hw.hwsafe.utils.PropertiesFactory;
import com.hw.hwsafe.utils.StringUtil;

/**
 * 
 * 项目名称：permission
 * 类名称：PermiFilter
 * 类描述：过滤掉不同用户类型所不能授权的模块，页面和操作
 * 创建人：杜群星
 * 创建时间：2012-12-12 下午5:00:56
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 
 * 
 */
@SuppressWarnings("unchecked")
public class PermiFilter {

	private static final String MODULE_FILE_PATH = "/module.properties";

	private static final String ENT_ADMIN_DISABLED_MODULE = getModuleProperties("entAdminDisabledModule");
	private static final String ENT_ADMIN_DISABLED_PAGE = getModuleProperties("entAdminDisabledPage");
	private static final String ENT_ADMIN_DISABLED_OPER = getModuleProperties("entAdminDisabledOper");

	private static final String GOV_ADMIN_DISABLED_MODULE = getModuleProperties("govAdminDisabledModule");
	private static final String GOV_ADMIN_DISABLED_PAGE = getModuleProperties("govAdminDisabledPage");
	private static final String GOV_ADMIN_DISABLED_OPER = getModuleProperties("govAdminDisabledOper");

	private static final String ENT_USER_DISABLED_MODULE = getModuleProperties("entUserDisabledModule");
	private static final String ENT_USER_DISABLED_PAGE = getModuleProperties("entUserDisabledPage");
	private static final String ENT_USER_DISABLED_OPER = getModuleProperties("entUserDisabledOper");

	private static final String GOV_USER_DISABLED_MODULE = getModuleProperties("govUserDisabledModule");
	private static final String GOV_USER_DISABLED_PAGE = getModuleProperties("govUserDisabledPage");
	private static final String GOV_USER_DISABLED_OPER = getModuleProperties("govUserDisabledOper");

	private static final String SYS_USER_DISABLED_MODULE = getModuleProperties("sysUserDisabledModule");
	private static final String SYS_USER_DISABLED_PAGE = getModuleProperties("sysUserDisabledPage");
	private static final String SYS_USER_DISABLED_OPER = getModuleProperties("sysUserDisabledOper");

	public static final String MODULE_TYPE = "MODULE";
	public static final String PAGE_TYPE = "PAGE";
	public static final String OPER_TYPE = "TYPE";

	/**
	 * 获取要过滤的模块，页面或操作资源 getModuleProperties
	 * 
	 * @param
	 * @return String
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public static String getModuleProperties(String key) {
		Properties properties = PropertiesFactory.reload(MODULE_FILE_PATH)
				.getProperties();
		String value = properties.getProperty(key, "");
		return value;
	}

	/**
	 * 根据登录用户类型，分配角色类型，模块类型查询要过滤的权限 getDisabledMap
	 * 
	 * @param
	 * @return Map
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public static Map getDisabledMap(String userType, String roleType,
			String disabledType) {
		Map disabledMap = new HashMap();

		if (userType.equals("SYS") && roleType.equals("SYS")) {
			if (disabledType.equals("MODULE")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(SYS_USER_DISABLED_MODULE.split(",")));
			} else if (disabledType.equals("PAGE")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(SYS_USER_DISABLED_PAGE.split(",")));
			} else if (disabledType.equals("OPER")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(SYS_USER_DISABLED_OPER.split(",")));
			}
		}
		if (userType.equals("SYS") && roleType.equals("ENT")) {
			if (disabledType.equals("MODULE")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(ENT_ADMIN_DISABLED_MODULE.split(",")));
			} else if (disabledType.equals("PAGE")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(ENT_ADMIN_DISABLED_PAGE.split(",")));
			} else if (disabledType.equals("OPER")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(ENT_ADMIN_DISABLED_OPER.split(",")));
			}
		}
		if (userType.equals("SYS") && roleType.equals("GOV")) {
			if (disabledType.equals("MODULE")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(GOV_ADMIN_DISABLED_MODULE.split(",")));
			} else if (disabledType.equals("PAGE")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(GOV_ADMIN_DISABLED_PAGE.split(",")));
			} else if (disabledType.equals("OPER")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(GOV_ADMIN_DISABLED_OPER.split(",")));
			}
		}

		if (userType.equals("ENT") && roleType.equals("ENT")) {
			if (disabledType.equals("MODULE")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(ENT_USER_DISABLED_MODULE.split(",")));
			}
			if (disabledType.equals("PAGE")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(ENT_USER_DISABLED_PAGE.split(",")));
			}
			if (disabledType.equals("OPER")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(ENT_USER_DISABLED_OPER.split(",")));
			}
		}

		if (userType.equals("GOV") && roleType.equals("GOV")) {
			if (disabledType.equals("MODULE")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(GOV_USER_DISABLED_MODULE.split(",")));
			} else if (disabledType.equals("PAGE")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(GOV_USER_DISABLED_PAGE.split(",")));
			} else if (disabledType.equals("OPER")) {
				disabledMap.put("DISABLED", StringUtil
						.stringArr2Null(GOV_USER_DISABLED_OPER.split(",")));
			}
		}

		return disabledMap;
	}

}
