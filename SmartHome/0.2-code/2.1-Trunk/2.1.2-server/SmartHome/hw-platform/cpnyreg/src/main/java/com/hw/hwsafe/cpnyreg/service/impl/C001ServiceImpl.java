/**
 * 文件名：C001ServiceImpl.java
 *
 * 版本信息：
 * 日期：2012-8-6
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.cpnyreg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.cpnyreg.dao.IC001Dao;
import com.hw.hwsafe.cpnyreg.pojo.C001PO;
import com.hw.hwsafe.cpnyreg.service.IC001Service;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：C001ServiceImpl
 * 类描述：
 * 创建人：付强
 * 创建时间：2012-8-6 下午2:41:55
 * 
 */
public class C001ServiceImpl extends BaseServiceImpl implements IC001Service {

	@Autowired
	private IC001Dao c001Dao;
	
	@Override
	public C001PO retrieveC001ByMA001(String MA001) throws Exception {
		C001PO c001Po=c001Dao.retrieveC001ByMA001(MA001);
		return c001Po;
	}

	@Override
	public UserMessageData insertC001(C001PO c001po) throws Exception {
		UserMessageData message=new UserMessageData();
		c001po.setMA001(UUIDGenerater.getUUID());
		c001po.setMA012(DateTimeUtils.getCurrentDate());
		c001po.setMA017(C001PO.MA017_VALID);
		int optRecNum=c001Dao.insertC001(c001po);
		if(optRecNum==1){
			message.set(Constants.MSG_OK, Constants.ADD_SUCCESS_MSG);
		}else{
			message.set(Constants.MSG_ERROR, Constants.ADD_FAILED_MSG);
		}
		return message;
	}

	@Override
	public UserMessageData updateC001(C001PO c001po) throws Exception {
		UserMessageData message=new UserMessageData();
		c001po.setMA014(DateTimeUtils.getCurrentDate());
		int optRecNum=c001Dao.updateC001(c001po);
		if(optRecNum==1){
			message.set(Constants.MSG_OK, Constants.UPDATE_SUCCESS_MSG);
		}else{
			message.set(Constants.MSG_ERROR, Constants.EMPTY_DATA_MSG);
		}
		return message;
	}

	@Override
	public UserMessageData deleteC001ByIds(String ids) throws Exception {
		UserMessageData message=new UserMessageData();
		Map<String,Object> args=new HashMap<String,Object>();
		String[] idArray=ids.split(",");
		args.put("ids", idArray);
		Integer recNum=c001Dao.deleteC001ByIds(args);
		if(recNum.intValue()==idArray.length){
			message.set(Constants.MSG_OK, Constants.DEL_SUCCESS_MSG);
		}else if(recNum.intValue()<idArray.length){
//			message.set(Constants.MSG_WARNING, Constants.DEL_SUCCESS_MSG_PRE+recNum+Constants.DEL_SUCCESS_MSG_SUF);
			message.set(Constants.MSG_OK, Constants.DEL_SUCCESS_MSG);
		}
		return message;
	}
	
	@Override
	public boolean isUniqueMa002(C001PO c001po) throws Exception {
		boolean result = false;
		Integer count = c001Dao.countInstanceByMa002(c001po);
		if(count == 0){
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean isUniqueMa003(C001PO c001PO)throws Exception{
		if(c001Dao.countC001ByMa003(c001PO)>0){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean isUniqueMa007(C001PO c001PO)throws Exception{
		if(c001Dao.countC001ByMa007(c001PO)>0){
			return false;
		}
		return true;
	}

	@Override
	public List<C001PO> retrieveAll() throws Exception {
		return c001Dao.retrieveAll();
	}

	@Override
	public List<C001PO> retrieveAllValid() throws Exception {
		return c001Dao.retrieveAllValid();
	}
	
	@Override
	public List<C001PO> retrieveByCondition(Map map) throws Exception {
		return c001Dao.retrieveByCondition(map);
	}

	@Override
	public Integer retrieveCountByCondition(Map map) throws Exception {
		return c001Dao.retrieveCountByCondition(map);
	}

	@Override
	public C001PO queryC001POByMa003(String ma003) throws Exception {
		return c001Dao.queryC001POByMa003(ma003);
	}

	@Override
	public Integer retrieveMa009ByMa001(String ma001) throws Exception {
		return c001Dao.retrieveMa009ByMa001(ma001);
	}

	@Override
	public List<Map<String, Object>> retrieveOrgan() throws Exception {
		return c001Dao.retrieveOrgan();
	}

}
