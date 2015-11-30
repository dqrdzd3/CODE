/**
 * 文件名：C001Action.java
 *
 * 版本信息：
 * 日期：2012-8-6
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.cpnyreg.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.cpnyreg.pojo.C001PO;
import com.hw.hwsafe.cpnyreg.service.IC001Service;
import com.hw.hwsafe.gov.pojo.C002PO;
import com.hw.hwsafe.gov.service.IC002Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.pojo.TreeNodePO;
import com.hw.hwsafe.platform.userinfo.UserSession;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：C001Action
 * 类描述：企业注册
 * 创建人：付强
 * 创建时间：2012-8-6 下午3:08:18
 * 
 */
public class C001Action extends BaseAction {

	@Autowired
	private IC001Service c001Service;
	@Autowired
	private IC002Service c002Service;
	
	private C001PO c001PO;
	private String c001Json;
	
	private List<TreeNodePO> treeNodes;

	/**
	 * 函 数 名：doList
	 * 功能描述：查询企业登记列表信息
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午10:01:38
	 */
	public String doList() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * 函 数 名：doAdd
	 * 功能描述：添加企业登记信息页面
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午10:06:31
	 */
	public String doAdd() throws Exception{
		return "add";
	}
	
	/**
	 * 函 数 名：doEdit
	 * 功能描述：修改企业登记信息页面
	 * * 返 回 值：
	 * @return jsonPO json格式的pojo信息
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午10:07:10
	 */
	public String doEdit(){
		try{
			c001PO=c001Service.retrieveC001ByMA001(c001PO.getMA001());
			if (c001PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			setJsonPO(c001PO);
		}catch(Exception e){
			e.printStackTrace();
		}
		return JSON_PO;
	}
	
	/**
	 * 函 数 名：doSaveAdd
	 * 功能描述：新增企业登记信息
	 * 输入参数：
	 * @param c001PO 表单pojo信息
	 * 返 回 值：
	 * @return UserMessage 操作描述信息
	 * 异    常： Exception
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午10:07:48
	 */
	public String doSaveAdd(){
		try{
			UserSession userSession=(UserSession)this.getSession().getAttribute(Constants.USER_SESSION_KEY);
			c001PO.setMA011(userSession.getUserPO().getId());
			message=c001Service.insertC001(c001PO);
		}catch(Exception e){
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 函 数 名：doSaveEdit
	 * 功能描述：更新企业登记信息
	 * 输入参数：
	 * @param c001PO 表单pojo信息
	 * 返 回 值：
	 * @return UserMessage 操作描述信息
	 * 异    常： Exception
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午10:16:32
	 */
	public String doSaveEdit(){
		try{
			UserSession userSession=(UserSession)this.getSession().getAttribute(Constants.USER_SESSION_KEY);
			c001PO.setMA013(userSession.getUserPO().getId());
			message=c001Service.updateC001(c001PO);
		}catch(Exception e){
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 函 数 名：checkOnlyMa002
	 * 功能描述：校验企业名称的唯一性
	 * 创建人：马宁
	 * 创建时间：2013-08-19 13:52
	 */
	public void checkOnlyMa002(){
		try{
			response.setContentType("text/html;charset=UTF-8");
			String ma002 = request.getParameter("ma002");
			if(StringUtils.isNotBlank(ma002)){
				String ma001=StringUtils.isNotBlank(request.getParameter("ma001"))?request.getParameter("ma001"):null;
				C001PO c001po = new C001PO();
				c001po.setMA001(ma001);
				c001po.setMA002(ma002);
				if(c001Service.isUniqueMa002(c001po)){
					response.getWriter().print(true);
					return;
				}
				response.getWriter().print(false);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 函 数 名：checkOnlyMa003
	 * 功能描述：校验组织机构代码的唯一性
	 * 输入参数：
	 * @param String ma003 组织机构代码
	 * 创建人：付强
	 * 创建时间：2012-12-12 下午3:13:57
	 */
	public void checkOnlyMa003(){
		try {
			response.setContentType("text/html;charset=UTF-8");
			if (StringUtils.isNotBlank(request.getParameter("ma003"))) {
				String ma003 = request.getParameter("ma003");
				String ma001=StringUtils.isNotBlank(request.getParameter("ma001"))?request.getParameter("ma001"):null;
				C001PO c001Po=new C001PO();
				c001Po.setMA001(ma001);
				c001Po.setMA003(ma003);
				if (c001Service.isUniqueMa003(c001Po)) {
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
	 * 函 数 名：checkOnlyMa007
	 * 功能描述：校验邮箱的唯一性
	 * 输入参数：
	 * @param String ma007 邮箱
	 * 创建人：付强
	 * 创建时间：2012-12-12 下午3:44:57
	 */
	public void checkOnlyMa007(){
		try {
			response.setContentType("text/html;charset=UTF-8");
			if (StringUtils.isNotBlank(request.getParameter("ma007"))) {
				String ma007 = request.getParameter("ma007");
				String ma001=StringUtils.isNotBlank(request.getParameter("ma001"))?request.getParameter("ma001"):null;
				C001PO c001Po=new C001PO();
				c001Po.setMA001(ma001);
				c001Po.setMA007(ma007);
				if (c001Service.isUniqueMa007(c001Po)) {
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
	 * 函 数 名：doDelC002ByMa001s
	 * 功能描述：依据获取到的MA001集合删除对应记录
	 * 输入参数：ids 主键集合
	 * @param ma001s 主键集合
	 * 创建人：付强
	 * 创建时间：2012-12-17 上午11:16:07
	 */
	public String doDeleteC002ByMa001s(){
		try {
			if(StringUtils.isNotBlank(request.getParameter("ids"))){
				String ids=request.getParameter("ids");
				message=c001Service.deleteC001ByIds(ids);
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
	 * 函 数 名：queryAllInstance
	 * 功能描述：查询所有实例,并转化成json对象
	 * 创建人：马宁
	 * 创建时间：2012-10-25 15:11
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String queryAllInstanceToJson(){
		try {
			List<C001PO> pos = c001Service.retrieveAll();
			assignC001Json(pos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "c001Json";
	}
	
	/**
	 * 查询所有有效的实例,并转化成json对象
	 * 
	 * @author 马宁
	 * 创建时间: 2013-03-07 11:24
	 */
	public String queryAllValidInstanceToJson(){
		try {
			List<Map<String,Object>> mapList = c001Service.retrieveOrgan();
			List<C002PO> pos = c002Service.retrieveAllValid();
			treeNodes = new ArrayList<TreeNodePO>();
			for(C002PO c002PO : pos){
				TreeNodePO treeNode = new TreeNodePO();
				treeNode.setId(c002PO.getMa001());
				treeNode.setpId(c002PO.getMa004());
				treeNode.setName(c002PO.getMa003());
				treeNode.setChkDisabled(true);
				treeNodes.add(treeNode);
			}
			for(Map<String,Object> map : mapList){
				TreeNodePO treeNode = new TreeNodePO();
				treeNode.setId(map.get("CORPID").toString());
				treeNode.setpId(map.get("GOVID").toString());
				treeNode.setName(map.get("CORPNAME").toString());
				treeNodes.add(treeNode);
			}
			assignTreeNodeJson(treeNodes);
			//assignC001Json(pos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "c001Json";
	}

	/**
	 * 
	 * 函 数 名：assignC001Json
	 * 功能描述：对c001Json变量赋值
	 * 创建人：马宁
	 * 创建时间：2012-10-25 15:41
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	private void assignC001Json(List<C001PO> pos){
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(pos);
		c001Json = jsonArray.toString();
	}
	
	private void assignTreeNodeJson(List<TreeNodePO> pos){
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(pos);
		c001Json = jsonArray.toString();
		System.out.println(c001Json);
	}
	
	public C001PO getC001PO() {
		return c001PO;
	}

	public void setC001PO(C001PO c001po) {
		c001PO = c001po;
	}
	public String getC001Json() {
		return c001Json;
	}

	public void setC001Json(String c001Json) {
		this.c001Json = c001Json;
	}

	public IC001Service getC001Service() {
		return c001Service;
	}

	public void setC001Service(IC001Service c001Service) {
		this.c001Service = c001Service;
	}

	public List<TreeNodePO> getTreeNodes() {
		return treeNodes;
	}

	public void setTreeNodes(List<TreeNodePO> treeNodes) {
		this.treeNodes = treeNodes;
	}

	public IC002Service getC002Service() {
		return c002Service;
	}

	public void setC002Service(IC002Service c002Service) {
		this.c002Service = c002Service;
	}
	
}
