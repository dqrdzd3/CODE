/**
 * 文件名：BeanUtils.java
 *
 * 版本信息：1.0
 * 日期：2012-5-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.utils;

import com.hw.hwsafe.platform.service.IDictionaryService;
import com.hw.hwsafe.platform.util.ServiceBeanUtils;
import com.hwsensor.permission.service.IDepartmentService;
import com.hwsensor.permission.service.ISysModuleService;
import com.hwsensor.permission.service.ISysOperationService;
import com.hwsensor.permission.service.ISysResourceService;
import com.hwsensor.permission.service.ISysRolePermService;
import com.hwsensor.permission.service.IUserService;

/**
 * 
 * 项目名称：framework
 * 类名称：BeanUtils
 * 类描述：用户获取Service
 * 创建人：盛家龙
 * 创建时间：2012-5-8 下午7:57:37
 * 修改人：张成
 * 修改时间：2012-5-8 下午7:57:37
 * 修改备注：
 * @version 
 * 
 */
public class BeanUtils extends ServiceBeanUtils {

	public static IUserService getUserService() {
		return (IUserService) getBean("sysUserService");
	}

	public static IDepartmentService getDepartmentService() {
		return (IDepartmentService) getBean("iDepartmentService");
	}

	/**
	 * 获取字典类service
	 * 
	 * getDictionaryService
	 * 
	 * @param
	 * @return IDictionaryService
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public static IDictionaryService getDictionaryService() {
		return (IDictionaryService) getBean("dictionaryService");
	}

	// 新权限系统service工具类 start

	/**
	 * 系统模块工具service
	 */
	public static ISysModuleService getSysModuleService() {
		return (ISysModuleService) getBean("sysModuleService");
	}

	/**
	 * 系统操作工具service
	 */
	public static ISysOperationService getSysOperationService() {
		return (ISysOperationService) getBean("sysOperationService");
	}

	/**
	 * 系统资源工具service
	 */
	public static ISysResourceService getSysResourceService() {
		return (ISysResourceService) getBean("sysResourceService");
	}

	/**
	 * 系统相关操作关联工具service
	 */
	public static ISysRolePermService getSysRolePermService() {
		return (ISysRolePermService) getBean("sysRolePermService");
	}

	// 新权限系统service工具类 end
}
