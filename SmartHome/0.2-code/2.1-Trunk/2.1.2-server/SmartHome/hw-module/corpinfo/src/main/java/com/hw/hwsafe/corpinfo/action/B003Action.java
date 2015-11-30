/**
 * 文件名：B003Action.java
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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.constants.CorpinfoConstants;
import com.hw.hwsafe.corpinfo.pojo.B003PO;
import com.hw.hwsafe.corpinfo.service.IB00302Service;
import com.hw.hwsafe.corpinfo.service.IB003Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.pojo.CodeValuePO;
import com.hw.hwsafe.platform.pojo.DictionaryPO;
import com.hw.hwsafe.platform.service.CodeValueService;
import com.hw.hwsafe.platform.service.IDictionaryService;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B003Action
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:33:40
 * 修改人：
 * 修改时间：2012-6-11 下午6:33:40
 * 修改备注：
 * @version 
 * 
 */
public class B003Action extends BaseAction implements Preparable{
	
	private B003PO b003PO;
	
	@Autowired
	private IB003Service b003Service;
	@Autowired
	private IB00302Service b00302Service;
	
	private List<B003PO> b003List;
	
	@Autowired
	private CodeValueService codevalueService;//代码表Service
	
	@Autowired
	private IDictionaryService dictService;//新的数据字段表
	private List<DictionaryPO>  codeValueAQRYLB;//安全人员类别
	private List<CodeValuePO>  sex;//性别
	private List<CodeValuePO>  CodeValueMZ; //民族
	private List<CodeValuePO>  CodeValueXL;//学历
	private List<CodeValuePO>  CodeValueJSZC;//技术职称
	private List<DictionaryPO>  CodeValueTSGZ;//特殊工种
	B003PO  paramPO = new B003PO();
	public void prepare() throws Exception {
		if(b003PO==null){
			b003PO=new B003PO();
		}
		b003PO.setMA003(this.getSessionUserPO().getCorpid());//企业用户只能察看自己公司的信息
	} 
	/**
	 * 展示所有的企业列表
	 */
	public String doList() {
		return SUCCESS;
	}
	
