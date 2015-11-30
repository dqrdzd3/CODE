/**
 * 文件名：B005Action.java
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
import com.hw.hwsafe.corpinfo.pojo.B005PO;
import com.hw.hwsafe.corpinfo.service.IB005Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B005Action
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:01:06
 * 修改人：
 * 修改时间：2012-6-11 下午6:01:06
 * 修改备注：
 * @version 
 * 
 */
public class B005Action extends BaseAction implements Preparable{
	
	private B005PO b005PO;
	
	@Autowired
	private IB005Service b005Service;
	
	private List<B005PO> b005List;
	
	private String ids;
	/**
	 * 准备
	 */
	public void prepare() throws Exception {
		if(b005PO==null){
			b005PO=new B005PO();
		}
		b005PO.setMA003(this.getSessionUserPO().getCorpid());
	} 
	/**
	 * 跳转到List页面
	 */
	public String doList() {
		
		return SUCCESS;
	}
	/**
	 * 增加操作
	 */
	public String doAdd(){
		return "add";
		
	}
	
	/**
	 * 增加保存操作
	 */
	public String doSaveAdd() {
		try {
			String c004id = getRequest().getParameter("c004ids");
			/**
			 * 没有点开浏览的按钮，对图片不做操作
			 */
			if ("noupdate".equals(c004id)) {

			}
			/**
			 * 点开过，处理图片，按c004ids的值
			 */
			else {
				String[] c004ids = c004id.split(",");
				b005PO.setC004ids(c004ids);
			}
			b005PO.setMA001(UUIDGenerater.getUUID());
			b005PO.setMA003(SessionUtil.getOrgId());// 企业ID
			b005PO.setMA010(B005PO.YX);// 1:有效0：无效
			b005PO.setMA013(getSessionUserPO().getId());
			b005PO.setMA014(DateTimeUtils.getCurrentDate());
			/**
			 * 企业证书的编号不允许重复
			 */
			if (b005Service.checkCorpcertificate(b005PO)) {
				getMessage().set(Constants.MSG_ERROR,CorpinfoConstants.NUMBER_EXIST_MSG);
				return JSON_MSG;
			}
			b005Service.insertB005(b005PO);
			setAddSuccessMsg();
			b005PO.setC004ids(null);
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
			b005PO=b005Service.retrieveB005ByID(b005PO.getMA001());
			if(b005PO==null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}
	/**
	 * 浏览时查询数据
	 */
	public String doView() {
		try {
			b005PO=b005Service.retrieveB005ByID(b005PO.getMA001());
			if(b005PO==null) {
				setEmptyDataMsg();
				return JSON_MSG;
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
			/**
			 * 企业证书的编号不允许重复
			 */
			if (b005Service.checkCorpcertificate(b005PO)) {
				getMessage().set(Constants.MSG_ERROR,CorpinfoConstants.NUMBER_EXIST_MSG);
				return JSON_MSG;
			}
			/**
			 * 查看记录是否存在
			 */
			if (b005Service.isExist(b005PO.getMA001())) {
				String c004id = getRequest().getParameter("c004ids");
				String[] c004ids = c004id.split(",");
				b005PO.setC004ids(c004ids);
				b005Service.updateB005(b005PO);
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
		String[] b005Ids = ids.split(",");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ids", b005Ids);
		try {
			b005Service.updateB005SBSJ(map);
			setSubmitSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setSubmitFailedMsg();
		}
		return JSON_MSG;
	}

	public IB005Service getB005Service() {
		return b005Service;
	}

	public void setB005Service(IB005Service b005Service) {
		this.b005Service = b005Service;
	}
	public void setB005List(List<B005PO> b005List) {
		this.b005List = b005List;
	}
	
	public List<B005PO> getB005List() {
		return b005List;
	}
	
	public void setB005PO(B005PO b005PO) {
		this.b005PO = b005PO;
	}

	public B005PO getB005PO() {
		return b005PO;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
}
