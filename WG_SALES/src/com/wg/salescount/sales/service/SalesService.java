package com.wg.salescount.sales.service;

import java.util.List;

import com.wg.salescount.sales.po.SalesPO;
import com.wg.salescount.util.Pager;
import com.wg.salescount.weeklysales.po.WeeklySalesPO;

public interface SalesService {

	public Pager getList(SalesPO salesEntity,Pager pager);
	
	public List<SalesPO> getListWithoutPager(SalesPO salesEntity);
	
	public SalesPO add(SalesPO salesEntity);
	
	public SalesPO update(SalesPO salesEntity);
	
	public int delete(SalesPO salesEntity);
	
	public SalesPO queryById(String salesId);
}
