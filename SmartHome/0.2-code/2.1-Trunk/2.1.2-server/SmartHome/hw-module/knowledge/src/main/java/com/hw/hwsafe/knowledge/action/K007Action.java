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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.attachment.pojo.C004PO;
import com.hw.hwsafe.attachment.service.IAttachmentService;
import com.hw.hwsafe.knowledge.pojo.K007PO;
import com.hw.hwsafe.knowledge.service.IK007Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CodeValuePO;
import com.hw.hwsafe.platform.service.CodeValueService;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：K007Action
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-6 下午2:59:17
 * 修改人：李玉梅
 * 修改时间：2012-6-6 下午2:59:17
 * 修改备注：
 * @version 
 *
 */
public class K007Action  extends BaseAction { 
	
	private K007PO k007PO;
	
	@Autowired
	private IK007Service k007Service;//k007Service
	
	@Autowired
	private CodeValueService codevalueService;//代码表Service
	@Autowired
	private IAttachmentService attachmentService;
	
	private List<K007PO> k007List;
	
	private List<CodeValuePO> codeValueList=new ArrayList<CodeValuePO>();//预案模块中事故类型：大类
	
	private List<CodeValuePO> codeValueZlList;//预案模块中事故类型：子类
	
	private Map<String,List<CodeValuePO>> codeValueZlMap=new HashMap<String,List<CodeValuePO>>();
	
	private List<CodeValuePO> yazlList;//预案种类：综合、专项、现场

	public static String getExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 展示所有的记录列表
	 * @throws Exception 
	 */
	public String doList() {
		try {
			yazlList=codevalueService.retrieveCodeByYazl();//预案类型：
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到添加页面
	 */
	public String doAdd(){
		
		try {
			yazlList=codevalueService.retrieveCodeByYazl();//预案类型：
			codeValueList=codevalueService.retrieveCodeByYalx();//事故类型：大类
			codeValueZlList = new ArrayList<CodeValuePO>();//事故类型：子类
			for(CodeValuePO codevalue : codeValueList){
				List<CodeValuePO> codevaluelist=codevalueService.retrieveCodeByYalxZl(codevalue.getValue());
				codeValueZlMap.put(codevalue.getValue(), codevaluelist);
			}
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
			k007PO.setC004ids(c004ids);
			k007Service.insertK007(this);
			setAddSuccessMsg();
		}catch(Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	/**
	 * 修改操作
	 */
	public String doEdit()
	{
		try {
			yazlList=codevalueService.retrieveCodeByYazl();//预案类型：
			codeValueList=codevalueService.retrieveCodeByYalx();//事故类型：大类
			codeValueZlList = new ArrayList<CodeValuePO>();//事故类型：子类
			for(CodeValuePO codevalue : codeValueList){
				List<CodeValuePO> codevaluelist=codevalueService.retrieveCodeByYalxZl(codevalue.getValue());
				codeValueZlMap.put(codevalue.getValue(), codevaluelist);
			}
			k007PO=k007Service.retrieveK007ByID(k007PO.getMA001());
			List<C004PO> c004poList = attachmentService.getC004List(k007PO.getMA001(),null);
			k007PO.setC004poList(c004poList);
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
			if(k007Service.isExist(k007PO.getMA001())){
				String c004id =request.getParameter("c004ids");
				if(c004id!=null){
					String[] c004ids = c004id.split(",");
					k007PO.setC004ids(c004ids);
				}
				k007Service.updateK007(k007PO);
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
	public String doView()
	{
		try {
			yazlList=codevalueService.retrieveCodeByYazl();//预案类型：
			codeValueList=codevalueService.retrieveCodeByYalx();//事故类型：大类
			codeValueZlList = new ArrayList<CodeValuePO>();//事故类型：子类
			for(CodeValuePO codevalue : codeValueList){
				List<CodeValuePO> codevaluelist=codevalueService.retrieveCodeByYalxZl(codevalue.getValue());
				codeValueZlMap.put(codevalue.getValue(), codevaluelist);
			}
			k007PO=k007Service.retrieveK007ByID(k007PO.getMA001());
			List<C004PO> c004poList = attachmentService.getC004List(k007PO.getMA001(),null);
			k007PO.setC004poList(c004poList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}
	/**
	 * 通过事故种类，显示子类
	 */
	public String doZlByType() throws Exception {
		String typeId = request.getParameter("typeId");// 获得从前台得到的参数，事故种类：大类
		typeId = new String(typeId.getBytes("ISO-8859-1"), "UTF-8");
		doAdd();
		k007PO = new K007PO(); // 使得大类选中
		k007PO.setMA006(typeId);
		codeValueZlList = codevalueService.retrieveCodeByYalxZl(typeId);
		return "select";
	}
	public Map<String, List<CodeValuePO>> getCodeValueZlMap() {
		return codeValueZlMap;
	}

	public void setCodeValueZlMap(Map<String, List<CodeValuePO>> codeValueZlMap) {
		this.codeValueZlMap = codeValueZlMap;
	}

	public void setCodeValueList(List<CodeValuePO> codeValueList) {
		this.codeValueList = codeValueList;
	}

	public IK007Service getK007Service() {
		return k007Service;
	}

	public void setK007Service(IK007Service k007Service) {
		this.k007Service = k007Service;
	}

	public CodeValueService getCodevalueService() {
		return codevalueService;
	}

	public void setCodevalueService(CodeValueService codevalueService) {
		this.codevalueService = codevalueService;
	}

	public List<CodeValuePO> getYazlList() {
		return yazlList;
	}

	public void setYazlList(List<CodeValuePO> yazlList) {
		this.yazlList = yazlList;
	}

	public List<CodeValuePO> getCodeValueList() {
		return codeValueList;
	}

	public void setCodevalueList(List<CodeValuePO> codeValueList) {
		this.codeValueList = codeValueList;
	}

	public List<CodeValuePO> getCodeValueZlList() {
		return codeValueZlList;
	}

	public void setCodeValueZlList(List<CodeValuePO> codeValueZlList) {
		this.codeValueZlList = codeValueZlList;
	}

	public void setK007List(List<K007PO> k007List) {
		this.k007List = k007List;
	}

	public List<K007PO> getK007List() {
		return k007List;
	}

	public void setK007PO(K007PO k007PO) {
		this.k007PO = k007PO;
	}

	public K007PO getK007PO() {
		return k007PO;
	}
	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
}
