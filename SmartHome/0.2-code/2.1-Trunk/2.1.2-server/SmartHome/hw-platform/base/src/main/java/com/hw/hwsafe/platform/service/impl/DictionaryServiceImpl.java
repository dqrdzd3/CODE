/**
 * 文件名：DictionaryServiceImpl.java
 *
 * 版本信息：
 * 日期：2012-8-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.dao.IDictionaryDao;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.pojo.DictionaryPO;
import com.hw.hwsafe.platform.service.IDictionaryService;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：framework
 * 类名称：DictionaryServiceImpl
 * 类描述：系统字典Service实现类
 * 创建人：付强
 * 创建时间：2012-8-8 上午11:32:38
 * 
 */
public class DictionaryServiceImpl extends BaseServiceImpl implements
		IDictionaryService {
	
	@Autowired
	public IDictionaryDao dictDao;

	@Override
	public DictionaryPO retrieveDictById(String id) throws Exception {
		DictionaryPO dictPO=dictDao.retrieveDictionaryById(id);
		if(null==dictPO)
			return null;
		
		dictPO.setFullKey(dictPO.getKey());
		if(dictPO.getId().equals(dictPO.getParentid())){
			dictPO.setParentKey("");
		}else{
			DictionaryPO parentDictPO=dictDao.retrieveDictionaryById(dictPO.getParentid());
			String parentKey=parentDictPO.getKey();
			String key=dictPO.getKey();
			if(key.startsWith(parentKey)){
				key=key.substring(parentKey.length()+1);
				dictPO.setParentKey(parentKey);
				dictPO.setKey(key);
			}
		}
		return dictPO;
	}

	@Override
	public UserMessageData insertDict(DictionaryPO dictPO) throws Exception {
		UserMessageData message=new UserMessageData();
		dictPO.setId(UUIDGenerater.getUUID());
		dictPO.setDelFlag(DictionaryPO.DEL_FLAG_VALID);
		DictionaryPO args=new DictionaryPO();
		args.setParentid(dictPO.getParentid());
		args.setOrdernum(dictPO.getOrdernum());
		if(dictDao.countDictByOrdernum(args)>0){
			dictDao.updateOrdernumIncrease(args);
		}
		if(null==dictPO.getParentid() || "".equals(dictPO.getParentid())){
			dictPO.setParentid(dictPO.getId());
		}else{
			DictionaryPO parentDictPO=dictDao.retrieveDictionaryById(dictPO.getParentid());
			dictPO.setKey(parentDictPO.getKey()+DictionaryPO.SEPARATOR+dictPO.getKey());
		}
		dictDao.insertDictionary(dictPO);
		message.set(Constants.MSG_OK, Constants.ADD_SUCCESS_MSG);
		return message;
	}

	@Override
	public UserMessageData updateDict(DictionaryPO dictPO) throws Exception {
		//记录找不到
		UserMessageData message=new UserMessageData();
		DictionaryPO oldDict=this.retrieveDictById(dictPO.getId());
		if(null==oldDict){
			message.set(Constants.MSG_ERROR, Constants.EMPTY_DATA_MSG);
			return message;
		}
		
		DictionaryPO args=new DictionaryPO();
		if(oldDict.getId().equals(oldDict.getParentid())){
			args.setParentid(null);
		}else{
			args.setParentid(oldDict.getParentid());
			DictionaryPO parentDictPO=dictDao.retrieveDictionaryById(dictPO.getParentid());
			dictPO.setKey(parentDictPO.getKey()+DictionaryPO.SEPARATOR+dictPO.getKey());
		}
		args.setOrdernum(dictPO.getOrdernum());
		args.setId(dictPO.getId());
		//是否需要更新排序号
		if(dictDao.countDictByOrdernum(args)>0){
			dictDao.updateOrdernumIncrease(args);
		}
		dictPO.setParentid(oldDict.getParentid());
		dictDao.updateDictionary(dictPO);
		
		message.set(Constants.MSG_OK, Constants.UPDATE_SUCCESS_MSG);
		return message;
	}

	@Override
	public UserMessageData deleteDictionaryByIds(String ids) throws Exception {
		UserMessageData message=new UserMessageData();
		Map<String,Object> args=new HashMap<String,Object>();
		String[] idArray=ids.split(",");
		args.put("ids", idArray);
		//逻辑删除子节点项
		if(idArray!=null && idArray.length>0){
			for(int i=0;i<idArray.length;i++){
				DictionaryPO dictPO=dictDao.retrieveDictionaryById(idArray[i]);
				dictDao.deleteChildDictByKey(dictPO.getKey());
			}
		}
		//删除选中节点项
		Integer recNum=dictDao.deleteDictionaryByIds(args);
		if(recNum.intValue()==idArray.length){
			message.set(Constants.MSG_OK, Constants.DEL_SUCCESS_MSG);
		}else if(recNum.intValue()<idArray.length){
//			message.set(Constants.MSG_WARNING, Constants.DEL_SUCCESS_MSG_PRE+recNum+Constants.DEL_SUCCESS_MSG_SUF);
			message.set(Constants.MSG_OK, Constants.DEL_SUCCESS_MSG);
		}
		return message;
	}

	@Override
	public Boolean isUniqueKey(DictionaryPO dictPO) throws Exception {
		if(dictDao.countDictByKey(dictPO)>0){
			return false;
		}
		return true;
	}
	
	@Override
	public Boolean isUniqueValueUnderParentNode(DictionaryPO dictPO)throws Exception{
		if(dictDao.countDictByValueUnderParentNode(dictPO)>0){
			return false;
		}
		return true;
	}

	@Override
	public List<DictionaryPO> retrieveDictByPO(DictionaryPO dictPo)
			throws Exception {
		return dictDao.retrieveDictByPO(dictPo);
	}
	
	@Override
	public List<DictionaryPO> retrieveChildDictListByKey(String key)
			throws Exception {
		List<DictionaryPO> dictList=dictDao.retrieveChildDictListByKey(key);
		return dictList;
	}

	@Override
	public List<DictionaryPO> retrieveDirectChildDictListByKey(String key)
			throws Exception {
		List<DictionaryPO> dictList=dictDao.retrieveChildDictListByKey(key);
		DictionaryPO dictPO=dictDao.retrieveDictByKey(key);
		for(DictionaryPO dictTmpPO:dictList){
			if(!dictPO.getId().equals(dictTmpPO.getParentid())){
				dictList.remove(dictTmpPO);
			}
		}
		return dictList;
	}

	@Override
	public List<DictionaryPO> retrieveTermDictListByKey(String key)
			throws Exception {
		List<DictionaryPO> dictList=dictDao.retrieveChildDictListByKey(key);
		for(int i=0;i<dictList.size();i++){
			DictionaryPO dict=dictList.get(i);
			boolean termFlag=true;
			for(int j=0;j<dictList.size();j++){
				DictionaryPO dictTmp=dictList.get(j);
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
			DictionaryPO dict=dictList.get(k);
			if(dict.getTermNode()!=null && !dict.getTermNode()){
				dictList.remove(k);
			}
		}
		return dictList;
	}

	@Override
	public DictionaryPO retrieveDictByKey(String key) throws Exception {
		DictionaryPO dictPO=dictDao.retrieveDictByKey(key);
		dictPO.setFullKey(dictPO.getKey());
		if(!dictPO.getId().equals(dictPO.getParentid())){
			DictionaryPO parentDictPO=dictDao.retrieveDictionaryById(dictPO.getParentid());
			
			String parentKey=parentDictPO.getKey();
			String dictKey=dictPO.getKey();
			if(dictKey.startsWith(parentKey)){
				dictKey=dictKey.substring(parentKey.length()+1);
				dictPO.setParentKey(parentDictPO.getKey());
				dictPO.setKey(key);
			}
		}
		return dictPO;
	}

}
