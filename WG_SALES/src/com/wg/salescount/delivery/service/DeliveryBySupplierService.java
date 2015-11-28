package com.wg.salescount.delivery.service;

import java.util.List;

import com.wg.salescount.delivery.vo.DeliveryBySupplierVO;

public interface DeliveryBySupplierService {
	public DeliveryBySupplierVO queryCompanyId(DeliveryBySupplierVO vo);
	
	public DeliveryBySupplierVO updateP001(DeliveryBySupplierVO vo);
	
	public List<String> queryCompanyName();
	
	public List<DeliveryBySupplierVO> batchUpdate(final List<DeliveryBySupplierVO> voList);
	
}
