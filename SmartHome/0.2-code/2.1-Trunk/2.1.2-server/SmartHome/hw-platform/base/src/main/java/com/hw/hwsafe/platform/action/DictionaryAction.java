/**
 * 文件名：DictionaryAction.java
 *
 * 版本信息：
 * 日期：2012-8-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.action;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.pojo.DictionaryPO;
import com.hw.hwsafe.platform.service.IDictionaryService;

/**
 * 
 * 项目名称：framework
 * 类名称：DictionaryAction
 * 类描述：系统字典管理
 * 创建人：付强
 * 创建时间：2012-8-8 下午1:32:31
 * 
 */
public class DictionaryAction extends BaseAction {
	
	@Autowired
	private IDictionaryService dictService;
	
	private DictionaryPO dictPO;
	
	private String dictTree;
	
	public String doMain() throws Exception{
		return "main";
	}
	
	public String doLoadTree()throws Exception{
		List<DictionaryPO> dictPoList=dictService.retrieveDictByPO(null);
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
			dictPO=dictService.retrieveDictById(dictPO.getId());
			if (dictPO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			setJsonPO(dictPO);
		}catch(Exception e){
			e.printStackTrace();
		}
		return JSON_PO;
	}
	
	/**
	 * 函 数 名：doSaveAdd
	 * 功能描述：新增一条字典数据
	 * @param dictPO 字典pojo
	 * @return message 操作结果描述
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午1:55:12
	 */
	public String doSaveAdd(){
		try{
			message=dictService.insertDict(dictPO);
		}catch(Exception e){
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 函 数 名：doSaveEdit
	 * 功能描述：更新一条字典数据
	 * @param dictPO 字典pojo
	 * @return message 操作结果描述
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午1:56:55
	 */
	public String doSaveEdit(){
		try{
			message=dictService.updateDict(dictPO);
		}catch(Exception e){
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 函 数 名：delDictByIds
	 * 功能描述：逻辑批量删除ids记录
	 * @param ids 字典项主键集合
	 * @return 
	 * 创建人：付强
	 * 创建时间：2013-1-5 上午10:35:42
	 */
	public String delDictByIds(){
		try {
			if(StringUtils.isNotBlank(request.getParameter("ids"))){
				String ids=request.getParameter("ids");
				message=dictService.deleteDictionaryByIds(ids);
			}else{
				setDelFailedMsg();
			}
		} catch (Exception e) {
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 函 数 名：doQueryDictByKey
	 * 功能描述：key查询一条字典数据
	 * @param key 键编码
	 * @return 
	 * 创建人：付强
	 * 创建时间：2012-8-13 下午12:33:05
	 */
	public String doQueryDictByKey(){
		try{
			dictPO=dictService.retrieveDictByKey(dictPO.getKey());
			if (dictPO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			setJsonPO(dictPO);
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
				DictionaryPO dictPO=new DictionaryPO();
				if(parentKey!=null){
					dictPO.setKey(parentKey+DictionaryPO.SEPARATOR+key);
				}else{
					dictPO.setKey(key);
				}
				dictPO.setId(uuid);
				if(dictService.isUniqueKey(dictPO)){
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
				DictionaryPO dictPO=new DictionaryPO();
				dictPO.setValue(value);
				dictPO.setId(uuid);
				dictPO.setParentid(parentId);
				if(dictService.isUniqueValueUnderParentNode(dictPO)){
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
	 * @param List<DictionaryPO> 功能数据表
	 * @param String 根节点ID
	 * @return： json格式转化后的功能菜单树信息
	 */
	private String recBuildNodeStr(List<DictionaryPO> list, String parentNodeID) {
		StringBuffer str = new StringBuffer();
		for (DictionaryPO po : list) {
			String id = po.getId();
			String parentId = po.getParentid();
			if (parentNodeID.equals("0") ? id.equals(parentId) : (parentId
					.equals(parentNodeID) && !id.equals(parentId))) {
				str.append("{id:\"")
						.append(id)
						.append("\", pId:\"")
						.append(parentNodeID)
						.append("\", name:\"")
						.append(po.getDisplay())
						.append("\", iconSkin:\"pIcon\"},");
				str.append(recBuildNodeStr(list, id));
			}
		}
		return str.toString();
	}
	
	/**
	 * 函 数 名：getDictFmtJsonByKey
	 * 功能描述：依据字典key获取所有子节点json格式数据
	 * @param dictKey 字典key
	 * @return String json格式字典数据
	 * 创建人：付强
	 * 创建时间：2012-8-29 下午2:58:34
	 */
	public void getDictFmtJsonByKey(){
		try {
			if(request.getParameter("dictKey")!=null || !"".equals(request.getParameter("dictKey"))){
				String dictKey=request.getParameter("dictKey");
				List<DictionaryPO> dictList=dictService.retrieveChildDictListByKey(dictKey);
				StringBuffer sb=new StringBuffer();
				String dictJson="[";
				if(null!=dictList && dictList.size()>0){
					for(DictionaryPO dict:dictList){
						sb.append("{\"value\":\"")
								.append(dict.getValue())
								.append("\",\"display\":\"")
								.append(dict.getDisplay())
								.append("\"},");
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

	public DictionaryPO getDictPO() {
		return dictPO;
	}

	public void setDictPO(DictionaryPO dictPO) {
		this.dictPO = dictPO;
	}

	public String getDictTree() {
		return dictTree;
	}

	public void setDictTree(String dictTree) {
		this.dictTree = dictTree;
	}

	public IDictionaryService getDictService() {
		return dictService;
	}

	public void setDictService(IDictionaryService dictService) {
		this.dictService = dictService;
	}
}
