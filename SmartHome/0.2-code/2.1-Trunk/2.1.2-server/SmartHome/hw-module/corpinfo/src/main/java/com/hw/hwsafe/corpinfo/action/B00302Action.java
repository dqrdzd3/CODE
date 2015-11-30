package com.hw.hwsafe.corpinfo.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B00302PO;
import com.hw.hwsafe.corpinfo.pojo.B003PO;
import com.hw.hwsafe.corpinfo.service.IB00302Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 
 * 项目名称：hw-corpinfo
 * 类名称：B00302Action
 * 类描述：人员证书信息类
 * 创建人：刁海港
 * 创建时间：2013-4-9 下午3:12:52
  * @version 
 *
 */
public class B00302Action extends BaseAction {
	
	@Autowired
	private IB00302Service b00302Service;
	
	private B003PO b003PO;
	private B00302PO b00302PO;
	
	private List<B00302PO> b00302List;
	/**
	 * 跳转到列表页面
	 */
	public String doList(){
		return SUCCESS;
	}
	
	/**
	 * 增加操作
	 */
	public String doAdd() {
		return "add";
	}

	/**
	 * 增加保存操作
	 */
	public String doSaveAdd() {
		try {
			b00302PO.setMA001(UUIDGenerater.getUUID());
			b00302PO.setMA008(getSessionUserPO().getId());
			b00302Service.insertB00302(b00302PO);
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
			b00302PO = b00302Service.retrieveB00302ByID(b00302PO.getMA001());
			if (b00302PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}else {
				setEmptyDataMsg();
			}
			// 查询代码表
		} catch (Exception e) {
			setQueryFailedMsg();
		}
		return JSON_PO;
	}

	public String doView() {
		try {
			b00302PO = b00302Service.retrieveB00302ByID(b00302PO.getMA001());
			if (b00302PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			} else {
				setEmptyDataMsg();
			}
			// 查询代码表
		} catch (Exception e) {
			setQueryFailedMsg();
		}
		return "view";
	}
	/**
	 * 修改保存操作
	 */
	public String doSaveEdit() {
		try {
			if(b00302Service.isExist(b00302PO.getMA001())){
				b00302Service.updateB00302(b00302PO);
				setUpdateSuccessMsg();
			}else {
				setEmptyDataMsg();
			}
		} catch (Exception e) {
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	/**
	 * 删除人员证书信息
	 */
	public String delb00302() {
		try {
			message=b00302Service.delb00302(request.getParameter("ids"));
			setDelSuccessMsg();
		} catch (Exception e) {
			setDelFailedMsg();
		}
		return JSON_MSG;
	}

	public B003PO getB003PO() {
		return b003PO;
	}

	public void setB003PO(B003PO b003po) {
		b003PO = b003po;
	}

	public B00302PO getB00302PO() {
		return b00302PO;
	}

	public void setB00302PO(B00302PO b00302po) {
		b00302PO = b00302po;
	}

	public List<B00302PO> getB00302List() {
		return b00302List;
	}

	public void setB00302List(List<B00302PO> b00302List) {
		this.b00302List = b00302List;
	}

	public IB00302Service getB00302Service() {
		return b00302Service;
	}

	public void setB00302Service(IB00302Service b00302Service) {
		this.b00302Service = b00302Service;
	}

}
