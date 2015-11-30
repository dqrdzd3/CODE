package com.hw.hwsafe.corpinfo.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B018PO;
import com.hw.hwsafe.corpinfo.service.IB018Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：hw-corpinfo
 * 类名称：B018Action
 * 类描述：应急药品类
 * 创建人：刁海港
 * 创建时间：2013-4-10 下午2:42:49
  * @version 
 */
public class B018Action extends BaseAction {
	  
	private B018PO b018PO;
	
	@Autowired
	private IB018Service b018Service;
	
	private List<B018PO> b018List;
	/**
	 * 展示所有记录列表
	 */
	public String doList() {
		return SUCCESS;
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String doAdd(){
		return "add";
	}
	
	/**
	 * 增加保存操作
	 */
	public String doSaveAdd(){
		try{
			b018PO.setMA001(UUIDGenerater.getUUID());// 主键
			b018PO.setMA002(SessionUtil.getOrgId());//
			b018PO.setMA022(1);// 状态 1：有效 0：无效
			b018PO.setMA025(DateTimeUtils.getCurrentDate());
			b018PO.setMA024(SessionUtil.getUserId());
			b018Service.insertB018(b018PO);
			setAddSuccessMsg();
		}catch(Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 修改操作
	 */
	public String doEdit()	{
		try {		
			b018PO=b018Service.retrieveB018ByID(b018PO.getMA001());
			if(b018PO==null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}
	/**
	 * 浏览操作查询记录
	 */
	public String doView()	{
		try {		
			b018PO=b018Service.retrieveB018ByID(b018PO.getMA001());
			if(b018PO==null) {
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
			if(b018Service.isExist(b018PO.getMA001())){
				b018Service.updateB018(b018PO);
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

	public IB018Service getB018Service() {
		return b018Service;
	}

	public void setB018Service(IB018Service b018Service) {
		this.b018Service = b018Service;
	}

	public void setB018List(List<B018PO> b018List) {
		this.b018List = b018List;
	}

	public List<B018PO> getB018List() {
		return b018List;
	}

	public void setB018PO(B018PO b018PO) {
		this.b018PO = b018PO;
	}

	public B018PO getB018PO() {
		return b018PO;
	}
}