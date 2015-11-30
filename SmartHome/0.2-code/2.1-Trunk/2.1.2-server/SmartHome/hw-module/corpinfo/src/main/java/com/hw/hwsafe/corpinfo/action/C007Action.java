/**
 * 文件名：C007ionaryAction.java
 *
 * 版本信息：
 * 日期：2012-8-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.action;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.C007PO;
import com.hw.hwsafe.corpinfo.service.IC007Service;
import com.hw.hwsafe.platform.action.BaseAction;

/**
 * 
 * 项目名称：framework
 * 类名称：C007ionaryAction
 * 类描述：系统字典管理
 * 创建人：付强
 * 创建时间：2012-8-8 下午1:32:31
 * 
 */
public class C007Action extends BaseAction {

	@Autowired
	private IC007Service c007Service;
	
	private C007PO c007PO;
	
	private String dictTree;
	
	public String doMain() throws Exception{
		return "main";
	}
	
	public String doLoadTree()throws Exception{
		List<C007PO> dictPoList=c007Service.retrieveC007ByPO(null);
		String tmp=recBuildNodeStr(dictPoList,"0");
		tmp=(tmp.length() > 1 ? tmp.substring(0, tmp.length() - 1) : tmp);
		dictTree="["+tmp+"]";
		return "tree";
	}
	
	/**
	 * 函 数 名：doList
	 * 功能描述：字典列表信息
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午1:51:25
	 */
	public String doList() throws Exception {
		return "list";
	}
	
	/**
	 * 函 数 名：doAdd
	 * 功能描述：添加字典页面
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午1:52:57
	 */
	public String doAdd() throws Exception{
		return "add";
	}
	
