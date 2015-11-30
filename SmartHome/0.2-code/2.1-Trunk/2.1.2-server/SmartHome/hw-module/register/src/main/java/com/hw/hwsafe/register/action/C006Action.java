/**
 * 文件名：C006Action.java
 * 版本信息：
 * 日期：2012-12-21
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 */
package com.hw.hwsafe.register.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.corpinfo.service.IC007Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.pojo.CodeValuePO;
import com.hw.hwsafe.platform.service.CodeValueService;
import com.hw.hwsafe.platform.service.IDictionaryService;
import com.hw.hwsafe.register.pojo.C006PO;
import com.hw.hwsafe.register.service.IC006Service;
import com.hw.hwsafe.utils.PasswordUtil;

/**
 * 
 * 
 * 项目名称：hw-cpnyreg
 * 类名称：C006Action
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-12-21 下午4:57:54
 * 修改人：李玉梅
 * 修改时间：2012-12-21 下午4:57:54
 * 修改备注：
 * @version 
 *
 */
public class C006Action extends BaseAction {
	
	@Autowired
	private IC006Service c006Service;
	
	@Autowired
	private IDictionaryService dictService;
	
	@Autowired
	private IC007Service c007Service;
	
	@Autowired
	private CodeValueService codevalueService;// 代码表Service
	
	
	private C006PO c006PO;
	
	
	private List codeValueJJLX;// 代码表：经济类型
	private List codeValueSSHY;// 代码表：所属行业
	
	private List<CodeValuePO> codeValueLSGX;// 代码表：隶属关系
	private List<CodeValuePO> codeValueQYLX;// 代码表：企业类型
	
	private List hymlList;
	private List dlList;
	private List zlList;
	private List xlList;
	private List zgdwList;
	
	private List zgdwData;
	public List getZgdwList() {
		return zgdwList;
	}

	public List getZgdwData() {
		return zgdwData;
	}

	public void setZgdwData(List zgdwData) {
		this.zgdwData = zgdwData;
	}

	public void setZgdwList(List zgdwList) {
		this.zgdwList = zgdwList;
	}

	private String hymlID;
	private String dlID;
	private String zlID;
	private String xlID;
	
	private String editC006ID;
	
	//修改时，用户名和密码信息
	private String username;
	private String password;
	


	public IC006Service getC006Service() {
		return c006Service;
	}

	public void setC006Service(IC006Service c006Service) {
		this.c006Service = c006Service;
	}

	

	public IDictionaryService getDictService() {
		return dictService;
	}

	public void setDictService(IDictionaryService dictService) {
		this.dictService = dictService;
	}

