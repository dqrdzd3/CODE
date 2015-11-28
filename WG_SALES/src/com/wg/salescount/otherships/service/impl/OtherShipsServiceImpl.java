package com.wg.salescount.otherships.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.salescount.otherships.dao.OtherShipsDAO;
import com.wg.salescount.otherships.po.OtherShipsPO;
import com.wg.salescount.otherships.service.OtherShipsService;
import com.wg.salescount.util.Pager;
import com.wg.salescount.weeklysales.po.WeeklySalesPO;

@Service
public class OtherShipsServiceImpl implements OtherShipsService{

	@Autowired
	OtherShipsDAO otherShipsDAO;
	
	@Override
	public Pager getList(OtherShipsPO otherShipsEntity, Pager pager) {
		Pager pagerForReturn = otherShipsDAO.query(pager, otherShipsEntity);
		return pagerForReturn;
	}

	@Override
	public List<OtherShipsPO> getListWithoutPager(OtherShipsPO otherShipsEntity) {
		List<OtherShipsPO> otherShipsList = otherShipsDAO.queryNoPager(otherShipsEntity);
		return otherShipsList;
	}

	@Override
	public OtherShipsPO add(OtherShipsPO otherShipsEntity) {
		OtherShipsPO otherShipsForReturn = otherShipsDAO.save(otherShipsEntity);
		return otherShipsForReturn;
	}

	@Override
	public OtherShipsPO update(OtherShipsPO otherShipsEntity) {
		OtherShipsPO otherShipsForReturn = otherShipsDAO.update(otherShipsEntity);
		return otherShipsForReturn;
	}

	@Override
	public int delete(OtherShipsPO otherShipsEntity) {
		int result = otherShipsDAO.delete(otherShipsEntity);
		return result;
	}

	@Override
	public OtherShipsPO queryById(String otherShipsId) {
		OtherShipsPO otherShipsForReturn = otherShipsDAO.queryById(otherShipsId);
		return otherShipsForReturn;
	}

}
