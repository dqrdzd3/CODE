/**
 * 文件名：OperationTag.java
 *
 * 版本信息：
 * 日期：2012-11-6
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hw.hwsafe.platform.constants.Constants;
import com.hwsensor.permission.common.PermUtil;
import com.hwsensor.permission.pojo.SysModulePO;
import com.hwsensor.permission.pojo.SysOperationPO;



/**
 * 项目名称：framework
 * 类名称：OperationTag
 * 类描述：页面权限按钮的自定义操作标签
 * 创建人：杜群星
 * 创建时间：2012-11-6 上午10:38:25
 */
public class OperationTag extends BodyTagSupport {

	private static final long serialVersionUID = -8127288276578397820L;

	private static final Logger logger = Logger.getLogger(OperationTag.class);
	
	/**
	 * 模块编码
	 */
	private String code;
	
	/**
	 * 操作名称字符串
	 */
	private String param;

	/**
	 * session中权限模块
	 */
	private List<SysModulePO> modules;
	
	/**
	 * session中的操作权限
	 */
	private List<SysOperationPO> operations;

	public OperationTag() {
		super();
	}

	@Override
	public int doStartTag() throws JspException {

		String str = "";

		init();

		if (modules == null) {
			modules = new ArrayList<SysModulePO>();
		}

		if (operations == null) {
			operations = new ArrayList<SysOperationPO>();
		}

		if (code != null && !code.isEmpty()) {
			if (param != null) {
				logger.info("操作标签初始化开始,code:{" + code + "},param:{" + param
						+ "}");
				str = PermUtil.buildPermButtonsStr(code, param, modules,
						operations);
			}
		}

		try {
			this.pageContext.getOut().print(str);
		} catch (IOException e) {
			logger.info("操作标签初始化失败：" + e.getMessage());
		}
		logger.info("操作标签初始化成功:" + str);
		return SKIP_BODY;
	}

	@SuppressWarnings("unchecked")
	private void init() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		modules = (List<SysModulePO>) session
				.getAttribute(Constants.ALL_USER_PERM);
		operations = (List<SysOperationPO>) session
				.getAttribute(Constants.ALL_USER_OPER);
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getParam() {
		return param;
	}

}
