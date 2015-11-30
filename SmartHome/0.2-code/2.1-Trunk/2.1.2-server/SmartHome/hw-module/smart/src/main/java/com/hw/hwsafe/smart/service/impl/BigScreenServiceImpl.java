package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IBigScreenDao;
import com.hw.hwsafe.smart.pojo.AdvertisePO;
import com.hw.hwsafe.smart.pojo.BigScreenAuthPO;
import com.hw.hwsafe.smart.pojo.BigScreenPO;
import com.hw.hwsafe.smart.pojo.InfomationPO;
import com.hw.hwsafe.smart.pojo.SceneDispPO;
import com.hw.hwsafe.smart.service.IBigScreenService;

public class BigScreenServiceImpl extends BaseServiceImpl implements
		IBigScreenService {

	@Autowired
	private IBigScreenDao bigScreenDao;
	
	@Override
	public AdvertisePO retrieveAdvertiseById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return bigScreenDao.retrieveAdvertiseById(ma001);
	}

	@Override
	public void deleteAdvertiseById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		bigScreenDao.deleteAdvertiseById(ma001);
	}

	@Override
	public void insertAdvertise(AdvertisePO a001po) throws Exception {
		// TODO Auto-generated method stub
		bigScreenDao.insertAdvertise(a001po);
	}

	@Override
	public List<AdvertisePO> retrieveAdvertiseByPO(AdvertisePO a001po)
			throws Exception {
		// TODO Auto-generated method stub
		return bigScreenDao.retrieveAdvertiseByPO(a001po);
	}

	@Override
	public InfomationPO retrieveMessageById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return bigScreenDao.retrieveMessageById(ma001);
	}

	@Override
	public void deleteMessageById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		bigScreenDao.deleteMessageById(ma001);
	}

	@Override
	public void insertMessage(InfomationPO a001po) throws Exception {
		// TODO Auto-generated method stub
		bigScreenDao.insertMessage(a001po);
	}

	@Override
	public List<InfomationPO> retrieveMessageByPO(InfomationPO a001po)
			throws Exception {
		// TODO Auto-generated method stub
		return bigScreenDao.retrieveMessageByPO(a001po);
	}

	@Override
	public void insertDisp(SceneDispPO a001po) throws Exception {
		// TODO Auto-generated method stub
		bigScreenDao.insertDisp(a001po);
	}

	@Override
	public List<SceneDispPO> retrieveDispByPO(SceneDispPO a001po)
			throws Exception {
		// TODO Auto-generated method stub
		return bigScreenDao.retrieveDispByPO(a001po);
	}

	@Override
	public void deleteDispById(String ma001) throws Exception {
		bigScreenDao.deleteDispById(ma001);
		
	}

	@Override
	public void updateAdvertise(AdvertisePO a001po) throws Exception {
		// TODO Auto-generated method stub
		bigScreenDao.updateAdvertise(a001po);
	}

	@Override
	public List<BigScreenAuthPO> getBigscreenAuthList(String userid) {
		// TODO Auto-generated method stub
		return bigScreenDao.getBigscreenAuthList(userid);
	}

	@Override
	public BigScreenAuthPO auth(String mac) {
		// TODO Auto-generated method stub
		return bigScreenDao.auth(mac);
	}

	@Override
	public List<BigScreenPO> getBigscreenList(String userid) {
		// TODO Auto-generated method stub
		return bigScreenDao.getBigscreenList(userid);
	}

	@Override
	public void applyAuth(BigScreenAuthPO po) {
		// TODO Auto-generated method stub
		 bigScreenDao.applyAuth(po);
	}

}