	/**
	 * 
	
	 * doList(页面跳转
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doList(){
		return JSON_MSG;
	}
	/**
	 * 
	
	 * doAdd(添加页面跳转
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @throws Exception 
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doAdd() throws Exception{

		codeValueJJLX = dictService.retrieveChildDictListByKey("jjlx");
		codeValueSSHY = dictService.retrieveChildDictListByKey("sshy");
		codeValueLSGX = codevalueService.retrieveCodeByType("lsgx");// 隶属关系
		codeValueQYLX = codevalueService.retrieveCodeByType("qylx");// 企业类型
		hymlList=c007Service.getHymList();
		dlList=new ArrayList();
		zlList=new ArrayList();
		xlList=new ArrayList();
		
		return "add";
	}
	
	/**
	 * 注册成功后跳转
	 * @return          
	 * @author 陈浙东
	 * @create_time 2013-9-16 下午5:22:37
	 */
	public String doSuccess(){
		String zhanghao = request.getParameter("zhanghao");
		request.setAttribute("zhanghao", zhanghao);
		return "success1";
	}
	/**
	 * 
	
	 * doRegister(注册页面跳转
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doRegister() throws Exception{

		codeValueJJLX = dictService.retrieveChildDictListByKey("jjlx");
		codeValueSSHY = dictService.retrieveChildDictListByKey("sshy");
		codeValueLSGX = codevalueService.retrieveCodeByType("lsgx");// 隶属关系
		codeValueQYLX = codevalueService.retrieveCodeByType("qylx");// 企业类型
		hymlList=c007Service.getHymList();
		zgdwList=c006Service.getZgdwList();
		dlList=new ArrayList();
		zlList=new ArrayList();
		xlList=new ArrayList();
		return "register";
	}

	public String  getZgdw()throws Exception{
		String pId=request.getParameter("pId");
		zgdwData = c006Service.getZgdwData(pId);
//		request.setAttribute("zgdwData", zgdwData);
		return "zgdwdata";
	}
	/**
	 * 
	
	 * doEdit修改页面跳转
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doEdit(){
		try{
			codeValueJJLX = dictService.retrieveChildDictListByKey("jjlx");
			codeValueSSHY = dictService.retrieveChildDictListByKey("sshy");
			codeValueLSGX = codevalueService.retrieveCodeByType("lsgx");// 隶属关系
			codeValueQYLX = codevalueService.retrieveCodeByType("qylx");// 企业类型
			c006PO=c006Service.retrieveC006ByID(c006PO.getMa001());
		}catch(Exception e){
			e.printStackTrace();
		}
		return JSON_PO;
	}
	/**
	 * 
	
	 * doEditHC(点击核查后的跳转页面
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doEditHC() {
		try {
			codeValueJJLX = dictService.retrieveChildDictListByKey("jjlx");

			c006PO=c006Service.retrieveC006ByID(c006PO.getMa001());
			
			if (c006PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			
			hymlList=c007Service.getHymList();
			String hymlid=c006PO.getHymlID();
			String hydlid=c006PO.getHydlID();
			String hyzlid=c006PO.getHyzlID();		
			
			if(hymlid==null||hymlid.equals("")) {
				dlList=new ArrayList();
			}else {
				dlList = c007Service.getHydlList(hymlid);
			}
			if(hydlid==null||hydlid.equals(""))	{
				zlList=new ArrayList();
			}else {
				zlList = c007Service.getHydlList(hydlid);
			}
			if(hyzlid==null||hyzlid.equals(""))	{
				xlList=new ArrayList();
			}else {
				xlList = c007Service.getHydlList(hyzlid);
			}

			setJsonPO(c006PO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "hc";
	}
	/**
	 * 
	
	 * doSaveAdd(添加保存注册信息，因为添加时通过注册页面添加，不是从系统内添加，所以添加后PO清空，列表要重新赋值
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doSaveAdd(){
		try{
			message=c006Service.insertC006(c006PO);	
			//this.getRequest().setAttribute("registersuccess", "registersuccess");
			//c006PO = new C006PO();
			//codeValueJJLX = dictionaryService.retrieveChildDictListByKey("jjlx", null);
			//codeValueSSHY = dictionaryService.retrieveChildDictListByKey("sshy", null);
		}catch(Exception e){
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;	
		
	}
	
	public boolean sendMessage(C006PO c006po) throws Exception{
		
		return c006Service.sendSMSNotice(c006po ,1);
		
	}

	/**
	 * 
	
	 * doSaveEdit(修改保存
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doSaveEdit(){
		try{
			message=c006Service.updateC006(c006PO);
		}catch(Exception e){
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	/**
	 * 
	
	 * doSaveHCEdit(政府端核查企业信息：核查通过
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doSaveHCT(){
		try{
			c006PO.setMa020(C006PO.STATUS_YES);//核查通过，将状态置为20
			message=c006Service.updateHCC006(c006PO);
			//setUpdateSuccessMsg();
		}catch(Exception e){
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	/**
	 * 
	
	 * doSaveHCN(政府端核查信息：核查不通过
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doSaveHCN(){
		try{
			c006PO.setMa020(C006PO.STATUS_NO);//核查未通过，将状态置为30
			message=c006Service.updateHCC006(c006PO);
		}catch(Exception e){
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	/**
	 * 
	
	 * checkOnlyMa003(判断代码是否已经存在
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void checkOnlyMa003(){
		try {
			response.setContentType("text/html;charset=UTF-8");
			if (StringUtils.isNotBlank(request.getParameter("ma003"))) {
				String ma003 = request.getParameter("ma003");
				String ma001=StringUtils.isNotBlank(request.getParameter("ma001"))?request.getParameter("ma001"):null;
				C006PO c006Po=new C006PO();
				c006Po.setMa001(ma001);
				c006Po.setMa003(ma003);
				if (c006Service.isUniqueMa003(c006Po)) {
					response.getWriter().print(true);
					return;
				}
			}
			response.getWriter().print(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	
	 * checkOnlyMa002(判断企业名称是否已经存在
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void checkOnlyMa002(){
		try {
			response.setContentType("text/html;charset=UTF-8");
			if (StringUtils.isNotBlank(request.getParameter("ma002"))) {
				String ma002 = request.getParameter("ma002");
				String ma001=StringUtils.isNotBlank(request.getParameter("ma001"))?request.getParameter("ma001"):null;
				C006PO c006Po=new C006PO();
				c006Po.setMa001(ma001);
				c006Po.setMa002(ma002);
				if (c006Service.isUniqueMa002(c006Po)) {
					response.getWriter().print(true);
					return;
				}
			}
			response.getWriter().print(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	
	 * checkOnlyMa002(判断企业邮箱是否已经存在
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void checkOnlyMa015(){
		try {
			response.setContentType("text/html;charset=UTF-8");
			if (StringUtils.isNotBlank(request.getParameter("ma015"))) {
				String ma015 = request.getParameter("ma015");
				String ma001=StringUtils.isNotBlank(request.getParameter("ma001"))?request.getParameter("ma001"):null;
				C006PO c006Po=new C006PO();
				c006Po.setMa001(ma001);
				c006Po.setMa015(ma015);
				if (c006Service.isUniqueMa015(c006Po)) {
					response.getWriter().print(true);
					return;
				}
			}
			response.getWriter().print(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	
	 * checkOnlyMa010(判断用户名是否已经存在
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void checkOnlyMa010(){
		try {
			response.setContentType("text/html;charset=UTF-8");
			if (StringUtils.isNotBlank(request.getParameter("ma010"))) {
				String ma010 = request.getParameter("ma010");
				String ma001=StringUtils.isNotBlank(request.getParameter("ma001"))?request.getParameter("ma001"):null;
				C006PO c006Po=new C006PO();
				c006Po.setMa001(ma001);
				c006Po.setMa010(ma010);
				if (c006Service.isUniqueMa010(c006Po)) {
					response.getWriter().print(true);
					return;
				}
			}
			response.getWriter().print(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkOnlyMa014(){
		try {
			response.setContentType("text/html;charset=UTF-8");
			if (StringUtils.isNotBlank(request.getParameter("ma014"))) {
				String ma014 = request.getParameter("ma014");
				String ma001=StringUtils.isNotBlank(request.getParameter("ma001"))?request.getParameter("ma001"):null;
				C006PO c006Po=new C006PO();
				c006Po.setMa001(ma001);
				c006Po.setMa014(ma014);
				if (c006Service.isUniqueMa014(c006Po)) {
					response.getWriter().print(true);
					return;
				}
			}
			response.getWriter().print(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String doDeleteC006ByIds(){
		
		try {
			if(StringUtils.isNotBlank(request.getParameter("ids"))){
				String ids=request.getParameter("ids");
				message=c006Service.deleteC006ByIds(ids);
			}else{
				setDelFailedMsg();
			}
		} catch (Exception e) {
			setDelFailedMsg();
		}
		return JSON_MSG;
	}

	
	
	/**
	 * 
	 * 查询行业大类List:根据门类
	 */
	public String doGetHydlList() {
		try {
			dlList = c007Service.getHydlList(hymlID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dloption";
	}

	/**
	 * 
	 * 查询行业中类List:根据大类
	 */
	public String doGetHyzlList() {
		try {
			zlList = c007Service.getHydlList(dlID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "zloption";
	}
	/**
	 * 
	 * 查询行业小类List:根据中类
	 */
	public String doGetHyxlList() {
		try {
			xlList = c007Service.getHydlList(zlID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "xloption";
	}
	
	
	/**
	 * 
	
	 * doEditRegister(修改注册跳转页面
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doEditRegister() throws Exception{
		c006PO=c006Service.retrieveC006ByID(editC006ID);
		codeValueJJLX = dictService.retrieveChildDictListByKey("jjlx");
		codeValueSSHY = dictService.retrieveChildDictListByKey("sshy");
		codeValueLSGX = codevalueService.retrieveCodeByType("lsgx");// 隶属关系
		codeValueQYLX = codevalueService.retrieveCodeByType("qylx");// 企业类型
		hymlList=c007Service.getHymList();
		String hymlid=c006PO.getHymlID();
		String hydlid=c006PO.getHydlID();
		String hyzlid=c006PO.getHyzlID();		
		
		if(hymlid==null||hymlid.isEmpty())
		{
			dlList=new ArrayList();
		}else {
			dlList = c007Service.getHydlList(hymlid);
		}
		if(hydlid==null||hydlid.isEmpty())
		{
			zlList=new ArrayList();
			
		}else {
			zlList = c007Service.getHydlList(hydlid);
		}
		if(hyzlid==null||hyzlid.isEmpty())
		{
			xlList=new ArrayList();
		}else {
			xlList = c007Service.getHydlList(hyzlid);
		}
		c006PO.setOldMa003(c006PO.getMa003());//记录组织机构代码：记录修改前的组织机构,在修改之前先记录
		return "loginRegister";
	}
	/**
	 * 
	
	 * doLoginC006(登录页面跳转，
	 * @param   李玉梅
	 */
	public String doLoginC006() {
		C006PO loginC006PO=new C006PO();
		loginC006PO.setMa010(username);
		loginC006PO.setMa011(PasswordUtil.createPassword(password));
		try {
			c006PO=c006Service.retrieveC006ByNamePassword(loginC006PO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		if(c006PO!=null){
			HashMap c006map=new HashMap();
			//如果已经审核过，则不允许进行修改：审核通过后不允许修改，要是审核未通过则应该允许修改
			if(c006PO.getMa020().equals(C006PO.STATUS_YES)){
				message.set(Constants.MSG_OK, "no",c006map) ;
			}else {
				c006map.put("c006PO",c006PO);
				message.set(Constants.MSG_OK, "true",c006map) ;
			}
		}else
		{
			message.set(Constants.MSG_ERROR, "false") ;
		}
		return JSON_MSG;
	}
	
	public C006PO getC006PO() {
		return c006PO;
	}

	public void setC006PO(C006PO c006po) {
		c006PO = c006po;
	}


	public List getCodeValueJJLX() {
		return codeValueJJLX;
	}
	public void setCodeValueJJLX(List codeValueJJLX) {
		this.codeValueJJLX = codeValueJJLX;
	}
	public List getCodeValueSSHY() {
		return codeValueSSHY;
	}
	public void setCodeValueSSHY(List codeValueSSHY) {
		this.codeValueSSHY = codeValueSSHY;
	}

	public IC007Service getC007Service() {
		return c007Service;
	}

	public void setC007Service(IC007Service c007Service) {
		this.c007Service = c007Service;
	}

	public List getHymlList() {
		return hymlList;
	}

	public void setHymlList(List hymlList) {
		this.hymlList = hymlList;
	}

	public List getDlList() {
		return dlList;
	}

	public void setDlList(List dlList) {
		this.dlList = dlList;
	}

	public List getZlList() {
		return zlList;
	}

	public void setZlList(List zlList) {
		this.zlList = zlList;
	}

	public List getXlList() {
		return xlList;
	}

	public void setXlList(List xlList) {
		this.xlList = xlList;
	}

	public String getHymlID() {
		return hymlID;
	}

	public void setHymlID(String hymlID) {
		this.hymlID = hymlID;
	}

	public String getDlID() {
		return dlID;
	}

	public void setDlID(String dlID) {
		this.dlID = dlID;
	}

	public String getZlID() {
		return zlID;
	}

	public void setZlID(String zlID) {
		this.zlID = zlID;
	}

	public String getXlID() {
		return xlID;
	}

	public void setXlID(String xlID) {
		this.xlID = xlID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEditC006ID() {
		return editC006ID;
	}

	public void setEditC006ID(String editC006ID) {
		this.editC006ID = editC006ID;
	}

	public CodeValueService getCodevalueService() {
		return codevalueService;
	}

	public void setCodevalueService(CodeValueService codevalueService) {
		this.codevalueService = codevalueService;
	}

	public List<CodeValuePO> getCodeValueLSGX() {
		return codeValueLSGX;
	}

	public void setCodeValueLSGX(List<CodeValuePO> codeValueLSGX) {
		this.codeValueLSGX = codeValueLSGX;
	}

	public List<CodeValuePO> getCodeValueQYLX() {
		return codeValueQYLX;
	}

	public void setCodeValueQYLX(List<CodeValuePO> codeValueQYLX) {
		this.codeValueQYLX = codeValueQYLX;
	}


	
}
