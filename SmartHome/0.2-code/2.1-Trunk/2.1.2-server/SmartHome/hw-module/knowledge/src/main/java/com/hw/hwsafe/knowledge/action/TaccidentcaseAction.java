/**
 * 文件名：AccidentCasesAction.java
 *
 * 版本信息：
 * 日期：2012-5-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.knowledge.pojo.TaccidentcasePO;
import com.hw.hwsafe.knowledge.service.ITaccidentcaseService;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CodeValuePO;
import com.hw.hwsafe.platform.service.CodeValueService;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：TaccidentcaseAction
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-6 上午9:04:23
 * 修改人：李玉梅
 * 修改时间：2012-6-6 上午9:04:23
 * 修改备注：
 * @version 
 *
 */
public class TaccidentcaseAction  extends BaseAction {
	
	private TaccidentcasePO taccidentcasePO;
	
	@Autowired
	private ITaccidentcaseService taccidentcaseService;
	
	private List<TaccidentcasePO> taccidentcaseList;
	
	private List<CodeValuePO> sglxList;//事故案例中事故类型：
	private List<CodeValuePO> sghyList;//事故案例中事故行业：
	
	@Autowired
	private CodeValueService codevalueService;//代码表Service

	/**
	 * 展示所有的案例事故列表
	 */
	public String doList() {
		return SUCCESS;
	}
	/**
	 * 增加操作
	 */
	public String doAdd(){
		try {
			sglxList=codevalueService.retrieveCodeByCaseType();
			sghyList=codevalueService.retrieveCodeBySghy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "add";
	}
	
	/**
	 * 增加保存操作
	 */
	public String doSaveAdd(){
		try{
			taccidentcasePO.setObjid(UUIDGenerater.getUUID());
			taccidentcasePO.setCreator(getSessionUserPO().getId());
			taccidentcasePO.setCreatdate(DateTimeUtils.getCurrentDate());
			taccidentcaseService.insertTaccidentcase(taccidentcasePO);
			setAddSuccessMsg();
		}catch(Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 跳转到修改页面
	 */
	public String doEdit() {
		try {
			taccidentcasePO = taccidentcaseService.retrieveTaccidentcaseByID(taccidentcasePO.getObjid());
			if (taccidentcasePO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			} else {
				sglxList = codevalueService.retrieveCodeByCaseType();
				sghyList = codevalueService.retrieveCodeBySghy();
				setJsonPO(taccidentcasePO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}
	
	/**
	 * 修改保存操作
	 */
	public String doSaveEdit(){
		try{
			if(taccidentcaseService.isExist(taccidentcasePO.getObjid())){
				taccidentcaseService.updateTaccidentcase(taccidentcasePO);
				setUpdateSuccessMsg();
			}else{
				setEmptyDataMsg();
			}
		}catch(Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * doView(查询记录，浏览操作使用)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doView() {
		try {
			taccidentcasePO = taccidentcaseService.retrieveTaccidentcaseByID(taccidentcasePO.getObjid());
			sglxList = codevalueService.retrieveCodeByCaseType();
			sghyList = codevalueService.retrieveCodeBySghy();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}

	public ITaccidentcaseService getTaccidentcaseService() {
		return taccidentcaseService;
	}

	public void setTaccidentcaseService(
			ITaccidentcaseService taccidentcaseService) {
		this.taccidentcaseService = taccidentcaseService;
	}

	public CodeValueService getCodevalueService() {
		return codevalueService;
	}

	public void setCodevalueService(CodeValueService codevalueService) {
		this.codevalueService = codevalueService;
	}

	public void setTaccidentcaseList(List<TaccidentcasePO> taccidentcaseList) {
		this.taccidentcaseList = taccidentcaseList;
	}

	public List<TaccidentcasePO> getTaccidentcaseList() {
		return taccidentcaseList;
	}

	public void setTaccidentcasePO(TaccidentcasePO taccidentcasePO) {
		this.taccidentcasePO = taccidentcasePO;
	}

	public TaccidentcasePO getTaccidentcasePO() {
		return taccidentcasePO;
	}

	public List<CodeValuePO> getSglxList() {
		return sglxList;
	}

	public void setSglxList(List<CodeValuePO> sglxList) {
		this.sglxList = sglxList;
	}

	public List<CodeValuePO> getSghyList() {
		return sghyList;
	}

	public void setSghyList(List<CodeValuePO> sghyList) {
		this.sghyList = sghyList;
	}
	
}
