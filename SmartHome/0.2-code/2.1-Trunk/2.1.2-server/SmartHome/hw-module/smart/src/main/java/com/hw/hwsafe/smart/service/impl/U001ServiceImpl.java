package com.hw.hwsafe.smart.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IU001Dao;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.IU001Service;
import com.hw.hwsafe.utils.PasswordUtil;
import com.hwsensor.permission.dao.IUserDao;


public class U001ServiceImpl extends BaseServiceImpl implements IU001Service {
	
	@Autowired
	private IU001Dao u001Dao;
	
	@Autowired
	private IUserDao userDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return u001Dao.retrieveByPage(map);
	}

	@Override
	public U001PO retrieveInstanceById(String ma001) throws Exception {
		return u001Dao.retrieveInstanceById(ma001);
	}
	
	@Override
	public U001PO retrieveInstanceByToken(String ma010) throws Exception {
		return u001Dao.retrieveInstanceByToken(ma010);
	}

	@Override
	public List<U001PO> retrieveInstanceByPO(U001PO u001po) throws Exception {
		return u001Dao.retrieveInstanceByPO(u001po);
	}

	@Override
	public void insertInstance(U001PO u001po) throws Exception {
		UserPO userPO = new UserPO();
		userPO.setUUID(u001po.getMa001());
		userPO.setLOGIN_NAME(u001po.getMa008());
		userPO.setPassword(u001po.getMa009());
		userPO.setEMAIL(u001po.getMa005());
		userPO.setMOBILE_NUMBER(u001po.getMa006());
		userDao.insertSysUser(userPO);
		u001Dao.insertInstance(u001po);
	}

	@Override
	public void updateInstance(U001PO u001po) throws Exception {
		u001Dao.updateInstance(u001po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		u001Dao.deleteInstanceById(ma001);
	}
	
	@Override
	public boolean checkAccount(U001PO u001PO) throws Exception{
		List<U001PO> list = u001Dao.retrieveInstanceByPO(u001PO);
		if(list.size() > 0){
			return true; //重复
		}else{
			return false;//不重复
		}
	}

	public U001PO checkUserBySessionId(String sessionId) throws Exception {
		U001PO u001PO = u001Dao.retrieveInstanceByToken(sessionId);
		if (u001PO != null) {
			return u001PO;
		} else {
			return null;
		}
	}
	
	@Override
    public String GetMD5Code(String str1,String str2){
        String str1Md5 = PasswordUtil.createPassword(str1);
        StringBuilder sb = new StringBuilder();
        sb.append(str1Md5);
        sb.append(str2);
        String finaStr = sb.toString();
        String finalMd5 = PasswordUtil.createPassword(finaStr);
        return finalMd5;
    }

	@Override
	public void updateYZM(String ma001) throws Exception {
		// TODO Auto-generated method stub
		u001Dao.updateYZM(ma001);
	}

	@Override
	public void sendYZM(HashMap<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		u001Dao.sendYZM(map);
	}

	@Override
	public Integer getUserCount() throws Exception {
		// TODO Auto-generated method stub
		return u001Dao.getUserCount();
	}

}
