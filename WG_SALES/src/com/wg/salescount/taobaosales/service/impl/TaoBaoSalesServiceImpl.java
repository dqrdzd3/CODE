package com.wg.salescount.taobaosales.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.salescount.taobaosales.dao.TaoBaoSalesDAO;
import com.wg.salescount.taobaosales.po.TaoBaoSalesPO;
import com.wg.salescount.taobaosales.service.TaoBaoSalesService;
import com.wg.salescount.util.Pager;

@Service
public class TaoBaoSalesServiceImpl implements TaoBaoSalesService{

	@Autowired
	TaoBaoSalesDAO taoBaoSalesDAO;

	@Override
	public Pager getList(TaoBaoSalesPO taobaoSalesEntity, Pager pager) {
		Pager pagerForReturn = taoBaoSalesDAO.query(pager, taobaoSalesEntity);
		return pagerForReturn;
	}

	@Override
	public List<TaoBaoSalesPO> getListWithoutPager(TaoBaoSalesPO taobaoSalesEntity) {
		 List<TaoBaoSalesPO> taoBaoSalesList = taoBaoSalesDAO.queryNoPager(taobaoSalesEntity);
		return taoBaoSalesList;
	}

	@Override
	public TaoBaoSalesPO add(TaoBaoSalesPO taoBaoSalesEntity) {
		TaoBaoSalesPO taoBaoSalesForReturn = taoBaoSalesDAO.save(taoBaoSalesEntity);
		return taoBaoSalesForReturn;
	}

	@Override
	public TaoBaoSalesPO update(TaoBaoSalesPO taoBaoSalesEntity) {
		TaoBaoSalesPO taoBaoSalesForReturn = taoBaoSalesDAO.update(taoBaoSalesEntity);
		return taoBaoSalesForReturn;
	}

	@Override
	public int delete(TaoBaoSalesPO taoBaoSalesEntity) {
		int result = taoBaoSalesDAO.delete(taoBaoSalesEntity);
		return result;
	}

	@Override
	public TaoBaoSalesPO queryById(String taoBaoSalesId) {
		TaoBaoSalesPO taoBaoSalesForReturn = taoBaoSalesDAO.queryById(taoBaoSalesId);
		return taoBaoSalesForReturn;
	}
	

	
}
