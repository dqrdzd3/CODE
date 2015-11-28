package com.wg.salescount.taobaosales.service;

import java.util.List;

import com.wg.salescount.taobaosales.po.TaoBaoSalesPO;
import com.wg.salescount.util.Pager;

public interface TaoBaoSalesService {
	
	public Pager getList(TaoBaoSalesPO taobaoSalesEntity, Pager pager);
	
	public List<TaoBaoSalesPO> getListWithoutPager(TaoBaoSalesPO taobaoSalesEntity);
	
	public TaoBaoSalesPO add(TaoBaoSalesPO taoBaoSalesEntity);
	
	public TaoBaoSalesPO update(TaoBaoSalesPO taoBaoSalesEntity);
	
	public int delete(TaoBaoSalesPO taoBaoSalesEntity);
	
	public TaoBaoSalesPO queryById(String taoBaoSalesId);
}
