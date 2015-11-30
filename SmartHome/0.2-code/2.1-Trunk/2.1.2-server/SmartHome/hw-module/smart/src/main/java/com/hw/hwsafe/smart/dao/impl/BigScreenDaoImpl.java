package com.hw.hwsafe.smart.dao.impl;

import java.util.List;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.IBigScreenDao;
import com.hw.hwsafe.smart.pojo.AdvertisePO;
import com.hw.hwsafe.smart.pojo.BigScreenAuthPO;
import com.hw.hwsafe.smart.pojo.BigScreenPO;
import com.hw.hwsafe.smart.pojo.InfomationPO;
import com.hw.hwsafe.smart.pojo.SceneDispPO;

public class BigScreenDaoImpl extends BaseDaoImpl implements IBigScreenDao {

	@Override
	public AdvertisePO retrieveAdvertiseById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IBigScreenDao.class).retrieveAdvertiseById(ma001);
	}

	@Override
	public void deleteAdvertiseById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IBigScreenDao.class).deleteAdvertiseById(ma001);
	}

	@Override
	public void insertAdvertise(AdvertisePO a001po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IBigScreenDao.class).insertAdvertise(a001po);
	}

	@Override
	public List<AdvertisePO> retrieveAdvertiseByPO(AdvertisePO a001po)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IBigScreenDao.class).retrieveAdvertiseByPO(a001po);
	}

	@Override
	public InfomationPO retrieveMessageById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IBigScreenDao.class).retrieveMessageById(ma001);
	}

	@Override
	public void deleteMessageById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IBigScreenDao.class).deleteMessageById(ma001);
	}

	@Override
	public void insertMessage(InfomationPO a001po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IBigScreenDao.class).insertMessage(a001po);
	}

	@Override
	public List<InfomationPO> retrieveMessageByPO(InfomationPO a001po)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IBigScreenDao.class).retrieveMessageByPO(a001po);
	}

	@Override
	public void insertDisp(SceneDispPO a001po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IBigScreenDao.class).insertDisp(a001po);
	}

	@Override
	public List<SceneDispPO> retrieveDispByPO(SceneDispPO a001po)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IBigScreenDao.class).retrieveDispByPO(a001po);
	}

	@Override
	public void deleteDispById(String ma001) throws Exception {
		getSqlSession().getMapper(IBigScreenDao.class).deleteDispById(ma001);
		
	}

	@Override
	public void updateAdvertise(AdvertisePO a001po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IBigScreenDao.class).updateAdvertise(a001po);
	}

	@Override
	public List<BigScreenAuthPO> getBigscreenAuthList(String userid) {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IBigScreenDao.class).getBigscreenAuthList(userid);
	}

	@Override
	public BigScreenAuthPO auth(String mac) {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IBigScreenDao.class).auth(mac);
	}

	@Override
	public List<BigScreenPO> getBigscreenList(String userid) {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(IBigScreenDao.class).getBigscreenList(userid);
	}

	@Override
	public void applyAuth(BigScreenAuthPO po) {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(IBigScreenDao.class).applyAuth(po);
	}

}
