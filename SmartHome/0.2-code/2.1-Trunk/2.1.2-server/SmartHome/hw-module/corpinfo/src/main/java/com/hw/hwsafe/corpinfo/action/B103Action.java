/**
 * 文件名：B103Action.java
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

import com.hw.hwsafe.attachment.pojo.C004PO;
import com.hw.hwsafe.attachment.service.IAttachmentService;
import com.hw.hwsafe.corpinfo.constants.CorpinfoConstants;
import com.hw.hwsafe.corpinfo.pojo.B002PO;
import com.hw.hwsafe.corpinfo.pojo.B003PO;
import com.hw.hwsafe.corpinfo.pojo.B103PO;
import com.hw.hwsafe.corpinfo.service.IB00302Service;
import com.hw.hwsafe.corpinfo.service.IB003Service;
import com.hw.hwsafe.corpinfo.service.IB103Service;
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
 * 类名称：B103Action
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:33:40
 * 修改人：
 * 修改时间：2012-6-11 下午6:33:40
 * 修改备注：
 * @version 
 * 
 */
public class B103Action extends BaseAction implements Preparable{
	
    private B103PO b103PO;
    private B002PO b002PO;
	
	@Autowired
	private IB103Service b103Service;
	
	
	private List<B103PO> b103List;
	
	@Autowired
	private IAttachmentService attachmentService;
	
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
	B103PO  paramPO = new B103PO();
	public void prepare() throws Exception {
		if(b103PO==null){
			b103PO=new B103PO();
		}
		String corpID = request.getParameter("ORGAN_UUID");
		b103PO.setMA003(corpID != null && !corpID.equals("") ? corpID : this.getSessionUserPO().getCorpid());//企业用户只能察看自己公司的信息
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
			paramMap.put("po", b103PO); // 查询条件
//			pagerData.setRows(b103Service.retrieveByPage1(paramMap));
			
			// 根据条件查询总条数
			paramMap.put("selectCount", true);
//			pagerData.setRecords(b103Service.retrieveByPage1(paramMap));
			
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
//			/**
//			 * 安全人员编号不允许重复
//			 */
//			if (b103Service.checkCorpNum(b103PO)) {
//				message.set(Constants.MSG_ERROR,CorpinfoConstants.NUMBER_EXIST_MSG);
//				return JSON_MSG;
//			}
			String c004ids = request.getParameter("c004ids");
			if(c004ids != null){
				String[] idarr = c004ids.split(",");
				b103PO.setC004ids(idarr);
			}
			b103PO.setMA001(UUIDGenerater.getUUID());
			b103PO.setMA003(SessionUtil.getOrgId());
			b103PO.setMA006(b103PO.getMA024());
			b103PO.setMA013(DateTimeUtils.getCurrentDate());// 调用公共方法
			b103PO.setMA022(SessionUtil.getUserId());
			b103Service.insertB103(b103PO);
			b103PO.setC004ids(null);
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
			b103PO = b103Service.retrieveB103ByID(b103PO.getMA001());
			if (b103PO == null) {
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
		setJsonPO(b103PO);
		return JSON_PO;
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
			b103PO=b103Service.retrieveB103ByID(b103PO.getMA001());
			if(b103PO==null) {
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
				List<C004PO> c004poList = attachmentService.getC004List(b103PO.getMA001(),null);
				getRequest().setAttribute("list", c004poList);
				b103PO.setC004poList(c004poList);
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
//			/**
//			 * 判断人员编号是否重复
//			 */
//			if (b103Service.checkCorpNum(b103PO)) {
//				message.set(Constants.MSG_WARNING, CorpinfoConstants.NUMBER_EXIST_MSG);
//				return JSON_MSG ;
//			}
			
			 String c004id =request.getParameter("c004ids");
				if(c004id!=null){
					String[] c004ids = c004id.split(",");
					b103PO.setC004ids(c004ids);
				}
			/**
			 * 判断记录是否存在
			 */
			if(b103Service.isExist(b103PO.getMA001())){
				
				b103PO.setMA006(b103PO.getMA024());
				b103Service.updateB103(b103PO);
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
			b103PO=b103Service.retrieveB103ByID(b103PO.getMA001());
			setJsonPO(b103PO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}
	
//	/**
//	 * 通过企业ID和员工姓名查询员工信息（名称重复的展示最早的）
//	 * @return
//	 */
//	@SuppressWarnings("rawtypes")
//	public String getInfoByname(){
//		try {
//		
//		paramPO.setMA003(request.getParameter("corpid"));
//		paramPO.setMA004(request.getParameter("name"));
//		sex=codevalueService.retrieveCodeByType("sex");//性别
//		CodeValueMZ=codevalueService.retrieveCodeByType("mz");//民族
//		CodeValueJSZC=codevalueService.retrieveCodeByType("jszc");//技术职称
//		CodeValueXL=codevalueService.retrieveCodeByType("xl");//学历
//		List b103List= b103Service.getInfoByname(paramPO);
//		if(!b103List.isEmpty()){
//			paramPO=(B103PO)b103List.get(0);
//		}
//		if(paramPO.getMA001()==null||paramPO.getMA001().equals("")){
//			message.set(Constants.MSG_WARNING, "无此人信息");
//			return JSON_MSG;
//		}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "info";
//	}

	
	public String  delb103(){
		try {
			int stat = b103Service.getB002Stat(request.getParameter("ids").split(",")[0]);
			if (stat !=0){
				message.set(Constants.MSG_WARNING, "所选数据有已上报数据，不能删除，请重新选择!","信息");
				return JSON_MSG;
			}
			b103Service.delb103(request.getParameter("ids"),request.getParameter("ryType"));
			setDelSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	public List<DictionaryPO> getCodeValueAQRYLB() {
		return codeValueAQRYLB;
	}

	public void setCodeValueAQRYLB(List<DictionaryPO> codeValueAQRYLB) {
		this.codeValueAQRYLB = codeValueAQRYLB;
	}

	public void setB103List(List<B103PO> b103List) {
		this.b103List = b103List;
	}
	
	public List<B103PO> getB103List() {
		return b103List;
	}
	
	public void setB103PO(B103PO b103PO) {
		this.b103PO = b103PO;
	}

	public B103PO getB103PO() {
		return b103PO;
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

	public IB103Service getB103Service() {
		return b103Service;
	}

	public void setB103Service(IB103Service b103Service) {
		this.b103Service = b103Service;
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
	public B103PO getParamPO() {
		return paramPO;
	}
	public void setParamPO(B103PO paramPO) {
		this.paramPO = paramPO;
	}
	public B002PO getB002PO() {
		return b002PO;
	}
	public void setB002PO(B002PO b002po) {
		b002PO = b002po;
	}
	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}
	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
}
