package com.wg.salescount.delivery.service;

import java.util.List;

import com.wg.salescount.delivery.vo.DeliveryByThirdPartyVO;

public interface DeliveryByThirdPartyService {
	public DeliveryByThirdPartyVO queryCompanyId(DeliveryByThirdPartyVO vo);
	
	public DeliveryByThirdPartyVO updateP001(DeliveryByThirdPartyVO vo);
	
	public List<String> queryCompanyName();
	public List<DeliveryByThirdPartyVO> batchUpdate(final List<DeliveryByThirdPartyVO> voList);
}
