package com.wg.salescount.delivery.dao;

import java.util.List;

import com.wg.salescount.basedao.BaseDAO;
import com.wg.salescount.delivery.vo.DeliveryBySupplierVO;

public interface DeliveryBySupplierDAO extends BaseDAO<DeliveryBySupplierVO>{
	public DeliveryBySupplierVO queryCompanyId(DeliveryBySupplierVO vo);
	
	public List<String> queryCompanyName();
	
	public List<DeliveryBySupplierVO> batchUpdate(final List<DeliveryBySupplierVO> voList);
}
