package com.hw.hwsafe.corpinfo.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B00301PO;
import com.hw.hwsafe.corpinfo.pojo.B003PO;
import com.hw.hwsafe.corpinfo.service.IB00301Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 
 * 项目名称：hw-corpinfo
 * 类名称：B00301Action
 * 类描述：人员培训信息类
 * 创建人：刁海港
 * 创建时间：2013-4-9 下午2:35:06
  * @version 
 *
 */
public class B00301Action extends BaseAction {

	@Autowired
	private IB00301Service b00301Service;

	private B003PO b003PO;
	private B00301PO b00301PO;

	private List<B00301PO> b00301List;

	/**
	 * 查询列表
	 */
	public String doList() {
		return SUCCESS;
	}

	/**
	 * 跳转到添加页面
	 */
	public String doAdd() {
		return "add";
	}
	/**
	 * 增加保存操作
	 */
	public String doSaveAdd() {
		try {
			b00301PO.setMA001(UUIDGenerater.getUUID());
			b00301PO.setMA009(getSessionUserPO().getId());
			b00301Service.insertB00301(b00301PO);
			setAddSuccessMsg();
		} catch (Exception e) {
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
			b00301PO = b00301Service.retrieveB00301ByID(b00301PO.getMA001());
			if (b00301PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			} 
			// 查询代码表
		} catch (Exception e) {
			setQueryFailedMsg();
		}
		return JSON_PO;
	}
	/**
	 * 浏览操作
	 */
	public String doView() {
		try {
			b00301PO = b00301Service.retrieveB00301ByID(b00301PO.getMA001());
			if (b00301PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
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
			if(b00301Service.isExist(b00301PO.getMA001())){
				b00301Service.updateB00301(b00301PO);
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
	 * 删除人员培训信息
	 * @return
	 */
	public String delb00301() {
		try {
			message=b00301Service.delb00301(request.getParameter("ids"));
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

	public B00301PO getB00301PO() {
		return b00301PO;
	}

	public void setB00301PO(B00301PO b00301po) {
		b00301PO = b00301po;
	}

	public List<B00301PO> getB00301List() {
		return b00301List;
	}

	public void setB00301List(List<B00301PO> b00301List) {
		this.b00301List = b00301List;
	}

	public IB00301Service getB00301Service() {
		return b00301Service;
	}

	public void setB00301Service(IB00301Service b00301Service) {
		this.b00301Service = b00301Service;
	}
	
}
