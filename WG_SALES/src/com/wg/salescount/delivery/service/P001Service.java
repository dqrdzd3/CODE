package com.wg.salescount.delivery.service;

import java.util.List;

import com.wg.salescount.delivery.po.P001PO;

public interface P001Service {

	public List<P001PO> queryExist(P001PO po);

}
