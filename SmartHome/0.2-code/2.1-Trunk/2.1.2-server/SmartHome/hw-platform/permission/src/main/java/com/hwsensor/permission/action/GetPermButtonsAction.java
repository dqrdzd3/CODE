package com.hwsensor.permission.action;

import java.util.List;
import org.apache.struts2.ServletActionContext;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hwsensor.permission.common.PermUtil;
import com.hwsensor.permission.pojo.SysModulePO;
import com.hwsensor.permission.pojo.SysOperationPO;

/**
 * 
 * 项目名称：framework
 * 类名称：GetPermButtonsAction
 * 类描述：获取模块的权限菜单
 * 创建人：孟繁波
 * 创建时间：2012-8-11 下午11:12:30
 * 修改人：孟繁波
 * 修改时间：2012-8-11 下午11:12:30
 * 修改备注：
 * @version 
 * 
 */
@Deprecated
public class GetPermButtonsAction extends BaseAction {
	
	private static final String JSON_PERM_BTN = "jsonPerm";
	private String permButtonStr;

	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			// 模块url
			String actionUrl = ServletActionContext.getRequest().getParameter(
					"actionUrl");
			// UserPO userPO =
			// ((UserSession)getSession().getAttribute(Constants.USER_SESSION_KEY)).getUserPO();

			List<SysModulePO> sysModulePOs = (List<SysModulePO>) getSession()
					.getAttribute(Constants.ALL_USER_PERM);

			List<SysOperationPO> sysOperationPOs = (List<SysOperationPO>) getSession()
					.getAttribute(Constants.ALL_USER_OPER);

			// permButtonStr =
			// PermUtil.buildPermButtonsStr(actionUrl,userPO,sysModulePOs);
			permButtonStr = PermUtil.buildPermButtonsStr(actionUrl,
					sysModulePOs, sysOperationPOs);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return JSON_PERM_BTN;
	}

	
	public String getPermButtonStr() {
		return permButtonStr;
	}

	public void setPermButtonStr(String permButtonStr) {
		this.permButtonStr = permButtonStr;
	}
	
}
