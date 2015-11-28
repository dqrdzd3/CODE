package com.wg.salescount.delivery.dao;

import java.util.List;

import com.wg.salescount.basedao.BaseDAO;
import com.wg.salescount.delivery.vo.DeliveryByThirdPartyVO;

public interface DeliveryByThirdPartyDAO extends BaseDAO<DeliveryByThirdPartyVO>{
	public DeliveryByThirdPartyVO queryCompanyId(DeliveryByThirdPartyVO vo);
	
	public List<String> queryCompanyName();
	public List<DeliveryByThirdPartyVO> batchUpdate(final List<DeliveryByThirdPartyVO> voList);
	
}