	 /**
		 * 分页方法重写 (没有PO类)
		 */
		public String queryData1() throws Exception {
			// 分页信息
			pagerData =  initPagerData();
			// 参数Map
			Map<String, Object> paramMap = new HashMap<String,Object>();
			paramMap.put("pagerData", pagerData);
			// 根据条件查询分页数据
			paramMap.put("po", b003PO); // 查询条件
			pagerData.setRows(b003Service.retrieveByPage1(paramMap));
			
			// 根据条件查询总条数
			paramMap.put("selectCount", true);
			pagerData.setRecords(b003Service.retrieveByPage1(paramMap));
			
			return JSON_PAGER;
		}
		/**
		 * 返回到企业特殊工种人员的列表页面
		 * @return
		 */
		public String getCount(){
			return "count";
		}
	/**
	 * 增加操作
	 */
	public String doAdd(){
		try {
			//查询代码表
			codeValueAQRYLB=dictService.retrieveChildDictListByKey("aqrylx");//安全人员类别
			sex=codevalueService.retrieveCodeByType("sex");//性别
			CodeValueMZ=codevalueService.retrieveCodeByType("mz");//民族
			CodeValueXL=codevalueService.retrieveCodeByType("xl");//学历
			CodeValueJSZC=codevalueService.retrieveCodeByType("jszc");//技术职称
			CodeValueTSGZ=dictService.retrieveChildDictListByKey("tsgzry");//特殊工种
			
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
			/**
			 * 安全人员编号不允许重复
			 */
			if (b003Service.checkCorpNum(b003PO)) {
				message.set(Constants.MSG_ERROR,CorpinfoConstants.NUMBER_EXIST_MSG);
				return JSON_MSG;
			}
			b003PO.setMA001(UUIDGenerater.getUUID());
			b003PO.setMA003(SessionUtil.getOrgId());
			b003PO.setMA013(DateTimeUtils.getCurrentDate());// 调用公共方法
			b003PO.setMA022(SessionUtil.getUserId());
			b003Service.insertB003(b003PO);
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
			b003PO = b003Service.retrieveB003ByID(b003PO.getMA001());
			if (b003PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			} else {
				codeValueAQRYLB = dictService.retrieveChildDictListByKey("aqrylx");// 安全人员类别
				sex = codevalueService.retrieveCodeByType("sex");// 性别
				CodeValueMZ = codevalueService.retrieveCodeByType("mz");// 民族
				CodeValueXL = codevalueService.retrieveCodeByType("xl");// 学历
				CodeValueJSZC = codevalueService.retrieveCodeByType("jszc");// 技术职称
				CodeValueTSGZ = dictService.retrieveChildDictListByKey("tsgzry");// 特殊工种
			}
			// 查询代码表
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}
	/**
	 * 
	 * doView(浏览记录时查询)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doView() {
		try {
			b003PO=b003Service.retrieveB003ByID(b003PO.getMA001());
			if(b003PO==null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			else {
				codeValueAQRYLB=dictService.retrieveChildDictListByKey("aqrylx");//安全人员类别
				sex=codevalueService.retrieveCodeByType("sex");//性别
				CodeValueMZ=codevalueService.retrieveCodeByType("mz");//民族
				CodeValueXL=codevalueService.retrieveCodeByType("xl");//学历
				CodeValueJSZC=codevalueService.retrieveCodeByType("jszc");//技术职称
				CodeValueTSGZ=dictService.retrieveChildDictListByKey("tsgzry");//特殊工种
			}
			//查询代码表
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
			 * 判断人员编号是否重复
			 */
			if (b003Service.checkCorpNum(b003PO)) {
				message.set(Constants.MSG_WARNING, CorpinfoConstants.NUMBER_EXIST_MSG);
				return JSON_MSG ;
			}
			/**
			 * 判断记录是否存在
			 */
			if(b003Service.isExist(b003PO.getMA001())){
				b003Service.updateB003(b003PO);
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
	/**
	 * 
	 * doQueryByID(根据主键ID查询JsonPO对象
	 * @param  
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	
	public String doQueryByID()	{
		try {
			b003PO=b003Service.retrieveB003ByID(b003PO.getMA001());
			setJsonPO(b003PO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}
	
	/**
	 * 通过企业ID和员工姓名查询员工信息（名称重复的展示最早的）
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getInfoByname(){
		try {
		
		paramPO.setMA003(request.getParameter("corpid"));
		paramPO.setMA004(request.getParameter("name"));
		sex=codevalueService.retrieveCodeByType("sex");//性别
		CodeValueMZ=codevalueService.retrieveCodeByType("mz");//民族
		CodeValueJSZC=codevalueService.retrieveCodeByType("jszc");//技术职称
		CodeValueXL=codevalueService.retrieveCodeByType("xl");//学历
		List b003List= b003Service.getInfoByname(paramPO);
		if(!b003List.isEmpty()){
			paramPO=(B003PO)b003List.get(0);
		}
		if(paramPO.getMA001()==null||paramPO.getMA001().equals("")){
			message.set(Constants.MSG_WARNING, "无此人信息");
			return JSON_MSG;
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "info";
	}

	public List<DictionaryPO> getCodeValueAQRYLB() {
		return codeValueAQRYLB;
	}

	public void setCodeValueAQRYLB(List<DictionaryPO> codeValueAQRYLB) {
		this.codeValueAQRYLB = codeValueAQRYLB;
	}

	public void setB003List(List<B003PO> b003List) {
		this.b003List = b003List;
	}
	
	public List<B003PO> getB003List() {
		return b003List;
	}
	
	public void setB003PO(B003PO b003PO) {
		this.b003PO = b003PO;
	}

	public B003PO getB003PO() {
		return b003PO;
	}

	public List<CodeValuePO> getCodeValueMZ() {
		return CodeValueMZ;
	}

	public void setCodeValueMZ(List<CodeValuePO> codeValueMZ) {
		CodeValueMZ = codeValueMZ;
	}

	public List<CodeValuePO> getCodeValueXL() {
		return CodeValueXL;
	}

	public void setCodeValueXL(List<CodeValuePO> codeValueXL) {
		CodeValueXL = codeValueXL;
	}

	public List<CodeValuePO> getCodeValueJSZC() {
		return CodeValueJSZC;
	}

	public void setCodeValueJSZC(List<CodeValuePO> codeValueJSZC) {
		CodeValueJSZC = codeValueJSZC;
	}

	

	public List<DictionaryPO> getCodeValueTSGZ() {
		return CodeValueTSGZ;
	}

	public void setCodeValueTSGZ(List<DictionaryPO> codeValueTSGZ) {
		CodeValueTSGZ = codeValueTSGZ;
	}

	public IB003Service getB003Service() {
		return b003Service;
	}

	public void setB003Service(IB003Service b003Service) {
		this.b003Service = b003Service;
	}

	public CodeValueService getCodevalueService() {
		return codevalueService;
	}

	public void setCodevalueService(CodeValueService codevalueService) {
		this.codevalueService = codevalueService;
	}

	public IDictionaryService getDictService() {
		return dictService;
	}

	public void setDictService(IDictionaryService dictService) {
		this.dictService = dictService;
	}
	public List<CodeValuePO> getSex() {
		return sex;
	}
	public void setSex(List<CodeValuePO> sex) {
		this.sex = sex;
	}
	public IB00302Service getB00302Service() {
		return b00302Service;
	}
	public void setB00302Service(IB00302Service b00302Service) {
		this.b00302Service = b00302Service;
	}
	public B003PO getParamPO() {
		return paramPO;
	}
	public void setParamPO(B003PO paramPO) {
		this.paramPO = paramPO;
	}
	
}
