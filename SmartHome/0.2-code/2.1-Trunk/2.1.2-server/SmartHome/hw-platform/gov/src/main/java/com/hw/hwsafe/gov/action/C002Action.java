/**
 * 文件名：C002Action.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.gov.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.gov.pojo.C002PO;
import com.hw.hwsafe.gov.service.IC002Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.exception.jsonmsg.JsonMsgException;
import com.hw.hwsafe.platform.pojo.TreeNodePO;
import com.hw.hwsafe.utils.DateTimeUtils;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：C002Action
 * 类描述：政府机构Action
 * 创建人：马宁
 * 创建时间：2012-9-29 上午11:29:53
 * 修改人：马宁
 * 修改时间：2012-9-29 上午11:29:53
 * 修改备注：
 * @version 
 *
 */
public class C002Action extends BaseAction {

	@Autowired
	private IC002Service c002Service;

	private C002PO c002PO;

	private String c002Tree;
	
	private String c002Json;
	
	private List<TreeNodePO> treeNodes;

	/**
	 * 
	 * 函 数 名：doMain
	 * 功能描述：doMain方法
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午2:53:11
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doMain() throws Exception {
		logger.debug("doMain()"	+ this.getClass().getName());
		return "main";
	}

	/**
	 * 
	 * 函 数 名：doLoadTree
	 * 功能描述：操作c002Tree
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午2:53:39
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doLoadTree() throws Exception {
		logger.debug("doC002Tree()"	+ this.getClass().getName());
		List<C002PO> c002PoList = c002Service.retrieveC002ByPO(null);
		String tmp = recBuildNodeStr(c002PoList, "0");
		tmp = (tmp.length() > 1 ? tmp.substring(0, tmp.length() - 1) : tmp);
		c002Tree = "[" + tmp + "]";
		return "tree";
	}

	/**
	 * 
	 * 函 数 名：doList
	 * 功能描述：操作表单list
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午2:54:16
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doList() throws Exception {
		logger.debug("doList()"	+ this.getClass().getName());
		return "list";
	}

	/**
	 * 
	 * 函 数 名：doAdd
	 * 功能描述：添加动作
	 * 创建人：马宁
	 * @exception Exception
	 * 创建时间：2012-9-29 下午2:54:56
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doAdd() throws Exception {
		logger.debug("add()" + this.getClass().getName());
		return "add";
	}

	/**
	 * 
	 * 函 数 名：doEdit
	 * 功能描述：修改动作
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午2:55:39
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doEdit() {
		logger.debug("doEdit()"	+ this.getClass().getName());
		try {
			c002PO = c002Service.retrieveC002ByMa001(c002PO.getMa001());
			if (c002PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			setJsonPO(c002PO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}

	/**
	 * 
	 * 函 数 名：doQueryC002ByMa002
	 * 功能描述：通过ma002属性查询C002实体
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午2:56:00
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doQueryC002ByMa002() {
		logger.debug("doQueryC002ByMa002()"	+ this.getClass().getName());
		try {
			c002PO = c002Service.retrieveC002ByMa002(c002PO.getMa002());
			if (c002PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			setJsonPO(c002PO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}
	
	/**
	 * 
	 * 函 数 名：checkOnlyName
	 * 功能描述：判断机构名称是否唯一
	 * 创建人：马宁
	 * 创建时间：2013-08-19 下午1:16:41
	 */
	public void checkOnlyName(){
		try{
			response.setContentType("text/html;charset=UTF-8");
			String name = request.getParameter("c002Name");
			if(StringUtils.isNotBlank(name)){
				String ma001=StringUtils.isNotBlank(request.getParameter("ma001"))?request.getParameter("ma001"):null;
				c002PO.setMa001(ma001);
				c002PO.setMa003(name);
				if(c002Service.isUniqueName(c002PO)){
					response.getWriter().print(true);
					return;
				}
			}
			response.getWriter().print(false);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 函 数 名：checkOnlyCode
	 * 功能描述：判断机构编码是否唯一
	 * 创建人：马宁
	 * 创建时间：2012-12-8 下午3:30:41
	 */
	public void checkOnlyCode(){
		try {
			response.setContentType("text/html;charset=UTF-8");
			if (StringUtils.isNotBlank(request.getParameter("ma002"))){
				String ma002=request.getParameter("ma002");
				String ma001=StringUtils.isNotBlank(request.getParameter("ma001"))?request.getParameter("ma001"):null;
				c002PO.setMa001(ma001);
				c002PO.setMa002(ma002);
				if(c002Service.isUniqueCode(c002PO)){
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
	 * 功能描述：
	 * @param String ma007 邮箱地址
	 * @param String ma001 政府机构主键
	 * @return 邮箱地址是否已被使用
	 * 创建人：付强
	 * 创建时间：2012-12-19 下午12:44:44
	 */
	public void checkOnlyMa007(){
		try {
			response.setContentType("text/html;charset=UTF-8");
			if (StringUtils.isNotBlank(request.getParameter("ma007"))){
				String ma007=request.getParameter("ma007");
				String ma001=
						StringUtils.isNotBlank(request.getParameter("ma001"))
							?request.getParameter("ma001")
							:null;
				c002PO.setMa001(ma001);
				c002PO.setMa007(ma007);
				if(c002Service.isUniqueMa007(c002PO)){
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
	 * 函 数 名：doSaveAdd
	 * 功能描述：保存添加的数据
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午2:56:33
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doSaveAdd() {
		logger.debug("doSaveAdd()" + this.getClass().getName());
		try {
			setCreaterAndCreationDate();
			handleOnlineCount();
			message=c002Service.insertC002(c002PO);
		} catch (JsonMsgException e) {
			handleJsonMsgException(e);
//			e.printStackTrace();
//			setAddFailedMsg();
		}
		return JSON_MSG;
	}

	/**
	 * 
	 * 函 数 名：doSaveEdit
	 * 功能描述：保存修改的数据
	 * @exception Exception
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午2:56:59
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doSaveEdit() {
		logger.info("doSaveEdit()" + this.getClass().getName());
		try {
			setModifierAndModifiedDate();
			handleOnlineCount();
			message=c002Service.updateC002(c002PO);
		} catch (Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 
	 * 函 数 名：queryAllInstance
	 * 功能描述：查询所有实例,并转化成json对象
	 * 创建人：马宁
	 * 创建时间：2012-10-24 下午3:40:29
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String queryAllInstanceToJson(){
		try {
			List<C002PO> pos = c002Service.retrieveAll();
			//assignC002Json(pos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "c002Json";
	}
	
	/**
	 * 查询所有有效的实例,并转化成json对象
	 * 
	 * @author 马宁
	 * 创建时间: 2013-03-07 11:13
	 */
	public String queryAllValidInstanceToJson(){
		try {
			List<C002PO> pos = c002Service.retrieveAllValid();
			treeNodes = new ArrayList<TreeNodePO>();
			for(C002PO c002PO : pos){
				TreeNodePO treeNode = new TreeNodePO();
				treeNode.setId(c002PO.getMa001());
				treeNode.setpId(c002PO.getMa004());
				treeNode.setName(c002PO.getMa003());
				treeNodes.add(treeNode);
			}
			assignTreeNodeJson(treeNodes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "c002Json";
	}
	
	/**
	 * 函 数 名：doDeleteC002ByMa001s
	 * 功能描述：依据获取到的MA001集合删除对应记录
	 * @param ids 主键集合
	 * 创建人：付强
	 * 创建时间：2012-12-18 上午10:51:27
	 */
	public String doDeleteC002ByMa001s(){
		try {
			if(StringUtils.isNotBlank(request.getParameter("ids"))){
				String ids=request.getParameter("ids");
				message=c002Service.deleteC002ByIds(ids);
			}else{
				setDelFailedMsg();
			}
		} catch (Exception e) {
			setDelFailedMsg();
		}
		return JSON_MSG;
	}

	// ------------- private methods ------------

	/**
	 * 
	 * 函 数 名：recBuildNodeStr
	 * 功能描述：重新加载功能树形菜单
	 * 创建人：马宁
	 * 创建时间：2012-9-29 上午10:42:04
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	private String recBuildNodeStr(List<C002PO> pos, String parentNodeID) {
		StringBuffer sb = new StringBuffer("");
		for (C002PO po : pos) {
			String ma001 = po.getMa001();
			String ma004 = po.getMa004();
			if (parentNodeID.equals("0") ? ma001.equals(ma004) : (ma004
					.equals(parentNodeID) && !ma001.equals(ma004))) {
//				sb.append("{id:\"" + ma001 + "\", pId:\"" + parentNodeID
//						+ "\", name:\"" + po.getMa003()
//						+ "\", iconSkin:\"pIcon\"},");
				sb.append("{id:\"")
						.append(ma001)
						.append("\", pId:\"")
						.append(parentNodeID)
						.append("\", name:\"")
						.append(po.getMa003())
						.append("\", iconSkin:\"pIcon\"},")
						.append(recBuildNodeStr(pos, ma001));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * 函 数 名：setCreaterAndCreationDate
	 * 功能描述：设置创建者和创建时间
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:31:41
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	private void setCreaterAndCreationDate(){
		c002PO.setMa010(getSessionUserPO().getUUID());
		c002PO.setMa011(DateTimeUtils.getCurrentDate());
	}
	
	/**
	 * 
	 * 函 数 名：setModifierAndModifiedDate
	 * 功能描述：设置修改者和修改时间 
	 * 创建人：马宁
	 * 创建时间：2012-9-29 下午1:31:59
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	private void setModifierAndModifiedDate(){
		c002PO.setMa012(getSessionUserPO().getUUID());
		c002PO.setMa013(DateTimeUtils.getCurrentDate());
	}

	/**
	 * 处理在线人数
	 * @author 马宁
	 */
	private void handleOnlineCount() {
		Integer onlineCount = c002PO.getMa014();
		if(onlineCount == null){
			c002PO.setMa014(0);
		}
	}
	
	/**
	 * 
	 * 函 数 名：assignC002Json
	 * 功能描述：对c002Json变量赋值
	 * 创建人：马宁
	 * 创建时间：2012-10-24 下午3:41:31
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	private void assignC002Json(List<C002PO> pos){
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(pos);
		c002Json = jsonArray.toString();
	}
	
	
	private void assignTreeNodeJson(List<TreeNodePO> pos){
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(pos);
		c002Json = jsonArray.toString();
		System.out.println(c002Json);
	}
	
	// ------------- getters and setters -----------

	public C002PO getC002PO() {
		return c002PO;
	}

	public void setC002PO(C002PO c002PO) {
		this.c002PO = c002PO;
	}

	public String getC002Tree() {
		return c002Tree;
	}

	public void setC002Tree(String c002Tree) {
		this.c002Tree = c002Tree;
	}

	public String getC002Json() {
		return c002Json;
	}

	public void setC002Json(String c002Json) {
		this.c002Json = c002Json;
	}

	public IC002Service getC002Service() {
		return c002Service;
	}

	public void setC002Service(IC002Service c002Service) {
		this.c002Service = c002Service;
	}

	public List<TreeNodePO> getTreeNodes() {
		return treeNodes;
	}

	public void setTreeNodes(List<TreeNodePO> treeNodes) {
		this.treeNodes = treeNodes;
	}
	
}
