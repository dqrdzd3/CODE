/**
 * 文件名：B012Action.java
 *
 * 版本信息：1.0
 * 日期：2012-7-2
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B012PO;
import com.hw.hwsafe.corpinfo.service.IB012Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CodeValuePO;
import com.hw.hwsafe.platform.service.CodeValueService;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B012Action
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-7-2 上午10:49:54
 * 修改人：
 * 修改时间：2012-7-2 上午10:49:54
 * 修改备注：
 * @version 
 * 
 */
public class B012Action extends BaseAction {

	private B012PO b012PO;

	@Autowired
	private IB012Service b012Service;

	private List<B012PO> b012List;

	@Autowired
	private CodeValueService codevalueService;//代码表Service
	
	private List<CodeValuePO> codeValueCPLX;
	private List<CodeValuePO> codeValueDW;

	/**
	 * 展示所有的企业列表
	 */
	public String doList() {
		return SUCCESS;
	}
	/**
	 * 跳转到危化品的列表页面
	 * @return
	 */
	public String getCount(){
		return "count";
	}
	/**
	 * 跳转到添加页面
	 */
	public String doAdd(){
		try {
			codeValueCPLX=codevalueService.retrieveCodeByType("cplx");//危化品产品类型
			codeValueDW=codevalueService.retrieveCodeByType("sldw");//数量单位
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
			b012PO.setMA001(UUIDGenerater.getUUID());
			String corpid=SessionUtil.getOrgId();
			b012PO.setMA003(corpid);
			b012PO.setMA010(DateTimeUtils.getCurrentDate());
			b012PO.setMA011(DateTimeUtils.getCurrentDate());
			b012PO.setMA012(B012PO.ZT1);//状态 ：1--未申报；2--待审核；3--通过；0--未通过
			b012PO.setMA014(SessionUtil.getUserId());
			b012PO.setMA015(B012PO.BZ);
			b012Service.insertB012(b012PO);
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
	public String doEdit()	{
		try {
			b012PO=b012Service.retrieveB012ByID(b012PO.getMA001());
			if(b012PO==null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			else {
				codeValueCPLX=codevalueService.retrieveCodeByType("cplx");//危化品产品类型
				codeValueDW=codevalueService.retrieveCodeByType("sldw");//数量单位
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}
	/**
	 * 浏览操作查看
	 */
	public String doView()	{
		try {
			b012PO=b012Service.retrieveB012ByID(b012PO.getMA001());
			if(b012PO==null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			else {
				codeValueCPLX=codevalueService.retrieveCodeByType("cplx");//危化品产品类型
				codeValueDW=codevalueService.retrieveCodeByType("sldw");//数量单位
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}
	/**
	 * 修改保存操作
	 */
	public String doSaveEdit(){
		try{
			if(b012Service.isExist(b012PO.getMA001())){
				b012Service.updateB012(b012PO);
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

	public IB012Service getB012Service() {
		return b012Service;
	}

	public void setB012Service(IB012Service b012Service) {
		this.b012Service = b012Service;
	}

	public CodeValueService getCodevalueService() {
		return codevalueService;
	}

	public void setCodevalueService(CodeValueService codevalueService) {
		this.codevalueService = codevalueService;
	}

	public List<CodeValuePO> getCodeValueCPLX() {
		return codeValueCPLX;
	}

	public void setCodeValueCPLX(List<CodeValuePO> codeValueCPLX) {
		this.codeValueCPLX = codeValueCPLX;
	}

	public List<CodeValuePO> getCodeValueDW() {
		return codeValueDW;
	}

	public void setCodeValueDW(List<CodeValuePO> codeValueDW) {
		this.codeValueDW = codeValueDW;
	}

	public void setB012List(List<B012PO> b012List) {
		this.b012List = b012List;
	}

	public List<B012PO> getB012List() {
		return b012List;
	}

	public void setB012PO(B012PO b012PO) {
		this.b012PO = b012PO;
	}

	public B012PO getB012PO() {
		return b012PO;
	}

}