	/**
	 * 函 数 名：doEdit
	 * 功能描述：修改字典页面
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午1:53:24
	 */
	public String doEdit(){
		try{
			c007PO=c007Service.retrieveC007ById(c007PO.getId());
			if (c007PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			setJsonPO(c007PO);
		}catch(Exception e){
			e.printStackTrace();
		}
		return JSON_PO;
	}
	
	/**
	 * 函 数 名：doSaveAdd
	 * 功能描述：新增一条字典数据
	 * @param c007PO 字典pojo
	 * @return message 操作结果描述
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午1:55:12
	 */
	public String doSaveAdd(){
		try{
			message=c007Service.insertC007(c007PO);
		}catch(Exception e){
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 函 数 名：doSaveEdit
	 * 功能描述：更新一条字典数据
	 * @param c007PO 字典pojo
	 * @return message 操作结果描述
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午1:56:55
	 */
	public String doSaveEdit(){
		try{
			message=c007Service.updateC007(c007PO);
		}catch(Exception e){
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 函 数 名：delC007ByIds
	 * 功能描述：逻辑批量删除ids记录
	 * @param ids 字典项主键集合
	 * @return 
	 * 创建人：付强
	 * 创建时间：2013-1-5 上午10:35:42
	 */
	public String delC007ByIds(){
		try {
			if(StringUtils.isNotBlank(request.getParameter("ids"))){
				String ids=request.getParameter("ids");
				message=c007Service.deleteC007ByIds(ids);
			}else{
				setDelFailedMsg();
			}
		} catch (Exception e) {
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 函 数 名：doQueryC007ByKey
	 * 功能描述：key查询一条字典数据
	 * @param key 键编码
	 * @return 
	 * 创建人：付强
	 * 创建时间：2012-8-13 下午12:33:05
	 */
	public String doQueryC007ByKey(){
		try{
			c007PO=c007Service.retrieveC007ByKey(c007PO.getKey());
			if (c007PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			setJsonPO(c007PO);
		}catch(Exception e){
			e.printStackTrace();
		}
		return JSON_PO;
	}
	
	/**
	 * 函 数 名：checkOnlyKey
	 * 功能描述：检查key的唯一性
	 * @param key 代码
	 * @param parentKey 父节点编码
	 * @param uuid 该节点主键
	 * @return json格式true,false
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午1:59:22
	 */
	public void checkOnlyKey(){
		try {
			if(StringUtils.isNotBlank(request.getParameter("dictKey"))){
				String key=request.getParameter("dictKey");
				String uuid=StringUtils.isNotEmpty(request.getParameter("uuid"))?request.getParameter("uuid"):null;
				String parentKey=StringUtils.isNotEmpty(request.getParameter("parentKey"))?request.getParameter("parentKey"):null;
				C007PO c007PO=new C007PO();
				if(parentKey!=null){
					c007PO.setKey(parentKey+C007PO.SEPARATOR+key);
				}else{
					c007PO.setKey(key);
				}
				c007PO.setId(uuid);
				if(c007Service.isUniqueKey(c007PO)){
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
	 * 函 数 名：checkOnlyValueUnderParentNode
	 * 功能描述：检查相同父节点下Value的唯一性
	 * @param dictValue 值
	 * @param uuid 主键
	 * @param parentId 父节点主键
	 * @return 是否唯一
	 * 创建人：付强
	 * 创建时间：2012-12-27 下午3:08:01
	 */
	public void checkOnlyValueUnderParentNode(){
		try {
			if(StringUtils.isNotBlank(request.getParameter("dictValue"))){
				String value=request.getParameter("dictValue");
				String uuid=StringUtils.isNotBlank(request.getParameter("uuid"))?request.getParameter("uuid"):null;
				String parentId=StringUtils.isNotBlank(request.getParameter("parentId"))?request.getParameter("parentId"):null;
				C007PO c007PO=new C007PO();
				c007PO.setValue(value);
				c007PO.setId(uuid);
				c007PO.setParentid(parentId);
				if(c007Service.isUniqueValueUnderParentNode(c007PO)){
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
	 * 函 数 名：recBuildNodeStr
	 * 功能描述：加载功能树形菜单
	 * @param List<C007PO> 功能数据表
	 * @param String 根节点ID
	 * @return： json格式转化后的功能菜单树信息
	 */
	private String recBuildNodeStr(List<C007PO> list, String parentNodeID) {
		StringBuffer str = new StringBuffer();
		for (C007PO po : list) {
			String id = po.getId();
			String parentId = po.getParentid();
			if (parentNodeID.equals("0") ? id.equals(parentId) : (parentId
					.equals(parentNodeID) && !id.equals(parentId))) {
				str.append("{id:\"").append(id).append("\", pId:\"").append(parentNodeID).append("\", name:\"").append(po.getDisplay())
				.append("\", levelnum:\"").append(po.getLevelnum())
				.append("\", iconSkin:\"pIcon\"},");
				str.append(recBuildNodeStr(list, id));
			}
		}
		return str.toString();
	}
	
	/**
	 * 函 数 名：getC007FmtJsonByKey
	 * 功能描述：依据字典key获取所有子节点json格式数据
	 * @param dictKey 字典key
	 * @return String json格式字典数据
	 * 创建人：付强
	 * 创建时间：2012-8-29 下午2:58:34
	 */
	public void getC007FmtJsonByKey(){
		try {
			if(request.getParameter("dictKey")!=null || !"".equals(request.getParameter("dictKey"))){
				String dictKey=request.getParameter("dictKey");
				List<C007PO> dictList=c007Service.retrieveChildC007ListByKey(dictKey);
				StringBuffer sb=new StringBuffer();
				String dictJson="[";
				if(null!=dictList && dictList.size()>0){
					for(C007PO dict:dictList){
						sb.append("{\"value\":\"").append(dict.getValue()).append("\",\"display\":\"").append(dict.getDisplay()).append("\"},");
					}
					String tmp=sb.toString();
					if(tmp.length()>0){
						tmp=tmp.substring(0,tmp.length()-1);
					}
					dictJson=dictJson+tmp+"]";
					this.response.setContentType("text/html;charset=utf-8");
					this.response.getWriter().print(dictJson);
				}
				return;
			}
			this.response.getWriter().print("[]");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public C007PO getC007PO() {
		return c007PO;
	}

	public void setC007PO(C007PO c007PO) {
		this.c007PO = c007PO;
	}

	public String getDictTree() {
		return dictTree;
	}

	public void setDictTree(String dictTree) {
		this.dictTree = dictTree;
	}

	public IC007Service getC007Service() {
		return c007Service;
	}

	public void setC007Service(IC007Service c007Service) {
		this.c007Service = c007Service;
	}

}
