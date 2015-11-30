/**
 * 文件名：C007ServiceImpl.java
 *
 * 版本信息：
 * 日期：2012-8-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.dao.IC007Dao;
import com.hw.hwsafe.corpinfo.pojo.C007PO;
import com.hw.hwsafe.corpinfo.service.IC007Service;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：framework
 * 类名称：C007ServiceImpl
 * 类描述：系统字典Service实现类
 * 创建人：付强
 * 创建时间：2012-8-8 上午11:32:38
 * 
 */
public class C007ServiceImpl extends BaseServiceImpl implements
		IC007Service {
	
	@Autowired
	public IC007Dao c007Dao;

	@Override
	public C007PO retrieveC007ById(String id) throws Exception {
		C007PO c007PO=c007Dao.retrieveC007ById(id);
		if(null==c007PO)
			return null;
		
		c007PO.setFullKey(c007PO.getKey());
		if(c007PO.getId().equals(c007PO.getParentid())){
			c007PO.setParentKey("");
		}else{
			C007PO parentC007PO=c007Dao.retrieveC007ById(c007PO.getParentid());
			String parentKey=parentC007PO.getKey();
			String key=c007PO.getKey();
			if(key.startsWith(parentKey)){
				key=key.substring(parentKey.length()+1);
				c007PO.setParentKey(parentKey);
				c007PO.setKey(key);
			}
		}
		return c007PO;
	}

	@Override
	public UserMessageData insertC007(C007PO c007PO) throws Exception {
		UserMessageData message=new UserMessageData();
		c007PO.setId(UUIDGenerater.getUUID());
		c007PO.setDelFlag(C007PO.DEL_FLAG_VALID);
		C007PO args=new C007PO();
		args.setParentid(c007PO.getParentid());
		args.setOrdernum(c007PO.getOrdernum());
		if(c007Dao.countC007ByOrdernum(args)>0){
			c007Dao.updateOrdernumIncrease(args);
		}
		if(null==c007PO.getParentid() || "".equals(c007PO.getParentid())){
			c007PO.setParentid(c007PO.getId());
		}else{
			C007PO parentC007PO=c007Dao.retrieveC007ById(c007PO.getParentid());
			c007PO.setKey(parentC007PO.getKey()+C007PO.SEPARATOR+c007PO.getKey());
		}
		c007Dao.insertC007(c007PO);
//		message.set(Constants.MSG_OK, Constants.ADD_SUCCESS_CONTINUE_MSG);
		message.set(Constants.MSG_OK, Constants.ADD_SUCCESS_MSG);
		return message;
	}

	@Override
	public UserMessageData updateC007(C007PO c007PO) throws Exception {
		//记录找不到
		UserMessageData message=new UserMessageData();
		C007PO oldC007=this.retrieveC007ById(c007PO.getId());
		if(null==oldC007){
			message.set(Constants.MSG_WARNING, Constants.EMPTY_DATA_MSG);
			return message;
		}
		
		C007PO args=new C007PO();
		if(oldC007.getId().equals(oldC007.getParentid())){
			args.setParentid(null);
		}else{
			args.setParentid(oldC007.getParentid());
			C007PO parentC007PO=c007Dao.retrieveC007ById(c007PO.getParentid());
			c007PO.setKey(parentC007PO.getKey()+C007PO.SEPARATOR+c007PO.getKey());
		}
		args.setOrdernum(c007PO.getOrdernum());
		args.setId(c007PO.getId());
		//是否需要更新排序号
		if(c007Dao.countC007ByOrdernum(args)>0){
			c007Dao.updateOrdernumIncrease(args);
		}
		c007PO.setParentid(oldC007.getParentid());
		c007Dao.updateC007(c007PO);
		
		message.set(Constants.MSG_OK, Constants.UPDATE_SUCCESS_MSG);
		return message;
	}

	@Override
	public UserMessageData deleteC007ByIds(String ids) throws Exception {
		UserMessageData message=new UserMessageData();
		Map<String,Object> args=new HashMap<String,Object>();
		String[] idArray=ids.split(",");
		args.put("ids", idArray);
		//逻辑删除子节点项
		if(idArray!=null && idArray.length>0){
			for(int i=0;i<idArray.length;i++){
				C007PO c007PO=c007Dao.retrieveC007ById(idArray[i]);
				c007Dao.deleteChildC007ByKey(c007PO.getKey());
			}
		}
		//删除选中节点项
		Integer recNum=c007Dao.deleteC007ByIds(args);
		if(recNum.intValue()==idArray.length){
			message.set(Constants.MSG_OK, Constants.DEL_SUCCESS_MSG);
		}else if(recNum.intValue()<idArray.length){
//			message.set(Constants.MSG_WARNING, Constants.DEL_SUCCESS_MSG_PRE+recNum+Constants.DEL_SUCCESS_MSG_SUF);
			message.set(Constants.MSG_OK, Constants.DEL_SUCCESS_MSG);
		}
		return message;
	}

	@Override
	public Boolean isUniqueKey(C007PO c007PO) throws Exception {
		if(c007Dao.countC007ByKey(c007PO)>0){
			return false;
		}
		return true;
	}
	
	public Boolean isUniqueValueUnderParentNode(C007PO c007PO)throws Exception{
		if(c007Dao.countC007ByValueUnderParentNode(c007PO)>0){
			return false;
		}
		return true;
	}

	@Override
	public List<C007PO> retrieveC007ByPO(C007PO dictPo)
			throws Exception {
		return c007Dao.retrieveC007ByPO(dictPo);
	}
	
	@Override
	public List<C007PO> retrieveChildC007ListByKey(String key)
			throws Exception {
		List<C007PO> dictList=c007Dao.retrieveChildC007ListByKey(key);
		return dictList;
	}

	@Override
	public List<C007PO> retrieveDirectChildC007ListByKey(String key)
			throws Exception {
		List<C007PO> dictList=c007Dao.retrieveChildC007ListByKey(key);
		C007PO c007PO=c007Dao.retrieveC007ByKey(key);
		for(C007PO dictTmpPO:dictList){
			if(!c007PO.getId().equals(dictTmpPO.getParentid())){
				dictList.remove(dictTmpPO);
			}
		}
		return dictList;
	}

	@Override
	public List<C007PO> retrieveTermC007ListByKey(String key)
			throws Exception {
		List<C007PO> dictList=c007Dao.retrieveChildC007ListByKey(key);
		for(int i=0;i<dictList.size();i++){
			C007PO dict=dictList.get(i);
			boolean termFlag=true;
			for(int j=0;j<dictList.size();j++){
				C007PO dictTmp=dictList.get(j);
				if(i!=j && dict.getId().equals(dictTmp.getParentid())){
					dict.setTermNode(false);
					termFlag=false;
					break;
				}
			}
			if(!termFlag){
				break;
			}
		}
		for(int k=0;k<dictList.size();k++){
			C007PO dict=dictList.get(k);
			if(dict.getTermNode()!=null && !dict.getTermNode()){
				dictList.remove(k);
			}
		}
		return dictList;
	}

	@Override
	public C007PO retrieveC007ByKey(String key) throws Exception {
		C007PO c007PO=c007Dao.retrieveC007ByKey(key);
		c007PO.setFullKey(c007PO.getKey());
		if(!c007PO.getId().equals(c007PO.getParentid())){
			C007PO parentC007PO=c007Dao.retrieveC007ById(c007PO.getParentid());
			
			String parentKey=parentC007PO.getKey();
			String dictKey=c007PO.getKey();
			if(dictKey.startsWith(parentKey)){
//				dictKey=dictKey.substring(parentKey.length()+1);
				c007PO.setParentKey(parentC007PO.getKey());
				c007PO.setKey(key);
			}
		}
		return c007PO;
	}

	@Override
	public List getHymList() throws Exception {
		return c007Dao.getHymlList();
	}

	@Override
	public List getHydlList(String parentid) throws Exception {
		return c007Dao.getHydlList(parentid);
	}

	@Override
	public List getHyzlList(String parentid) throws Exception {
		return c007Dao.getHyzlList(parentid);
	}

	@Override
	public List getHyxlList(String parentid) throws Exception {
		return c007Dao.getHyxlList(parentid);
	}

	@Override
	public C007PO getC007POByValue(String value) throws Exception {
		C007PO	c007PO =c007Dao.getC007POByValue(value);
		return c007PO==null?new C007PO():c007PO;
	}
	
	/*
	   (non-Javadoc)
	 * @see com.hw.hwsafe.corpinfo.service.IC007Service#getHymlPOList()
	 */
		
	@Override
	public List<C007PO> getHymlPOList() {
		
		return c007Dao.getHymlPOList();
	}

	@Override
	public List<C007PO> retrieveChildC007ListByParent(String id) throws Exception {
		return c007Dao.retrieveChildC007ListByParent(id);
	}
}
