package com.wg.salescount.delivery.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.salescount.delivery.dao.DeliveryBySupplierDAO;
import com.wg.salescount.delivery.dao.P001DAO;
import com.wg.salescount.delivery.service.DeliveryBySupplierService;
import com.wg.salescount.delivery.vo.DeliveryBySupplierVO;

@Service
public class DeliveryBySupplierServiceImpl implements DeliveryBySupplierService{

	@Autowired
	DeliveryBySupplierDAO deliveryBySupplierDAO;
	@Autowired
	P001DAO p001DAO;
	
	public DeliveryBySupplierServiceImpl() {
	}

	@Override
	public DeliveryBySupplierVO queryCompanyId(DeliveryBySupplierVO vo) {
		DeliveryBySupplierVO deliveryBySupplier = deliveryBySupplierDAO.queryCompanyId(vo);
		return deliveryBySupplier;
	}

	@Override
	public DeliveryBySupplierVO updateP001(DeliveryBySupplierVO vo) {
		DeliveryBySupplierVO deliveryBySupplierForReturn = deliveryBySupplierDAO.update(vo);
		return deliveryBySupplierForReturn;
	}

	@Override
	public List<String> queryCompanyName() {
		List<String> companyName = new ArrayList<String>();
		companyName = deliveryBySupplierDAO.queryCompanyName();
		return companyName;
	}

	@Override
	public List<DeliveryBySupplierVO> batchUpdate(List<DeliveryBySupplierVO> voList) {
		List<DeliveryBySupplierVO> listForReturn = deliveryBySupplierDAO.batchUpdate(voList);
		return listForReturn;
	}

}
