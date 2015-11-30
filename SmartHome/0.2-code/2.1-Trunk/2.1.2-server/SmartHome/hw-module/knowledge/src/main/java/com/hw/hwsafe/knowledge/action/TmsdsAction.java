package com.hw.hwsafe.knowledge.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.knowledge.constants.KnowledgeConstants;
import com.hw.hwsafe.knowledge.pojo.TmsdsPO;
import com.hw.hwsafe.knowledge.service.ITmsdsService;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：framework
 * 类名称：TmsdsAction
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-5-12 下午2:27:02
 * 修改人：马宁
 * 修改时间：2012-10-23 16:57
 * 修改备注：添加对化学品名称唯一性的验证
 * @version 
 * 
 */
public class TmsdsAction extends BaseAction {

	private TmsdsPO tmsdsPO;
	
	@Autowired
	private ITmsdsService tmsdsService;
	
	private List<TmsdsPO> tmsdsList;

	/**
	 * 展示所有的角色列表
	 */
	public String doList() {
		return SUCCESS;
	}
	
	/**
	 * 
	 * 函 数 名：doAdd
	 * 功能描述：增加操作
	 * 创建时间：2012-10-23 下午4:58:06
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doAdd(){
		return "add";
	}
	
	/**
	 * 
	 * 函 数 名：doSaveAdd
	 * 功能描述：增加保存操作
	 * 创建时间：2012-10-23 下午4:58:16
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doSaveAdd(){
		try{
			if(isNotNameUniqueWhenAdd()){
				message.set(Constants.MSG_ERROR, KnowledgeConstants.NAME_EXIST_MSG);
				return JSON_MSG;
			}else{
				tmsdsPO.setObjid(UUIDGenerater.getUUID());	
				tmsdsPO.setInputdate(DateTimeUtils.getCurrentDate());
				tmsdsPO.setCreator(getSessionUserPO().getId());
				tmsdsService.insertTmsds(tmsdsPO);
				setAddSuccessMsg();
			}
		}catch(Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 
	 * 函 数 名：doEdit
	 * 功能描述：修改操作
	 * 创建时间：2012-10-23 下午4:58:27
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doEdit() {
		try {
			tmsdsPO = tmsdsService.retrieveTmsdsByID(tmsdsPO.getObjid());
			setJsonPO(tmsdsPO);
			if (tmsdsPO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
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
	 * 创建时间：2012-10-23 下午4:58:38
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doSaveEdit(){
		try{
			if(isNotNameUniqueWhenUpdate()){
				message.set(Constants.MSG_ERROR, KnowledgeConstants.NAME_EXIST_MSG);
				return JSON_MSG;
			}if(tmsdsService.isExist(tmsdsPO.getObjid())){
				tmsdsService.updateTmsds(tmsdsPO);
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
	 * 查询记录浏览操作
	 */
	public String doView() {
		try {
			tmsdsPO = tmsdsService.retrieveTmsdsByID(tmsdsPO.getObjid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}
	
	/**
	 * 
	 * isNotNameUniqueWhenAdd(查询名称是否重复)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	private boolean isNotNameUniqueWhenAdd() throws Exception{
		return !tmsdsService.isNameUniqueWhenAdd(tmsdsPO.getChnname());
	}
	/**
	 * 
	 * isNotNameUniqueWhenUpdate(查询名称是否重复)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	private boolean isNotNameUniqueWhenUpdate() throws Exception{
		return !tmsdsService.isNameUniqueWhenUpdate(tmsdsPO);
	}
	
	/**
	 * get和set方法
	 */
	public void setTmsdsList(List<TmsdsPO> tmsdsList) {
		this.tmsdsList = tmsdsList;
	}
	
	public List<TmsdsPO> getTmsdsList() {
		return tmsdsList;
	}
	
	public void setTmsdsPO(TmsdsPO tmsdsPO) {
		this.tmsdsPO = tmsdsPO;
	}

	public TmsdsPO getTmsdsPO() {
		return tmsdsPO;
	}

	public ITmsdsService getTmsdsService() {
		return tmsdsService;
	}

	public void setTmsdsService(ITmsdsService tmsdsService) {
		this.tmsdsService = tmsdsService;
	}
	
}

