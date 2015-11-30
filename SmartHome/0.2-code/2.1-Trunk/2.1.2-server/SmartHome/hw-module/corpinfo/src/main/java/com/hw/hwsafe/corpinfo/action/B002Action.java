/**
 * 文件名：B002Action.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.action;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.corpinfo.constants.CorpinfoConstants;
import com.hw.hwsafe.corpinfo.pojo.B002PO;
import com.hw.hwsafe.corpinfo.service.IB002Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.pojo.CodeValuePO;
import com.hw.hwsafe.platform.service.CodeValueService;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B002Action
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午5:59:59
 * 修改人：
 * 修改时间：2012-6-11 下午5:59:59
 * 修改备注：
 * @version 
 * 
 */
public class B002Action extends BaseAction implements Preparable  {
	
	private B002PO b002PO;

	@Autowired
	private IB002Service b002Service;

	private List<B002PO> b002List;

	@Autowired
	private CodeValueService codevalueService;// 代码表Service

	private List<CodeValuePO> codeValueYAZD;// 应急预案制定情况
	private List<CodeValuePO> codeValueAQPJ;// 安全评价情况
	
	private String ids;

	public void prepare() throws Exception {
		if (b002PO == null) {
			b002PO = new B002PO();
		}
		String corpID = request.getParameter("ORGAN_UUID");
		b002PO.setMA003((corpID != null && !corpID.equals("")) ? corpID : this.getSessionUserPO().getCorpid());// 企业用户只能察看自己公司的信息
	}
	
	/**
	 * 控制跳转到List页面
	 */
	public String doList() {
		
		return SUCCESS;
	}
	
	
	/**
	 * 增加操作
	 */
	public String doAdd(){
		try {
			//查询代码表
			codeValueYAZD=codevalueService.retrieveCodeByType("yazd");//应急预案制定情况
			codeValueAQPJ=codevalueService.retrieveCodeByType("aqpj");//安全评价情况
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "add";
	}
	
	/**
	 * 增加保存操作
	 */
	public String doSaveAdd() {
		try {
			/**
			 * 判断企业机构名称是否重复
			 */
			if (b002Service.checkCorpName(b002PO)) {
				message.set(Constants.MSG_ERROR, CorpinfoConstants.NAME_EXIST_MSG);//以后从常量类取值
				return JSON_MSG;
			}
			b002PO.setMA001(UUIDGenerater.getUUID());
			b002PO.setMA003(getSessionUserPO().getCorpid());
			b002PO.setMA022(DateTimeUtils.getCurrentDate());
			b002PO.setMA025(getSessionUserPO().getId());
			b002PO.setMA018(0);//安全管理人员数新添加的为0
			b002Service.insertB002(b002PO);
			setAddSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 修改操作
	 */
	public String doEdit() {
		try {
			b002PO = b002Service.retrieveB002ByID(b002PO.getMA001());
			if (b002PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			} else {
				codeValueYAZD = codevalueService.retrieveCodeByType("yazd");// 应急预案制定情况
				codeValueAQPJ = codevalueService.retrieveCodeByType("aqpj");// 安全评价情况
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}
	
	/**
	 * 浏览操作
	 */
	public String doView() {
		try {
			b002PO = b002Service.retrieveB002ByID(b002PO.getMA001());
			if (b002PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			} else {
				codeValueYAZD = codevalueService.retrieveCodeByType("yazd");// 应急预案制定情况
				codeValueAQPJ = codevalueService.retrieveCodeByType("aqpj");// 安全评价情况
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}
	
	/**
	 * 修改保存操作
	 * 
	 */
	public String doSaveEdit() {
		try{
			/**
			 * 判断机构名称是否重复
			 */
			if (b002Service.checkCorpName(b002PO)) {
				message.set(Constants.MSG_ERROR, CorpinfoConstants.NAME_EXIST_MSG);
				return JSON_MSG ;
			}
			/**
			 * 判断记录是否存在
			 */
			if(b002Service.isExist(b002PO.getMA001())){
				b002Service.updateB002(b002PO);
				setUpdateSuccessMsg();
			}else {
				setEmptyDataMsg();
			}
		}catch(Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	
	public String doGetUpdateSBSJ(){
		String[] b002Ids = ids.split(",");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ids", b002Ids);
		try {
			b002Service.updateB002SBSJ(map);
			setSubmitSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setSubmitFailedMsg();
		}
		return JSON_MSG;
	}


	public IB002Service getB002Service() {
		return b002Service;
	}

	public void setB002Service(IB002Service b002Service) {
		this.b002Service = b002Service;
	}

	public CodeValueService getCodevalueService() {
		return codevalueService;
	}

	public void setCodevalueService(CodeValueService codevalueService) {
		this.codevalueService = codevalueService;
	}
	
	public List<CodeValuePO> getCodeValueYAZD() {
		return codeValueYAZD;
	}

	public void setCodeValueYAZD(List<CodeValuePO> codeValueYAZD) {
		this.codeValueYAZD = codeValueYAZD;
	}

	public List<CodeValuePO> getCodeValueAQPJ() {
		return codeValueAQPJ;
	}

	public void setCodeValueAQPJ(List<CodeValuePO> codeValueAQPJ) {
		this.codeValueAQPJ = codeValueAQPJ;
	}

	public void setB002List(List<B002PO> b002List) {
		this.b002List = b002List;
	}
	
	public List<B002PO> getB002List() {
		return b002List;
	}
	
	public void setB002PO(B002PO b002PO) {
		this.b002PO = b002PO;
	}

	public B002PO getB002PO() {
		return b002PO;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
