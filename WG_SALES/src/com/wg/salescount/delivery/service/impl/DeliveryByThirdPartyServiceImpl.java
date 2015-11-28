package com.wg.salescount.delivery.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.salescount.delivery.dao.DeliveryByThirdPartyDAO;
import com.wg.salescount.delivery.service.DeliveryByThirdPartyService;
import com.wg.salescount.delivery.vo.DeliveryByThirdPartyVO;

@Service
public class DeliveryByThirdPartyServiceImpl implements DeliveryByThirdPartyService{

	@Autowired
	DeliveryByThirdPartyDAO deliveryByThirdPartyDAO;
	
	public DeliveryByThirdPartyServiceImpl() {
	}

	@Override
	public DeliveryByThirdPartyVO queryCompanyId(DeliveryByThirdPartyVO vo) {
		System.out.println("service="+vo);
		DeliveryByThirdPartyVO deliveryByThirdParty = deliveryByThirdPartyDAO.queryCompanyId(vo);
		return deliveryByThirdParty;
	}

	@Override
	public DeliveryByThirdPartyVO updateP001(DeliveryByThirdPartyVO vo) {
		DeliveryByThirdPartyVO deliveryByThirdPartyForReturn = deliveryByThirdPartyDAO.update(vo);
		return deliveryByThirdPartyForReturn;
	}

	@Override
	public List<String> queryCompanyName() {
		List<String> companyName = new ArrayList<String>();
		companyName = deliveryByThirdPartyDAO.queryCompanyName();
		return companyName;
	}

	@Override
	public List<DeliveryByThirdPartyVO> batchUpdate(List<DeliveryByThirdPartyVO> voList) {
		List<DeliveryByThirdPartyVO> deliveryByThirdPartyForReturn = deliveryByThirdPartyDAO.batchUpdate(voList);
		return deliveryByThirdPartyForReturn;
	}

}
