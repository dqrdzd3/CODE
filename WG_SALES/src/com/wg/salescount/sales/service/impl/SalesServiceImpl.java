package com.wg.salescount.sales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.salescount.sales.dao.SalesDAO;
import com.wg.salescount.sales.po.SalesPO;
import com.wg.salescount.sales.service.SalesService;
import com.wg.salescount.util.Pager;
import com.wg.salescount.weeklysales.po.WeeklySalesPO;

@Service
public class SalesServiceImpl implements SalesService{

	@Autowired
	SalesDAO salesDAO;
	
	@Override
	public Pager getList(SalesPO salesEntity, Pager pager) {
		Pager pagerForReturn = salesDAO.query(pager, salesEntity);
		return pagerForReturn;
	}

	@Override
	public List<SalesPO> getListWithoutPager(SalesPO salesEntity) {
		List<SalesPO> salesList = salesDAO.queryNoPager(salesEntity);
		return salesList;
	}

	@Override
	public SalesPO add(SalesPO salesEntity) {
		SalesPO salesForReturn = salesDAO.save(salesEntity);
		return salesForReturn;
	}

	@Override
	public SalesPO update(SalesPO salesEntity) {
		SalesPO salesForReturn = salesDAO.update(salesEntity);
		return salesForReturn;
	}

	@Override
	public int delete(SalesPO salesEntity) {
		int result = salesDAO.delete(salesEntity);
		return result;
	}

	@Override
	public SalesPO queryById(String salesId) {
		SalesPO salesForReturn = salesDAO.queryById(salesId);
		return salesForReturn;
	}

}
