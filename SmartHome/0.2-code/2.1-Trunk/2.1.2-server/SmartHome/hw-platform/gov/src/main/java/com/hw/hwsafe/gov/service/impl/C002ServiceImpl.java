/**
 * 文件名：C002ServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.gov.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.gov.dao.IC002Dao;
import com.hw.hwsafe.gov.pojo.C002PO;
import com.hw.hwsafe.gov.service.IC002Service;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.exception.jsonmsg.JsonMsgException;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：C002ServiceImpl
 * 类描述：政府机构服务实现类
 * 创建人：马宁
 * 创建时间：2012-9-29 上午11:23:42
 * 修改人：马宁
 * 修改时间：2012-9-29 上午11:23:42
 * 修改备注：
 * @version 
 *
 */
public class C002ServiceImpl extends BaseServiceImpl implements
		IC002Service {

	@Autowired
	private IC002Dao c002Dao;

	// ------------- methods -------------

	@Override
	public List<C002PO> retrieveC002ByPO(C002PO c002PO)
			throws Exception {
		return c002Dao.retrieveC002ByPO(c002PO);
	}

	@Override
	public C002PO retrieveC002ByMa001(String ma001) throws Exception {
		C002PO c002PO = c002Dao.retrieveC002ByMa001(ma001);
		return c002PO;
	}

	@Override
	public C002PO retrieveC002ByMa002(String fullMa002) throws Exception {
		C002PO c002PO = c002Dao.retrieveC002ByMa002(fullMa002);
		return c002PO;
	}

	@Override
	public List<C002PO> retrieveAll() throws Exception {
		return c002Dao.retrieveAll();
	}
	
	@Override
	public List<C002PO> retrieveAllValid() throws Exception {
		return c002Dao.retrieveAllValid();
	}
	
	@Override
	public Integer countC002ByMa002(String ma002) throws Exception {
		return c002Dao.countC002ByMa002(ma002);
	}

	@Override
	public UserMessageData insertC002(C002PO c002PO) throws JsonMsgException {
		UserMessageData message=new UserMessageData();
		c002PO.setMa001(UUIDGenerater.getUUID());
		c002PO.setMa017(C002PO.MA017_VALID);
		if (null == c002PO.getMa004() || "".equals(c002PO.getMa004())) {
			c002PO.setMa004(c002PO.getMa001());
		}
		int optRecNum;
		try {
			optRecNum = c002Dao.insertC002(c002PO);
			if(optRecNum==1){
				message.set(Constants.MSG_OK, Constants.ADD_SUCCESS_MSG);
			}else{
				message.set(Constants.MSG_WARNING, Constants.ADD_FAILED_MSG);
//				throw new JsonMsgException(Constants.MSG_WARNING, "添加失败");
			}
		} catch (Exception e) {
			throw new JsonMsgException(Constants.MSG_ERROR, "添加失败", e);
		}
		return message;
	}

	@Override
	public UserMessageData updateC002(C002PO c002PO) throws Exception {
		UserMessageData message=new UserMessageData();
		if (null == c002PO.getMa004() || "".equals(c002PO.getMa004())) {
			c002PO.setMa004(c002PO.getMa001());
		}
		int optRecNum=c002Dao.updateC002(c002PO);
		if(optRecNum==1){
			message.set(Constants.MSG_OK, Constants.UPDATE_SUCCESS_MSG);
		}else{
			message.set(Constants.MSG_ERROR, Constants.EMPTY_DATA_MSG);
		}
		return message;
	}

	@Override
	public List<C002PO> retrieveChildC002ListByMa002(String ma002)
			throws Exception {
		return c002Dao.retrieveChildC002ListByMa002(ma002);
	}

	@Override
	public boolean isUniqueName(C002PO c002po) {
		boolean result = false;
		Integer count = c002Dao.countInstanceByMa003(c002po);
		if(count == 0){
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean isUniqueCode(C002PO c002PO) {
		boolean result = false;
		Integer count = c002Dao.countInstanceByCode(c002PO);
		if(count == 0){
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean isUniqueMa007(C002PO c002PO){
		boolean result = false;
		Integer count = c002Dao.countInstanceByMa007(c002PO);
		if(count == 0){
			result = true;
		}
		return result;
	}

	@Override
	public UserMessageData deleteC002ByIds(String ids) throws Exception{
		UserMessageData message=new UserMessageData();
		Map<String,Object> args=new HashMap<String,Object>();
		String[] idArray=ids.split(",");
		args.put("ids", idArray);
		Integer childNodeNum=c002Dao.countChildByIds(args);
		if(childNodeNum>0){
			message.set(Constants.MSG_ERROR, "包含子机构不能删除！");
		}else{
			Integer recNum=c002Dao.deleteC002ByIds(args);
			if(recNum.intValue()==idArray.length){
				message.set(Constants.MSG_OK, Constants.DEL_SUCCESS_MSG);
			}else if(recNum.intValue()<idArray.length){
//				message.set(Constants.MSG_WARNING, Constants.DEL_SUCCESS_MSG_PRE+recNum+Constants.DEL_SUCCESS_MSG_SUF);
				message.set(Constants.MSG_OK, Constants.DEL_SUCCESS_MSG);
			}
		}
		return message;
	}
	
	@Override
	public C002PO getC002InfoByCorpId(String corpId) throws Exception {
		return c002Dao.getC002InfoByCorpId(corpId);
	}
	
	@Override
	public String insertORG(C002PO c002PO) throws Exception {
		String uuid = UUIDGenerater.getUUID();
		c002PO.setMa001(uuid);
		c002PO.setMa017(C002PO.MA017_VALID);
		if (null == c002PO.getMa004() || "".equals(c002PO.getMa004())) {
			c002PO.setMa004(c002PO.getMa001());
		}
		c002Dao.insertC002(c002PO);
		return uuid;
	}
	
	@Override
	public boolean updateORG(C002PO c002PO) throws Exception {
		if (null == c002PO.getMa004() || "".equals(c002PO.getMa004())) {
			c002PO.setMa004(c002PO.getMa001());
		}
		int optRecNum=c002Dao.updateC002(c002PO);
		if(optRecNum==1){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean deleteORGByIds(String ids) throws Exception {
		Map<String,Object> args=new HashMap<String,Object>();
		String[] idArray=ids.split(",");
		args.put("ids", idArray);
		Integer childNodeNum=c002Dao.countChildByIds(args);
		if(childNodeNum>0){
			System.out.println("包含子机构不能删除！");
			return false;
		}else{
			Integer recNum=c002Dao.deleteC002ByIds(args);
			return true;
		}
	}
	
	// ------------- setter and getter ---------------
	
	public IC002Dao getC002Dao() {
		return c002Dao;
	}

	public void setC002Dao(IC002Dao c002Dao) {
		this.c002Dao = c002Dao;
	}

	
}
