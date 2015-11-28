package com.wg.salescount.weeklysales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.salescount.util.Pager;
import com.wg.salescount.weeklysales.dao.WeeklySalesDAO;
import com.wg.salescount.weeklysales.po.WeeklySalesPO;
import com.wg.salescount.weeklysales.service.WeeklySalesService;

@Service
public class WeeklySalesServiceImpl implements WeeklySalesService{
	
	@Autowired
	WeeklySalesDAO weeklySalesDAO;

	@Override
	public Pager getList(WeeklySalesPO weeklySalesEntity, Pager pager) {
		Pager pagerForReturn = weeklySalesDAO.query(pager, weeklySalesEntity);
		return pagerForReturn;
	}

	@Override
	public List<WeeklySalesPO> getListWithoutPager(WeeklySalesPO weeklySalesEntity) {
		List<WeeklySalesPO> weeklySalesList = weeklySalesDAO.queryNoPager(weeklySalesEntity);
		return weeklySalesList;
	}

	@Override
	public WeeklySalesPO add(WeeklySalesPO weeklySalesEntity) {
		WeeklySalesPO weeklySalesForReturn = weeklySalesDAO.save(weeklySalesEntity);
		return weeklySalesForReturn;
	}

	@Override
	public WeeklySalesPO update(WeeklySalesPO weeklySalesEntity) {
		WeeklySalesPO weeklySalesForReturn = weeklySalesDAO.update(weeklySalesEntity);
		return weeklySalesForReturn;
	}

	@Override
	public int delete(WeeklySalesPO weeklySalesEntity) {
		int result = weeklySalesDAO.delete(weeklySalesEntity);
		return result;
	}

	@Override
	public WeeklySalesPO queryById(String weeklySalesId) {
		WeeklySalesPO weeklySalesForReturn = weeklySalesDAO.queryById(weeklySalesId);
		return weeklySalesForReturn;
	}

}
