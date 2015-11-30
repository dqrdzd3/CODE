/**
 * 文件名：TregulationAction.java
 *
 * 版本信息：
 * 日期：2012-5-30
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.action;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.attachment.pojo.C004PO;
import com.hw.hwsafe.attachment.service.IAttachmentService;
import com.hw.hwsafe.knowledge.pojo.TregulationPO;
import com.hw.hwsafe.knowledge.service.ITregulationService;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.DictionaryPO;
import com.hw.hwsafe.platform.service.CodeValueService;
import com.hw.hwsafe.platform.service.IDictionaryService;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：hwsafe 
 * 类名称：TregulationAction 
 * 类描述：规章制度Action 
 * 创建人：刁海港 
 * 创建时间：2012-5-30 上午10:31:33  
 * 修改时间：2012-5-30 上午10:31:33
 * 修改备注：
 * 
 * @version
 * 
 */
public class TregulationAction extends BaseAction {
	
    private TregulationPO tregulationPO;
     
    @Autowired
    private ITregulationService tregulationService;
    
	@Autowired
	private IAttachmentService attachmentService;
 	
 	private List<TregulationPO> tregulationList;
 	
 	@Autowired
 	private CodeValueService codevalueService;
 	
 	@Autowired
	private IDictionaryService dictService;//新的数据字段表
 	
 	private List<DictionaryPO> zdlx;//规章制度类型
 	
 	private String ids;
 	
	public static String getExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 展示所有的规章制度列表
	 */
	public String doList() {
		return SUCCESS;
	}
	
	/**
	 * 增加操作
	 */
	public String doAdd(){
		try {
			zdlx=dictService.retrieveChildDictListByKey("gzzd");
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
			String[] c004ids = request.getParameter("c004ids").split(",");
			tregulationPO.setMA001(UUIDGenerater.getUUID());
			tregulationPO.setC004ids(c004ids);
			tregulationPO.setMA002(getSessionUserPO().getCorpid());
			tregulationPO.setMA013(0);
			tregulationPO.setMA014(DateTimeUtils.getCurrentDate());
			tregulationPO.setMA017(getSessionUserPO().getId());
			tregulationService.insertTregulation(tregulationPO);
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
	public String doEdit() {
		try {
			tregulationPO = tregulationService.retrieveTregulationByID(tregulationPO.getMA001());
			zdlx=dictService.retrieveChildDictListByKey("gzzd");
			setJsonPO(tregulationPO);
			if (tregulationPO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			List<C004PO> c004poList = attachmentService.getC004List(tregulationPO.getMA001(),null);
			tregulationPO.setC004poList(c004poList);
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
		    	if(tregulationService.isExist(tregulationPO.getMA001())){
		    		String c004id =request.getParameter("c004ids");
					if(c004id!=null){
						String[] c004ids = c004id.split(",");
						tregulationPO.setC004ids(c004ids);
					}
		    		tregulationService.updateTregulation(tregulationPO);
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
	 * 浏览操作
	 */
	public String doView() {
		try {
			tregulationPO = tregulationService.retrieveTregulationByID(tregulationPO.getMA001());
			zdlx=dictService.retrieveChildDictListByKey("gzzd");
			setJsonPO(tregulationPO);
			if (tregulationPO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			List<C004PO> c004poList = attachmentService.getC004List(tregulationPO.getMA001(),null);
			tregulationPO.setC004poList(c004poList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}
	
	
	public String doGetUpdateSBSJ(){
		String[] Ids = ids.split(",");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ids", Ids);
		try {
			tregulationService.updateTregulationSBSJ(map);
			setSubmitSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setSubmitFailedMsg();
		}
		return JSON_MSG;
	}

	public ITregulationService getTregulationService() {
		return tregulationService;
	}

	public void setTregulationService(ITregulationService tregulationService) {
		this.tregulationService = tregulationService;
	}

	public CodeValueService getCodevalueService() {
		return codevalueService;
	}

	public void setCodevalueService(CodeValueService codevalueService) {
		this.codevalueService = codevalueService;
	}
	
	public List<DictionaryPO> getZdlx() {
		return zdlx;
	}

	public void setZdlx(List<DictionaryPO> zdlx) {
		this.zdlx = zdlx;
	}

	public TregulationPO getTregulationPO() {
		return tregulationPO;
	}

	public void setTregulationPO(TregulationPO tregulationPO) {
		this.tregulationPO = tregulationPO;
	}

	public void setTregulationList(List<TregulationPO> tregulationList) {
		this.tregulationList = tregulationList;
	}

	public List<TregulationPO> getTregulationList() {
		return tregulationList;
	}
	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public IDictionaryService getDictService() {
		return dictService;
	}

	public void setDictService(IDictionaryService dictService) {
		this.dictService = dictService;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
