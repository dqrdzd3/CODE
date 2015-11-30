/**
 * 文件名：SysResourceAction.java
 *
 * 版本信息：
 * 日期：2012-10-10
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.action;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hwsensor.permission.pojo.SysResourcePO;
import com.hwsensor.permission.service.ISysResourceService;

/**
 * 项目名称：framework 类名称：SysResourceAction 类描述： 创建人：杜群星 创建时间：2012-10-10 下午5:27:51
 */
public class SysResourceAction extends BaseAction {

	@Autowired
	private ISysResourceService sysResourceService;

	private SysResourcePO sysResourcePO;

	private List<SysResourcePO> resourceList = new ArrayList<SysResourcePO>();

	private String operTree;

	public String doLoadTree() {
		try {
			sysResourceService.buildTree(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "loadtree";
	}

	public String doList() {
		try {
			sysResourceService.retrieveSysResourceList(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	public String doAdd() {
		return "add";
	}

	public String doSaveAdd() {
		try {
			sysResourceService.insertSysResourceBP(this);
			operLog(getUserIp() + "添加资源：" + sysResourcePO.getURL());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.JSON_MSG;
	}

	public String doEdit() {
		try {
			sysResourcePO = sysResourceService.retrieveSysResourceBP(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}

	public String doView() {
		try {
			sysResourcePO = sysResourceService.retrieveSysResourceBP(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}

	public String doSaveEdit() {
		try {
			sysResourceService.updateSysResourceBP(this);
			operLog(getUserIp() + "修改资源：" + sysResourcePO.getURL());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.JSON_MSG;
	}

	public String doDelete() {
		try {
			sysResourceService.deleteSysResourceBP(this);
			operLog(getUserIp() + "删除资源：" + sysResourcePO.getURL());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.JSON_MSG;
	}

	// --------------- private methods --------------
	
	private String getUserIp(){
		return request.getRemoteAddr();
	}
	
	// --------------- getter and setter --------------
	
	public ISysResourceService getSysResourceService() {
		return sysResourceService;
	}

	public void setSysResourceService(ISysResourceService sysResourceService) {
		this.sysResourceService = sysResourceService;
	}

	public SysResourcePO getSysResourcePO() {
		return sysResourcePO;
	}

	public void setSysResourcePO(SysResourcePO sysResourcePO) {
		this.sysResourcePO = sysResourcePO;
	}

	public List<SysResourcePO> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<SysResourcePO> resourceList) {
		this.resourceList = resourceList;
	}

	public String getOperTree() {
		return operTree;
	}

	public void setOperTree(String operTree) {
		this.operTree = operTree;
	}
	
}
