package com.hw.hwsafe.knowledge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.knowledge.dao.ITcorpambDao;
import com.hw.hwsafe.knowledge.pojo.TcorpambPO;
import com.hw.hwsafe.knowledge.service.ITcorpambService;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

public class TcorpambServiceImpl extends BaseServiceImpl implements ITcorpambService{
	
	@Autowired	
	private ITcorpambDao iTcorpambDao;
	
	public List<TcorpambPO> retrieveAllTcorpamb() throws Exception {
		return iTcorpambDao.retrieveAllTcorpamb();
	}

	public TcorpambPO retrieveTcorpambByID(String tcorpambID) throws Exception {
		return iTcorpambDao.retrieveTcorpambByID(tcorpambID);
	}

	public List<TcorpambPO> retrieveTcorpambByPO(TcorpambPO tcorpambPO) throws Exception {
		return iTcorpambDao.retrieveTcorpambByPO(tcorpambPO);
	}
	/**
	 * 添加记录
	 */
	public void insertTcorpamb(TcorpambPO tcorpambPO) throws Exception   {
		iTcorpambDao.insertTcorpamb(tcorpambPO);
	 }
	/**
	 * 修改记录
	 */
	public void updateTcorpamb(TcorpambPO tcorpambPO) throws Exception {
		iTcorpambDao.updateTcorpamb(tcorpambPO);
	}

	public void deleteTcorpambByID(String userID) throws Exception {
		iTcorpambDao.deleteTcorpambByID(userID);
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id) throws Exception {
		return iTcorpambDao.retrieveTcorpambByID(id) == null ? false : true;
	}
}
