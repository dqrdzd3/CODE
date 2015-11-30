/**
 * 文件名：K006Action.java
 *
 * 版本信息：1.0
 * 日期：2012-6-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.knowledge.constants.KnowledgeConstants;
import com.hw.hwsafe.knowledge.pojo.K006PO;
import com.hw.hwsafe.knowledge.service.IK006Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.pojo.CodeValuePO;
import com.hw.hwsafe.platform.service.CodeValueService;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：hwsafe 
 * 类名称：K006Action 
 * 类描述： 
 * 创建人：刁海港 
 * 创建时间：2012-6-8 下午2:09:36 
 * 修改人：
 * 修改时间：2012-6-8 下午2:09:36 
 * 修改备注：
 * 
 * @version
 * 
 */
public class K006Action extends BaseAction {

	private K006PO k006PO;

	@Autowired
	private IK006Service k006Service;// k006Service

	private List<K006PO> k006List;

	private List<CodeValuePO> sslbList;// 职业病知识库中所属类别：

	@Autowired
	private CodeValueService codevalueService;// 代码表Service

	/**
	 * 展示所有的记录列表
	 */
	public String doList() {
		return SUCCESS;
	}

	/**
	 * 
	 * 函 数 名：doAdd
	 * 功能描述：跳转到添加页面
	 * 创建时间：2012-10-23 下午3:31:39
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doAdd() {
		try {
			sslbList = codevalueService.retrieveCodeByType("zyblb");// 职业病类别
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "add";
	}

	/**
	 * 
	 * 函 数 名：doSaveAdd
	 * 功能描述：增加保存操作
	 * 创建时间：2012-10-23 下午3:32:04
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doSaveAdd() {
		try {
			if(isNotMA002UniqueWhenAdd()){
				message.set(Constants.MSG_ERROR, KnowledgeConstants.NAME_EXIST_MSG);
			}else{
				k006PO.setMA001(UUIDGenerater.getUUID());
				k006PO.setMA025(getSessionUserPO().getId());
				k006PO.setMA007(DateTimeUtils.getCurrentDate());
				k006Service.insertK006(k006PO);
				setAddSuccessMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		
		return JSON_MSG;
	}

	/**
	 * 
	 * 函 数 名：doEdit
	 * 功能描述：修改操作
	 * 创建时间：2012-10-23 下午3:32:24
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doEdit() {
		try {
			k006PO = k006Service.retrieveK006ByID(k006PO.getMA001());
			if (k006PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			} else {
				sslbList = codevalueService.retrieveCodeByType("zyblb");// 职业病类别
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return JSON_PO;
	}

	/**
	 * 
	 * 函 数 名：doSaveEdit
	 * 功能描述：修改保存操作
	 * 创建时间：2012-10-23 下午3:32:46
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doSaveEdit() {
		try {
			if(isNotMA002UniqueWhenUpdate()){
				message.set(Constants.MSG_ERROR, KnowledgeConstants.NAME_EXIST_MSG);
				return JSON_MSG;
			}
			if(k006Service.isExist(k006PO.getMA001())){
				k006Service.updateK006(k006PO);
				setUpdateSuccessMsg();
			}else{
				setEmptyDataMsg();
			}
		} catch (Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	/**
	 * 浏览操作
	 */
	public String doView() {
		try {
			k006PO = k006Service.retrieveK006ByID(k006PO.getMA001());
			if (k006PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}else{
				sslbList = codevalueService.retrieveCodeByType("zyblb");// 职业病类别
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "view";
	}

	/**
	 * 
	 * isNotMA002UniqueWhenAdd(判断名称是否重复)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	private boolean isNotMA002UniqueWhenAdd() throws Exception{
		return !k006Service.isMA002UniqueWhenAdd(k006PO.getMA002());
	}
	/**
	 * 
	 * isNotMA002UniqueWhenUpdate(判断名称是否重复)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	private boolean isNotMA002UniqueWhenUpdate() throws Exception{
		return !k006Service.isMA002UniqueWhenUpdate(k006PO);
	}
	/**
	 * get()和set()方法
	 */

	public K006PO getK006PO() {
		return k006PO;
	}

	public void setK006PO(K006PO k006po) {
		k006PO = k006po;
	}

	public List<K006PO> getK006List() {
		return k006List;
	}

	public void setK006List(List<K006PO> k006List) {
		this.k006List = k006List;
	}

	public List<CodeValuePO> getSslbList() {
		return sslbList;
	}

	public void setSslbList(List<CodeValuePO> sslbList) {
		this.sslbList = sslbList;
	}

	public IK006Service getK006Service() {
		return k006Service;
	}

	public void setK006Service(IK006Service k006Service) {
		this.k006Service = k006Service;
	}

	public CodeValueService getCodevalueService() {
		return codevalueService;
	}

	public void setCodevalueService(CodeValueService codevalueService) {
		this.codevalueService = codevalueService;
	}
	
}
