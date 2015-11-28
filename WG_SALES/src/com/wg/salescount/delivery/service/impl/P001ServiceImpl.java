package com.wg.salescount.delivery.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.salescount.delivery.dao.DeliveryBySupplierDAO;
import com.wg.salescount.delivery.dao.P001DAO;
import com.wg.salescount.delivery.po.P001PO;
import com.wg.salescount.delivery.service.P001Service;
@Service
public class P001ServiceImpl implements P001Service{

	@Autowired
	DeliveryBySupplierDAO deliveryBySupplierDAO;
	@Autowired
	P001DAO p001DAO;
	
	public P001ServiceImpl() {
	}

	@Override
	public List<P001PO> queryExist(P001PO po) {
		System.out.println("enter p001service");
		List<P001PO> p001List = new ArrayList<P001PO>();
		p001List = p001DAO.queryExist(po);
		return p001List;
	}

}
