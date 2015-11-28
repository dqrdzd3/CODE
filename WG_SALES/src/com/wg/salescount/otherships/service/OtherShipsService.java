package com.wg.salescount.otherships.service;

import java.util.List;

import com.wg.salescount.otherships.po.OtherShipsPO;
import com.wg.salescount.util.Pager;

public interface OtherShipsService {

	public Pager getList(OtherShipsPO otherShipsEntity, Pager pager); 
	
	public List<OtherShipsPO> getListWithoutPager(OtherShipsPO otherShipsEntity);
	
	public OtherShipsPO add(OtherShipsPO otherShipsEntity);
	
	public OtherShipsPO update(OtherShipsPO otherShipsEntity);
	
	public int delete(OtherShipsPO otherShipsEntity);
	
	public OtherShipsPO queryById(String otherShipsId);
}
