package com.wg.salescount.weeklysales.service;

import java.util.List;

import com.wg.salescount.util.Pager;
import com.wg.salescount.weeklysales.po.WeeklySalesPO;


public interface WeeklySalesService {
		
	public Pager getList(WeeklySalesPO weeklySalesEntity,Pager pager);
	
	public List<WeeklySalesPO> getListWithoutPager(WeeklySalesPO weeklySalesEntity);
	
	public WeeklySalesPO add(WeeklySalesPO weeklySalesEntity);
	
	public WeeklySalesPO update(WeeklySalesPO weeklySalesEntity);
	
	public int delete(WeeklySalesPO weeklySalesEntity);
	
	public WeeklySalesPO queryById(String weeklySalesId);
}
